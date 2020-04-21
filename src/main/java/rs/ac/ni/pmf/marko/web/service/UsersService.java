package rs.ac.ni.pmf.marko.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.marko.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.marko.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.web.model.UsersSearchOptions;
import rs.ac.ni.pmf.marko.web.model.api.UserDTO;
import rs.ac.ni.pmf.marko.web.model.api.UserTicketLiteDTO;
import rs.ac.ni.pmf.marko.web.model.entity.MessageEntity;
import rs.ac.ni.pmf.marko.web.model.entity.MessageEntity_;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity_;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity_;
import rs.ac.ni.pmf.marko.web.model.mapper.UsersMapper;
import rs.ac.ni.pmf.marko.web.repository.UsersRepository;
import rs.ac.ni.pmf.marko.web.repository.specification.UsersSearchSpecification;

@Service
@RequiredArgsConstructor
public class UsersService {

	private static final Integer DEFAULT_PAGE_SIZE = 20;

	private final UsersRepository usersRepository;
	private final UsersMapper usersMapper;
	
	private final EntityManager entityManager;

	public Page<UserDTO> getAllUsers(final UsersSearchOptions searchOptions) {

		final PageRequest pageRequest;
		
		if (searchOptions.getPage() != null) {
			pageRequest = PageRequest.of(searchOptions.getPage(), searchOptions.getSize() != null ? searchOptions.getSize() : DEFAULT_PAGE_SIZE);
		}
		else {
			pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
		}

		return usersRepository
				.findAll(new UsersSearchSpecification(searchOptions), pageRequest)
				.map(usersMapper::toDto);
	}

	public UserDTO getUser(final String username) throws ResourceNotFoundException {
		final UserEntity userEntity = usersRepository.findById(username)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER, "User with username '" + username + "' does not exist"));

		return usersMapper.toDto(userEntity);
	}

	public UserDTO addUser(final UserDTO userDto) throws DuplicateResourceException {
		final String username = userDto.getUsername();

		if (usersRepository.existsById(username)) {
			throw new DuplicateResourceException(ResourceType.USER, "User '" + username + "' already exists!");
		}

		final UserEntity userEntity = usersMapper.toEntity(userDto);
		UserEntity savedEntity = usersRepository.save(userEntity);
		return usersMapper.toDto(savedEntity);
	}

	public void deleteUser(final String username) throws ResourceNotFoundException {
		if (!usersRepository.existsById(username)) {
			throw new ResourceNotFoundException(ResourceType.USER, "User '" + username + "' not found");
		}

		usersRepository.deleteById(username);
	}

	public List<UserTicketLiteDTO> getTicketsLite(final String username) {
		
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		final CriteriaQuery<UserTicketLiteDTO> cq = cb.createQuery(UserTicketLiteDTO.class);
		
		final Root<UserEntity> root = cq.from(UserEntity.class);
		final Join<UserEntity, TicketEntity> ticketsJoin = root.join(UserEntity_.tickets);
		
		final Path<String> usernamePath = root.get(UserEntity_.username);
		final Path<String> ticketTitle = ticketsJoin.get(TicketEntity_.title);
		
		final List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(cb.equal(usernamePath, username));
		
		cq.multiselect(usernamePath, ticketTitle);
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
//		cq.where(cb.equal(usernamePath, username));
		
		return entityManager.createQuery(cq).getResultList();
	}

	public long countMessages(String username) {
		
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		
		final Root<MessageEntity> root = cq.from(MessageEntity.class);
		final Join<MessageEntity, UserEntity> usersJoin = root.join(MessageEntity_.user, JoinType.INNER);

		final Path<String> usernamePath = usersJoin.get(UserEntity_.username);
		
		cq.select(cb.count(root));
		cq.where(cb.equal(usernamePath, username));
		
		return entityManager.createQuery(cq).getSingleResult();
	}
}

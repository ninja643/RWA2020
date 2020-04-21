package rs.ac.ni.pmf.marko.web.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.marko.web.model.UsersSearchOptions;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity_;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity_;

@RequiredArgsConstructor
public class UsersSearchSpecification implements Specification<UserEntity> {
	private static final long serialVersionUID = 1L;

	private final UsersSearchOptions searchOptions;

	@Override
	public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		final List<Predicate> predicates = new ArrayList<>();

		final Path<String> firstName = root.get(UserEntity_.firstName);
		final Path<String> lastName = root.get(UserEntity_.lastName);
		final Path<String> username = root.get(UserEntity_.username);

		final String firstNameFilter = searchOptions.getFirstNameFilter();

		if (firstNameFilter != null && !firstNameFilter.trim().isEmpty()) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(firstName), "%" + firstNameFilter + "%"));
		}

		final String lastNameFilter = searchOptions.getLastNameFilter();

		if (lastNameFilter != null && !lastNameFilter.trim().isEmpty()) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(lastName), "%" + lastNameFilter + "%"));
		}

		if (searchOptions.getMinTicketsCount() != null) {
			final Join<UserEntity, TicketEntity> ticketsJoin = root.join(UserEntity_.tickets, JoinType.INNER);
			final Path<Integer> ticketId = ticketsJoin.get(TicketEntity_.id);

			query.groupBy(username);
			query.having(criteriaBuilder.ge(criteriaBuilder.count(ticketId), searchOptions.getMinTicketsCount()));
		}

		query.orderBy(criteriaBuilder.asc(lastName), criteriaBuilder.asc(firstName));
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}

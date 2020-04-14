package rs.ac.ni.pmf.marko.web.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.marko.web.model.entity.TicketEntity;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity;
import rs.ac.ni.pmf.marko.web.model.entity.UserEntity_;
import rs.ac.ni.pmf.marko.web.search.UserSearchOptions;

@RequiredArgsConstructor
public class UserSpecification implements Specification<UserEntity> {

	private static final long serialVersionUID = 1L;

	private final UserSearchOptions options;

	@Override
	public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		final List<Predicate> predicates = new ArrayList<>();

		Path<String> username = root.get(UserEntity_.username);
		Path<String> firstName = root.get(UserEntity_.firstName);
		Path<String> lastName = root.get(UserEntity_.lastName);

		final String firstNameFilter = options.getFirstNameFilter();
		final String lastNameFilter = options.getLastNameFilter();

		if (firstNameFilter != null && !firstNameFilter.trim().isEmpty()) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(firstName), firstNameFilter.toLowerCase() + "%"));
		}

		if (lastNameFilter != null && !lastNameFilter.trim().isEmpty()) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(lastName), lastNameFilter.toLowerCase() + "%"));
		}

		Path<TicketEntity> tickets = root.join(UserEntity_.tickets);
		query.groupBy(username);
		query.having(criteriaBuilder.ge(criteriaBuilder.count(tickets), 1));

		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}

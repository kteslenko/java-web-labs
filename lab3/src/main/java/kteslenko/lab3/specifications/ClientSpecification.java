package kteslenko.lab3.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import kteslenko.lab3.model.Client;
import kteslenko.lab3.model.Gender;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientSpecification {
    @AllArgsConstructor
    @Getter
    public enum SortColumn {
        FIRST_NAME("firstName", "First Name"),
        LAST_NAME("lastName", "Last Name"),
        PHONE("phone", "Phone"),
        EMAIL("email", "Email");

        private final String name;
        private final String displayName;
    }

    private SortColumn sortColumn = SortColumn.FIRST_NAME;
    private SortDirection sortDirection = SortDirection.ASC;

    private String firstName = "";
    private String lastName = "";
    private String phone = "";
    private String email = "";
    private List<Gender> genders = Collections.emptyList();
    private Boolean isVIP = null;

    public Specification<Client> get() {
        return (root, query, builder) -> {
            String likeFilter = "%%%s%%";
            List<Predicate> predicates = new ArrayList<>();

            if (!firstName.isBlank()) {
                predicates.add(builder.like(root.get("firstName"), likeFilter.formatted(firstName)));
            }

            if (!lastName.isBlank()) {
                predicates.add(builder.like(root.get("lastName"), likeFilter.formatted(lastName)));
            }

            if (!phone.isBlank()) {
                predicates.add(builder.like(root.get("phone"), likeFilter.formatted(phone)));
            }

            if (!email.isBlank()) {
                predicates.add(builder.like(root.get("email"), likeFilter.formatted(email)));
            }

            if (!genders.isEmpty()) {
                CriteriaBuilder.In<Gender> in = builder.in(root.get("gender"));
                for (Gender gender : genders) {
                    in.value(gender);
                }
                predicates.add(in);
            }

            if (isVIP != null) {
                predicates.add(builder.equal(root.get("isVIP"), isVIP));
            }

            if (sortDirection == SortDirection.ASC) {
                query.orderBy(builder.asc(root.get(sortColumn.getName())));
            } else {
                query.orderBy(builder.desc(root.get(sortColumn.getName())));
            }

            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }
}

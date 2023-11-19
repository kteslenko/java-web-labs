package kteslenko.lab3.specifications;

import jakarta.persistence.criteria.Predicate;
import kteslenko.lab3.model.Address;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressSpecification {
    @AllArgsConstructor
    @Getter
    public enum SortColumn {
        COD_ADDRESS("codAddress", "Cod Address"),
        CITY("city", "City"),
        PHONE("address", "Address"),
        POINT_NP("pointNp", "Point NP"),
        POINT_UKR_POST("pointUkrPost", "Point Ukr Post");

        private final String name;
        private final String displayName;
    }

    private SortColumn sortColumn = SortColumn.COD_ADDRESS;
    private SortDirection sortDirection = SortDirection.ASC;

    private Long clientId = null;
    private String codAddress = "";
    private String city = "";
    private String address = "";
    private Integer pointNp = null;
    private Integer pointUkrPost = null;

    public Specification<Address> get() {
        return (root, query, builder) -> {
            String likeFilter = "%%%s%%";
            List<Predicate> predicates = new ArrayList<>();

            if (clientId != null) {
                predicates.add(builder.equal(root.get("client").get("id"), clientId));
            }

            if (!codAddress.isBlank()) {
                predicates.add(builder.like(root.get("codAddress"), likeFilter.formatted(codAddress)));
            }

            if (!city.isBlank()) {
                predicates.add(builder.like(root.get("city"), likeFilter.formatted(city)));
            }

            if (!address.isBlank()) {
                predicates.add(builder.like(root.get("address"), likeFilter.formatted(address)));
            }

            if (pointNp != null) {
                predicates.add(builder.equal(root.get("pointNp"), pointNp));
            }

            if (pointUkrPost != null) {
                predicates.add(builder.equal(root.get("pointUkrPost"), pointUkrPost));
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

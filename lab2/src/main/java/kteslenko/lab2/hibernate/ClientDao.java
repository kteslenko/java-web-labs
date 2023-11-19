package kteslenko.lab2.hibernate;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import kteslenko.lab2.entity.Client;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.CriteriaDefinition;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ClientDao {
    private final SessionFactory sessionFactory;

    public Optional<Client> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Client.class, id));
        }
    }

    public List<Client> findByCriteria(ClientFilter filter, ClientSort sort) {
        CriteriaQuery<Client> criteria =
                new CriteriaDefinition<>(sessionFactory, Client.class) {{
                    String likeFilter = "%%%s%%";
                    Root<Client> client = from(Client.class);

                    if (!filter.getFirstName().isBlank()) {
                        restrict(like(client.get("firstName"), likeFilter.formatted(filter.getFirstName())));
                    }

                    if (!filter.getLastName().isBlank()) {
                        restrict(like(client.get("lastName"), likeFilter.formatted(filter.getLastName())));
                    }

                    if (!filter.getPhone().isBlank()) {
                        restrict(like(client.get("phone"), likeFilter.formatted(filter.getPhone())));
                    }

                    if (!filter.getEmail().isBlank()) {
                        restrict(like(client.get("email"), likeFilter.formatted(filter.getEmail())));
                    }

                    if (!filter.getGenders().isEmpty()) {
                        restrict(in(client.get("gender"), filter.getGenders()));
                    }

                    if (filter.getIsVIP() != null) {
                        restrict(equal(client.get("isVIP"), filter.getIsVIP()));
                    }

                    String sortColumn = sort.getColumn().getFieldName();

                    if (sort.getDirection() == ClientSort.Direction.ASC) {
                        orderBy(asc(client.get(sortColumn)));
                    } else {
                        orderBy(desc(client.get(sortColumn)));
                    }
                }};

        try (Session session = sessionFactory.openSession()) {
            return session.createSelectionQuery(criteria)
                    .getResultList();
        }
    }

    public void insert(Client client) {
        sessionFactory.inTransaction(session -> session.persist(client));
    }

    public void update(Client client) {
        sessionFactory.inTransaction(session -> session.merge(client));
    }

    public void delete(Client client) {
        sessionFactory.inTransaction(session -> session.remove(client));
    }
}

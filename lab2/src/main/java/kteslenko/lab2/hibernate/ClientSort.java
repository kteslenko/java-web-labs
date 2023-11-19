package kteslenko.lab2.hibernate;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientSort {
    @AllArgsConstructor
    @Getter
    public enum Column {
        FIRST_NAME("firstName", "First Name"),
        LAST_NAME("lastName", "Last Name"),
        PHONE("phone", "Phone"),
        EMAIL("email", "Email");

        private final String fieldName;
        private final String displayName;
    }

    @AllArgsConstructor
    @Getter
    public enum Direction {
        ASC("Ascending"), DESC("Descending");

        private final String displayName;
    }

    private Column column;
    private Direction direction;
}

package kteslenko.lab3.specifications;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SortDirection {
    ASC("Ascending"), DESC("Descending");

    private final String displayName;
}

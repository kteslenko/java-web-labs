package kteslenko.lab3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    UNSPECIFIED("Unspecified");

    private final String displayName;
}

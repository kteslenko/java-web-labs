package kteslenko.lab2.hibernate;

import kteslenko.lab2.entity.Gender;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientFilter {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private List<Gender> genders;
    private Boolean isVIP;
}

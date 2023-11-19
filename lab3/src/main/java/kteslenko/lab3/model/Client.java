package kteslenko.lab3.model;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Builder
@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 128)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 128)
    private String lastName;

    @Column(name = "phone", nullable = false, length = 16)
    private String phone;

    @Column(name = "email", nullable = false, length = 128)
    private String email;

    @Type(PostgreSQLEnumType.class)
    @Column(name = "gender", nullable = false, columnDefinition = "gender")
    private Gender gender;

    @Column(name = "is_vip", nullable = false)
    private Boolean isVIP;
}

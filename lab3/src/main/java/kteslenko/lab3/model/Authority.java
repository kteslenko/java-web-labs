package kteslenko.lab3.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Builder
@Entity
@Table(name = "authorities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "authority", nullable = false, length = 128)
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    public String toString() {
        return authority;
    }
}

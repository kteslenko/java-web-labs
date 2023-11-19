package kteslenko.lab3.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_address", length = 128)
    private String codAddress;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, updatable = false)
    private Client client;

    @Column(name = "city", nullable = false, length = 64)
    private String city;

    @Column(name = "address", length = 128)
    private String address;

    @Column(name = "point_np")
    private Integer pointNp;

    @Column(name = "point_ukr_post")
    private Integer pointUkrPost;
}

package dev.oltijanuzi.busride.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    @NotBlank
    private String username;

    @Email
    @Column(nullable = false,length = 50)
    private String email;

    @Column(nullable = false,length = 50)
    @NotBlank
    @Size(min = 8)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


}

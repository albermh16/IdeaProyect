package es.daw.hotelesmvc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(r -> (GrantedAuthority) r::getName)
                .collect(Collectors.toSet());
    }

    // -------- Métodos de UserDetails --------

    @Override
    public boolean isAccountNonExpired() {
        return true; // no gestionamos expiración de cuenta
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // no gestionamos bloqueo de cuenta
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // no gestionamos expiración de credenciales
    }

    @Override
    public boolean isEnabled() {
        // Como no tenemos campo enabled en la entidad, devolvemos true siempre
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}

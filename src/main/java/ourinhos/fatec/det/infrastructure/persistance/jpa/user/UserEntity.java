package ourinhos.fatec.det.infrastructure.persistance.jpa.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "user_sys")
@Getter
@NoArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "user_id", nullable = false)
    private String id;

    private String cpf;

    @Column(name = "user_name", nullable = false)
    private String name;

    private String address;

    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String user_password;

    private final String authorities = "USER";

    public UserEntity(String userId, String cpf, String name, String address, String phone, String email, String password) {
        this.id = userId;
        this.cpf = cpf;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.user_password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.user_password;
    }

    @Override
    public String getUsername() {
        return this.cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

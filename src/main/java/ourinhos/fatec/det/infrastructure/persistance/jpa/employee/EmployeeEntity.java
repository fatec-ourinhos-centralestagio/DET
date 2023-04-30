package ourinhos.fatec.det.infrastructure.persistance.jpa.employee;

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

@Getter
@NoArgsConstructor
@Entity
@Table(name = "employee_sys")
public class EmployeeEntity implements UserDetails {

    @Id
    @Column(name = "employee_id", nullable = false)
    private String id;

    private String cpf;

    @Column(name = "employee_name", nullable = false)
    private String name;

    private String dateofbirth;

    private String address;

    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String user_password;

    @Column(name = "wage", nullable = false)
    private Float wage;

    @Column(name = "office", nullable = false)
    private String office;

    private final String authorities = "ADMIN";

    public EmployeeEntity(String userId, String cpf, String name, String dateOfBirth, String address, String phone, String email, String password, Float wage, String office) {
        this.id = userId;
        this.cpf = cpf;
        this.name = name;
        this.dateofbirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.user_password = password;
        this.wage = wage;
        this.office = office;
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

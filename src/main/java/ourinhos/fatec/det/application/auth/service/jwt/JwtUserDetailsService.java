package ourinhos.fatec.det.application.auth.service.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ourinhos.fatec.det.infrastructure.persistance.jpa.employee.EmployeeEntity;
import ourinhos.fatec.det.infrastructure.persistance.jpa.employee.EmployeeRepositoryImp;
import ourinhos.fatec.det.infrastructure.persistance.jpa.user.UserEntity;
import ourinhos.fatec.det.infrastructure.persistance.jpa.user.UserRepositoryImp;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositoryImp userRepository;

    @Autowired
    private EmployeeRepositoryImp employeeRepositoryImp;

    private User getUser(String username){
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Optional<EmployeeEntity> employee = employeeRepositoryImp.findByEmail(username);
            if(employee.isEmpty()){
                UserEntity user = userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with cpf: " + username));
                return new User(user.getEmail(), encoder.encode(user.getPassword()), new ArrayList<>());
            }
            return new User(employee.get().getEmail(), encoder.encode(employee.get().getPassword()), new ArrayList<>());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUser(username);
    }


}
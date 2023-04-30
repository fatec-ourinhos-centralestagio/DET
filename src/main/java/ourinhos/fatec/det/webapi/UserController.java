package ourinhos.fatec.det.webapi;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ourinhos.fatec.det.domain.user.UserService;
import ourinhos.fatec.det.domain.user.vo.UserVO;
import ourinhos.fatec.det.infrastructure.persistance.jpa.user.UserEntity;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "User", description = "Serviços que se referem ao usuário")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create/user")
    @Transactional
    public ResponseEntity<HttpStatus> createUser(
            @RequestBody UserVO user
    ) {
        return userService.createUser(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<UserEntity>> getUser(@PathVariable("id") String userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("/user/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String userId) {
        return userService.deleteUser(userId);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<HttpStatus> updateUser(
            @PathVariable("id") String userId,
            @RequestBody UserVO userChanged) {
        return userService.updateUser(userChanged, userId);
    }


}

package ru.sshibko.bsss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sshibko.bsss.entity.Role;
import ru.sshibko.bsss.entity.RoleType;
import ru.sshibko.bsss.entity.User;
import ru.sshibko.bsss.model.UserDto;
import ru.sshibko.bsss.service.UserService;

@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class PublicController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> getPublic() {
        return ResponseEntity.ok("Called public method");
    }

    @PostMapping("/account")
    public ResponseEntity<UserDto> createUserAccount(@RequestBody UserDto userDto,
                                 @RequestParam RoleType roleType) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createAccount(userDto, roleType));
    }

    private UserDto createAccount (UserDto userDto, RoleType roleType) {

        var user = new User();

        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());

        var createdUser = userService.createNewAccount(user, Role.from(roleType));

        return UserDto.builder()
                .username(createdUser.getUsername())
                .password(createdUser.getPassword())
                .build();
    }

}

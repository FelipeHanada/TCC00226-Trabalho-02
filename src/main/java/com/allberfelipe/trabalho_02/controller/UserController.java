package com.allberfelipe.trabalho_02.controller;

import com.allberfelipe.trabalho_02.dto.UserCreateRequest;
import com.allberfelipe.trabalho_02.model.PageResult;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public PageResult<User> getUsersWithPagination(
            @RequestParam(value = "page", defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<User> page = userService.getUsersWithPagination(pageable);
        return new PageResult<>(page);
    }

    @PostMapping
    public User createUser(@RequestBody UserCreateRequest user) {
        return userService.createUser(
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getAboutMe(),
                user.getPhoneNumber()
            );
    }

    @PatchMapping
    public User updateUser(@RequestBody User user) { return userService.updateUser(user); }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") long id) { userService.deleteUser(id); }
}

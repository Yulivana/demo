package com.example.demo.fasads;

import com.example.demo.entity.User;
import com.example.demo.forms.UserForm;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserFasad {

    @Autowired
    UserRepository userRepository;

    public List<UserForm> getAll() {
        List<User> userList = userRepository.findAll();
        List<UserForm> users = new ArrayList<>();
        userList.forEach(user -> {
            UserForm userForm = new UserForm(user.getUserName());
            users.add(userForm);
        });
        return users;
    }

    public UserForm getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> new UserForm(value.getUserName())).orElse(null);
    }

    public UserForm getByName(String username) {
        Optional<User> user = userRepository.findUserByUserName(username);
        return user.map(value -> new UserForm(value.getId(), value.getUserName(), value.getPassword(), value.getRole())).orElse(null);
    }
}

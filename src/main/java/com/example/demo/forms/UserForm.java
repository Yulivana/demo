package com.example.demo.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"id", "photo", "newPassword", "confirmPassword", "fileData"})
public class UserForm implements Serializable {

    private long id;
    private String username;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private String email;
    private String role = "user";
    private MultipartFile fileData;
    private byte[] photo;
    private String error;

    public UserForm(String username) {
        this.username = username;
    }

    public UserForm(long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

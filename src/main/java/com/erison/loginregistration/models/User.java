package com.erison.loginregistration.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "UserName can not be empty")
    @Size(min = 3,max = 30 ,message = "UserName must be between 3 and 30 caracters")
    private String userName;

    @NotEmpty(message = "Email can not be empty")
    @Size(min = 10,max = 30 ,message = "Email must be between 10 and 30 caracters")
    @Email(message = "Enter a valid email")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "Birthday is required")
    @Past(message = "Invalid date. Birthday must be in the past")
    private LocalDate birthday;

    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain at least one capital letter and one number")
    private String password;

    @Transient
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirm;

    public User(){}

    public User(Long id, String userName, String email, LocalDate birthday,String password, String confirm) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.birthday=birthday;
        this.password = password;
        this.confirm = confirm;
    }
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }


    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

}

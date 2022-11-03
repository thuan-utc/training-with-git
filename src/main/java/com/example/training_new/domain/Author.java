package com.example.training_new.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {
    @Id
    private String username;
    @NotBlank(message = "password is mandatory")
    private String password;
    @NotBlank(message = "firstName is mandatory")
    private String firstName;
    @NotBlank(message = "lastName is mandatory")
    private String lastName;
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotNull(message = "Date of birth(dob) is mandatory")
    private LocalDate dob;
    @NotNull(message = "Time added is mandatory")
    private Instant added;


    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private final Set<Authority> authorities = new HashSet<>();


    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private final Set<Post> posts = new HashSet<>();

    public Set<Post> getPosts() {
        return posts;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }
    public Author(String username, String password, String firstName,
                  String lastName, String email, LocalDate dob, Instant added) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.added = added;
    }

    public Author() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }


    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Instant getAdded() {
        return added;
    }

    public void setAdded(Instant added) {
        this.added = added;
    }

    @Override
    public String toString() {
        return "Author{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", added=" + added +
                '}';
    }
}

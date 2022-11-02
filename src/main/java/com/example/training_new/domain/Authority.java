package com.example.training_new.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Authority")
public class Authority {
    @Id
    @NotBlank(message = "username is mandatory")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String userName;
    @NotBlank(message = "authority is mandatory")
    private String authority;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_username", referencedColumnName = "username")
    private Author author;

    public Authority() {
    }

    public Authority(String userName, String authority) {
        this.userName = userName;
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "userName='" + userName + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

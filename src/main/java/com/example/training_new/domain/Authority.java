package com.example.training_new.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Authority {
    @Id
    @NotBlank(message = "userName is mandatory")
    @JsonProperty("authority_userName")
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

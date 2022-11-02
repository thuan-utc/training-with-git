package com.example.training_new.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "author is mandatory")
    private String author;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "description is mandatory")
    private String description;
    @NotBlank(message = "description is mandatory")
    private String content;
    @NotBlank(message = "pdate is mandatory")
    private LocalDate pdate;

    public void setPostAuthor(Author postAuthor) {
        this.postAuthor = postAuthor;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_username", referencedColumnName = "username")
    private Author postAuthor;

    public Post() {
    }

    public Post(int id, String author, String title, String description, String content, LocalDate pdate, Author postAuthor) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.content = content;
        this.pdate = pdate;
        this.postAuthor = postAuthor;
    }

    public Post(String author, String title, String description, String content, LocalDate pdate) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.content = content;
        this.pdate = pdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPdate() {
        return pdate;
    }

    public void setPdate(LocalDate pdate) {
        this.pdate = pdate;
    }

    public Author getPostAuthor() {
        return postAuthor;
    }
}

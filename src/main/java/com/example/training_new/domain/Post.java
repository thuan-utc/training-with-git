package com.example.training_new.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "author is mandatory")
    private String author;
    @NotBlank(message = "title is mandatory")
    private String title;
    @NotBlank(message = "description is mandatory")
    private String description;
    @NotBlank(message = "description is mandatory")
    private String content;


    @NotNull(message = "post date is mandatory")
    private LocalDate postDate;
    @ManyToOne(optional = false)
    @JoinColumn(name = "author_username", referencedColumnName = "username")
    private Author postAuthor;

    public void setPostAuthor(Author postAuthor) {
        this.postAuthor = postAuthor;
    }

    public Post() {
    }

    public Post(String author, String title, String description, String content, LocalDate postDate) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.content = content;
        this.postDate = postDate;
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

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public Author getPostAuthor() {
        return postAuthor;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", postDate=" + postDate +
                ", postAuthor=" + postAuthor +
                '}';
    }
}

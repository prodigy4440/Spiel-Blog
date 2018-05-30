package com.pc.spiel.web.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @NotNull
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotEmpty
    @Column(name = "message")
    private String message;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "article_id",referencedColumnName = "id")
    private Article article;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    public Comment() {
    }

    public Comment(String email, String phoneNumber, String message, Article article, LocalDateTime date) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.article = article;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(getId(), comment.getId()) &&
                Objects.equals(getEmail(), comment.getEmail()) &&
                Objects.equals(getPhoneNumber(), comment.getPhoneNumber()) &&
                Objects.equals(getMessage(), comment.getMessage()) &&
                Objects.equals(getDate(), comment.getDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getEmail(), getPhoneNumber(), getMessage(), getDate());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}

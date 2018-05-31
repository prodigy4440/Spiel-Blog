package com.pc.spiel.web.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "Article.findAll", query = "select a from Article a WHERE a.id < :lastid ORDER BY a.id desc")
})
@Entity
@Table(name= "article")
public class Article implements Serializable {

    @NotNull
    @Id
    @Column(name = "id", nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 0, max = 120)
    @Column(name = "title")
    private String title;

    @NotEmpty
    @Size(min = 0, max=32768)
    private String content;

    @Column(name = "link")
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "links", joinColumns = @JoinColumn(name = "id"))
    private Set<String> links;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    public Article() {
    }

    public Article(String title, String content, Set<String> links, LocalDateTime date) {
        this.title = title;
        this.content = content;
        this.links = links;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<String> getLinks() {
        return links;
    }

    public void setLinks(Set<String> links) {
        this.links = links;
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
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return Objects.equals(getId(), article.getId()) &&
                Objects.equals(getTitle(), article.getTitle()) &&
                Objects.equals(getContent(), article.getContent()) &&
                Objects.equals(getLinks(), article.getLinks()) &&
                Objects.equals(getDate(), article.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getContent(), getLinks(), getDate());
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", links=" + links +
                ", date=" + date +
                '}';
    }
}

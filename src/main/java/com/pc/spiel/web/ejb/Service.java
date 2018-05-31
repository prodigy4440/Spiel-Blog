package com.pc.spiel.web.ejb;

import com.pc.spiel.web.entities.Article;
import com.pc.spiel.web.entities.Comment;
import com.pc.spiel.web.model.BaseResponse;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Stateless
public class Service {

    @PersistenceContext
    private EntityManager entityManager;

    public BaseResponse createArticle(Article article){
        if(Objects.isNull(article)){
            return new BaseResponse.Builder<Void>()
                    .setCode(3)
                    .addDescription("Invalid parameter")
                    .build();
        }else{
            article.setDate(LocalDateTime.now());
            entityManager.persist(article);
            return new BaseResponse.Builder<Article>()
                    .setCode(0)
                    .addDescription("Article created")
                    .addData(article)
                    .build();
        }
    }

    public BaseResponse updateArticle(Article article){
        if(Objects.isNull(article)){
            return new BaseResponse.Builder<Void>()
                    .setCode(3)
                    .addDescription("Invalid parameter")
                    .build();
        }else{
            entityManager.merge(article);
            return new BaseResponse.Builder<Article>()
                    .setCode(0)
                    .addDescription("Article updated")
                    .addData(article)
                    .build();
        }
    }

    public BaseResponse removeArticle(Long id){
        if(Objects.isNull(id) || (id < 1)){
            return new BaseResponse.Builder<Void>()
                    .setCode(3)
                    .addDescription("Invalid parameter")
                    .build();
        }else{
            Article article = entityManager.find(Article.class, id);
            if(Objects.isNull(article)){
                return new BaseResponse.Builder<Void>()
                        .setCode(2)
                        .addDescription("Article not found")
                        .build();
            }else{
                entityManager.remove(article);
                return new BaseResponse.Builder<Article>()
                        .setCode(0)
                        .addDescription("Article Deleted")
                        .addData(article)
                        .build();
            }
        }
    }

    public BaseResponse findArticle(Long lastId, Integer size){
        if(Objects.isNull(lastId) || Objects.isNull(size) || (lastId < 1) || (size < 2)){
            return new BaseResponse.Builder<Void>()
                    .setCode(3)
                    .addDescription("Invalid parameter")
                    .build();
        }else{
            List<Article> articles = entityManager.createNamedQuery("Article.findAll", Article.class)
                    .setParameter("lastid", lastId)
                    .setMaxResults(size)
                    .getResultList();
            return new BaseResponse.Builder<Article>()
                    .setCode(0)
                    .addDescription("Article fetched")
                    .addData(articles)
                    .build();
        }
    }

    public BaseResponse createComment(Long id, Comment comment){
        if(Objects.isNull(id)|| (id<1)){
            return new BaseResponse.Builder<Void>()
                    .setCode(3)
                    .addDescription("Invalid id")
                    .build();
        }

        if(Objects.isNull(comment)){
            return new BaseResponse.Builder<Void>()
                    .setCode(3)
                    .addDescription("Invalid parameter")
                    .build();
        }

        Article article = entityManager.find(Article.class, id);
        if(Objects.isNull(article)){
            return new BaseResponse.Builder<Void>()
                    .setCode(2)
                    .addDescription("Article not found")
                    .build();
        }else{
            comment.setDate(LocalDateTime.now());
            comment.setArticle(article);
            entityManager.persist(comment);
            return new BaseResponse.Builder<Article>()
                    .setCode(0)
                    .addDescription("Comment created")
                    .addData(comment)
                    .build();
        }

    }

    public BaseResponse findComments(Long articleId, Long lastCommentId, Integer size){
        if(Objects.isNull(articleId) || Objects.isNull(lastCommentId) || Objects.isNull(size)
                || (articleId < 1) || (lastCommentId < 1) || (size < 1)){
            return new BaseResponse.Builder<Void>()
                    .setCode(3)
                    .addDescription("Invalid parameter")
                    .build();
        }else{
            List<Comment> comments = entityManager.createNamedQuery("Comment.findAll", Comment.class)
                    .setParameter("id", articleId)
                    .setParameter("lastid", lastCommentId)
                    .setMaxResults(size)
                    .getResultList();
            return new BaseResponse.Builder<Article>()
                    .setCode(0)
                    .addDescription("Comments fetched")
                    .addData(comments)
                    .build();
        }
    }

}

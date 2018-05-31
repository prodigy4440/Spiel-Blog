package com.pc.spiel.web;

import com.pc.spiel.web.ejb.Service;
import com.pc.spiel.web.entities.Article;
import com.pc.spiel.web.entities.Comment;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("blog")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class Blog {

    @EJB
    private Service service;

    @Path("article")
    @POST
    public Response createArticle(Article article) {
        return Response.ok(service.createArticle(article)).build();
    }

    @Path("article")
    @PUT
    public Response updateArticle(Article article){
        return Response.ok(service.updateArticle(article)).build();
    }

    @Path("article")
    @DELETE
    public Response deleteArticle(@QueryParam("id")Long id){
        return Response.ok(service.removeArticle(id)).build();
    }

    @Path("article")
    @GET
    public Response findArticles(@QueryParam("id")Long lastId, @QueryParam("size")Integer size){
        return Response.ok(service.findArticle(lastId,size)).build();
    }

    @Path("comment")
    @POST
    public Response createComment(@QueryParam("id")Long id, Comment comment){
        return Response.ok(service.createComment(id, comment)).build();
    }

    @Path("comment")
    @GET
    public Response findComments(@QueryParam("article")Long articleId, @QueryParam("comment")Long commentId,
                                 @QueryParam("size")Integer size){
        return Response.ok(service.findComments(articleId,commentId,size)).build();
    }
}

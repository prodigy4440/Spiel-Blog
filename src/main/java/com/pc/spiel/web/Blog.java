package com.pc.spiel.web;

import com.pc.spiel.web.entities.Article;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("blog")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class Blog {

    @Path("article")
    @POST
    public Response createArticle(Article article){

    }
}

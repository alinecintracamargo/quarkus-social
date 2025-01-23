package io.github.alinecintracamargo.quarkussocial.rest;


import io.github.alinecintracamargo.quarkussocial.domain.model.Post;
import io.github.alinecintracamargo.quarkussocial.domain.model.User;
import io.github.alinecintracamargo.quarkussocial.domain.repository.PostRepository;
import io.github.alinecintracamargo.quarkussocial.domain.repository.UserRepository;
import io.github.alinecintracamargo.quarkussocial.rest.dto.CreatePostRequest;
import io.github.alinecintracamargo.quarkussocial.rest.dto.PostResponse;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    private final UserRepository userRepository;
    private final PostRepository repository;

    @Inject
    public PostResource(UserRepository userRepository,
                        PostRepository repository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }

    @POST
    @Transactional
    public Response savePost(
            @PathParam("userId") Long userId, CreatePostRequest request){
        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setText(request.getText());
        post.setUser(user);


        repository.persist(post);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listPost(@PathParam("userId") Long userId){
        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        PanacheQuery<Post> query = repository.find(
                "user", Sort.by("dateTime", Sort.Direction.Descending), user);

        var list = query.list();

        var postResponse = list.stream()
                .map(post -> PostResponse.fromEntity(post))
                .collect(Collectors.toList());

        return Response.ok(postResponse).build();
    }
}

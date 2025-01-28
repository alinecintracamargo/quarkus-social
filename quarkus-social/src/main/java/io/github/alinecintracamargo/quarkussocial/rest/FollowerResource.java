package io.github.alinecintracamargo.quarkussocial.rest;


import io.github.alinecintracamargo.quarkussocial.domain.model.Follower;
import io.github.alinecintracamargo.quarkussocial.domain.repository.FollowerRepository;
import io.github.alinecintracamargo.quarkussocial.domain.repository.UserRepository;
import io.github.alinecintracamargo.quarkussocial.rest.dto.FollowerRequest;
import io.github.alinecintracamargo.quarkussocial.rest.dto.FollowerResponse;
import io.github.alinecintracamargo.quarkussocial.rest.dto.FollowersPerUserResponse;
import lombok.Data;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Data
@Path("users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource {

    private final FollowerRepository repository;
    private final UserRepository userRepository;

    @Inject
    public FollowerResource(
            FollowerRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @PUT
    @Transactional
    public Response followUser(
            @PathParam("userId") Long userId, FollowerRequest request) {

        if(userId.equals(request.getFollowerId())){
            return Response.status(Response.Status.CONFLICT)
                    .entity("You can´t follow yourself")
                    .build();
        }

      var user =  userRepository.findById(userId);
      if(user == null){
          return Response.status(Response.Status.NOT_FOUND).build();
      }

      var follower =  userRepository.findById(request.getFollowerId());

      boolean follows = repository.follows(follower, user);

      if(!follows){

      var entity = new Follower();
      entity.setUser(user);
      entity.setFollowers(follower);

      repository.persist(entity);

      }


      return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    public Response listFollowers(@PathParam("userId") Long userId,
                                  @HeaderParam("followerId") Long followerId ){

        var user =  userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }



        var list = repository.findByUser(userId);
        FollowersPerUserResponse responseObjetc = new FollowersPerUserResponse();
        responseObjetc.setFollowersCount(list.size());

        var followerList = list.stream()
                .map(FollowerResponse::new)
                .collect(Collectors.toList());

        responseObjetc.setContent(followerList);
        return Response.ok(responseObjetc).build();
    }

    @DELETE
    @Transactional
    public Response unfollowUser(@PathParam("userId") Long userId,
                                 @QueryParam("followerId") Long followerId ){

        var user =  userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        repository.deleteByFollowerAndUser(followerId, userId);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

}

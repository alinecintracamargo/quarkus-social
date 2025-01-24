package io.github.alinecintracamargo.quarkussocial.rest.dto;


import io.github.alinecintracamargo.quarkussocial.domain.model.Follower;
import lombok.Data;

@Data
public class FollowerResponse {
    private Long id;
    private String name;

    public FollowerResponse(Follower follower) {
        this(follower.getId(), follower.getFollowers().getName());
    }

    public FollowerResponse(Long id, String name) {
        this.id = id;
        this.name = name;

    }
}

package io.github.alinecintracamargo.quarkussocial.rest.dto;


import io.github.alinecintracamargo.quarkussocial.domain.model.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private String text;
    private LocalDateTime date_time;

    public static PostResponse fromEntity(Post post){
        var response = new PostResponse();
        response.setText(post.getText());
        response.setDate_time(post.getDateTime());

        return response;
    }

}

package io.github.alinecintracamargo.quarkussocial.rest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.transform.Source;

@Data
public class CreateUserRequest {

    @NotBlank(message = "name is Required")
    private String name;
    @NotNull(message ="age is Required")
    private Integer age;

}

package com.sergio.out.controller.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("GirlFriend Update Args")
public class GirlFriendUpdateInDTO {

    @NotBlank
    private String id;

    private String name;

    private Integer age;

    private Long mobile;


}
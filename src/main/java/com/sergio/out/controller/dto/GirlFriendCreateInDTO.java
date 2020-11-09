package com.sergio.out.controller.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("GirlFriend Create Args")
public class GirlFriendCreateInDTO {

// TODO javax validation: @NotBlank | @NotNull

    private String name;

    private Integer age;

    private Long mobile;


}
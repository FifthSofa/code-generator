package com.sergio.out.controller.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("GirlFriend Query Args")
public class GirlFriendQueryInDTO {

	private String[] ids;

    private String name;

    private Integer age;

    private Long mobile;

}
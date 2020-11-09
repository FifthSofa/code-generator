package com.sergio.generator;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class EntityField implements Serializable {

    @NotBlank
    private String fieldType;

    @NotBlank
    private String fieldName;

    @NotBlank
    private String dbColumn;

    @NotBlank
    private String dbType;

    private String notNull = "off";

    private String comment;
}

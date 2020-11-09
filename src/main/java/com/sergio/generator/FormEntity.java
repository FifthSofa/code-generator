package com.sergio.generator;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
public class FormEntity implements Serializable {

    private Integer initFlag;

    @NotBlank
    private String tableName;

    @NotBlank
    private String entityName;

    @NotNull
    @Size(min = 1)
    List<EntityField> entityFields;
}

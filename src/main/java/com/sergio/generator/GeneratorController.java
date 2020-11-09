package com.sergio.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class GeneratorController {

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value = "/generate-code")
    public String generator(@Valid @ModelAttribute FormEntity formEntity) throws JsonProcessingException {

        Iterator<EntityField> iterator = formEntity.getEntityFields().iterator();
        while (iterator.hasNext()) {
            EntityField entityField = iterator.next();
            if (entityField.getFieldName() == null) {
                iterator.remove();
            }
        }

        Map<String, Object> map = objectMapper.readValue(objectMapper.writeValueAsString(formEntity), Map.class);

        Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));

        String resultBaseDir = "src/main/java/com/sergio/out/";
        String templateBaseDir = "src/main/resources/templates/generator/";

        // entity
        String entityResultPath = resultBaseDir + "mybatis/entity/" + (String) formEntity.getEntityName() + ".java";
        String entityTemplatePath = templateBaseDir + "Entity.ftl";
        generatorCode(entityResultPath, entityTemplatePath, config, map);

        // mapper
        String mapperResultPath = resultBaseDir + "mybatis/mapper/" + (String) formEntity.getEntityName() + "Mapper.java";
        String mapperTemplatePath = templateBaseDir + "Mapper.ftl";
        generatorCode(mapperResultPath, mapperTemplatePath, config, map);

        // createInDTO
        String createInDTOResultPath = resultBaseDir + "controller/dto/" + (String) formEntity.getEntityName() + "CreateInDTO.java";
        String createInDTOTemplatePath = templateBaseDir + "InDTO_Create.ftl";
        generatorCode(createInDTOResultPath, createInDTOTemplatePath, config, map);

        // updateInDTO
        String updateInDTOResultPath = resultBaseDir + "controller/dto/" + (String) formEntity.getEntityName() + "UpdateInDTO.java";
        String updateInDTOTemplatePath = templateBaseDir + "InDTO_Update.ftl";
        generatorCode(updateInDTOResultPath, updateInDTOTemplatePath, config, map);

        // queryInDTO
        String queryInDTOResultPath = resultBaseDir + "controller/dto/" + (String) formEntity.getEntityName() + "QueryInDTO.java";
        String queryInDTOTemplatePath = templateBaseDir + "InDTO_Query.ftl";
        generatorCode(queryInDTOResultPath, queryInDTOTemplatePath, config, map);

        // controller
        String controllerResultPath = resultBaseDir + "controller/" + (String) formEntity.getEntityName() + "Controller.java";
        String controllerTemplatePath = templateBaseDir + "Controller.ftl";
        generatorCode(controllerResultPath, controllerTemplatePath, config, map);

        // createInBO
        String createInBOResultPath = resultBaseDir + "service/bo/" + (String) formEntity.getEntityName() + "CreateInBO.java";
        String createInBOTemplatePath = templateBaseDir + "InBO_Create.ftl";
        generatorCode(createInBOResultPath, createInBOTemplatePath, config, map);

        // updateInBO
        String updateInBOResultPath = resultBaseDir + "service/bo/" + (String) formEntity.getEntityName() + "UpdateInBO.java";
        String updateInBOTemplatePath = templateBaseDir + "InBO_Update.ftl";
        generatorCode(updateInBOResultPath, updateInBOTemplatePath, config, map);

        // queryInBO
        String queryInBOResultPath = resultBaseDir + "service/bo/" + (String) formEntity.getEntityName() + "QueryInBO.java";
        String queryInBOTemplatePath = templateBaseDir + "InBO_Query.ftl";
        generatorCode(queryInBOResultPath, queryInBOTemplatePath, config, map);

        // service
        String serviceResultPath = resultBaseDir + "service/" + (String) formEntity.getEntityName() + "Service.java";
        String serviceTemplatePath = templateBaseDir + "Service.ftl";
        generatorCode(serviceResultPath, serviceTemplatePath, config, map);

        // serviceImpl
        String serviceImplResultPath = resultBaseDir + "service/impl/" + (String) formEntity.getEntityName() + "ServiceImpl.java";
        String serviceImplTemplatePath = templateBaseDir + "ServiceImpl.ftl";
        generatorCode(serviceImplResultPath, serviceImplTemplatePath, config, map);

        // sql
        String sqlResultPath = resultBaseDir + "sql/sql.sql";
        String sqlTemplatePath = templateBaseDir + "Sql.ftl";
        generatorCode(sqlResultPath, sqlTemplatePath, config, map);

        formEntity.setInitFlag(0);
        return "generator";
    }

    // ----------------------------------------------

    @GetMapping("/generator")
    public String test(ModelMap map) {
        FormEntity formEntity = new FormEntity();
        formEntity.setTableName("demo");
        formEntity.setEntityName("Demo");

        List<EntityField> entityFields = new ArrayList<>();

        EntityField entityField = new EntityField();
        entityField.setFieldName("id");
        entityField.setFieldType("String");
        entityField.setDbColumn("id");
        entityField.setDbType("varchar(36)");
        entityField.setNotNull("on");
        entityField.setComment("id");
        entityFields.add(entityField);

        EntityField entityField1 = new EntityField();
        entityField1.setFieldName("accountId");
        entityField1.setFieldType("String");
        entityField1.setDbColumn("account_id");
        entityField1.setDbType("varchar(36)");
        entityField1.setNotNull("on");
        entityField1.setComment("account id");
        entityFields.add(entityField1);

        formEntity.setEntityFields(entityFields);
        formEntity.setInitFlag(1);

        map.put("formEntity", formEntity);

        return "generator";
    }

    @GetMapping(value = "/generate-code")
    public String generator() {
        return "redirect:generator";
    }

    private void generatorCode(String resultPath, String templatePath, Configuration config, Map<String, Object> map) {
        try {
            prepareFile(resultPath);
            Template template = config.getTemplate(templatePath, "UTF-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultPath), "UTF-8"));
            template.process(map, out);
            out.flush();
            out.close();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();

            File file = new File(resultPath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private String prepareFile(String resultPath) throws IOException {

        // if file exists, delete it
        File file = new File(resultPath);
        if (file.exists()) {
            file.delete();
        }

        // if parent directory do not exists, create the parent directory
        if (!file.getParentFile().exists()) {
            boolean mkdir = file.getParentFile().mkdirs();
            if (!mkdir) {
                throw new RuntimeException("mkdir parent directory failed");
            }
        }

        // create file
        file.createNewFile();

        return resultPath;
    }

}


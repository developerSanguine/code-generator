package com.sanguine.codegenerator.utility;

import com.sanguine.codegenerator.domain.model.Fields;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GenerateResponse {

    public static void generateResponseObject(String directoryPath, String directoryName
            , String packageName, Map<String, List<Fields>> tablesMap) throws IOException {

        String repoPath = directoryPath+"/"+directoryName+"/domain/response/";
        Path dirPath = Paths.get(repoPath);
        Files.createDirectories(dirPath);

        Arrays.stream(Objects.requireNonNull(new File(repoPath).listFiles())).forEach(File::delete);

        List<String> commonFields = List.of("userCreated", "userEdited", "dateCreated",
            "dateEdited");

        for(Map.Entry<String, List<Fields>> entry : tablesMap.entrySet()){

            List<Fields> fieldsList = entry.getValue();

            Map<String, String> tblMap = CodeGeneratorUtility.fillTableMap();
            String tableName = entry.getKey();

            String entityInstanceName = tblMap.get(tableName);
            String firstLetter = String.valueOf(entityInstanceName.charAt(0));
            String entityName = tblMap.get(tableName).replaceFirst(firstLetter, firstLetter.toUpperCase());

            entityName = entityName.replaceAll("Pos", "POS");
            entityName = entityName.replaceAll("pos", "POS");

            String responseName = entityName+"Response";

            String path = repoPath+responseName+".java";
            File file = new File(path);
            FileWriter fw = new FileWriter(file);

            StringBuilder sbFileContents = new StringBuilder();

            sbFileContents.append("package ").append(packageName).append(".domain.response;");

            sbFileContents.append("\n\nimport com.fasterxml.jackson.annotation.JsonProperty;");
            sbFileContents.append("\nimport jakarta.validation.constraints.NotBlank;");
            sbFileContents.append("\nimport lombok.Builder;");

            sbFileContents.append("\n\n@Builder");
            sbFileContents.append("\npublic record ").append(responseName).append("(");

            for(Fields field : fieldsList){

                //System.out.println(field.getFieldName());
                String fieldName = field.getFieldName();
                String col = field.getFieldName();
                if(field.getFieldName().length() > 3 && (field.getFieldName().startsWith("str")
                        || field.getFieldName().startsWith("dbl")
                        || field.getFieldName().startsWith("int")
                        || field.getFieldName().startsWith("dte"))) {
                    col = field.getFieldName().substring(3);
                }

                if(!col.startsWith("POS")) {
                    fieldName = CodeGeneratorUtility.getInstanceName(col);
                }

                String fieldType = CodeGeneratorUtility.getFieldType(field.getFieldType());

                if(!commonFields.contains(fieldName)) {
                    sbFileContents.append("\n\t @JsonProperty(\"").append(fieldName).append("\")");
                    sbFileContents.append("\n\t ").append(fieldType).append(" ")
                            .append(fieldName).append(",");
                    sbFileContents.append("\n");
                }
            }
            sbFileContents.append("\n){}");

            fw.write(sbFileContents.toString());
            fw.flush();
        }
    }
}

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

public class GenerateEntity {

    public static void generateEntity(String directoryPath, String directoryName
            , String packageName, Map<String, List<Fields>> tablesMap) throws IOException {

        String repoPath = directoryPath+"/"+directoryName+"/entity/";
        Path dirPath = Paths.get(repoPath);
        Files.createDirectories(dirPath);
        Arrays.stream(Objects.requireNonNull(new File(repoPath).listFiles())).forEach(File::delete);

        for(Map.Entry<String, List<Fields>> entry : tablesMap.entrySet()){

            List<Fields> fieldsList = entry.getValue();

            Map<String, String> tblMap = CodeGeneratorUtility.fillTableMap();
            String tableName = entry.getKey();

            String entityInstanceName = tblMap.get(tableName);
            String firstLetter = String.valueOf(entityInstanceName.charAt(0));
            String entityName = tblMap.get(tableName).replaceFirst(firstLetter, firstLetter.toUpperCase());

            entityName = entityName.replaceAll("Pos", "POS");
            entityName = entityName.replaceAll("pos", "POS");

            String path = directoryPath+"/"+directoryName+"/entity/"+entityName+".java";
            File file = new File(path);
            FileWriter fw = new FileWriter(file);

            StringBuilder sbFileContents = new StringBuilder();

            sbFileContents.append("package ").append(packageName).append(".domain.entity;");

            sbFileContents.append("\n\nimport ").append("jakarta.persistence.*;");
            sbFileContents.append("\nimport lombok.AllArgsConstructor;");
            sbFileContents.append("\nimport lombok.Data;");
            sbFileContents.append("\nimport lombok.NoArgsConstructor;");
            sbFileContents.append("\nimport org.springframework.data.annotation.CreatedDate;");
            sbFileContents.append("\nimport org.springframework.data.annotation.LastModifiedDate;");
            sbFileContents.append("\nimport java.time.OffsetDateTime;");

            sbFileContents.append("\n\n@Data");
            sbFileContents.append("\n@AllArgsConstructor");
            sbFileContents.append("\n@NoArgsConstructor");
            sbFileContents.append("\n@Entity");
            sbFileContents.append("\n@Table(name = \"").append(tableName).append("\")");
            sbFileContents.append("\npublic class ").append(entityName).append("{");

            sbFileContents.append("\n\n\t@Id ");
            sbFileContents.append("\n\t@GeneratedValue(strategy = GenerationType.IDENTITY) ");
            sbFileContents.append("\n\tprivate Long id;");

            sbFileContents.append("\n\n\t@Version");
            sbFileContents.append("\n\tprivate int version;\n");

            System.out.println(tableName);

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

                if("dteDateCreated".equals(field.getFieldName())){
                    sbFileContents.append("\n\n\t@Column(name = \"").append(field.getFieldName()).append("\", ")
                            .append("nullable = false, updatable = false)");
                    sbFileContents.append("\n\t@CreatedDate");
                } else if(!"id".equals(field.getFieldName())) {
                    sbFileContents.append("\n\t@Column(name = \"").append(field.getFieldName()).append("\")");
                }

                if("dteDateEdited".equals(field.getFieldName())){
                    sbFileContents.append("\n\t@LastModifiedDate");
                }

                if(!"id".equals(field.getFieldName())) {
                    sbFileContents.append("\n\tprivate ").append(fieldType).append(" ")
                            .append(fieldName).append(";");
                    sbFileContents.append("\n");
                }
            }
            sbFileContents.append("\n}");

            fw.write(sbFileContents.toString());
            fw.flush();
        }
    }
}

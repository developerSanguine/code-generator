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

public class GenerateRepository {

    public static void generateRepository(String directoryPath, String directoryName
            , String packageName, Map<String, List<Fields>> tablesMap) throws IOException {

        String repoPath = directoryPath+"/"+directoryName+"/repository/";
        Path dirPath = Paths.get(repoPath);
        Files.createDirectories(dirPath);

        Arrays.stream(Objects.requireNonNull(new File(repoPath).listFiles())).forEach(File::delete);

        for(Map.Entry<String, List<Fields>> entry : tablesMap.entrySet()){

            Map<String, String> tblMap = CodeGeneratorUtility.fillTableMap();
            String tableName = entry.getKey();

            String entityInstanceName = tblMap.get(tableName);
            String firstLetter = String.valueOf(entityInstanceName.charAt(0));
            String entityName = tblMap.get(tableName).replaceFirst(firstLetter, firstLetter.toUpperCase());

            if(entityName.startsWith("pos") || entityName.startsWith("Pos")) {
                entityName = entityName.replaceFirst("Pos", "POS");
                entityName = entityName.replaceFirst("pos", "POS");
            }

            String repositoryName = entityName+"Repository";

            String path = directoryPath+"/"+directoryName+"/repository/"+repositoryName+".java";
            File file = new File(path);
            FileWriter fw = new FileWriter(file);

            StringBuilder sbFileContents = new StringBuilder();

            sbFileContents.append("package ").append(packageName).append(".repository;");

            sbFileContents.append("\n\nimport ").append(packageName).append(".domain.entity.")
                    .append(entityName).append(";");
            sbFileContents.append("\nimport org.springframework.data.jpa.repository.JpaRepository;");
            sbFileContents.append("\nimport org.springframework.data.jpa.repository.Query;");
            sbFileContents.append("\nimport org.springframework.stereotype.Repository;");
            sbFileContents.append("\nimport java.util.List;");

            sbFileContents.append("\n\n@Repository");
            sbFileContents.append("\npublic interface ").append(repositoryName).
                    append(" extends JpaRepository<").append(entityName).append(", Long> {");

            sbFileContents.append("\n\n\t@Query(\"from ").append(entityName).append("\")");
            sbFileContents.append("\n\tpublic List<").append(entityName).append("> find")
                    .append(entityName).append("List();");

            sbFileContents.append("\n}");

            fw.write(sbFileContents.toString());
            fw.flush();
        }
    }
}

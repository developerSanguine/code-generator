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

public class GenerateConstants {

    public static void generateConstants(String directoryPath, String directoryName
            , String packageName, Map<String, List<Fields>> tablesMap) throws IOException {

        String repoPath = directoryPath+"/"+directoryName+"/domain/constants/";
        Path dirPath = Paths.get(repoPath);
        Files.createDirectories(dirPath);

        Arrays.stream(Objects.requireNonNull(new File(repoPath).listFiles())).forEach(File::delete);

        String path = repoPath+"/MasterConstants"+".java";
        File file = new File(path);
        FileWriter fw = new FileWriter(file);

        StringBuilder sbFileContents = new StringBuilder();

        sbFileContents.append("package ").append(packageName).append(".domain.constants;");

        sbFileContents.append("\n\n");

        sbFileContents.append("\n").append("public class MasterConstants {");
        sbFileContents.append("\n\n");

        sbFileContents.append("// API Method name constants");
        sbFileContents.append("\n\tpublic static final String CREATE_MASTER = \"/create\";");
        sbFileContents.append("\n\tpublic static final String UPDATE_MASTER = \"/update/{id}\";");
        sbFileContents.append("\n\tpublic static final String GET_MASTER_BY_CODE = \"/get\";");
        sbFileContents.append("\n\tpublic static final String GET_ALL = \"/getAll\";");

        sbFileContents.append("\n\n// Master common constants");
        sbFileContents.append("\n\tpublic static final String DATA_POST_FLAG_NO = \"N\";");
        sbFileContents.append("\n\tpublic static final String DATA_POST_FLAG_YES = \"Y\";");

        sbFileContents.append("\n\n// API name constants");
        sbFileContents.append("\n\tpublic static final String MASTER_API = \"/webpos/master\";");
        sbFileContents.append("\n\n");

        for(Map.Entry<String, List<Fields>> entry : tablesMap.entrySet()){

            Map<String, String> tblMap = CodeGeneratorUtility.fillTableMap();
            String tableName = entry.getKey();

            String entityInstanceName = tblMap.get(tableName);
            String firstLetter = String.valueOf(entityInstanceName.charAt(0));
            String entityName = tblMap.get(tableName).replaceFirst(firstLetter, firstLetter.toUpperCase());

            entityName = entityName.replaceAll("Pos", "POS");
            entityName = entityName.replaceAll("pos", "POS");

            String apiName = entityName.toUpperCase();
            System.out.println(entityName);
            if(apiName.equals("GROUPHD")){
                System.out.println(apiName);
            }

            sbFileContents.append("\n\tpublic static final String ").append(apiName).append("_MASTER_API")
                    .append(" = \"\";");

            sbFileContents.append("\n\tpublic static final String ").append(apiName).append("_MASTER")
                    .append(" = \"\";");

            sbFileContents.append("\n\tpublic static final String ").append(apiName).append("_MASTER_INTERNAL")
                    .append(" = \"\";");

            sbFileContents.append("\n\tpublic static final String ").append(apiName).append("_MASTER_CODE")
                    .append(" = \"\";");

            sbFileContents.append("\n\tpublic static final int ").append(apiName).append("_MASTER_FORMATTERNO")
                    .append(" = 0;");
            sbFileContents.append("\n");
        }

        sbFileContents.append("\n}");

        fw.write(sbFileContents.toString());
        fw.flush();
    }

}

package com.sanguine.codegenerator.utility;

import com.sanguine.codegenerator.domain.model.Fields;
import com.sanguine.codegenerator.domain.request.CodeGeneratorRequest;

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

public class GenerateController {

    public static boolean createController(String directoryPath
            , String directoryName, String packageName
            , CodeGeneratorRequest request) throws IOException {

        String path = directoryPath+"/"+directoryName+"/"+request.getControllerName()+".java";

        File file = new File(path);
        FileWriter fw = new FileWriter(file);

        String masterApiName = request.getMasterName().contains(" ") ? request.getMasterName().replaceAll(" ", "_").toUpperCase() : request.getMasterName().toUpperCase();
        String serviceInstance = CodeGeneratorUtility.getInstanceName(request.getServiceName());
        String entityInstance = CodeGeneratorUtility.getInstanceName(request.getEntityName());
        String entityListInstance = CodeGeneratorUtility.getInstanceName(request.getEntityName())+"List";
        String responseInstance = CodeGeneratorUtility.getInstanceName(request.getResponseName());

        String saveFunctionName = CodeGeneratorUtility.getFunctionName(request.getEntityName(), "save", "");
        String updateFunctionName = CodeGeneratorUtility.getFunctionName(request.getEntityName(), "update", "");

        String findByCodeName = CodeGeneratorUtility.getFindByCode(request.getFindByCode());
        String findByCodeFunctionName = CodeGeneratorUtility.getFunctionName(request.getEntityName(), "find", findByCodeName);
        String findAllFunctionName = CodeGeneratorUtility.getFunctionName(request.getEntityName(), "find", "List");


        StringBuilder sbFileContents = new StringBuilder();

        sbFileContents.append("package ").append(packageName).append(".controller;");
        sbFileContents.append("\n\nimport ").append(packageName).append(".domain.entity.").append(request.getEntityName()).append(";");
        sbFileContents.append("\nimport ").append(packageName).append(".domain.request.")
                .append(request.getRequestName()).append(";");
        sbFileContents.append("\nimport ").append(packageName).append(".domain.response.").append(request.getResponseName()).append(";");
        sbFileContents.append("\nimport ").append(packageName).append(".service.").append(request.getServiceName()).append(";");
        sbFileContents.append("\nimport ").append("org.springframework.beans.factory.annotation.Autowired;");
        sbFileContents.append("\nimport ").append("org.springframework.http.HttpStatus;");
        sbFileContents.append("\nimport ").append("org.springframework.http.ResponseEntity;");
        sbFileContents.append("\nimport ").append("org.springframework.web.bind.annotation.*;");
        sbFileContents.append("\nimport ").append("java.util.List;");
        sbFileContents.append("\nimport ").append("static com.sanguine.webpos.master.domain.constants.MasterConstants.*;");

        sbFileContents.append("\n\n");

        sbFileContents.append("@RestController");
        sbFileContents.append("\n").append("@RequestMapping(MASTER_API+").append(masterApiName).append("_MASTER_API)");
        sbFileContents.append("\n").append("public class ").append(request.getControllerName()).append("{");
        sbFileContents.append("\n");
        sbFileContents.append("\t@Autowired");
        sbFileContents.append("\n\t").append(request.getServiceName()).append(" ").append(serviceInstance).append(";");

    // Create Master API
        sbFileContents.append("\n\n\t@PostMapping(CREATE_MASTER)");
        sbFileContents.append("\n\tpublic ResponseEntity<").append(request.getResponseName()).append("> ")
                .append("create").append(request.getEntityName())
                .append("(@RequestBody ").append(request.getRequestName())
                .append(" request){");
        sbFileContents.append("\n\n\t\t").append(request.getEntityName())
                .append(" ").append(entityInstance).append(" = ").append(serviceInstance).append(".")
                .append(saveFunctionName).append("(request);");
        sbFileContents.append("\n\n\t\t").append(request.getResponseName())
                .append(" ").append(responseInstance).append(" = ").append(request.getResponseName()).append(".")
                .append("to(").append(createResponseFromFields(entityInstance, request.getFieldsList()));
        sbFileContents.append("\n\n\t\t").append("return new ResponseEntity<>(").append(responseInstance)
                .append(", HttpStatus.CREATED);");
        sbFileContents.append("\n\n\t}");

    // Update Master API
        sbFileContents.append("\n\n\t@PutMapping(UPDATE_MASTER)");
        sbFileContents.append("\n\tpublic ResponseEntity<").append(request.getEntityName()).append(">")
                .append(updateFunctionName).append("(@PathVariable Long id");
        sbFileContents.append("\n\t\t\t, @RequestBody ").append(request.getRequestName()).append(" request){");
        sbFileContents.append("\n\n\t\t").append(request.getEntityName()).append(" ").append(entityInstance)
                .append(" = ").append(serviceInstance).append(".").append(updateFunctionName)
                .append("(request, id);");
        sbFileContents.append("\n\n\t\t").append("return new ResponseEntity<>(").append(entityInstance)
                .append(", HttpStatus.FOUND);");
        sbFileContents.append("\n\n\t}");


    // Get Master By Code API

        sbFileContents.append("\n\n\t@GetMapping(GET_MASTER_BY_CODE+\"/{\"").append(request.getFindByCode()).append("\"}\")");
        sbFileContents.append("\n\tpublic ResponseEntity<").append(request.getEntityName()).append(">")
                .append(findByCodeFunctionName).append("(@PathVariable String ")
                .append(request.getFindByCode()).append("){");
        sbFileContents.append("\n\n\t\t").append(request.getEntityName()).append(" ").append(entityInstance)
                .append(" = ").append(serviceInstance).append(".").append(findByCodeFunctionName)
                .append("(").append(request.getFindByCode()).append(")");
        sbFileContents.append("\n\t\t").append("return new ResponseEntity<>(").append(entityInstance)
                .append(", HttpStatus.FOUND);");
        sbFileContents.append("\n\n\t}");


// Get All Masters

        sbFileContents.append("\n\n\t@GetMapping(GET_ALL)");
        sbFileContents.append("\n\tpublic ResponseEntity<List<").append(request.getEntityName()).append(">>")
                .append(findAllFunctionName).append("();");

        sbFileContents.append("\n\n\t\t").append("List<").append(request.getEntityName()).append("> ")
                .append(entityListInstance).append(" = ").append(serviceInstance).append(".")
                .append(findAllFunctionName).append("();");

        sbFileContents.append("\n\t\t").append("return new ResponseEntity<>(").append(entityListInstance)
                .append(", HttpStatus.FOUND);");
        sbFileContents.append("\n\n\t}");

        sbFileContents.append("\n}");

        fw.write(sbFileContents.toString());
        fw.flush();


        return true;
    }


    private static String createResponseFromFields(String entityInstance, List<Fields> fieldsList){

        StringBuilder sb = new StringBuilder();

        for(Fields field : fieldsList){
            sb.append("\n\t\t\t").append(entityInstance).append(".")
                    .append(CodeGeneratorUtility.getGetterMethodName(field.getFieldName()))
                    .append(",");
        }

        String response = sb.substring(0, sb.lastIndexOf(","));
        response = response+");";

        return response;
    }


    public static void generateController(String directoryPath, String directoryName
            , String packageName, Map<String, List<Fields>> tablesMap) throws IOException {

        String repoPath = directoryPath+"/"+directoryName+"/controller/";
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

            String controllerName = entityName+"Controller";

            String requestName = entityName+"Request";
            String requestInstanceName = entityInstanceName+"Request";

            String responseName = entityName+"Response";
            String responseInstanceName = entityInstanceName+"Response";

            String serviceName = entityName+"Service";
            String serviceInstanceName = entityInstanceName+"Service";

            String apiName = entityName.toUpperCase();

            String saveFunctionName = "save"+entityName;

            String updateFunctionName = "update"+entityName;

            String findAllFunctionName = "find"+entityName+"List";

            String entityListInstance = entityInstanceName+"List";

            String path = directoryPath+"/"+directoryName+"/controller/"+controllerName+".java";
            File file = new File(path);
            FileWriter fw = new FileWriter(file);

            StringBuilder sbFileContents = new StringBuilder();

            sbFileContents.append("package ").append(packageName).append(".controller;");
            sbFileContents.append("\n\nimport ").append(packageName).append(".domain.entity.").append(entityName).append(";");
            sbFileContents.append("\nimport ").append(packageName).append(".domain.request.")
                    .append(requestName).append(";");
            sbFileContents.append("\nimport ").append(packageName).append(".domain.response.").append(responseName).append(";");
            sbFileContents.append("\nimport ").append(packageName).append(".service.").append(serviceName).append(";");
            sbFileContents.append("\nimport ").append("org.springframework.beans.factory.annotation.Autowired;");
            sbFileContents.append("\nimport ").append("org.springframework.http.HttpStatus;");
            sbFileContents.append("\nimport ").append("org.springframework.http.ResponseEntity;");
            sbFileContents.append("\nimport ").append("org.springframework.web.bind.annotation.*;");
            sbFileContents.append("\nimport ").append("java.util.List;");
            sbFileContents.append("\nimport ").append("static com.sanguine.webpos.master.domain.constants.MasterConstants.*;");

            sbFileContents.append("\n\n");

            sbFileContents.append("@RestController");
            sbFileContents.append("\n").append("@RequestMapping(MASTER_API+").append(apiName).append("_MASTER_API)");
            sbFileContents.append("\n").append("public class ").append(controllerName).append("{");
            sbFileContents.append("\n");
            sbFileContents.append("\t@Autowired");
            sbFileContents.append("\n\t").append(serviceName).append(" ").append(serviceInstanceName).append(";");

        // Create Master API
            sbFileContents.append("\n\n\t@PostMapping(CREATE_MASTER)");
            sbFileContents.append("\n\tpublic ResponseEntity<").append(responseName).append("> ")
                    .append("create").append(entityName)
                    .append("(@RequestBody ").append(requestName)
                    .append(" request){");
            sbFileContents.append("\n\n\t\t").append(entityName)
                    .append(" ").append(entityInstanceName).append(" = ").append(serviceInstanceName).append(".")
                    .append(saveFunctionName).append("(request);");
            sbFileContents.append("\n\n\t\t").append(responseName)
                    .append(" ").append(responseInstanceName).append(" = ").append(responseName).append(".")
                    .append("to(").append("\n\t // TODO - Add code to set response object ").append("\n\t\t);");
            sbFileContents.append("\n\n\t\t").append("return new ResponseEntity<>(").append(responseInstanceName)
                    .append(", HttpStatus.CREATED);");
            sbFileContents.append("\n\n\t}");

        // Update Master API
            sbFileContents.append("\n\n\t@PutMapping(UPDATE_MASTER)");
            sbFileContents.append("\n\tpublic ResponseEntity<").append(entityName).append(">")
                    .append(updateFunctionName).append("(@PathVariable Long id");
            sbFileContents.append("\n\t\t\t, @RequestBody ").append(requestName).append(" request){");
            sbFileContents.append("\n\n\t\t").append(entityName).append(" ").append(entityInstanceName)
                    .append(" = ").append(serviceInstanceName).append(".").append(updateFunctionName)
                    .append("(request, id);");
            sbFileContents.append("\n\n\t\t").append("return new ResponseEntity<>(").append(entityInstanceName)
                    .append(", HttpStatus.OK);");
            sbFileContents.append("\n\n\t}");


        // Get Master By Code API
            sbFileContents.append("\n\n\t@GetMapping(GET_MASTER_BY_CODE+\"/{").append("}\")");
            sbFileContents.append("\n\tpublic ResponseEntity<").append(entityName).append("> ")
                    .append("(@PathVariable String ")
                    .append("){");
            sbFileContents.append("\n\n\t //TODO - Add code to fetch entity");

            sbFileContents.append("\n\t\t").append("return new ResponseEntity<>(").append(entityInstanceName)
                    .append(", HttpStatus.OK);");
            sbFileContents.append("\n\n\t}");


        // Get All Masters
            sbFileContents.append("\n\n\t@GetMapping(GET_ALL)");
            sbFileContents.append("\n\tpublic ResponseEntity<List<").append(entityName).append(">> ")
                    .append(findAllFunctionName).append("(){");

            sbFileContents.append("\n\n\t\t").append("List<").append(entityName).append("> ")
                    .append(entityListInstance).append(" = ").append(serviceInstanceName).append(".")
                    .append(findAllFunctionName).append("();");

            sbFileContents.append("\n\t\t").append("return new ResponseEntity<>(").append(entityListInstance)
                    .append(", HttpStatus.OK);");
            sbFileContents.append("\n\n\t}");

            sbFileContents.append("\n}");

            fw.write(sbFileContents.toString());
            fw.flush();
        }
    }

}

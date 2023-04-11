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

public class GenerateService {

    public static boolean createService(String directoryPath
            , String directoryName, String packageName
            , CodeGeneratorRequest request) throws IOException {

        String path = directoryPath+"/"+directoryName+"/"+request.getServiceName()+".java";

        File file = new File(path);
        FileWriter fw = new FileWriter(file);

        String masterNameUpperCase = request.getMasterName().contains(" ") ? request.getMasterName().replaceAll(" ", "_").toUpperCase() : request.getMasterName().toUpperCase();
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

        sbFileContents.append("package ").append(packageName).append(".service;");
        sbFileContents.append("\n\nimport ").append(packageName).append(".domain.entity.").append(request.getEntityName()).append(";");
        sbFileContents.append("\nimport ").append(packageName).append(".domain.request.")
                .append(request.getRequestName()).append(";");
        sbFileContents.append("\nimport ").append(packageName).append(".exception.MasterNotFoundException;");
        sbFileContents.append("\nimport ").append(packageName).append(".utility.GlobalProperties").append(";");
        sbFileContents.append("\nimport ").append(packageName).append(".utility.MasterUtility").append(";");
        sbFileContents.append("\nimport ").append(packageName).append(".service.").append(request.getRepositoryName()).append(";");
        sbFileContents.append("\nimport ").append("org.springframework.beans.factory.annotation.Autowired;");
        sbFileContents.append("\nimport ").append("org.springframework.stereotype.Service;");
        sbFileContents.append("\nimport ").append("java.time.OffsetDateTime;");
        sbFileContents.append("\nimport ").append("java.util.List;");
        sbFileContents.append("\nimport ").append("java.util.Optional;");
        sbFileContents.append("\nimport ").append("static com.sanguine.webpos.master.domain.constants.MasterConstants.*;");

        sbFileContents.append("\n\n");

        sbFileContents.append("@Service");
        sbFileContents.append("\n").append("public class ").append(request.getServiceName()).append("{");
        sbFileContents.append("\n\t@Autowired");
        sbFileContents.append("\n\t").append(request.getRepositoryName()).append(" ").append(responseInstance).append(";");

        sbFileContents.append("\n\t@Autowired");
        sbFileContents.append("\n\t").append("InternalMasterService internalMasterService;");


        sbFileContents.append("\n\n\tpublic ").append(request.getEntityName())
                .append(" ").append(findByCodeFunctionName).append("(String").append(findByCodeName)
                .append("){");
        sbFileContents.append("\n\t\t").append(responseInstance).append(".")
                .append(findByCodeFunctionName).append("(").append(findByCodeName).append(")")
                .append(".orElseThrow(() -> new MasterNotFoundException(").append(masterNameUpperCase)
                .append("_MASTER").append("));");
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


    public static void generateService(String directoryPath, String directoryName
            , String packageName, Map<String, List<Fields>> tablesMap) throws IOException {

        String repoPath = directoryPath+"/"+directoryName+"/service/";
        Path dirPath = Paths.get(repoPath);
        Files.createDirectories(dirPath);

        Arrays.stream(Objects.requireNonNull(new File(repoPath).listFiles())).forEach(File::delete);

        for(Map.Entry<String, List<Fields>> entry : tablesMap.entrySet()){

            Map<String, String> tblMap = CodeGeneratorUtility.fillTableMap();
            String tableName = entry.getKey();

            String entityInstanceName = tblMap.get(tableName);
            String firstLetter = String.valueOf(entityInstanceName.charAt(0));
            String entityName = tblMap.get(tableName).replaceFirst(firstLetter, firstLetter.toUpperCase());

            entityName = entityName.replaceAll("Pos", "POS");
            entityName = entityName.replaceAll("pos", "POS");

            String requestName = entityName+"Request";
            String requestInstanceName = entityInstanceName+"Request";

            String responseName = entityName+"Response";
            String responseInstanceName = entityInstanceName+"Response";

            String serviceName = entityName+"Service";
            String serviceInstanceName = entityInstanceName+"Service";

            String repositoryName = entityName+"Repository";
            String repositoryInstanceName = entityInstanceName+"Repository";

            String apiName = entityName.toUpperCase();
            String masterInternal = apiName+"_MASTER_INTERNAL";
            String masterCode = apiName+"_MASTER_CODE";
            String masterFormatterNo = apiName+"_MASTER_FORMATTERNO";

            String saveFunctionName = "save"+entityName;

            String updateFunctionName = "update"+entityName;

            String findAllFunctionName = "find"+entityName+"List";

            String entityListInstance = entityInstanceName+"List";

            String path = directoryPath+"/"+directoryName+"/service/"+serviceName+".java";
            File file = new File(path);
            FileWriter fw = new FileWriter(file);

            StringBuilder sbFileContents = new StringBuilder();

            sbFileContents.append("package ").append(packageName).append(".service;");
            sbFileContents.append("\n\nimport ").append(packageName).append(".domain.entity.")
                    .append(entityName).append(";");
            sbFileContents.append("\nimport ").append(packageName).append(".domain.request.")
                    .append(requestName).append(";");
            sbFileContents.append("\nimport ").append(packageName).append(".exception.MasterNotFoundException;");
            sbFileContents.append("\nimport ").append(packageName).append(".utility.GlobalProperties").append(";");
            sbFileContents.append("\nimport ").append(packageName).append(".utility.MasterUtility").append(";");
            sbFileContents.append("\nimport ").append(packageName).append(".repository.").append(repositoryName).append(";");
            sbFileContents.append("\nimport ").append("org.springframework.beans.factory.annotation.Autowired;");
            sbFileContents.append("\nimport ").append("org.springframework.stereotype.Service;");
            sbFileContents.append("\nimport ").append("java.time.OffsetDateTime;");
            sbFileContents.append("\nimport ").append("java.util.List;");
            sbFileContents.append("\nimport ").append("java.util.Optional;");
            sbFileContents.append("\nimport ").append("static com.sanguine.webpos.master.domain.constants.MasterConstants.*;");

            sbFileContents.append("\n\n");

            sbFileContents.append("@Service");
            sbFileContents.append("\n").append("public class ").append(serviceName).append("{");
            sbFileContents.append("\n\n\t@Autowired");
            sbFileContents.append("\n\t").append(repositoryName).append(" ")
                    .append(repositoryInstanceName).append(";");

            sbFileContents.append("\n\n\t@Autowired");
            sbFileContents.append("\n\t").append("InternalMasterService internalMasterService;");


        // Save Master
            sbFileContents.append("\n\n\t").append("public ").append(entityName).append(" ")
                    .append(saveFunctionName).append("(").append(requestName).append(" ")
                    .append(requestInstanceName).append("){");

            sbFileContents.append("\n\n\t\t").append(entityName).append(" ").append(entityInstanceName)
                            .append(" = ").append("new ").append(entityName).append("();");

            sbFileContents.append("\n\n\t\tString code = ").append("MasterUtility.getMasterCode(internalMasterService.findLastNoByTransactionType(")
                    .append(masterInternal).append("), ").append(masterCode).append(", ")
                    .append(masterFormatterNo).append(");");

            sbFileContents.append("\n\n\t //TODO : Set master fields to be saved");

            sbFileContents.append("\n\t\t").append(entityInstanceName).append(".")
                    .append("setDataPostFlag(").append("DATA_POST_FLAG_NO);");
            sbFileContents.append("\n\t\t").append(entityInstanceName).append(".")
                    .append("setClientCode(").append("GlobalProperties.clientCode);");
            sbFileContents.append("\n\t\t").append(entityInstanceName).append(".")
                    .append("setUserCreated(").append("GlobalProperties.userCode);");
            sbFileContents.append("\n\t\t").append(entityInstanceName).append(".")
                    .append("setUserEdited(").append("GlobalProperties.userCode);");
            sbFileContents.append("\n\t\t").append(entityInstanceName).append(".")
                    .append("setDateCreated(").append("OffsetDateTime.now());");
            sbFileContents.append("\n\t\t").append(entityInstanceName).append(".")
                    .append("setDateEdited(").append("OffsetDateTime.now());");

            sbFileContents.append("\n\t\t").append("return ").append(repositoryInstanceName).append(".")
                    .append("save(").append(entityInstanceName).append(");");

            sbFileContents.append("\n\t}");


        // Update master

            sbFileContents.append("\n\n\t").append("public ").append(entityName).append(" ")
                    .append(updateFunctionName).append("(").append(requestName).append(" ")
                    .append(requestInstanceName).append(", ").append("Long id){");

            sbFileContents.append("\n\n\t\t").append(entityName).append(" ").append(entityInstanceName)
                    .append(" = ").append(repositoryInstanceName).append(".findById(id)")
                    .append(".orElseThrow(() -> new MasterNotFoundException(").append(apiName).append("_MASTER));");

            sbFileContents.append("\n\n\t //TODO : Set master fields to be updated");

            sbFileContents.append("\n\t\t").append(entityInstanceName).append(".")
                    .append("setClientCode(").append("GlobalProperties.clientCode);");

            sbFileContents.append("\n\t\t").append(entityInstanceName).append(".")
                    .append("setUserEdited(").append("GlobalProperties.userCode);");

            sbFileContents.append("\n\t\t").append(entityInstanceName).append(".")
                    .append("setDateEdited(").append("OffsetDateTime.now());");

            sbFileContents.append("\n\t\t").append("return ").append(repositoryInstanceName).append(".")
                    .append("save(").append(entityInstanceName).append(");");

            sbFileContents.append("\n\t}");


        // Find master list

            sbFileContents.append("\n\n\t").append("public List<").append(entityName).append("> ")
                    .append(findAllFunctionName).append("(){");

            sbFileContents.append("\n\t\t").append("return ").append(repositoryInstanceName).append(".")
                    .append(findAllFunctionName).append("();");

            sbFileContents.append("\n\t}");


            sbFileContents.append("\n\n\t").append("// TODO : Add other methods to fetch data from db against given criteria");


            sbFileContents.append("\n\n}");

            fw.write(sbFileContents.toString());
            fw.flush();
        }
    }

}

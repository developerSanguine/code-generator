package com.sanguine.codegenerator.service;

import com.sanguine.codegenerator.config.CodegeneratorProperties;
import com.sanguine.codegenerator.domain.model.Fields;
import com.sanguine.codegenerator.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeGeneratorService {

    @Autowired
    CodegeneratorProperties codegeneratorDirectoryProperties;

    @Value("${webpos.codegenerator.packagename}")
    private String packageName;

    @Value("${spring.datasource.url}")
    private  String datasourceUrl;

    @Value("${spring.datasource.username}")
    private  String datasourceUname;

    @Value("${spring.datasource.password}")
    private  String datasourcePassword;

    public boolean generateCode() throws IOException {

        /*String masterName = request.getMasterName().replaceAll(" ","");
        List<Fields> fieldsList = request.getFieldsList();

        String controllerName = masterName.concat(CONTROLLER_POSTFIX);
        String serviceName = masterName.concat(SERVICE_POSTFIX);
        String entityName = masterName.concat(ENTITY_POSTFIX);
        String repositoryName = masterName.concat(REPOSITORY_POSTFIX);
        String responseName = masterName.concat(RESPONSE_POSTFIX);
        String requestName = CREATE_REQUEST_PREFIX.concat(entityName)+CREATE_REQUEST_POSTFIX;

        request.setEntityName(entityName);
        request.setControllerName(controllerName);
        request.setServiceName(serviceName);
        request.setRepositoryName(repositoryName);
        request.setResponseName(responseName);
        request.setRequestName(requestName);

        GenerateController.createController(codegeneratorDirectoryProperties.getPath()
            , codegeneratorDirectoryProperties.getName(), packageName
            , request);*/

        List<String> tableList = getTableList();
        Map<String, List<Fields>> mapTableDetails = new HashMap<>();

        for(String entity:tableList){
            List<Fields> fieldsList = getMasterDetails(entity);
            mapTableDetails.put(entity, fieldsList);
        }

        GenerateController.generateController(codegeneratorDirectoryProperties.getPath()
                , codegeneratorDirectoryProperties.getName(), packageName, mapTableDetails);

        GenerateService.generateService(codegeneratorDirectoryProperties.getPath()
                , codegeneratorDirectoryProperties.getName(), packageName, mapTableDetails);

        GenerateEntity.generateEntity(codegeneratorDirectoryProperties.getPath()
                , codegeneratorDirectoryProperties.getName(), packageName, mapTableDetails);

        GenerateRepository.generateRepository(codegeneratorDirectoryProperties.getPath()
                , codegeneratorDirectoryProperties.getName(), packageName, mapTableDetails);

        GenerateRequest.generateRequestObject(codegeneratorDirectoryProperties.getPath()
                , codegeneratorDirectoryProperties.getName(), packageName, mapTableDetails);

        GenerateResponse.generateResponseObject(codegeneratorDirectoryProperties.getPath()
                , codegeneratorDirectoryProperties.getName(), packageName, mapTableDetails);

        GenerateConstants.generateConstants(codegeneratorDirectoryProperties.getPath()
                , codegeneratorDirectoryProperties.getName(), packageName, mapTableDetails);

        return true;
    }


    public boolean generateControllersCode() throws IOException {

        List<String> tableList = getTableList();
        Map<String, List<Fields>> mapTableDetails = new HashMap<>();

        for(String entity:tableList){
            List<Fields> fieldsList = getMasterDetails(entity);
            mapTableDetails.put(entity, fieldsList);
        }

        return true;
    }


    public boolean generateEntitiesCode() throws IOException {

        List<String> tableList = getTableList();
        Map<String, List<Fields>> mapTableDetails = new HashMap<>();

        for(String entity:tableList){
            List<Fields> fieldsList = getMasterDetails(entity);
            mapTableDetails.put(entity, fieldsList);
        }

        GenerateEntity.generateEntity(codegeneratorDirectoryProperties.getPath()
                , codegeneratorDirectoryProperties.getName(), packageName, mapTableDetails);

        return true;
    }


    public boolean generateRepositoriesCode() throws IOException {

        List<String> tableList = getTableList();
        Map<String, List<Fields>> mapTableDetails = new HashMap<>();

        for(String entity:tableList){
            List<Fields> fieldsList = getMasterDetails(entity);
            mapTableDetails.put(entity, fieldsList);
        }

        GenerateRepository.generateRepository(codegeneratorDirectoryProperties.getPath()
                , codegeneratorDirectoryProperties.getName(), packageName, mapTableDetails);

        return true;
    }




    public List<String> getTableList() {

        String query = "Show tables";
        List<String> tableList = new ArrayList<>();

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/multiwebpos?useSSL=false", "root", "root");

             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            //System.out.println("Tables in the current database: ");
            while(rs.next()) {
                //System.out.println(rs.getString(1));
                tableList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return tableList;
    }

    public List<Fields> getMasterDetails(String tableName) {

        String query = "select * from "+tableName;
        List<Fields> fieldsList = new ArrayList<>();

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/multiwebpos?useSSL=false", "root", "root");

             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            for(int i=1; i<resultSetMetaData.getColumnCount();i++) {
                Fields field = new Fields();
                field.setFieldName(resultSetMetaData.getColumnName(i));
                field.setFieldType(resultSetMetaData.getColumnTypeName(i));
                fieldsList.add(field);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return fieldsList;
    }


    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


}

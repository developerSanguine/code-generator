package com.sanguine.codegenerator.controller;

import com.sanguine.codegenerator.domain.model.Fields;
import com.sanguine.codegenerator.domain.request.MasterDetailsRequest;
import com.sanguine.codegenerator.domain.response.MasterDetailsResponse;
import com.sanguine.codegenerator.service.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.sanguine.codegenerator.constants.CodeGeneratorConstants.CODE_GENRATOR_API;
import static com.sanguine.codegenerator.constants.CodeGeneratorConstants.MASTER_API;

@RestController
@RequestMapping(CODE_GENRATOR_API+MASTER_API)
public class MasterCodeGeneratorController {

    @Autowired
    CodeGeneratorService codeGeneratorService;

    @PostMapping("/generate-code")
    public ResponseEntity<Boolean> generateCode() throws IOException {

        return new ResponseEntity<>(codeGeneratorService.generateCode(), HttpStatus.OK);
    }

    /*@PostMapping("/generate-code")
    public ResponseEntity<Boolean> generateCode(@RequestBody CodeGeneratorRequest request) throws IOException {

        return new ResponseEntity<>(codeGeneratorService.generateCode(request), HttpStatus.OK);
    }*/


    @GetMapping("/get-master-details")
    public ResponseEntity<MasterDetailsResponse> getMasterDetails(@RequestBody MasterDetailsRequest request) throws IOException {

        List<Fields> fieldsList = codeGeneratorService.getMasterDetails(request.getTableName());
        MasterDetailsResponse masterDetailsResponse = new MasterDetailsResponse();
        masterDetailsResponse.setFieldsList(fieldsList);

        return new ResponseEntity<>(masterDetailsResponse, HttpStatus.OK);
    }

    @GetMapping("/generate-master-entities")
    public ResponseEntity<Boolean> generateMasterEntities() throws IOException {

        return new ResponseEntity<>(codeGeneratorService.generateEntitiesCode(), HttpStatus.OK);
    }


    @GetMapping("/generate-master-repositories")
    public ResponseEntity<Boolean> generateMasterRepositories() throws IOException {

        return new ResponseEntity<>(codeGeneratorService.generateRepositoriesCode(), HttpStatus.OK);
    }

}

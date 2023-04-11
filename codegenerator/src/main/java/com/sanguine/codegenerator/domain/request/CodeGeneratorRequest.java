package com.sanguine.codegenerator.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sanguine.codegenerator.domain.model.Fields;
import lombok.Data;

import java.util.List;

@Data
public class CodeGeneratorRequest{

        @JsonProperty("masterName") private String masterName;
        @JsonProperty("fields") private List<Fields> fieldsList;
        @JsonProperty("formatterNo") private String formatterNo;
        @JsonProperty("findByCode") private String findByCode;
        private String controllerName;
        private String serviceName;
        private String repositoryName;
        private String entityName;
        private String responseName;
        private String requestName;
}

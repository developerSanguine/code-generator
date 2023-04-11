package com.sanguine.codegenerator.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sanguine.codegenerator.domain.model.Fields;
import lombok.Data;

import java.util.List;

@Data
public class MasterDetailsResponse {
        @JsonProperty("masterName") private String masterName;
        @JsonProperty("formatterNo") private String formatterNo;
        @JsonProperty("findByCode") private String findByCode;
        @JsonProperty("fields") private List<Fields> fieldsList;
}

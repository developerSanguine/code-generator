package com.sanguine.codegenerator.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sanguine.codegenerator.domain.model.Fields;
import lombok.Data;

import java.util.List;

@Data
public class MasterDetailsRequest {

        @JsonProperty("tableName") private String tableName;
}

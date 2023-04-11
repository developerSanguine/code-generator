package com.sanguine.codegenerator.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EntityListRequest {

        @JsonProperty("tableNameList") private List<String> tableNameList;
}

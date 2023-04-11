package com.sanguine.codegenerator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "webpos.codegenerator.directory")
@Data
public class CodegeneratorProperties {

    private String path;
    private String name;
}

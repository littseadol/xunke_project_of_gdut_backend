package com.ruoyi.lab.conf;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "knowledge.base")
@Data
public class KnowledgeBaseConfig {
    private String filePath;
    private boolean cacheEnabled;
    private boolean reloadOnStartup;
}

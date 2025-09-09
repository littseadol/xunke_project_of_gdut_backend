package com.ruoyi.lab.service.impl;

import com.ruoyi.lab.conf.KnowledgeBaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;

@Slf4j
@Service
public class KnowledgeBaseService {

    @Autowired
    private KnowledgeBaseConfig config;

    private Set<String> knowledgePoints = new HashSet<>();

    @PostConstruct
    public void init() {
        if (config.isReloadOnStartup()) {
            loadKnowledgeBase();
        }
    }

    /**
     * 加载知识库文件
     */
    public synchronized void loadKnowledgeBase() {
        try {


            ClassPathResource resource = new ClassPathResource(config.getFilePath());
            Set<String> newPoints = new HashSet<>();
            log.info("尝试加载知识库文件: {}", config.getFilePath());
            log.info("文件是否存在: {}", resource.exists());
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    // 跳过空行和注释
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#")) {
                        continue;
                    }

                    // 分割逗号分隔的术语
                    String[] terms = line.split(",");
                    for (String term : terms) {
                        String trimmedTerm = term.trim();
                        if (!trimmedTerm.isEmpty()) {
                            newPoints.add(trimmedTerm);
                        }
                    }
                }
            }

            knowledgePoints = newPoints;
            log.info("知识库加载完成，共加载 {} 个术语", knowledgePoints.size());

        } catch (Exception e) {
            log.error("加载知识库文件失败: {}", e.getMessage());
            // 可以设置默认值或抛出异常
        }
    }

    /**
     * 获取知识库术语
     */
    public Set<String> getKnowledgePoints() {
        return knowledgePoints;
    }

    /**
     * 检查术语是否在知识库中
     */
    public boolean containsTerm(String term) {
        return knowledgePoints.contains(term);
    }

    /**
     * 重新加载知识库
     */
    public void reload() {
        loadKnowledgeBase();
    }

    /**
     * 添加单个术语（运行时动态添加）
     */
    public void addTerm(String term) {
        knowledgePoints.add(term.trim());
    }

    /**
     * 批量添加术语
     */
    public void addTerms(Set<String> terms) {
        terms.forEach(term -> knowledgePoints.add(term.trim()));
    }

    /**
     * 获取知识库大小
     */
    public int size() {
        return knowledgePoints.size();
    }
}
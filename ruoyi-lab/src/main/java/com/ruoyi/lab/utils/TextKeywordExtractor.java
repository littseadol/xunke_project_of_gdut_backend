package com.ruoyi.lab.utils;

import com.ruoyi.lab.service.impl.KnowledgeBaseService;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class TextKeywordExtractor {
    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    private StanfordCoreNLP pipeline;
    private static final Set<String> KNOWLEDGE_POINTS = new HashSet<>();
    @PostConstruct
    public void init() {
        KNOWLEDGE_POINTS.addAll(knowledgeBaseService.getKnowledgePoints());
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public List<String> extractKeywords(String text) {
        if (StringUtils.isBlank(text)) {
            return Collections.emptyList();
        }

        try {
            // 1. 先进行全文匹配（优先匹配长复合词）
            Set<String> foundKeywords = new LinkedHashSet<>();

            // 按长度降序匹配，确保长词优先
            KNOWLEDGE_POINTS.stream()
                    .sorted((a, b) -> Integer.compare(b.length(), a.length()))
                    .forEach(term -> {
                        if (text.contains(term)) {
                            foundKeywords.add(term);
                        }
                    });

            // 2. 补充NLP分词结果（捕获可能遗漏的单词）
            Annotation document = new Annotation(text);
            pipeline.annotate(document);

            document.get(CoreAnnotations.TokensAnnotation.class)
                    .stream()
                    .map(CoreLabel::word)
                    .filter(KNOWLEDGE_POINTS::contains)
                    .forEach(foundKeywords::add);

            return new ArrayList<>(foundKeywords);

        } catch (Exception e) {
            throw new RuntimeException("关键词提取失败: " + e.getMessage());
        }
    }

    /**
     * 增强版：包含模糊匹配和同义词
     */
    public List<String> extractKeywordsEnhanced(String text) {
        Set<String> keywords = new LinkedHashSet<>();

        // 1. 精确匹配
        KNOWLEDGE_POINTS.forEach(term -> {
            if (text.contains(term)) {
                keywords.add(term);
            }
        });

        // 2. 模糊匹配（处理大小写和简写）
        String lowerText = text.toLowerCase();
        KNOWLEDGE_POINTS.forEach(term -> {
            String lowerTerm = term.toLowerCase();
            if (lowerText.contains(lowerTerm) && !keywords.contains(term)) {
                keywords.add(term);
            }
        });

        // 3. 处理常见变体
        handleVariants(text, keywords);

        return new ArrayList<>(keywords);
    }

    /**
     * 处理常见术语变体
     */
    private void handleVariants(String text, Set<String> keywords) {
        // Hessian矩阵变体
        if (text.contains("Hessian") || text.contains("hessian")) {
            keywords.add("Hessian矩阵");
        }

        // 梯度相关变体
        if (text.contains("梯度方向") || text.contains("梯度向量")) {
            keywords.add("梯度");
        }

        // 极值相关变体
        if (text.contains("极大值") || text.contains("极小值")) {
            keywords.add("极值");
        }
    }

    /**
     * 提取并排序（按出现顺序）
     */
    public List<String> extractKeywordsInOrder(String text) {
        List<String> keywords = extractKeywords(text);

        // 按文本中出现顺序排序
        keywords.sort((a, b) -> {
            int indexA = text.indexOf(a);
            int indexB = text.indexOf(b);
            return Integer.compare(indexA, indexB);
        });

        return keywords;
    }
}
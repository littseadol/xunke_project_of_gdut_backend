package com.ruoyi.lab.service.impl;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.ruoyi.lab.service.IAiKeywordExtractorService;
import com.ruoyi.lab.utils.TextKeywordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AiKeywordExtractorServiceImpl implements IAiKeywordExtractorService {

    @Autowired
    private TextKeywordExtractor textKeywordExtractor;

    // 强化版停用词表（包含所有需要过滤的词汇）
    private static final Set<String> STOP_WORDS = new HashSet<String>() {{
        // 基础虚词
        addAll(Arrays.asList("的", "了", "在", "是", "和", "就", "不", "都", "也", "很",
                "这", "那", "他", "她", "它", "我们", "你们", "他们",
                "这个", "那个", "什么", "怎么", "可以", "应该", "需要"));

        // 单个动词（全部过滤）
        addAll(Arrays.asList("有", "做", "用", "使", "让", "叫", "来", "去", "到", "要",
                "会", "能", "可", "想", "看", "说", "听", "走", "开", "关",
                "给", "拿", "放", "找", "问", "答", "写", "读", "学", "干"));

        // 状语词汇
        addAll(Arrays.asList("非常", "十分", "相当", "比较", "稍微", "完全", "部分", "整体",
                "局部", "实际", "理论", "非常", "特别", "尤其", "主要", "重要",
                "关键", "基本", "各种", "多个", "一些", "很多", "较少", "更多",
                "更少", "非常", "十分", "相当", "比较", "稍微", "完全", "部分"));

        // 连接词
        addAll(Arrays.asList("首先", "然后", "最后", "比如", "例如", "包括", "具有",
                "进行", "实现", "通过", "对于", "关于", "根据", "按照", "由于",
                "因此", "所以", "但是", "然而", "虽然", "尽管", "如果", "只要",
                "因为", "并且", "或者", "不仅", "而且", "即使", "无论", "不管"));

        // 无实际意义的短语
        addAll(Arrays.asList("一般来说", "简单来说", "具体来说", "换句话说", "总而言之",
                "综上所述", "在这种情况下", "从某种意义上", "在某种程度上"));
    }};

    // 数学符号和变量模式（需要过滤）
    private static final Set<String> MATH_SYMBOLS = Set.of(
            "f_x", "f_xx", "f_xy", "f_yx", "f_yy", "dx", "dy", "dz",
            "∂x", "∂y", "∂z", "∂f", "∂²", "Δx", "Δy", "Δz"
    );

    @Override
    public List<String> extractKeywords(String text) {
        // 1. 调用工具类获取原始关键词
        List<String> rawKeywords = textKeywordExtractor.extractKeywords(text);

        // 2. 服务层清洗流程
        return rawKeywords.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(keyword -> !keyword.isEmpty())
                .filter(this::isValidKeyword)  // 有效性验证
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 关键词有效性验证
     */
    private boolean isValidKeyword(String keyword) {
        // 基础检查
        if (keyword.length() < 2 || keyword.length() > 12) return false;

        // 停用词检查
        if (STOP_WORDS.contains(keyword)) return false;

        // 数学符号检查
        if (MATH_SYMBOLS.contains(keyword)) return false;

        // 使用HanLP进行词性验证
        List<Term> terms = HanLP.segment(keyword);
        return !terms.isEmpty() &&
                terms.get(0).nature.startsWith("n"); // 主要词性为名词
    }

    /**
     * 增强清洗（带自定义停用词）
     */
    public List<String> extractKeywords(String text, Set<String> customStopWords) {
        Set<String> allStopWords = new HashSet<>(STOP_WORDS);
        allStopWords.addAll(customStopWords);

        List<String> rawKeywords = textKeywordExtractor.extractKeywords(text);

        return rawKeywords.stream()
                .filter(keyword -> !allStopWords.contains(keyword))
                .filter(this::isValidKeyword)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 提取并保留复合术语（如"多元函数微分学"）
     */
    public List<String> extractComplexTerms(String text) {
        List<String> keywords = new ArrayList<>();

        // 先提取长短语（4字以上）
        List<Term> terms = HanLP.segment(text);
        StringBuilder phraseBuffer = new StringBuilder();

        for (Term term : terms) {
            if (term.nature.startsWith("n") && term.word.length() >= 2) {
                if (phraseBuffer.length() > 0) phraseBuffer.append(term.word);
                else phraseBuffer.append(term.word);
            } else if (phraseBuffer.length() > 0) {
                if (phraseBuffer.length() >= 4) {
                    keywords.add(phraseBuffer.toString());
                }
                phraseBuffer.setLength(0);
            }
        }

        // 补充工具类提取结果
        keywords.addAll(textKeywordExtractor.extractKeywords(text));

        return keywords.stream()
                .distinct()
                .filter(this::isValidKeyword)
                .collect(Collectors.toList());
    }
}
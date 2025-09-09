package com.ruoyi.lab.service;

import java.util.List;

public interface IAiKeywordExtractorService {
    List<String> extractKeywords(String text);
}
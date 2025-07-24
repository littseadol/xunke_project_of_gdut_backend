package com.ruoyi.lab.service;

import cn.smartjavaai.common.entity.DetectionRectangle;
import com.ruoyi.lab.domain.AiFaceRectangle;

import java.util.List;

public interface IAiImageIOService {
    public String drawRect(String imagePath, List<AiFaceRectangle> aiFaceRectangles);
}

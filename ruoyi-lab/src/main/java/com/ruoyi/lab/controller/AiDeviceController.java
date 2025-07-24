package com.ruoyi.lab.controller;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.lab.domain.AiDevice;
import com.ruoyi.lab.domain.AiDeviceAlgorithm;
import com.ruoyi.lab.domain.request.*;
import com.ruoyi.lab.service.IAiDeviceService;
import com.ruoyi.lab.service.IAiMediaService;
import com.ruoyi.common.core.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/lab/device")
@Slf4j
public class AiDeviceController extends BaseController {
    @Autowired
    private IAiDeviceService iDeviceService;

    @Autowired
    private IAiMediaService iAiMediaService;

    // 上传摄像头快照
    @PostMapping("/upload-snapshot")
    public AjaxResult uploadSnapshot(@RequestBody AiDevice aiDevice) throws Exception {
        String rtspUrl = aiDevice.getRtspUrl();
        String snapshotUrl = iAiMediaService.getSnap(rtspUrl);
        if (snapshotUrl == null) {
            return AjaxResult.error("生成快照失败");
        } else {
            return AjaxResult.success((Object) snapshotUrl);
        }
    }

    @PostMapping
    public AjaxResult add(@RequestBody AiDevice aiDevice) throws Exception {
        log.debug("添加摄像头");
        log.debug(JSON.toJSONString(aiDevice));
        iDeviceService.addDevice(aiDevice, aiDevice.getDeviceAlgorithmList());
        return AjaxResult.success("添加摄像头成功");
    }

    @PutMapping
    public AjaxResult edit(@RequestBody AiDevice aiDevice) throws Exception {
        log.debug("更新摄像头");
        log.debug(JSON.toJSONString(aiDevice));
        iDeviceService.updateDevice(aiDevice, aiDevice.getDeviceAlgorithmList());
        return AjaxResult.success("更新摄像头成功");
    }

    // 分页获取设备
    @GetMapping("/list")
    public TableDataInfo list(AiDevice aiDevice) {
        log.debug("获取设备列表参数：{}", JSON.toJSONString(aiDevice));
        startPage();
        List<AiDevice> list = iDeviceService.getDeviceList(aiDevice);
        log.debug("获取设备列表：{}", JSON.toJSONString(list));
        return getDataTable(list);
    }

    // 获取所有设备
    @GetMapping("/list-all")
    public AjaxResult listAll(AiDevice aiDevice) {
        List<AiDevice> list = iDeviceService.getDeviceList(aiDevice);
        return AjaxResult.success(list);
    }

    @DeleteMapping("/{deviceIds}")
    public AjaxResult deleteDevice(@PathVariable Long[] deviceIds) {
        log.debug("删除设备{}", JSON.toJSONString(deviceIds));
        iDeviceService.deleteDevice(deviceIds);
        return AjaxResult.success("删除摄像头成功");
    }

    @PostMapping("/get-device-algorithm-list-by-device-id")
    public AjaxResult getDeviceAlgorithmListByDeviceId(@RequestBody GetDeviceAlgorithmListByDeviceIdRequest getDeviceAlgorithmListByDeviceIdRequest) {
        log.debug("根据设备id获取算法列表{}", getDeviceAlgorithmListByDeviceIdRequest.getDeviceId());
        Long deviceId = getDeviceAlgorithmListByDeviceIdRequest.getDeviceId();
        List<AiDeviceAlgorithm> aiDeviceAlgorithmList = iDeviceService.getDeviceAlgorithmListByDeviceId(deviceId);
        return AjaxResult.success(aiDeviceAlgorithmList);
    }

    @PostMapping("/update-check-status")
    public AjaxResult updateCheckStatus(@RequestBody AiDevice aiDevice) {
        log.debug("更新设备分屏状态{}", JSON.toJSONString(aiDevice));
        iDeviceService.updateCheckStatus(aiDevice);
        return AjaxResult.success("更新设备分屏状态成功");
    }

    @PostMapping("/get-checked-device-list")
    public AjaxResult getCheckedDeviceList() {
        List<AiDevice> aiDeviceList = iDeviceService.getCheckedDeviceList();
        log.debug("分屏的设备列表{}", JSON.toJSONString(aiDeviceList));
        return AjaxResult.success(aiDeviceList);
    }

    @PostMapping("/add-stream-proxy")
    public AjaxResult addStreamProxy(@RequestBody AiDevice aiDevice) {
        log.debug("添加流代理：{}", JSON.toJSONString(aiDevice));
        iDeviceService.addStreamProxy(aiDevice);
        return AjaxResult.success("添加流代理成功");
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('lab:device:export')")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiDevice aiDevice)
    {
        List<AiDevice> list = iDeviceService.getDeviceList(aiDevice);
        ExcelUtil<AiDevice> util = new ExcelUtil<>(AiDevice.class);
        util.exportExcel(response, list, "设备数据");
    }

    // 更新坐标
    @PutMapping("/update-coordinate")
    public AjaxResult updateCoordinate(@RequestBody AiDevice aiDevice) {
        log.debug("更新坐标：{}", JSON.toJSONString(aiDevice));
        return toAjax(iDeviceService.updateCoordinate(aiDevice));
    }
}
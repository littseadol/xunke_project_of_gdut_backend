package com.ruoyi.lab.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.lab.domain.AiFace;
import com.ruoyi.lab.service.IAiFaceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 人脸Controller
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@RestController
@RequestMapping("/lab/face")
public class AiFaceController extends BaseController
{
    @Autowired
    private IAiFaceService aiFaceService;

    /**
     * 查询人脸列表
     */
    @PreAuthorize("@ss.hasPermi('lab:face:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiFace aiFace)
    {
        startPage();
        List<AiFace> list = aiFaceService.selectAiFaceList(aiFace);
        return getDataTable(list);
    }

    /**
     * 导出人脸列表
     */
    @PreAuthorize("@ss.hasPermi('lab:face:export')")
    @Log(title = "人脸", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiFace aiFace)
    {
        List<AiFace> list = aiFaceService.selectAiFaceList(aiFace);
        ExcelUtil<AiFace> util = new ExcelUtil<AiFace>(AiFace.class);
        util.exportExcel(response, list, "人脸数据");
    }

    /**
     * 获取人脸详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:face:query')")
    @GetMapping(value = "/{faceId}")
    public AjaxResult getInfo(@PathVariable("faceId") Long faceId)
    {
        return success(aiFaceService.selectAiFaceByFaceId(faceId));
    }

    /**
     * 新增人脸
     */
    @PreAuthorize("@ss.hasPermi('lab:face:add')")
    @Log(title = "人脸", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiFace aiFace)
    {
        return toAjax(aiFaceService.insertAiFace(aiFace));
    }

    /**
     * 修改人脸
     */
    @PreAuthorize("@ss.hasPermi('lab:face:edit')")
    @Log(title = "人脸", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiFace aiFace)
    {
        return toAjax(aiFaceService.updateAiFace(aiFace));
    }

    /**
     * 删除人脸
     */
    @PreAuthorize("@ss.hasPermi('lab:face:remove')")
    @Log(title = "人脸", businessType = BusinessType.DELETE)
	@DeleteMapping("/{faceIds}")
    public AjaxResult remove(@PathVariable Long[] faceIds)
    {
        return toAjax(aiFaceService.deleteAiFaceByFaceIds(faceIds));
    }

    /**
     * 比对人脸
     */
    @PreAuthorize("@ss.hasPermi('lab:face:compare')")
    @Log(title = "人脸", businessType = BusinessType.OTHER)
    @GetMapping("/compare")
    public AjaxResult compare(@RequestParam String facePicture1, @RequestParam String facePicture2) {
        Float result = aiFaceService.compareFace(facePicture1, facePicture2);
        if (result != null) {
            return AjaxResult.success(result);
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * 检索人脸
     */
    @PreAuthorize("@ss.hasPermi('lab:face:search')")
    @Log(title = "人脸", businessType = BusinessType.OTHER)
    @GetMapping("/search")
    public AjaxResult search(@RequestParam String facePicture) {
        Map<String, Object> data = aiFaceService.searchFace(facePicture);
        return AjaxResult.success(data);
    }

    /**
     * 检测表情
     */
    @PreAuthorize("@ss.hasPermi('lab:face:detectExpression')")
    @Log(title = "人脸", businessType = BusinessType.OTHER)
    @GetMapping("/detect-expression")
    public AjaxResult detectExpression(@RequestParam String facePicture) {
        Map<String, Object> data = aiFaceService.detectExpression(facePicture);
        return AjaxResult.success(data);
    }

    /**
     * 检测属性
     */
    @PreAuthorize("@ss.hasPermi('lab:face:detectAttribute')")
    @Log(title = "人脸", businessType = BusinessType.OTHER)
    @GetMapping("/detect-attribute")
    public AjaxResult detectAttribute(@RequestParam String facePicture) {
        Map<String, Object> data = aiFaceService.detectFaceAttribute(facePicture);
        return AjaxResult.success(data);
    }
}
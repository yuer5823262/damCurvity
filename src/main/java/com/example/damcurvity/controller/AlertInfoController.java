package com.example.damcurvity.controller;


import com.example.damcurvity.common.ApiRestResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.entity.AlertInfo;
import com.example.damcurvity.que.InfoAlertQue;
import io.swagger.annotations.Api;
import com.example.damcurvity.util.JwtUtils;
import com.example.damcurvity.util.TimeUtils;
import com.example.damcurvity.service.AlertInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.damcurvity.que.BaseQue;
import com.example.damcurvity.util.ExcelUtil;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (AlertInfo)表控制层
 *
 * @author makejava
 * @since 2023-08-07 16:19:00
 */
@Api("alertInfo")
@RestController
@RequestMapping("alertInfo")
public class AlertInfoController {
    /**
     * 服务对象
     */
    @Resource
    private AlertInfoService alertInfoService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param alertInfo 查询实体
     * @return 所有数据
     */
    @GetMapping("select")
    public ApiRestResponse selectAll(InfoAlertQue infoAlertQue) {
            Page<AlertInfo> page = new Page<>(infoAlertQue.getPageNum(),infoAlertQue.getPageSize());
            return ApiRestResponse.success(this.alertInfoService.selectListJoin(page,infoAlertQue));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getOneDdata")
    public ApiRestResponse selectOne(Integer id) {
            return ApiRestResponse.success(this.alertInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param alertInfo 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ApiRestResponse insert(@RequestBody AlertInfo alertInfo) {
           return ApiRestResponse.success(this.alertInfoService.save(alertInfo));
    }


    @PostMapping("addBatch")
    public ApiRestResponse insertBatch(@RequestBody List<AlertInfo>  alertInfoList, HttpServletRequest request) {
        return ApiRestResponse.success(this.alertInfoService.saveBatch(alertInfoList));
    }


    /**
     * 修改数据
     *
     * @param alertInfo 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public ApiRestResponse update(@RequestBody AlertInfo alertInfo) {

            return ApiRestResponse.success(this.alertInfoService.updateById(alertInfo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @GetMapping("deleteByIds")
    public ApiRestResponse delete(@RequestParam("idList") List<Long> idList) {
            return ApiRestResponse.success(this.alertInfoService.removeByIds(idList));
    }










    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(HttpServletResponse response) {
        ExcelUtil.exportExcel(this.alertInfoService.list(),"AlertInfo","AlertInfo",AlertInfo.class,"AlertInfo",response);
    }

}


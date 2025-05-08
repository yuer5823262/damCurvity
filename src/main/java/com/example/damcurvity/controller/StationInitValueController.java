package com.example.damcurvity.controller;


import com.example.damcurvity.common.ApiRestResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.entity.StationInitValue;
import io.swagger.annotations.Api;
import com.example.damcurvity.util.JwtUtils;
import com.example.damcurvity.util.TimeUtils;
import com.example.damcurvity.service.StationInitValueService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.damcurvity.que.BaseQue;
import com.example.damcurvity.util.ExcelUtil;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (StationInitValue)表控制层
 *
 * @author makejava
 * @since 2023-08-31 22:37:40
 */
@Api("stationInitValue")
@RestController
@RequestMapping("stationInitValue")
public class StationInitValueController {
    /**
     * 服务对象
     */
    @Resource
    private StationInitValueService stationInitValueService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param stationInitValue 查询实体
     * @return 所有数据
     */
    @GetMapping("select")
    public ApiRestResponse selectAll(BaseQue baseQue, StationInitValue stationInitValue) {
            Page<StationInitValue> page = new Page<>(baseQue.getPageNum(),baseQue.getPageSize());
            return ApiRestResponse.success(this.stationInitValueService.page(page, new QueryWrapper<>(stationInitValue)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getOneDdata")
    public ApiRestResponse selectOne(Integer id) {
            return ApiRestResponse.success(this.stationInitValueService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param stationInitValue 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ApiRestResponse insert(@RequestBody StationInitValue stationInitValue) {

           return ApiRestResponse.success(this.stationInitValueService.addStationInitValue(stationInitValue));
    }


    @PostMapping("addByStationIdAndTime")

    public ApiRestResponse addByStationIdAndTime(@RequestBody Integer stationId, @RequestBody String time) {
        this.stationInitValueService.addByStationIdAndTime(stationId,time);
        return ApiRestResponse.success();
    }




    @PostMapping("addBatch")
    public ApiRestResponse insertBatch(@RequestBody List<StationInitValue>  stationInitValueList) {
        return ApiRestResponse.success(this.stationInitValueService.saveBatch(stationInitValueList));
    }


    /**
     * 修改数据
     *
     * @param stationInitValue 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public ApiRestResponse update(@RequestBody StationInitValue stationInitValue) {
            return ApiRestResponse.success(this.stationInitValueService.updateById(stationInitValue));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @GetMapping("deleteByIds")
    public ApiRestResponse delete(@RequestParam("idList") List<Long> idList) {
            return ApiRestResponse.success(this.stationInitValueService.removeByIds(idList));
    }



    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(HttpServletResponse response) {
        ExcelUtil.exportExcel(this.stationInitValueService.list(),"StationInitValue","StationInitValue",StationInitValue.class,"StationInitValue",response);
    }

}


package com.example.damcurvity.controller;


import com.example.damcurvity.common.ApiRestResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.entity.DataPush;
import io.swagger.annotations.Api;
import com.example.damcurvity.util.JwtUtils;
import com.example.damcurvity.util.TimeUtils;
import com.example.damcurvity.service.DataPushService;
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
 * (DataPush)表控制层
 *
 * @author makejava
 * @since 2023-08-27 09:19:04
 */
@Api("dataPush")
@RestController
@RequestMapping("dataPush")
public class DataPushController {
    /**
     * 服务对象
     */
    @Resource
    private DataPushService dataPushService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param dataPush 查询实体
     * @return 所有数据
     */
    @GetMapping("select")
    public ApiRestResponse selectAll(BaseQue baseQue, DataPush dataPush) {
            Page<DataPush> page = new Page<>(baseQue.getPageNum(),baseQue.getPageSize());
            return ApiRestResponse.success(this.dataPushService.page(page, new QueryWrapper<>(dataPush)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getOneDdata")
    public ApiRestResponse selectOne(Integer id) {
            return ApiRestResponse.success(this.dataPushService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param dataPush 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ApiRestResponse insert(@RequestBody DataPush dataPush, HttpServletRequest request) {
           String token = request.getHeader("token");
           String username =  JwtUtils.GetUserName(token);
           dataPush.setOperator(username);
           dataPush.setCreateTime(TimeUtils.getNowTime());
           return ApiRestResponse.success(this.dataPushService.save(dataPush));
    }


    @PostMapping("addBatch")
    public ApiRestResponse insertBatch(@RequestBody List<DataPush>  dataPushList, HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        dataPushList.forEach(t-> t.setOperator(username));
        dataPushList.forEach(t-> t.setCreateTime(TimeUtils.getNowTime()));
        return ApiRestResponse.success(this.dataPushService.saveBatch(dataPushList));
    }


    /**
     * 修改数据
     *
     * @param dataPush 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public ApiRestResponse update(@RequestBody DataPush dataPush) {
            return ApiRestResponse.success(this.dataPushService.updateById(dataPush));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @GetMapping("deleteByIds")
    public ApiRestResponse delete(@RequestParam("idList") List<Long> idList) {
            return ApiRestResponse.success(this.dataPushService.removeByIds(idList));
    }










    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(HttpServletResponse response) {
        ExcelUtil.exportExcel(this.dataPushService.list(),"DataPush","DataPush",DataPush.class,"DataPush",response);
    }

}


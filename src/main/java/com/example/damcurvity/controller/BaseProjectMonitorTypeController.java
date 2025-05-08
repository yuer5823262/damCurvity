package com.example.damcurvity.controller;


import com.example.damcurvity.common.ApiRestResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.entity.BaseProjectMonitorType;
import io.swagger.annotations.Api;
import com.example.damcurvity.service.BaseProjectMonitorTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import com.example.damcurvity.que.BaseQue;
import com.example.damcurvity.util.ExcelUtil;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (BaseProjectMonitorType)表控制层
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
@Api("baseProjectMonitorType")
@RestController
@RequestMapping("baseProjectMonitorType")
public class BaseProjectMonitorTypeController {
    /**
     * 服务对象
     */
    @Resource
    private BaseProjectMonitorTypeService baseProjectMonitorTypeService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param baseProjectMonitorType 查询实体
     * @return 所有数据
     */
    @GetMapping("select")
    public ApiRestResponse selectAll(BaseQue baseQue, BaseProjectMonitorType baseProjectMonitorType) {
            Page<BaseProjectMonitorType> page = new Page<>(baseQue.getPageNum(),baseQue.getPageSize());
            return ApiRestResponse.success(this.baseProjectMonitorTypeService.page(page, new QueryWrapper<>(baseProjectMonitorType)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getOneDdata")
    public ApiRestResponse selectOne(Integer id) {
            return ApiRestResponse.success(this.baseProjectMonitorTypeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param baseProjectMonitorType 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ApiRestResponse insert(@RequestBody BaseProjectMonitorType baseProjectMonitorType) {
            return ApiRestResponse.success(this.baseProjectMonitorTypeService.save(baseProjectMonitorType));
    }


    @PostMapping("addBatch")
    public ApiRestResponse insertBatch(@RequestBody List<BaseProjectMonitorType>  baseProjectMonitorTypeList) {
        return ApiRestResponse.success(this.baseProjectMonitorTypeService.saveBatch(baseProjectMonitorTypeList));
    }


    /**
     * 修改数据
     *
     * @param baseProjectMonitorType 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public ApiRestResponse update(@RequestBody BaseProjectMonitorType baseProjectMonitorType) {
            return ApiRestResponse.success(this.baseProjectMonitorTypeService.updateById(baseProjectMonitorType));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @GetMapping("deleteByIds")
    public ApiRestResponse delete(@RequestParam("idList") List<Long> idList) {
            return ApiRestResponse.success(this.baseProjectMonitorTypeService.removeByIds(idList));
    }
    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(HttpServletResponse response) {
        ExcelUtil.exportExcel(this.baseProjectMonitorTypeService.list(),"BaseProjectMonitorType","BaseProjectMonitorType",BaseProjectMonitorType.class,"BaseProjectMonitorType",response);
    }
}


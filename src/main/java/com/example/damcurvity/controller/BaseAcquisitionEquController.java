package com.example.damcurvity.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.damcurvity.common.ApiRestResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.entity.BaseAcquisitionEqu;
import com.example.damcurvity.exception.DamPourException;
import com.example.damcurvity.exception.DampouringExceptionEnum;
import io.swagger.annotations.Api;
import com.example.damcurvity.util.JwtUtils;
import com.example.damcurvity.util.TimeUtils;
import com.example.damcurvity.service.BaseAcquisitionEquService;
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
 * (BaseAcquisitionEqu)表控制层
 *
 * @author makejava
 * @since 2023-08-25 16:01:50
 */
@Api("baseAcquisitionEqu")
@RestController
@RequestMapping("baseAcquisitionEqu")
public class BaseAcquisitionEquController {
    /**
     * 服务对象
     */
    @Resource
    private BaseAcquisitionEquService baseAcquisitionEquService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param baseAcquisitionEqu 查询实体
     * @return 所有数据
     */
    @GetMapping("select")
    public ApiRestResponse selectAll(BaseQue baseQue, BaseAcquisitionEqu baseAcquisitionEqu) {
            Page<BaseAcquisitionEqu> page = new Page<>(baseQue.getPageNum(),baseQue.getPageSize());
            return ApiRestResponse.success(this.baseAcquisitionEquService.page(page, new QueryWrapper<>(baseAcquisitionEqu)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getOneDdata")
    public ApiRestResponse selectOne(Integer id) {
            return ApiRestResponse.success(this.baseAcquisitionEquService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param baseAcquisitionEqu 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ApiRestResponse insert(@RequestBody BaseAcquisitionEqu baseAcquisitionEqu, HttpServletRequest request) {
        QueryWrapper<BaseAcquisitionEqu> baseAcquisitionEquQueryWrapper = new QueryWrapper<>();
        baseAcquisitionEquQueryWrapper.eq("name",baseAcquisitionEqu.getName());
        BaseAcquisitionEqu one = baseAcquisitionEquService.getOne(baseAcquisitionEquQueryWrapper);
        if(one != null)
            throw new DamPourException(DampouringExceptionEnum.NAME_EXISTED);
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        baseAcquisitionEqu.setOperator(username);
        baseAcquisitionEqu.setCreateTime(TimeUtils.getNowTime());
        return ApiRestResponse.success(this.baseAcquisitionEquService.save(baseAcquisitionEqu));
    }


    @PostMapping("addBatch")
    public ApiRestResponse insertBatch(@RequestBody List<BaseAcquisitionEqu>  baseAcquisitionEquList, HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        baseAcquisitionEquList.forEach(t-> t.setOperator(username));
        baseAcquisitionEquList.forEach(t-> t.setCreateTime(TimeUtils.getNowTime()));
        return ApiRestResponse.success(this.baseAcquisitionEquService.saveBatch(baseAcquisitionEquList));
    }


    /**
     * 修改数据
     *
     * @param baseAcquisitionEqu 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public ApiRestResponse update(@RequestBody BaseAcquisitionEqu baseAcquisitionEqu) {
            return ApiRestResponse.success(this.baseAcquisitionEquService.updateById(baseAcquisitionEqu));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @GetMapping("deleteByIds")
    public ApiRestResponse delete(@RequestParam("idList") List<Long> idList) {
            return ApiRestResponse.success(this.baseAcquisitionEquService.removeByIds(idList));
    }










    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(HttpServletResponse response) {
        ExcelUtil.exportExcel(this.baseAcquisitionEquService.list(),"BaseAcquisitionEqu","BaseAcquisitionEqu",BaseAcquisitionEqu.class,"BaseAcquisitionEqu",response);
    }

}


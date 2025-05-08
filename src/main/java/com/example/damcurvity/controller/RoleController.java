package com.example.damcurvity.controller;


import com.example.damcurvity.common.ApiRestResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.entity.Role;
import io.swagger.annotations.Api;
import com.example.damcurvity.util.JwtUtils;
import com.example.damcurvity.util.TimeUtils;
import com.example.damcurvity.service.RoleService;
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
 * (Role)表控制层
 *
 * @author makejava
 * @since 2023-09-24 16:14:01
 */
@Api("role")
@RestController
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param role 查询实体
     * @return 所有数据
     */
    @GetMapping("select")
    public ApiRestResponse selectAll(BaseQue baseQue, Role role) {
            Page<Role> page = new Page<>(baseQue.getPageNum(),baseQue.getPageSize());
            return ApiRestResponse.success(this.roleService.page(page, new QueryWrapper<>(role)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getOneDdata")
    public ApiRestResponse selectOne(Integer id) {
            return ApiRestResponse.success(this.roleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param role 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ApiRestResponse insert(@RequestBody Role role, HttpServletRequest request) {
           String token = request.getHeader("token");
           String username =  JwtUtils.GetUserName(token);
           role.setOperator(username);
           role.setCreateTime(TimeUtils.getNowTime());
           return ApiRestResponse.success(this.roleService.save(role));
    }


    @PostMapping("addBatch")
    public ApiRestResponse insertBatch(@RequestBody List<Role>  roleList, HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        roleList.forEach(t-> t.setOperator(username));
        roleList.forEach(t-> t.setCreateTime(TimeUtils.getNowTime()));
        return ApiRestResponse.success(this.roleService.saveBatch(roleList));
    }


    /**
     * 修改数据
     *
     * @param role 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public ApiRestResponse update(@RequestBody Role role) {
            return ApiRestResponse.success(this.roleService.updateById(role));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @GetMapping("deleteByIds")
    public ApiRestResponse delete(@RequestParam("idList") List<Long> idList) {
            return ApiRestResponse.success(this.roleService.removeByIds(idList));
    }










    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(HttpServletResponse response) {
        ExcelUtil.exportExcel(this.roleService.list(),"Role","Role",Role.class,"Role",response);
    }

}


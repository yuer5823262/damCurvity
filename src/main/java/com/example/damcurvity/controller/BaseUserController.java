package com.example.damcurvity.controller;


import com.example.damcurvity.common.ApiRestResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.common.Constant;
import com.example.damcurvity.entity.BaseUser;
import com.example.damcurvity.req.UserLoginReq;
import com.example.damcurvity.util.JwtUtils;
import com.example.damcurvity.util.MD5Utils;
import com.example.damcurvity.vo.UserVO;
import io.swagger.annotations.Api;
import com.example.damcurvity.service.BaseUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.damcurvity.que.BaseQue;
import com.example.damcurvity.util.ExcelUtil;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * (BaseUser)表控制层
 *
 * @author makejava
 * @since 2023-08-05 15:08:19
 */
@Api("baseUser")
@RestController
@RequestMapping("baseUser")
public class BaseUserController {
    /**
     * 服务对象
     */
    @Resource
    private BaseUserService baseUserService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param baseUser 查询实体
     * @return 所有数据
     */

//    @SecurityParameter(inDecode = true,outEncode = true)
    @PostMapping("/login")
    @ResponseBody
    public ApiRestResponse login(@RequestBody @Valid UserLoginReq userLoginReq, HttpServletRequest request){

        String ip = request.getRemoteAddr();
        BaseUser user = baseUserService.login(userLoginReq.getUserName(),userLoginReq.getPwd(),ip);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        userVO.setToken(JwtUtils.getToken(user));
        return ApiRestResponse.success(userVO);
    }


    @GetMapping("select")
    public ApiRestResponse selectAll(BaseQue baseQue, BaseUser baseUser) {
            Page<BaseUser> page = new Page<>(baseQue.getPageNum(),baseQue.getPageSize());
            Page<BaseUser> baseUserPage = this.baseUserService.page(page, new QueryWrapper<>(baseUser));
            return ApiRestResponse.success(baseUserPage);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getOneDdata")
    public ApiRestResponse selectOne(Integer id) {
            return ApiRestResponse.success(this.baseUserService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param baseUser 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ApiRestResponse insert(@RequestBody BaseUser baseUser) {
        try {
            baseUser.setPwd(MD5Utils.getMD5Str(baseUser.getPwd()));
        } catch (NoSuchAlgorithmException e) {
            Constant.logger.error("MD5加密错误",e);
        }
        return ApiRestResponse.success(this.baseUserService.save(baseUser));
    }


    @PostMapping("addBatch")
    public ApiRestResponse insertBatch(@RequestBody List<BaseUser>  baseUserList) {

        baseUserList.forEach(t-> {
            try {
                t.setPwd(MD5Utils.getMD5Str(t.getPwd()));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        });
        return ApiRestResponse.success(this.baseUserService.saveBatch(baseUserList));
    }


    /**
     * 修改数据
     *
     * @param baseUser 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public ApiRestResponse update(@RequestBody BaseUser baseUser) {
        if(baseUser.getPwd()!=null)
        {
            try {
                baseUser.setPwd(MD5Utils.getMD5Str(baseUser.getPwd()));
            } catch (NoSuchAlgorithmException e) {
                Constant.logger.error("MD5加密错误",e);
            }
        }
        return ApiRestResponse.success(this.baseUserService.updateById(baseUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @GetMapping("deleteByIds")
    public ApiRestResponse delete(@RequestParam("idList") List<Long> idList) {
            return ApiRestResponse.success(this.baseUserService.removeByIds(idList));
    }
    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(HttpServletResponse response) {
        ExcelUtil.exportExcel(this.baseUserService.list(),"BaseUser","BaseUser",BaseUser.class,"BaseUser",response);
    }


    @GetMapping("logout")
    @ResponseBody
    public ApiRestResponse logout(HttpServletRequest request){
        String token = request.getHeader("token");
        String userName = JwtUtils.GetUserName(token);
        String ip = request.getRemoteAddr();
        baseUserService.logout(userName,ip);
        return ApiRestResponse.success();
    }

}


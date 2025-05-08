package com.example.damcurvity.controller;


import com.example.damcurvity.common.ApiRestResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.common.Constant;
import com.example.damcurvity.entity.BaseProject;
import com.example.damcurvity.util.JwtUtils;
import com.example.damcurvity.util.TimeUtils;
import io.swagger.annotations.Api;
import com.example.damcurvity.service.BaseProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.damcurvity.que.BaseQue;
import com.example.damcurvity.util.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * (BaseProject)表控制层
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
@Api("baseProject")
@RestController
@RequestMapping("baseProject")
public class BaseProjectController {
    /**
     * 服务对象
     */
    @Resource
    private BaseProjectService baseProjectService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param baseProject 查询实体
     * @return 所有数据
     */
    @GetMapping("select")
    public ApiRestResponse selectAll(BaseQue baseQue, BaseProject baseProject) {
            Page<BaseProject> page = new Page<>(baseQue.getPageNum(),baseQue.getPageSize());
            return ApiRestResponse.success(this.baseProjectService.page(page, new QueryWrapper<>(baseProject)));
    }




    @GetMapping("selectByIds")
    public ApiRestResponse selectAll(@RequestParam("ids") Integer[] ids) {

        return ApiRestResponse.success(this.baseProjectService.listByIds(Arrays.asList(ids)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getOneDdata")
    public ApiRestResponse selectOne(Integer id) {
        BaseProject baseProject=  this.baseProjectService.getById(id);
//        String imagePath = Constant.imgPath+baseProject.getImgName(); // 替换为你本地PNG图片的路径
//        String base64EncodedImage = null;
//        try {
//            // 从文件路径创建Path对象
//            byte[] imageData = Files.readAllBytes(Paths.get(imagePath));
//
//            // 将图片字节流转为Base64编码
//            base64EncodedImage = Base64.getEncoder().encodeToString(imageData);
//
//        } catch (IOException e) {
//            Constant.logger.error("图片转换base64错误",e);
//        }
//        baseProject.setImgName(base64EncodedImage);
        return ApiRestResponse.success(baseProject);
    }

    /**
     * 新增数据
     *
     * @param baseProject 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ApiRestResponse insert(@RequestBody BaseProject baseProject, HttpServletRequest request) {
//        if (!upload.isEmpty()) {
//            long timestamp = System.currentTimeMillis();
//            String newFilePath = Constant.imgPath+timestamp+".png";
//            try {
//                upload.transferTo(new File(newFilePath));//将传来的文件写入新建的文件
//                baseProject.setImgName(timestamp+".png");
//            } catch (IOException e) {
//                Constant.logger.error("图片上传失败",e);
//            }
//        }
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        baseProject.setOperator(username);
        baseProject.setCreateTime(TimeUtils.getNowTime());
        return ApiRestResponse.success(this.baseProjectService.save(baseProject));
    }


    @PostMapping("addBatch")
    public ApiRestResponse insertBatch(@RequestBody List<BaseProject>  baseProjectList, HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        baseProjectList.forEach(t-> t.setOperator(username));
        baseProjectList.forEach(t-> t.setCreateTime(TimeUtils.getNowTime()));
        return ApiRestResponse.success(this.baseProjectService.saveBatch(baseProjectList));
    }


    /**
     * 修改数据
     *
     * @param baseProject 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public ApiRestResponse update(@RequestBody BaseProject baseProject) {
            return ApiRestResponse.success(this.baseProjectService.updateById(baseProject));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @GetMapping("deleteByIds")
    public ApiRestResponse delete(@RequestParam("idList") List<Long> idList) {
            return ApiRestResponse.success(this.baseProjectService.removeByIds(idList));
    }
    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(HttpServletResponse response) {
        ExcelUtil.exportExcel(this.baseProjectService.list(),"BaseProject","BaseProject",BaseProject.class,"BaseProject",response);
    }
}


package com.example.damcurvity.common;

import com.example.damcurvity.dao.SystemConstantDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class Constant implements ApplicationRunner {
    @Resource
    SystemConstantDao systemConstantDao;
    public static final String SALT = "sad2asdAS%D&2#284.";
    public static Logger logger= LoggerFactory.getLogger("constant");
    public static String imgPath = System.getProperty("user.dir")+"\\img\\";
    public static String reportTempPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\temp.xlsx";
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        QueryWrapper<SystemConstant> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("type","采集串口号");
//        SystemConstant port = systemConstantDao.selectOne(queryWrapper);
//        if(port!=null)
//        {
//            String p = "com"+port.getVal();
//            if(SerialPortUtils.init(p,115200))
//            {
//                logger.info("已初始化端口:"+p);
//            }
//
//            else
//                logger.info("初始化端口失败:"+p);
//        }

    }
}

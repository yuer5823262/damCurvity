package com.example.damcurvity;

import com.example.damcurvity.util.DeviceUtils;
import com.example.damcurvity.util.SerialPortUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
public class DamCurvityApplication {
    public static void main(String[] args) {
        SpringApplication.run(DamCurvityApplication.class, args);

    }

}

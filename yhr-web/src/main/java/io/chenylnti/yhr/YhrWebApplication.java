package io.chenylnti.yhr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@MapperScan(basePackages = "io.chenylnti.yhr.*.mapper")
public class YhrWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YhrWebApplication.class, args);
    }

}

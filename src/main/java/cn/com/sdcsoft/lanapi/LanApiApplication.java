package cn.com.sdcsoft.lanapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LanApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LanApiApplication.class, args);
    }

}

package com.trkj.esexpaperservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EsExpaperServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsExpaperServiceApplication.class, args);
    }

}

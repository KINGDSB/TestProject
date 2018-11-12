package com.dsb.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.dsb.entity.ResultVO;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * Created by DSB 2018/11/8 12:58
 */
@FeignClient(name = "fileService", url = "http://dc-api.lexin580.com/file",configuration = FileClient.MultipartSupportConfig.class)
public interface FileClient {

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultVO upload(@RequestPart(value = "file") MultipartFile file);

    @Scope("prototype")
    @Primary
    @Configuration
    class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }

}
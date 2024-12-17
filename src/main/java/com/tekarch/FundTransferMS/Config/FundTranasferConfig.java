package com.tekarch.FundTransferMS.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FundTranasferConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

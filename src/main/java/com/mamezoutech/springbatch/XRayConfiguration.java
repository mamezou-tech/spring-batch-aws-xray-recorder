package com.mamezoutech.springbatch;

import com.amazonaws.xray.AWSXRayRecorder;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XRayConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AWSXRayRecorder awsxrayRecorder() {
        return AWSXRayRecorderBuilder.standard().build();
    }

}

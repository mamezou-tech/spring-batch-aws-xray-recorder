package com.mamezoutech.springbatch;

import com.amazonaws.xray.AWSXRayRecorder;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XRayConfiguration {

    @Bean
    public AWSXRayRecorder awsxrayRecorder() {
        return AWSXRayRecorderBuilder.standard().build();
    }

}

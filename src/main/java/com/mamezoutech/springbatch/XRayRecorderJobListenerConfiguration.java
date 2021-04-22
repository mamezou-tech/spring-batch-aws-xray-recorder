package com.mamezoutech.springbatch;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorder;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XRayRecorderJobListenerConfiguration {

    @Bean
    public XRayRecorderJobListener xrayRecorderJobListener(@Autowired AWSXRayRecorder recorder) {
        return new XRayRecorderJobListener(recorder);
    }
}

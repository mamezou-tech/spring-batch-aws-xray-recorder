package com.mamezoutech.test.batch;

import com.mamezoutech.springbatch.XRayRecorderJobListenerConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@SpringBatchTest
@ContextConfiguration(classes = {AWSXRayRecorderConfiguration.class, XRayRecorderJobListenerConfiguration.class, TestConfiguration.class})
public class BatchTest {

    @Autowired
    private JobLauncherTestUtils launcher;

    @Autowired
    private Job job;

    @Test
    public void jobTest() throws Exception {
        JobParameters jobParameters = launcher.getUniqueJobParameters();
        launcher.launchJob(jobParameters);

        Thread.sleep(10000L);
    }

}

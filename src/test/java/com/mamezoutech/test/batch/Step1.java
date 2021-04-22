package com.mamezoutech.test.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class Step1 implements Tasklet {

    private static Logger logger = LoggerFactory.getLogger(Step1.class);

    public Step create(StepBuilderFactory steps) {
        logger.info("create");
        return steps.get("step1").tasklet(this).build();
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        logger.info("process");
        return RepeatStatus.FINISHED;
    }
}

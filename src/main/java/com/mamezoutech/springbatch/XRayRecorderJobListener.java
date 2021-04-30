package com.mamezoutech.springbatch;

import com.amazonaws.xray.AWSXRayRecorder;
import com.amazonaws.xray.entities.TraceHeader;
import com.amazonaws.xray.entities.TraceID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import java.util.Optional;

public class XRayRecorderJobListener implements JobExecutionListener {

    private static Logger logger = LoggerFactory.getLogger(XRayRecorderJobListener.class);

    private AWSXRayRecorder recorder;

    public XRayRecorderJobListener(AWSXRayRecorder recorder) {
        this.recorder = recorder;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        String name = jobExecution.getJobParameters().getString("Host");
        if (name == null || name.equals("")) {
            name = "localhost";
        }
        String traceId = jobExecution.getJobParameters().getString("X-Amzn-Trace-Id");
        Optional<TraceHeader> maybeTraceHeader = traceId == null ? Optional.empty() : Optional.of(TraceHeader.fromString(traceId));
        if (logger.isDebugEnabled() && maybeTraceHeader.isPresent()) {
            logger.debug("Incoming trace header received: " + ((TraceHeader) maybeTraceHeader.get()).toString());
        }

        TraceID rootTraceId = maybeTraceHeader.isPresent() ? maybeTraceHeader.get().getRootTraceId() : new TraceID();
        String parentId = maybeTraceHeader.isPresent() ? maybeTraceHeader.get().getParentId() : null;
        recorder.beginSegment(name, rootTraceId, parentId);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        recorder.endSegment();
    }
}

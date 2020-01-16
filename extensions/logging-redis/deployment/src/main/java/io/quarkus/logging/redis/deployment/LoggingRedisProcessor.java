package io.quarkus.logging.redis.deployment;

import java.lang.reflect.InvocationTargetException;

import org.jboss.logmanager.EmbeddedConfigurator;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.logging.redis.runtime.LoggingRedisConfig;
import io.quarkus.logging.redis.runtime.LoggingRedisRecorder;
import io.quarkus.runtime.annotations.Recorder;

@Recorder
public final class LoggingRedisProcessor implements EmbeddedConfigurator {

    @Record(ExecutionTime.RUNTIME_INIT)
    @BuildStep
    public void loggingRedisProcessorBuildStep(LoggingRedisRecorder recorder, LoggingRedisConfig config)
            throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        recorder.initializeLogging(config);
    }

}

package io.quarkus.logging.redis.runtime;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Handler;

import org.jboss.logmanager.EmbeddedConfigurator;

import io.quarkus.runtime.annotations.Recorder;
import io.quarkus.runtime.logging.InitialConfigurator;

@Recorder
public class LoggingRedisRecorder implements EmbeddedConfigurator {

    public LoggingRedisRecorder() {
    }

    public void initializeLogging(LoggingRedisConfig config)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        if (config.enable) {
            ArrayList<Handler> handlers = new ArrayList<>(1);
            handlers.add(new LoggingRedisHandler(config));
            InitialConfigurator.DELAYED_HANDLER.setHandlers(handlers.toArray(EmbeddedConfigurator.NO_HANDLERS));
        }
    }

}

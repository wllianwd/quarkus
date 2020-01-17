package io.quarkus.logging.redis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.logging.Handler;

import org.jboss.logmanager.handlers.DelayedHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.logging.redis.runtime.LoggingRedisHandler;
import io.quarkus.runtime.logging.InitialConfigurator;
import io.quarkus.test.QuarkusUnitTest;

public class LoggingRedisDefaultConfigTest {

    @RegisterExtension
    static final QuarkusUnitTest config = new QuarkusUnitTest()
            .withConfigurationResource("application-logging-redis-default.properties");

    @Test
    public void loggingRedisDefaultConfigTest() {
        DelayedHandler delayedHandler = InitialConfigurator.DELAYED_HANDLER;

        Handler handler = Arrays.stream(delayedHandler.getHandlers())
                .filter(h -> (h instanceof LoggingRedisHandler))
                .findFirst().orElse(null);

        assertThat(handler).isNotNull();
    }

}

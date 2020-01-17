package io.quarkus.logging.redis.runtime;

import java.util.logging.Level;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import redis.clients.jedis.Protocol;

/**
 * The Logstash logging redis configuration.
 */
@ConfigRoot(phase = ConfigPhase.RUN_TIME, name = "log.redis")
public class LoggingRedisConfig {

    private static final String UNSET = "unset";

    /**
     * Enable/disable the logging redis extension, default true
     */
    @ConfigItem(defaultValue = "true")
    public Boolean enable;

    /**
     * Event [source] value, default unset
     */
    @ConfigItem(defaultValue = UNSET)
    public String source;

    /**
     * Event [path] value, default unset
     */
    @ConfigItem(defaultValue = UNSET)
    public String sourcePath;

    /**
     * Event [type] value, default unset
     */
    @ConfigItem(defaultValue = UNSET)
    public String type;

    /**
     * Comma-separated strings of [tags], default unset
     */
    @ConfigItem(defaultValue = UNSET)
    public String tags;

    /**
     * Redis Key to append the logs to, default unset
     */
    @ConfigItem(defaultValue = UNSET)
    public String key;

    /**
     * Redis Server Host, default is localhost
     */
    @ConfigItem(defaultValue = Protocol.DEFAULT_HOST)
    public String host;

    /**
     * Redis Server Port, default is 6379
     */
    @ConfigItem(defaultValue = Protocol.DEFAULT_PORT + "")
    public Integer port;

    /**
     * Redis connection timeout, default is 2000
     */
    @ConfigItem(defaultValue = Protocol.DEFAULT_TIMEOUT + "")
    public Integer timeout;

    /**
     * Redis connection password, default is null
     */
    @ConfigItem(defaultValue = UNSET)
    public String password;

    /**
     * Redis database number, default is 0
     */
    @ConfigItem(defaultValue = Protocol.DEFAULT_DATABASE + "")
    public Integer database;

    /**
     * Logger min level, default is INFO
     */
    @ConfigItem(defaultValue = "DEBUG")
    public Level minLevel;

    /**
     * Appender implementation of LoggingRedisAppender, default is JsonLoggingRedisAppender
     */
    @ConfigItem(defaultValue = "io.quarkus.logging.redis.runtime.appender.JsonLoggingRedisAppender")
    public String appender;

}

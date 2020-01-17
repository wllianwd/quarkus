package io.quarkus.logging.redis.runtime;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.jboss.logmanager.ExtLogRecord;
import org.jboss.logmanager.handlers.WriterHandler;

import io.quarkus.logging.redis.runtime.appender.LoggingRedisAppender;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class LoggingRedisHandler extends WriterHandler {

    private final String key;

    private final Level minLevel;

    private final JedisPool pool;

    private final LoggingRedisAppender appender;

    public LoggingRedisHandler(LoggingRedisConfig config)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException,
            ClassNotFoundException {
        this.key = config.key;
        this.minLevel = config.minLevel;
        this.appender = (LoggingRedisAppender) Class.forName(config.appender).getDeclaredConstructor(LoggingRedisConfig.class)
                .newInstance(config);
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setTestOnBorrow(true);
        pool = new JedisPool(poolConfig, config.host, config.port, config.timeout, config.password, config.database);
    }

    @Override
    protected void doPublish(ExtLogRecord record) {
        if (record.getLevel().intValue() >= minLevel.intValue()) {
            try (Jedis client = pool.getResource()) {
                String json = appender.getFormattedLog(record);
                client.rpush(key, json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

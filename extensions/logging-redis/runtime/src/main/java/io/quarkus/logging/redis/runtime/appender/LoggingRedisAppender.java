package io.quarkus.logging.redis.runtime.appender;

import org.jboss.logmanager.ExtLogRecord;

public interface LoggingRedisAppender {

    String getFormattedLog(ExtLogRecord record);

}

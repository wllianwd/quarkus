package io.quarkus.logging.redis.runtime.appender;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.jboss.logmanager.ExtLogRecord;

import com.google.gson.Gson;

import io.quarkus.logging.redis.runtime.LoggingRedisConfig;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class JsonLoggingRedisAppender implements LoggingRedisAppender {

    private final Gson gson = new Gson();

    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ");

    private final String source;

    private final String sourcePath;

    private final String tags;

    private final String type;

    public JsonLoggingRedisAppender(LoggingRedisConfig config) {
        this.source = config.source;
        this.sourcePath = config.sourcePath;
        this.tags = config.tags;
        this.type = config.type;
    }

    public String getFormattedLog(ExtLogRecord record) {
        JsonRecord jsonEvent = new JsonRecord();
        jsonEvent.source = source;
        jsonEvent.source_host = record.getHostName();
        jsonEvent.source_path = sourcePath;
        jsonEvent.type = type;
        jsonEvent.tags = Collections.singletonList(tags);
        jsonEvent.message = String.format(record.getMessage(), record.getParameters());
        jsonEvent.timestamp = this.df.format(new Date(record.getMillis()));
        JsonRecord.Fields fields = new JsonRecord.Fields();
        fields.level = record.getLevel().toString();
        fields.level_value = record.getLevel().intValue();
        fields.logger = record.getLoggerName();
        fields.thread = record.getThreadName();
        if (record.getThrown() != null) {
            fields.throwable = getStackTrace(record.getThrown());
        }
        JsonRecord.Location location = new JsonRecord.Location();
        location.clazz = record.getSourceClassName();
        location.method = record.getSourceMethodName();
        location.file = record.getSourceFileName();
        location.line = record.getSourceLineNumber();
        location.full_name = location.method + " @ " + location.file + ":" + location.line;
        fields.location = location;
        Map<String, String> propertyMap = record.getMdcCopy();
        if (propertyMap.size() != 0) {
            fields.properties = propertyMap;
        }
        jsonEvent.fields = fields;
        return this.gson.toJson(jsonEvent);
    }

    private String getStackTrace(final Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }

}

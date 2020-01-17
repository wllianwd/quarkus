package io.quarkus.logging.redis.runtime.appender;

import java.util.Map;

public class JsonRecordFields {

    public String logger;

    public String level;

    public Integer level_value;

    public String thread;

    public String throwable;

    public JsonRecordLocation location;

    public Map<String, String> properties;

    public JsonRecordFields() {
    }

}

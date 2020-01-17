package io.quarkus.logging.redis.runtime.appender;

import javax.json.bind.annotation.JsonbProperty;

public class JsonRecordLocation {

    @JsonbProperty("class")
    public String clazz;

    public String method;

    public String file;

    public Integer line;

    public String full_name;

    public JsonRecordLocation() {
    }

}

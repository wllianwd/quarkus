package io.quarkus.logging.redis.runtime.appender;

import java.util.List;

import javax.json.bind.annotation.JsonbProperty;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class JsonRecord {

    @JsonbProperty("@source")
    public String source;

    @JsonbProperty("@source_host")
    public String source_host;

    @JsonbProperty("@source_path")
    public String source_path;

    @JsonbProperty("@type")
    public String type;

    @JsonbProperty("@tags")
    public List<String> tags;

    @JsonbProperty("@message")
    public String message;

    @JsonbProperty("@timestamp")
    public String timestamp;

    @JsonbProperty("@fields")
    public JsonRecordFields fields;

    public JsonRecord() {
    }

}

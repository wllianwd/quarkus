package io.quarkus.logging.redis.runtime.appender;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class JsonRecord {

    @SerializedName("@source")
    public String source;

    @SerializedName("@source_host")
    public String source_host;

    @SerializedName("@source_path")
    public String source_path;

    @SerializedName("@type")
    public String type;

    @SerializedName("@tags")
    public List<String> tags;

    @SerializedName("@message")
    public String message;

    @SerializedName("@timestamp")
    public String timestamp;

    @SerializedName("@fields")
    public JsonRecord.Fields fields;

    public JsonRecord() {
    }

    public static class Fields {

        public String logger;

        public String level;

        public Integer level_value;

        public String thread;

        public String throwable;

        public JsonRecord.Location location;

        public Map<String, String> properties;

        public Fields() {
        }
    }

    public static class Location {

        @SerializedName("class")
        public String clazz;

        public String method;

        public String file;

        public Integer line;

        public String full_name;

        public Location() {
        }

    }

}

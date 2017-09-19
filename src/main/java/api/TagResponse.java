package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.TagsRecord;

import java.math.BigDecimal;
import java.sql.Time;

public class TagResponse {
    @JsonProperty
    Integer id;

    @JsonProperty
    String tagName;


    public TagResponse(TagsRecord dbRecord) {
        this.tagName = dbRecord.getTag();
        this.id = dbRecord.getId();
    }
}

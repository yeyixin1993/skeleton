package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.ReceiptsRecord;

import java.math.BigDecimal;
import java.sql.Time;

public class TagResponse {
    @JsonProperty
    Integer id;

    @JsonProperty
    String tagName;

    @JsonProperty
    Integer id_received;

    @JsonProperty
    Time created;

  //  public TagResponse(TagsRecord dbRecord) {
     //   this.tagName = dbRecord.getTagName();
     //   this.id_receive = dbRecord.getRecieveId();
    //    this.created = dbRecord.getUploaded();
    //    this.id = dbRecord.getId();
   // }
}

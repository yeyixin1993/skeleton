package dao;

import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.RECEIPTS;
import static generated.Tables.TAGS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public int insert(String merchantName, BigDecimal amount) {
        ReceiptsRecord receiptsRecord = dsl
                .insertInto(RECEIPTS, RECEIPTS.MERCHANT, RECEIPTS.AMOUNT)
                .values(merchantName, amount)
                .returning(RECEIPTS.ID)
                .fetchOne();

        checkState(receiptsRecord != null && receiptsRecord.getId() != null, "Insert failed");

        return receiptsRecord.getId();
    }

    public List<ReceiptsRecord> getReceipts(String tag) {
        List<TagsRecord> tagName = dsl.selectFrom(TAGS).where(TAGS.TAG.eq(tag)).fetch();
        System.out.println("Start to fetch out all receipts with the tag");
        return dsl.selectFrom(RECEIPTS).where(RECEIPTS.ID.in(dsl.select(TAGS.ID).from(TAGS).where(TAGS.TAG.eq(tag)).fetch())).fetch();
    }

    public void toggle(String tag, int id) {
        if (dsl.selectFrom(RECEIPTS).where(RECEIPTS.ID.eq(id)).fetchOne() != null) {
            if (dsl.selectFrom(TAGS).where(TAGS.ID.eq(id).and(TAGS.TAG.eq(tag))).fetchOne() != null) {
                System.out.println("Already had the tag, start to delete");
                dsl.deleteFrom(TAGS).where(TAGS.ID.eq(id).and(TAGS.TAG.eq(tag)));
            }
            else {
                dsl.insertInto(TAGS).values(id, tag).execute();
                System.out.println("Tag created successfully");
            }
        }
    }
}

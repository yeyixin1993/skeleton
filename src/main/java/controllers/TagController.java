package controllers;

import api.CreateReceiptRequest;
import api.ReceiptResponse;
import api.TagResponse;
import dao.ReceiptDao;
import dao.TagDao;
import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tag;

    public TagController(TagDao tagdao) {
        tag = tagdao;
    }

     @PUT
     @Path("/tags/{tag}")
     public void toggleTag(@PathParam("tag") String tagName, int id) {
        tag.toggle(tagName, id);
     }
     @GET
     @Path("/tags/{tag}")
     public List<ReceiptResponse> getTaggedReceipts(@PathParam("tag") String tagName) {
        List<ReceiptsRecord> receiptRecords = tag.getReceipts(tagName);
        return receiptRecords.stream().map(ReceiptResponse::new).collect(toList());
     }
     @GET
     @Path("/fetchtags")
     public List<TagResponse> fetchTags() {
        List<TagsRecord> tagRecords = tag.fetchTags();
        return tagRecords.stream().map(TagResponse::new).collect(toList());
     }
}

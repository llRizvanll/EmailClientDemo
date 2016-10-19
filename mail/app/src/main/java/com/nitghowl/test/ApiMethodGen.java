package com.nitghowl.test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * App Api Interface , methods
 */
public interface ApiMethodGen {

    @GET(HttpRequestMaker.domain+HttpRequestMaker.path+"api/message/")
    Call<List<MessageListModel>> getMessagesList();

    @GET(HttpRequestMaker.domain+HttpRequestMaker.path+"api/message/{id}")
    Call<MessageItemModel> getSingleMessage( @Path("id") int id);

    @DELETE(HttpRequestMaker.domain+HttpRequestMaker.path+"api/message/{:id}")
    Call<MessageItemModel>  deleteSingleMessageById( @Path(":id") int id);

}

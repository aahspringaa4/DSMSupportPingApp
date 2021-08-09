package com.example.dsmsupportping;

import android.telecom.Call;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerApi {
    @POST("URL")
    Call<ServerResponse> createPost(@Body ServerResponse serverResponse);

//    @GET("URL")
//    Call<ServerRequest> getServer();
//
//    @DELETE("URL")
//    Call<ServerResponse> getDelete(@Path("feed_id") String feed_id);
//
//    @PATCH("URL")
//    Call<ServerResponse> editPost(
//            @Path("feed_id") String feed_id,
//            @Body ServerResponse serverResponse
//    );
}

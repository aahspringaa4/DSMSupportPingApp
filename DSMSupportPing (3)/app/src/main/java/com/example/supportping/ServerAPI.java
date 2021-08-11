package com.example.supportping;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Path;

public interface ServerAPI {

    //Login
    @POST("http://18.222.184.39:8081/join") //로그인
    Call<RequestLogin> Login(@Body RequestLogin userData);

    //Normal Activity
    @POST("/user/post")
    Call<ServerResponse> createPost(@Body ServerResponse serverResponse);

    @GET("/board")
    Call<ServerRequest> getServer();

    //Mypage

    @GET("/user/me/{user-id}")
    Call<ServerRequest> getMypage(@Path("{user-id}") String id);

    @DELETE("/user/delete/{board-id}")
    Call<ServerResponse> getDelete(@Path("{board-id}") String id);

    @PATCH("/user/update/{board-id}")
    Call<ServerResponse> editPost(
            @Path("{board-id}") String id,
            @Body ServerResponse serverResponse
    );

    //참여

    @POST("/user/enter/{board-id}")
    Call<ServerResponse> enter(@Path("{board-id}") String id);

    @DELETE("/user/exit/{board-id}")
    Call<ServerResponse> enterDelete(@Path("{board-id}") String id);
}

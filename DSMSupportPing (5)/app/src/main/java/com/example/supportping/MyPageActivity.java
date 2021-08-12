package com.example.supportping;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends AppCompatActivity {

    ArrayList<MainData> arrayList;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
    }
    public static void deletePost(String username) {
        ServerAPI ServerApi = ApiProvider.getInstance().create(ServerAPI.class);
        ServerApi.getDelete(MainActivity.username).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, @NotNull Response<ServerResponse> response) {
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
            }
        });
    }

    private void StartSetPost(ServerRequest serverRequest) {
        int totalElements = serverRequest.getBoardinfos().size();
        Log.d("왜 안될까", "되나");
        for (int i = 0; i < totalElements; i++) {
            JsonObject jsonObject = serverRequest.getBoardinfos().get(i);

            String tv_id = jsonObject.get("id").toString();
            String tv_title = jsonObject.get("title").toString();
            String tv_content = jsonObject.get("content").toString();
            String tv_place = jsonObject.get("place").toString();
            String tv_people = jsonObject.get("people").toString();

            MainData mainData = new MainData(tv_title, tv_content, tv_id, tv_place, tv_people);
            arrayList.add(mainData);
            mainAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        arrayList.clear();
        mainAdapter.notifyDataSetChanged();

        ServerAPI ServerApi = ApiProvider.getInstance().create(ServerAPI.class);
        Call<ServerRequest> call = ServerApi.getMypage(MainActivity.username);

        call.enqueue(new Callback<ServerRequest>() {
            @Override
            public void onResponse(Call<ServerRequest> call, Response<ServerRequest> response) {
                ServerRequest requestValue = response.body();
                StartSetPost(requestValue);
            }

            @Override
            public void onFailure(Call<ServerRequest> call, Throwable t) {
            }
        });


    }
}
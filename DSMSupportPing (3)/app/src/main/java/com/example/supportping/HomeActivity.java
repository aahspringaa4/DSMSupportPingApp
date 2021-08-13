package com.example.supportping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton ib_add;

    ArrayList<MainData> arrayList;
    MainAdapter mainAdapter;
    LinearLayout linearLayout;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        mainAdapter = new MainAdapter(arrayList, getApplicationContext());
        recyclerView.setAdapter(mainAdapter);

        ib_add = (ImageButton) findViewById(R.id.ib_add);
        ib_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PostActivity.class));
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

    public static void deletePost(String data) {
        ServerAPI ServerApi = ApiProvider.getInstance().create(ServerAPI.class);
        ServerApi.getDelete(data).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, @NotNull Response<ServerResponse> response) {
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        arrayList.clear();
        mainAdapter.notifyDataSetChanged();

        ServerAPI ServerApi = ApiProvider.getInstance().create(ServerAPI.class);

        Call<ServerRequest> call = ServerApi.getServer();

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

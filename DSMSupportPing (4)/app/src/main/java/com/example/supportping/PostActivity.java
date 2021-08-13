package com.example.supportping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private static View PostButton;
    private Spinner floor;
    private EditText location;
    private TextView NowLocation, people;
    private Spinner person;
    private Button button;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        floor = (Spinner) findViewById(R.id.floor);
        location = (EditText) findViewById(R.id.location);
        NowLocation = (TextView) findViewById(R.id.NowLocation);
        person = (Spinner) findViewById(R.id.person);
        people = (TextView) findViewById(R.id.people);
        button = (Button) findViewById(R.id.button);
        PostButton = (TextView) findViewById(R.id.PostButton);
        back = (ImageButton)findViewById(R.id.back);
        if(MainAdapter.editStatus == true){
            editPost("data", "title", "content", "nowLocation", "people");
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        floor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                NowLocation.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        person.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                people.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (location != null) {
                    NowLocation.setText(NowLocation.getText() + " " + location.getText().toString());
                }
            }
        });
        PostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerAPI ServerApi = ApiProvider.getInstance().create(ServerAPI.class);
                Log.d("결과", "성공4");
                ServerResponse post = new ServerResponse("title", "content", "NowLocation", "people");
                Log.d("결과", "성공2");
                Call<ServerResponse> call = ServerApi.createPost(post);
                Log.d("결과", "성공3");
                call.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        Log.d("결과", "성공");
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Log.d("결과", "실패");
                    }
                });
            }
        });


    }

    private void editPost(String data, String title, String content, String nowLocation, String people) {
        PostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerAPI ServerAPI = ApiProvider.getInstance().create(ServerAPI.class);
                ServerResponse id = new ServerResponse("title", "content", "NowLocation", "people");
                Call<ServerResponse> call = ServerAPI.editPost(data, id);
                call.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        Log.d("결과", "성공");
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Log.d("실패", "실패");
                    }
                });
            }
        });
    }
}
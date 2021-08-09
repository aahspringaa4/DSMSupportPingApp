package com.example.dsmsupportping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Spinner floor;
    private EditText location;
    private TextView NowLocation,people, PostButton;
    private Spinner person;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floor = (Spinner)findViewById(R.id.floor);
        location = (EditText)findViewById(R.id.location);
        NowLocation = (TextView)findViewById(R.id.NowLocation);
        person = (Spinner)findViewById(R.id.person);
        people = (TextView)findViewById(R.id.people);
        button = (Button)findViewById(R.id.button);
        PostButton = (TextView)findViewById(R.id.PostButton);

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
                ServerApi ServerApi = ApiProvider.getInstance().create(ServerApi.class);
                ServerResponse post = new ServerResponse("title", "content", "NowLocation", "people");
                Call<ServerResponse> call = ServerApi.createPost(post);
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
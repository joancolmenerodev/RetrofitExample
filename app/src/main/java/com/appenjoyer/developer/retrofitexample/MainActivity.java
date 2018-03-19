package com.appenjoyer.developer.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appenjoyer.developer.retrofitexample.model.GitHubUser;
import com.appenjoyer.developer.retrofitexample.rest.ApiClient;
import com.appenjoyer.developer.retrofitexample.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnGetuser;
    private EditText etUserName;
    private ApiInterface apiService;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService =
                ApiClient.getClient().create(ApiInterface.class);

        btnGetuser = findViewById(R.id.btnGetUser);
        etUserName = findViewById(R.id.etGitHubUsername);
        tvInfo = findViewById(R.id.tvInfo);
        btnGetuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<GitHubUser> call = apiService.getUsername(etUserName.getText().toString().isEmpty() ? null : etUserName.getText().toString());
                call.enqueue(new Callback<GitHubUser>() {
                    @Override
                    public void onResponse(Call<GitHubUser>call, Response<GitHubUser> response) {
                        GitHubUser gitHubUser = response.body();
                        tvInfo.setText(gitHubUser.toString());
                    }

                    @Override
                    public void onFailure(Call<GitHubUser>call, Throwable t) {
                        tvInfo.setText("Invalid username");
                    }
                });
            }
        });


    }
}

package com.journaldev.okhttp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtString;
    Button asynchronousGet, synchronousGet, asynchronousPOST;

    public final String host = "https://reqres.in/";
    public String url = "https://reqres.in/api/users/2";
    public String postUrl = "https://reqres.in/api/users/";
    public String postBody = "{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"leader\"\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        asynchronousGet = (Button) findViewById(R.id.asynchronousGet);
        synchronousGet = (Button) findViewById(R.id.synchronousGet);
        asynchronousPOST = (Button) findViewById(R.id.asynchronousPost);

        asynchronousGet.setOnClickListener(this);
        synchronousGet.setOnClickListener(this);
        asynchronousPOST.setOnClickListener(this);

        txtString = (TextView) findViewById(R.id.txtString);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.asynchronousGet:
                getUser("2");
                break;
            case R.id.synchronousGet:
                getUsers();
                break;
            case R.id.asynchronousPost:
                Login("peter@klaven", "cityslicka");
                break;
        }
    }

    public void getUser(String userId) {

        NetworkApi networkApi = ApiUtils.getNetworkApi();
        networkApi.getUser(userId).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                Log.e("RESP", new GsonBuilder().setPrettyPrinting().create().toJson(response));
                if (response.code() != 200) {
                    txtString.setText("ERROR: " + String.valueOf(response.code()));
                } else {
                    UserData data = response.body();
                    UserModel userModel = data.getData();
                    txtString.setText(userModel.getFirstName() + " - " + userModel.getLastName());
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                txtString.setText("Parsing Failed");
            }
        });
    }

    public void getUsers() {

        NetworkApi networkApi = ApiUtils.getNetworkApi();
        networkApi.getUsers().enqueue(new Callback<UsersList>() {
            @Override
            public void onResponse(Call<UsersList> call, Response<UsersList> response) {
                Log.e("RESP", new GsonBuilder().setPrettyPrinting().create().toJson(response));
                if (response.code() != 200) {
                    txtString.setText("ERROR: " + String.valueOf(response.code()));
                } else {
                    UsersList users = response.body();
                    for (UserModel userModel: users.getData()) {
                        txtString.append(userModel.getFirstName() + " - " + userModel.getLastName() + "\n");
                    }
                }
            }

            @Override
            public void onFailure(Call<UsersList> call, Throwable t) {
                txtString.setText("Parsing Failed");
            }
        });
    }

    public void Login(String email, String password) {

        NetworkApi networkApi = ApiUtils.getNetworkApi();
        networkApi.Login(email, password).enqueue(new Callback<LoginToken>() {
            @Override
            public void onResponse(Call<LoginToken> call, Response<LoginToken> response) {
                Log.e("RESP", new GsonBuilder().setPrettyPrinting().create().toJson(response));
                if (response.code() != 200) {
                    txtString.setText("ERROR: " + String.valueOf(response.code()));
                } else {
                    LoginToken token = response.body();
                    txtString.setText(token.getToken());
                }
            }

            @Override
            public void onFailure(Call<LoginToken> call, Throwable t) {
                txtString.setText("Parsing Failed");
            }
        });
    }

}

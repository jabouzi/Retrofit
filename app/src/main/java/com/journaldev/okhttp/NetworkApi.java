package com.journaldev.okhttp;

/**
 * Created by Skander Jabouzi on 2018-01-11.
 * Manulife
 * skander_jabouzi@manulife.com
 */

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkApi {
    @Headers({
            "Accept: application/json; charset=utf-8"
    })
    @GET("api/users/{userId}")
    Call<UserData> getUser(@Path("userId") String userId);

    @GET("api/users")
    Call<UsersList> getUsers();

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginToken> Login(@Field("email") String email, @Field("password") String password);
}

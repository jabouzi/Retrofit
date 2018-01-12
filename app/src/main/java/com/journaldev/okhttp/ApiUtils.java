package com.journaldev.okhttp;

/**
 * Created by Skander Jabouzi on 2018-01-12.
 * Manulife
 * skander_jabouzi@manulife.com
 */

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://reqres.in/";

    public static NetworkApi getNetworkApi() {

        return RetrofitClient.getClient(BASE_URL).create(NetworkApi.class);
    }
}

package com.journaldev.okhttp;

/**
 * Created by Skander Jabouzi on 2018-01-12.
 * Manulife
 * skander_jabouzi@manulife.com
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginToken {
    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

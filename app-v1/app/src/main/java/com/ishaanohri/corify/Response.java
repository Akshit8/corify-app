package com.ishaanohri.corify;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("response")
    private List response;

    public List getResponse() {
        return response;
    }

}

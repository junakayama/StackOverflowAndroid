package com.example.julia.stackoverflowandroid.models;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by julia on 01/12/16.
 */

public interface StackOverflowService {

    public static String BASE_URL = "https://api.stackexchange.com/2.2/";

    @GET("tags/Android/info?order=desc&sort=popular&site=stackoverflow")
    Call<TagsList> listTags();


}

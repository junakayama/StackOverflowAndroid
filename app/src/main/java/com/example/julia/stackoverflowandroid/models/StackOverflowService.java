package com.example.julia.stackoverflowandroid.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by julia on 01/12/16.
 */

public interface StackOverflowService {

    public static String BASE_URL = "https://api.stackexchange.com/2.2/";

    @GET("tags/android/related?site=stackoverflow")
    Call<TagsList> listTags();

    @GET("tags/{tags}/faq?pagesize=20&site=stackoverflow")
    Call<QuestionsList> listQuestions(@Path("tags") String tag);
}

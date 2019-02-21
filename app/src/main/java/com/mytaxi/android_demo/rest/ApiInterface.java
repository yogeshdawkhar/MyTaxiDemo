package com.mytaxi.android_demo.rest;

import com.mytaxi.android_demo.models.MultipleResource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("/api/?seed=a1f30d446f820665")
    Call<MultipleResource> doGetListResources();
}

package com.mytaxi.android_demo.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MultipleResource {

    @SerializedName("results")
    public List<Driver> results = null;

    public class Driver {
        @SerializedName("login")
        public Login login = null;

    }
    public class Login {
        @SerializedName("username")
        public String username;
        @SerializedName("password")
        public String password;
    }
}
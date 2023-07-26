package com.example.biometric;
//
//public class Article {
//}
import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    // Add other relevant fields here

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    // Add getter methods for other fields
}

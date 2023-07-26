package com.example.biometric;
//
//public class NewsResponse {
//}

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NewsResponse {

    @SerializedName("articles")
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }
}

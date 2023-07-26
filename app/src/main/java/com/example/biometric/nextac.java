package com.example.biometric;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class nextac extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_nextac);
//    }
//}
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class nextac extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nextac);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Make a network request to fetch news data
        fetchNewsData();
    }

    private void fetchNewsData() {
        NewsApiClient newsApiClient = RetrofitClient.getRetrofitClientInstance().create(NewsApiClient.class);
        Call<NewsResponse> call = newsApiClient.getNews("india", "c06c010dd62a4f62b01612dfeb03778b"); // Replace YOUR_API_KEY with your actual API key

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    List<Article> articles = response.body().getArticles();
                    showNews(articles);
                } else {
                    Toast.makeText(nextac.this, "Failed to fetch news data.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("API_CALL", "Error occurred: " + t.getMessage());
                Toast.makeText(nextac.this, "Failed to fetch news data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showNews(List<Article> articles) {
        newsAdapter = new NewsAdapter(this, articles);
        recyclerView.setAdapter(newsAdapter);
    }
}

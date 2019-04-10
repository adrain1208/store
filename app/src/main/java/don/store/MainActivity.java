package don.store;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    private GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/").build();
        service = retrofit.create(GitHubService.class);
        Call call = service.listRepos("octocat");
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                System.out.println(response.body().get(0).getArchive_url());
            }



            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}

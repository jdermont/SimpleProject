package pl.derjack.simpleproject.ws;

import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import pl.derjack.simpleproject.ws.model.WsShow;
import pl.derjack.simpleproject.ws.model.WsTVShow;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://api.tvmaze.com";

    private final static ApiClient apiClient = new ApiClient();

    public static ApiClient getInstance() {
        return apiClient;
    }

    private ApiInterface apiInterface;

    private ApiClient() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(HttpClientBuilder.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public Observable<List<WsTVShow>> retrieveTVShows(String shows) {
        return apiInterface.getTVShows(shows);
    }

    public Observable<WsShow> retrieveTVShow(String id) {
        return apiInterface.getTVShow(id);
    }
}

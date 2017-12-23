package pl.derjack.simpleproject.ws;

import java.util.List;

import io.reactivex.Observable;
import pl.derjack.simpleproject.ws.model.WsShow;
import pl.derjack.simpleproject.ws.model.WsTVShow;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search/shows")
    Observable<List<WsTVShow>> getTVShows(@Query("q") String shows);

    @GET("shows/{id}?embed=episodes")
    Observable<WsShow> getTVShow(@Path("id") String id);
}

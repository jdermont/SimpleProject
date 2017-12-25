package pl.derjack.simpleproject;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.derjack.simpleproject.entity.TvShow;
import pl.derjack.simpleproject.recyclerview.MainAdapter;
import pl.derjack.simpleproject.ws.ApiClient;
import pl.derjack.simpleproject.ws.model.WsModelConverterKt;
import pl.derjack.simpleproject.ws.model.WsTVShow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainAdapter = new MainAdapter(this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchShows(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchShows(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void searchShows(String query) {
        ApiClient.getInstance().retrieveTVShows(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wsShows -> {
                    List<TvShow> tvShows = new ArrayList<>(wsShows.size());
                    for (WsTVShow wsShow : wsShows) {
                        tvShows.add(WsModelConverterKt.convertToTvShowEntity(wsShow.getShow()));
                    }
                    mainAdapter.updateShows(tvShows);
                });
    }

    @Override
    public void onClick(View v) {
        TvShow tvShow = (TvShow) v.getTag();
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra(DetailsActivity.KEY_TVSHOW, tvShow);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, v.findViewById(R.id.mainItemImage), getString(R.string.transition_image));
        startActivity(intent, options.toBundle());
    }

}

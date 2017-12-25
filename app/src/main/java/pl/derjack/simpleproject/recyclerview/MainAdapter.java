package pl.derjack.simpleproject.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.derjack.simpleproject.R;
import pl.derjack.simpleproject.entity.TvShow;

public class MainAdapter extends RecyclerView.Adapter<TvViewHolder> {

    private View.OnClickListener onClickListener;
    private List<TvShow> tvShows;

    public MainAdapter(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void updateShows(List<TvShow> tvShows) {
        this.tvShows = tvShows;
        notifyDataSetChanged();
    }

    @Override
    public TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new TvViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(TvViewHolder holder, int position) {
        holder.updateView(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShows == null ? 0 : tvShows.size();
    }
}

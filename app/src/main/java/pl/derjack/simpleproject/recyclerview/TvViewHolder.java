package pl.derjack.simpleproject.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import pl.derjack.simpleproject.R;
import pl.derjack.simpleproject.entity.TvShow;

public class TvViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView titleText;
    private TextView descriptionText;

    public TvViewHolder(View itemView, View.OnClickListener onClickListener) {
        super(itemView);
        itemView.setOnClickListener(onClickListener);
        imageView = itemView.findViewById(R.id.mainItemImage);
        titleText = itemView.findViewById(R.id.mainItemTitle);
        descriptionText = itemView.findViewById(R.id.mainItemDescription);
    }

    public void updateView(TvShow tvShow) {
        itemView.setTag(tvShow);
        Picasso.with(imageView.getContext()).load(tvShow.getOriginalImage()).into(imageView);
        titleText.setText(tvShow.getName());

        String description = String.format(Locale.ENGLISH, "Premiered: %s\nType: %s\nStatus: %s\nRating: %.1f",
                tvShow.getPremiered(), tvShow.getType(), tvShow.getStatus(), tvShow.getRating());
        descriptionText.setText(description);

    }
}

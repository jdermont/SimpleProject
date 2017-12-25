package pl.derjack.simpleproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import pl.derjack.simpleproject.entity.TvShow;

public class DetailsActivity extends AppCompatActivity {
    public static final String KEY_TVSHOW = "key_tvshow";

    private TvShow tvShow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.detailsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        tvShow = getIntent().getParcelableExtra(KEY_TVSHOW);
        setupViews();
    }

    private void setupViews() {
        ImageView detailsShowImage = findViewById(R.id.detailsShowImage);
        Picasso.with(detailsShowImage.getContext()).load(tvShow.getOriginalImage()).into(detailsShowImage);
        TextView detailsDescriptionText = findViewById(R.id.detailsDescriptionText);
        String description = String.format(Locale.ENGLISH, "%s\n%s\n%s\n%.1f",
                tvShow.getPremiered(), tvShow.getType(), tvShow.getStatus(), tvShow.getRating());
        detailsDescriptionText.setText(description);
        TextView detailsSummaryText = findViewById(R.id.detailsSummaryText);
        detailsSummaryText.setText(Html.fromHtml(tvShow.getSummary()));
    }
}

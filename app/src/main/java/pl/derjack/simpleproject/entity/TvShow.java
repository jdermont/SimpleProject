package pl.derjack.simpleproject.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TvShow implements Parcelable, Comparable<TvShow> {
    private long id;
    private String url;
    private String name;
    private String type;
    private String language;
    private List<String> genres;
    private String status;
    private int runtime;
    private String premiered;
    private String scheduleTime;
    private List<String> scheduleDays;
    private double rating;
    private int weight;
    private Network network;
    private String mediumImage;
    private String originalImage;
    private String summary;
    private long updated;
    private List<Episode> episodes;

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getStatus() {
        return status;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getPremiered() {
        return premiered;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public List<String> getScheduleDays() {
        return scheduleDays;
    }

    public double getRating() {
        return rating;
    }

    public int getWeight() {
        return weight;
    }

    public Network getNetwork() {
        return network;
    }

    public String getMediumImage() {
        return mediumImage;
    }

    public String getOriginalImage() {
        return originalImage;
    }

    public String getSummary() {
        return summary;
    }

    public long getUpdated() {
        return updated;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public TvShow() {

    }

    protected TvShow(Parcel in) {
        id = in.readLong();
        url = in.readString();
        name = in.readString();
        type = in.readString();
        language = in.readString();
        genres = in.createStringArrayList();
        status = in.readString();
        runtime = in.readInt();
        premiered = in.readString();
        scheduleTime = in.readString();
        scheduleDays = in.createStringArrayList();
        rating = in.readDouble();
        weight = in.readInt();
        network = in.readParcelable(Network.class.getClassLoader());
        mediumImage = in.readString();
        originalImage = in.readString();
        summary = in.readString();
        updated = in.readLong();
        int episodeSize = in.readInt();
        episodes = new ArrayList<>(episodeSize);
        for (int i=0;i<episodeSize;i++) {
            episodes.add((Episode)in.readParcelable(Episode.class.getClassLoader()));
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(url);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(language);
        dest.writeStringList(genres);
        dest.writeString(status);
        dest.writeInt(runtime);
        dest.writeString(premiered);
        dest.writeString(scheduleTime);
        dest.writeStringList(scheduleDays);
        dest.writeDouble(rating);
        dest.writeInt(weight);
        dest.writeParcelable(network, flags);
        dest.writeString(mediumImage);
        dest.writeString(originalImage);
        dest.writeString(summary);
        dest.writeLong(updated);
        int episodeSize = episodes.size();
        dest.writeInt(episodeSize);
        for (Episode episode : episodes) {
            dest.writeParcelable(episode, flags);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    @Override
    public int compareTo(@NonNull TvShow o) {
        if (name == null) {
            return o.name == null ? 0 : 1;
        } else if (o.name == null) {
            return -1;
        } else {
            return name.compareTo(o.name);
        }
    }
}

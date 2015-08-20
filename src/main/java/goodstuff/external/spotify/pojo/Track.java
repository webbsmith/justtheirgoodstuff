package goodstuff.external.spotify.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by webb on 10/19/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {
    private List<Artist> artists;
    private int track_number;
    private String name;
    private int popularity;

    @Override
    public String toString() {
        return "Track{" +
                "artists=" + artists +
                ", track_number=" + track_number +
                ", name='" + name + '\'' +
                ", popularity=" + popularity +
                '}';
    }

    public int getTrack_number() {
        return track_number;
    }

    public void setTrack_number(int track_number) {
        this.track_number = track_number;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}

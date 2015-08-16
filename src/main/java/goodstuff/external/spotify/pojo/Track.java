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

    @Override
    public String toString() {
        return "Track{" +
                "artists=" + artists +
                ", track_number=" + track_number +
                ", name='" + name + '\'' +
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
}

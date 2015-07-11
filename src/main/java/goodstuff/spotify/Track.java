package goodstuff.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by webb on 10/19/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {
    List<Artist> artists;
    int track_number;

    @Override
    public String toString() {
        return "Track{" +
                "artists=" + artists +
                ", track_number=" + track_number +
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
}

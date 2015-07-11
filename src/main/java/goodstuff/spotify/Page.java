package goodstuff.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by webb on 10/19/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {
    TrackSearchResult tracks;

    @Override
    public String toString() {
        return "Page{" +
                "tracks=" + tracks +
                '}';
    }

    public TrackSearchResult getTracks() {
        return tracks;
    }

    public void setTracks(TrackSearchResult tracks) {
        this.tracks = tracks;
    }
}

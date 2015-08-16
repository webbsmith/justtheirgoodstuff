package goodstuff.external.echonest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by webb on 10/19/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EchoResponse {
    private List<EchoSong> songs;

    @Override
    public String toString() {
        return "EchoResponse{" +
                "songs=" + songs +
                '}';
    }

    public List<EchoSong> getSongs() {
        return songs;
    }

    public void setSongs(List<EchoSong> songs) {
        this.songs = songs;
    }
}

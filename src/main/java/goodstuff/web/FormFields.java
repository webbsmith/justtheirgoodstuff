package goodstuff.web;

import goodstuff.echonest.EchoSong;
import goodstuff.spotify.Track;

import java.util.Arrays;
import java.util.List;

/**
 * Created by webb on 10/18/14.
 */
public class FormFields {

    String search;
    String artist;
    Track[] tracks;
    List<EchoSong> songs;

    @Override
    public String toString() {
        return "FormFields{" +
                "search='" + search + '\'' +
                ", artist='" + artist + '\'' +
                ", tracks=" + Arrays.toString(tracks) +
                ", songs=" + songs +
                '}';
    }

    private String convertSpaces(String s) {
        return s.replaceAll(" ", "+");
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Track[] getTracks() {
        return tracks;
    }

    public void setTracks(Track[] tracks) {
        this.tracks = tracks;
    }

    public List<EchoSong> getSongs() {
        return songs;
    }

    public void setSongs(List<EchoSong> songs) {
        this.songs = songs;
    }

}
package goodstuff;

import java.util.Arrays;
import java.util.List;

/**
 * Created by webb on 10/18/14.
 */
public class FormFields {

    String search;
    String artist;
    Track[] tracks;
    List<EchoSong> songs1;
    List<EchoSong> songs2;
    List<EchoSong> songs3;

    @Override
    public String toString() {
        return "FormFields{" +
                "search='" + search + '\'' +
                ", artist='" + artist + '\'' +
                ", tracks=" + Arrays.toString(tracks) +
                ", songs1=" + songs1 +
                ", songs2=" + songs2 +
                ", songs3=" + songs3 +
                '}';
    }

    public Track[] getTracks() {
        return tracks;
    }

    public void setTracks(Track[] tracks) {
        this.tracks = tracks;
    }

    public List<EchoSong> getSongs1() {
        return songs1;
    }

    public void setSongs1(List<EchoSong> songs1) {
        this.songs1 = songs1;
    }

    public List<EchoSong> getSongs2() {
        return songs2;
    }

    public void setSongs2(List<EchoSong> songs2) {
        this.songs2 = songs2;
    }

    public List<EchoSong> getSongs3() {
        return songs3;
    }

    public void setSongs3(List<EchoSong> songs3) {
        this.songs3 = songs3;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = convertSpaces(search);
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    private String convertSpaces(String s) {
        return s.replaceAll(" ", "+");
    }

}

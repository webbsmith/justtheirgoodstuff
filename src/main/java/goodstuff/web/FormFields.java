package goodstuff.web;

import goodstuff.external.SearchResultHolder;
import goodstuff.external.echonest.pojo.EchoSong;
import goodstuff.external.spotify.pojo.Track;

import java.util.Arrays;
import java.util.List;

/**
 * Created by webb on 10/18/14.
 */
public class FormFields {

    private String search;
    private String artist;
    private String likeAboutIt = "Tempo";
    private Track[] tracks;
    private List<String> songs;
    private boolean success;
    private String errorMessage;
    private SearchResultHolder searchResultHolder = new SearchResultHolder();

    @Override
    public String toString() {
        return "FormFields{" +
                "search='" + search + '\'' +
                ", artist='" + artist + '\'' +
                ", likeAboutIt='" + likeAboutIt + '\'' +
                ", tracks=" + Arrays.toString(tracks) +
                ", songs=" + songs +
                ", success=" + success +
                ", errorMessage='" + errorMessage + '\'' +
                ", searchResultHolder=" + searchResultHolder +
                '}';
    }

    public String getLikeAboutIt() {
        return likeAboutIt;
    }

    public void setLikeAboutIt(String likeAboutIt) {
        this.likeAboutIt = likeAboutIt;
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

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public SearchResultHolder getSearchResultHolder() {
        return searchResultHolder;
    }

    public void setSearchResultHolder(SearchResultHolder searchResultHolder) {
        this.searchResultHolder = searchResultHolder;
    }
}

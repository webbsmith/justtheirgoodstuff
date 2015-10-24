package goodstuff.web;

import goodstuff.external.FilteredSearchResults;
import goodstuff.external.spotify.pojo.Track;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by webb on 10/18/14.
 */
public class FormFields {

    private String search;
    private String selectedArtist;
    private String likeAboutIt = "Tempo";
    private Track[] tracks;
    private Collection<String> songs;
    private Collection<String> otherArtists;
    private boolean success;
    private String errorMessage;

    public void updateWithSearchResults(FilteredSearchResults searchResults) {
        if (searchResults == null || searchResults.noSongs()) {
            this.setSuccess(false);
            this.setErrorMessage("No results found");
        } else {
            this.setSongs(convertSpaces(searchResults.getSelectedArtistSongs()));
            this.setSelectedArtist(convertSpaces(searchResults.getSelectedArtist()));
            this.setOtherArtists(convertSpaces(searchResults.getOtherArtistList()));
            this.setSuccess(true);
        }
    }

    private Collection<String> convertSpaces(Collection<String> collection) {
        return collection.stream().map(this::convertSpaces).collect(Collectors.toList());
    }

    private String convertSpaces(String s) {
        return s.replace('+', ' ');
    }

    @Override
    public String toString() {
        return "FormFields{" +
                "search='" + search + '\'' +
                ", selectedArtist='" + selectedArtist + '\'' +
                ", likeAboutIt='" + likeAboutIt + '\'' +
                ", tracks=" + Arrays.toString(tracks) +
                ", songs=" + songs +
                ", otherArtists=" + otherArtists +
                ", success=" + success +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public String getLikeAboutIt() {
        return likeAboutIt;
    }

    public void setLikeAboutIt(String likeAboutIt) {
        this.likeAboutIt = likeAboutIt;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSelectedArtist() {
        return selectedArtist;
    }

    public void setSelectedArtist(String selectedArtist) {
        this.selectedArtist = selectedArtist;
    }

    public Track[] getTracks() {
        return tracks;
    }

    public void setTracks(Track[] tracks) {
        this.tracks = tracks;
    }

    public Collection<String> getSongs() {
        return songs;
    }

    public void setSongs(Collection<String> songs) {
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

    public Collection<String> getOtherArtists() {
        return otherArtists;
    }

    public void setOtherArtists(Collection<String> otherArtists) {
        this.otherArtists = otherArtists;
    }
}

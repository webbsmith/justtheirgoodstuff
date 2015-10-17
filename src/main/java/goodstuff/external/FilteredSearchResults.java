package goodstuff.external;

import java.util.List;

/**
 * Created by webb on 8/15/15.
 */
public class FilteredSearchResults {
    private final String selectedArtist;
    private final List<String> selectedArtistSongs;
    private final List<String> otherArtistList;

    FilteredSearchResults(String selectedArtist, List<String> selectedArtistSongs, List<String> otherArtistList) {
        this.selectedArtist = selectedArtist;
        this.selectedArtistSongs = selectedArtistSongs;
        this.otherArtistList = otherArtistList;
    }

    public boolean noSongs() {
        return selectedArtistSongs.isEmpty();
    }

    public List<String> getSelectedArtistSongs() {
        return selectedArtistSongs;
    }

    public String getSelectedArtist() {
        return selectedArtist;
    }

    public List<String> getOtherArtistList() {
        return otherArtistList;
    }

    @Override
    public String toString() {
        return "FilteredSearchResults{" +
                "selectedArtist='" + selectedArtist + '\'' +
                ", selectedArtistSongs=" + selectedArtistSongs +
                ", otherArtistList=" + otherArtistList +
                '}';
    }
}

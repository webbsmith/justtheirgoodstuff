package goodstuff.external;

import java.util.Collection;
import java.util.List;

/**
 * Created by webb on 8/15/15.
 */
public class FilteredSearchResults {
    private final String selectedArtist;
    private final Collection<String> selectedArtistSongs;
    private final Collection<String> otherArtistList;

    FilteredSearchResults(String selectedArtist, Collection<String> selectedArtistSongs, Collection<String> otherArtistList) {
        this.selectedArtist = selectedArtist;
        this.selectedArtistSongs = selectedArtistSongs;
        this.otherArtistList = otherArtistList;
    }

    public boolean noSongs() {
        return selectedArtistSongs.isEmpty();
    }

    public Collection<String> getSelectedArtistSongs() {
        return selectedArtistSongs;
    }

    public String getSelectedArtist() {
        return selectedArtist;
    }

    public Collection<String> getOtherArtistList() {
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

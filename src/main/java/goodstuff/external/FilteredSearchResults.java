package goodstuff.external;

import java.util.List;

/**
 * Created by webb on 8/15/15.
 */
public class FilteredSearchResults {
    private List<String> songNames;
    private String artistName;

    FilteredSearchResults(String artistName, List<String> songNames) {
        this.artistName = artistName;
        this.songNames = songNames;
    }

    public boolean isEmpty() {
        return songNames.isEmpty();
    }

    public List<String> getSongNames() {
        return songNames;
    }

    public String getArtistName() {
        return artistName;
    }
}

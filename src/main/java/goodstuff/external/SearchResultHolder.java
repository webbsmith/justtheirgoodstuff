package goodstuff.external;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by webb on 8/19/15.
 */
public class SearchResultHolder {
    public static final class Key {
        private final String songName;
        private final String artistName;
        private final String filterName;

        private Key(String songName, String artistName, String filterName) {
            this.songName = songName;
            this.artistName = artistName;
            this.filterName = filterName;
        }
    }

    public static Key makeKey(String songName, String artistName, String filterName) {
        return new Key(songName, artistName, filterName);
    }

    private Map<Key, FilteredSearchResults> searchResultsMap = new HashMap<Key, FilteredSearchResults>();

    public FilteredSearchResults get(Key key) {
        return searchResultsMap.get(key);
    }

    public void put(Key key, FilteredSearchResults filteredSearchResults) {
        searchResultsMap.put(key, filteredSearchResults);
    }

    public int size() {
        return searchResultsMap.size();
    }

    @Override
    public String toString() {
        return "SearchResultHolder{" +
                "searchResultsMap=" + Arrays.toString(searchResultsMap.entrySet().toArray()) +
                '}';
    }
}

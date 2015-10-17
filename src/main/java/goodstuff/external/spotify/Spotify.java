package goodstuff.external.spotify;

import goodstuff.external.spotify.pojo.Page;
import goodstuff.external.spotify.pojo.Track;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by webb on 8/15/15.
 */
public class Spotify {

    public Results searchSpotifyApi(String songSearch) {
        // todo - when adding multiple artists, sort by track popularity (new param for Track)
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.spotify.com/v1/search?query=" + songSearch +
                "&offset=0&limit=5&type=track&market=US";
        Page page = restTemplate.getForObject(url, Page.class);
        System.out.println("Spotify URL: " + url);
        System.out.println("Track result count: " + page.getTracks().getItems().size());
        return new Results(page);
    }

    public class Results {

        /** true if there are no tracks returned from the search */
        private boolean empty;

        private List<Result> resultList;

        public boolean isEmpty() {
            return empty;
        }

        public int size() {
            return resultList.size();
        }

        public Result get(int index) {
            return resultList.get(index);
        }

        private Results(Page spotifySearchResultsPage) {
            this.empty = determineIfNoSearchResults(spotifySearchResultsPage);
            if (!empty) {
                resultList = parseResults(spotifySearchResultsPage);
            }
        }

        private boolean determineIfNoSearchResults(Page page) {
            return page.getTracks().getItems().size() == 0;
        }

        private List<Result> parseResults(Page page) {
            List<Result> resultList = new ArrayList<Result>();
            for (Track track : page.getTracks().getItems()) {
                resultList.add(new Result(track.getName(), track.getArtists().get(0).getName()));
            }
            return resultList;
        }
    }

    public class Result {
        /** the name of the first song in the search results */
        private String songName;
        /** the artist of the first song in the search results */
        private String artistName;

        private Result(String songName, String artistName) {
            this.songName = songName;
            this.artistName = artistName;
        }

        public String getSongName() {
            return songName;
        }

        public String getArtistName() {
            return artistName;
        }
    }

}

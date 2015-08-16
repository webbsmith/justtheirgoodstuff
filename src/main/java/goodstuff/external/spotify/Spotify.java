package goodstuff.external.spotify;

import goodstuff.external.spotify.pojo.Page;
import org.springframework.web.client.RestTemplate;

/**
 * Created by webb on 8/15/15.
 */
public class Spotify {

    public Result searchSpotifyApi(String songSearch) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.spotify.com/v1/search?query=" + songSearch +
                "&offset=0&limit=5&type=track&market=US";
        Page page = restTemplate.getForObject(url, Page.class);
        System.out.println("Spotify URL: " + url);
        System.out.println("Track result count: " + page.getTracks().getItems().size());
        return new Result(page);
    }

    public class Result {
        /** true if there are no tracks returned from the search */
        private boolean empty;
        /** the name of the first song in the search results */
        private String songName;
        /** the artist of the first song in the search results */
        private String artistName;

        private Result(Page spotifySearchResultsPage) {
            this.empty = determineIfNoSearchResults(spotifySearchResultsPage);
            if (!isEmpty()) {
                this.songName = parseSongName(spotifySearchResultsPage);
                this.artistName = parseArtistName(spotifySearchResultsPage);
            }
        }

        public boolean isEmpty() {
            return empty;
        }

        public String getSongName() {
            return songName;
        }

        public String getArtistName() {
            return artistName;
        }

        private boolean determineIfNoSearchResults(Page page) {
            return page.getTracks().getItems().size() == 0;
        }

        private String parseSongName(Page page) {
            return page.getTracks().getItems().get(0).getName();
        }

        private String parseArtistName(Page page) {
            return page.getTracks().getItems().get(0).getArtists().get(0).getName();
        }

    }
}

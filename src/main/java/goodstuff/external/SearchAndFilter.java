package goodstuff.external;

import goodstuff.external.echonest.EchoNestPoller;
import goodstuff.external.echonest.FailedToRetrieveSongFromEchoNestException;
import goodstuff.external.echonest.pojo.EchoSong;
import goodstuff.external.spotify.Spotify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by webb on 8/15/15.
 */
public class SearchAndFilter {
    public static FilteredSearchResults searchAndFilter(String songSearch, String filterName) {
        Spotify.Result spotifyResult = new Spotify().searchSpotifyApi(songSearch);
        String artist = spotifyResult.getArtistName();
        List<EchoSong> filteredSongList;
        try {
            filteredSongList = new EchoNestPoller()
                    .getFilteredPopularSongs(spotifyResult.getSongName(), artist, filterName);
        } catch (FailedToRetrieveSongFromEchoNestException e) {
            System.out.println(e.getLocalizedMessage());
            // If the song name couldn't be retrieved from echonest, treat as no results found
            filteredSongList = new ArrayList<EchoSong>();
        }
        return new FilteredSearchResults(artist, getSongNames(filteredSongList));
    }

    private static List<String> getSongNames(List<EchoSong> echoSongList) {
        List<String> songNames = new ArrayList<String>();
        for (EchoSong song : echoSongList) {
            songNames.add(song.getTitle());
        }
        return songNames;
    }
}

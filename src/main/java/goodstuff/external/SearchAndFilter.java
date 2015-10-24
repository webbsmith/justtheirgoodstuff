package goodstuff.external;

import goodstuff.external.echonest.EchoNestPoller;
import goodstuff.external.echonest.FailedToRetrieveSongFromEchoNestException;
import goodstuff.external.echonest.pojo.EchoSong;
import goodstuff.external.spotify.Spotify;

import java.util.*;

/**
 * Created by webb on 8/15/15.
 */
public class SearchAndFilter {
    public static FilteredSearchResults searchAndFilter(String songSearch, String filterName) {
        final Spotify.Results spotifyResults = new Spotify().searchSpotifyApi(songSearch);
        if (spotifyResults.isEmpty()) {
            return null;
        }

        final Spotify.Result firstSpotifyResult = spotifyResults.get(0);

        final String selectedArtist = firstSpotifyResult.getArtistName();
        final Collection<String> otherArtistList = getOtherArtistList(spotifyResults);
        final Collection<String> selectedArtistSongs = getSongsFromEchoNest(
                filterName, firstSpotifyResult.getSongName(), selectedArtist);

        return new FilteredSearchResults(selectedArtist, selectedArtistSongs, otherArtistList);
    }

    private static List<String> getSongsFromEchoNest(String filterName, String songName, String artist) {
        List<EchoSong> filteredSongList;
        try {
            filteredSongList = new EchoNestPoller()
                    .getFilteredPopularSongs(songName, artist, filterName);
        } catch (FailedToRetrieveSongFromEchoNestException e) {
            System.out.println(e.getLocalizedMessage());
            // If the song name couldn't be retrieved from echonest, treat as no results found
            filteredSongList = new ArrayList<>();
        }
        return getSongNames(filteredSongList);
    }

    private static Set<String> getOtherArtistList(Spotify.Results spotifyResults) {
        // There will be duplicate artists; use a HashSet to avoid duplicates in the list
        Set<String> otherArtistList = new HashSet<>();

        final int size = spotifyResults.size();
        if (size > 1) {
            for (int i = 1; i < size; i++) {
                otherArtistList.add(spotifyResults.get(i).getArtistName());
            }
        }

        // There may be duplicates of the "selectedArtist". Remove those.
        otherArtistList.remove(spotifyResults.get(0).getArtistName());

        return otherArtistList;
    }

    private String decodeUrlSpaces(String s) {
        return s.replace('+', ' ');
    }

    private static List<String> getSongNames(List<EchoSong> echoSongList) {
        List<String> songNames = new ArrayList<String>();
        for (EchoSong song : echoSongList) {
            songNames.add(song.getTitle());
        }
        return songNames;
    }
}

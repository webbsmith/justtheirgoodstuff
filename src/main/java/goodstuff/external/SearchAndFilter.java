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
    public static FilteredSearchResults searchAndFilter(String songSearch, String filterName, String selectedArtist) {
        final Spotify.Results spotifyResults = new Spotify().searchSpotifyApi(songSearch);
        if (spotifyResults.isEmpty()) {
            return null;
        }

        final Spotify.Result firstSpotifyResult = spotifyResults.get(0);

        final String artistToSearchFor;
        if (selectedArtist != null) {
            artistToSearchFor = selectedArtist;
        } else {
            artistToSearchFor = firstSpotifyResult.getArtistName();
        }
        final Collection<String> otherArtistList = getOtherArtistList(convertSpaces(artistToSearchFor), spotifyResults);
        final Collection<String> selectedArtistSongs = getSongsFromEchoNest(
                filterName, firstSpotifyResult.getSongName(), artistToSearchFor);

        return new FilteredSearchResults(artistToSearchFor, selectedArtistSongs, otherArtistList);
    }

    private static List<String> getSongsFromEchoNest(String filterName, String songName, String artist) {
        artist = cleanUpArtist(artist);
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

    /** Get rid of &s, may need to add others... */
    private static String cleanUpArtist(String artist) {
        return artist.replaceAll("&", "");
    }

    private static Set<String> getOtherArtistList(String artistToDisplay, Spotify.Results spotifyResults) {
        // There will be duplicate artists; use a HashSet to avoid duplicates in the list
        Set<String> otherArtistList = new HashSet<>();

        final int size = spotifyResults.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                otherArtistList.add(spotifyResults.get(i).getArtistName());
            }
        }

        // Remove the artist to display from the other artists list
        otherArtistList.remove(artistToDisplay);

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

    private static String convertSpaces(String s) {
        return s.replace(' ', '+');
    }
}

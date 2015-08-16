package goodstuff.external.echonest;

import goodstuff.external.echonest.pojo.EchoReply;
import goodstuff.external.echonest.pojo.EchoSong;
import goodstuff.songfilter.SongFilterType;
import goodstuff.songfilter.SongFilterer;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by webb on 8/15/15.
 */
public class EchoNestPoller {

    public List<EchoSong> getFilteredPopularSongs(String songName, String artistName, String filterName)
            throws FailedToRetrieveSongFromEchoNestException {

        List<EchoSong> echoSongs = pollEchoNestForArtist(artistName);

        try {
            new EchoSongListUtility().getSongIndex(songName, echoSongs);
        } catch (FailedToRetrieveSongFromEchoNestException e) {
            echoSongs.add(pollForSongName(artistName, songName));
        }

        return filterSongList(
                echoSongs,
                songName,
                SongFilterType.toSongFilterType(filterName)
        );
    }

    private List<EchoSong> pollEchoNestForArtist(String artistName) {
        List<EchoSong> echoSongs = new ArrayList<EchoSong>();
        int startingIndex = 0;
        echoSongs.addAll(get100SongsByArtist(artistName, startingIndex));

        if (echoSongs.size() == 100) { // Get the next 100 songs
            echoSongs.addAll(get100SongsByArtist(artistName, startingIndex + 100));
        }

        return echoSongs;
    }

    private EchoSong pollForSongName(String artistName, String songName)
            throws FailedToRetrieveSongFromEchoNestException {
        EchoReply echoReply = new RestTemplate().getForObject(
                getSongAndArtistSearchUrl(artistName, songName),
                EchoReply.class);
        return new EchoSongListUtility().getSong(songName, echoReply.getSongsFromResponse());
    }

    private List<EchoSong> get100SongsByArtist(String artistName, int startingIndex) {
        EchoReply echoReply = new RestTemplate().getForObject(
                getArtistSearchUrl(artistName, startingIndex),
                EchoReply.class);
        return echoReply.getSongsFromResponse();
    }

    private String getArtistSearchUrl(String artistName, int searchIndex) {
        String url = "http://developer.echonest.com/api/v4/song/search?api_key=IRQFDNLAMR8ZPGXYQ&artist=" +
                artistName +
                "&format=json&start=" + searchIndex +
                "&results=100&sort=song_hotttnesss-desc&bucket=audio_summary";
        System.out.println("EchoNest URL: " + url);
        return url;
    }

    private String getSongAndArtistSearchUrl(String artistName, String songName) {
        int searchIndex = 0;
        String url = "http://developer.echonest.com/api/v4/song/search?api_key=IRQFDNLAMR8ZPGXYQ&artist=" +
                artistName +
                "&title=" + songName.replaceAll(" ", "+") +
                "&format=json&start=" + searchIndex +
                "&results=100&sort=song_hotttnesss-desc&bucket=audio_summary";
        System.out.println("EchoNest URL: " + url);
        return url;
    }

    private List<EchoSong> filterSongList(
            List<EchoSong> songList, String songName, SongFilterType filterType) {
        return SongFilterer.filterSongList(songList, songName, filterType);
    }
}

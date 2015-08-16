package goodstuff.external.echonest;

import goodstuff.external.echonest.pojo.EchoReply;
import goodstuff.external.echonest.pojo.EchoSong;
import goodstuff.external.spotify.Spotify;
import goodstuff.songfilter.SongFilterType;
import goodstuff.songfilter.SongFilterer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by webb on 8/15/15.
 */
public class EchoNest {

    public List<EchoSong> getFilteredPopularSongs(String songName, String artistName, String filterName) {
        EchoReply echoReply = new RestTemplate().getForObject(
                "http://developer.echonest.com/api/v4/song/search?api_key=IRQFDNLAMR8ZPGXYQ&artist=" +
                        artistName +
                        "&format=json&start=0&results=100&sort=song_hotttnesss-desc&bucket=audio_summary",
                EchoReply.class);

        return filterSongList(
                echoReply.getSongsFromResponse(),
                songName,
                SongFilterType.toSongFilterType(filterName)
        );
    }

    private List<EchoSong> filterSongList(
            List<EchoSong> songList, String songName, SongFilterType filterType) {
        return SongFilterer.filterSongList(songList, songName, filterType);
    }
}

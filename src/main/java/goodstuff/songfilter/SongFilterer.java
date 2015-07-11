package goodstuff.songfilter;

import goodstuff.external.echonest.EchoSong;

import java.util.List;

/**
 * Created by webbs_000 on 7/11/2015.
 */
public enum SongFilterer {
    INSTANCE;

    public static List<EchoSong> filterSongList(
            List<EchoSong> songList, String songName, SongFilterType filterType) {
        SongFilter songFilter = SongFilterFactory.getFilter(filterType);
        return songFilter.filter(songList, songName);
    }
}

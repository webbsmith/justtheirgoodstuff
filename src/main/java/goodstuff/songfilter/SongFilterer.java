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
        SongFilter songFilter = new ComparatorFilter(SongComparatorFactory.getComparator(filterType));
        return songFilter.filter(songList, songName);
    }
}

package goodstuff.songfilter;

import goodstuff.external.echonest.EchoSongListUtility;
import goodstuff.external.echonest.FailedToRetrieveSongFromEchoNestException;
import goodstuff.external.echonest.pojo.EchoSong;

import java.util.*;

/**
 * Created by webbs_000 on 7/11/2015.
 */
class ComparatorFilter implements SongFilter {

    private final EchoSongListUtility echoSongListUtility = new EchoSongListUtility();
    private Comparator<EchoSong> songComparator;

    public ComparatorFilter(Comparator<EchoSong> songComparator) {
        this.songComparator = songComparator;
    }

    @Override
    public List<EchoSong> filter(List<EchoSong> songList, String songName) {

        // Remove duplicates (it's sorted by 'hotttnesss' descending so keep the first one)
        List<EchoSong> songListNoDuplicates = new ArrayList<EchoSong>(new LinkedHashSet<EchoSong>(songList));

        Collections.sort(songListNoDuplicates, songComparator);

        return echoSongListUtility.getSurroundingSongs(songName, songListNoDuplicates);
    }

}

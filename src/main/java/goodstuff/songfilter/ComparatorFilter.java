package goodstuff.songfilter;

import goodstuff.external.echonest.EchoSong;

import java.util.*;

/**
 * Created by webbs_000 on 7/11/2015.
 */
class ComparatorFilter implements SongFilter {

    private Comparator<EchoSong> songComparator;

    // number of songs to output (other than the provided song)
    private static final int NUMBER_OF_OTHER_SONGS = 14;

    public ComparatorFilter(Comparator<EchoSong> songComparator) {
        this.songComparator = songComparator;
    }

    @Override
    public List<EchoSong> filter(List<EchoSong> songList, String songName) {

        // Remove duplicates (it's sorted by 'hotttnesss' descending so keep the first one)
        List<EchoSong> songListNoDuplicates = new ArrayList<EchoSong>(new LinkedHashSet<EchoSong>(songList));

        Collections.sort(songListNoDuplicates, songComparator);

        int songIndex = getSongIndex(songName, songListNoDuplicates);

        return getSurroundingSongs(songListNoDuplicates, songIndex);
    }

    private int getSongIndex(String songName, List<EchoSong> songList) {
        for (int i = 0; i < songList.size(); i++)
            if (songName.equals(songList.get(i).getTitle())) {
                return i;
            }

        throw new RuntimeException("Failed to retrieve song index. Song name: " + songName);
    }

    private List<EchoSong> getSurroundingSongs(List<EchoSong> songList, int songIndex) {

        List<EchoSong> surroundingSongs = new ArrayList<EchoSong>();
        int firstSongIndex = songIndex - NUMBER_OF_OTHER_SONGS/2;
        int lastSongIndex = songIndex + NUMBER_OF_OTHER_SONGS/2;

        for (int i = firstSongIndex > 0 ? firstSongIndex : 0; i <= lastSongIndex; i++) {
            try {
                surroundingSongs.add(songList.get(i));
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }

        return surroundingSongs;
    }
}

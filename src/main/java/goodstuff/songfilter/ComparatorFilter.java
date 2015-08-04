package goodstuff.songfilter;

import goodstuff.external.echonest.EchoSong;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        try {
            return getSongIndexExactMatch(songName, songList);
        } catch (Exception e) {
            return getSongIndexPartialMatch(songName, songList);
        }
    }

    private int getSongIndexExactMatch(String songName, List<EchoSong> songList) throws Exception {
        for (int i = 0; i < songList.size(); i++) {
            if (songName.equals(songList.get(i).getTitle())) {
                return i;
            }
        }
        throw new Exception("Failed to retrieve exact song name match from song list. Song name: " + songName);
    }

    private int getSongIndexPartialMatch(String songName, List<EchoSong> songList) {
        final Pattern firstWordInSongPattern = Pattern.compile(".*" + songName.split(" ")[0] + ".*", Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < songList.size(); i++) {
            Matcher matcher = firstWordInSongPattern.matcher(songList.get(i).getTitle());
            if (matcher.matches()) {
                return i;
            }
        }
        final Pattern secondWordInSongPattern = Pattern.compile(songName.split(".*[ :,\\.\\-_]+.*")[0], Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < songList.size(); i++) {
            Matcher matcher = secondWordInSongPattern.matcher(songList.get(i).getTitle());
            if (matcher.matches()) {
                return i;
            }
        }
        throw new RuntimeException("Failed to retrieve song name match from song list. Song name: " + songName);
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

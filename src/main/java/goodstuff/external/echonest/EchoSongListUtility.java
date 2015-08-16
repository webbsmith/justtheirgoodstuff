package goodstuff.external.echonest;

import goodstuff.external.echonest.pojo.EchoSong;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoSongListUtility {

    // number of songs to output (other than the provided song)
    private static final int NUMBER_OF_OTHER_SONGS = 14;

    public EchoSongListUtility() {
    }

    public EchoSong getSong(String songName, List<EchoSong> songList)
            throws FailedToRetrieveSongFromEchoNestException {
        return songList.get(getSongIndex(songName, songList));
    }

    public int getSongIndex(String songName, List<EchoSong> songList)
            throws FailedToRetrieveSongFromEchoNestException {
        try {
            return getSongIndexExactMatch(songName, songList);
        } catch (FailedToRetrieveSongFromEchoNestException e) {
            return getSongIndexPartialMatch(songName, songList);
        }
    }

    private int getSongIndexExactMatch(String songName, List<EchoSong> songList) throws FailedToRetrieveSongFromEchoNestException {
        for (int i = 0; i < songList.size(); i++) {
            if (songName.equals(songList.get(i).getTitle())) {
                return i;
            }
        }
        throw new FailedToRetrieveSongFromEchoNestException("Failed to retrieve exact song name match from song list. Song name: " + songName);
    }

    private int getSongIndexPartialMatch(String songName, List<EchoSong> songList) throws FailedToRetrieveSongFromEchoNestException {

        String[] songNameSplit = songName.split("[ :,\\.\\-_]+");

        final Pattern firstWordInSongPattern = Pattern.compile(".*" + songNameSplit[0] + ".*", Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < songList.size(); i++) {
            Matcher matcher = firstWordInSongPattern.matcher(songList.get(i).getTitle());
            if (matcher.matches()) {
                return i;
            }
        }
        if (songNameSplit.length > 1) {
            final Pattern secondWordInSongPattern = Pattern.compile(".*" + songNameSplit[1] + ".*", Pattern.CASE_INSENSITIVE);
            for (int i = 0; i < songList.size(); i++) {
                Matcher matcher = secondWordInSongPattern.matcher(songList.get(i).getTitle());
                if (matcher.matches()) {
                    return i;
                }
            }
        }
        throw new FailedToRetrieveSongFromEchoNestException("Failed to retrieve song name match from song list. Song name: " + songName);
    }

    public List<EchoSong> getSurroundingSongs(String songName, List<EchoSong> songList) {

        int songIndex;
        try {
            songIndex = getSongIndex(songName, songList);
        } catch (FailedToRetrieveSongFromEchoNestException e) {
            // Ok to catch the exception here since the song name will have already
            // been added to the song list // TODO - evaluate better way to handle this
            e.printStackTrace();
            songIndex = 0;
        }

        List<EchoSong> surroundingSongs = new ArrayList<EchoSong>();
        int firstSongIndex = songIndex - NUMBER_OF_OTHER_SONGS / 2;
        int lastSongIndex = songIndex + NUMBER_OF_OTHER_SONGS / 2;

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
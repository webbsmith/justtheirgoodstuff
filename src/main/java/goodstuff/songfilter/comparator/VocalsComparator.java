package goodstuff.songfilter.comparator;

import goodstuff.external.echonest.pojo.EchoSong;

import java.util.Comparator;

/**
 * Created by webb on 8/3/15.
 */
class VocalsComparator implements Comparator<EchoSong> {
    @Override
    public int compare(EchoSong o1, EchoSong o2) {
        return Double.compare(o1.getAudio_summary().getSpeechiness(), o2.getAudio_summary().getSpeechiness());
    }
}

package goodstuff.songfilter;

import goodstuff.external.echonest.EchoSong;

import java.util.Comparator;

/**
 * Created by webbs_000 on 7/11/2015.
 */
public class TempoComparator implements Comparator<EchoSong> {
    @Override
    public int compare(EchoSong o1, EchoSong o2) {
        return Double.compare(o1.getAudio_summary().getTempo(), o2.getAudio_summary().getTempo());
    }
}

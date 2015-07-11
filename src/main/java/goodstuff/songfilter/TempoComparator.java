package goodstuff.songfilter;

import goodstuff.external.echonest.EchoSong;

import java.util.Comparator;

/**
 * Created by webbs_000 on 7/11/2015.
 */
public class TempoComparator implements Comparator<EchoSong> {
    @Override
    public int compare(EchoSong o1, EchoSong o2) {
        return compare(o1.getAudio_summary().getTempo(), o2.getAudio_summary().getTempo());
    }

    private static int compare(double d1, double d2) {
        if (d1 < d2)
            return -1;
        else if (d1 > d2)
            return 1;
        else
            return 0;
    }
}

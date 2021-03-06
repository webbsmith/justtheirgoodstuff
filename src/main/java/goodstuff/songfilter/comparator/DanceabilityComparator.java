package goodstuff.songfilter.comparator;

import goodstuff.external.echonest.pojo.EchoSong;

import java.util.Comparator;

/**
 * Created by webbs_000 on 7/25/2015.
 */
class DanceabilityComparator implements Comparator<EchoSong> {
    @Override
    public int compare(EchoSong o1, EchoSong o2) {
        return Double.compare(o1.getAudio_summary().getDanceability(), o2.getAudio_summary().getDanceability());
    }
}

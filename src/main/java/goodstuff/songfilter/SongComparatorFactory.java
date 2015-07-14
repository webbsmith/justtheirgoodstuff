package goodstuff.songfilter;

import goodstuff.external.echonest.EchoSong;

import java.util.Comparator;

/**
 * Created by webbs_000 on 7/11/2015.
 */
class SongComparatorFactory {
    public static Comparator<EchoSong> getComparator(SongFilterType filterType) {
        switch (filterType) {
            case TEMPO:
                return new TempoComparator();
            default:
                return new TempoComparator(); // TODO - remove default
        }
    }
}

package goodstuff.songfilter.comparator;

import goodstuff.external.echonest.EchoSong;
import goodstuff.songfilter.SongFilterType;

import java.util.Comparator;

/**
 * Created by webbs_000 on 7/11/2015.
 */
public class SongComparatorFactory {
    public static Comparator<EchoSong> getComparator(SongFilterType filterType) {
        switch (filterType) {
            case TEMPO:
                return new TempoComparator();
            case DANCEABILITY:
                return new DanceabilityComparator();
            default:
                throw new IllegalArgumentException("Filter type '" + filterType +
                        "' not defined in SongComparatorFactory");
        }
    }
}

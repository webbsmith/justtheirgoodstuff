package goodstuff.songfilter;

/**
 * Created by webbs_000 on 7/11/2015.
 */
class SongFilterFactory {
    public static SongFilter getFilter(SongFilterType filterType) {
        switch (filterType) {
            case TEMPO:
                return new TempoFilter();
            default:
                return new TempoFilter(); // TODO - remove default
        }
    }
}

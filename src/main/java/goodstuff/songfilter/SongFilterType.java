package goodstuff.songfilter;

/**
 * Created by webbs_000 on 7/11/2015.
 */
public enum SongFilterType {
    TEMPO, DANCEABILITY, VOCALS, CATCHINESS;

    public static SongFilterType toSongFilterType(String type) {
        for (SongFilterType songFilterType : SongFilterType.values())
            if (songFilterType.name().equalsIgnoreCase(type))
                return songFilterType;

        throw new IllegalArgumentException("Type '" + type + "' is undefined in the SongFilterType enum");
    }
}

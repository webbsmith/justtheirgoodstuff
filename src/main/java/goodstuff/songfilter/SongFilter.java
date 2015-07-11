package goodstuff.songfilter;

import goodstuff.external.echonest.EchoSong;

import java.util.List;

/**
 * Created by webbs_000 on 7/11/2015.
 */
interface SongFilter {
    List<EchoSong> filter(List<EchoSong> songList, String songName);
}

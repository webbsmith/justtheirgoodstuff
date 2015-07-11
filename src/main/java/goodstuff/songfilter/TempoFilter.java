package goodstuff.songfilter;

import goodstuff.external.echonest.EchoSong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by webbs_000 on 7/11/2015.
 */
class TempoFilter implements SongFilter {

    @Override
    public List<EchoSong> filter(List<EchoSong> songList) {

        // TODO - remove duplicates (it's sorted by hotttnesss descending so keep the first one)
        List<EchoSong> songListNoDuplicates = new ArrayList<EchoSong>(new LinkedHashSet<EchoSong>(songList));
        songList.clear();
        songList.addAll(songListNoDuplicates);

        /* Tempo calculation: // TODO - Reevaluate calculation, only return similar tempo

           remove outliers (will maybe add later)
           max = highest tempo
           min = lowest tempo
           range = max - min
           low tempo < (min + range/3) < mid tempo < (max - range/3) < high tempo

         */

        List<Double> tempoList = new ArrayList<Double>();
        String a = null;
        for (EchoSong song : songList) {
            if (song.getTitle().matches("^.*(?i)last.*$")) { //DEBUG
                System.out.println(song.getTitle());
                if (a == null)
                    a = song.getTitle();
                else
                    System.out.println(a.equalsIgnoreCase(song.getTitle()));

            }
            tempoList.add(song.getAudio_summary().getTempo());
        }
        Collections.sort(tempoList);

        double max = Collections.max(tempoList);
        double min = Collections.min(tempoList);
        double range = max - min;

        // TODO - update to only use one song list
        List<EchoSong> songList1 = new ArrayList<EchoSong>();
        List<EchoSong> songList2 = new ArrayList<EchoSong>();
        List<EchoSong> songList3 = new ArrayList<EchoSong>();

        for (EchoSong song : songList) {
            double tempo = song.getAudio_summary().getTempo();
            if (tempo < (min + range / 3))
                songList1.add(song);
            else if (tempo < (max - range / 3))
                songList2.add(song);
            else
                songList3.add(song);
        }

        return songList2;
    }
}

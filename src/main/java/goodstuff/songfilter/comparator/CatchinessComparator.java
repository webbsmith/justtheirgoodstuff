package goodstuff.songfilter.comparator;

import goodstuff.external.echonest.EchoAudioSummary;
import goodstuff.external.echonest.EchoSong;

import java.util.Comparator;

/**
 * Created by webbs_000 on 7/26/2015.
 */
class CatchinessComparator implements Comparator<EchoSong> {
    @Override
    public int compare(EchoSong o1, EchoSong o2) {
        EchoAudioSummary as1 = o1.getAudio_summary();
        EchoAudioSummary as2 = o2.getAudio_summary();

        int energyComparison = compareEnergy(as1, as2);

        if (energyComparison == 0)
            return compareMode(as1, as2);
        else
            return energyComparison;

    }

    private int compareMode(EchoAudioSummary as1, EchoAudioSummary as2) {
        return Integer.compare(as1.getMode(), as2.getMode());
    }

    private int compareEnergy(EchoAudioSummary as1, EchoAudioSummary as2) {
        int energy1 = times10(as1.getEnergy());
        int energy2 = times10(as2.getEnergy());

        return Integer.compare(energy1, energy2);
    }

    /** The energy from EchoNest is returned as a double between 0 and 1 */
    private int times10(double energy) {
        return (int)Math.round(energy * 10);
    }
}

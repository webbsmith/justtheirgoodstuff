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

        if (energyConsideredEqual(as1.getEnergy(), as2.getEnergy())) {
            return Integer.compare(as1.getMode(), as2.getMode());
        } else if (as1.getEnergy() < as2.getEnergy()) {
            return -1;
        } else {
            return 1;
        }
    }

    private boolean energyConsideredEqual(double energy1, double energy2) {
        double difference = Math.abs(energy2 - energy1);
        return difference < 0.10;
    }
}

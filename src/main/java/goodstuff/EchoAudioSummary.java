package goodstuff;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by webb on 10/25/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EchoAudioSummary {

    double acousticness;
    String analysis_url;
    String audio_md5;
    double danceability;
    double duration;
    double energy;
    double instrumentalness;
    int key;
    double liveness;
    double loudness;
    int mode;
    double speechiness;
    double tempo;
    int time_signature;
    double valence;

    @Override
    public String toString() {
        return "EchoAudioSummary{" +
                "acousticness=" + acousticness +
                ", analysis_url='" + analysis_url + '\'' +
                ", audio_md5='" + audio_md5 + '\'' +
                ", danceability=" + danceability +
                ", duration=" + duration +
                ", energy=" + energy +
                ", instrumentalness=" + instrumentalness +
                ", key=" + key +
                ", liveness=" + liveness +
                ", loudness=" + loudness +
                ", mode=" + mode +
                ", speechiness=" + speechiness +
                ", tempo=" + tempo +
                ", time_signature=" + time_signature +
                ", valence=" + valence +
                '}';
    }

    public double getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(double acousticness) {
        this.acousticness = acousticness;
    }

    public String getAnalysis_url() {
        return analysis_url;
    }

    public void setAnalysis_url(String analysis_url) {
        this.analysis_url = analysis_url;
    }

    public String getAudio_md5() {
        return audio_md5;
    }

    public void setAudio_md5(String audio_md5) {
        this.audio_md5 = audio_md5;
    }

    public double getDanceability() {
        return danceability;
    }

    public void setDanceability(double danceability) {
        this.danceability = danceability;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(double instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public double getLiveness() {
        return liveness;
    }

    public void setLiveness(double liveness) {
        this.liveness = liveness;
    }

    public double getLoudness() {
        return loudness;
    }

    public void setLoudness(double loudness) {
        this.loudness = loudness;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public double getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(double speechiness) {
        this.speechiness = speechiness;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    public int getTime_signature() {
        return time_signature;
    }

    public void setTime_signature(int time_signature) {
        this.time_signature = time_signature;
    }

    public double getValence() {
        return valence;
    }

    public void setValence(double valence) {
        this.valence = valence;
    }
}

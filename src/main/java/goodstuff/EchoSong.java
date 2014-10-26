package goodstuff;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by webb on 10/19/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EchoSong {

    String id;
    String title;
    EchoAudioSummary audio_summary;

    @Override
    public String toString() {
        return "EchoSong{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", audio_summary=" + audio_summary +
                '}';
    }

    public EchoAudioSummary getAudio_summary() {
        return audio_summary;
    }

    public void setAudio_summary(EchoAudioSummary audio_summary) {
        this.audio_summary = audio_summary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

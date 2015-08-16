package goodstuff.external.echonest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by webb on 10/19/14.
 * Container for the response from the echonest API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EchoReply {
    EchoResponse response;

    @Override
    public String toString() {
        return "EchoReply{" +
                "response=" + response +
                '}';
    }

    public List<EchoSong> getSongsFromResponse() {
        return getResponse().getSongs();
    }

    public EchoResponse getResponse() {
        return response;
    }

    public void setResponse(EchoResponse response) {
        this.response = response;
    }
}

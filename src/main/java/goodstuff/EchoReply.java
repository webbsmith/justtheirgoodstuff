package goodstuff;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by webb on 10/19/14.
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

    public EchoResponse getResponse() {
        return response;
    }

    public void setResponse(EchoResponse response) {
        this.response = response;
    }
}

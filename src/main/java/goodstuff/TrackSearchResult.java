package goodstuff;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by webb on 10/19/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackSearchResult {
    List<Track> items;
    String href;

    @Override
    public String toString() {
        return "TrackSearchResult{" +
                "items=" + items +
                ", href='" + href + '\'' +
                '}';
    }

    public List<Track> getItems() {
        return items;
    }

    public void setItems(ArrayList<Track> items) {
        this.items = items;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

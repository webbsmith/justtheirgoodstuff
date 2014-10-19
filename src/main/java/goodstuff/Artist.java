package goodstuff;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by webb on 10/19/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {
    String id;
    String name;

    @Override
    public String toString() {
        return "Artist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name.replace(' ', '+');
    }

    public void setName(String name) {
        this.name = name;
    }
}

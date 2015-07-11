package goodstuff.web;

import com.google.gson.Gson;
import goodstuff.external.echonest.EchoReply;
import goodstuff.external.echonest.EchoSong;
import goodstuff.songfilter.SongFilterType;
import goodstuff.songfilter.SongFilterer;
import goodstuff.external.spotify.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * Created by webb on 10/18/14.
 */
@Controller
public class WebController {

    @RequestMapping(value="/justtheirgoodstuff", method=RequestMethod.GET)
    public String goodStuffForm(Model model) {
        model.addAttribute("formFields", new FormFields());
        return "justtheirgoodstuff"; // Maps to resources/templates/justtheirgoodstuff.html (because of thymeleaf)
    }

    @RequestMapping(value="/justtheirgoodstuff", method=RequestMethod.POST)
    public String goodStuffFormSubmit(@ModelAttribute FormFields formFields, Model model) {

        String json = new Gson().toJson(formFields);

        RestTemplate restTemplate = new RestTemplate();
        Page page = restTemplate.getForObject(
                "https://api.spotify.com/v1/search?query=" +formFields.getSearch() +
                        "&offset=0&limit=1&type=track", Page.class);
        String artistName = getArtistName(page);

        EchoReply echoReply = restTemplate.getForObject(
                "http://developer.echonest.com/api/v4/song/search?api_key=IRQFDNLAMR8ZPGXYQ&artist=" +
                         artistName +
                        "&format=json&start=0&results=100&sort=song_hotttnesss-desc&bucket=audio_summary",
                EchoReply.class);

        List<EchoSong> filteredSongList = filterSongList(
                echoReply.getSongsFromResponse(), SongFilterType.TEMPO);

        formFields.setSongs(filteredSongList);
        formFields.setArtist(artistName.replace('+',' '));

        try {
            System.out.println("Result from spotify API: " + page);
            System.out.println("Artist Name: " + artistName + "\nArtist ID: " + getArtistId(page));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("formFields", formFields);
        return "justtheirgoodstuff";
    }

    private List<EchoSong> filterSongList(List<EchoSong> songList, SongFilterType filterType) {
        return SongFilterer.filterSongList(songList, filterType);
    }

    private String getArtistId(Page page) {
        return page.getTracks().getItems().get(0).getArtists().get(0).getId();
    }

    private String getArtistName(Page page) {
        return page.getTracks().getItems().get(0).getArtists().get(0).getName();
    }



}


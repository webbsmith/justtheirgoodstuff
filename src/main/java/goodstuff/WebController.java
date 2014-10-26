package goodstuff;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
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
        Page page = restTemplate.getForObject("https://api.spotify.com/v1/search?query=" + formFields.getSearch() + "&offset=0&limit=1&type=track", Page.class);
        //EchoReply echoReply = restTemplate.getForObject("http://developer.echonest.com/api/v4/artist/songs?api_key=IRQFDNLAMR8ZPGXYQ&id=spotify:artist:" + getArtistId(page) + "&format=json&start=0&results=100", EchoReply.class);
        EchoReply echoReply = restTemplate.getForObject("http://developer.echonest.com/api/v4/song/search?api_key=IRQFDNLAMR8ZPGXYQ&artist=" + getArtistName(page) + "&format=json&start=0&results=100&sort=song_hotttnesss-desc&bucket=audio_summary", EchoReply.class);
        // TODO - create while loop to page through all results (only 100 can be returned at a time)

        List<EchoSong> songList = echoReply.getResponse().getSongs();

        /* Tempo calculation:

           remove outliers (will maybe add later)
           max = highest tempo
           min = lowest tempo
           range = max - min
           low tempo < (min + range/3) < mid tempo < (max - range/3) < high tempo

         */

        List<Double> tempoList = new ArrayList<Double>();
        for (EchoSong song : songList) {
            tempoList.add(song.getAudio_summary().getTempo());
        }
        Collections.sort(tempoList);

        double max = Collections.max(tempoList);
        double min = Collections.min(tempoList);
        double range = max - min;

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

        formFields.setSongs1(songList1);
        formFields.setSongs2(songList2);
        formFields.setSongs3(songList3);

        formFields.setArtist(getArtistName(page).replace('+',' '));

        try {
            System.out.println("Result from spotify API: " + page);
            System.out.println("Artist Name: " + getArtistName(page) + "\nArtist ID: " + getArtistId(page));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("formFields", formFields);
        return "justtheirgoodstuff";
    }

    private String getArtistId(Page page) {
        return page.getTracks().getItems().get(0).getArtists().get(0).getId();
    }

    private String getArtistName(Page page) {
        return page.getTracks().getItems().get(0).getArtists().get(0).getName();
    }



}


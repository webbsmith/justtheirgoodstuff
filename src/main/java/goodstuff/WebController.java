package goodstuff;

import com.google.gson.Gson;
import org.json.JSONObject;
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
        Page page = restTemplate.getForObject("https://api.spotify.com/v1/search?query=" + formFields.getSearch() + "&offset=0&limit=1&type=track", Page.class);
        //EchoReply echoReply = restTemplate.getForObject("http://developer.echonest.com/api/v4/artist/songs?api_key=IRQFDNLAMR8ZPGXYQ&id=spotify:artist:" + getArtistId(page) + "&format=json&start=0&results=100", EchoReply.class);
        EchoReply echoReply = restTemplate.getForObject("http://developer.echonest.com/api/v4/song/search?api_key=IRQFDNLAMR8ZPGXYQ&artist=" + getArtistName(page) + "&format=json&start=0&results=99&sort=tempo-desc&bucket=audio_summary", EchoReply.class);
        for (EchoSong song : echoReply.getResponse().getSongs()) {
            System.out.println(song);
        }

        List<EchoSong> songList = echoReply.getResponse().getSongs();

        int oneThird = songList.size()/3;

        formFields.setSongs1(songList.subList(0, oneThird + 1));
        formFields.setSongs2(songList.subList(oneThird + 1, oneThird * 2));
        formFields.setSongs3(songList.subList(oneThird * 2, songList.size() - 1));

        formFields.setArtist(getArtistName(page).replace('+',' '));
        System.out.println(formFields);

        try {
            System.out.println("Result from spotify API: " + page);
            System.out.println("Artist Name: " + getArtistName(page) + "\nArtist ID: " + getArtistId(page));
            System.out.println("The post worked! JSON: " + json);

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


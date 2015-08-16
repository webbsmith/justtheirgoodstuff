package goodstuff.web;

import goodstuff.external.echonest.pojo.EchoReply;
import goodstuff.external.echonest.pojo.EchoSong;
import goodstuff.external.spotify.Spotify;
import goodstuff.songfilter.SongFilterType;
import goodstuff.songfilter.SongFilterer;
import goodstuff.external.spotify.pojo.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * WebController - Maps HTTP requests
 * Automatically discovered by Spring's ComponentScan in Application.java
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

        String songSearch = formFields.getSearch();

        Spotify.Result spotifyResult = new Spotify().searchSpotifyApi(songSearch);

        if (spotifyResult.isEmpty()) {
            formFields.setSuccess(false);
            formFields.setErrorMessage("No results found");
            model.addAttribute("formFields", formFields);
            return "justtheirgoodstuff";
        }

        EchoReply echoReply = new RestTemplate().getForObject(
                "http://developer.echonest.com/api/v4/song/search?api_key=IRQFDNLAMR8ZPGXYQ&artist=" +
                         spotifyResult.getArtistName() +
                        "&format=json&start=0&results=100&sort=song_hotttnesss-desc&bucket=audio_summary",
                EchoReply.class);

        List<EchoSong> filteredSongList = filterSongList(
                echoReply.getSongsFromResponse(),
                spotifyResult.getSongName(),
                SongFilterType.toSongFilterType(formFields.getLikeAboutIt())
        );

        formFields.setSongs(filteredSongList);
        formFields.setArtist(spotifyResult.getArtistName().replace('+',' '));
        formFields.setSuccess(true);
        model.addAttribute("formFields", formFields);

        return "justtheirgoodstuff";
    }

    private List<EchoSong> filterSongList(
            List<EchoSong> songList, String songName, SongFilterType filterType) {
        return SongFilterer.filterSongList(songList, songName, filterType);
    }


}


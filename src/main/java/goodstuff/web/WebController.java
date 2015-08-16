package goodstuff.web;

import goodstuff.external.FilteredSearchResults;
import goodstuff.external.SearchAndFilter;
import goodstuff.external.echonest.EchoNest;
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

    private static String HTML_PAGE = "justtheirgoodstuff";

    @RequestMapping(value="/justtheirgoodstuff", method=RequestMethod.GET)
    public String goodStuffForm(Model model) {
        model.addAttribute("formFields", new FormFields());
        return HTML_PAGE; // Maps to resources/templates/justtheirgoodstuff.html (because of thymeleaf)
    }

    @RequestMapping(value="/justtheirgoodstuff", method=RequestMethod.POST)
    public String goodStuffFormSubmit(@ModelAttribute FormFields formFields, Model model) {

        String songSearch = formFields.getSearch();

        FilteredSearchResults searchResults = SearchAndFilter.searchAndFilter(songSearch, formFields.getLikeAboutIt());

        if (searchResults.isEmpty()) {
            formFields.setSuccess(false);
            formFields.setErrorMessage("No results found");
            model.addAttribute("formFields", formFields);
            return HTML_PAGE;
        }

        formFields.setSongs(searchResults.getSongNames());
        formFields.setArtist(searchResults.getArtistName().replace('+',' '));
        formFields.setSuccess(true);
        model.addAttribute("formFields", formFields);

        return HTML_PAGE;
    }
}


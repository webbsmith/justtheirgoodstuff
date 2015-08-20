package goodstuff.web;

import goodstuff.external.FilteredSearchResults;
import goodstuff.external.SearchAndFilter;
import goodstuff.external.SearchResultHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
        System.out.println(formFields);

        String songSearch = formFields.getSearch();

        final SearchResultHolder.Key searchResultKey = SearchResultHolder.makeKey(songSearch, formFields.getArtist(), formFields.getLikeAboutIt());
        final SearchResultHolder searchResultHolder = formFields.getSearchResultHolder(); // TODO - why isn't this persisting through submits? it's getting re-instantiatied every time

        FilteredSearchResults searchResults = searchResultHolder.get(searchResultKey);

        if (searchResults == null) {
            searchResults = SearchAndFilter.searchAndFilter(songSearch, formFields.getLikeAboutIt());
            searchResultHolder.put(searchResultKey, searchResults);
        }

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


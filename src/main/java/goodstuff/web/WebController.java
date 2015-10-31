package goodstuff.web;

import goodstuff.external.FilteredSearchResults;
import goodstuff.external.SearchAndFilter;
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
        System.out.println("DEBUG - formFields: " + formFields);

        String songSearch = formFields.getSearch();

        FilteredSearchResults searchResults = SearchAndFilter.searchAndFilter(
                songSearch, formFields.getLikeAboutIt(), formFields.getSelectedArtist());
        System.out.println("DEBUG - searchResults: " + searchResults);

        formFields.updateWithSearchResults(searchResults);

        model.addAttribute("formFields", formFields);
        return HTML_PAGE;
    }
}


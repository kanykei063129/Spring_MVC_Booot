package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Agency;
import peaksoft.service.AgencyService;
@Controller
@RequestMapping("/agencies")
@RequiredArgsConstructor
public class AgencyApi {
    private final AgencyService agencyService;

    @GetMapping
    public String getAllAgencies(Model model) {
        model.addAttribute("agencies", agencyService.getAllAgencies());
        return "agency/agency";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("agency", agencyService.getAgencyById(id));
        return "agency/agencyPage";
    }

    @GetMapping("/new")
    public String createAgency(Model model) {
        model.addAttribute("newAgency", new Agency());
        return "agency/newAgency";
    }

    @PostMapping("/save")
    public String saveAgency(@ModelAttribute("newAgency") Agency agency) {
        agencyService.saveAgency(agency);
        return "redirect:/agencies";
    }

    @GetMapping("/{id}/edit")
    public String updateAgency(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editAgency", agencyService.getAgencyById(id));
        return "agency/updateAgency";
    }

    @PostMapping("/updateAgency/{id}")
    public String saveUpdate(@ModelAttribute("editAgency") Agency agency, @PathVariable("id") Long id) {
        agencyService.updateAgency(id, agency);
        return "redirect:/agencies";
    }

    @GetMapping("/{id}/delete")
    public String deleteAgency(@PathVariable("id") Long id) {
        agencyService.deleteAgencyById(id);
        return "redirect:/agencies";
    }

    @GetMapping("/info")
    public String getAllAgencyHouse(Model model) {
        model.addAttribute("agencyHouse", agencyService.getAllHousesToAgency());
        return "agency/agencyInfo";
    }

    @GetMapping("/search")
    public String searchAgency(@RequestParam("word") String word, Model model) {
        model.addAttribute("agencies", agencyService.searchAgency(word));
        return "agency/searchAgency";
    }
}

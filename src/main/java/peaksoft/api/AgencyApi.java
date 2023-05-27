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
        return "agency";
    }

    @GetMapping("/new")
    public String createAgency(Model model) {
        model.addAttribute("newAgency", new Agency());
        return "newAgency";
    }

    @PostMapping("/save")
    public String saveAgency(@ModelAttribute("newAgency") Agency agency) {
        agencyService.saveAgency(agency);
        return "redirect:/agencies";
    }

    @GetMapping("/{id}/getAgency")
    public String getAgencyToUpdateById(@PathVariable Long id, Model model) {
        model.addAttribute("agency", agencyService.getAgencyById(id));
        return "updateAgency";
    }

//    @GetMapping("/{email}/getAgency")
//    public String getAgencyToUpdateByEmail(@PathVariable String email, Model model){
//        model.addAttribute("user",userService.getUserByEmail(email));
//        return "updateAgency";
//    }

    @PostMapping("/{id}/update")
    public String updateAgency(@PathVariable("id") Long id,
                             @ModelAttribute("agency") Agency agency) {
        agencyService.updateAgency(id, agency);
        return "redirect:/agencies";
    }

    @GetMapping("/{id}/delete")
    public String deleteAgency(@PathVariable Long id) {
        agencyService.deleteAgencyById(id);
        return "redirect:/agencies";
    }
}

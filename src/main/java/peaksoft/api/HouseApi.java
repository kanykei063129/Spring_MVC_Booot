package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Customer;
import peaksoft.entity.House;
import peaksoft.service.AgencyService;
import peaksoft.service.HouseService;

@Controller
@RequestMapping("/houses")
@RequiredArgsConstructor
public class HouseApi {
    private final HouseService houseService;
    private final AgencyService agencyService;

    @GetMapping
    public String getAllHouses(Model model) {
        model.addAttribute("houses", houseService.getAllHouses());
        return "house/house";
    }

    @GetMapping("/house/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("house", houseService.getHouseById(id));
        return "house/infoHouse";
    }

    @GetMapping("/createHouse")
    public String createHouse(Model model) {
        model.addAttribute("house", new House());
        model.addAttribute("agencies", agencyService.getAllAgencies());
        return "house/newHouse";
    }

    @PostMapping("/saveHouse")
    public String saveHouse(@ModelAttribute("house") House house,
                            @RequestParam("agencyName") Long agencyId) {
        houseService.saveHouse(agencyId, house);
        return "redirect:/houses";
    }


    @GetMapping("/update/{id}")
    public String updateHouse(@PathVariable("id") Long id, Model model) {
            model.addAttribute("houses", houseService.getHouseById(id));
            model.addAttribute("agencies", agencyService.getAllAgencies());
            return "house/editHouse";
        }
        @PostMapping("/saveUpdate/{id}")
        public String saveUpdate (@ModelAttribute("house") House house, @PathVariable("id") Long id){
            houseService.updateHouseById(id, house);
            return "redirect:/houses";
        }

        @GetMapping("/{id}/delete")
        public String deleteHouse (@PathVariable("id") Long id){
            houseService.deleteHouseById(id);
            return "redirect:/houses";
        }
        @GetMapping("/sort")
        public String getAllSortedHouse (Model model, @RequestParam("sort") String word){
            model.addAttribute("houses", houseService.getAllSortedHouse(word));
            return "house/sortHouse";
        }
    }

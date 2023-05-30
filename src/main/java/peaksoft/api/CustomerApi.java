package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Agency;
import peaksoft.entity.Customer;
import peaksoft.service.AgencyService;
import peaksoft.service.CustomerService;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerApi {
    private final CustomerService customerService;
    private final AgencyService agencyService;
    @GetMapping
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customer/customer";
    }
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer/customerPage";
    }
    @GetMapping("/newCustomer")
    public String createCustomer(Model model) {
        model.addAttribute("newCustomer", new Customer());
        return "customer/newCustomer";
    }
    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("newCustomer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String updateCustomer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editCustomer", customerService.getCustomerById(id));
        return "customer/updateCustomer";
    }

    @PostMapping("/updateCustomer/{id}")
    public String saveUpdate(@ModelAttribute("editCustomer") Customer customer, @PathVariable("id") Long id) {
        customerService.updateCustomerById(id, customer);
        return "redirect:/customers";
    }
    @GetMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }
    @GetMapping("/assign/{id}")
    public  String assignCustomerToAgency(@PathVariable Long id,Model model){
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customers",customer);
        model.addAttribute("agency",agencyService.getAllAgencies());
        return "customer/customerAssign";
    }
    @PostMapping("/saveAssign/{id}")
    public String saveAssign(@RequestParam("agencyName") Long agencyId,
                             @PathVariable Long id){
        customerService.assignCustomerToAgency(id,agencyId);
        return "redirect:/customers";
    }
}

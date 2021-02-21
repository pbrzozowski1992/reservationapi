package pl.sdaacademy.reservationapi.organization;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @PostMapping
    public Organization addOrganization(@RequestBody Organization organization) {
        return organizationService.addOrganization(organization);
    }

    //@RequestParam -> parametr żądania: localhost:8080/organizations?id=Intive: filtrowanie, sortowanie, paginacja
    //@PathVariable -> parametr ścieżki: localhost:8080/organizations/Intive: do zwracania unikalnego elementu z zbioru
    @DeleteMapping("/{id}")
    public Organization removeOrganization(@PathVariable String id) {
        return organizationService.removeOrganization(id);
    }
}

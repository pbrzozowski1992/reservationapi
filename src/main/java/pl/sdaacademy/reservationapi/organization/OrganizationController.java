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
    public List<OrganizationDTO> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @GetMapping("/{id}")
    public OrganizationDTO getOrganizationById(@PathVariable String id) {
        return organizationService.getOrganizationById(id);
    }

    @PostMapping
    public OrganizationDTO addOrganization(@RequestBody OrganizationDTO organization) {
        return organizationService.addOrganization(organization);
    }

    @DeleteMapping("/{id}")
    public OrganizationDTO removeOrganization(@PathVariable String id) {
        return organizationService.removeOrganization(id);
    }

    @PutMapping("/{id}")
    public OrganizationDTO updateOrganization(@PathVariable String id, @RequestBody OrganizationDTO organization) {
        return organizationService.updateOrganization(id, organization);
    }
}

package pl.sdaacademy.reservationapi.organization;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.sdaacademy.reservationapi.validation.Add;
import pl.sdaacademy.reservationapi.validation.Update;

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
    public OrganizationDTO addOrganization(@Validated(Add.class) @RequestBody OrganizationDTO organization) {
        return organizationService.addOrganization(organization);
    }

    @PutMapping("/{name}")
    public OrganizationDTO updateOrganization(@PathVariable String name, @Validated(Update.class) @RequestBody OrganizationDTO organization) {
        return organizationService.updateOrganization(name, organization);
    }

    @DeleteMapping("/{name}")
    public OrganizationDTO removeOrganization(@PathVariable String name) {
        return organizationService.removeOrganization(name);
    }

}

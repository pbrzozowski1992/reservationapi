package pl.sdaacademy.reservationapi.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization addOrganization(Organization organization) {
        //sprawdzić czy organizacja jest już w repo
        organizationRepository.findAll()
                .stream()
                .filter(org -> org.getName().equals(organization.getName()))
                .findAny()
                .ifPresent(org->{
                    throw new IllegalArgumentException("organization with provided name already exists!");
                });
        return organizationRepository.save(organization);
    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
}

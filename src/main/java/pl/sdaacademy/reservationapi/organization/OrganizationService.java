package pl.sdaacademy.reservationapi.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization addOrganization(Organization organization) {
        organizationRepository.findById(organization.getName())
                .ifPresent(org->{
                    throw new IllegalArgumentException("organization with provided name already exists!");
                });
        return organizationRepository.save(organization);
    }

    public Organization removeOrganization(String id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(()->{
                    throw new NoSuchElementException("no organization found!");
                });
        organizationRepository.delete(organization);
        return organization;
    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
}

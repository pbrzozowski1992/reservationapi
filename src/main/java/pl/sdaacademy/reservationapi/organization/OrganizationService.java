package pl.sdaacademy.reservationapi.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.reservationapi.organization.exception.NoOrganizationFoundException;
import pl.sdaacademy.reservationapi.organization.exception.OrganizationAlreadyExistsException;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization addOrganization(Organization organization) {
        final String organizationName = organization.getName();
        organizationRepository.findById(organizationName)
                .ifPresent(org -> {
                    throw new OrganizationAlreadyExistsException(organizationName);
                });
        return organizationRepository.save(organization);
    }

    public Organization removeOrganization(String id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoOrganizationFoundException(id);
                });
        organizationRepository.delete(organization);
        return organization;
    }

    public Organization updateOrganization(String id, Organization organizationUpdate) {
        Organization organizationToUpdate = organizationRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoOrganizationFoundException(id);
                });
        String newOrganizationDescription = organizationUpdate.getDescription();
        if (newOrganizationDescription != null) {
            organizationToUpdate.setDescription(newOrganizationDescription);
        }
        return organizationRepository.save(organizationToUpdate);
    }

    public Organization getOrganizationById(String id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoOrganizationFoundException(id);
                });
    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
}

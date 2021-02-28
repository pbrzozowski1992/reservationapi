package pl.sdaacademy.reservationapi.organization;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrganizationTransformer {

    private final OrganizationConferenceRoomTransformer organizationConferenceRoomTransformer;

    public OrganizationTransformer(OrganizationConferenceRoomTransformer organizationConferenceRoomTransformer) {
        this.organizationConferenceRoomTransformer = organizationConferenceRoomTransformer;
    }

    public OrganizationDTO toDTO(Organization organization) {
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setName(organization.getName());
        organizationDTO.setDescription(organization.getDescription());
        organizationDTO.setConferenceRooms(
                organization.getConferenceRooms().stream()
                        .map(organizationConferenceRoomTransformer::toDTO)
                        .collect(Collectors.toList())
        );
        return organizationDTO;
    }

    public Organization toEntity(OrganizationDTO organizationDTO) {
        Organization organization = new Organization();
        organization.setName(organizationDTO.getName());
        organization.setDescription(organizationDTO.getDescription());
        organization.setConferenceRooms(
                organizationDTO.getConferenceRooms().stream()
                        .map(organizationConferenceRoomTransformer::toEntity)
                        .collect(Collectors.toList())
        );
        return organization;
    }
}

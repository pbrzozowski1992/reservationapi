package pl.sdaacademy.reservationapi.organization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sdaacademy.reservationapi.organization.exception.NoOrganizationFoundException;
import pl.sdaacademy.reservationapi.organization.exception.OrganizationAlreadyExistsException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class OrganizationServiceTest {

    @TestConfiguration
    static class TestOrganizationServiceBeanProvider {
        @Bean
        public OrganizationService organizationService(OrganizationRepository organizationRepository) {
            return new OrganizationService(organizationRepository, new OrganizationTransformer(
                    new OrganizationConferenceRoomTransformer()));
        }
    }

    @MockBean
    OrganizationRepository organizationRepository;

    @Autowired
    OrganizationService organizationService;


    @Test
    void when_organization_org1_exists_in_repo_then_search_for_organization_by_id_should_return_it() {
        //given
        Mockito.when(organizationRepository.findById("org1"))
                .thenReturn(Optional.of(new Organization("org1", "description")));

        //when
        OrganizationDTO organization = organizationService.getOrganizationById("org1");

        //then
        assertEquals("org1", organization.getName());
        assertEquals("description", organization.getDescription());
    }

    @Test
    void when_organization_org1_does_not_exist_in_repo_then_search_for_organization_by_id_should_throw_an_exception() {
        //given
        Mockito.when(organizationRepository.findById("org2"))
                .thenReturn(Optional.of(new Organization("org2", "description")));

        //when
        //then
        assertThrows(NoOrganizationFoundException.class, ()->{
            organizationService.getOrganizationById("org1");
        });
    }

    @Test
    void when_try_to_add_organization_org1_which_is_not_exist_in_repo_then_it_should_be_added() {
        //given
        Organization organization = new Organization("org1", "organization");
        Mockito.when(organizationRepository.save(organization)).thenReturn(organization);

        //when
        OrganizationDTO addedOrganization = organizationService.addOrganization(new OrganizationDTO(organization.getName(), organization.getDescription()));

        //then
        Mockito.verify(organizationRepository).save(organization);
        assertEquals("org1", addedOrganization.getName());
        assertEquals("organization", addedOrganization.getDescription());
    }

    @Test
    void when_try_to_add_organization_org1_which_exists_in_repo_then_exception_should_be_thrown() {
        //given
        Organization organization = new Organization("org1", "organization");
        Mockito.when(organizationRepository.findById("org1")).thenReturn(Optional.of(organization));

        //when
        //then
        assertThrows(OrganizationAlreadyExistsException.class, () -> {
            organizationService.addOrganization(new OrganizationDTO(organization.getName(), organization.getDescription()));
        });
        Mockito.verify(organizationRepository, Mockito.never()).save(organization);
    }
}
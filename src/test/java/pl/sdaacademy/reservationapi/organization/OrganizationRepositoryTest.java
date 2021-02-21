package pl.sdaacademy.reservationapi.organization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class OrganizationRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    OrganizationRepository organizationRepository;

    @Test
    void when_db_is_empty_then_return_empty_organization_list() {
        //given

        //when
        List<Organization> organizationList = organizationRepository.findAll();

        //then
        assertEquals(0, organizationList.size());
    }

    @Test
    void when_db_contains_organization_with_name_org1_then_find_by_id_should_return_it() {
        //given
        Organization organization = new Organization("org1", "org1 description");
        testEntityManager.persist(organization);

        //when
        Optional<Organization> organizationOptional = organizationRepository.findById("org1");

        //then
        assertEquals("org1", organizationOptional.get().getName());
        assertEquals("org1 description", organizationOptional.get().getDescription());
    }

    @Test
    void when_db_contains_organization_with_description_contains_text_it_then_find_by_descriptions_contains_should_return_them() {
        //given
        String descriptionPart = "IT";
        testEntityManager.persist(new Organization("org1", "org"));
        testEntityManager.persist(new Organization("org2", "software house IT company"));
        testEntityManager.persist(new Organization("org3", "IT software programming"));
        testEntityManager.persist(new Organization("org4", "IT"));

        //when
        List<Organization> itOrganizations = organizationRepository.findByDescriptionContains(descriptionPart);

        //then
        assertEquals(3, itOrganizations.size());
        assertEquals("software house IT company", itOrganizations.get(0).getDescription());
        assertEquals("IT software programming", itOrganizations.get(1).getDescription());
        assertEquals("IT", itOrganizations.get(2).getDescription());
    }
}
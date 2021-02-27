package pl.sdaacademy.reservationapi.organization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.sdaacademy.reservationapi.organization.exception.NoOrganizationFoundException;
import pl.sdaacademy.reservationapi.organization.exception.OrganizationAlreadyExistsException;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrganizationController.class)
class OrganizationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrganizationService organizationService;

    @Test
    void when_there_is_no_organization_then_empty_json_array_should_be_returned() throws Exception {
        //given

        //when
        //then
        mockMvc.perform(
                get("/organizations").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void when_there_are_organizations_available_then_array_list_with_those_organizations_should_be_returned() throws Exception {
        //given
        Mockito.when(organizationService.getAllOrganizations()).thenReturn(Arrays.asList(
                new Organization("org1", "org1 description"),
                new Organization("org2", "org2 description")
        ));

        //when
        //then
        mockMvc.perform(
                get("/organizations").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", equalTo("org2")));
    }

    @Test
    void when_request_organization_with_existing_id_then_a_organization_should_be_returned_as_json_object() throws Exception {
        //given
        String id = "org_test";
        Mockito.when(organizationService.getOrganizationById(id)).thenReturn(new Organization(id, "description"));

        //when
        //then
        mockMvc.perform(
                get("/organizations/org_test").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$.name", equalTo("org_test")))
                .andExpect(jsonPath("$.description", equalTo("description")));
    }

    @Test
    void when_request_organization_with_unknown_id_then_4xx_error_code_should_be_returned() throws Exception {
        //given
        String id = "org_test";
        Mockito.when(organizationService.getOrganizationById(id)).thenThrow(new NoOrganizationFoundException(id));

        //when
        //then
        mockMvc.perform(
                get("/organizations/org_test").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError())
                .andExpect(content().string("No organization with id: org_test found!"));
    }

    @Test
    void when_send_post_request_to_add_organization_which_not_exist_in_system_then_organization_should_be_added_and_returned_as_json_object() throws Exception {
        //given
        Organization organizationToAdd = new Organization("org_to_add", "org_to_add description");
        Mockito.when(organizationService.addOrganization(organizationToAdd)).thenReturn(organizationToAdd);

        //when
        //then
        mockMvc.perform(
                post("/organizations").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"org_to_add\",\"description\":\"org_to_add description\"}")
        ).andExpect(jsonPath("$.name", equalTo("org_to_add")))
                .andExpect(jsonPath("$.description", equalTo("org_to_add description")));
    }

    @Test
    void when_send_post_request_to_add_organization_which_already_exist_in_system_then_organization_already_exits_exception_should_be_thrown() throws Exception {
        //given
        Organization organizationToAdd = new Organization("org_to_add", "org_to_add description");
        Mockito.when(organizationService.addOrganization(organizationToAdd)).thenThrow(new OrganizationAlreadyExistsException(organizationToAdd.getName()));

        //when
        //then
        mockMvc.perform(
                post("/organizations").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"org_to_add\",\"description\":\"org_to_add description\"}")
        ).andExpect(status().is4xxClientError())
                .andExpect(content().string("Organization with provided id: org_to_add already exists!"));
    }
}
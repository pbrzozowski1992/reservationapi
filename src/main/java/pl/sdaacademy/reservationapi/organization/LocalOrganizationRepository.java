package pl.sdaacademy.reservationapi.organization;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LocalOrganizationRepository implements OrganizationRepository {
    private final List<Organization> organizations = new ArrayList<>();

    @Override
    public Organization save(Organization organization) {
        organizations.add(organization);
        return organization;
    }

    @Override
    public Organization remove(String name) {
        return null;
    }

    @Override
    public List<Organization> findAll() {
        return organizations;
    }
}

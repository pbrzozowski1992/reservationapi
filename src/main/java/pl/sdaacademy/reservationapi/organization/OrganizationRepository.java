package pl.sdaacademy.reservationapi.organization;

import org.springframework.stereotype.Repository;

import java.util.List;

//TODO: replace with JpaRepository
@Repository
public interface OrganizationRepository {

    Organization save(Organization organization);

    Organization remove(String name);

    List<Organization> findAll();
}

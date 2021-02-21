package pl.sdaacademy.reservationapi.organization.exception;

public class NoOrganizationFoundException extends RuntimeException {

    public NoOrganizationFoundException(String organizationName) {
        super(String.format("No organization with id: %s found!", organizationName));
    }
}

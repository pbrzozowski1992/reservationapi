package pl.sdaacademy.reservationapi.organization.exception;

public class OrganizationAlreadyExistsException extends RuntimeException {
    public OrganizationAlreadyExistsException(String id) {
        super(String.format("Organization with provided id: %s already exists!", id));
    }
}

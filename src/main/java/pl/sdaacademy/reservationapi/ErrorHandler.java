package pl.sdaacademy.reservationapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.sdaacademy.reservationapi.organization.exception.NoOrganizationFoundException;
import pl.sdaacademy.reservationapi.organization.exception.OrganizationAlreadyExistsException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = NoOrganizationFoundException.class)
    public ResponseEntity<Object> handleNoOrganizationFoundException(NoOrganizationFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OrganizationAlreadyExistsException.class)
    public ResponseEntity<Object> handleOrganizationAlreadyExistsException(OrganizationAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

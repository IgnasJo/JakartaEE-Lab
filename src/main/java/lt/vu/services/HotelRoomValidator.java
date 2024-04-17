package lt.vu.services;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Hotel;
import lt.vu.entities.Housekeeper;
import lt.vu.entities.Room;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.Set;

@ApplicationScoped
public class HotelRoomValidator implements Serializable {
    @Getter
    @Setter
    private String message = "";

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public boolean validate(Room room) {
        return true;
//        Set<ConstraintViolation<Room>> violations = room.validateConstraints(validator);
//        if (!violations.isEmpty()) {
//            // Handle constraint violations
//            for (ConstraintViolation<Room> violation : violations) {
//                message = message.concat("Constraint Violation: " + violation.getPropertyPath() + " " + violation.getMessage());
//            }
//            return false;
//        }
//        message = "";
//        return true;
    }
}

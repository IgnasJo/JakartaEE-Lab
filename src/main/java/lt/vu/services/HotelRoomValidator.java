package lt.vu.services;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Housekeeper;
import lt.vu.entities.Room;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class HotelRoomValidator implements Serializable {
    @Getter
    @Setter
    private String message;

    public boolean validateHousekeepers(String housekeeperNames, List<Housekeeper> housekeepers) {
        for (Housekeeper housekeeper : housekeepers) {
            if (!housekeeperNames.contains(housekeeper.getName())) {
                this.message = "Invalid housekeepers. Please choose only existing housekeepers.";
                return false;
            }
        }
        this.message = "";
        return true;
    }
}

package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Hotel;
import lt.vu.entities.Housekeeper;
import lt.vu.entities.Room;
import lt.vu.persistence.HotelsDAO;
import lt.vu.persistence.HousekeepersDAO;
import lt.vu.persistence.RoomsDAO;
import lt.vu.services.HotelRoomValidator;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class HotelRooms {

    @Inject
    private HotelsDAO hotelsDAO;

    @Inject
    private RoomsDAO roomsDAO;

    @Inject
    private HousekeepersDAO housekeepersDAO;

    @Inject
    @Getter
    private HotelRoomValidator validator;

    @Getter
    @Setter
    private Hotel hotel;

    @Getter
    @Setter
    private Room roomToCreate = new Room();

    @Getter
    @Setter
    private String housekeepers;

    @Getter
    @Setter
    private List<Housekeeper> allHousekeepers;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer hotelId = Integer.parseInt(requestParameters.get("hotelId"));
        this.hotel = hotelsDAO.findOne(hotelId);
        this.roomToCreate.setHotel(this.hotel);
        this.allHousekeepers = housekeepersDAO.loadAll();
    }

    @Transactional
    public void createRoom() {
        System.out.println("CREATE ROOM WORKS");
        if (!this.validator
                .validateHousekeepers(housekeepers, this.allHousekeepers)) {
            System.out.println("INVALID HOUSEKEEPERS");
            return;
        }
        System.out.println("VALID HOUSEKEEPERS");
        for (Housekeeper housekeeper : this.allHousekeepers) {
            if (this.housekeepers.contains(housekeeper.getName())) {
                roomToCreate.getHousekeepers().add(housekeeper);
            }
        }
        this.roomsDAO.persist(this.roomToCreate);
    }
}

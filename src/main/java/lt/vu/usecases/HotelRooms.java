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

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Getter
    @Setter
    private List<String> housekeeperNames;

    @Getter
    @Setter
    private List<String> selectedHousekeeperNames = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer hotelId = Integer.parseInt(requestParameters.get("hotelId"));
        this.hotel = hotelsDAO.findOne(hotelId);
        this.roomToCreate.setHotel(this.hotel);
        this.allHousekeepers = housekeepersDAO.loadAll();
        this.housekeeperNames = allHousekeepers.stream().map(Housekeeper::getName).collect(Collectors.toList());
    }

    @Transactional
    public void createRoom() {
        System.out.println("CREATE ROOM WORKS");
        if (!this.validator
                .validate(roomToCreate)) {
            return;
        }
        for (String selectedHousekeeperName  : this.selectedHousekeeperNames) {
            Housekeeper housekeeper = this.allHousekeepers
                    .get(this.housekeeperNames.indexOf(selectedHousekeeperName));
            this.roomToCreate.getHousekeepers().add(housekeeper);
        }
        this.roomsDAO.persist(this.roomToCreate);
    }
}

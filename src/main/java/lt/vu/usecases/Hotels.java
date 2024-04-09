package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Hotel;
import lt.vu.persistence.HotelsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Model
public class Hotels {

    @Inject
    private HotelsDAO hotelsDAO;

    @Getter
    @Setter
    private Hotel hotelToCreate = new Hotel();

    @Getter
    private List<Hotel> allHotels;

    @Getter
    @Setter
    @NotNull
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating must be at most 10")
    private Integer rating;


    @PostConstruct
    public void init(){
        loadAllHotels();
    }

    @Transactional
    public void createHotel(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.isValidationFailed()) {
            // Prevent form submission
            return;
        }
        this.hotelToCreate.setRating(this.getRating());
        this.hotelsDAO.persist(hotelToCreate);
    }

    private void loadAllHotels(){
        this.allHotels = hotelsDAO.loadAll();
    }
}

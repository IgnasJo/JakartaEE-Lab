package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Housekeeper;
import lt.vu.persistence.HousekeepersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Housekeepers {
    @Inject
    private HousekeepersDAO housekeepersDAO;

    @Getter
    @Setter
    private Housekeeper housekeeperToCreate = new Housekeeper();

    @Getter
    private List<Housekeeper> allHousekeepers;

    @PostConstruct
    public void init(){
        loadAllHousekeepers();
    }

    @Transactional
    public void createHousekeeper(){
        this.housekeepersDAO.persist(housekeeperToCreate);
    }

    private void loadAllHousekeepers(){
        this.allHousekeepers = housekeepersDAO.loadAll();
    }
}

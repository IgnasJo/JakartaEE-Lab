package lt.vu.persistence;

import lt.vu.entities.Housekeeper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class HousekeepersDAO {
    @Inject
    private EntityManager em;

    public void persist(Housekeeper housekeeper){
        this.em.persist(housekeeper);
    }

    public Housekeeper findOne(Integer id){
        return em.find(Housekeeper.class, id);
    }

    public List<Housekeeper> loadAll() {
        return em.createNamedQuery("Housekeeper.findAll", Housekeeper.class).getResultList();
    }

    public Housekeeper update(Housekeeper housekeeper){
        return em.merge(housekeeper);
    }
}

package lt.vu.persistence;

import lt.vu.entities.Hotel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class HotelsDAO {
    @Inject
    private EntityManager em;

    public void persist(Hotel hotel){
        this.em.persist(hotel);
    }

    public Hotel findOne(Integer id){
        return em.find(Hotel.class, id);
    }

    public List<Hotel> loadAll() {
        return em.createNamedQuery("Hotel.findAll", Hotel.class).getResultList();
    }

    public Hotel update(Hotel hotel){
        return em.merge(hotel);
    }
}

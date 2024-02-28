package repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import models.Coach;

import java.util.List;

@Stateless
public class CoachRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Coach entity) {
        em.persist(entity);
    }

    public List<Coach> findAll() {
        return em.createQuery("select i from Coach i", Coach.class).getResultList();
    }

    public Coach findById(int id) {
        return em.createQuery("select i from Coach i where i.id = :id", Coach.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void delete(int id) {
        Coach entity = em.find(Coach.class, id);
        em.remove(entity);
    }
}

package repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import models.Pokemon;

import java.util.List;

@Stateless
public class PokemonRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Pokemon entity) {
        em.persist(entity);
    }

    public List<Pokemon> findAll() {
        return em.createQuery("select i from Pokemon i", Pokemon.class).getResultList();
    }

    public Pokemon findById(int id) {
        return em.createQuery("select i from Pokemon i where i.id = :id", Pokemon.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void delete(int id) {
        Pokemon entity = em.find(Pokemon.class, id);
        em.remove(entity);
    }
}

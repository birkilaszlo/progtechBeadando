package model;

import javax.persistence.*;
import java.util.List;

/**
 * @author Birki László
 */

public class ModelDAO {

    /**
     * paraméter nélküli alap constructor
     */
    public ModelDAO() {
    }
    /**
     * EntityManagerFactory.
     */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
    /**
     * EntityManager.
     */
    EntityManager em = emf.createEntityManager();

    /**
     * Bejegyzés rögzítése a szavak táblába
     * @param angol új angol szó
     * @param magyar új magyar szó
     */
    public void createSzavak(String angol, String magyar){
        em.getTransaction().begin();
        szavak sz = new szavak(angol, magyar);
        em.persist(sz);
        em.getTransaction().commit();

    }

    /**
     * Bejegyzés olvasása a szavak táblából
     * @param id elsődtelegs kulcs
     * @return beazonosított bejegyzés
     */
    public szavak readSzavak(int id){
        return em.find(szavak.class, id);
    }

    /**
     * A szavak tábla összes bejegyzésének lekérése
     * @return szavakat tartalmazó lista
     */
    public List<szavak> osszesSzo(){
        TypedQuery<szavak> query = em.createQuery( "select t from model.szavak t", szavak.class );
        return query.getResultList();
    }

    /**
     * szavak tábla bejegyzéseinek lekérése
     * @return aktuális bejegyzés szám
     */
    public int tableCount() {
        Query q = em.createQuery("SELECT count(*) FROM szavak");
        Number result = (Number) q.getSingleResult();
        return result.intValue();

    }

}

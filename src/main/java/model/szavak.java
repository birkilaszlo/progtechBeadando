package model;

import javax.persistence.*;

/**
 * @author Birki László
 */
@Entity
@Table(name = "szavak")
public class szavak implements Comparable<szavak>{

    /**
     * Elsődleges kulcs (autoincrement)
      */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * Angol szó
     */
    @Column(name = "angol")
    private String angol;
    /**
     * Magyar szó
     */
    @Column(name = "magyar")
    private String magyar;

    /**
     * constructor
     * @param angol angol szó
     * @param magyar magyar szó
     */
    public szavak(String angol, String magyar) {
        this.angol = angol;
        this.magyar = magyar;
    }

    /**
     * paraméter nélküli alap constructor
     */
    public szavak() {
    }

    /**
     * Elsődleges kulcs gettere
     * @return id azonosító
     */
    public Integer getId() {
        return id;
    }

    /**
     * Elsődleges kulcs settere
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Angol szó gettere
     * @return angol szó
     */
    public String getAngol() {
        return angol;
    }

    /**
     * Angol szó settere
     * @param angol angol szó
     */
    public void setAngol(String angol) {
        this.angol = angol;
    }
    /**
     * magyarszó gettere
     * @return magyar szó
     */
    public String getMagyar() {
        return magyar;
    }
    /**
     * Magyar szó settere
     * @param magyar magyar szó
     */
    public void setMagyar(String magyar) {
        this.magyar = magyar;
    }

    @Override
    public String toString() {
        return id + " Angol: " + angol + " magyar: " + magyar;
    }

    /**
     * Szavak ABC sorba történő rendezése (növekvőbe)
     * @param o hasonlitandó szó
     * @return a sorrendet jelző int
     */
    @Override
    public int compareTo(szavak o) {
        return this.getAngol().compareTo(o.getAngol());
    }
}

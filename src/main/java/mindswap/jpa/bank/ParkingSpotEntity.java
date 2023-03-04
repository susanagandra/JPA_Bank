package mindswap.jpa.bank;

import javax.persistence.*;

@Entity
public class ParkingSpotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private TeacherEntity owner;

    public ParkingSpotEntity(Long id, TeacherEntity owner) {
        this.id = id;
        this.owner = owner;
    }

    public ParkingSpotEntity(TeacherEntity owner) {
        this.owner = owner;
    }

    public ParkingSpotEntity() {
    }

    public void setOwner(TeacherEntity person) {
        this.owner = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void printParkingSpot() {
        System.out.println("ParkingSpotEntity{" +
                "id=" + getId() +
                ", owner='" + getOwner() +
                '}');
    }
}

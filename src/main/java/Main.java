import mindswap.jpa.bank.ParkingSpotEntity;
import mindswap.jpa.bank.TeacherEntity;
import mindswap.jpa.bank.VehicleEntity;
import mindswap.jpa.bank.VehiclesType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("parking_spots_assignment");
        EntityManager em = emf.createEntityManager();

        // Insert Teachers
        System.out.println("Please insert the name of the teachers");
        em.getTransaction().begin();

        TeacherEntity t1 = new TeacherEntity("Rui", "Eletropichelaria");
        TeacherEntity t2 = new TeacherEntity("Susana", "De Gruas 101");
        TeacherEntity t3 = new TeacherEntity("Rui", "Cenas e tal");

        em.persist(t1);
        em.persist(t2);
        em.persist(t3);

        em.getTransaction().commit();

        // Get all the Teachers
        System.out.println("Print all the Employees");
        em.getTransaction().begin();

        em.createQuery("SELECT e FROM TeacherEntity e", TeacherEntity.class)
                .getResultList()
                .forEach(TeacherEntity::printTeachers);

        em.getTransaction().commit();

        // Creates parking spots
        em.getTransaction().begin();
        ParkingSpotEntity p1 = new ParkingSpotEntity(t1);
        ParkingSpotEntity p2 = new ParkingSpotEntity(t2);
        ParkingSpotEntity p3 = new ParkingSpotEntity(t3);
        ParkingSpotEntity p4 = new ParkingSpotEntity();
        ParkingSpotEntity p5 = new ParkingSpotEntity();


        t1.addParkingSpot(p1);
        t2.addParkingSpot(p2);
        t3.addParkingSpot(p3);

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.persist(p5);

        em.getTransaction().commit();

        // Create vehicles and associate with teachers

        em.getTransaction().begin();
        VehicleEntity c1 = new VehicleEntity("00AB11","bmw",2010, VehiclesType.CAR);
        VehicleEntity c2 = new VehicleEntity("11CD12","mercedes",2020, VehiclesType.CAR);
        t1.addVehicle(c1);
        t1.addVehicle(c2);

        VehicleEntity c3 = new VehicleEntity("55ZD15","nissan",2005, VehiclesType.CAR);
        VehicleEntity m1 = new VehicleEntity("12KD34", "bmw",2015,VehiclesType.MOTORCYCLE);
        t2.addVehicle(c3);
        t2.addVehicle(m1);

        VehicleEntity m2 = new VehicleEntity("54OK95", "suzuki",2003, VehiclesType.MOTORCYCLE);
        t3.addVehicle(m2);

        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(m1);
        em.persist(m2);

        em.getTransaction().commit();

        // Listing all the vehicles
        System.out.println("List of all the vehicles");
        em.getTransaction().begin();

        em.createQuery("SELECT v FROM VehicleEntity v", VehicleEntity.class)
                .getResultList()
                .forEach(VehicleEntity::printVehicles);

        em.getTransaction().commit();

        // Find the associate Vehicle to a particular Spot
        System.out.println("Find vehicle to a particular spot");
        em.getTransaction().begin();

        em.createQuery("SELECT v FROM VehicleEntity v " +
                        "JOIN v.owner t " +
                        "JOIN t.parkingSpotEntity p " +
                        "WHERE p = :parkingSpot", VehicleEntity.class)
                .setParameter("parkingSpot", p1)
                .getResultList()
                .forEach(VehicleEntity::printVehicles);

        em.getTransaction().commit();


        // Find the owner of a particular vehicle
        System.out.println("Find the owner of a particular vehicle");
        em.getTransaction().begin();

        em.createQuery("SELECT o FROM VehicleEntity o WHERE o.id = 9", VehicleEntity.class)
                .getSingleResult()
                .getOwner()
                .printTeachers();

        em.createQuery("SELECT o FROM VehicleEntity o WHERE o.id = :notworking", VehicleEntity.class)
                .setParameter("notworking", c1)
                .getSingleResult()
                .getOwner()
                .printTeachers();

        em.getTransaction().commit();

    }
}

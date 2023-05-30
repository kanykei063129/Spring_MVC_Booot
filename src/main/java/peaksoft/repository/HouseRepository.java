package peaksoft.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Customer;
import peaksoft.entity.House;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House,Long>{
    @Query("SELECT h FROM House h ORDER BY h.houseType ASC")
    List<House> getAllSortedHouseA();

    @Query("SELECT h FROM House h ORDER BY h.houseType DESC")
    List<House> getAllSortedHouseD();

}

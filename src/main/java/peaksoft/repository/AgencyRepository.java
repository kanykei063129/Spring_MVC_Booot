package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Agency;
import peaksoft.entity.House;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<Agency,Long> {
    @Query(value ="select h from Agency a join a.houses h ")
    List<House> getAllHousesToAgency();

    @Query(value = "SELECT a FROM Agency a WHERE a.name ilike :word " + "OR a.email ilike :word")
    List<Agency> searchAgency(@Param("word")String word);

}

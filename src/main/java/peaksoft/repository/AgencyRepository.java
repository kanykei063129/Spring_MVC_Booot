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
    @Query(value = "SELECT a FROM Agency a WHERE a.name = ?1 OR a.country = ?1 OR a.email = ?1")
    List<Agency> searchAgency(String word);

    @Query(value = "SELECT h FROM House h WHERE h.agency.id = :agencyId")
    List<House> getAllHousesToAgency(@Param("agencyId") Long agencyId);
}

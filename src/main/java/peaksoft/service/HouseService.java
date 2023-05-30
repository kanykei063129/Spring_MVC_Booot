package peaksoft.service;

import peaksoft.entity.House;

import java.util.List;

public interface HouseService {
    void saveHouse(Long agencyId,House house);

    List<House> getAllHouses();

    House getHouseById(Long id);

    void updateHouseById(Long id, House house);

    void deleteHouseById(Long id);
    List<House> getAllSortedHouse(String ascOrDesc);
}
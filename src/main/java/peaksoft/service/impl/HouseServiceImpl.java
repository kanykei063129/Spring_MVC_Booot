package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.entity.House;
import peaksoft.repository.AgencyRepository;
import peaksoft.repository.HouseRepository;
import peaksoft.service.HouseService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final AgencyRepository agencyRepository;
    @Override
    public void saveHouse(Long agencyId, House house) {
        try{
            Agency agency = agencyRepository.findById(agencyId).orElseThrow(() -> new NullPointerException("Agency with Id: " + agencyId + " is not found"));
            house.setAgency(agency);
            house.setIsBooked(false);
            houseRepository.save(house);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    @Override
    public House getHouseById(Long id) {
            return houseRepository.findById(id).orElseThrow(()->new NullPointerException("Agency with Id: " + id + " is not found"));
    }

    @Override
    public void updateHouseById(Long id, House newHouse) {
        try{
            House house = houseRepository.findById(id).orElseThrow(() ->
                    new NullPointerException("Agency with Id: " +
                            id + " is not found"));
            house.setHouseType(newHouse.getHouseType());
            house.setCountry(newHouse.getCountry());
            house.setImage(newHouse.getImage());
            house.setAddress(newHouse.getAddress());
            house.setDescription(newHouse.getDescription());
            house.setRoom(newHouse.getRoom());
            house.setPrice(newHouse.getPrice());
            house.setIsBooked(newHouse.getIsBooked());
            houseRepository.save(house);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteHouseById(Long id) {
            if (houseRepository.existsById(id)){
                houseRepository.deleteById(id);
            }else  throw new NullPointerException("Agency with Id: " +
                    id + " is not found ");
    }

    @Override
    public List<House> getAllSortedHouse(String ascDesc) {
        try{
            if (ascDesc.equalsIgnoreCase("asc")) {
                return houseRepository.getAllSortedHouseA();
            } else if (ascDesc.equalsIgnoreCase("desc")) {
                return houseRepository.getAllSortedHouseD();
            } else {
                throw new NullPointerException("The list is empty");
            }
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

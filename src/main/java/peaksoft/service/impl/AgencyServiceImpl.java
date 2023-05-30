package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.entity.House;
import peaksoft.repository.AgencyRepository;
import peaksoft.service.AgencyService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {
    private final AgencyRepository agencyRepository;

    @Override
    public void saveAgency(Agency agency) {
        agencyRepository.save(agency);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return agencyRepository.findAll();
    }

    @Override
    public Agency getAgencyById(Long id) {
        return agencyRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Agency with id: " + id + " is not found"));
    }

    @Override
    public void updateAgency(Long id, Agency newAgency) {
        Agency agency = agencyRepository.findById(id).orElseThrow(() -> new NullPointerException("Agency with id: " + id + " is not found"));
        agency.setName(newAgency.getName());
        agency.setImageLink(newAgency.getImageLink());
        agency.setCountry(newAgency.getCountry());
        agency.setEmail(newAgency.getEmail());
        agency.setPhoneNumber(newAgency.getPhoneNumber());
        agencyRepository.save(agency);
    }

    @Override
    public void deleteAgencyById(Long id) {
        if (agencyRepository.existsById(id)) {
            agencyRepository.deleteById(id);
        }
        else throw new NullPointerException("Agency with id: " + id + " is not found");
    }

    @Override
    public List<Agency> searchAgency(String word) {
        return agencyRepository.searchAgency(word);
    }

    @Override
    public List<House> getAllHousesToAgency() {
        return agencyRepository.getAllHousesToAgency();
    }
}

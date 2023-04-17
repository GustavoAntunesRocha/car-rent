package br.com.antunes.gustavo.carrentproject.service;

import org.springframework.stereotype.Service;

import br.com.antunes.gustavo.carrentproject.model.State;
import br.com.antunes.gustavo.carrentproject.model.dto.StateDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.StateRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StateService {
    
    private StateRepository stateRepository;

    private CountryService countryService;

    public StateService(StateRepository stateRepository, CountryService countryService) {
        this.stateRepository = stateRepository;
        this.countryService = countryService;
    }

    public StateDTO createState(StateDTO stateDTO) {
        State state = convertToEntity(stateDTO);
        stateRepository.save(state);
        return new StateDTO(state);
    }

    public StateDTO updateState(StateDTO stateDTO) {
        State state = stateRepository.findById(stateDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("State not found with id " + stateDTO.getId()));
        stateRepository.save(state);
        return new StateDTO(state);
    }

    public StateDTO getStateById(Long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("State not found with id " + id));
        return new StateDTO(state);
    }

    public void deleteState(Long id) {
        stateRepository.deleteById(id);
    }

    public StateDTO convertToDTO(State state){
        StateDTO stateDTO = new StateDTO(state);
        return stateDTO;
    }

    public State convertToEntity(StateDTO stateDTO){
        State state = new State();
        state.setId(stateDTO.getId());
        state.setName(stateDTO.getName());
        state.setCountry(countryService.convertToEntity(stateDTO.getCountryDTO()));
        return state;
    }
}

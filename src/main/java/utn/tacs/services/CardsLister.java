package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.repositories.CardsRepository;
import utn.tacs.dto.deck.response.CardDataModel;

import java.util.List;

@Service
public class CardsLister {

    private CardsRepository repository;

    public CardsLister(CardsRepository repository){
        this.repository = repository;
    }

    public List<CardDataModel> list(int page, int pageSize, String sortField, String sortDirection) {
        return repository.findAll();
    }
}

package utn.tacs.cards.application.list;

import org.springframework.stereotype.Service;
import utn.tacs.cards.domain.CardsRepository;
import utn.tacs.model.responseModel.CardDataModel;

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

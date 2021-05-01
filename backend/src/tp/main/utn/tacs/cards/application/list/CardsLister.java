package utn.tacs.cards.application.list;

import org.springframework.beans.factory.annotation.Autowired;
import utn.tacs.cards.domain.CardsRepository;
import utn.tacs.model.responseModel.CardDataModel;
import org.springframework.stereotype.Service;

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

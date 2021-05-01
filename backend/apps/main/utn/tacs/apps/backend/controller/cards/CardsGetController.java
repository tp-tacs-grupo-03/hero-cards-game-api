package utn.tacs.apps.backend.controller.cards;

import io.swagger.annotations.Api;
import utn.tacs.cards.application.list.CardsLister;
import utn.tacs.model.responseModel.CardDataModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/cards")
@Api(tags = "Cards")
@RestController
public class CardsGetController {
    private CardsLister lister;

    public CardsGetController(CardsLister lister){
        this.lister = lister;
    }

    @GetMapping
    @ApiOperation(value = "Obtener todas las cartas")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Listado de las cartas")
    })
    public List<CardDataModel> getAllCards(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                           @RequestParam(value = "sortBy", required = false) String sortField,
                                           @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
        return lister.list();
    }
}
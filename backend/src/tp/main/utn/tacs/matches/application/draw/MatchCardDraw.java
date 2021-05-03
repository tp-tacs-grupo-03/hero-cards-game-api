package utn.tacs.matches.application.draw;

import org.springframework.stereotype.Service;
import utn.tacs.cards.CardModelResponse;
import utn.tacs.cards.domain.Card;
import utn.tacs.matches.domain.Match;
import utn.tacs.matches.domain.MatchesRepository;

@Service
public class MatchCardDraw {

    MatchesRepository matches;

    public MatchCardDraw(MatchesRepository matches) {
        this.matches = matches;
    }

    public CardModelResponse draw(MatchDrawRequest matchDrawRequest) throws Exception {
        Match match = matches.find(matchDrawRequest.getMatchId()).orElseThrow(()-> new Exception("No hay match con ese id"));
        Card card = match.getNextCard(matchDrawRequest.getPlayerId());
        return new CardModelResponse(card.getId());
    }
}

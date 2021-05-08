package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Match;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.dto.match.MatchDrawRequest;
import utn.tacs.repositories.MatchesRepository;

@Service
public class MatchCardDraw {

    private MatchesRepository matches;

    public MatchCardDraw(MatchesRepository matches) {
        this.matches = matches;
    }

    public CardModelResponse draw(MatchDrawRequest matchDrawRequest) throws Exception {
        final Match match = matches.find(matchDrawRequest.getMatchId()).orElseThrow(()-> new Exception("No hay match con ese id"));
        final CardId cardId = match.getNextCard(matchDrawRequest.getPlayerId());
        return new CardModelResponse(cardId.getId());
    }
}

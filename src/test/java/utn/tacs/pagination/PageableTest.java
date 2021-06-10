package utn.tacs.pagination;

import org.junit.jupiter.api.Test;
import utn.tacs.domain.Card;
import utn.tacs.domain.CardId;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Powerstats;
import utn.tacs.pagination.exceptions.PaginationException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

class PageableTest implements Pageable<Card>{

    private List<Card> cards = new ArrayList<>();

    @Test
    void testPagination() throws PaginationException {
        int sizeOfList = 123;
        int limit = 10;
        createCardList(sizeOfList);
        for (int page=1; page <= cards.size() / limit; page++){
            int offSet = (page -1) * limit;
            final List<Card> result = getPage(new Page(offSet, limit), cards);
            assertEquals(result.size(), limit);
            assertEquals(result.get(0).getCardId().getId(), "" + offSet);
            assertEquals(result.get(limit-1).getCardId().getId(),"" + (offSet + limit - 1));
        }

        final List<Card> lastPage = getPage(new Page(120, limit), cards);
        assertEquals(lastPage.size(), 3);
        assertEquals(lastPage.get(0).getCardId().getId(), "120");
        assertEquals(lastPage.get(2).getCardId().getId(),"122");
    }

    private void createCardList(int to) {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 1000 + 1);
        List<Integer> collect = IntStream.range(0, to).boxed().collect(Collectors.toList());
        collect.forEach(i -> {
            final CardId cardId = new CardId("" + i);
            final Powerstats powerstats = new Powerstats();
            powerstats.setCombat(randomNum * 13);
            powerstats.setDurability(randomNum * 17);
            powerstats.setIntelligence(randomNum * 23);
            powerstats.setPower(randomNum * 7);
            powerstats.setSpeed(randomNum * 11);
            powerstats.setStrength(randomNum * 3);
            try {
                cards.add(new Card(cardId,powerstats ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        assertEquals(cards.size(), 123);
    }

}

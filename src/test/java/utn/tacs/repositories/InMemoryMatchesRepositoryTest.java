package utn.tacs.repositories;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import utn.tacs.domain.Match;
import utn.tacs.dto.match.MatchStatusEnum;
import utn.tacs.sorting.Sort;
import utn.tacs.sorting.exceptions.SortingException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryMatchesRepositoryTest {

    private static InMemoryMatchesRepository memory = new InMemoryMatchesRepository();

    @BeforeAll
    static void setUp() {
        List<Integer> collect = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        collect.forEach(i -> {
            final Match match = new Match();
            match.setId("" + i);
            match.setCreationDate(new Date());
            MatchStatusEnum status = MatchStatusEnum.values()[(int)(Math.random()*MatchStatusEnum.values().length)];
            match.setStatus(status);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            memory.save(match);
        });
    }

    @Test
    void testOrderDescByDate() throws SortingException {
        Pageable pageable = PageRequest.of(0, 10);
        final List<Match> all = memory.findAll(pageable, new Sort("date", "desc"));
        assertEquals(10, all.size());
        for(int i=0; i < all.size()-1; i++) {
            assertTrue(all.get(i).getDate() >= all.get(i+1).getDate());
        }
    }

    @Test
    void testOrderAscByDate() throws SortingException {
        Pageable pageable = PageRequest.of(0, 10);
        final List<Match> all = memory.findAll(pageable, new Sort("date", "asc"));
        assertEquals(10, all.size());
        for(int i=0; i < all.size()-1; i++) {
            assertTrue(all.get(i).getDate() <= all.get(i+1).getDate());
        }
    }

    @Test
    void testOrderAscByStatus() throws SortingException {
        Pageable pageable = PageRequest.of(0, 10);
        final List<Match> all = memory.findAll(pageable, new Sort("status", "asc"));
        assertEquals(10, all.size());
        all.forEach(v -> System.out.println(v.getStatus()));
    }

}

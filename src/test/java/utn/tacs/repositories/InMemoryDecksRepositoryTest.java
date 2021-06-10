package utn.tacs.repositories;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utn.tacs.domain.Deck;
import utn.tacs.pagination.Page;
import utn.tacs.pagination.exceptions.PaginationException;
import utn.tacs.sorting.Sort;
import utn.tacs.sorting.exceptions.SortingException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryDecksRepositoryTest {

    private static InMemoryDecksRepository memory = new InMemoryDecksRepository();

    @BeforeAll
    static void setUp() {
        List<Integer> collect = IntStream.range(0, 20).boxed().collect(Collectors.toList());
        collect.forEach(i -> {
            final Deck deck = new Deck();
            deck.setId("" + i);
            deck.setName("" + i);
            memory.save(deck);
        });
    }

    @Test
    void testOrderDescById() throws PaginationException, SortingException {
        final List<Deck> all = memory.findAll(new Page(0, 10), new Sort("id", "desc"));
        assertEquals(10, all.size());
        for(int i=0; i < all.size()-1; i++) {
            assertTrue(all.get(i).getId().compareTo(all.get(i+1).getId()) >= 0);
        }
    }

    @Test
    void testOrderAscById() throws PaginationException, SortingException {
        final List<Deck> all = memory.findAll(new Page(0, 10), new Sort("id", "asc"));
        assertEquals(10, all.size());
        for(int i=0; i < all.size()-1; i++) {
            assertTrue(all.get(i).getId().compareTo(all.get(i+1).getId()) <= 0);
        }
    }

    @Test
    void testOrderAscByName() throws PaginationException, SortingException {
        final List<Deck> all = memory.findAll(new Page(0, 10), new Sort("name", "asc"));
        assertEquals(10, all.size());
        for(int i=0; i < all.size()-1; i++) {
            assertTrue(all.get(i).getName().compareTo(all.get(i+1).getName()) <= 0);
        }
    }

    @Test
    void testOrderDescByName() throws PaginationException, SortingException {
        final List<Deck> all = memory.findAll(new Page(0, 10), new Sort("name", "desc"));
        assertEquals(10, all.size());
        for(int i=0; i < all.size()-1; i++) {
            assertTrue(all.get(i).getName().compareTo(all.get(i+1).getName()) >= 0);
        }
    }

}

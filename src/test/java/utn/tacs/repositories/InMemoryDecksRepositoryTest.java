package utn.tacs.repositories;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import utn.tacs.domain.Deck;
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
        List<Integer> collect = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        collect.forEach(i -> {
            final Deck deck = new Deck();
            deck.setId("" + i);
            deck.setName("" + i);
            memory.save(deck);
        });
    }

    @Test
    void testOrderDescById() throws SortingException {
        Pageable pageable = PageRequest.of(0, 10);
        final List<Deck> all = memory.findAll(pageable, new Sort("id", "desc"));
        assertEquals(10, all.size());
        for(int i=0; i < all.size()-1; i++) {
            assertTrue(all.get(i).getId().compareTo(all.get(i+1).getId()) >= 0);
        }
    }

    @Test
    void testOrderAscById() throws SortingException {
        Pageable pageable = PageRequest.of(0, 10);
        final List<Deck> all = memory.findAll(pageable, new Sort("id", "asc"));
        assertEquals(10, all.size());
        for(int i=0; i < all.size()-1; i++) {
            assertTrue(all.get(i).getId().compareTo(all.get(i+1).getId()) <= 0);
        }
    }

    @Test
    void testOrderAscByName() throws SortingException {
        Pageable pageable = PageRequest.of(0, 10);
        final List<Deck> all = memory.findAll(pageable, new Sort("name", "asc"));
        assertEquals(10, all.size());
        for(int i=0; i < all.size()-1; i++) {
            assertTrue(all.get(i).getName().compareTo(all.get(i+1).getName()) <= 0);
        }
    }

    @Test
    void testOrderDescByName() throws SortingException {
        Pageable pageable = PageRequest.of(0, 10);
        final List<Deck> all = memory.findAll(pageable, new Sort("name", "desc"));
        assertEquals(10, all.size());
        for(int i=0; i < all.size()-1; i++) {
            assertTrue(all.get(i).getName().compareTo(all.get(i+1).getName()) >= 0);
        }
    }

}

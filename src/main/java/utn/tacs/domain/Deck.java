package utn.tacs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.sorting.IdComparable;
import utn.tacs.sorting.NameComparable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
@NoArgsConstructor
public class Deck implements IdComparable, NameComparable {

    private String id;
    private List<CardId> cardIds;
    private String name;

    public Deck(List<CardId> cardIds, String name) {
        this.cardIds = cardIds;
        this.name = name;
    }

    public void addCards(List<CardId> cardIds) {
        this.cardIds.addAll(cardIds);
    }

    public void deleteCard(CardId cardId){
        cardIds.remove(cardId);
    }

    public void shuffle() {
        Collections.shuffle(this.cardIds);
    }

    public List<Queue<CardId>> split(int users) {
        int module = this.cardIds.size() % users;
        int partitionSize = this.cardIds.size() / users;
        List<Queue<CardId>> partitions = new LinkedList<>();
        for (int i = 0; i < this.cardIds.size() - module; i += partitionSize) {
            partitions.add(new LinkedList<>(this.cardIds.subList(i,
                    Math.min(i + partitionSize, this.cardIds.size()))));
        }
        return partitions;
    }
}

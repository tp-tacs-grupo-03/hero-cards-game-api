package utn.tacs.domain;

import java.util.Objects;

public class CardId {

    private String id;

    public CardId(){}

    public CardId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardId cardId = (CardId) o;
        return Objects.equals(id, cardId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Integer.valueOf(id));
    }
}

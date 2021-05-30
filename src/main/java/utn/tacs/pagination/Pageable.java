package utn.tacs.pagination;

import java.util.List;

public interface Pageable <T>{

    default List<T> getPage(Page p, List<T> list){
        int maybeTo = p.getOffSet() + p.getLimit();
        int to = Math.min(maybeTo, list.size());
        return list.subList(p.getOffSet(), to);
    }

}

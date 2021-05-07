package utn.tacs.dto.match;

public abstract class Paging {

    private int page;
    private int pageSize;
    private String sortDirection;

    public Paging(int page, int pageSize, String sortDirection) {
        this.page = page;
        this.pageSize = pageSize;
        this.sortDirection = sortDirection;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}

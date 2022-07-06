import java.util.Map;

public class Page {
    private Map<String, PageEntry> pageEntries;

    public Page(Map<String, PageEntry> pageEntries) {
        this.pageEntries = pageEntries;
    }

    public Map<String, PageEntry> getPageEntries() {
        return pageEntries;
    }

}

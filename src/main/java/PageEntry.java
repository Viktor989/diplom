import com.google.gson.Gson;

import java.util.List;

public class PageEntry implements Comparable<PageEntry> {
    private final String pdfName;
    private final int page;
    private final int count;

    public PageEntry(String pdfName, int page, int count) {
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }

    @Override
    public int compareTo(PageEntry o) {
        return Integer.compare(count, o.count);
    }

    @Override
    public String toString() {
        return "{Наименование документа = '" + pdfName + '\'' +
                ", страница = " + page +
                ", колличество = " + count +
                "} \n";
    }
}

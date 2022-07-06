import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;

public class BooleanSearchEngine implements SearchEngine {
    List<Page> pages = new ArrayList<>();

    public BooleanSearchEngine(File pdfsDir) throws IOException {
        String[] listFile = pdfsDir.list(new ExtensionFilter("pdf"));
        PdfPage page;
        String text;
        String[] words;
        for (String pdf : listFile) {
            var doc = new PdfDocument(new PdfReader("pdfs\\" + pdf));
            int totalPage = doc.getNumberOfPages();
            for (int i = 1; i <= totalPage; i++) {
                Map<String, Integer> freqs = new HashMap<>();
                Map<String, PageEntry> pageEntries = new HashMap<>();
                page = doc.getPage(i);
                text = PdfTextExtractor.getTextFromPage(page);
                words = text.split("\\P{IsAlphabetic}+");
                for (String word : words) {
                    if (word.isEmpty()) {
                        continue;
                    }
                    freqs.put(word.toLowerCase(), freqs.getOrDefault(word.toLowerCase(), 0) + 1);
                }
                for (Map.Entry<String, Integer> entry : freqs.entrySet()) {
                    pageEntries.put(entry.getKey(), new PageEntry(pdf, i, entry.getValue()));
                }
                pages.add(new Page(pageEntries));
            }
        }
    }

    // получение списка всех файлов pdf в дирректории pdfs
    private static class ExtensionFilter implements FilenameFilter {
        private final String extension;

        public ExtensionFilter(String ext) {
            extension = ext;
        }

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(extension);
        }
    }

    @Override
    public List<PageEntry> search(String word) {
        List<PageEntry> listString = new ArrayList<>();
        for (Page p : pages) {
            if (p.getPageEntries().containsKey(word)) {
                listString.add(p.getPageEntries().get(word));
            }
        }
        Collections.sort(listString, Collections.reverseOrder());
        return listString;
    }
}
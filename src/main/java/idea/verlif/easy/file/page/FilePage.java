package idea.verlif.easy.file.page;

import java.io.File;
import java.util.Arrays;

/**
 * @author Verlif
 * @version 1.0
 */
public class FilePage {

    protected File[] files;
    protected long total;
    protected long size;
    protected long current;
    protected long pages;

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "FilePage{" +
                "files=" + Arrays.toString(files) +
                ", total=" + total +
                ", size=" + size +
                ", current=" + current +
                ", pages=" + pages +
                '}';
    }
}

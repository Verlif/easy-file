package idea.verlif.easy.file.page;

import java.io.File;
import java.io.FileFilter;
import java.util.Comparator;

/**
 * 文件查询条件
 *
 * @author Verlif
 * @version 1.0
 */
public class FileQuery {

    /**
     * 文件名查询
     */
    protected String name;

    /**
     * 每页大小。若size小于0，则每页大小无限。
     */
    protected Integer size = 15;

    /**
     * 页码，从1开始
     */
    protected Integer current = 1;

    /**
     * 过滤条件
     */
    protected FileFilter fileFilter;

    /**
     * 文件排序比较器
     */
    protected Comparator<File> comparator;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size == null ? 15 : size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCurrent() {
        return current == null ? 1 : current;
    }

    /**
     * 获取页头序号
     */
    public Integer getPageHead() {
        return (current - 1) * size;
    }

    public void setCurrent(Integer current) {
        if (current < 1) {
            this.current = 1;
        } else {
            this.current = current;
        }
    }

    public FileFilter getFileFilter() {
        return fileFilter;
    }

    public void setFileFilter(FileFilter fileFilter) {
        this.fileFilter = fileFilter;
    }

    public Comparator<File> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<File> comparator) {
        this.comparator = comparator;
    }
}

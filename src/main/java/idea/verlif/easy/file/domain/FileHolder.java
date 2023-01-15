package idea.verlif.easy.file.domain;

import idea.verlif.easy.file.page.FilePage;
import idea.verlif.easy.file.page.FileQuery;
import idea.verlif.easy.file.util.File64Util;
import idea.verlif.easy.file.util.FileUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Verlif
 */
public class FileHolder extends File {

    public FileHolder(String pathname) {
        super(pathname);
    }

    public FileHolder(String parent, String child) {
        super(parent, child);
    }

    public FileHolder(File parent, String child) {
        super(parent, child);
    }

    public FileHolder(URI uri) {
        super(uri);
    }

    public FileHolder(File file) {
        super(file.getPath());
    }

    public String getBaseName() {
        String name = getName();
        if (isDirectory()) {
            return name;
        }
        int i = name.lastIndexOf('.');
        if (i == -1) {
            return name;
        }
        return name.substring(0, i);
    }

    /**
     * 获取文件后缀名
     *
     * @return 文件后缀名。当文件类型是文件夹或文件没有后缀名则返回null。
     */
    public String getSuffix() {
        if (isDirectory()) {
            return null;
        }
        String name = getName();
        int i = name.lastIndexOf('.');
        if (i == -1) {
            return null;
        }
        return name.substring(i + 1);
    }

    /**
     * 分页查询子文件
     *
     * @param query 查询条件
     * @return 分页结果
     */
    public FilePage queryPage(FileQuery query) {
        File[] children = listFiles(query.getFileFilter());
        return FileUtil.pageList(query, children);
    }

    /**
     * 分页搜索文件
     *
     * @param query 查询条件
     * @return 分页结果
     */
    public FilePage searchPage(FileQuery query) {
        List<File> list = new ArrayList<>();
        File[] files = listFiles();
        if (files != null) {
            for (File file : files) {
                filterList(file, list, query.getFileFilter());
            }
        }
        return FileUtil.pageList(query, list.toArray(new File[0]));
    }

    private void filterList(File target, List<File> list, FileFilter fileFilter) {
        File[] files = target.listFiles();
        // 先判断当前文件对象是都符合过滤条件
        if (fileFilter.accept(target)) {
            list.add(target);
        }
        // 如果是文件则直接返回
        if (files == null) {
            return;
        }
        // 文件夹则遍历子文件
        for (File file : files) {
            filterList(file, list, fileFilter);
        }
    }

    /**
     * 文件转Base64字符串
     *
     * @return Base64字符串
     * @throws IOException 文件读取异常
     */
    public String toBase64() throws IOException {
        return File64Util.toBase64(this);
    }

    /**
     * 级联删除
     *
     * @return 是否删除成功
     */
    public boolean deleteCascaded() {
        return FileUtil.deleteFile(this) > 0;
    }

    /**
     * 获取子文件
     *
     * @param name 子文件名
     * @return 子文件对象
     */
    public FileHolder child(String name) {
        return new FileHolder(this, name);
    }

    /**
     * 复制文件
     *
     * @param dest  目标文件
     * @param cover 是否覆盖已存在文件
     */
    public void copyTo(File dest, boolean cover) throws FileNotFoundException {
        FileUtil.copyFile(this, dest, cover);
    }
}

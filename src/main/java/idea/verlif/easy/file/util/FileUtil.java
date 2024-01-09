package idea.verlif.easy.file.util;

import idea.verlif.easy.file.EasyFileException;
import idea.verlif.easy.file.page.FilePage;
import idea.verlif.easy.file.page.FileQuery;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * @author Verlif
 * @version 1.0
 */
public class FileUtil {

    /**
     * 级联删除文件
     *
     * @param file 目标文件
     * @return 删除的文件及文件夹总数
     */
    public static int deleteFile(File file) {
        int count = 0;
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                count += deleteFile(f);
            }
        }
        // 最后删除文件夹
        if (file.delete()) {
            count++;
        }
        return count;
    }

    /**
     * 从文件中获取字符串内容
     *
     * @param file 文件对象
     * @return 目标文件的字符串内容
     */
    public static String readContentAsString(File file) {
        return readContentAsString(file, StandardCharsets.UTF_8);
    }

    /**
     * 从文件中获取字符串内容
     *
     * @param file    文件对象
     * @param charset 字符集
     * @return 目标文件的字符串内容
     */
    public static String readContentAsString(File file, Charset charset) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Files.newInputStream(file.toPath()), charset))) {
            char[] b = new char[1024];
            StringBuilder sb = new StringBuilder();
            int length;
            while ((length = reader.read(b)) > 0) {
                sb.append(b, 0, length);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 向文件中写入字符串
     *
     * @param file    文件对象。若文件不存在则创建。
     * @param content 写入内容
     */
    public static void writeStringToFile(File file, String content) {
        writeStringToFile(file, content, StandardCharsets.UTF_8);
    }

    /**
     * 向文件中写入字符串
     *
     * @param file    文件对象。若文件不存在则创建。
     * @param content 写入内容
     * @param charset 字符集
     */
    public static void writeStringToFile(File file, String content, Charset charset) {
        writeStringToFile(file, content, charset, false);
    }

    /**
     * 向文件中追加字符串
     *
     * @param file    文件对象。若文件不存在则创建。
     * @param content 追加内容
     */
    public static void appendStringToFile(File file, String content) {
        appendStringToFile(file, content, StandardCharsets.UTF_8);
    }

    /**
     * 向文件中追加字符串
     *
     * @param file    文件对象。若文件不存在则创建。
     * @param content 追加内容
     * @param charset 字符集
     */
    public static void appendStringToFile(File file, String content, Charset charset) {
        writeStringToFile(file, content, charset, true);
    }

    public static void writeStringToFile(File file, String content, Charset charset, boolean append) {
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new EasyFileException("Cannot create file - " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                throw new EasyFileException(e);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(Files.newOutputStream(file.toPath()), charset))) {
            if (append) {
                writer.append(content);
            } else {
                writer.write(content);
            }
            writer.flush();
        } catch (IOException e) {
            throw new EasyFileException(e);
        }
    }

    /**
     * 将文件列表进行分页
     *
     * @param query 分页条件
     * @param files 文件列表
     * @return 分页结果
     */
    public static FilePage pageList(FileQuery query, File[] files) {
        FilePage page = new FilePage();
        if (files == null) {
            return page;
        }
        if (query.getComparator() != null) {
            Arrays.sort(files, query.getComparator());
        }
        page.setTotal(files.length);
        if (query.getSize() < 0) {
            page.setSize(files.length);
            page.setPages(1);
            page.setCurrent(1);
            page.setFiles(files);
            return page;
        }
        page.setSize(query.getSize());
        page.setPages(page.getTotal() / page.getSize());
        if (page.getTotal() % page.getSize() > 0) {
            page.setPages(page.getPages() + 1);
        }
        page.setCurrent(query.getCurrent());
        if (query.getPageHead() > files.length) {
            page.setFiles(new File[]{});
        } else {
            int head = query.getPageHead();
            int end = head + query.getSize();
            int length = Math.min(end, files.length) - query.getPageHead();
            File[] fileArray = new File[length];
            System.arraycopy(files, head, fileArray, 0, length);
            page.setFiles(fileArray);
        }
        return page;
    }

    public static void copyFile(File source, String destPath, boolean cover) throws FileNotFoundException {
        copyFile(source, new File(destPath), cover);
    }

    /**
     * 复制文件。<br/>
     * 源文件是文件时，目标文件类型是文件夹时复制源文件到文件夹下，名称相同；目标文件类型是文件时复制源文件到目标文件。<br/>
     * 源文件是文件夹时，目标文件类型只能是文件夹。
     *
     * @param source 源文件
     * @param dest   目标文件
     * @param cover  是否覆盖已存在文件
     */
    public static void copyFile(File source, File dest, boolean cover) throws FileNotFoundException {
        if (!source.exists()) {
            throw new FileNotFoundException(source.getAbsolutePath());
        }
        File[] sChildren = source.listFiles();
        // 文件复制
        if (sChildren == null) {
            if (!dest.exists() || cover) {
                copyIt(source, dest);
            }
        } else {
            // 文件夹不存在时创建文件夹
            if (!dest.exists() && !dest.mkdirs()) {
                throw new EasyFileException("Cannot make directory - " + dest.getAbsolutePath());
            }
            // 文件夹则优先判断目标文件是否是文件夹
            if (dest.isDirectory()) {
                for (File sChild : sChildren) {
                    autoCopyFile(sChild, dest, cover);
                }
            } else {
                throw new EasyFileException("Cannot copy directory to a file!");
            }
        }
    }

    private static void autoCopyFile(File source, File parentFile, boolean cover) {
        File[] sChildren = source.listFiles();
        File dest = new File(parentFile, source.getName());
        // 文件则直接进行复制
        if (sChildren == null) {
            if (!dest.exists() || cover) {
                copyIt(source, dest);
            }
        } else {
            // 创建文件夹
            if (dest.exists() || dest.mkdir()) {
                // 遍历文件
                for (File sChild : sChildren) {
                    autoCopyFile(sChild, dest, cover);
                }
            }
        }
    }

    private static void copyIt(File source, File targetFile) {
        // 若目标文件是文件夹，则复制到此文件夹下
        if (targetFile.isDirectory()) {
            targetFile = new File(targetFile, source.getName());
        }
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(targetFile);
             FileChannel inputChannel = fis.getChannel();
             FileChannel outputChannel = fos.getChannel()) {
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (IOException e) {
            throw new EasyFileException(e);
        }
    }

}

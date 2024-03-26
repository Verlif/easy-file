package idea.verlif.easy.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @author Verlif
 */
public class File64Util {
    /**
     * 文件转Base64字符串
     *
     * @param path 文件路径
     * @return Base64字符串
     */
    public static String toBase64(String path) throws IOException {
        File file = new File(path);
        return toBase64(file);
    }

    /**
     * 文件转Base64字符串
     *
     * @param file 文件路径
     * @return Base64字符串
     */
    public static String toBase64(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[(int) file.length()];
            if (fis.read(buffer) == -1) {
                return null;
            }
            return Base64.getEncoder().encodeToString(buffer);
        }
    }

    /**
     * 将Base64字符串解析到成文件
     *
     * @param base64Code Base64字符串
     * @param targetPath 目标文件路径
     */
    public static void toFile(String base64Code, String targetPath) throws IOException {
        byte[] buffer = Base64.getDecoder().decode(base64Code);
        try (FileOutputStream out = new FileOutputStream(targetPath)) {
            out.write(buffer);
        }
    }

    /**
     * 将Base64字符串解析到成文件
     *
     * @param base64Code Base64字符串
     * @param targetFile 目标文件
     */
    public static void toFile(String base64Code, File targetFile) throws IOException {
        toFile(base64Code, targetFile.getPath());
    }
}

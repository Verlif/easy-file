package idea.verlif.easy.file;

import idea.verlif.easy.file.domain.FileHolder;
import idea.verlif.easy.file.page.FilePage;
import idea.verlif.easy.file.page.FileQuery;
import idea.verlif.easy.file.util.File64Util;
import idea.verlif.easy.file.util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author Verlif
 */
public class MainTest {

    public static void main(String[] args) throws IOException {
    }

    @Test
    public void file64Test() throws IOException {
        String base64 = File64Util.toBase64(new File("readme.md"));
        File64Util.toFile(base64,
                "test\\readme.md");
    }

    @Test
    public void searchTest() {
        FileHolder holder = new FileHolder("F:\\Code\\JAVA\\easy-file");
        FileQuery query = new FileQuery();
        query.setFileFilter(pathname -> pathname.getName().toUpperCase().contains("PAGE"));
        query.setComparator((o1, o2) -> {
            if (o1.isDirectory() && o2.isFile()) {
                return -1;
            }
            if (o2.isDirectory() && o1.isFile()) {
                return 1;
            }
            return o1.getName().length() - o2.getName().length();
        });
        query.setSize(-1);
        FilePage page = holder.searchPage(query);
        for (File file : page.getFiles()) {
            FileHolder fh = new FileHolder(file);
            System.out.println(fh.getBaseName());
        }
        System.out.println(page);
    }

    @Test
    public void readAndWriteTest() throws IOException {
        File file = new File("test\\dir1\\a.txt");
        FileUtil.writeStringToFile(file, "Hello World！444");
        System.out.println(FileUtil.readContentAsString(file));
        FileUtil.copyFile(file, "test\\dir2\\3.txt", true);
    }
}

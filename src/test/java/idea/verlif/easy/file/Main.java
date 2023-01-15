package idea.verlif.easy.file;

import idea.verlif.easy.file.domain.FileHolder;
import idea.verlif.easy.file.page.FilePage;
import idea.verlif.easy.file.page.FileQuery;
import idea.verlif.easy.file.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Verlif
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        searchTest();
    }

    private static void searchTest() {
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

    private static void readAndWriteTest() throws FileNotFoundException {
        File file = new File("F:\\temp\\FileTest\\a.txt");
        FileUtil.writeStringToFile(file, "Hello World！444");
        System.out.println(FileUtil.readContentAsString(file));
        FileUtil.copyFile(file.getParentFile(), new File("F:\\temp\\FileTest2"), true);
        System.out.println(new Scanner(System.in).next());
    }
}

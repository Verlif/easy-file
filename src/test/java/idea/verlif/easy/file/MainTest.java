package idea.verlif.easy.file;

import idea.verlif.easy.file.domain.FileHolder;
import idea.verlif.easy.file.page.FilePage;
import idea.verlif.easy.file.page.FileQuery;
import idea.verlif.easy.file.util.File64Util;
import idea.verlif.easy.file.util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author Verlif
 */
public class MainTest {

    public static void main(String[] args) throws IOException {
    }

    @Test
    public void file64Test() throws IOException {
        File64Util.toFile("data:application/pdf;base64,JVBERi0xLjMKJbrfrOAKMyAwIG9iago8PC9UeXBlIC9QYWdlCi9QYXJlbnQgMSAwIFIKL1Jlc291cmNlcyAyIDAgUgovTWVkaWFCb3ggWzAgMCA1OTUuMjc5OTk5OTk5OTk5OTcyNyA4NDEuODg5OTk5OTk5OTk5OTg2NF0KL0NvbnRlbnRzIDQgMCBSCj4+CmVuZG9iago0IDAgb2JqCjw8Ci9MZW5ndGggOTU0Cj4+CnN0cmVhbQowLjU2NzAwMDAwMDAwMDAwMDEgdwowIEcKQlQKL0YxIDE2IFRmCjE4LjM5OTk5OTk5OTk5OTk5ODYgVEwKMCBnCjI4LjM0NjQ1NjY5MjkxMzM4ODggODEzLjU0MzU0MzMwNzA4NjU2NTYgVGQKKDw/eG1sIHZlcnNpb249IjEuMCIgZW5jb2Rpbmc9IlVURi04IiA/PikgVGoKVCogKDxkYXRhPikgVGoKVCogKAAgACAAIAAgADwAdABhAGcATgBhAG0AZQA+TwFOGk66VFhTwk/ddnuLsAA8AC8AdABhAGcATgBhAG0AZQA+KSBUagpUKiAoICAgIDxjaGlsZHJlbj4pIFRqClQqICgAIAAgACAAIAAgACAAIAAgADwAdABhAGcATgBhAG0AZQA+XFwxWTFOGk/hYG8APAAvAHQAYQBnAE4AYQBtAGUAPikgVGoKVCogKCAgICAgICAgPGNoaWxkcmVuPikgVGoKVCogKAAgACAAIAAgACAAIAAgACAAIAAgACAAIAA8AHQAYQBnAE4AYQBtAGUAPlnTVA0APAAvAHQAYQBnAE4AYQBtAGUAPikgVGoKVCogKCAgICAgICAgICAgIDxjaGlsZHJlbj56aGFuZ3NhbjwvY2hpbGRyZW4+KSBUagpUKiAoICAgICAgICA8L2NoaWxkcmVuPikgVGoKVCogKCAgICAgICAgPGNoaWxkcmVuPikgVGoKVCogKAAgACAAIAAgACAAIAAgACAAIAAgACAAIAA8AHQAYQBnAE4AYQBtAGUAPo6rTv2LwQA8AC8AdABhAGcATgBhAG0AZQA+KSBUagpUKiAoICAgICAgICAgICAgPGNoaWxkcmVuPjEyODYyMzg2NzEyODkxPC9jaGlsZHJlbj4pIFRqClQqICggICAgICAgIDwvY2hpbGRyZW4+KSBUagpUKiAoICAgICAgICA8Y2hpbGRyZW4+KSBUagpUKiAoACAAIAAgACAAIAAgACAAIAAgACAAIAAgADwAdABhAGcATgBhAG0AZQA+YCdSKwA8AC8AdABhAGcATgBhAG0AZQA+KSBUagpUKiAoACAAIAAgACAAIAAgACAAIAAgACAAIAAgADwAYwBoAGkAbABkAHIAZQBuAD51NwA8AC8AYwBoAGkAbABkAHIAZQBuAD4pIFRqClQqICggICAgICAgIDwvY2hpbGRyZW4+KSBUagpUKiAoICAgIDwvY2hpbGRyZW4+KSBUagpUKiAoPC9kYXRhPikgVGoKRVQKZW5kc3RyZWFtCmVuZG9iagoxIDAgb2JqCjw8L1R5cGUgL1BhZ2VzCi9LaWRzIFszIDAgUiBdCi9Db3VudCAxCj4+CmVuZG9iago1IDAgb2JqCjw8Ci9UeXBlIC9Gb250Ci9CYXNlRm9udCAvSGVsdmV0aWNhCi9TdWJ0eXBlIC9UeXBlMQovRW5jb2RpbmcgL1dpbkFuc2lFbmNvZGluZwovRmlyc3RDaGFyIDMyCi9MYXN0Q2hhciAyNTUKPj4KZW5kb2JqCjYgMCBvYmoKPDwKL1R5cGUgL0ZvbnQKL0Jhc2VGb250IC9IZWx2ZXRpY2EtQm9sZAovU3VidHlwZSAvVHlwZTEKL0VuY29kaW5nIC9XaW5BbnNpRW5jb2RpbmcKL0ZpcnN0Q2hhciAzMgovTGFzdENoYXIgMjU1Cj4+CmVuZG9iago3IDAgb2JqCjw8Ci9UeXBlIC9Gb250Ci9CYXNlRm9udCAvSGVsdmV0aWNhLU9ibGlxdWUKL1N1YnR5cGUgL1R5cGUxCi9FbmNvZGluZyAvV2luQW5zaUVuY29kaW5nCi9GaXJzdENoYXIgMzIKL0xhc3RDaGFyIDI1NQo+PgplbmRvYmoKOCAwIG9iago8PAovVHlwZSAvRm9udAovQmFzZUZvbnQgL0hlbHZldGljYS1Cb2xkT2JsaXF1ZQovU3VidHlwZSAvVHlwZTEKL0VuY29kaW5nIC9XaW5BbnNpRW5jb2RpbmcKL0ZpcnN0Q2hhciAzMgovTGFzdENoYXIgMjU1Cj4+CmVuZG9iago5IDAgb2JqCjw8Ci9UeXBlIC9Gb250Ci9CYXNlRm9udCAvQ291cmllcgovU3VidHlwZSAvVHlwZTEKL0VuY29kaW5nIC9XaW5BbnNpRW5jb2RpbmcKL0ZpcnN0Q2hhciAzMgovTGFzdENoYXIgMjU1Cj4+CmVuZG9iagoxMCAwIG9iago8PAovVHlwZSAvRm9udAovQmFzZUZvbnQgL0NvdXJpZXItQm9sZAovU3VidHlwZSAvVHlwZTEKL0VuY29kaW5nIC9XaW5BbnNpRW5jb2RpbmcKL0ZpcnN0Q2hhciAzMgovTGFzdENoYXIgMjU1Cj4+CmVuZG9iagoxMSAwIG9iago8PAovVHlwZSAvRm9udAovQmFzZUZvbnQgL0NvdXJpZXItT2JsaXF1ZQovU3VidHlwZSAvVHlwZTEKL0VuY29kaW5nIC9XaW5BbnNpRW5jb2RpbmcKL0ZpcnN0Q2hhciAzMgovTGFzdENoYXIgMjU1Cj4+CmVuZG9iagoxMiAwIG9iago8PAovVHlwZSAvRm9udAovQmFzZUZvbnQgL0NvdXJpZXItQm9sZE9ibGlxdWUKL1N1YnR5cGUgL1R5cGUxCi9FbmNvZGluZyAvV2luQW5zaUVuY29kaW5nCi9GaXJzdENoYXIgMzIKL0xhc3RDaGFyIDI1NQo+PgplbmRvYmoKMTMgMCBvYmoKPDwKL1R5cGUgL0ZvbnQKL0Jhc2VGb250IC9UaW1lcy1Sb21hbgovU3VidHlwZSAvVHlwZTEKL0VuY29kaW5nIC9XaW5BbnNpRW5jb2RpbmcKL0ZpcnN0Q2hhciAzMgovTGFzdENoYXIgMjU1Cj4+CmVuZG9iagoxNCAwIG9iago8PAovVHlwZSAvRm9udAovQmFzZUZvbnQgL1RpbWVzLUJvbGQKL1N1YnR5cGUgL1R5cGUxCi9FbmNvZGluZyAvV2luQW5zaUVuY29kaW5nCi9GaXJzdENoYXIgMzIKL0xhc3RDaGFyIDI1NQo+PgplbmRvYmoKMTUgMCBvYmoKPDwKL1R5cGUgL0ZvbnQKL0Jhc2VGb250IC9UaW1lcy1JdGFsaWMKL1N1YnR5cGUgL1R5cGUxCi9FbmNvZGluZyAvV2luQW5zaUVuY29kaW5nCi9GaXJzdENoYXIgMzIKL0xhc3RDaGFyIDI1NQo+PgplbmRvYmoKMTYgMCBvYmoKPDwKL1R5cGUgL0ZvbnQKL0Jhc2VGb250IC9UaW1lcy1Cb2xkSXRhbGljCi9TdWJ0eXBlIC9UeXBlMQovRW5jb2RpbmcgL1dpbkFuc2lFbmNvZGluZwovRmlyc3RDaGFyIDMyCi9MYXN0Q2hhciAyNTUKPj4KZW5kb2JqCjE3IDAgb2JqCjw8Ci9UeXBlIC9Gb250Ci9CYXNlRm9udCAvWmFwZkRpbmdiYXRzCi9TdWJ0eXBlIC9UeXBlMQovRmlyc3RDaGFyIDMyCi9MYXN0Q2hhciAyNTUKPj4KZW5kb2JqCjE4IDAgb2JqCjw8Ci9UeXBlIC9Gb250Ci9CYXNlRm9udCAvU3ltYm9sCi9TdWJ0eXBlIC9UeXBlMQovRmlyc3RDaGFyIDMyCi9MYXN0Q2hhciAyNTUKPj4KZW5kb2JqCjIgMCBvYmoKPDwKL1Byb2NTZXQgWy9QREYgL1RleHQgL0ltYWdlQiAvSW1hZ2VDIC9JbWFnZUldCi9Gb250IDw8Ci9GMSA1IDAgUgovRjIgNiAwIFIKL0YzIDcgMCBSCi9GNCA4IDAgUgovRjUgOSAwIFIKL0Y2IDEwIDAgUgovRjcgMTEgMCBSCi9GOCAxMiAwIFIKL0Y5IDEzIDAgUgovRjEwIDE0IDAgUgovRjExIDE1IDAgUgovRjEyIDE2IDAgUgovRjEzIDE3IDAgUgovRjE0IDE4IDAgUgo+PgovWE9iamVjdCA8PAo+Pgo+PgplbmRvYmoKMTkgMCBvYmoKPDwKL1Byb2R1Y2VyIChqc1BERiAyLjUuMSkKL0NyZWF0aW9uRGF0ZSAoRDoyMDIzMDYxMDExNTAzNyswOCcwMCcpCj4+CmVuZG9iagoyMCAwIG9iago8PAovVHlwZSAvQ2F0YWxvZwovUGFnZXMgMSAwIFIKL09wZW5BY3Rpb24gWzMgMCBSIC9GaXRIIG51bGxdCi9QYWdlTGF5b3V0IC9PbmVDb2x1bW4KPj4KZW5kb2JqCnhyZWYKMCAyMQowMDAwMDAwMDAwIDY1NTM1IGYgCjAwMDAwMDExNTcgMDAwMDAgbiAKMDAwMDAwMjk3NCAwMDAwMCBuIAowMDAwMDAwMDE1IDAwMDAwIG4gCjAwMDAwMDAxNTIgMDAwMDAgbiAKMDAwMDAwMTIxNCAwMDAwMCBuIAowMDAwMDAxMzM5IDAwMDAwIG4gCjAwMDAwMDE0NjkgMDAwMDAgbiAKMDAwMDAwMTYwMiAwMDAwMCBuIAowMDAwMDAxNzM5IDAwMDAwIG4gCjAwMDAwMDE4NjIgMDAwMDAgbiAKMDAwMDAwMTk5MSAwMDAwMCBuIAowMDAwMDAyMTIzIDAwMDAwIG4gCjAwMDAwMDIyNTkgMDAwMDAgbiAKMDAwMDAwMjM4NyAwMDAwMCBuIAowMDAwMDAyNTE0IDAwMDAwIG4gCjAwMDAwMDI2NDMgMDAwMDAgbiAKMDAwMDAwMjc3NiAwMDAwMCBuIAowMDAwMDAyODc4IDAwMDAwIG4gCjAwMDAwMDMyMjIgMDAwMDAgbiAKMDAwMDAwMzMwOCAwMDAwMCBuIAp0cmFpbGVyCjw8Ci9TaXplIDIxCi9Sb290IDIwIDAgUgovSW5mbyAxOSAwIFIKL0lEIFsgPDhCOTdEQjE5Njg2RjkzMEQzOTdCMEVDQkMyMjk1OTc0PiA8OEI5N0RCMTk2ODZGOTMwRDM5N0IwRUNCQzIyOTU5NzQ+IF0KPj4Kc3RhcnR4cmVmCjM0MTIKJSVFT0Y=",
               "test\\test.pdf");
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

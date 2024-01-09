# Easy-File

简单的本地文件工具，包括`级联删除`、`查询文件`等常用功能，以及常用工具，例如`Base64转换`、`字符串读写`等。

本组件基于Java源码，`FileHolder`继承自`java.io.File`，使用的查询条件使用的`FileFilter`与`Comparator<File>`，尽量做到无感使用。

## 使用举例

### 级联搜索

对文件及其子文件进行搜索，支持结果分页。直接在`FileHolder`中使用，表示了搜索此文件下的所有文件。

```java
// 新建FileHolder对象
FileHolder holder = new FileHolder("F:\\Code\\JAVA\\easy-file");
// 新建查询条件
FileQuery query = new FileQuery();
// 设置查询过滤，这里使用的是FileFilter。实例表示只查询文件名带有"page"的文件
query.setFileFilter(pathname -> pathname.getName().toUpperCase().contains("PAGE"));
// 设置结果排序，这里使用的是Comparator<File>。下面的实例表示文件夹优先且短文件名优先
query.setComparator((o1, o2) -> {
    if (o1.isDirectory() && o2.isFile()) {
        return -1;
    }
    if (o2.isDirectory() && o1.isFile()) {
        return 1;
    }
    return o1.getName().length() - o2.getName().length();
});
// 不分页大小
query.setSize(-1);
// 开始查询
FilePage page = holder.searchPage(query);
System.out.println(page);
```

搜索结果如下：

```text
FilePage{
  files=[F:\Code\JAVA\easy-file\src\main\java\idea\verlif\easy\file\page, F:\Code\JAVA\easy-file\target\classes\idea\verlif\easy\file\page, F:\Code\JAVA\easy-file\src\main\java\idea\verlif\easy\file\page\FilePage.java, F:\Code\JAVA\easy-file\target\classes\idea\verlif\easy\file\page\FilePage.class],
  total=4, size=4, current=1, pages=1
}
```

__FileHolder.queryPage则是相同的用法，不过是只查询子文件，并不会级联查询__

### 文件读写

文件读写可以直接通过`FileUtil`的`write`和`read`方法，因为`FileHolder`继承自`File`，所以也可以直接对`FileHolder`操作。

```java
File file = new File("F:\\temp\\FileTest\\a.txt");
FileUtil.writeStringToFile(file, "Hello World！");
System.out.println(FileUtil.readContentAsString(file));
```

### 文件复制

通过`FileUtil.copyFile`方法来复制文件或文件夹。

```java
File file = new File("test\\dir1\\a.txt");
FileUtil.writeStringToFile(file, "Hello World！444");
System.out.println(FileUtil.readContentAsString(file));
FileUtil.copyFile(file.getParentFile(), "test\\dir2", true);
```

## 功能

- __子文件查询和搜索__
  - `FileHolder.queryPage(FileQuery)`
  - `FileHolder.searchPage(FileQuery)`
- __Base64转换__
  - `FileHolder.toBase64()`
  - `File64Util.toBase64(File|String)`
  - `File64Util.toFile(String, File|String)`
- __级联删除__
  - `FileHolder.deleteCascaded()`
  - `FileUtil.deleteFile(File)`
- __级联复制__
  - `FileHolder.copyTo(File, Boolean)`
  - `FileUtil.copyFile(File, File, Boolean)`
- __字符串读取__
  - `FileUtil.readContentAsString(File, [Charset])`
  - `FileUtil.appendStringToFile(File, String, [Charset])`
  - `FileUtil.writeStringToFile(File, String, [Charset], [Boolean])`

## 依赖

1. 添加Jitpack仓库源

   maven

    ```xml
    <repositories>
       <repository>
           <id>jitpack.io</id>
           <url>https://jitpack.io</url>
       </repository>
    </repositories>
    ```

   Gradle

    ```text
    allprojects {
      repositories {
          maven { url 'https://jitpack.io' }
      }
    }
    ```

2. 添加依赖

   __lastVersion__ [![](https://jitpack.io/v/Verlif/easy-file.svg)](https://jitpack.io/#Verlif/easy-file)

   maven

   ```xml
      <dependencies>
          <dependency>
              <groupId>com.github.Verlif</groupId>
              <artifactId>easy-file</artifactId>
              <version>lastVersion</version>
          </dependency>
      </dependencies>
   ```

   Gradle

   ```text
   dependencies {
     implementation 'com.github.Verlif:easy-file:lastVersion'
   }
   ```

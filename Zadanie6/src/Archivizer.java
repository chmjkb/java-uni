import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.Stack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

class Archivizer implements ArchivizerInterface{
    private static void addFileToZip(File file, String base, ZipOutputStream zos) {
        /*
        Adds a file to a zip
         */
        try (FileInputStream fis = new FileInputStream(file)) {
            ZipEntry entry = new ZipEntry(base);
            zos.putNextEntry(entry);
            int b;
            while ((b = fis.read()) != -1) {
                zos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compress(String dir, String filename) {
        /*
        Compresses a directory located in dir parameter and stores it in filename.zip
         */
        File directory = new File(dir);
        File zip = new File(filename);

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip))){
            Stack<File> stack = new Stack<>();      // Stack for holding the directories
            stack.push(directory);                  // Pushing root to the stack

            while (!stack.isEmpty()){
                File currentDir = stack.pop();
                for (File f : currentDir.listFiles()){
                    System.out.println(currentDir.listFiles());
                    if (f.isDirectory()){
                        stack.push(f);
                    }
                    if (!f.isDirectory()){
                        // Zipping if we encounter a file
                        Path dirPath = directory.toPath();
                        String base = dirPath.relativize(f.toPath()).toString();
                        addFileToZip(f, base, zos);
                    }
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return (int) zip.length();
    }


    @Override
    public void decompress(String filename, String dir) {
        try (ZipFile zf = new ZipFile(filename)) {
            Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zf.entries(); // Generating entries of a zip file
            while (entries.hasMoreElements()) {                                   // Iterating over entries
                ZipEntry e = entries.nextElement();
                String currentFilename = e.getName();
                File newFile = new File(dir + File.separator + currentFilename);
                Files.createDirectories(newFile.getParentFile().toPath());       // Creating a dir

                try (InputStream is = zf.getInputStream(e)) {
                    FileOutputStream fos = new FileOutputStream(newFile);       // Handling output stream
                    int b;
                    while ((b = is.read()) != -1){
                        fos.write(b);
                    }
                    // Closing stream
                    fos.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        Archivizer arch = new Archivizer();
        arch.compress("/Users/jakubchmura/Desktop/t1", "/Users/jakubchmura/Desktop/test.zip");
//        arch.decompress("/Users/jakubchmura/Desktop/test.zip", "/Users/jakubchmura/Desktop/tested");
    }
}
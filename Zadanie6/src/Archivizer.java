import java.io.*;
import java.nio.file.Path;
import java.util.Stack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

class Archivizer implements ArchivizerInterface{
    private static void addFileToZip(File file, String base, ZipOutputStream zos) {
        /*
        Adds a file to a zip
         */
        try (FileInputStream fis = new FileInputStream(file)) {
            ZipEntry entry = new ZipEntry(base);
            zos.putNextEntry(entry);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zos.write(buffer, 0, length);
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

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip + ".zip"))){
            Stack<File> stack = new Stack<>();      // Stack for holding the directories
            stack.push(directory);                  // Pushing root to the stack

            while (!stack.isEmpty()){
                File currentDir = stack.pop();
                for (File f : currentDir.listFiles()){
                    if (f.isDirectory() && !f.isHidden()){
                        stack.push(f);
                    } else{ if (f.isHidden()) {continue;} }  // Ignoring hidden directories

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
        File destinationDir = new File(dir);
        if(!destinationDir.exists()) destinationDir.mkdirs();
        byte[] buffer = new byte[1024];
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(filename))){
            ZipEntry ze = zis.getNextEntry();
            while (ze != null){
                String f = ze.getName();
                File newFile = new File(dir + File.separator + f);
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
        } catch (IOException ex){
            System.out.println("No file!");
        }

    }

    public static void main(String[] args) {
        Archivizer arch = new Archivizer();
    }
}
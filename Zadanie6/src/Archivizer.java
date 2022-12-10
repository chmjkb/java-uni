import java.io.File;
import java.util.ArrayList;
import java.util.List;

//https://www.youtube.com/watch?v=7xGmuyvZksY&t=588
public class Archivizer implements ArchivizerInterface{
    public List<String> listFiles(String directoryPath) {
        /*
        Lists all files in a given directory and its subdirectories using recursion
         */
        File directory = new File(directoryPath);
        List<String> filePaths = new ArrayList<>();
        filePaths.addAll(listFilesRecursivelyUtil(directory));
        return filePaths;
    }

    public List<String> listFilesRecursivelyUtil(File directory) {
        /*
        Helper method for listFilesRecursively
         */
        File[] files = directory.listFiles();
        List<String> filePaths = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                filePaths.addAll(listFiles(file.getAbsolutePath()));
            } else {
                if (!file.isHidden()) {
                    filePaths.add(file.getAbsolutePath());
                }
            }
        }
        return filePaths;
    }

    public boolean isSubdir(String file, String parentDir){
        /*
        Checks whether a file is a part of the original dir, or a subdir
         */
        return (file.substring(parentDir.length() + 1)).contains("/");
    }

    @Override
    public int compress(String dir, String filename) {
        List<String> allFiles = listFiles(dir);

        return 1;
    }



    @Override
    public void decompress(String filename, String dir) {

    }

    public static void main(String[] args) {
        Archivizer arch = new Archivizer();
        arch.compress("/Users/jakubchmura/Desktop/Test_Java", "Dupa");
    }
}

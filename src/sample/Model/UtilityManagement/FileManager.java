package sample.Model.UtilityManagement;

import sample.Main;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FileManager {

    //Directories
    private static String urlStylesheets = "/resources";
    private static String urlDatafile = "src/sample/Resources/datafile/data.txt";

    //READ
    public static List<String> readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(urlDatafile)))) {
            String line;
            List<String> lineArray = new ArrayList<>();
            while ((line = reader.readLine()) != null)
                lineArray.add(line);
            return lineArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> readLineFromFile(String name) {
        List<String> resultset = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(urlDatafile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(name)) {
                    resultset = Arrays.asList(line.split(","));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultset;
    }

    private static List<String> readLineFromFileNameAndOccupation(String name, String occupation) {
        List<String> resultset = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(urlDatafile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] linearr = line.split(",");
                if (linearr[0].equals(name) && linearr[1].equals(occupation)) {
                    resultset = Arrays.asList(linearr);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultset;
    }

    public static boolean checkUserExists(String name) {
        return readLineFromFile(name) != null;
    }

    public static boolean checkLoginValid(String name, String occupation) {
        return readLineFromFileNameAndOccupation(name, occupation) != null;
    }

    //UPDATE/WRITE
    public static void writeFile(List<String> lineArray) throws IOException {
        FileWriter fw = new FileWriter(urlDatafile);
        BufferedWriter bw = new BufferedWriter(fw);

        for (String line : lineArray) {
            bw.write(line);
            bw.flush();
        }
        bw.close();
    }

    public static void writeLineToFile(String newline) throws IOException {
        FileWriter fw = new FileWriter(urlDatafile);
        BufferedWriter bw = new BufferedWriter(fw);

        String attribute = newline.split(",")[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(urlDatafile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(attribute)) {
                    bw.write(newline);
                    bw.flush();
                    bw.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendLineToFile(String newline) throws URISyntaxException, IOException {
        System.out.println(Files.notExists(Path.of("/sample/Resources/datafile/data.txt")));
        FileWriter fw = new FileWriter(new File(Main.class.getResource("/sample/Resources/datafile/data.txt").toURI()), false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(newline);
        bw.flush();
        bw.close();
    }

    private static URI getUriPathFromResources(String path) throws URISyntaxException {
        return new URI(Main.class.getResource(path).toExternalForm());
    }

    public static File getDatafileFromResources() throws URISyntaxException {
        return new File(getUriPathFromResources("/src/sample/Resources/datafile/data.txt"));
    }

    public static void writeFileTest(String str) {

        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            String url = "src/sample/Resources/datafile/data.txt";
            File file = new File(url);
            if (!file.exists())
                System.out.println(file.createNewFile());

            fw = new FileWriter(url, true);
            bw = new BufferedWriter(fw);

            if (file.length() != 0) bw.newLine();
            bw.write(str);

            bw.flush();
            fw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                    fw.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void writeFileTest2() {
        BufferedWriter bw;
        try (FileWriter fw = new FileWriter("src/sample/Resources/datafile/data2.txt")) {
            bw = new BufferedWriter(fw);
            bw.write("test");
            //fw.write("test2"); //writes after bw

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("done");
    }

    public static Boolean checkDatafileExists() {
        try {
            Path path = Path.of(Main.class.getResource("/sample/Resources/datafile/data.txt").toURI());

            System.out.println(!Files.notExists(path));

            File file = new File(path.toUri());
            if (file.exists()) return true;
        } catch (URISyntaxException e) {
            e.getMessage();
        }
        return false;
    }

    private static String getProjectSourcePath() {
        return Paths.get("").toAbsolutePath().toString() + "/src";
    }

    public static boolean isDirectory(String url) {
        return new File(getProjectSourcePath() + url).isDirectory();
    }

    public static File[] getAllFilesFromDirectory(String path) {
        try {
            return new File(Main.class.getResource(path).toURI().toString()).listFiles();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}

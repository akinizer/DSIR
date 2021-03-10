package sample.SuperiorManagement.UtilityManagement;

import javafx.application.Application;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FileManager {

    //Directories
    private static String urlStylesheets="/resources";
    private static String urlDatafile="sample/Resources/datafile/data.txt";

    private static Class mainclass;

    public static void setClass(Class mainclass){
        FileManager.mainclass=mainclass;
    }

    //READ
    public static List<String> readFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(urlDatafile)))) {
            String line;
            List<String> lineArray=new ArrayList<>();
            while ((line = reader.readLine()) != null)
                lineArray.add(line);
            return lineArray;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> readLineFromFile(String attribute){
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(urlDatafile)))) {
            String line;
            while ((line=reader.readLine()) != null){
                if(line.startsWith(attribute)){
                    return Arrays.asList(line.split(","));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //UPDATE/WRITE
    public static void writeFile(List<String> lineArray) throws IOException {
        FileWriter fw = new FileWriter(urlDatafile);
        BufferedWriter bw = new BufferedWriter(fw);

        for (String line: lineArray) {
            bw.write(line);
            bw.flush();
        }
        bw.close();
    }

    public static void writeLineToFile(String newline) throws IOException {
        FileWriter fw = new FileWriter(urlDatafile);
        BufferedWriter bw = new BufferedWriter(fw);

        String attribute=newline.split(",")[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(urlDatafile)))) {
            String line;
            while ((line=reader.readLine()) != null){
                if(line.startsWith(attribute)){
                    bw.write(newline);
                    bw.flush();
                    bw.close();
                    return;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendLineToFile(String newline, Class mainclass) throws URISyntaxException, IOException {
        System.out.println(Files.notExists(Path.of("/sample/Resources/datafile/data.txt")));
        FileWriter fw = new FileWriter(new File(mainclass.getResource("/sample/Resources/datafile/data.txt").toURI()),false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(newline);
        bw.flush();
        bw.close();



    }

    public static URI getUriPathFromResources(String path) throws URISyntaxException{
        return new URI(mainclass.getResource(path).toExternalForm());
    }

    public static File getDatafileFromResources(Class mainclass) throws URISyntaxException {
        return new File(getUriPathFromResources("/src/sample/Resources/datafile/data.txt"));
    }

    public static void writeFileTest(String str){

        BufferedWriter bw=null;
        FileWriter fw=null;
        try {
            String url="src/sample/Resources/datafile/data.txt";
            File file = new File(url);
            if(!file.exists())
                System.out.println(file.createNewFile());

            fw = new FileWriter(url,true);
            bw = new BufferedWriter(fw);

            if(file.length()!=0) bw.newLine();
            bw.write(str);

            bw.flush();
            fw.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(bw != null) {
                    bw.close();
                    fw.close();
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

    public static void writeFileTest2(Class mainclass){
        BufferedWriter bw = null;
        try (FileWriter fw = new FileWriter("src/sample/Resources/datafile/data2.txt")){
            bw=new BufferedWriter(fw);
            bw.write("test");
            //fw.write("test2"); //writes after bw

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("done");







        /*
        FileWriter fw = new FileWriter(file,false);
        System.out.println(fw);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("test");
        bw.flush();
        bw.close();

         */
    }

    private void checkpathExists(){
        try {
            Path path = Path.of(mainclass.getResource("/sample/Resources/datafile/data.txt").toURI());

            System.out.println(!Files.notExists(path));

            File file = new File(path.toUri());
            if(!file.exists())
                file.createNewFile();
        }
        catch (Exception e){
            e.getMessage();
        }
    }
}

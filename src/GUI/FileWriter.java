package GUI;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileWriter {

    private java.io.FileWriter fileWriter;
    private File file;

    public FileWriter(String name) {
        file = new File(name);
        try {
            file.createNewFile();
            fileWriter = new java.io.FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRow(List<String> values){
        for (String value : values) {
            try {
                this.fileWriter.write(value + ";");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void close(){
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
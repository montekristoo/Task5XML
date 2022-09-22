import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        Path path = Path.of(PathsInfo.ROOT_PATH.toString() + PathsInfo.MANAGER_FOLDER);
        Path pathToFile = Path.of(path + "//" + PathsInfo.MANAGER_FILE);
        int number;
        Files.createDirectories(path);
        if (!Files.exists(pathToFile)) {
            Files.writeString(pathToFile, "0");
            number = 0;
        }
        else {
            String value = Files.readString(pathToFile);
            System.out.println(value);
            number = Integer.parseInt(value);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(PathsInfo.ROOT_PATH + PathsInfo.MANAGER_FOLDER.toString() + PathsInfo.FILE_DATA));
        DocumentInfo documentInfo = new DocumentInfo(PathsInfo.ROOT_PAGE.toString(), PathsInfo.ROOT_TAG.toString(), writer, number);
        DataWriter.writeWithManager(documentInfo);
    }
}

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        String value = Files.readString(DataWriter.manager);
        BufferedWriter writer = new BufferedWriter(new FileWriter(PathsInfo.ROOT_PATH + PathsInfo.MANAGER_FOLDER.toString() + PathsInfo.FILE_DATA));
        int number = Integer.parseInt(value);
        DocumentInfo documentInfo = new DocumentInfo(PathsInfo.ROOT_PAGE.toString(), PathsInfo.ROOT_TAG.toString(), writer, number);
        DataWriter.writeWithManager(documentInfo);
    }
}

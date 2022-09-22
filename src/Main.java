import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(PathsInfo.ROOT_PATH + PathsInfo.ALL_DATA_FOLDER.toString() + PathsInfo.FILE_DATA));
        DocumentInfo documentInfo = new DocumentInfo(PathsInfo.ROOT_PAGE.toString(), PathsInfo.ROOT_TAG.toString(),writer);
        DataWriter.writeAllDataInSameFile(documentInfo);
    }
}
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(PathsInfo.ROOT_PATH.toString() + "//0.txt"));
        DocumentInfo documentInfo = new DocumentInfo(PathsInfo.ROOT_PAGE.toString(), PathsInfo.ROOT_TAG.toString(), writer);
        DataWriter.writeWithManager(documentInfo);
    }
}

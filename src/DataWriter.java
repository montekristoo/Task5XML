import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataWriter {

    public static int counter = 0;

    protected static void writeWithManager(DocumentInfo documentInfo) throws IOException, ParserConfigurationException, SAXException {
        String address = documentInfo.getAddress();
        String tag = documentInfo.getTag();
        BufferedWriter writer = documentInfo.getWriter();

        Document doc = getParsedXMLDocument(address);
        NodeList nodeList = doc.getElementsByTagName(tag);

        for (int iterator = 0; iterator < nodeList.getLength(); iterator++) {
            Node node = nodeList.item(iterator);
            Element eElement = (Element) node;
            String nodeAddress = eElement.getChildNodes().item(1).getTextContent();
            if (tag.equals(PathsInfo.BREAKPOINT.toString())) {
                writer.write(nodeAddress + "\n");
            }
            else {
                counter++;
                Path path = Path.of(PathsInfo.ROOT_PATH + PathsInfo.FOLDERS_PATH.toString() + "//" + counter);
                Files.createDirectories(path);
                writer = new BufferedWriter(new FileWriter(path.toString() + "//" + PathsInfo.FILE_DATA));
                DocumentInfo infoToSend = new DocumentInfo(nodeAddress, PathsInfo.BREAKPOINT.toString(), writer);
                writeWithManager(infoToSend);
                writer.close();
            }
        }
    }

    private static Document getParsedXMLDocument(String address) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(new URL(address).openStream());
    }

}

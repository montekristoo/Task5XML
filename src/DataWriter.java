import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

@SuppressWarnings("ConstantConditions")
public class DataWriter {

    public static int counter = 0;
    public static final Path manager = Path.of(PathsInfo.ROOT_PATH.toString() + PathsInfo.MANAGER_FOLDER + PathsInfo.MANAGER_FILE);

    protected static void writeWithManager(DocumentInfo documentInfo) throws IOException, ParserConfigurationException, SAXException {
        String address = documentInfo.getAddress();
        String tag = documentInfo.getTag();
        Document doc = getParsedXMLDocument(address);
        BufferedWriter writer = documentInfo.getWriter();
        int aux = documentInfo.getAux();
        NodeList nodeList = doc.getElementsByTagName(tag);

        for (int iterator = 0; iterator < nodeList.getLength(); iterator++) {
            Node node = nodeList.item(iterator);
            Element eElement = (Element) node;
            String nodeAddress = eElement.getChildNodes().item(1).getTextContent();
            System.out.println(counter);
            if (tag.equals(PathsInfo.BREAKPOINT.toString()) && aux == 0) {
                writeToManagerAndFile(writer, nodeAddress);
                counter++;
            }
            else if (tag.equals(PathsInfo.BREAKPOINT.toString()) && counter < aux) {
                counter++;
            }
            else if (tag.equals(PathsInfo.BREAKPOINT.toString()) && counter >= aux) {
                writeToManagerAndFile(writer, nodeAddress);
                counter++;
            }
            else {
                DocumentInfo infoToSend = new DocumentInfo(nodeAddress, PathsInfo.BREAKPOINT.toString(), writer, aux);
                writeWithManager(infoToSend);
            }
        }
    }

    private static void writeToManagerAndFile(BufferedWriter fileWriter, String url) throws IOException {
       fileWriter.flush();
       fileWriter.write(url + "\n");
       Files.write(manager, String.valueOf(counter).getBytes());
    }

    private static Document getParsedXMLDocument(String address) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(new URL(address).openStream());
    }

}

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
import java.util.ArrayList;
import java.util.List;

public class DataWriter {
    private static int fileCounter = 1;
    public static final List<String> linksBuffer = new ArrayList<>();
    protected static void writeLimitedData(DocumentInfo documentInfo) throws IOException, ParserConfigurationException, SAXException {

        String address = documentInfo.getAddress();
        String tag = documentInfo.getTag();
        int limit = documentInfo.getLimit();
        Document doc = getParsedXMLDocument(address);
        NodeList nodeList = doc.getElementsByTagName(tag);

        for (int iterator = 0; iterator < nodeList.getLength(); iterator++) {
            Node node = nodeList.item(iterator);
            Element eElement = (Element) node;
            String nodeAddress = eElement.getChildNodes().item(1).getTextContent();
            if (tag.equals(PathsInfo.BREAKPOINT.toString())) {
                linksBuffer.add(nodeAddress);
                if (linksBuffer.size() == limit) {
                    writeToFile();
                    fileCounter++;
                }
            }

            else {
                DocumentInfo infoToSend = new DocumentInfo(nodeAddress, PathsInfo.BREAKPOINT.toString(), limit);
                writeLimitedData(infoToSend);
                if (!tag.equals(PathsInfo.BREAKPOINT) && iterator == nodeList.getLength()-1) {
                    writeToFile();
                }
            }
        }
    }

    private static void writeToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(PathsInfo.ROOT_PATH + "//" + fileCounter + ".txt"));
        linksBuffer.forEach(url -> {
            try {
                writer.write(url + '\n');
                System.out.println(url);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        writer.close();
        linksBuffer.clear();
    }

    private static Document getParsedXMLDocument(String address) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(new URL(address).openStream());
    }

}

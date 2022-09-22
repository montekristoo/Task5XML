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

public class DataWriter {

    /**
     *
     * @param documentInfo Informations about current node, to know which link and when write to file because it's recursive approach.
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    protected static void writeAllDataInSameFile(DocumentInfo documentInfo) throws IOException, ParserConfigurationException, SAXException {
        String address = documentInfo.getAddress();
        String tag = documentInfo.getTag();
        BufferedWriter writer = documentInfo.getWriter();

        Document document = getParsedXMLDocument(address);
        NodeList nodeList = document.getElementsByTagName(tag);

        for (int iterator = 0; iterator < nodeList.getLength(); iterator++) {
            Node node = nodeList.item(iterator);
            Element eElement = (Element) node;
            String nodeAddress = eElement.getChildNodes().item(1).getTextContent();
            if (tag.equals(PathsInfo.BREAKPOINT.toString())) {
                System.out.println(nodeAddress);
                writer.write(nodeAddress + '\n');
            } else {
                DocumentInfo infoToSend = new DocumentInfo(nodeAddress, PathsInfo.BREAKPOINT.toString(), writer);
                writeAllDataInSameFile(infoToSend);
                if (iterator == nodeList.getLength() - 1) writer.close();
            }
        }
    }

    /**
     *
     * @param address Extracted address from node, return parsed document to access his meta-data.
     * @return Document
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private static Document getParsedXMLDocument(String address) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(new URL(address).openStream());
    }
}
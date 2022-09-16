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
import java.nio.file.Paths;

public class Main {

    static BufferedWriter writer = null;

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        writer = null;
        parseDocument("https://makeup.md/sitemap/sitemap.xml", "sitemap", writer);
    }
    private static void parseDocument(String adress, String tag, BufferedWriter functionWriter) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new URL(adress).openStream());
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName(tag);
        for (int iterator = 0; iterator < nodeList.getLength(); iterator++) {
            Node node = nodeList.item(iterator);
            Element eElement = (Element) node;
            String url = eElement.getChildNodes().item(1).getTextContent();
            if (tag.equals("url")) {
                functionWriter.write(url + '\n');
            } else {
                Files.createDirectory(Paths.get("C://Users//GDB-01//Desktop//Data//" + "document_" + iterator));
                functionWriter = new BufferedWriter(new FileWriter("C://Users//GDB-01" + "//Desktop//Data//" + "document_" + iterator + "//" + "data.txt"));
                parseDocument(url, "url", functionWriter);
                functionWriter.close();
            }
        }
    }
}

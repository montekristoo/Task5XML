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
    final static String parentPath = "C://Users//GDB-01//Desktop//Data";
    final static String folderName = "//document_";
    final static String fileName = "//data.txt";
    final static String allDataInOneFile = "//alldata.txt";
    final static String limitedUrls = "//limitedData.txt";
    final static String mainPage = "https://makeup.md/sitemap/sitemap.xml";
    final static String mainTag = "sitemap";
    final static int limitedLineSizes = 10000;
    static int limitIterator = 0;

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        writer = new BufferedWriter(new FileWriter(parentPath + allDataInOneFile));
        String mainPage = "https://makeup.md/sitemap/sitemap.xml";
        String mainTag = "sitemap";
        parseDocument(mainPage, mainTag, writer);
    }
    private static void parseDocument(String adress, String tag, BufferedWriter functionWriter) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new URL(adress).openStream());
        NodeList nodeList = doc.getElementsByTagName(tag);
        for (int iterator = 0; iterator < nodeList.getLength(); iterator++) {
            Node node = nodeList.item(iterator);
            Element eElement = (Element) node;
            String url = eElement.getChildNodes().item(1).getTextContent();
            if (tag.equals("url")) {
                functionWriter.write(url + '\n');
            } else {
                Files.createDirectory(Paths.get(parentPath + folderName + iterator));
                functionWriter = new BufferedWriter(new FileWriter(parentPath + folderName + iterator + fileName));
                parseDocument(url, "url", functionWriter);
                functionWriter.close();
            }
        }
    }
}

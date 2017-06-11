package lapr4.green.s2.core.n1150738.contacts.application;

import lapr4.green.s2.core.n1150738.contacts.domain.Profession;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a service to import Professions from a external XML file
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class ProfessionImporterService {

    public static final String PROFESSIONS_RES_FILE = "lapr4/green/s2/core/n1150738/contacts/res/professions.xml";

    public List<Profession> professions() throws IOException, SAXException, ParserConfigurationException {
        return professions(PROFESSIONS_RES_FILE);
    }

    List<Profession> professions(String resPath) throws ParserConfigurationException, IOException, SAXException {
        InputStream professionsRes = getClass().getClassLoader().getResourceAsStream(resPath);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(professionsRes);

        NodeList xmlProfessions = document.getElementsByTagName("profession");
        List<Profession> list = new LinkedList<>();

        for(int i = 0; i < xmlProfessions.getLength(); i++){
            Node xmlProfession = xmlProfessions.item(i);
            NodeList childs = xmlProfession.getChildNodes();

            for(int j = 0; j < childs.getLength(); j++){
                Node n = childs.item(j);
                if(n.getNodeName().equalsIgnoreCase("name")){
                    Profession p = new Profession(n.getTextContent());
                    if(p!=null){
                        list.add(p);
                    }
                    break;
                }
            }

        }
        return list;
    }
}

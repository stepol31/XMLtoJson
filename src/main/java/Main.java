import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        FunctionCreateFile.createXMLFile();
        List<Employee> list = parseXML("data.xml");
        String json = listToJson(list);
        writeString(json, "data2.json");
    }

    private static void writeString(String name, String fileName) {
        try (FileWriter file = new
                FileWriter(fileName)) {
            file.write(name);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String listToJson(List<Employee> list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        return gson.toJson(list, listType);
    }

    private static List<Employee> parseXML(String fileName) throws ParserConfigurationException, IOException, SAXException {
        List<Employee> list = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(fileName));
        Node staff = doc.getDocumentElement();
        NodeList childNodes = staff.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            if (Node.ELEMENT_NODE != childNodes.item(i).getNodeType()) {
                continue;
            }

            NodeList childElements = childNodes.item(i).getChildNodes();
            Employee employee = new Employee();
            for (int j = 0; j < childElements.getLength(); j++) {
                if (Node.ELEMENT_NODE != childElements.item(j).getNodeType()) {
                    continue;
                }
                fillEmployee(childElements, employee, j);
            }
            list.add(employee);
        }
        return list;
    }

    private static void fillEmployee(NodeList childElements, Employee employee, int j) {
        switch (childElements.item(j).getNodeName()) {
            case "id" -> employee.setId(Long.parseLong(childElements.item(j).getTextContent()));
            case "firstName" -> employee.setFirstName(childElements.item(j).getTextContent());
            case "lastName" -> employee.setLastName(childElements.item(j).getTextContent());
            case "country" -> employee.setCountry(childElements.item(j).getTextContent());
            case "age" -> employee.setAge(Integer.parseInt(childElements.item(j).getTextContent()));
        }
    }
}





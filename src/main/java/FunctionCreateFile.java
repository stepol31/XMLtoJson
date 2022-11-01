import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class FunctionCreateFile {
    public static void createXMLFile() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element staff = document.createElement("staff");
        document.appendChild(staff);
        Element employee = document.createElement("employee");
        staff.appendChild(employee);
        Element id = document.createElement("id");
        id.appendChild(document.createTextNode("1"));
        employee.appendChild(id);
        Element firstName = document.createElement("firstName");
        firstName.appendChild(document.createTextNode("John"));
        employee.appendChild(firstName);
        Element lastName = document.createElement("lastName");
        lastName.appendChild(document.createTextNode("Smith"));
        employee.appendChild(lastName);
        Element country = document.createElement("country");
        country.appendChild(document.createTextNode("USA"));
        employee.appendChild(country);
        Element age = document.createElement("age");
        age.appendChild(document.createTextNode("25"));
        employee.appendChild(age);

        Element secondEmployee = document.createElement("employee");
        staff.appendChild(secondEmployee);
        Element idSecond = document.createElement("id");
        idSecond.appendChild(document.createTextNode("2"));
        secondEmployee.appendChild(idSecond);
        Element firstNameSecond = document.createElement("firstName");
        firstNameSecond.appendChild(document.createTextNode("Inav"));
        secondEmployee.appendChild(firstNameSecond);
        Element lastNameSecond = document.createElement("lastName");
        lastNameSecond.appendChild(document.createTextNode("Petrov"));
        secondEmployee.appendChild(lastNameSecond);
        Element countrySecond = document.createElement("country");
        countrySecond.appendChild(document.createTextNode("RU"));
        secondEmployee.appendChild(countrySecond);
        Element ageSecond = document.createElement("age");
        ageSecond.appendChild(document.createTextNode("23"));
        secondEmployee.appendChild(ageSecond);

        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File("data.xml"));
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(domSource, streamResult);
    }
}



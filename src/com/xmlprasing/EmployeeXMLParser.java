package com.xmlprasing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class EmployeeXMLParser implements EmployeeParser {
    @Override
    public List<Employee> parseEmployees(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("employee");

            employees = IntStream.range(0, nodeList.getLength())
                    .mapToObj(nodeList::item)
                    .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                    .map(EmployeeXMLParser::createEmployee)
                    .collect(Collectors.toList());

        }catch (Exception e) {
            System.err.println("Error parsing XML file: " + e.getMessage());
        }

        return employees;


    }

    private static Employee createEmployee(Node node) {
        Element employeeElement = (Element) node;

        String id = employeeElement.getAttribute("id");
        String name = getTagValue("name", employeeElement);
        String role = getTagValue("role", employeeElement);
        String salary = getTagValue("salary", employeeElement);
        String age = getTagValue("age", employeeElement);
        String phone = getTagValue("phone", employeeElement);
        String address = getTagValue("address", employeeElement);
        return new Employee(id, name, role, salary, age, phone, address);
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        return (nodeList.getLength() > 0) ? nodeList.item(0).getTextContent() : "";
    }

}

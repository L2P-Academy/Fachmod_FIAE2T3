package controller;

import model.CarModel;
import model.CustomerModel;
import model.MotorcycleModel;
import model.VehicleModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLController {

    private final File vehicleFile = new File(".//res//xml//vehicles.xml");
    private final File customerFile = new File(".//res//xml//customer.xml");

    private Element createXMLElement(Document document, String tagName, String tagContent){
        Element element = document.createElement(tagName);
        element.setTextContent(tagContent);
        return element;
    }

    private Document loadXMLDocument(File outputFile, String rootTagName) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        Document doc = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            Element rootElement;
            // Check if file already exists
            if (outputFile.exists()) {
                // parse the content of the existing file
                doc = docBuilder.parse(vehicleFile);
            } else {
                // create new file
                doc = docBuilder.newDocument();
                // create new root-element
                rootElement = doc.createElement(rootTagName);
                // add root-element to the document
                doc.appendChild(rootElement);
            }
        }catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
        return doc;
    }

    private void transformToXML(Document doc, File outputFile){
        try {
            // Transform to XML and format it
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("res/xml/format.xslt")));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(outputFile);

            // save it as file
            transformer.transform(source, result);
        }catch (TransformerException e){
            e.printStackTrace();
        }
    }

    public void writeCustomerToList(CustomerModel customerModel){
        Document doc = loadXMLDocument(customerFile, "Customers");
        Element rootElement = doc.getDocumentElement();
        Date date = new Date();
        Element customer = doc.createElement("Customer");

        customer.appendChild(createXMLElement(doc, "name", customerModel.getName()));
        customer.appendChild(createXMLElement(doc, "dayOfBirth", String.valueOf(customerModel.getDayOfBirth().getTime())));
        customer.appendChild(createXMLElement(doc, "registeredAt", String.valueOf(customerModel.getRegisteredAt().getTime())));
        customer.appendChild(createXMLElement(doc, "address", customerModel.getAddress()));

        rootElement.appendChild(customer);

        transformToXML(doc, customerFile);

    }

    public ArrayList<String[]> readCustomerFromList(){
        Document doc = loadXMLDocument(customerFile, "Customers");
        ArrayList<String[]> vehicleData = new ArrayList<>();
        Element rootElement = doc.getDocumentElement();

        // Get all Vehicle nodes
        NodeList nodeList = rootElement.getElementsByTagName("Vehicle");

        // iterate over all Vehicle nodes
        for (int i = 0 ; i < nodeList.getLength() ; i++){
            Element element = (Element) nodeList.item(i);

            String name = element.getElementsByTagName("name").item(0).getTextContent();
            String dayOfBirth = element.getElementsByTagName("dayOfBirth").item(0).getTextContent();
            String registeredAt = element.getElementsByTagName("registeredAt").item(0).getTextContent();
            String address = element.getElementsByTagName("address").item(0).getTextContent();

            String[] tempData = {name, dayOfBirth, registeredAt, address};
            vehicleData.add(tempData);
        }
        return vehicleData;
    }

    public void writeVehicleToListXML(VehicleModel model, String type) {
        // ternary operator to check if the given object is a car or a bike model and cast it to the specific type
        CarModel carModel = (model instanceof CarModel) ? (CarModel) model : null;
        MotorcycleModel motorcycleModel = (model instanceof MotorcycleModel) ? (MotorcycleModel) model : null;

        List<String> colors = model.getColor();

        Document doc = loadXMLDocument(vehicleFile, "Vehicles");
        Element rootElement = doc.getDocumentElement();

        // create a vehicle node
        Element vehicle = doc.createElement("Vehicle");


        vehicle.appendChild(createXMLElement(doc, "vehicleType", type));

        vehicle.appendChild(createXMLElement(doc, "modelYear", String.valueOf(model.getModelYear())));

        vehicle.appendChild(createXMLElement(doc, "odometer", String.valueOf(model.getOdometer())));

        vehicle.appendChild(createXMLElement(doc, "model", model.getModel()));

        for (String color : colors) {
            vehicle.appendChild(createXMLElement(doc, "colour", color));
        }

        vehicle.appendChild(createXMLElement(doc, "priceDaily", String.valueOf(model.getPriceDaily())));

        vehicle.appendChild(createXMLElement(doc, "isAvailable", String.valueOf(model.getIsAvailable())));

        // check if given VehicleModel object is a car or a bike to add their unique variables to the XML file
        if (carModel != null) {
            vehicle.appendChild(createXMLElement(doc, "doors", String.valueOf(carModel.getDoors())));

            vehicle.appendChild(createXMLElement(doc, "hasAircon", String.valueOf(carModel.getHasAircon())));
        } else if (motorcycleModel != null) {
            vehicle.appendChild(createXMLElement(doc, "licenseType", String.valueOf(motorcycleModel.getLicenseType())));

            vehicle.appendChild(createXMLElement(doc, "hasTopCase", String.valueOf(motorcycleModel.getHasTopCase())));
        }

        // add the new vehicle node to the root node
        rootElement.appendChild(vehicle);

        transformToXML(doc, vehicleFile);
    }

    public ArrayList<String[][]> readVehicleFromListXML() {
        ArrayList<String[][]> vehicleData = new ArrayList<>();
        Document doc = loadXMLDocument(vehicleFile, "Vehicles");
        Element rootElement = doc.getDocumentElement();

        // Get all Vehicle nodes
        NodeList nodeList = rootElement.getElementsByTagName("Vehicle");

        // iterate over all Vehicle nodes
        for (int i = 0 ; i < nodeList.getLength() ; i++){
            // Get the node from index i
            Node node = nodeList.item(i);
            Element element = (Element) node;

            // get the VehicleModel attributes from the element
            String vehicleType = element.getElementsByTagName("vehicleType").item(0).getTextContent();
            String modelYear = element.getElementsByTagName("modelYear").item(0).getTextContent();
            String odometer = element.getElementsByTagName("odometer").item(0).getTextContent();
            String model = element.getElementsByTagName("model").item(0).getTextContent();

            NodeList colorsNodeList = element.getElementsByTagName("colour");
            String[] colorsArray = new String[colorsNodeList.getLength()];
            for (int j = 0; j < colorsNodeList.getLength(); j++){
                String color = colorsNodeList.item(j).getTextContent();
                colorsArray[j] = color;
            }
            String priceDaily = element.getElementsByTagName("priceDaily").item(0).getTextContent();
            String isAvailable = element.getElementsByTagName("isAvailable").item(0).getTextContent();

            // get the specific Vehicle attributes from the element
            if (vehicleType.equals("Car")) {
                String doors = element.getElementsByTagName("doors").item(0).getTextContent();
                String hasAircon = element.getElementsByTagName("hasAircon").item(0).getTextContent();
                String[][] dataTemp = {{vehicleType}, {modelYear}, {odometer}, {model}, colorsArray, {priceDaily}, {isAvailable}, {doors}, {hasAircon}};
                vehicleData.add(dataTemp);
            }else {
                String licenseType = element.getElementsByTagName("licenseType").item(0).getTextContent();
                String hasTopCase = element.getElementsByTagName("hasTopCase").item(0).getTextContent();
                String[][] dataTemp = {{vehicleType}, {modelYear}, {odometer}, {model}, colorsArray, {priceDaily}, {isAvailable}, {licenseType}, {hasTopCase}};
                vehicleData.add(dataTemp);
            }
        }
        return vehicleData;
    }
}

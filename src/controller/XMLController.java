package controller;

import model.CarModel;
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

public class XMLController {

    File output = new File(".//res//xml//vehicles.xml");

    private Element createXMLElement(Document document, String tagName, String tagContent){
        Element element = document.createElement(tagName);
        element.setTextContent(tagContent);
        return element;
    }

    public void writeVehicleListXML(VehicleModel model, String type) {
        // ternary operator to check if the given object is a car or a bike model and cast it to the specific type
        CarModel carModel = (model instanceof CarModel) ? (CarModel) model : null;
        MotorcycleModel motorcycleModel = (model instanceof MotorcycleModel) ? (MotorcycleModel) model : null;

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc;
            Element rootElement;
            // Check if file already exists
            if (output.exists()) {
                // parse the content of the existing file
                doc = docBuilder.parse(output);
                // get the child element and set it as root-element
                rootElement = doc.getDocumentElement();
            } else {
                // create new file
                doc = docBuilder.newDocument();
                // create new root-element
                rootElement = doc.createElement("Vehicles");
                // add root-element to the document
                doc.appendChild(rootElement);
            }
            // create a vehicle node
            Element vehicle = doc.createElement("Vehicle");


            vehicle.appendChild(createXMLElement(doc, "vehicleType", type));

            vehicle.appendChild(createXMLElement(doc, "modelYear", String.valueOf(model.getModelYear())));

            vehicle.appendChild(createXMLElement(doc, "odometer", String.valueOf(model.getOdometer())));

            vehicle.appendChild(createXMLElement(doc, "model", model.getModel()));

            // TODO: Add color to XML writer

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

            // Transform to XML and format it
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("res/xml/format.xslt")));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(output);

            // save it as file
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> readVehicleListXML() {
        ArrayList<String[]> vehicleData = new ArrayList<>();
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc;
            Element rootElement;
            // Check if file already exists
            if (output.exists()) {
                // parse the content of the existing file
                doc = docBuilder.parse(output);
                // get the child element and set it as root-element
                rootElement = doc.getDocumentElement();
            }else {
                return new ArrayList<>();
            }

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
                // TODO: Add colour to XML reader
                String priceDaily = element.getElementsByTagName("priceDaily").item(0).getTextContent();
                String isAvailable = element.getElementsByTagName("isAvailable").item(0).getTextContent();

                // get the specific Vehicle attributes from the element
                if (vehicleType.equals("Car")) {
                    String doors = element.getElementsByTagName("doors").item(0).getTextContent();
                    String hasAircon = element.getElementsByTagName("hasAircon").item(0).getTextContent();
                    String[] dataTemp = {vehicleType, modelYear, odometer, model, priceDaily, isAvailable, doors, hasAircon};
                    vehicleData.add(dataTemp);
                }else {
                    String licenseType = element.getElementsByTagName("licenseType").item(0).getTextContent();
                    String hasTopCase = element.getElementsByTagName("hasTopCase").item(0).getTextContent();
                    String[] dataTemp = {vehicleType, modelYear, odometer, model, priceDaily, isAvailable, licenseType, hasTopCase};
                    vehicleData.add(dataTemp);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return vehicleData;
    }
}

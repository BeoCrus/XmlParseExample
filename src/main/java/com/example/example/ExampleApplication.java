package com.example.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class ExampleApplication {
    private final static String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<sic_response>\n" +
            "    <request_id>1683969799</request_id>\n" +
            "    <request>\n" +
            "        <target_system>xfer</target_system>\n" +
            "        <proc_name>exchangerates</proc_name>\n" +
            "        <proc_params>\n" +
            "            <param>\n" +
            "                <name>rateType</name>\n" +
            "                <value>ALL</value>\n" +
            "            </param>\n" +
            "            <param>\n" +
            "                <name>currencyCode</name>\n" +
            "                <value>KZT</value>\n" +
            "            </param>\n" +
            "            <param>\n" +
            "                <name>effectiveDate</name>\n" +
            "                <value>2023-05-12T08:00:00Z</value>\n" +
            "            </param>\n" +
            "        </proc_params>\n" +
            "    </request>\n" +
            "    <result>\n" +
            "        <result_data>\n" +
            "            <exchangeRatesList_1_buyRate>7.9662</exchangeRatesList_1_buyRate>\n" +
            "            <exchangeRatesList_2_participantId>10039</exchangeRatesList_2_participantId>\n" +
            "            <exchangeRatesList_2_buyRate>7.9662</exchangeRatesList_2_buyRate>\n" +
            "            <exchangeRatesList_0_buyRate>7.9662</exchangeRatesList_0_buyRate>\n" +
            "            <exchangeRatesList_3_buyRate>7.9662</exchangeRatesList_3_buyRate>\n" +
            "            <exchangeRatesList_0_sellRate>7.656862745135</exchangeRatesList_0_sellRate>\n" +
            "            <exchangeRatesList_0_currencyCode>KZT</exchangeRatesList_0_currencyCode>\n" +
            "            <exchangeRatesList_2_currencyCode>KZT</exchangeRatesList_2_currencyCode>\n" +
            "            <exchangeRatesList_3_currencyCode>KZT</exchangeRatesList_3_currencyCode>\n" +
            "            <exchangeRatesList_1_currencyCode>KZT</exchangeRatesList_1_currencyCode>\n" +
            "            <exchangeRatesList_0_participantId>10038</exchangeRatesList_0_participantId>\n" +
            "            <exchangeRatesList_1_sellRate>7.656862745135</exchangeRatesList_1_sellRate>\n" +
            "            <exchangeRatesList_1_participantId>10055</exchangeRatesList_1_participantId>\n" +
            "            <exchangeRatesList_3_sellRate>7.656862745135</exchangeRatesList_3_sellRate>\n" +
            "            <exchangeRatesList_3_participantId>10027</exchangeRatesList_3_participantId>\n" +
            "            <exchangeRatesList_2_sellRate>7.656862745135</exchangeRatesList_2_sellRate>\n" +
            "            <effectiveDate>2023-05-12T08:00:00Z</effectiveDate>\n" +
            "        </result_data>\n" +
            "        <result_code>0</result_code>\n" +
            "        <result_desc>OK</result_desc>\n" +
            "        <ext_result_code></ext_result_code>\n" +
            "        <ext_result_desc></ext_result_desc>\n" +
            "    </result>\n" +
            "    <date>13.05.2023 15:23:20</date>\n" +
            "    <sic_ver>2.38.07</sic_ver>\n" +
            "</sic_response>";

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
        XMLHelper xmlHelper = new XMLHelper();
        try {
            Document doc = xmlHelper.getDocumentBuilder().parse(
                    xmlHelper.getInputStream(xml)
            );
            doc.getDocumentElement().normalize();
            Node sicRespone = xmlHelper.getChildNodeByName(doc, "sic_response");
            Node result = xmlHelper.getChildNodeByName(sicRespone, "result");
            Node resultData = xmlHelper.getChildNodeByName(result, "result_data");
            Map<String, String> resultDataMap = xmlHelper.getElementKeyValue(resultData.getChildNodes());

            Map<String, Map<String, String>> exchangeRates = new HashMap<>();
            //collect all exchange rates into map of maps, e.g.:
            // {"1": {buyRate: 7.9662, sellRate: 7.656862745135, currencyCode: KZT}, "2": {...}, ...}
            for (Map.Entry<String, String> entry : resultDataMap.entrySet()) {
//                System.out.println(entry.getKey() + " = " + entry.getValue());
                if(entry.getKey().split("_")[0].equals("exchangeRatesList")) {
                    if(entry.getKey().split("_").length != 3) {
                        //or throw exception
                        continue;
                    }
                    if(!exchangeRates.containsKey(entry.getKey().split("_")[1])) {
                        exchangeRates.put(entry.getKey().split("_")[1], new HashMap<>());
                    }
                    exchangeRates.get(entry.getKey().split("_")[1]).put(entry.getKey().split("_")[2], entry.getValue());
                }
            }
            List<XmlCustomObject> resultObjects = new ArrayList<>();
            for (Map.Entry<String, Map<String, String>> entry : exchangeRates.entrySet()) {
                Map<String, String> exchangeRate = entry.getValue();
                XmlCustomObject xmlCustomObject = new XmlCustomObject(
                        exchangeRate.getOrDefault("participantId", ""),
                        exchangeRate.getOrDefault("buyRate", ""),
                        exchangeRate.getOrDefault("sellRate", ""),
                        exchangeRate.getOrDefault("currencyCode", "")
                );
                resultObjects.add(xmlCustomObject);
            }
            System.out.println(resultObjects);

        } catch (SAXParseException e) {
            //log error
        } catch (SAXException e) {
            //log error
        } catch (IOException e) {
            //log error
        }

    }

//    private List<XmlCustomObject> getXmlObjectFromString(String xml) {
//    }



}

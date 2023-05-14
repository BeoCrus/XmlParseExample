package com.example.example;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Simple XML Class -
 * This XML Class is a simple helper only for specific case. Originally developed to get
 * key and value of flat xml.
 * Feel free to use this piece of code as you like with/out permission - if possible mention this site.
 * I am not responsible for any consequences that would result after using this piece of code.
 *
 * @author http://gullele.com
 */
public class XMLHelper {
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;

    public XMLHelper(){
        try{
            this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
            this.documentBuilder = this.documentBuilderFactory.newDocumentBuilder();
        }
        catch(ParserConfigurationException pce){
            //log error
        }
    }

    /**
     * Provides NodeList from the given Document object by the tagName
     * @param Document - doc
     * @param tagname
     * @return NodeList
     */
    public NodeList getNodeListByTag(Document doc, String tagname){
        NodeList nodeList = null;
        if (doc !=null && !tagname.isEmpty()){
            nodeList = doc.getElementsByTagName(tagname);
        }
        return nodeList;
    }

    public Node getChildNodeByName(Node node, String name){
        Node childNode = null;
        if (node!=null && !name.isEmpty()){
            NodeList nodeList = node.getChildNodes();
            for(int i=0 ; i < nodeList.getLength() ; i++){
                Node tempNode = nodeList.item(i);
                if (tempNode.getNodeName().equals(name)){
                    childNode = tempNode;
                    break;
                }
            }
        }
        return childNode;
    }

    /**
     *
     * @param nodeList
     * @return Map
     */
    public Map getElementKeyValue(NodeList nodeList){
        Map elements = new HashMap();
        if (nodeList!=null && nodeList.getLength()>0 ){
            for(int i=0 ; i < nodeList.getLength() ; i++){
                Node node = nodeList.item(i); //Take the node from the list
                NodeList valueNode = node.getChildNodes(); // get the children of the node
                String value = (valueNode.item(0)!=null) ? valueNode.item(0).getNodeValue() : "";
                elements.put(node.getNodeName(), value);
            }
        }
        return elements;
    }

    /**
     * Returns InputString given string
     * @param string
     * @return InputStream
     */
    public InputStream getInputStream(String string){
        InputStream inputStream = null;
        if (!string.isEmpty()){
            try{
                inputStream = new ByteArrayInputStream(string.getBytes("UTF-8"));
            }
            catch(UnsupportedEncodingException uee){
            }
        }
        return inputStream;
    }

    /**
     * Setters and getters
     * @return
     */
    public DocumentBuilderFactory getDocumentBuilderFactory() {
        return documentBuilderFactory;
    }
    public void setDocumentBuilderFactory(
            DocumentBuilderFactory documentBuilderFactory) {
        this.documentBuilderFactory = documentBuilderFactory;
    }
    public DocumentBuilder getDocumentBuilder() {
        return documentBuilder;
    }
    public void setDocumentBuilder(DocumentBuilder documentBuilder) {
        this.documentBuilder = documentBuilder;
    }
}
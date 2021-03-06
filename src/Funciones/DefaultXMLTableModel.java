package Funciones;
import javax.swing.*;  
import javax.swing.table.TableModel;  
import javax.swing.table.DefaultTableModel;  
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import org.w3c.dom.Document;  
import org.w3c.dom.Node;  
import org.w3c.dom.Element;  
import org.w3c.dom.NodeList;  
  
import java.awt.*;  
import java.io.File;  
import java.awt.event.*;  
  
public class DefaultXMLTableModel extends DefaultTableModel {        
    public File xml = null;  
    public Document dom = null;   
    public JScrollPane jScrollPane1;  
      
    public DefaultXMLTableModel() {   
        xml = new File(System.getProperty("user.dir") + File.separator + "inventario.xml");          
           
         
        //add some columns  
        addColumn("Codigo");  
        addColumn("Nombre");  
        addColumn("Precio");  
        addColumn("Existencia");
        addColumn("Proveedor");
                  
        //if the xml file exists at the current location in the default user directory  
        //then parse the xml data with the parseFile(File file) method        
        if (xml.exists() && xml.length() != 0) {  
            dom = parseFile(xml);  
            insertTableRows(this,dom);  
        }            
    }  
      
     
      
    //creates an instance of a Document object    
    public Document parseFile(File file) {  
        try {  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder = factory.newDocumentBuilder();       
        dom = (Document) builder.parse(file);  
        } catch (Exception e) { e.printStackTrace(); }    
        return dom;  
    }  
      
    public void insertTableRows(DefaultTableModel tableModel,Document doc) {              
        Element root = doc.getDocumentElement();  
        NodeList list = root.getElementsByTagName("producto");  
        for (int i = 0; i < list.getLength(); ++i) {  
            Element e = (Element) list.item(i);  
            if (e.getNodeType() == Element.ELEMENT_NODE) {  
                Object[] row = { getArticleInfo("Codigo",e),getArticleInfo("Nombre",e),getArticleInfo("Precio",e),getArticleInfo("Existencia",e),getArticleInfo("Proveedor",e) };  
                tableModel.addRow(row);               
            }  
        }         
          
        tableModel.fireTableStructureChanged();   
        tableModel.fireTableDataChanged();  
    }  
      
    public Object getArticleInfo(String tagName, Element elem) {      
        NodeList list = elem.getElementsByTagName(tagName);  
        for (int i = 0; i < list.getLength(); ++i) {  
            Node node = (Node) list.item(i);  
            if (node.getNodeType() == Node.ELEMENT_NODE) {  
                Node child = (Node) node.getFirstChild();  
                return child.getTextContent().trim();  
            }  
              
            return null;  
        }  
      
        return null;  
    }  
}  
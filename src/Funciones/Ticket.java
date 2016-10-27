package Funciones;
import Funciones.Factura;
import Funciones.FileChuser;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Mauricio Martinez 12041156
 * ____  __  ___  __  __  __   ___     __  _  _  __   _  _  ___   __   ___    __   ___    ___  ___ 
 *(_  _)/  \(  ,\(  )/ _)/  \ / __)   (  )( )( )(  ) ( \( )(_  ) (  ) (   \  /  \ / __)  (   \(  _)
 *  )( ( () )) _/ )(( (_( () )\__ \   /__\ \\// /__\  )  (  / /  /__\  ) ) )( () )\__ \   ) ) )) _)
 * (__) \__/(_)  (__)\__)\__/ (___/  (_)(_)(__)(_)(_)(_)\_)(___)(_)(_)(___/  \__/ (___/  (___/(___)
 * ___  ___   __    __  ___    __   __  __   __   __  __  __  _  _ 
 *(  ,\(  ,) /  \  / _)(  ,)  (  ) (  \/  ) (  ) / _)(  )/  \( \( )
 * ) _/ )  \( () )( (/\ )  \  /__\  )    (  /__\( (_  )(( () ))  ( 
 *(_)  (_)\_)\__/  \__/(_)\_)(_)(_)(_/\/\_)(_)(_)\__)(__)\__/(_)\_)
 */
public class Ticket{
    // Se crea el documentoEste es el segundo y tiene una fuente rara
    File archivo;
    FileChuser dir=new FileChuser();
    String directorio;
    public Ticket(String[] lista,  String [] articulos, String [] precios, String[] precunit) throws FileNotFoundException, DocumentException{
        directorio=dir.obtenerDirectorio(".pdf");
        this.archivo = new File(directorio);
        Document documento = new Document();
        FileOutputStream ficheroPdf = new FileOutputStream(archivo);
        PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
        documento.open();
        try {
            URL url = this.getClass().getResource("/imagensitas/logopdf.png");
            Image foto = Image.getInstance(url);
            foto.setAlignment(Chunk.ALIGN_MIDDLE);
            documento.add(foto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Paragraph parrafo1=new Paragraph("Deportes \"El Inge\" SA. de CV.");
        parrafo1.setAlignment(1);
        documento.add(parrafo1);
        Paragraph parrafo2=new Paragraph("Blvd. Francisco Villa No. 3455");
        parrafo2.setAlignment(1);
        documento.add(parrafo2);
        Paragraph parrafo3=new Paragraph("Teléfono: 8-13-23-67");
        parrafo3.setAlignment(1);
        documento.add(parrafo3);
        Paragraph parrafo4=new Paragraph("Correo: deportes_inge@tristemon.com");
        parrafo4.setAlignment(1);
        documento.add(parrafo4);
        PdfPTable tabla=new PdfPTable(4);
        tabla.addCell("Producto");
        tabla.addCell("Cantidad");
        tabla.addCell("Precio Unitario");
        tabla.addCell("Total");
        int []cantidad=new int[lista.length];
        for(int x=0; x<cantidad.length; x++){
            cantidad[x]=0;
        }
        String []total=new String[lista.length];
        String []producto=new String[lista.length];
        for(int a=0; a<lista.length; a++){
            for(int b=0; b<articulos.length; b++){
                if(lista[a].equals(articulos[b])){
                    cantidad[a]=cantidad[a]+1;
                    producto[a]=articulos[b];
                }
            }
            if(producto[a]!=null){
                tabla.addCell(producto[a]);
                tabla.addCell(Integer.toString(cantidad[a]));
                tabla.addCell(precunit[a]);
                total[a]=Double.toString(Double.parseDouble(precunit[a])*cantidad[a]);
                tabla.addCell(total[a]);
            }
        }
        documento.add(tabla);
        double tot=0;
        for(int c=0;c<total.length;c++){
            if(total[c]!=null){
                tot=tot+Double.parseDouble(total[c]);
                
            }
        }
        double iva=tot*0.16;
        double neto=tot+iva;
        Paragraph parrafo5=new Paragraph("Total: $"+tot);
        parrafo5.setAlignment(Element.ALIGN_CENTER);
        documento.add(parrafo5);
        Paragraph parrafo6=new Paragraph("IVA: $"+iva);
        parrafo6.setAlignment(Element.ALIGN_CENTER);
        documento.add(parrafo6);
        Paragraph parrafo7=new Paragraph("Total Neto: $"+neto,
                FontFactory.getFont("arial",   // fuente
                22,                            // tamaño
                Font.BOLD));
        parrafo7.setAlignment(Element.ALIGN_CENTER);
        documento.add(parrafo7);
        documento.close();
        ejecutarPDF();
    }
    public void ejecutarPDF(){
        String d="";
        try { 
            
            if("/".equals(directorio.substring(0, 1))){
                Runtime.getRuntime().exec("evince "+directorio);
            }
            if(":".equals(directorio.substring(1,2))){
                char signo=(char) 92;
                String [] arreglo=directorio.split("\\\\");
                for(int a=0; a<arreglo.length;a++){
                    d=d+arreglo[a]+"\\\\\\\\";
                }
                Desktop.getDesktop().open(new File(d));
            }
        } catch (IOException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
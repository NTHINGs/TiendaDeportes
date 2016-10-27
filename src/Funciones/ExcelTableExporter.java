
package Funciones;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import jxl.write.*;
import jxl.*;

public class ExcelTableExporter {

private File file;
private JTable table;
private String nombreTab;
public ExcelTableExporter(JTable table,File file,String nombreTab){
this.file=file;
this.table=table;
this.nombreTab=nombreTab;
System.out.println(file);
export();
try { 
            Runtime.getRuntime().exec("libreoffice4.2 --calc "+file);
        } catch (IOException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
}



public boolean export(){
try
{
DataOutputStream out=new DataOutputStream(new FileOutputStream(file));

WritableWorkbook w = Workbook.createWorkbook(out);

WritableSheet s = w.createSheet(nombreTab, 0);
for (int i = 0; i < table.getColumnCount(); i++) {
                        Object objeto=table.getValueAt(i,0);
			s.addCell(new Label(i, 0, String.valueOf(objeto)));
                    } 
for(int i=0;i< table.getRowCount();i++){	
	for(int j=0;j<table.getColumnCount();j++){
                        s.addCell(new Label(i,j,table.getColumnName(i)));
}
}
w.write();
w.close();
out.close();



return true;

}catch(IOException ex){ex.printStackTrace();}
catch(WriteException ex){ex.printStackTrace();}
return false;
}

}

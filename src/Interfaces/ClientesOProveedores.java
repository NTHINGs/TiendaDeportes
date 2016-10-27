package Interfaces;
import Funciones.Factura;
import Funciones.*;
import com.itextpdf.text.DocumentException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
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
public class ClientesOProveedores extends JFrame implements ActionListener{
    JLabel tit1=new JLabel(), l1=new JLabel("Nombre:"), l2=new JLabel("Dirección:"), l3=new JLabel(), l4=new JLabel("Teléfono:");
    
    JTextField t1=new JTextField(20), t2=new JTextField(20), t3=new JTextField(20), t4=new JTextField(20);
    JButton alta=new JButton("Dar De Alta"), limpiar=new JButton("Limpiar Datos");
    
    JLabel tit2=new JLabel(), l5=new JLabel("Buscar:");
    JTextField t5=new JTextField(10);
    JButton buscar=new JButton("Buscar"), seleccionar=new JButton("Seleccionar");
    JTable tabla=new JTable();
    TransparentPanel existentes;
    DefaultTableModel dft;
    JMenuItem borrar=new JMenuItem("Borrar Seleccionado"), guardar=new JMenuItem("Guardar Cambios");
    String tipo;
    String [] datos=new String[4];
    String[] lista;
    String[] articulos;
    String[] precios;
    String[] precunit;
    ClientesOProveedores(boolean cliente, String[] l, String[] a, String [] p, String[] pu){
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        lista=l;
        articulos=a;
        precios=p;
        precunit=pu;
        TransparentPanel izquierda=new TransparentPanel();
        izquierda.setLayout(new GridBagLayout());
        existentes=new TransparentPanel();
        existentes.setLayout(new GridBagLayout());
        borrar.addActionListener(this);
        borrar.setToolTipText("Borrar el registro seleccionado");
        JMenu opciones=new JMenu("Opciones");
        opciones.add(borrar);
        guardar.addActionListener(this);
        guardar.setToolTipText("Guardar Cambios");
        opciones.addSeparator();
        opciones.add(guardar);
        JMenuBar barrita=new JMenuBar();
        barrita.add(opciones);
        setJMenuBar(barrita);
        if(cliente==true){
            setTitle("Clientes");
            tit1.setText("Nuevo Cliente");
            tit2.setText("Cliente Existente");
            l3.setText("R.F.C:");
            GridBagConstraints config16=new GridBagConstraints();
            config16.gridx=3;
            config16.gridy=1;
            seleccionar.addActionListener(this);
            seleccionar.setContentAreaFilled(false);
            seleccionar.setBorderPainted(false);
            seleccionar.setFocusPainted(false);
            seleccionar.setForeground(new Color(0,255,154));
            seleccionar.setFont(new Font("Arial", 0, 18));
            existentes.add(seleccionar,config16);
            dft=new XMLClientes();
            tipo="cliente";
        }else{
            setTitle("Proveedores");
            tit1.setText("Nuevo Proveedor");
            tit2.setText("Proveedor Existente");
            l3.setText("Conceptos:");
            dft=new XMLProveedores();
            tipo="proveedore";
        }
        GridBagConstraints config1=new GridBagConstraints();
        config1.gridx=0;
        config1.gridy=0;
        config1.gridwidth=2;
        tit1.setForeground(new Color(0,255,154));
        tit1.setFont(new Font("Arial", 0, 54));
        izquierda.add(tit1,config1);
        
        GridBagConstraints config2=new GridBagConstraints();
        config2.gridx=0;
        config2.gridy=1;
        l1.setForeground(new Color(0,255,154));
        l1.setFont(new Font("Arial", 0, 24));
        izquierda.add(l1,config2);
        
        GridBagConstraints config3=new GridBagConstraints();
        config3.gridx=1;
        config3.gridy=1;
        izquierda.add(t1,config3);
        
        GridBagConstraints config4=new GridBagConstraints();
        config4.gridx=0;
        config4.gridy=2;
        l2.setForeground(new Color(0,255,154));
        l2.setFont(new Font("Arial", 0, 24));
        izquierda.add(l2,config4);
        
        GridBagConstraints config5=new GridBagConstraints();
        config5.gridx=1;
        config5.gridy=2;
        izquierda.add(t2,config5);
        
        GridBagConstraints config6=new GridBagConstraints();
        config6.gridx=0;
        config6.gridy=3;
        l3.setForeground(new Color(0,255,154));
        l3.setFont(new Font("Arial", 0, 24));
        izquierda.add(l3,config6);
        
        GridBagConstraints config7=new GridBagConstraints();
        config7.gridx=1;
        config7.gridy=3;
        izquierda.add(t3,config7);
        
        GridBagConstraints config8=new GridBagConstraints();
        config8.gridx=0;
        config8.gridy=4;
        l4.setForeground(new Color(0,255,154));
        l4.setFont(new Font("Arial", 0, 24));
        izquierda.add(l4,config8);
        
        GridBagConstraints config9=new GridBagConstraints();
        config9.gridx=1;
        config9.gridy=4;
        izquierda.add(t4,config9);
        
        GridBagConstraints config10=new GridBagConstraints();
        config10.gridx=0;
        config10.gridy=5;
        config10.fill=GridBagConstraints.BOTH;
        alta.addActionListener(this);
        alta.setContentAreaFilled(false);
        alta.setBorderPainted(false);
        alta.setFocusPainted(false);
        alta.setForeground(new Color(0,255,154));
        alta.setFont(new Font("Arial", 0, 18));
        alta.setIcon(new ImageIcon(getClass().getResource("/imagensitas/selec.png")));
        izquierda.add(alta,config10);
        
        GridBagConstraints config11=new GridBagConstraints();
        config11.gridx=1;
        config11.gridy=5;
        config11.fill=GridBagConstraints.BOTH;
        limpiar.addActionListener(this);
        limpiar.setContentAreaFilled(false);
        limpiar.setBorderPainted(false);
        limpiar.setFocusPainted(false);
        limpiar.setForeground(new Color(0,255,154));
        limpiar.setFont(new Font("Arial", 0, 18));
        limpiar.setIcon(new ImageIcon(getClass().getResource("/imagensitas/selec.png")));
        izquierda.add(limpiar,config11);
        JPanel principal=new JPanel();
        principal.setLayout(new BorderLayout());
        principal.setBackground(Color.darkGray);
        principal.add(izquierda, BorderLayout.WEST);
        
        
        GridBagConstraints config12=new GridBagConstraints();
        config12.gridx=0;
        config12.gridy=0;
        config12.gridwidth=2;
        tit2.setForeground(new Color(0,255,154));
        tit2.setFont(new Font("Arial", 0, 24));
        existentes.add(tit2,config12);
        
        GridBagConstraints config13=new GridBagConstraints();
        config13.gridx=0;
        config13.gridy=1;
        l5.setForeground(new Color(0,255,154));
        l5.setFont(new Font("Arial", 0, 24));
        existentes.add(l5,config13);
        
        GridBagConstraints config14=new GridBagConstraints();
        config14.gridx=1;
        config14.gridy=1;
        t5.addActionListener(this);
        existentes.add(t5 ,config14);
        
        GridBagConstraints config15=new GridBagConstraints();
        config15.gridx=2;
        config15.gridy=1;
        buscar.addActionListener(this);
        buscar.setContentAreaFilled(false);
        buscar.setBorderPainted(false);
        buscar.setFocusPainted(false);
        buscar.setForeground(new Color(0,255,154));
        buscar.setFont(new Font("Arial", 0, 18));
        existentes.add(buscar,config15);
        
        TransparentPanel derecha=new TransparentPanel();
        derecha.setLayout(new BorderLayout());
        derecha.add(existentes, BorderLayout.NORTH);
        tabla.setModel(dft);
        tabla.getColumnModel().getColumn(0).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
        tabla.getColumnModel().getColumn(1).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
        tabla.getColumnModel().getColumn(2).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
        tabla.getColumnModel().getColumn(3).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
        JScrollPane scr=new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scr.setOpaque(false);
        scr.getViewport().setOpaque(false);
        scr.setBorder(null);
        scr.setViewportBorder(null);
        
        tabla.setBackground(Color.darkGray);
        tabla.setOpaque(false);
        tabla.setForeground(new Color(0,255,154));
        tabla.setSelectionBackground(new Color(0,255,154));
        tabla.setSelectionForeground(Color.darkGray);
        derecha.add(scr, BorderLayout.CENTER);
        
        principal.add(derecha, BorderLayout.EAST);
        setContentPane(principal);
        pack();
        show();
    }
    public void guardarTabla() throws Exception {
        String [] etiquetas=new String [5];
        String p="";
        BufferedWriter bfw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + File.separator + tipo+"s.xml"));
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            etiquetas[i]=tabla.getColumnName(i);
        }
        bfw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        bfw.newLine();
        bfw.write("<"+tipo+"s>");
        for (int i = 0; i < tabla.getRowCount(); i++) {
            bfw.newLine();
            if("proveedore".equals(tipo)){
                tipo="proveedor";
            }
            bfw.write("<"+tipo+">");
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                bfw.newLine();
                bfw.write("<"+tabla.getColumnName(j)+">"+(String) (tabla.getValueAt(i, j)+"</"+tabla.getColumnName(j)+">"));
            }
            bfw.newLine();
            bfw.write("</"+tipo+">");
        }
        bfw.newLine();
        if("proveedor".equals(tipo)){
            tipo="proveedore";
        }
        bfw.write("</"+tipo+"s>");
        bfw.close();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj==alta){
            datos[0]=t1.getText();
            datos[1]=t2.getText();
            datos[2]=t3.getText();
            datos[3]=t4.getText();
            dft.addRow(datos);
            try {
                guardarTabla();
            } catch (Exception ex) {
                Logger.getLogger(ClientesOProveedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(obj==limpiar){
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
        }
        if(obj==seleccionar){
            try {
                Factura factura=new Factura((String) tabla.getValueAt(tabla.getSelectedRow(),0),(String) tabla.getValueAt(tabla.getSelectedRow(),1),(String) tabla.getValueAt(tabla.getSelectedRow(),2),(String) tabla.getValueAt(tabla.getSelectedRow(),3) , lista, articulos, precios, precunit);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ClientesOProveedores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(ClientesOProveedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(obj==borrar){
            DefaultTableModel miTableModel = (DefaultTableModel) tabla.getModel();
                int indFil = tabla.getSelectedRow();
                if (indFil >= 0){
                    miTableModel.removeRow(indFil);
                }else{
                    JOptionPane.showMessageDialog(null, "No hay una fila seleccionada", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        }
        if(obj==guardar){
            try {
                guardarTabla();
            } catch (Exception ex) {
                Logger.getLogger(ClientesOProveedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(obj==buscar || obj==t5){
            String ele = t5.getText();
                boolean band=false;
                for (int i = 0; i < tabla.getRowCount(); i++) {
                    if (tabla.getValueAt(i, 0).equals(ele)) {                                           
                        tabla.changeSelection(i, 0, false, false);
                        band=true;
                    }
                }
                t5.setText("");
                if(band==false){
                    JOptionPane.showMessageDialog(null, "No se encuentra el empleado que buscaste", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        }
        
    }
    
}
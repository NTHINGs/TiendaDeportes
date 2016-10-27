package Interfaces;
import Funciones.RendererTabla;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import Funciones.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
public class Inventario extends JFrame implements ActionListener {
    JLabel imagensita=new JLabel();
    JLabel l1=new JLabel("Buscar Artículo Por Su Código: ");
    JButton buscar=new JButton("Buscar");
    JTextField busqueda=new JTextField(20);
    JMenu opciones=new JMenu("Opciones");
    JMenuItem exportar=new JMenuItem("Exportar A Excel");
    JMenuItem guardar=new JMenuItem("Guardar Cambios");
    JMenuItem agregar=new JMenuItem("Agregar Producto");
    JMenuItem borrar=new JMenuItem("Borrar...");
    DefaultXMLTableModel dft=new DefaultXMLTableModel();
    JTable tabla;
    JPasswordField pass=new JPasswordField(10);
    JPanel panelsin=new JPanel();
    Inventario(boolean admin){
        super("Registros");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(800,400);
        if(admin==true){
            tabla= new JTable();
            borrar.addActionListener(this);
            borrar.setToolTipText("Borrar el registro seleccionado");
            opciones.add(borrar);
            agregar.addActionListener(this);
            agregar.setToolTipText("Agregar Registros");
            opciones.add(agregar);
            guardar.addActionListener(this);
            guardar.setToolTipText("Guardar Cambios");
            opciones.addSeparator();
            opciones.add(guardar);
            opciones.addSeparator();
        }if(admin==false){
            tabla = new JTable(){
                @Override
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
        }
        //Agregar acciones
        exportar.addActionListener(this);
        exportar.setToolTipText("Exporta a un archivo XML");
       
        buscar.addActionListener(this);
        busqueda.addActionListener(this);
        buscar.setToolTipText("Buscar un registro específico");
        
        //Agregar menu
        opciones.add(exportar);
        JMenuBar barrita=new JMenuBar();
        barrita.add(opciones);
        setJMenuBar(barrita);
        
        //Imagensita de la impresora
        imagensita.setIcon(new ImageIcon(getClass().getResource("/imagensitas/impresora.png")));
      
        
        //icono del boton buscar
        buscar.setContentAreaFilled(false);
        buscar.setBorderPainted(false);
        buscar.setFocusPainted(false);
        buscar.setForeground(new Color(0,255,154));
        buscar.setFont(new Font("Arial", 0, 18));
        buscar.setIcon(new ImageIcon(getClass().getResource("/imagensitas/lupa.png")));
        
        panelsin.add(new JLabel("Ingrese La Contraseña De Administrador:"));
        panelsin.add(pass);
        //Agregar al panel superior con GridBagLayout como manager
        JPanel panelsuperior=new JPanel();
        panelsuperior.setLayout(new GridBagLayout());
        panelsuperior.setBackground(Color.darkGray);
        GridBagConstraints config=new GridBagConstraints();
        config.gridx=0;
        config.gridy=0;
        panelsuperior.add(imagensita, config);
        GridBagConstraints config1=new GridBagConstraints();
        config1.gridx=1;
        config1.gridy=0;
        l1.setForeground(new Color(0,255,154));
        panelsuperior.add(l1, config1);
        GridBagConstraints config2=new GridBagConstraints();
        config2.gridx=2;
        config2.gridy=0;
        panelsuperior.add(busqueda, config2);
        GridBagConstraints config3=new GridBagConstraints();
        config3.gridx=3;
        config3.gridy=0;
        panelsuperior.add(buscar, config3);
        
        //Panel principal
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(panelsuperior, BorderLayout.NORTH);
        
        //Tabla
        tabla.setModel(dft);
        tabla.getColumnModel().getColumn(0).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
        tabla.getColumnModel().getColumn(1).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
        tabla.getColumnModel().getColumn(2).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
        tabla.getColumnModel().getColumn(3).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
        tabla.getColumnModel().getColumn(4).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
        tabla.setBackground(Color.darkGray);
        tabla.setOpaque(false);
        tabla.setForeground(new Color(0,255,154));
        tabla.setSelectionBackground(new Color(0,255,154));
        tabla.setSelectionForeground(Color.darkGray);
        JScrollPane scroll =new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);
        setContentPane(panel); 
        show();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj= e.getSource();
        if(obj instanceof JMenuItem){
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
                BufferedWriter bfw = null;
                try {
                    String[] etiquetas = new String[5];
                    bfw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + File.separator + "inventario.xml"));
                    for (int i = 0; i < tabla.getColumnCount(); i++) {
                        etiquetas[i] = tabla.getColumnName(i);
                    }   bfw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
                bfw.newLine();
                    bfw.write("<inventario>");
                    for (int i = 0; i < tabla.getRowCount(); i++) {
                        bfw.newLine();
                        bfw.write("<producto>");
                        for (int j = 0; j < tabla.getColumnCount(); j++) {
                            bfw.newLine();
                            bfw.write("<" + tabla.getColumnName(j) + ">" + (String) (tabla.forueAt(i, j) + "</" + tabla.getColumnName(j) + ">"));
                        }
                        bfw.newLine();
                        bfw.write("</producto>");
                    }   bfw.newLine();
                bfw.write("</inventario>");
                    bfw.close();
                } catch (IOException ex) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        bfw.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            if(obj==agregar){
                int filas=0;
                DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
                modelo.addRow(new Object[filas]);
            }
            
            if(obj==exportar){
                FileChuser file=new FileChuser();
                ExcelTableExporter excel=new ExcelTableExporter(tabla, new File(file.obtenerDirectorio(".xls")), "Inventario");
            }
        }
        if(obj instanceof JButton){
            if(obj==buscar){
                String ele = busqueda.getText();
                boolean band=false;
                for (int i = 0; i < tabla.getRowCount(); i++) {
                    if (tabla.getValueAt(i, 0).equals(ele)) {                                           
                        tabla.changeSelection(i, 0, false, false);
                        band=true;
                    }
                }
                if(band==false){
                    JOptionPane.showMessageDialog(null, "No se encuentra el artículo que buscaste", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(obj instanceof JTextField){
            if(obj==busqueda){
                String ele = busqueda.getText();
                boolean band=false;
                for (int i = 0; i < tabla.getRowCount(); i++) {
                    if (tabla.getValueAt(i, 0).equals(ele)) {                                           
                        tabla.changeSelection(i, 0, false, false);
                        band=true;
                    }
                }
                busqueda.setText("");
                if(band==false){
                    JOptionPane.showMessageDialog(null, "No se encuentra el empleado que buscaste", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        }
    }


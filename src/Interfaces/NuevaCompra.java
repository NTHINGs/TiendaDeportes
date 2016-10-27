package Interfaces;

import Funciones.Ticket;
import Funciones.RendererTabla;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Funciones.*;
import java.io.*;
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
public class NuevaCompra extends JFrame implements ActionListener{
    JLabel instruccion=new JLabel("Selecciona Los Artículos Que Esta Comprando Tu Cliente");
    JLabel total=new JLabel("$00.00");
    JTextArea compras=new JTextArea(5,15);
    JButton seleccionar=new JButton("Seleccionar Artículo");
    JButton comprar=new JButton("Realizar Compra");
    DefaultXMLTableModel dft=new DefaultXMLTableModel();
    String lista[];
    String precunit[]; 
    String art[];
    String prec[];
    JTable articulos=new JTable(){
        @Override
        public boolean isCellEditable(int rowIndex, int vColIndex){
            return false;
        }
    };
    
    NuevaCompra(){
        super("Compra");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setupTabla();
        JPanel principal=new JPanel();
        principal.setLayout(new BorderLayout());
        JPanel superior=new JPanel();
        superior.setLayout(new GridBagLayout());
        superior.setBackground(Color.darkGray);
        GridBagConstraints config1=new GridBagConstraints();
        config1.gridx=0;
        config1.gridy=0;    
        config1.gridwidth=2;
        config1.fill=GridBagConstraints.BOTH;
        instruccion.setForeground(new Color(0,255,154));
        instruccion.setFont(new Font("Arial", 0, 24));
        superior.add(instruccion, config1);
        GridBagConstraints config2=new GridBagConstraints();
        config2.gridx=0;
        config2.gridy=1;
        total.setForeground(new Color(0,255,154));
        total.setFont(new Font("Arial", 0, 74));
        superior.add(total, config2);
        GridBagConstraints config3=new GridBagConstraints();
        config3.gridx=1;
        config3.gridy=1;
        config3.fill=GridBagConstraints.BOTH;
        JScrollPane scr = new JScrollPane(compras, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scr.setOpaque(false);
        scr.getViewport().setOpaque(false);
        scr.setBorder(null);
        scr.setViewportBorder(null);

        compras.setBorder(null);
        compras.setLineWrap(true);
        compras.setWrapStyleWord(true);
        compras.setEditable(false);
        compras.setOpaque(false);
        compras.setForeground(new Color(0,255,154));
        compras.setBackground(Color.darkGray);
        compras.setText("Articulos Seleccionados Hasta Ahora:");
        superior.add(scr, config3);
        principal.add(superior, BorderLayout.NORTH);
        articulos.setBackground(Color.darkGray);
        articulos.setOpaque(false);
        articulos.setForeground(new Color(0,255,154));
        articulos.setSelectionBackground(new Color(0,255,154));
        articulos.setSelectionForeground(Color.darkGray);
        JScrollPane scroll=new JScrollPane(articulos);
        principal.add(scroll, BorderLayout.CENTER);
        JPanel botonera=new JPanel();
        botonera.setLayout(new GridBagLayout());
        botonera.setBackground(Color.darkGray);
        GridBagConstraints confi1=new GridBagConstraints();
        confi1.gridx=0;
        confi1.weighty=1;
        confi1.gridy=0;    
        confi1.weightx=1;
        confi1.fill=GridBagConstraints.BOTH;
        seleccionar.setContentAreaFilled(false);
        seleccionar.setBorderPainted(false);
        seleccionar.setFocusPainted(false);
        seleccionar.setForeground(new Color(0,255,154));
        seleccionar.setFont(new Font("Arial", 0, 18));
        seleccionar.setIcon(new ImageIcon(getClass().getResource("/imagensitas/selec.png")));
        seleccionar.addActionListener(this);
        botonera.add(seleccionar, confi1);
        GridBagConstraints confi2=new GridBagConstraints();
        confi2.gridx=2;
        confi2.gridy=0;
        confi2.fill=GridBagConstraints.BOTH;
        comprar.setContentAreaFilled(false);
        comprar.setBorderPainted(false);
        comprar.setFocusPainted(false);
        comprar.setForeground(new Color(0,255,154));
        comprar.setFont(new Font("Arial", 0, 18));
        comprar.setIcon(new ImageIcon(getClass().getResource("/imagensitas/realic.png")));
        comprar.addActionListener(this);
        botonera.add(comprar, confi2);
        principal.add(botonera, BorderLayout.SOUTH);
        lista=new String[articulos.getRowCount()];
        precunit=new String[articulos.getRowCount()];
        art=new String[articulos.getRowCount()];
        prec=new String[articulos.getRowCount()];
        for(int x=0;x<articulos.getRowCount();x++){
            lista[x]=(String) articulos.getValueAt(x,1);
            precunit[x]=(String) articulos.getValueAt(x,2);
        }
        setContentPane(principal);
        setSize(700,500);
        show();
    }
    void setupTabla(){
        articulos.setModel(dft);
        articulos.getColumnModel().getColumn(0).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
        articulos.getColumnModel().getColumn(1).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
        articulos.getColumnModel().getColumn(2).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
        articulos.getColumnModel().getColumn(3).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
        articulos.getColumnModel().getColumn(4).setHeaderRenderer(new RendererTabla(Color.darkGray, new Color(0,255,154)));
    }
    public void guardarTabla() throws Exception {
        String [] etiquetas=new String [5];
        BufferedWriter bfw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + File.separator + "inventario.xml"));
        for (int i = 0; i < articulos.getColumnCount(); i++) {
            etiquetas[i]=articulos.getColumnName(i);
        }
        bfw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        bfw.newLine();
        bfw.write("<inventario>");
        for (int i = 0; i < articulos.getRowCount(); i++) {
            bfw.newLine();
            bfw.write("<producto>");
            for (int j = 0; j < articulos.getColumnCount(); j++) {
                bfw.newLine();
                bfw.write("<"+articulos.getColumnName(j)+">"+(String) (articulos.getValueAt(i, j)+"</"+articulos.getColumnName(j)+">"));
            }
            bfw.newLine();
            bfw.write("</producto>");
        }
        bfw.newLine();
        bfw.write("</inventario>");
        bfw.close();
    }
    int a=0;
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj==seleccionar){
                double totals=0;
                int indFil = articulos.getSelectedRow();
                if (indFil >= 0){
                    totals=Double.parseDouble(total.getText().substring(1))+Double.parseDouble((String) articulos.getValueAt(articulos.getSelectedRow(),2));
                    total.setText("$"+totals);
                    compras.setText(compras.getText()+"\n"+(String) articulos.getValueAt(articulos.getSelectedRow(),1));
                        art[a]=(String) articulos.getValueAt(articulos.getSelectedRow(),1);
                        prec[a]=(String) articulos.getValueAt(articulos.getSelectedRow(),2);
                        a=a+1;
                    int existencia=Integer.parseInt((String) articulos.getValueAt(articulos.getSelectedRow(),3));
                    if(existencia>0){
                    articulos.setValueAt(Integer.toString(existencia-1), articulos.getSelectedRow(), 3);
                    }else{
                        JOptionPane.showMessageDialog(null, "No hay existencia", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "No hay un artículo seleccionado", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        }
        if(obj==comprar){
            try {
                guardarTabla();
                int opc=JOptionPane.showConfirmDialog(null, "¿El cliente desea factura?","FACTURA", JOptionPane.YES_NO_OPTION);
                if(opc==JOptionPane.YES_OPTION){
                    ClientesOProveedores cliente=new ClientesOProveedores(true, lista,art,prec,precunit);
                }
                if(opc==JOptionPane.NO_OPTION){
                    Ticket ticket=new Ticket(lista,art,prec,precunit);
                }
            } catch (Exception ex) {
                Logger.getLogger(NuevaCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
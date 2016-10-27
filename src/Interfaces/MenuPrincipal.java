package Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
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
public class MenuPrincipal extends JFrame implements ActionListener {
    JLabel logo =new JLabel();
    JLabel logochico=new JLabel();
    JButton nuevacompra=new JButton("Nueva Compra");
    JButton consultainv=new JButton("Consulta Inv.");
    JButton actualizarinv=new JButton("Actualizar Inv.");
    JButton proveedores=new JButton("Proveedores");
    
    MenuPrincipal(){
        super("Deportes \"El Inge\"");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        nuevacompra.addActionListener(this);
        consultainv.addActionListener(this);
        actualizarinv.addActionListener(this);
        proveedores.addActionListener(this);
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints config1=new GridBagConstraints();
        config1.gridx=0;
        config1.weighty=1;
        config1.gridy=0;    
        config1.weightx=1;
        config1.fill=GridBagConstraints.BOTH;
        nuevacompra.setContentAreaFilled(false);
        nuevacompra.setBorderPainted(false);
        nuevacompra.setFocusPainted(false);
        nuevacompra.setForeground(new Color(0,255,154));
        nuevacompra.setIcon(new ImageIcon(getClass().getResource("/imagensitas/money13.png")));
        panel.add(nuevacompra, config1);
        GridBagConstraints config2=new GridBagConstraints();
        config2.gridx=0;
        config2.gridy=2;
        config2.weightx=1;
        config2.weighty=1;
        config2.fill=GridBagConstraints.BOTH;
        consultainv.setContentAreaFilled(false);
        consultainv.setBorderPainted(false);
        consultainv.setFocusPainted(false);
        consultainv.setForeground(new Color(0,255,154));
        consultainv.setIcon(new ImageIcon(getClass().getResource("/imagensitas/equalizer16.png")));
        panel.add(consultainv, config2);
        GridBagConstraints config3=new GridBagConstraints();
        config3.gridx=1;
        config3.gridy=1;
        config3.weightx=1;
        config3.weighty=2;
        config3.gridheight=2;
        config3.fill=GridBagConstraints.CENTER;
        logochico.setIcon(new ImageIcon(getClass().getResource("/imagensitas/three13.png")));
        panel.add(logochico, config3);
        GridBagConstraints config4=new GridBagConstraints();
        config4.gridx=2;
        config4.gridy=0;
        config4.weightx=1;
        config4.weighty=1;
        config4.fill=GridBagConstraints.BOTH;
        actualizarinv.setContentAreaFilled(false);
        actualizarinv.setBorderPainted(false);
        actualizarinv.setFocusPainted(false);
        actualizarinv.setForeground(new Color(0,255,154));
        actualizarinv.setIcon(new ImageIcon(getClass().getResource("/imagensitas/note25.png")));
        panel.add(actualizarinv, config4);
        GridBagConstraints config5=new GridBagConstraints();
        config5.gridx=2;
        config5.gridy=2;
        config5.weightx=1;
        config5.weighty=1;
        config5.fill=GridBagConstraints.BOTH;
        proveedores.setContentAreaFilled(false);
        proveedores.setBorderPainted(false);
        proveedores.setFocusPainted(false);
        proveedores.setForeground(new Color(0,255,154));
        proveedores.setIcon(new ImageIcon(getClass().getResource("/imagensitas/male66.png")));
        panel.add(proveedores, config5);
        panel.setBackground(Color.darkGray);
        JPanel panelprincipal=new JPanel();
        panelprincipal.setLayout(new BorderLayout());
        logo.setIcon(new ImageIcon(getClass().getResource("/imagensitas/logosuperior.png")));
        panelprincipal.add(logo, BorderLayout.NORTH);
        panelprincipal.add(panel, BorderLayout.CENTER);
        setContentPane(panelprincipal);
        pack();
        show();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj==nuevacompra){
            NuevaCompra compra=new NuevaCompra();
        }
        if(obj==consultainv){
            Inventario inv=new Inventario(false);
        }
        if(obj==actualizarinv){
            JPasswordField pass=new JPasswordField(10);
            JPanel panelsin=new JPanel();
            panelsin.add(new JLabel("Ingrese La Contraseña De Administrador:"));
            panelsin.add(pass);
            int opc=JOptionPane.showConfirmDialog(null, panelsin, "ADMINISTRADOR", JOptionPane.OK_CANCEL_OPTION);
                if(opc==JOptionPane.OK_OPTION){
                    if("123456".equals(pass.getText())){
                        Inventario invent=new Inventario(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "CONTRASEÑA INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
        }
        if(obj==proveedores){
            JPasswordField pass=new JPasswordField(10);
            JPanel panelsin=new JPanel();
            panelsin.add(new JLabel("Ingrese La Contraseña De Administrador:"));
            panelsin.add(pass);
            int opc=JOptionPane.showConfirmDialog(null, panelsin, "ADMINISTRADOR", JOptionPane.OK_CANCEL_OPTION);
                if(opc==JOptionPane.OK_OPTION){
                    if("123456".equals(pass.getText())){
                        ClientesOProveedores proveedor=new ClientesOProveedores(false, null, null, null, null);
                    }else{
                        JOptionPane.showMessageDialog(null, "CONTRASEÑA INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            
        }
    }
    
    public static void main(String a[]){
        MenuPrincipal main=new MenuPrincipal();
    }

}
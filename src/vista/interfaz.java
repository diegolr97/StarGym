/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import clases.Administrador;
import clases.Trabajador;
import fachada.fachada;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import modelo.comprobacion;
import modelo.conexion;
import modelo.reloj;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


import org.edisoncor.gui.panel.NewJFrame;
import org.edisoncor.gui.util.Avatar;

/**
 *
 * @author Manuel
 */
public class interfaz extends javax.swing.JFrame implements DocumentListener, Runnable{
    
    fachada f = new fachada();
    comprobacion c = new comprobacion();
    //ComboBox tabla Clases
    JComboBox jcb  = new JComboBox(f.comboMonitores());
    //Reloj
    Thread h1;
    String hora,minutos,segundos,ampm;
    Calendar calendario;
    
    String dniCliente;
    
    public interfaz() {
        
        initComponents();
        setLocationRelativeTo(null);
        llenarMenu();
        panelLoguin.setVisible(true);
        avatarAdmin.setVisible(false);
        panelAdmin.setVisible(false);
        panelAdminAdmin.setVisible(false);
        panelAdminCliente.setVisible(false);
        panelAdminMaquina.setVisible(false);
        panelAdminTrabajador.setVisible(false);
        
        avatarTrab.setVisible(false);
        panelTrab.setVisible(false);
        panelTrabPerfil.setVisible(false);
        panelTrabCliente.setVisible(false);
        panelTrabMatricula.setVisible(false);
        panelTrabCobro.setVisible(false);

        
        this.txtnombre.getDocument().addDocumentListener(this);
        
        h1 = new Thread(this);
        h1.start();
        setTitle("Tu Tutorial"); //Titulo del frame
        setLocationRelativeTo(null); //Para centrar la ventana
        setVisible(true);
        
    }
    
    
    

    public void llenarMenu(){
        List<Avatar> avatarsAdmin = new ArrayList<Avatar>();
        avatarsAdmin.add(new Avatar("Admin", loadImage("/imagenes/admin.png")));
        avatarsAdmin.add(new Avatar("Clientes", loadImage("/imagenes/clients.png")));
        avatarsAdmin.add(new Avatar("Trabajadores", loadImage("/imagenes/trabajador.png")));
        avatarsAdmin.add(new Avatar("Clases", loadImage("/imagenes/maquina.png")));
        avatarsAdmin.add(new Avatar("Exit", loadImage("/imagenes/exit.png")));
        
        avatarAdmin.setAvatars(avatarsAdmin);
        
        List<Avatar> avatarsTrab = new ArrayList<Avatar>();
        avatarsTrab.add(new Avatar("Perfil", loadImage("/imagenes/admin.png")));
        avatarsTrab.add(new Avatar("Clientes", loadImage("/imagenes/clients.png")));
        avatarsTrab.add(new Avatar("Matricula", loadImage("/imagenes/trabajador.png")));
        avatarsTrab.add(new Avatar("Cobros", loadImage("/imagenes/maquina.png")));
        avatarsTrab.add(new Avatar("Exit", loadImage("/imagenes/exit.png")));
        
        avatarTrab.setAvatars(avatarsTrab);
    }
    
    public static Image loadImage(String filename){
        try {
            return ImageIO.read(interfaz.class.getResource(filename));
        } catch (Exception e) {
            return null;
        }
    }
    
    public void calcula () {

        calendario = new GregorianCalendar();
        java.util.Date fechaHoraActual = new java.util.Date();

        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
        if(ampm.equals("PM")){
        int h = calendario.get(Calendar.HOUR_OF_DAY)-12;
        hora = h>9?""+h:"0"+h;
        }else{
        hora = calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY); }
        minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        String nombre= this.txtnombre.getText();
        this.tablaClientes.setModel(f.listarClientesLetra(nombre));
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String nombre = this.txtnombre.getText();
        this.tablaClientes.setModel(f.listarClientesLetra(nombre));
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while(ct == h1) {
        calcula();
        lblHoraAdmin.setText(hora + ":" + minutos + ":" + segundos + " "+ampm);
        lblHoraTrabajador.setText(hora + ":" + minutos + ":" + segundos + " "+ampm);
        try {
        Thread.sleep(1000);
        }catch(InterruptedException e) {}
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        tabbedPaneTask1 = new org.edisoncor.gui.tabbedPane.TabbedPaneTask();
        diaAdminClienteTarifa = new javax.swing.JDialog();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        buttonAeroRight1 = new org.edisoncor.gui.button.ButtonAeroRight();
        buttonAeroLeft1 = new org.edisoncor.gui.button.ButtonAeroLeft();
        jScrollPane9 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        labelMetric44 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric45 = new org.edisoncor.gui.label.LabelMetric();
        buttonAction19 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction20 = new org.edisoncor.gui.button.ButtonAction();
        diaAdminMaquinaMonitor = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbMonitores = new javax.swing.JTable();
        labelMetric34 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric35 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric36 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric37 = new org.edisoncor.gui.label.LabelMetric();
        txtMonitorApellidos = new org.edisoncor.gui.textField.TextField();
        txtMonitorDni = new org.edisoncor.gui.textField.TextField();
        txtMonitorTelefono = new org.edisoncor.gui.textField.TextField();
        txtMonitorCorreo = new org.edisoncor.gui.textField.TextField();
        labelMetric39 = new org.edisoncor.gui.label.LabelMetric();
        Guardar = new org.edisoncor.gui.button.ButtonAction();
        buttonAction22 = new org.edisoncor.gui.button.ButtonAction();
        labelMetric40 = new org.edisoncor.gui.label.LabelMetric();
        txtMonitorNombre = new org.edisoncor.gui.textField.TextField();
        jSpinner1 = new javax.swing.JSpinner();
        panelPrincipal = new javax.swing.JPanel();
        panelLoguin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtLoguinUsuario = new org.edisoncor.gui.textField.TextFieldRound();
        txtLoguinContraseña = new org.edisoncor.gui.passwordField.PasswordFieldRound();
        labelMetric1 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric2 = new org.edisoncor.gui.label.LabelMetric();
        btnLoguinEntrar = new org.edisoncor.gui.button.ButtonAction();
        panelAdmin = new javax.swing.JPanel();
        avatarAdmin = new org.edisoncor.gui.panel.PanelAvatarChooser();
        btnAdminIpod = new org.edisoncor.gui.button.ButtonIpod();
        panelAdminAdmin = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaAdmin = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        labelMetric13 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric14 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorTelefono = new org.edisoncor.gui.textField.TextField();
        labelMetric8 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorNombre = new org.edisoncor.gui.textField.TextField();
        txtAdminAdministradorDireccion = new org.edisoncor.gui.textField.TextField();
        labelMetric12 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorApellidos = new org.edisoncor.gui.textField.TextField();
        labelMetric6 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorCodPostal = new org.edisoncor.gui.textField.TextField();
        labelMetric15 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorCorreo = new org.edisoncor.gui.textField.TextField();
        labelMetric9 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorDni = new org.edisoncor.gui.textField.TextField();
        labelMetric16 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorContraseña = new org.edisoncor.gui.textField.TextField();
        labelMetric5 = new org.edisoncor.gui.label.LabelMetric();
        jPanel4 = new javax.swing.JPanel();
        buttonAction4 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction5 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction1 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction3 = new org.edisoncor.gui.button.ButtonAction();
        jLabel2 = new javax.swing.JLabel();
        labelTask1 = new org.edisoncor.gui.label.LabelTask();
        panelAdminCliente = new javax.swing.JPanel();
        labelTask2 = new org.edisoncor.gui.label.LabelTask();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        labelMetric7 = new org.edisoncor.gui.label.LabelMetric();
        jPanel6 = new javax.swing.JPanel();
        buttonAction6 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction7 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction9 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction10 = new org.edisoncor.gui.button.ButtonAction();
        labelMetric17 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric18 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminClienteTelefono = new org.edisoncor.gui.textField.TextField();
        labelMetric10 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminClienteApellidos = new org.edisoncor.gui.textField.TextField();
        txtAdminClienteDireccion = new org.edisoncor.gui.textField.TextField();
        labelMetric19 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminClienteCiudad = new org.edisoncor.gui.textField.TextField();
        labelMetric11 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminClienteCP = new org.edisoncor.gui.textField.TextField();
        labelMetric20 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminClienteCorreo = new org.edisoncor.gui.textField.TextField();
        labelMetric21 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminClienteDni = new org.edisoncor.gui.textField.TextField();
        labelMetric22 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminClienteNombre = new org.edisoncor.gui.textField.TextField();
        labelMetric23 = new org.edisoncor.gui.label.LabelMetric();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaClientes = new javax.swing.JList<>();
        fecha = new com.toedter.calendar.JDateChooser();
        labelMetric42 = new org.edisoncor.gui.label.LabelMetric();
        txtnombre = new org.edisoncor.gui.textField.TextFieldRound();
        labelMetric43 = new org.edisoncor.gui.label.LabelMetric();
        panelAdminTrabajador = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        listaTrabajadores = new javax.swing.JList<>();
        jPanel8 = new javax.swing.JPanel();
        labelMetric24 = new org.edisoncor.gui.label.LabelMetric();
        jPanel9 = new javax.swing.JPanel();
        buttonAction11 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction12 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction13 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction14 = new org.edisoncor.gui.button.ButtonAction();
        labelMetric25 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric26 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminTrabajTeled = new org.edisoncor.gui.textField.TextField();
        labelMetric27 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminTrabajNombre = new org.edisoncor.gui.textField.TextField();
        txtAdminTrabajDireccion = new org.edisoncor.gui.textField.TextField();
        labelMetric28 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminTrabajApellid = new org.edisoncor.gui.textField.TextField();
        labelMetric29 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminTrabajCP = new org.edisoncor.gui.textField.TextField();
        labelMetric30 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminTrabajCorreo = new org.edisoncor.gui.textField.TextField();
        labelMetric31 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminTrabajDni = new org.edisoncor.gui.textField.TextField();
        labelMetric32 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminTrabajContrsña = new org.edisoncor.gui.passwordField.PasswordField();
        labelTask3 = new org.edisoncor.gui.label.LabelTask();
        panelAdminMaquina = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        buttonAction31 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction32 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction33 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction34 = new org.edisoncor.gui.button.ButtonAction();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaMaquinas = new javax.swing.JTable();
        labelMetric38 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminMaquiNombre = new org.edisoncor.gui.textField.TextField();
        cmbAdminMaquinas = new org.edisoncor.gui.comboBox.ComboBoxRound();
        labelMetric46 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric47 = new org.edisoncor.gui.label.LabelMetric();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaClases = new javax.swing.JTable();
        labelMetric33 = new org.edisoncor.gui.label.LabelMetric();
        buttonIcon1 = new org.edisoncor.gui.button.ButtonIcon();
        jPanel13 = new javax.swing.JPanel();
        buttonAction15 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction16 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction17 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction18 = new org.edisoncor.gui.button.ButtonAction();
        txtAdminClasesNombre = new org.edisoncor.gui.textField.TextField();
        labelMetric48 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric49 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminClasesPrecio = new org.edisoncor.gui.textField.TextField();
        labelMetric50 = new org.edisoncor.gui.label.LabelMetric();
        cmbAdminClases = new org.edisoncor.gui.comboBox.ComboBoxRound();
        labelTask4 = new org.edisoncor.gui.label.LabelTask();
        btnAdminRetroceso = new org.edisoncor.gui.button.ButtonCircle();
        labelMetric3 = new org.edisoncor.gui.label.LabelMetric();
        lblAdministrador = new org.edisoncor.gui.label.LabelMetric();
        lblHoraAdmin = new javax.swing.JLabel();
        panelTrab = new javax.swing.JPanel();
        avatarTrab = new org.edisoncor.gui.panel.PanelAvatarChooser();
        btnTrabIpod = new org.edisoncor.gui.button.ButtonIpod();
        panelTrabPerfil = new javax.swing.JPanel();
        dniTrabajador = new org.edisoncor.gui.label.LabelTask();
        jPanel31 = new javax.swing.JPanel();
        labelMetric89 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric90 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorTelefono6 = new org.edisoncor.gui.textField.TextField();
        labelMetric91 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorNombre16 = new org.edisoncor.gui.textField.TextField();
        txtAdminAdministradorDireccion6 = new org.edisoncor.gui.textField.TextField();
        labelMetric92 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorApellidos10 = new org.edisoncor.gui.textField.TextField();
        labelMetric93 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorCodPostal6 = new org.edisoncor.gui.textField.TextField();
        labelMetric94 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorCorreo6 = new org.edisoncor.gui.textField.TextField();
        labelMetric95 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorNombre17 = new org.edisoncor.gui.textField.TextField();
        labelMetric96 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorApellidos11 = new org.edisoncor.gui.textField.TextField();
        labelMetric41 = new org.edisoncor.gui.label.LabelMetric();
        jLabel3 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        buttonAction21 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction25 = new org.edisoncor.gui.button.ButtonAction();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        labelMetric97 = new org.edisoncor.gui.label.LabelMetric();
        panelTrabCliente = new javax.swing.JPanel();
        labelTask6 = new org.edisoncor.gui.label.LabelTask();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        labelMetric59 = new org.edisoncor.gui.label.LabelMetric();
        jPanel22 = new javax.swing.JPanel();
        buttonAction28 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction29 = new org.edisoncor.gui.button.ButtonAction();
        btnTrabClienteMatricula = new org.edisoncor.gui.button.ButtonAction();
        btnTrabClienteClases = new org.edisoncor.gui.button.ButtonAction();
        labelMetric60 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric61 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorTelefono4 = new org.edisoncor.gui.textField.TextField();
        labelMetric62 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorNombre8 = new org.edisoncor.gui.textField.TextField();
        txtAdminAdministradorDireccion4 = new org.edisoncor.gui.textField.TextField();
        labelMetric63 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorApellidos7 = new org.edisoncor.gui.textField.TextField();
        labelMetric64 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorCodPostal4 = new org.edisoncor.gui.textField.TextField();
        labelMetric65 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorCorreo4 = new org.edisoncor.gui.textField.TextField();
        labelMetric66 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorNombre9 = new org.edisoncor.gui.textField.TextField();
        labelMetric67 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorApellidos8 = new org.edisoncor.gui.textField.TextField();
        labelMetric68 = new org.edisoncor.gui.label.LabelMetric();
        jScrollPane13 = new javax.swing.JScrollPane();
        tablaClientes2 = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        listaClientes1 = new javax.swing.JList<>();
        fecha1 = new com.toedter.calendar.JDateChooser();
        labelMetric69 = new org.edisoncor.gui.label.LabelMetric();
        txtnombre1 = new org.edisoncor.gui.textField.TextFieldRound();
        labelMetric70 = new org.edisoncor.gui.label.LabelMetric();
        panelTrabMatricula = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        labelMetric72 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric73 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorTelefono5 = new org.edisoncor.gui.textField.TextField();
        labelMetric74 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorNombre10 = new org.edisoncor.gui.textField.TextField();
        txtAdminAdministradorDireccion5 = new org.edisoncor.gui.textField.TextField();
        labelMetric75 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric76 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorCodPostal5 = new org.edisoncor.gui.textField.TextField();
        labelMetric77 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorCorreo5 = new org.edisoncor.gui.textField.TextField();
        labelMetric78 = new org.edisoncor.gui.label.LabelMetric();
        txtAdminAdministradorNombre11 = new org.edisoncor.gui.textField.TextField();
        labelMetric79 = new org.edisoncor.gui.label.LabelMetric();
        passwordField2 = new org.edisoncor.gui.passwordField.PasswordField();
        fecha3 = new com.toedter.calendar.JDateChooser();
        txtAdminAdministradorCorreo7 = new org.edisoncor.gui.textField.TextField();
        labelMetric80 = new org.edisoncor.gui.label.LabelMetric();
        jPanel25 = new javax.swing.JPanel();
        buttonAction39 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction48 = new org.edisoncor.gui.button.ButtonAction();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        listTrabMatricTarifa = new javax.swing.JList<>();
        buttonAeroRight2 = new org.edisoncor.gui.button.ButtonAeroRight();
        buttonAeroLeft2 = new org.edisoncor.gui.button.ButtonAeroLeft();
        jScrollPane19 = new javax.swing.JScrollPane();
        listTrabMatricClases = new javax.swing.JList<>();
        labelMetric51 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric52 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric53 = new org.edisoncor.gui.label.LabelMetric();
        lblTrabTotalMatricula = new org.edisoncor.gui.label.LabelMetric();
        labelTask7 = new org.edisoncor.gui.label.LabelTask();
        labelMetric71 = new org.edisoncor.gui.label.LabelMetric();
        panelTrabCobro = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tablaTrabCobroCliente = new javax.swing.JTable();
        labelMetric83 = new org.edisoncor.gui.label.LabelMetric();
        jScrollPane20 = new javax.swing.JScrollPane();
        tablaTrabCobroMensual = new javax.swing.JTable();
        labelMetric88 = new org.edisoncor.gui.label.LabelMetric();
        buttonAction46 = new org.edisoncor.gui.button.ButtonAction();
        jLabel5 = new javax.swing.JLabel();
        labelMetric84 = new org.edisoncor.gui.label.LabelMetric();
        Cobro = new org.edisoncor.gui.label.LabelTask();
        btnTrabajadorRetroceso = new org.edisoncor.gui.button.ButtonCircle();
        labelMetric87 = new org.edisoncor.gui.label.LabelMetric();
        lblTrabajador = new org.edisoncor.gui.label.LabelMetric();
        lblHoraTrabajador = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jEditorPane1);

        jPanel14.setBackground(new java.awt.Color(0, 0, 0));

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(jList1);

        buttonAeroRight1.setForeground(new java.awt.Color(0, 0, 0));
        buttonAeroRight1.setText(">>");
        buttonAeroRight1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N

        buttonAeroLeft1.setForeground(new java.awt.Color(0, 0, 0));
        buttonAeroLeft1.setText("<<");
        buttonAeroLeft1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane9.setViewportView(jList2);

        labelMetric44.setText("Clases:");

        labelMetric45.setText("Mi Tarifa:");

        buttonAction19.setText("Cancelar");

        buttonAction20.setText("Aceptar");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMetric44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonAeroLeft1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonAeroRight1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMetric45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(buttonAction20, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(buttonAction19, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMetric44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(buttonAeroLeft1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonAeroRight1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonAction19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout diaAdminClienteTarifaLayout = new javax.swing.GroupLayout(diaAdminClienteTarifa.getContentPane());
        diaAdminClienteTarifa.getContentPane().setLayout(diaAdminClienteTarifaLayout);
        diaAdminClienteTarifaLayout.setHorizontalGroup(
            diaAdminClienteTarifaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        diaAdminClienteTarifaLayout.setVerticalGroup(
            diaAdminClienteTarifaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(0, 0, 0));

        tbMonitores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbMonitores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMonitoresMouseClicked(evt);
            }
        });
        tbMonitores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMonitoresKeyReleased(evt);
            }
        });
        jScrollPane11.setViewportView(tbMonitores);

        labelMetric34.setText("Dni:");

        labelMetric35.setText("Apellidos:");

        labelMetric36.setText("Telefono:");

        labelMetric37.setText("Correo:");

        labelMetric39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric39.setText("MONITORES");
        labelMetric39.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        buttonAction22.setText("Eliminar");
        buttonAction22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction22ActionPerformed(evt);
            }
        });

        labelMetric40.setText("Nombre:");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                            .addComponent(labelMetric36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtMonitorTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(labelMetric37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtMonitorCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                            .addComponent(labelMetric35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtMonitorApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(labelMetric34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)
                                        .addComponent(txtMonitorDni, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelMetric40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)
                                        .addComponent(txtMonitorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonAction22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMetric39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMetric34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonitorDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonitorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMetric35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonitorApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelMetric37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMonitorCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMonitorTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelMetric36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAction22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout diaAdminMaquinaMonitorLayout = new javax.swing.GroupLayout(diaAdminMaquinaMonitor.getContentPane());
        diaAdminMaquinaMonitor.getContentPane().setLayout(diaAdminMaquinaMonitorLayout);
        diaAdminMaquinaMonitorLayout.setHorizontalGroup(
            diaAdminMaquinaMonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        diaAdminMaquinaMonitorLayout.setVerticalGroup(
            diaAdminMaquinaMonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1140, 628));

        panelPrincipal.setBackground(new java.awt.Color(0, 0, 0));
        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLoguin.setBackground(new java.awt.Color(0, 0, 0));
        panelLoguin.setMaximumSize(new java.awt.Dimension(1140, 628));
        panelLoguin.setPreferredSize(new java.awt.Dimension(1140, 628));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N

        txtLoguinUsuario.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N

        txtLoguinContraseña.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N

        labelMetric1.setText("Usuario:");
        labelMetric1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N

        labelMetric2.setText("Contraseña:");
        labelMetric2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N

        btnLoguinEntrar.setText("Entrar");
        btnLoguinEntrar.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnLoguinEntrar.setMain(true);
        btnLoguinEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoguinEntrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLoguinLayout = new javax.swing.GroupLayout(panelLoguin);
        panelLoguin.setLayout(panelLoguinLayout);
        panelLoguinLayout.setHorizontalGroup(
            panelLoguinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoguinLayout.createSequentialGroup()
                .addGap(299, 299, 299)
                .addGroup(panelLoguinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLoguinLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel1))
                    .addGroup(panelLoguinLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLoguinUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLoguinLayout.createSequentialGroup()
                        .addComponent(labelMetric2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLoguinContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLoguinLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(btnLoguinEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(407, Short.MAX_VALUE))
        );
        panelLoguinLayout.setVerticalGroup(
            panelLoguinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoguinLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(panelLoguinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLoguinLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtLoguinUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLoguinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMetric2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLoguinContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnLoguinEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelPrincipal.add(panelLoguin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, 1140, 660));

        panelAdmin.setBackground(new java.awt.Color(0, 0, 0));
        panelAdmin.setPreferredSize(new java.awt.Dimension(1140, 628));
        panelAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        avatarAdmin.setColorPrimario(new java.awt.Color(0, 0, 0));
        avatarAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                avatarAdminKeyPressed(evt);
            }
        });

        btnAdminIpod.setText(" ");
        btnAdminIpod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminIpodActionPerformed(evt);
            }
        });
        avatarAdmin.add(btnAdminIpod, new java.awt.GridBagConstraints());

        panelAdmin.add(avatarAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 1140, 596));

        panelAdminAdmin.setBackground(new java.awt.Color(0, 0, 0));
        panelAdminAdmin.setPreferredSize(new java.awt.Dimension(1140, 628));
        panelAdminAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        listaAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaAdminMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listaAdmin);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));

        labelMetric13.setText("Direccion:");
        labelMetric13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        labelMetric14.setText("Telefono:");
        labelMetric14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorTelefono.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric8.setText("Nombre:");
        labelMetric8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtAdminAdministradorDireccion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric12.setText("Apellidos:");
        labelMetric12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorApellidos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric6.setText("Cod. Postal");
        labelMetric6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorCodPostal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric15.setText("Correo:");
        labelMetric15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorCorreo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric9.setText("Dni:");
        labelMetric9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorDni.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric16.setText("Contraseña:");
        labelMetric16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorContraseña.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtAdminAdministradorDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelMetric6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAdminAdministradorCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtAdminAdministradorTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(labelMetric15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAdminAdministradorCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtAdminAdministradorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelMetric12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAdminAdministradorApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(labelMetric9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAdminAdministradorDni, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(labelMetric16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAdminAdministradorContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)))
                .addGap(36, 36, 36))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAdminAdministradorContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorDni, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelMetric12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAdminAdministradorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMetric8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAdminAdministradorCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMetric14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        labelMetric5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric5.setText("Star GYM");
        labelMetric5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(12, 12, 12));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));

        buttonAction4.setText("Modificar");
        buttonAction4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction4ActionPerformed(evt);
            }
        });

        buttonAction5.setText("Eliminar");
        buttonAction5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction5ActionPerformed(evt);
            }
        });

        buttonAction1.setText("Nuevo");
        buttonAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction1ActionPerformed(evt);
            }
        });

        buttonAction3.setText("Guardar");
        buttonAction3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(buttonAction1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonAction3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(buttonAction4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonAction5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAction1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAction5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo205.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(labelMetric5, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMetric5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        panelAdminAdmin.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 86, 1120, -1));

        labelTask1.setForeground(new java.awt.Color(255, 255, 255));
        labelTask1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/admin42.png"))); // NOI18N
        labelTask1.setText("Administradores");
        labelTask1.setDescription(" ");
        panelAdminAdmin.add(labelTask1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        panelAdmin.add(panelAdminAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, -1, -1));

        panelAdminCliente.setBackground(new java.awt.Color(0, 0, 0));
        panelAdminCliente.setPreferredSize(new java.awt.Dimension(1140, 628));

        labelTask2.setForeground(new java.awt.Color(255, 255, 255));
        labelTask2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/admin42.png"))); // NOI18N
        labelTask2.setText("Cliente");
        labelTask2.setDescription(" ");

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelMetric7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric7.setText("DATOS CLIENTES");
        labelMetric7.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N

        jPanel6.setBackground(new java.awt.Color(12, 12, 12));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        buttonAction6.setText("Modificar");
        buttonAction6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction6ActionPerformed(evt);
            }
        });

        buttonAction7.setText("Eliminar");
        buttonAction7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction7ActionPerformed(evt);
            }
        });

        buttonAction9.setText("Matricula");
        buttonAction9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction9ActionPerformed(evt);
            }
        });

        buttonAction10.setText("Clases");
        buttonAction10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(buttonAction10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonAction9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(buttonAction6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonAction7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAction6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAction7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                .addComponent(buttonAction10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAction9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        labelMetric17.setText("Direccion:");
        labelMetric17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        labelMetric18.setText("Telefono:");
        labelMetric18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminClienteTelefono.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric10.setText("Apellidos:");
        labelMetric10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminClienteApellidos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtAdminClienteDireccion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtAdminClienteDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdminClienteDireccionActionPerformed(evt);
            }
        });

        labelMetric19.setText("Ciudad:");
        labelMetric19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminClienteCiudad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric11.setText("C.P.");
        labelMetric11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminClienteCP.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric20.setText("Correo:");
        labelMetric20.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminClienteCorreo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric21.setText("Dni:");
        labelMetric21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminClienteDni.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtAdminClienteDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdminClienteDniActionPerformed(evt);
            }
        });

        labelMetric22.setText("Nombre:");
        labelMetric22.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminClienteNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtAdminClienteNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdminClienteNombreActionPerformed(evt);
            }
        });

        labelMetric23.setText("F. Nacimiento:");
        labelMetric23.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        tablaClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaClientesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaClientesKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tablaClientes);

        listaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaClientesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(listaClientes);

        fecha.setDateFormatString("yyyy-MM-dd");

        labelMetric42.setText("Buscar:");
        labelMetric42.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        labelMetric43.setText("Clases");
        labelMetric43.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMetric7, javax.swing.GroupLayout.PREFERRED_SIZE, 1106, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(labelMetric21, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(txtAdminClienteDni, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(labelMetric23, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(labelMetric11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(txtAdminClienteCP, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(labelMetric18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtAdminClienteTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(labelMetric20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtAdminClienteCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(labelMetric42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(277, 277, 277)
                        .addComponent(labelMetric43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(69, 69, 69)
                            .addComponent(labelMetric17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(txtAdminClienteDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(labelMetric19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(txtAdminClienteCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(79, 79, 79)
                            .addComponent(labelMetric22, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(txtAdminClienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(95, 95, 95)
                                    .addComponent(txtAdminClienteApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(labelMetric10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(labelMetric7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAdminClienteDni, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAdminClienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAdminClienteApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAdminClienteDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAdminClienteCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAdminClienteCP, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAdminClienteTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAdminClienteCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelMetric43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelAdminClienteLayout = new javax.swing.GroupLayout(panelAdminCliente);
        panelAdminCliente.setLayout(panelAdminClienteLayout);
        panelAdminClienteLayout.setHorizontalGroup(
            panelAdminClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAdminClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelAdminClienteLayout.createSequentialGroup()
                        .addComponent(labelTask2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelAdminClienteLayout.setVerticalGroup(
            panelAdminClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdminClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTask2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelAdmin.add(panelAdminCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, -1, -1));

        panelAdminTrabajador.setBackground(new java.awt.Color(0, 0, 0));
        panelAdminTrabajador.setPreferredSize(new java.awt.Dimension(1140, 628));

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        listaTrabajadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaTrabajadoresMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(listaTrabajadores);

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelMetric24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric24.setText("DATOS TRABAJADORES");
        labelMetric24.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jPanel8.add(labelMetric24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 34, 929, 39));

        jPanel9.setBackground(new java.awt.Color(12, 12, 12));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        buttonAction11.setText("Modificar");
        buttonAction11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction11ActionPerformed(evt);
            }
        });

        buttonAction12.setText("Eliminar");
        buttonAction12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction12ActionPerformed(evt);
            }
        });

        buttonAction13.setText("Nuevo");
        buttonAction13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction13ActionPerformed(evt);
            }
        });

        buttonAction14.setText("Guardar");
        buttonAction14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(buttonAction13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(buttonAction11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(buttonAction12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(buttonAction14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAction13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 810, 60));

        labelMetric25.setText("Direccion:");
        labelMetric25.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel8.add(labelMetric25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));

        labelMetric26.setText("Telefono:");
        labelMetric26.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel8.add(labelMetric26, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        txtAdminTrabajTeled.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel8.add(txtAdminTrabajTeled, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 170, 24));

        labelMetric27.setText("Nombre:");
        labelMetric27.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel8.add(labelMetric27, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 80, -1));

        txtAdminTrabajNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel8.add(txtAdminTrabajNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 280, 24));

        txtAdminTrabajDireccion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel8.add(txtAdminTrabajDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 480, 24));

        labelMetric28.setText("Apellidos:");
        labelMetric28.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel8.add(labelMetric28, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 169, -1, -1));

        txtAdminTrabajApellid.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel8.add(txtAdminTrabajApellid, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 167, 310, 24));

        labelMetric29.setText("Cod. Postal");
        labelMetric29.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel8.add(labelMetric29, new org.netbeans.lib.awtextra.AbsoluteConstraints(666, 209, -1, -1));

        txtAdminTrabajCP.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel8.add(txtAdminTrabajCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 210, 100, 24));

        labelMetric30.setText("Correo:");
        labelMetric30.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel8.add(labelMetric30, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 252, -1, -1));

        txtAdminTrabajCorreo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel8.add(txtAdminTrabajCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 253, 430, 24));

        labelMetric31.setText("Dni:");
        labelMetric31.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel8.add(labelMetric31, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, -1, -1));

        txtAdminTrabajDni.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtAdminTrabajDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdminTrabajDniActionPerformed(evt);
            }
        });
        jPanel8.add(txtAdminTrabajDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 140, 24));

        labelMetric32.setText("Contraseña:");
        labelMetric32.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel8.add(labelMetric32, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 119, -1, -1));

        txtAdminTrabajContrsña.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel8.add(txtAdminTrabajContrsña, new org.netbeans.lib.awtextra.AbsoluteConstraints(429, 120, 273, 24));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE))
                .addContainerGap())
        );

        labelTask3.setForeground(new java.awt.Color(255, 255, 255));
        labelTask3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/trabajador42.png"))); // NOI18N
        labelTask3.setText("Trabajador");
        labelTask3.setDescription(" ");

        javax.swing.GroupLayout panelAdminTrabajadorLayout = new javax.swing.GroupLayout(panelAdminTrabajador);
        panelAdminTrabajador.setLayout(panelAdminTrabajadorLayout);
        panelAdminTrabajadorLayout.setHorizontalGroup(
            panelAdminTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminTrabajadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAdminTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelAdminTrabajadorLayout.createSequentialGroup()
                        .addComponent(labelTask3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelAdminTrabajadorLayout.setVerticalGroup(
            panelAdminTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdminTrabajadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTask3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelAdmin.add(panelAdminTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, -1, -1));

        panelAdminMaquina.setBackground(new java.awt.Color(0, 0, 0));
        panelAdminMaquina.setPreferredSize(new java.awt.Dimension(1140, 628));
        panelAdminMaquina.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(12, 12, 12));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));

        jPanel17.setBackground(new java.awt.Color(12, 12, 12));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        buttonAction31.setText("Modificar");
        buttonAction31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction31ActionPerformed(evt);
            }
        });

        buttonAction32.setText("Eliminar");
        buttonAction32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction32ActionPerformed(evt);
            }
        });

        buttonAction33.setText("Nuevo");
        buttonAction33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction33ActionPerformed(evt);
            }
        });

        buttonAction34.setText("Guardar");
        buttonAction34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction34ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAction33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAction34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAction31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAction32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAction33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaMaquinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaMaquinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMaquinasMouseClicked(evt);
            }
        });
        tablaMaquinas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaMaquinasKeyPressed(evt);
            }
        });
        jScrollPane7.setViewportView(tablaMaquinas);

        labelMetric38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric38.setText("MAQUINAS");
        labelMetric38.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N

        txtAdminMaquiNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric46.setText("Nombre:");
        labelMetric46.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        labelMetric47.setText("Clase:");
        labelMetric47.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMetric38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(labelMetric46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAdminMaquiNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelMetric47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbAdminMaquinas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMetric38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtAdminMaquiNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAdminMaquinas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel10.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 12, -1, 500));

        jPanel12.setBackground(new java.awt.Color(12, 12, 12));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));

        tablaClases.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaClases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClasesMouseClicked(evt);
            }
        });
        tablaClases.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaClasesKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tablaClases);

        labelMetric33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric33.setText("CLASES");
        labelMetric33.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N

        buttonIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anadir24.png"))); // NOI18N
        buttonIcon1.setText("buttonIcon1");
        buttonIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIcon1ActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(12, 12, 12));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        buttonAction15.setText("Modificar");
        buttonAction15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction15ActionPerformed(evt);
            }
        });

        buttonAction16.setText("Eliminar");
        buttonAction16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction16ActionPerformed(evt);
            }
        });

        buttonAction17.setText("Nuevo");
        buttonAction17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction17ActionPerformed(evt);
            }
        });

        buttonAction18.setText("Guardar");
        buttonAction18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAction17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAction18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAction15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAction16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAction17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtAdminClasesNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric48.setText("Nombre:");
        labelMetric48.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        labelMetric49.setText("Precio:");
        labelMetric49.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminClasesPrecio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric50.setText("Monitor:");
        labelMetric50.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        cmbAdminClases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAdminClasesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(labelMetric33, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(labelMetric48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtAdminClasesNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(labelMetric49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtAdminClasesPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(labelMetric50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(cmbAdminClases, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(buttonIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(labelMetric33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(labelMetric48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtAdminClasesNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric49, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminClasesPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(labelMetric50, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(cmbAdminClases, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel10.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 12, 541, 500));

        panelAdminMaquina.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 86, -1, -1));

        labelTask4.setForeground(new java.awt.Color(255, 255, 255));
        labelTask4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/maquina42.png"))); // NOI18N
        labelTask4.setText("Clases");
        labelTask4.setDescription(" ");
        panelAdminMaquina.add(labelTask4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        panelAdmin.add(panelAdminMaquina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, -1, -1));

        btnAdminRetroceso.setText("buttonCircle1");
        btnAdminRetroceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminRetrocesoActionPerformed(evt);
            }
        });
        panelAdmin.add(btnAdminRetroceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 6, 20, 20));

        labelMetric3.setText("Administrador:");
        panelAdmin.add(labelMetric3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 9, -1, -1));

        lblAdministrador.setText("xxxxxxxxx");
        panelAdmin.add(lblAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 9, -1, -1));

        lblHoraAdmin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblHoraAdmin.setForeground(new java.awt.Color(255, 204, 0));
        lblHoraAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoraAdmin.setText("Hora");
        panelAdmin.add(lblHoraAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 2, 140, 30));

        panelPrincipal.add(panelAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 660));

        panelTrab.setBackground(new java.awt.Color(0, 0, 0));
        panelTrab.setPreferredSize(new java.awt.Dimension(1140, 628));
        panelTrab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        avatarTrab.setColorPrimario(new java.awt.Color(0, 0, 0));
        avatarTrab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                avatarTrabKeyPressed(evt);
            }
        });

        btnTrabIpod.setText(" ");
        btnTrabIpod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrabIpodActionPerformed(evt);
            }
        });
        avatarTrab.add(btnTrabIpod, new java.awt.GridBagConstraints());

        panelTrab.add(avatarTrab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 1140, 596));

        panelTrabPerfil.setBackground(new java.awt.Color(0, 0, 0));
        panelTrabPerfil.setPreferredSize(new java.awt.Dimension(1140, 628));
        panelTrabPerfil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dniTrabajador.setForeground(new java.awt.Color(255, 255, 255));
        dniTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/admin42.png"))); // NOI18N
        dniTrabajador.setText("Mi Perfil");
        dniTrabajador.setDescription(" ");
        panelTrabPerfil.add(dniTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jPanel31.setBackground(new java.awt.Color(0, 0, 0));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));

        labelMetric89.setText("Direccion:");
        labelMetric89.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        labelMetric90.setText("Telefono:");
        labelMetric90.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorTelefono6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric91.setText("Nombre:");
        labelMetric91.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorNombre16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtAdminAdministradorDireccion6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric92.setText("Apellidos:");
        labelMetric92.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorApellidos10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric93.setText("Cod. Postal");
        labelMetric93.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorCodPostal6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric94.setText("Correo:");
        labelMetric94.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorCorreo6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric95.setText("Dni:");
        labelMetric95.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorNombre17.setEditable(false);
        txtAdminAdministradorNombre17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric96.setText("Contraseña:");
        labelMetric96.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorApellidos11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(labelMetric95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtAdminAdministradorNombre17, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(labelMetric96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(txtAdminAdministradorApellidos11, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel31Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(labelMetric91, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(txtAdminAdministradorNombre16, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)
                            .addComponent(labelMetric92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)
                            .addComponent(txtAdminAdministradorApellidos10, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(labelMetric89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtAdminAdministradorDireccion6, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelMetric93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(txtAdminAdministradorCodPostal6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(labelMetric90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtAdminAdministradorTelefono6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(labelMetric94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(txtAdminAdministradorCorreo6, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMetric95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorNombre17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorApellidos11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMetric91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorNombre16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorApellidos10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMetric89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorDireccion6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorCodPostal6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMetric90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorTelefono6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorCorreo6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        panelTrabPerfil.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        labelMetric41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric41.setText("Star GYM");
        labelMetric41.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        panelTrabPerfil.add(labelMetric41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 1096, 39));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo205.png"))); // NOI18N
        panelTrabPerfil.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 180, -1, 211));

        jPanel19.setBackground(new java.awt.Color(12, 12, 12));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));

        buttonAction21.setText("Modificar");
        buttonAction21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction21ActionPerformed(evt);
            }
        });

        buttonAction25.setText("Guardar");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAction21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonAction25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAction21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTrabPerfil.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(jTable1);

        panelTrabPerfil.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 450, 360, 130));

        labelMetric97.setText("Matriculas:");
        panelTrabPerfil.add(labelMetric97, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 430, -1, -1));

        panelTrab.add(panelTrabPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, -1, -1));

        panelTrabCliente.setBackground(new java.awt.Color(0, 0, 0));
        panelTrabCliente.setPreferredSize(new java.awt.Dimension(1140, 628));

        labelTask6.setForeground(new java.awt.Color(255, 255, 255));
        labelTask6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/admin42.png"))); // NOI18N
        labelTask6.setText("Cliente");
        labelTask6.setDescription(" ");

        jPanel20.setBackground(new java.awt.Color(0, 0, 0));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));

        jPanel21.setBackground(new java.awt.Color(0, 0, 0));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelMetric59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric59.setText("DATOS CLIENTES");
        labelMetric59.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N

        jPanel22.setBackground(new java.awt.Color(12, 12, 12));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        buttonAction28.setText("Modificar");
        buttonAction28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction28ActionPerformed(evt);
            }
        });

        buttonAction29.setText("Eliminar");
        buttonAction29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction29ActionPerformed(evt);
            }
        });

        btnTrabClienteMatricula.setText("Matricula");
        btnTrabClienteMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrabClienteMatriculaActionPerformed(evt);
            }
        });

        btnTrabClienteClases.setText("Clases");
        btnTrabClienteClases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrabClienteClasesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(btnTrabClienteClases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTrabClienteMatricula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(buttonAction28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonAction29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAction28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAction29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                .addComponent(btnTrabClienteClases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTrabClienteMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        labelMetric60.setText("Direccion:");
        labelMetric60.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        labelMetric61.setText("Telefono:");
        labelMetric61.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorTelefono4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric62.setText("Apellidos:");
        labelMetric62.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorNombre8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtAdminAdministradorNombre8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdminAdministradorNombre8ActionPerformed(evt);
            }
        });

        txtAdminAdministradorDireccion4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtAdminAdministradorDireccion4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdminAdministradorDireccion4ActionPerformed(evt);
            }
        });

        labelMetric63.setText("Ciudad:");
        labelMetric63.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorApellidos7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric64.setText("C.P.");
        labelMetric64.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorCodPostal4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric65.setText("Correo:");
        labelMetric65.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorCorreo4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric66.setText("Dni:");
        labelMetric66.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorNombre9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtAdminAdministradorNombre9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdminAdministradorNombre9ActionPerformed(evt);
            }
        });

        labelMetric67.setText("Nombre:");
        labelMetric67.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorApellidos8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtAdminAdministradorApellidos8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdminAdministradorApellidos8ActionPerformed(evt);
            }
        });

        labelMetric68.setText("F. Nacimiento:");
        labelMetric68.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        tablaClientes2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaClientes2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientes2MouseClicked(evt);
            }
        });
        tablaClientes2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaClientes2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaClientes2KeyReleased(evt);
            }
        });
        jScrollPane13.setViewportView(tablaClientes2);

        listaClientes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaClientes1MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(listaClientes1);

        fecha1.setDateFormatString("yyyy-MM-dd");

        labelMetric69.setText("Buscar:");
        labelMetric69.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        labelMetric70.setText("Clases");
        labelMetric70.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMetric59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(txtAdminAdministradorDireccion4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(labelMetric63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(txtAdminAdministradorApellidos7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(labelMetric64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(txtAdminAdministradorCodPostal4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createSequentialGroup()
                                .addComponent(txtAdminAdministradorTelefono4, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelMetric65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAdminAdministradorCorreo4, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(labelMetric66, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(labelMetric67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(txtAdminAdministradorApellidos8, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(labelMetric62, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(txtAdminAdministradorNombre9, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelMetric68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAdminAdministradorNombre8, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(199, 199, 199)
                                .addComponent(labelMetric69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtnombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jScrollPane13)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMetric59, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addGap(52, 82, Short.MAX_VALUE)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtAdminAdministradorNombre9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelMetric66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelMetric68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAdminAdministradorNombre8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAdminAdministradorApellidos8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelMetric60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelMetric63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAdminAdministradorDireccion4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(labelMetric64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelMetric65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAdminAdministradorCorreo4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAdminAdministradorTelefono4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(labelMetric61, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtAdminAdministradorApellidos7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAdminAdministradorCodPostal4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric70, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric69, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombre1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelTrabClienteLayout = new javax.swing.GroupLayout(panelTrabCliente);
        panelTrabCliente.setLayout(panelTrabClienteLayout);
        panelTrabClienteLayout.setHorizontalGroup(
            panelTrabClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTrabClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTrabClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTrabClienteLayout.createSequentialGroup()
                        .addComponent(labelTask6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTrabClienteLayout.setVerticalGroup(
            panelTrabClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTrabClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTask6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTrab.add(panelTrabCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, -1, -1));

        panelTrabMatricula.setBackground(new java.awt.Color(0, 0, 0));
        panelTrabMatricula.setPreferredSize(new java.awt.Dimension(1140, 628));
        panelTrabMatricula.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(0, 0, 0));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel24.setBackground(new java.awt.Color(0, 0, 0));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));

        labelMetric72.setText("Direccion:");
        labelMetric72.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        labelMetric73.setText("Telefono:");
        labelMetric73.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorTelefono5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric74.setText("Apellidos:");
        labelMetric74.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorNombre10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtAdminAdministradorDireccion5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric75.setText("F. Nacimiento:");
        labelMetric75.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        labelMetric76.setText("C.P.");
        labelMetric76.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorCodPostal5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric77.setText("Correo:");
        labelMetric77.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorCorreo5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric78.setText("Dni:");
        labelMetric78.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtAdminAdministradorNombre11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtAdminAdministradorNombre11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdminAdministradorNombre11ActionPerformed(evt);
            }
        });

        labelMetric79.setText("Nombre:");
        labelMetric79.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        passwordField2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtAdminAdministradorCorreo7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        labelMetric80.setText("Correo:");
        labelMetric80.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(labelMetric78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtAdminAdministradorNombre11, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(labelMetric79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(labelMetric73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAdminAdministradorTelefono5, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelMetric77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtAdminAdministradorCorreo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(labelMetric76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAdminAdministradorCodPostal5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(labelMetric74, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtAdminAdministradorNombre10, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(labelMetric72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtAdminAdministradorDireccion5, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(labelMetric80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtAdminAdministradorCorreo7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(labelMetric75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(fecha3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)))))
                .addGap(27, 27, 27))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAdminAdministradorNombre11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(labelMetric74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtAdminAdministradorNombre10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAdminAdministradorDireccion5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorCorreo7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAdminAdministradorTelefono5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorCorreo5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminAdministradorCodPostal5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel25.setBackground(new java.awt.Color(12, 12, 12));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        buttonAction39.setText("Realizar");
        buttonAction39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction39ActionPerformed(evt);
            }
        });

        buttonAction48.setText("Nueva");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonAction39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAction48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonAction39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo205.png"))); // NOI18N

        listTrabMatricTarifa.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane15.setViewportView(listTrabMatricTarifa);

        buttonAeroRight2.setForeground(new java.awt.Color(0, 0, 0));
        buttonAeroRight2.setText(">>");
        buttonAeroRight2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        buttonAeroRight2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAeroRight2ActionPerformed(evt);
            }
        });

        buttonAeroLeft2.setForeground(new java.awt.Color(0, 0, 0));
        buttonAeroLeft2.setText("<<");
        buttonAeroLeft2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        buttonAeroLeft2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAeroLeft2ActionPerformed(evt);
            }
        });

        listTrabMatricClases.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane19.setViewportView(listTrabMatricClases);

        labelMetric51.setText("Clases:");

        labelMetric52.setText("Mi Tarifa:");

        labelMetric53.setText("Total:");

        lblTrabTotalMatricula.setText("Importe");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMetric51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(labelMetric52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonAeroLeft2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonAeroRight2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelMetric53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTrabTotalMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addComponent(jLabel4))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMetric51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(buttonAeroLeft2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonAeroRight2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelMetric53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTrabTotalMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        panelTrabMatricula.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 151, 1020, 420));

        labelTask7.setForeground(new java.awt.Color(255, 255, 255));
        labelTask7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/trabajador42.png"))); // NOI18N
        labelTask7.setText("Matricula");
        labelTask7.setDescription(" ");
        panelTrabMatricula.add(labelTask7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        labelMetric71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric71.setText("Star GYM");
        labelMetric71.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        panelTrabMatricula.add(labelMetric71, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 1096, 39));

        panelTrab.add(panelTrabMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, -1, -1));

        panelTrabCobro.setBackground(new java.awt.Color(0, 0, 0));
        panelTrabCobro.setPreferredSize(new java.awt.Dimension(1140, 628));
        panelTrabCobro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel26.setBackground(new java.awt.Color(0, 0, 0));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel29.setBackground(new java.awt.Color(0, 0, 0));
        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaTrabCobroCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaTrabCobroCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTrabCobroClienteMouseClicked(evt);
            }
        });
        tablaTrabCobroCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaTrabCobroClienteKeyReleased(evt);
            }
        });
        jScrollPane18.setViewportView(tablaTrabCobroCliente);

        jPanel29.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 661, 157));

        labelMetric83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric83.setText("Clientes");
        labelMetric83.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jPanel29.add(labelMetric83, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 660, -1));

        tablaTrabCobroMensual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaTrabCobroMensual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTrabCobroMensualMouseClicked(evt);
            }
        });
        tablaTrabCobroMensual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaTrabCobroMensualKeyReleased(evt);
            }
        });
        jScrollPane20.setViewportView(tablaTrabCobroMensual);

        jPanel29.add(jScrollPane20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 661, 157));

        labelMetric88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric88.setText("Mensualidades");
        labelMetric88.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jPanel29.add(labelMetric88, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 660, -1));

        buttonAction46.setText("Pagar");
        jPanel29.add(buttonAction46, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, -1, -1));

        jPanel26.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 820, 440));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo205.png"))); // NOI18N
        jPanel26.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 60, -1, 211));

        labelMetric84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric84.setText("Star GYM");
        labelMetric84.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jPanel26.add(labelMetric84, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, -1));

        panelTrabCobro.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 86, 1120, 531));

        Cobro.setForeground(new java.awt.Color(255, 255, 255));
        Cobro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/trabajador42.png"))); // NOI18N
        Cobro.setText("Trabajador");
        Cobro.setDescription(" ");
        panelTrabCobro.add(Cobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        panelTrab.add(panelTrabCobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, -1, -1));

        btnTrabajadorRetroceso.setText("buttonCircle1");
        btnTrabajadorRetroceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrabajadorRetrocesoActionPerformed(evt);
            }
        });
        panelTrab.add(btnTrabajadorRetroceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 6, 20, 20));

        labelMetric87.setText("Trabajador:");
        panelTrab.add(labelMetric87, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 9, -1, -1));

        lblTrabajador.setText("xxxxxxxxx");
        panelTrab.add(lblTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 9, -1, -1));

        lblHoraTrabajador.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblHoraTrabajador.setForeground(new java.awt.Color(255, 204, 0));
        lblHoraTrabajador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoraTrabajador.setText("Hora");
        panelTrab.add(lblHoraTrabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 2, 140, 30));

        panelPrincipal.add(panelTrab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoguinEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoguinEntrarActionPerformed
        //Boton loguin entrar        

        if(f.iniciarSesion(this.txtLoguinUsuario.getText(), this.txtLoguinContraseña.getText())==1){
            panelLoguin.setVisible(false);
            avatarAdmin.setVisible(true);
            panelAdmin.setVisible(true);
            panelAdminAdmin.setVisible(false);
            
            panelAdminCliente.setVisible(false);
            panelAdminMaquina.setVisible(false);
            panelAdminTrabajador.setVisible(false);
            
            avatarTrab.setVisible(false);
            panelTrab.setVisible(false);
            panelTrabPerfil.setVisible(false);
            panelTrabCliente.setVisible(false);
            panelTrabMatricula.setVisible(false);
            panelTrabCobro.setVisible(false);
            
            //Colocar en el label superior izquierdo el identificador
            lblAdministrador.setText(txtLoguinUsuario.getText());
            
        }else if(f.iniciarSesion(this.txtLoguinUsuario.getText(), this.txtLoguinContraseña.getText())==0){
            panelLoguin.setVisible(false);
            avatarTrab.setVisible(true);
            panelTrab.setVisible(true);
            panelTrabPerfil.setVisible(false);
            panelTrabCliente.setVisible(false);
            panelTrabMatricula.setVisible(false);
            panelTrabCobro.setVisible(false);
            
            panelLoguin.setVisible(false);
            avatarAdmin.setVisible(false);
            panelAdmin.setVisible(false);
            panelAdminAdmin.setVisible(false);
            panelAdminCliente.setVisible(false);
            panelAdminMaquina.setVisible(false);
            panelAdminTrabajador.setVisible(false);
            
            //Colocar en el label superior izquierdo el identificador
            lblTrabajador.setText(txtLoguinUsuario.getText());  
        }else{
           JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    }//GEN-LAST:event_btnLoguinEntrarActionPerformed

    private void btnAdminIpodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminIpodActionPerformed
        //Boton ipod avatarAdmin
        if(avatarAdmin.getSelectedtitulo().equals("Admin")){
            panelLoguin.setVisible(false);
            avatarAdmin.setVisible(false);
            panelAdmin.setVisible(true);
            panelAdminAdmin.setVisible(true);
            
            panelAdminCliente.setVisible(false);
            panelAdminMaquina.setVisible(false);
            panelAdminTrabajador.setVisible(false);
            this.listaAdmin.setModel(f.listAdmin());
            
            txtAdminAdministradorDni.setText("");
            txtAdminAdministradorNombre.setText("");
            txtAdminAdministradorContraseña.setText("");
            txtAdminAdministradorApellidos.setText("");
            txtAdminAdministradorDireccion.setText("");
            txtAdminAdministradorCodPostal.setText("");
            txtAdminAdministradorTelefono.setText("");
            txtAdminAdministradorCorreo.setText("");
        }
        
        if(avatarAdmin.getSelectedtitulo().equals("Clientes")){
            panelLoguin.setVisible(false);
            avatarAdmin.setVisible(false);
            panelAdmin.setVisible(true);
            panelAdminAdmin.setVisible(false);
            
            panelAdminCliente.setVisible(true);
            panelAdminMaquina.setVisible(false);
            panelAdminTrabajador.setVisible(false);
            
            txtAdminClienteDni.setText("");
            txtAdminClienteNombre.setText("");
            txtAdminClienteApellidos.setText("");
            txtAdminClienteDireccion.setText("");
            txtAdminClienteCP.setText("");
            txtAdminClienteTelefono.setText("");
            txtAdminClienteCorreo.setText("");
            txtAdminClienteCiudad.setText("");
            
            this.tablaClientes.setModel(f.listarClientes());
            
        }
        
        if(avatarAdmin.getSelectedtitulo().equals("Trabajadores")){
            panelLoguin.setVisible(false);
            avatarAdmin.setVisible(false);
            panelAdmin.setVisible(true);
            panelAdminAdmin.setVisible(false);
            
            panelAdminCliente.setVisible(false);
            panelAdminMaquina.setVisible(false);
            panelAdminTrabajador.setVisible(true);
            this.listaTrabajadores.setModel(f.listTrabajador());
            
            txtAdminTrabajDni.setText("");
            txtAdminTrabajNombre.setText("");
            txtAdminTrabajApellid.setText("");
            txtAdminTrabajDireccion.setText("");
            txtAdminTrabajCP.setText("");
            txtAdminTrabajTeled.setText("");
            txtAdminTrabajCorreo.setText("");
            txtAdminTrabajContrsña.setText("");
        }
        
        if(avatarAdmin.getSelectedtitulo().equals("Clases")){
            panelLoguin.setVisible(false);
            avatarAdmin.setVisible(false);
            panelAdmin.setVisible(true);
            panelAdminAdmin.setVisible(false);
            
            panelAdminCliente.setVisible(false);
            panelAdminMaquina.setVisible(true);
            panelAdminTrabajador.setVisible(false);
            
            //------------------------------------------------------------------------
            this.tablaClases.setModel(f.listarClases());
            
//            TableColumn tc = tablaClases.getColumnModel().getColumn(3);
//            TableCellEditor tce = new DefaultCellEditor(jcb);
//            tc.setCellEditor(tce);
            //------------------------------------------------------------------------
            this.tablaMaquinas.setModel(f.listarMaquinas());
            this.cmbAdminClases.setModel(f.comboMonitores());
            this.cmbAdminMaquinas.setModel(f.comboClases());
            
            txtAdminClasesNombre.setText("");
            txtAdminClasesPrecio.setText("");
            
            txtAdminMaquiNombre.setText("");
        }
        
                
        if(avatarAdmin.getSelectedtitulo().equals("Exit")){
            txtLoguinUsuario.setText("");
            txtLoguinContraseña.setText("");
            
            panelLoguin.setVisible(true);
            avatarAdmin.setVisible(false);
            panelAdmin.setVisible(false);
            panelAdminAdmin.setVisible(false);
            
            panelAdminCliente.setVisible(false);
            panelAdminMaquina.setVisible(false);
            panelAdminTrabajador.setVisible(false);
        }
    }//GEN-LAST:event_btnAdminIpodActionPerformed

    private void btnAdminRetrocesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminRetrocesoActionPerformed
        //boton admin retroceso
        panelLoguin.setVisible(false);
        avatarAdmin.setVisible(true);
        panelAdmin.setVisible(true);
        panelAdminAdmin.setVisible(false);
        
        panelAdminCliente.setVisible(false);
        panelAdminMaquina.setVisible(false);
        panelAdminTrabajador.setVisible(false);
    }//GEN-LAST:event_btnAdminRetrocesoActionPerformed

    private void avatarAdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_avatarAdminKeyPressed
        
    }//GEN-LAST:event_avatarAdminKeyPressed

    private void listaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaClientesMouseClicked
     
        
    }//GEN-LAST:event_listaClientesMouseClicked

    private void txtAdminClienteNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdminClienteNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdminClienteNombreActionPerformed

    private void txtAdminClienteDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdminClienteDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdminClienteDniActionPerformed

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        //admin tabla clientes
        this.txtAdminClienteDni.setText((String) this.tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0));
        this.txtAdminClienteNombre.setText((String) this.tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 1));
        this.txtAdminClienteApellidos.setText((String) this.tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 2));
        this.txtAdminClienteDireccion.setText((String) this.tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 3));
        this.txtAdminClienteCiudad.setText((String) this.tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 4));
        this.txtAdminClienteCP.setText((String) this.tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 5));
        this.txtAdminClienteTelefono.setText((String) this.tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 6));
       // this.txtAdminAdministradorApellidos4.setText((String) this.tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 7));
        this.txtAdminClienteCorreo.setText((String) this.tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 8));
        
        this.listaClientes.setModel(f.listClases((String) this.tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0)));
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void txtAdminClienteDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdminClienteDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdminClienteDireccionActionPerformed

    private void buttonAction7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction7ActionPerformed
        //boton admin cliente eliminar
        if (tablaClientes.getSelectedRow() > -1) {
            if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar?, se eliminaran las matriculas correspondientes")==0){
                f.eliminarCliente((String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0));
                this.tablaClientes.setModel(f.listarClientes());
                this.listaClientes.setModel(f.listClases(dniCliente));
                   // textField.getDocument().addDocumentListener(new documentListener());
                   
            txtAdminClienteDni.setText("");
            txtAdminClienteNombre.setText("");
            txtAdminClienteApellidos.setText("");
            txtAdminClienteDireccion.setText("");
            txtAdminClienteCP.setText("");
            txtAdminClienteTelefono.setText("");
            txtAdminClienteCorreo.setText("");
            txtAdminClienteCiudad.setText("");

            }
     
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila");
        }
        
    }//GEN-LAST:event_buttonAction7ActionPerformed

    private void listaAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaAdminMouseClicked
        // Lista administradores

        String admin = listaAdmin.getSelectedValue();
        List<String> l = Arrays.asList(admin.split(" "));
        
        String idAdm = l.get(0);
        
        Administrador a= f.datosAdmin(idAdm); 
        
        System.out.println("La id del admin es: " + idAdm);
        
        this.txtAdminAdministradorDni.setText(a.getIdEmpleado());
        this.txtAdminAdministradorNombre.setText(a.getNombre());
        this.txtAdminAdministradorDireccion.setText(a.getDireccion());
        this.txtAdminAdministradorTelefono.setText(String.valueOf(a.getTelefono()));
        
        this.txtAdminAdministradorContraseña.setText(a.getClave());
        this.txtAdminAdministradorApellidos.setText(a.getApellidos());
        this.txtAdminAdministradorCodPostal.setText(String.valueOf(a.getCodPostal()));
        this.txtAdminAdministradorCorreo.setText(a.getCorreo());
        
    }//GEN-LAST:event_listaAdminMouseClicked

    private void tablaClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaClientesKeyReleased
        String dni=(String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0);
        String nombre=(String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 1);
        String apellidos=(String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 2);
        String fechaNacimiento=(String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(),7);
        String direccion=(String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 3);
        String codPostal=(String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 5);
        String ciudad=(String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 4);
        String telefono=(String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 6);
        String correo=(String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 8);
    
        System.out.println("tecla presionada");
        
        
        f.modificarClienteTable(dni, nombre, apellidos, fechaNacimiento, direccion, Integer.parseInt(codPostal), ciudad, Integer.parseInt(telefono), correo);
        
        this.tablaClientes.setModel(f.listarClientes());
        
        
    }//GEN-LAST:event_tablaClientesKeyReleased

    private void tablaClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaClientesKeyPressed
       //Tabla AdminCliente boton suprimir para eliminar cliente keypressed
        String dni=(String) tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0);
        if(evt.getKeyCode()==KeyEvent.VK_DELETE){
            
            f.eliminarCliente(dni);
            
            
            
        
        }
    }//GEN-LAST:event_tablaClientesKeyPressed

    private void txtAdminTrabajDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdminTrabajDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdminTrabajDniActionPerformed

    private void buttonAction10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction10ActionPerformed
        //Admin Cliente boton para añadir o quitar clases de la matricula
        diaAdminClienteTarifa.setLocationRelativeTo(panelPrincipal);
        diaAdminClienteTarifa.setSize(387, 315);
        diaAdminClienteTarifa.setVisible(true);
    }//GEN-LAST:event_buttonAction10ActionPerformed

    private void buttonIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIcon1ActionPerformed
        //Admin maquinas boton para añadir monitor al registro
        diaAdminMaquinaMonitor.setLocationRelativeTo(panelPrincipal);
        diaAdminMaquinaMonitor.setSize(537, 430);
        diaAdminMaquinaMonitor.setVisible(true);
        this.tbMonitores.setModel(f.listarMonitores());
        
        txtMonitorDni.setText("");
        txtMonitorNombre.setText("");
        txtMonitorApellidos.setText("");
        txtMonitorTelefono.setText("");
        txtMonitorCorreo.setText("");
    }//GEN-LAST:event_buttonIcon1ActionPerformed

    private void buttonAction22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction22ActionPerformed
        //Boton dialog monitores eliminar
        f.eliminarMonitor((String) tbMonitores.getValueAt(tbMonitores.getSelectedRow(), 0) );
        this.tbMonitores.setModel(f.listarMonitores());
    }//GEN-LAST:event_buttonAction22ActionPerformed

    private void tbMonitoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonitoresMouseClicked
        this.txtMonitorDni.setText((String) this.tbMonitores.getValueAt(tbMonitores.getSelectedRow(), 0));
        this.txtMonitorNombre.setText((String) this.tbMonitores.getValueAt(tbMonitores.getSelectedRow(), 1));
        this.txtMonitorApellidos.setText((String) this.tbMonitores.getValueAt(tbMonitores.getSelectedRow(), 2));
        this.txtMonitorTelefono.setText((String) this.tbMonitores.getValueAt(tbMonitores.getSelectedRow(), 3));
        this.txtMonitorCorreo.setText((String) this.tbMonitores.getValueAt(tbMonitores.getSelectedRow(), 4));
        
    }//GEN-LAST:event_tbMonitoresMouseClicked

    private void tbMonitoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMonitoresKeyReleased
    String dni=(String) tbMonitores.getValueAt(tbMonitores.getSelectedRow(), 0);
    String nombre=(String) tbMonitores.getValueAt(tbMonitores.getSelectedRow(), 1);
    String apellidos=(String) tbMonitores.getValueAt(tbMonitores.getSelectedRow(), 2);
    String telefono=(String) tbMonitores.getValueAt(tbMonitores.getSelectedRow(), 3);
    String correo=(String) tbMonitores.getValueAt(tbMonitores.getSelectedRow(), 4);
    
    f.modificarMonitor(dni, nombre, apellidos, Integer.parseInt(telefono), correo);
    this.tablaClases.setModel(f.listarClases());
    }//GEN-LAST:event_tbMonitoresKeyReleased

    private void buttonAction18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction18ActionPerformed
        //Admin guardar clase
        if(!txtAdminClasesNombre.getText().isEmpty() && !txtAdminClasesPrecio.getText().isEmpty()){
        if(c.comprobacion(txtAdminClasesPrecio.getText())){
        String dniMonitor = (String)this.cmbAdminClases.getSelectedItem();
        
        String dniM = dniMonitor.substring(0, 9);
        System.out.println("El dni del monitor es: " + dniM);
        f.añadirClase(this.txtAdminClasesNombre.getText(), Double.parseDouble(this.txtAdminClasesPrecio.getText()), dniM);
        
        this.tablaClases.setModel(f.listarClases());
//        TableColumn tc = tablaClases.getColumnModel().getColumn(3);
//        TableCellEditor tce = new DefaultCellEditor(jcb);
//        tc.setCellEditor(tce);
        
        this.cmbAdminMaquinas.setModel(f.comboClases());
        
        txtAdminClasesNombre.setText("");
        txtAdminClasesPrecio.setText("");
        
            JOptionPane.showMessageDialog(null, "Clase guardada");
        }else{
            JOptionPane.showMessageDialog(null, "Solo caracteres numericos para el precio");
        }
        }else{
            JOptionPane.showMessageDialog(null, "Rellena todos los campos");
        }
    }//GEN-LAST:event_buttonAction18ActionPerformed

    private void buttonAction16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction16ActionPerformed
        //boton admin clase eliminar
        if(tablaClases.getSelectedRow()> -1) {
            if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar?")==0){
        String id;
        id= String.valueOf( this.tablaClases.getValueAt(tablaClases.getSelectedRow(), 0)); 
        f.eliminarClase(Integer.parseInt(id));
        this.tablaClases.setModel(f.listarClases());
        
        txtAdminClasesNombre.setText("");
        txtAdminClasesPrecio.setText("");
            }
      }else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }
    }//GEN-LAST:event_buttonAction16ActionPerformed

    private void tablaClasesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaClasesKeyReleased
        //key released tabla clases
//        String idClase=(String) tablaClases.getValueAt(tablaClases.getSelectedRow(), 0);
//        String nombre=(String) tablaClases.getValueAt(tablaClases.getSelectedRow(), 1);
//        String precio=(String) tablaClases.getValueAt(tablaClases.getSelectedRow(), 2);
//        String idMonitor=(String) tablaClases.getValueAt(tablaClases.getSelectedRow(), 3);
//        f.modificarClase(Integer.parseInt(idClase), nombre, Double.parseDouble(precio), idMonitor);
//        
//        tablaClases.setModel(f.listarClases());
        
        
//        String idMonitor = jcb.getSelectedItem().toString();
//        String idM = idMonitor.substring(0, 9);
        
        //List<String> l = Arrays.asList(idMonitor.split(" "));
        
        //String idM = l.get(0);
        
//        System.out.println("El idDe monitor de la tabla es: " + idM);
        
//        TableColumn tc = tablaClases.getColumnModel().getColumn(3);
//        TableCellEditor tce = new DefaultCellEditor(jcb);
//        tc.setCellEditor(tce);
    }//GEN-LAST:event_tablaClasesKeyReleased

    private void tablaClasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClasesMouseClicked
        this.txtAdminClasesNombre.setText((String) this.tablaClases.getValueAt(tablaClases.getSelectedRow(), 1));
        this.txtAdminClasesPrecio.setText((String) this.tablaClases.getValueAt(tablaClases.getSelectedRow(), 2));
        
        
    }//GEN-LAST:event_tablaClasesMouseClicked

    private void buttonAction34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction34ActionPerformed
    //Boton admin maquinas guardar
        if(!txtAdminMaquiNombre.getText().isEmpty()){    
        String idClaseProv = cmbAdminMaquinas.getSelectedItem().toString();
        List<String> l = Arrays.asList(idClaseProv.split(" "));
        
        int idClase = Integer.parseInt(l.get(0));
        System.out.println("la Id es: " + idClase);
        
        f.añadirMaquina(idClase, this.txtAdminMaquiNombre.getText());
        this.tablaMaquinas.setModel(f.listarMaquinas());
        
        txtAdminMaquiNombre.setText("");
        }else{
            JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        }
    }//GEN-LAST:event_buttonAction34ActionPerformed

    private void cmbAdminClasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAdminClasesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAdminClasesActionPerformed

    private void buttonAction32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction32ActionPerformed
        //boton admin clase eliminar maquina
        if(tablaMaquinas.getSelectedRow()> -1) {
            if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar?")==0){
        String idMaquina= (String) tablaMaquinas.getValueAt(tablaMaquinas.getSelectedRow(), 0);
        f.eliminarMaquina(Integer.parseInt(idMaquina));
        this.tablaMaquinas.setModel(f.listarMaquinas());
        
        txtAdminMaquiNombre.setText("");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_buttonAction32ActionPerformed

    private void buttonAction9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction9ActionPerformed
try{
        
        conexion cn = new conexion();
        String dir;
        dir = "src/reporte/matricula.jrxml";
        Map parametro = new HashMap();
        parametro.put("parameter1", txtAdminClienteDni.getText());
        JasperReport reporteJasper = JasperCompileManager.compileReport(dir);
        JasperPrint mostrarReporte = JasperFillManager.fillReport(reporteJasper, parametro, cn.getConexion());
        JasperViewer visor = new JasperViewer(mostrarReporte, false);
        visor.setVisible(true);
       
        }catch(JRException ex){
            Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
            
        }            
    }//GEN-LAST:event_buttonAction9ActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        //Boton dialog monitores guardar
        f.añadirMonitor(this.txtMonitorDni.getText(), this.txtMonitorNombre.getText(), this.txtMonitorApellidos.getText(), Integer.parseInt(this.txtMonitorTelefono.getText()), this.txtMonitorCorreo.getText());
        this.tbMonitores.setModel(f.listarMonitores());
        cmbAdminClases.setModel(f.comboMonitores());
        
        txtMonitorDni.setText("");
        txtMonitorNombre.setText("");
        txtMonitorApellidos.setText("");
        txtMonitorTelefono.setText("");
        txtMonitorCorreo.setText("");
    }//GEN-LAST:event_GuardarActionPerformed

    private void tablaMaquinasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaMaquinasKeyPressed
        //admin
    }//GEN-LAST:event_tablaMaquinasKeyPressed

    private void tablaMaquinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMaquinasMouseClicked
        //admin tabla maquinas
        this.txtAdminMaquiNombre.setText((String) tablaMaquinas.getValueAt(tablaMaquinas.getSelectedRow(), 1));
    }//GEN-LAST:event_tablaMaquinasMouseClicked

    private void buttonAction33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction33ActionPerformed
        //boton admin maquina nuevo
        txtAdminMaquiNombre.setText("");
    }//GEN-LAST:event_buttonAction33ActionPerformed

    private void btnTrabIpodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrabIpodActionPerformed
        //Menu Trabajador
        if(avatarTrab.getSelectedtitulo().equals("Perfil")){
            panelLoguin.setVisible(false);
            avatarTrab.setVisible(false);
            panelTrab.setVisible(true);
            panelTrabPerfil.setVisible(true);
            panelTrabCliente.setVisible(false);
            panelTrabMatricula.setVisible(false);
            panelTrabCobro.setVisible(false);
            //diego
            String idTra= lblTrabajador.getText();
            Trabajador t= f.datosTrabajador(idTra);
            
            System.out.println("La id del admin es: " + idTra);
        
            this.txtAdminAdministradorNombre17.setText(t.getIdEmpleado());
            this.txtAdminAdministradorNombre16.setText(t.getNombre());
            this.dniTrabajador.setText(t.getNombre());
            this.txtAdminAdministradorDireccion6.setText(t.getDireccion());
            this.txtAdminAdministradorTelefono6.setText(String.valueOf(t.getTelefono()));
            
        
            this.txtAdminAdministradorApellidos11.setText(t.getClave());
            this.txtAdminAdministradorApellidos10.setText(t.getApellidos());
            this.txtAdminAdministradorCodPostal6.setText(String.valueOf(t.getCodPostal()));
            this.txtAdminAdministradorCorreo6.setText(t.getCorreo());
            
            
        }
        
        if(avatarTrab.getSelectedtitulo().equals("Clientes")){
            panelLoguin.setVisible(false);
            avatarTrab.setVisible(false);
            panelTrab.setVisible(true);
            panelTrabPerfil.setVisible(false);
            panelTrabCliente.setVisible(true);
            panelTrabMatricula.setVisible(false);
            panelTrabCobro.setVisible(false);
            
            this.tablaClientes2.setModel(f.listarClientes());
            
        }
        
        if(avatarTrab.getSelectedtitulo().equals("Matricula")){
            panelLoguin.setVisible(false);
            avatarTrab.setVisible(false);
            panelTrab.setVisible(true);
            panelTrabPerfil.setVisible(false);
            panelTrabCliente.setVisible(false);
            panelTrabMatricula.setVisible(true);
            panelTrabCobro.setVisible(false);
            
            listTrabMatricClases.setModel(f.listTodasClases());
            listTrabMatricTarifa.setModel(new DefaultListModel<>());
        }
        
        if(avatarTrab.getSelectedtitulo().equals("Cobros")){
            panelLoguin.setVisible(false);
            avatarTrab.setVisible(false);
            panelTrab.setVisible(true);
            panelTrabPerfil.setVisible(false);
            panelTrabCliente.setVisible(false);
            panelTrabMatricula.setVisible(false);
            panelTrabCobro.setVisible(true);
            

        }
        
        if(avatarTrab.getSelectedtitulo().equals("Exit")){
            panelLoguin.setVisible(true);
            avatarTrab.setVisible(false);
            panelTrab.setVisible(true);
            panelTrabPerfil.setVisible(false);
            panelTrabCliente.setVisible(false);
            panelTrabMatricula.setVisible(false);
            panelTrabCobro.setVisible(false);
        }
        
    }//GEN-LAST:event_btnTrabIpodActionPerformed

    private void avatarTrabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_avatarTrabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_avatarTrabKeyPressed

    private void buttonAction29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction29ActionPerformed
         if (tablaClientes2.getSelectedRow() > -1) {
            if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar?, se eliminaran las matriculas correspondientes")==0){
                f.eliminarCliente((String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 0));
                this.tablaClientes2.setModel(f.listarClientes());
            }
            }else{
             JOptionPane.showMessageDialog(null, "Seleccione una fila");
         }
    }//GEN-LAST:event_buttonAction29ActionPerformed

    private void btnTrabClienteMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrabClienteMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTrabClienteMatriculaActionPerformed

    private void btnTrabClienteClasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrabClienteClasesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTrabClienteClasesActionPerformed

    private void txtAdminAdministradorDireccion4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdminAdministradorDireccion4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdminAdministradorDireccion4ActionPerformed

    private void txtAdminAdministradorNombre9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdminAdministradorNombre9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdminAdministradorNombre9ActionPerformed

    private void txtAdminAdministradorApellidos8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdminAdministradorApellidos8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdminAdministradorApellidos8ActionPerformed

    private void tablaClientes2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientes2MouseClicked
        this.txtAdminAdministradorNombre9.setText((String) this.tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 0));
        this.txtAdminAdministradorApellidos8.setText((String) this.tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 1));
        this.txtAdminAdministradorNombre8.setText((String) this.tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 2));
        this.txtAdminAdministradorDireccion4.setText((String) this.tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 3));
        this.txtAdminAdministradorApellidos7.setText((String) this.tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 4));
        this.txtAdminAdministradorCodPostal4.setText((String) this.tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 5));
        this.txtAdminAdministradorTelefono4.setText((String) this.tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 6));
        this.txtAdminAdministradorCorreo4.setText((String) this.tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 8));
    }//GEN-LAST:event_tablaClientes2MouseClicked

    private void tablaClientes2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaClientes2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaClientes2KeyPressed

    private void tablaClientes2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaClientes2KeyReleased
        String dni=(String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 0);
        String nombre=(String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 1);
        String apellidos=(String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 2);
        String fechaNacimiento=(String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(),7);
        String direccion=(String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 3);
        String codPostal=(String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 5);
        String ciudad=(String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 4);
        String telefono=(String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 6);
        String correo=(String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 8);
        System.out.println("tecla presionada");
        
        
        f.modificarClienteTable(dni, nombre, apellidos, fechaNacimiento, direccion, Integer.parseInt(codPostal), ciudad, Integer.parseInt(telefono), correo);
        
        this.tablaClientes.setModel(f.listarClientes());
        
    }//GEN-LAST:event_tablaClientes2KeyReleased

    private void listaClientes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaClientes1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_listaClientes1MouseClicked

    private void txtAdminAdministradorNombre11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdminAdministradorNombre11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdminAdministradorNombre11ActionPerformed

    private void tablaTrabCobroClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTrabCobroClienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaTrabCobroClienteMouseClicked

    private void tablaTrabCobroClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaTrabCobroClienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaTrabCobroClienteKeyReleased

    private void btnTrabajadorRetrocesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrabajadorRetrocesoActionPerformed
        //Boton trabajador retroceso
        panelLoguin.setVisible(false);
            avatarTrab.setVisible(true);
            panelTrab.setVisible(true);
            panelTrabPerfil.setVisible(false);
            panelTrabCliente.setVisible(false);
            panelTrabMatricula.setVisible(false);
            panelTrabCobro.setVisible(false);
            
            panelLoguin.setVisible(false);
            avatarAdmin.setVisible(false);
            panelAdmin.setVisible(false);
            panelAdminAdmin.setVisible(false);
            
            panelAdminCliente.setVisible(false);
            panelAdminMaquina.setVisible(false);
            panelAdminTrabajador.setVisible(false);
    }//GEN-LAST:event_btnTrabajadorRetrocesoActionPerformed

    private void buttonAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction1ActionPerformed
        //Boton admin nuevo
        txtAdminAdministradorDni.setText("");
        txtAdminAdministradorNombre.setText("");
        txtAdminAdministradorContraseña.setText("");
        txtAdminAdministradorApellidos.setText("");
        txtAdminAdministradorDireccion.setText("");
        txtAdminAdministradorCodPostal.setText("");
        txtAdminAdministradorTelefono.setText("");
        txtAdminAdministradorCorreo.setText("");
        
    }//GEN-LAST:event_buttonAction1ActionPerformed

    private void tablaTrabCobroMensualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTrabCobroMensualMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaTrabCobroMensualMouseClicked

    private void tablaTrabCobroMensualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaTrabCobroMensualKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaTrabCobroMensualKeyReleased

    private void buttonAction4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction4ActionPerformed
        //boton admin admin modificar
        if(!txtAdminAdministradorDni.getText().isEmpty() && !txtAdminAdministradorContraseña.getText().isEmpty() && !txtAdminAdministradorContraseña.getText().isEmpty() && !txtAdminAdministradorNombre.getText().isEmpty() && !txtAdminAdministradorApellidos.getText().isEmpty() && !txtAdminAdministradorDireccion.getText().isEmpty() && !txtAdminAdministradorTelefono.getText().isEmpty() && !txtAdminAdministradorCorreo.getText().isEmpty() && !txtAdminAdministradorCodPostal.getText().isEmpty()){
        if(c.comprobacion(txtAdminAdministradorTelefono.getText()) && c.comprobacion(txtAdminAdministradorCodPostal.getText())){
        f.modificarEmpleado(txtAdminAdministradorDni.getText(),txtAdminAdministradorContraseña.getText(), txtAdminAdministradorNombre.getText(), txtAdminAdministradorApellidos.getText(), txtAdminAdministradorDireccion.getText(), Integer.parseInt(txtAdminAdministradorTelefono.getText()),txtAdminAdministradorCorreo.getText(), Integer.parseInt(txtAdminAdministradorCodPostal.getText()));
        this.listaAdmin.setModel(f.listAdmin());
       
        txtAdminAdministradorDni.setText("");
        txtAdminAdministradorNombre.setText("");
        txtAdminAdministradorContraseña.setText("");
        txtAdminAdministradorApellidos.setText("");
        txtAdminAdministradorDireccion.setText("");
        txtAdminAdministradorCodPostal.setText("");
        txtAdminAdministradorTelefono.setText("");
        txtAdminAdministradorCorreo.setText("");
        
            JOptionPane.showMessageDialog(null, "Administrador modificado");
        }else{
            JOptionPane.showMessageDialog(null, "Solo caracteres numericos en Teléfono");
        }
        }else{
            JOptionPane.showMessageDialog(null, "Rellena todo los campos");
        }
    }//GEN-LAST:event_buttonAction4ActionPerformed

    private void buttonAction5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction5ActionPerformed
        //Boton admin admin eliminar
        if (listaAdmin.getSelectedIndex() > -1) {
            if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar?")==0){
            
        f.eliminarEmpleado(txtAdminAdministradorDni.getText());
        this.listaAdmin.setModel(f.listAdmin());
        
        txtAdminAdministradorDni.setText("");
        txtAdminAdministradorNombre.setText("");
        txtAdminAdministradorContraseña.setText("");
        txtAdminAdministradorApellidos.setText("");
        txtAdminAdministradorDireccion.setText("");
        txtAdminAdministradorCodPostal.setText("");
        txtAdminAdministradorTelefono.setText("");
        txtAdminAdministradorCorreo.setText("");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona algun administrador de la lista");
        }
    }//GEN-LAST:event_buttonAction5ActionPerformed

    private void buttonAction3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction3ActionPerformed
        
        if(!txtAdminAdministradorDni.getText().isEmpty() && !txtAdminAdministradorContraseña.getText().isEmpty() && !txtAdminAdministradorContraseña.getText().isEmpty() && !txtAdminAdministradorNombre.getText().isEmpty() && !txtAdminAdministradorApellidos.getText().isEmpty() && !txtAdminAdministradorDireccion.getText().isEmpty() && !txtAdminAdministradorTelefono.getText().isEmpty() && !txtAdminAdministradorCorreo.getText().isEmpty() && !txtAdminAdministradorCodPostal.getText().isEmpty()){
        if(c.comprobacion(txtAdminAdministradorTelefono.getText())  && c.comprobacion(txtAdminAdministradorCodPostal.getText())){
        
            f.añadirAdministrador(txtAdminAdministradorDni.getText(),txtAdminAdministradorContraseña.getText(), 1, txtAdminAdministradorNombre.getText(), txtAdminAdministradorApellidos.getText(), txtAdminAdministradorDireccion.getText(), Integer.parseInt(txtAdminAdministradorTelefono.getText()),txtAdminAdministradorCorreo.getText(), Integer.parseInt(txtAdminAdministradorCodPostal.getText()));
            this.listaAdmin.setModel(f.listAdmin());

            txtAdminAdministradorDni.setText("");
            txtAdminAdministradorNombre.setText("");
            txtAdminAdministradorContraseña.setText("");
            txtAdminAdministradorApellidos.setText("");
            txtAdminAdministradorDireccion.setText("");
            txtAdminAdministradorCodPostal.setText("");
            txtAdminAdministradorTelefono.setText("");
            txtAdminAdministradorCorreo.setText("");
            
            JOptionPane.showMessageDialog(null, "Administrador guardado");
        }else{
            JOptionPane.showMessageDialog(null, "Solo caracteres numericos en Teléfono");
        }
        }else{
            JOptionPane.showMessageDialog(null, "Rellena todo los campos");
        }
    }//GEN-LAST:event_buttonAction3ActionPerformed

    private void buttonAction6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction6ActionPerformed
        if(!txtAdminClienteDni.getText().isEmpty() && !txtAdminClienteNombre.getText().isEmpty() && !txtAdminClienteApellidos.getText().isEmpty() && !txtAdminClienteDireccion.getText().isEmpty() && !txtAdminClienteCP.getText().isEmpty() && !txtAdminClienteCiudad.getText().isEmpty() && !txtAdminClienteTelefono.getText().isEmpty() && !txtAdminClienteCorreo.getText().isEmpty()){
        if(c.comprobacion(txtAdminClienteCP.getText())  && c.comprobacion(txtAdminClienteTelefono.getText())){
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(this.fecha.getDate());
        f.modificarCliente(txtAdminClienteDni.getText(), txtAdminClienteNombre.getText(), txtAdminClienteApellidos.getText(), fecha, txtAdminClienteDireccion.getText(), Integer.parseInt(txtAdminClienteCP.getText()), txtAdminClienteCiudad.getText(), Integer.parseInt(txtAdminClienteTelefono.getText()), txtAdminClienteCorreo.getText());
        this.tablaClientes.setModel(f.listarClientes());
        
        txtAdminClienteDni.setText("");
        txtAdminClienteNombre.setText("");
        txtAdminClienteApellidos.setText("");
        txtAdminClienteDireccion.setText("");
        txtAdminClienteCP.setText("");
        txtAdminClienteTelefono.setText("");
        txtAdminClienteCorreo.setText("");
        txtAdminClienteCiudad.setText("");
        
            JOptionPane.showMessageDialog(null, "Cliente modificado");
        }else{
            JOptionPane.showMessageDialog(null, "Solo caracteres numericos en Telefono y codigo postal");
        }
        }else{
        JOptionPane.showMessageDialog(null, "Rellene todo los campos");
    }
    }//GEN-LAST:event_buttonAction6ActionPerformed

    private void buttonAction14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction14ActionPerformed
        //boton admin trabajador guardar
        if(!txtAdminTrabajDni.getText().isEmpty() && !txtAdminTrabajContrsña.getText().isEmpty() && !txtAdminTrabajNombre.getText().isEmpty() && !txtAdminTrabajApellid.getText().isEmpty() && !txtAdminTrabajDireccion.getText().isEmpty() && !txtAdminTrabajTeled.getText().isEmpty() && !txtAdminTrabajCorreo.getText().isEmpty() && !txtAdminTrabajCP.getText().isEmpty()){
        if(c.comprobacion(txtAdminTrabajCP.getText())  && c.comprobacion(txtAdminTrabajTeled.getText())){
        f.añadirTrabajador(txtAdminTrabajDni.getText(), txtAdminTrabajContrsña.getText(), 0, txtAdminTrabajNombre.getText(), txtAdminTrabajApellid.getText(), txtAdminTrabajDireccion.getText(), Integer.parseInt(txtAdminTrabajTeled.getText()), txtAdminTrabajCorreo.getText(),Integer.parseInt(txtAdminTrabajCP.getText()));
        this.listaTrabajadores.setModel(f.listTrabajador());
        
        txtAdminTrabajDni.setText("");
        txtAdminTrabajNombre.setText("");
        txtAdminTrabajApellid.setText("");
        txtAdminTrabajDireccion.setText("");
        txtAdminTrabajCP.setText("");
        txtAdminTrabajTeled.setText("");
        txtAdminTrabajCorreo.setText("");
        txtAdminTrabajContrsña.setText("");
        
            JOptionPane.showMessageDialog(null, "Trabajador guardado");
        }else{
            JOptionPane.showMessageDialog(null, "Solo caracteres numericos en Telefono y codigo postal");
        }
        }else{
            JOptionPane.showMessageDialog(null, "Rellena todo los campos");
        }
    }//GEN-LAST:event_buttonAction14ActionPerformed

    private void listaTrabajadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTrabajadoresMouseClicked
         String trabajador = listaTrabajadores.getSelectedValue();
        List<String> l = Arrays.asList(trabajador.split(" "));
        
        String idTra = l.get(0);
        
        Trabajador t= f.datosTrabajador(idTra); 
        
        System.out.println("La id del admin es: " + idTra);
        
        this.txtAdminTrabajDni.setText(t.getIdEmpleado());
        this.txtAdminTrabajNombre.setText(t.getNombre());
        this.txtAdminTrabajDireccion.setText(t.getDireccion());
        this.txtAdminTrabajTeled.setText(String.valueOf(t.getTelefono()));
        
        this.txtAdminTrabajContrsña.setText(t.getClave());
        this.txtAdminTrabajApellid.setText(t.getApellidos());
        this.txtAdminTrabajCP.setText(String.valueOf(t.getCodPostal()));
        this.txtAdminTrabajCorreo.setText(t.getCorreo());       
    }//GEN-LAST:event_listaTrabajadoresMouseClicked

    private void buttonAction12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction12ActionPerformed
        //boton admin trabajador eliminar
        if (listaTrabajadores.getSelectedIndex() > -1) {
            if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar?")==0){
        f.eliminarTrabajador(txtAdminTrabajDni.getText());
        this.listaTrabajadores.setModel(f.listTrabajador());
        
        txtAdminTrabajDni.setText("");
        txtAdminTrabajNombre.setText("");
        txtAdminTrabajApellid.setText("");
        txtAdminTrabajDireccion.setText("");
        txtAdminTrabajCP.setText("");
        txtAdminTrabajTeled.setText("");
        txtAdminTrabajCorreo.setText("");
        txtAdminTrabajContrsña.setText("");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_buttonAction12ActionPerformed

    private void buttonAction11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction11ActionPerformed
        //boton admin trabajador modificar
        if(!txtAdminTrabajDni.getText().isEmpty() && !txtAdminTrabajContrsña.getText().isEmpty() && txtAdminTrabajContrsña.getText().isEmpty() && !txtAdminTrabajNombre.getText().isEmpty() && !txtAdminTrabajApellid.getText().isEmpty() && !txtAdminTrabajDireccion.getText().isEmpty() && !txtAdminTrabajTeled.getText().isEmpty() && !txtAdminTrabajCorreo.getText().isEmpty() && !txtAdminTrabajCP.getText().isEmpty()){
        if(c.comprobacion(txtAdminTrabajCP.getText())  && c.comprobacion(txtAdminTrabajTeled.getText())){
        f.modificarTrabajador(txtAdminTrabajDni.getText(), txtAdminTrabajContrsña.getText(), txtAdminTrabajNombre.getText(), txtAdminTrabajApellid.getText(), txtAdminTrabajDireccion.getText(), Integer.parseInt(txtAdminTrabajTeled.getText()), txtAdminTrabajCorreo.getText(),Integer.parseInt(txtAdminTrabajCP.getText()));
        this.listaTrabajadores.setModel(f.listTrabajador());
        
        txtAdminTrabajDni.setText("");
        txtAdminTrabajNombre.setText("");
        txtAdminTrabajApellid.setText("");
        txtAdminTrabajDireccion.setText("");
        txtAdminTrabajCP.setText("");
        txtAdminTrabajTeled.setText("");
        txtAdminTrabajCorreo.setText("");
        txtAdminTrabajContrsña.setText("");
        
            JOptionPane.showMessageDialog(null, "Trabajador modificado");
        }else{
            JOptionPane.showMessageDialog(null, "Solo caracteres numericos en Telefono y codigo postal");
        }
        }else{
            JOptionPane.showMessageDialog(null, "Rellena todo los campos");
        }

    }//GEN-LAST:event_buttonAction11ActionPerformed

    private void buttonAction15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction15ActionPerformed
        if(!txtAdminClasesNombre.getText().isEmpty() && !txtAdminClasesPrecio.getText().isEmpty()){
        if(c.comprobacion(txtAdminClasesPrecio.getText())){
        String idClase=(String) tablaClases.getValueAt(tablaClases.getSelectedRow(), 0);
        
        String dniMonitor = (String)this.cmbAdminClases.getSelectedItem();
        String dniM = dniMonitor.substring(0, 9);
        System.out.println("El dni del monitor es: " + dniM);
        f.modificarClase(Integer.parseInt(idClase), txtAdminClasesNombre.getText(),  Double.parseDouble(txtAdminClasesPrecio.getText()), dniM);
        this.tablaClases.setModel(f.listarClases());
        this.cmbAdminMaquinas.setModel(f.comboClases());
        
        txtAdminClasesNombre.setText("");
        txtAdminClasesPrecio.setText("");
        }else{
            JOptionPane.showMessageDialog(null, "Solo caracteres numericos para el precio");
        }
        }else{
            JOptionPane.showMessageDialog(null, "Rellena todos los campos");
        }
    }//GEN-LAST:event_buttonAction15ActionPerformed

    private void buttonAction31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction31ActionPerformed
        if(!txtAdminMaquiNombre.getText().isEmpty()){
        String idClaseProv = cmbAdminMaquinas.getSelectedItem().toString();
        List<String> l = Arrays.asList(idClaseProv.split(" "));
        int idClase = Integer.parseInt(l.get(0));
        System.out.println("la Id es: " + idClase);
        String idMaquina=(String) tablaMaquinas.getValueAt(tablaMaquinas.getSelectedRow(), 0);
        f.modificarMaquina(Integer.parseInt(idMaquina), idClase , txtAdminMaquiNombre.getText());
        this.tablaMaquinas.setModel(f.listarMaquinas());
        
        txtAdminMaquiNombre.setText("");
        }else{
            JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        }
    }//GEN-LAST:event_buttonAction31ActionPerformed

    private void buttonAction13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction13ActionPerformed
        //boton admin trabajador nuevo
        txtAdminTrabajDni.setText("");
        txtAdminTrabajNombre.setText("");
        txtAdminTrabajApellid.setText("");
        txtAdminTrabajDireccion.setText("");
        txtAdminTrabajCP.setText("");
        txtAdminTrabajTeled.setText("");
        txtAdminTrabajCorreo.setText("");
        txtAdminTrabajContrsña.setText("");
    }//GEN-LAST:event_buttonAction13ActionPerformed

    private void buttonAction17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction17ActionPerformed
        //boton admin clase nuevo
        txtAdminClasesNombre.setText("");
        txtAdminClasesPrecio.setText("");
    }//GEN-LAST:event_buttonAction17ActionPerformed

    private void buttonAction21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction21ActionPerformed
        f.modificarTrabajador(txtAdminAdministradorNombre17.getText(), txtAdminAdministradorApellidos11.getText(), txtAdminAdministradorNombre16.getText(), txtAdminAdministradorApellidos10.getText(), txtAdminAdministradorDireccion6.getText(), Integer.parseInt(txtAdminAdministradorTelefono6.getText()), txtAdminAdministradorCorreo6.getText(), Integer.parseInt(txtAdminAdministradorCodPostal6.getText()));
    }//GEN-LAST:event_buttonAction21ActionPerformed

    private void txtAdminAdministradorNombre8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdminAdministradorNombre8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdminAdministradorNombre8ActionPerformed

    private void buttonAction28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction28ActionPerformed
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(this.fecha1.getDate());
        f.modificarCliente(txtAdminAdministradorNombre9.getText(), txtAdminAdministradorApellidos8.getText(), txtAdminAdministradorNombre8.getText(), fecha, txtAdminAdministradorDireccion4.getText(), Integer.parseInt(txtAdminAdministradorCodPostal4.getText()), txtAdminAdministradorApellidos7.getText(), Integer.parseInt(txtAdminAdministradorTelefono4.getText()), txtAdminAdministradorCorreo4.getText());
        this.tablaClientes2.setModel(f.listarClientes());
    }//GEN-LAST:event_buttonAction28ActionPerformed

    private void buttonAction39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction39ActionPerformed
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(this.fecha1.getDate());
       // f.añadirCliente(txtAdminAdministradorNombre11.getText(), passwordField2.getText(), txtAdminAdministradorNombre10.getText(), fecha, txtAdminAdministradorDireccion5.getText(), Integer.parseInt(txtAdminAdministradorCodPostal5.getText()), txtAdminAdministradorTelefono7.getText(), Integer.parseInt(txtAdminAdministradorTelefono5.getText()), txtAdminAdministradorCorreo5.getText());
    }//GEN-LAST:event_buttonAction39ActionPerformed

    private void buttonAeroRight2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAeroRight2ActionPerformed
        // Trabajador matricula añadir a tarifa
        if(listTrabMatricClases.getSelectedIndex() != -1){
            DefaultListModel mt = (DefaultListModel) listTrabMatricTarifa.getModel();
            mt.addElement(listTrabMatricClases.getSelectedValue());
            
            DefaultListModel mc = (DefaultListModel) listTrabMatricClases.getModel();
            mc.remove(listTrabMatricClases.getSelectedIndex());
            
            float precio = f.precioMatricula(mt);
            
            lblTrabTotalMatricula.setText(String.valueOf(precio));
            
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona alguna clase");
        }
    }//GEN-LAST:event_buttonAeroRight2ActionPerformed

    private void buttonAeroLeft2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAeroLeft2ActionPerformed
        // Trabajador matricula añadir a clase
        if(listTrabMatricTarifa.getSelectedIndex() != -1){
            DefaultListModel mt = (DefaultListModel) listTrabMatricClases.getModel();
            mt.addElement(listTrabMatricTarifa.getSelectedValue());
            
            DefaultListModel mc = (DefaultListModel) listTrabMatricTarifa.getModel();
            mc.remove(listTrabMatricTarifa.getSelectedIndex());
            
            float precio = f.precioMatricula(mc);
            
            lblTrabTotalMatricula.setText(String.valueOf(precio));
            
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona alguna clase");
        }
    }//GEN-LAST:event_buttonAeroLeft2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.label.LabelTask Cobro;
    private org.edisoncor.gui.button.ButtonAction Guardar;
    private org.edisoncor.gui.panel.PanelAvatarChooser avatarAdmin;
    private org.edisoncor.gui.panel.PanelAvatarChooser avatarTrab;
    private org.edisoncor.gui.button.ButtonIpod btnAdminIpod;
    private org.edisoncor.gui.button.ButtonCircle btnAdminRetroceso;
    private org.edisoncor.gui.button.ButtonAction btnLoguinEntrar;
    private org.edisoncor.gui.button.ButtonAction btnTrabClienteClases;
    private org.edisoncor.gui.button.ButtonAction btnTrabClienteMatricula;
    private org.edisoncor.gui.button.ButtonIpod btnTrabIpod;
    private org.edisoncor.gui.button.ButtonCircle btnTrabajadorRetroceso;
    private org.edisoncor.gui.button.ButtonAction buttonAction1;
    private org.edisoncor.gui.button.ButtonAction buttonAction10;
    private org.edisoncor.gui.button.ButtonAction buttonAction11;
    private org.edisoncor.gui.button.ButtonAction buttonAction12;
    private org.edisoncor.gui.button.ButtonAction buttonAction13;
    private org.edisoncor.gui.button.ButtonAction buttonAction14;
    private org.edisoncor.gui.button.ButtonAction buttonAction15;
    private org.edisoncor.gui.button.ButtonAction buttonAction16;
    private org.edisoncor.gui.button.ButtonAction buttonAction17;
    private org.edisoncor.gui.button.ButtonAction buttonAction18;
    private org.edisoncor.gui.button.ButtonAction buttonAction19;
    private org.edisoncor.gui.button.ButtonAction buttonAction20;
    private org.edisoncor.gui.button.ButtonAction buttonAction21;
    private org.edisoncor.gui.button.ButtonAction buttonAction22;
    private org.edisoncor.gui.button.ButtonAction buttonAction25;
    private org.edisoncor.gui.button.ButtonAction buttonAction28;
    private org.edisoncor.gui.button.ButtonAction buttonAction29;
    private org.edisoncor.gui.button.ButtonAction buttonAction3;
    private org.edisoncor.gui.button.ButtonAction buttonAction31;
    private org.edisoncor.gui.button.ButtonAction buttonAction32;
    private org.edisoncor.gui.button.ButtonAction buttonAction33;
    private org.edisoncor.gui.button.ButtonAction buttonAction34;
    private org.edisoncor.gui.button.ButtonAction buttonAction39;
    private org.edisoncor.gui.button.ButtonAction buttonAction4;
    private org.edisoncor.gui.button.ButtonAction buttonAction46;
    private org.edisoncor.gui.button.ButtonAction buttonAction48;
    private org.edisoncor.gui.button.ButtonAction buttonAction5;
    private org.edisoncor.gui.button.ButtonAction buttonAction6;
    private org.edisoncor.gui.button.ButtonAction buttonAction7;
    private org.edisoncor.gui.button.ButtonAction buttonAction9;
    private org.edisoncor.gui.button.ButtonAeroLeft buttonAeroLeft1;
    private org.edisoncor.gui.button.ButtonAeroLeft buttonAeroLeft2;
    private org.edisoncor.gui.button.ButtonAeroRight buttonAeroRight1;
    private org.edisoncor.gui.button.ButtonAeroRight buttonAeroRight2;
    private org.edisoncor.gui.button.ButtonIcon buttonIcon1;
    private org.edisoncor.gui.comboBox.ComboBoxRound cmbAdminClases;
    private org.edisoncor.gui.comboBox.ComboBoxRound cmbAdminMaquinas;
    private javax.swing.JDialog diaAdminClienteTarifa;
    private javax.swing.JDialog diaAdminMaquinaMonitor;
    private org.edisoncor.gui.label.LabelTask dniTrabajador;
    private com.toedter.calendar.JDateChooser fecha;
    private com.toedter.calendar.JDateChooser fecha1;
    private com.toedter.calendar.JDateChooser fecha3;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private org.edisoncor.gui.label.LabelMetric labelMetric1;
    private org.edisoncor.gui.label.LabelMetric labelMetric10;
    private org.edisoncor.gui.label.LabelMetric labelMetric11;
    private org.edisoncor.gui.label.LabelMetric labelMetric12;
    private org.edisoncor.gui.label.LabelMetric labelMetric13;
    private org.edisoncor.gui.label.LabelMetric labelMetric14;
    private org.edisoncor.gui.label.LabelMetric labelMetric15;
    private org.edisoncor.gui.label.LabelMetric labelMetric16;
    private org.edisoncor.gui.label.LabelMetric labelMetric17;
    private org.edisoncor.gui.label.LabelMetric labelMetric18;
    private org.edisoncor.gui.label.LabelMetric labelMetric19;
    private org.edisoncor.gui.label.LabelMetric labelMetric2;
    private org.edisoncor.gui.label.LabelMetric labelMetric20;
    private org.edisoncor.gui.label.LabelMetric labelMetric21;
    private org.edisoncor.gui.label.LabelMetric labelMetric22;
    private org.edisoncor.gui.label.LabelMetric labelMetric23;
    private org.edisoncor.gui.label.LabelMetric labelMetric24;
    private org.edisoncor.gui.label.LabelMetric labelMetric25;
    private org.edisoncor.gui.label.LabelMetric labelMetric26;
    private org.edisoncor.gui.label.LabelMetric labelMetric27;
    private org.edisoncor.gui.label.LabelMetric labelMetric28;
    private org.edisoncor.gui.label.LabelMetric labelMetric29;
    private org.edisoncor.gui.label.LabelMetric labelMetric3;
    private org.edisoncor.gui.label.LabelMetric labelMetric30;
    private org.edisoncor.gui.label.LabelMetric labelMetric31;
    private org.edisoncor.gui.label.LabelMetric labelMetric32;
    private org.edisoncor.gui.label.LabelMetric labelMetric33;
    private org.edisoncor.gui.label.LabelMetric labelMetric34;
    private org.edisoncor.gui.label.LabelMetric labelMetric35;
    private org.edisoncor.gui.label.LabelMetric labelMetric36;
    private org.edisoncor.gui.label.LabelMetric labelMetric37;
    private org.edisoncor.gui.label.LabelMetric labelMetric38;
    private org.edisoncor.gui.label.LabelMetric labelMetric39;
    private org.edisoncor.gui.label.LabelMetric labelMetric40;
    private org.edisoncor.gui.label.LabelMetric labelMetric41;
    private org.edisoncor.gui.label.LabelMetric labelMetric42;
    private org.edisoncor.gui.label.LabelMetric labelMetric43;
    private org.edisoncor.gui.label.LabelMetric labelMetric44;
    private org.edisoncor.gui.label.LabelMetric labelMetric45;
    private org.edisoncor.gui.label.LabelMetric labelMetric46;
    private org.edisoncor.gui.label.LabelMetric labelMetric47;
    private org.edisoncor.gui.label.LabelMetric labelMetric48;
    private org.edisoncor.gui.label.LabelMetric labelMetric49;
    private org.edisoncor.gui.label.LabelMetric labelMetric5;
    private org.edisoncor.gui.label.LabelMetric labelMetric50;
    private org.edisoncor.gui.label.LabelMetric labelMetric51;
    private org.edisoncor.gui.label.LabelMetric labelMetric52;
    private org.edisoncor.gui.label.LabelMetric labelMetric53;
    private org.edisoncor.gui.label.LabelMetric labelMetric59;
    private org.edisoncor.gui.label.LabelMetric labelMetric6;
    private org.edisoncor.gui.label.LabelMetric labelMetric60;
    private org.edisoncor.gui.label.LabelMetric labelMetric61;
    private org.edisoncor.gui.label.LabelMetric labelMetric62;
    private org.edisoncor.gui.label.LabelMetric labelMetric63;
    private org.edisoncor.gui.label.LabelMetric labelMetric64;
    private org.edisoncor.gui.label.LabelMetric labelMetric65;
    private org.edisoncor.gui.label.LabelMetric labelMetric66;
    private org.edisoncor.gui.label.LabelMetric labelMetric67;
    private org.edisoncor.gui.label.LabelMetric labelMetric68;
    private org.edisoncor.gui.label.LabelMetric labelMetric69;
    private org.edisoncor.gui.label.LabelMetric labelMetric7;
    private org.edisoncor.gui.label.LabelMetric labelMetric70;
    private org.edisoncor.gui.label.LabelMetric labelMetric71;
    private org.edisoncor.gui.label.LabelMetric labelMetric72;
    private org.edisoncor.gui.label.LabelMetric labelMetric73;
    private org.edisoncor.gui.label.LabelMetric labelMetric74;
    private org.edisoncor.gui.label.LabelMetric labelMetric75;
    private org.edisoncor.gui.label.LabelMetric labelMetric76;
    private org.edisoncor.gui.label.LabelMetric labelMetric77;
    private org.edisoncor.gui.label.LabelMetric labelMetric78;
    private org.edisoncor.gui.label.LabelMetric labelMetric79;
    private org.edisoncor.gui.label.LabelMetric labelMetric8;
    private org.edisoncor.gui.label.LabelMetric labelMetric80;
    private org.edisoncor.gui.label.LabelMetric labelMetric83;
    private org.edisoncor.gui.label.LabelMetric labelMetric84;
    private org.edisoncor.gui.label.LabelMetric labelMetric87;
    private org.edisoncor.gui.label.LabelMetric labelMetric88;
    private org.edisoncor.gui.label.LabelMetric labelMetric89;
    private org.edisoncor.gui.label.LabelMetric labelMetric9;
    private org.edisoncor.gui.label.LabelMetric labelMetric90;
    private org.edisoncor.gui.label.LabelMetric labelMetric91;
    private org.edisoncor.gui.label.LabelMetric labelMetric92;
    private org.edisoncor.gui.label.LabelMetric labelMetric93;
    private org.edisoncor.gui.label.LabelMetric labelMetric94;
    private org.edisoncor.gui.label.LabelMetric labelMetric95;
    private org.edisoncor.gui.label.LabelMetric labelMetric96;
    private org.edisoncor.gui.label.LabelMetric labelMetric97;
    private org.edisoncor.gui.label.LabelTask labelTask1;
    private org.edisoncor.gui.label.LabelTask labelTask2;
    private org.edisoncor.gui.label.LabelTask labelTask3;
    private org.edisoncor.gui.label.LabelTask labelTask4;
    private org.edisoncor.gui.label.LabelTask labelTask6;
    private org.edisoncor.gui.label.LabelTask labelTask7;
    private org.edisoncor.gui.label.LabelMetric lblAdministrador;
    private javax.swing.JLabel lblHoraAdmin;
    private javax.swing.JLabel lblHoraTrabajador;
    private org.edisoncor.gui.label.LabelMetric lblTrabTotalMatricula;
    private org.edisoncor.gui.label.LabelMetric lblTrabajador;
    private javax.swing.JList<String> listTrabMatricClases;
    private javax.swing.JList<String> listTrabMatricTarifa;
    private javax.swing.JList<String> listaAdmin;
    private javax.swing.JList<String> listaClientes;
    private javax.swing.JList<String> listaClientes1;
    private javax.swing.JList<String> listaTrabajadores;
    private javax.swing.JPanel panelAdmin;
    private javax.swing.JPanel panelAdminAdmin;
    private javax.swing.JPanel panelAdminCliente;
    private javax.swing.JPanel panelAdminMaquina;
    private javax.swing.JPanel panelAdminTrabajador;
    private javax.swing.JPanel panelLoguin;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelTrab;
    private javax.swing.JPanel panelTrabCliente;
    private javax.swing.JPanel panelTrabCobro;
    private javax.swing.JPanel panelTrabMatricula;
    private javax.swing.JPanel panelTrabPerfil;
    private org.edisoncor.gui.passwordField.PasswordField passwordField2;
    private org.edisoncor.gui.tabbedPane.TabbedPaneTask tabbedPaneTask1;
    private javax.swing.JTable tablaClases;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTable tablaClientes2;
    private javax.swing.JTable tablaMaquinas;
    private javax.swing.JTable tablaTrabCobroCliente;
    private javax.swing.JTable tablaTrabCobroMensual;
    private javax.swing.JTable tbMonitores;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorApellidos;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorApellidos10;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorApellidos11;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorApellidos7;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorApellidos8;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorCodPostal;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorCodPostal4;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorCodPostal5;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorCodPostal6;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorContraseña;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorCorreo;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorCorreo4;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorCorreo5;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorCorreo6;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorCorreo7;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorDireccion;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorDireccion4;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorDireccion5;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorDireccion6;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorDni;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorNombre;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorNombre10;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorNombre11;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorNombre16;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorNombre17;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorNombre8;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorNombre9;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorTelefono;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorTelefono4;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorTelefono5;
    private org.edisoncor.gui.textField.TextField txtAdminAdministradorTelefono6;
    private org.edisoncor.gui.textField.TextField txtAdminClasesNombre;
    private org.edisoncor.gui.textField.TextField txtAdminClasesPrecio;
    private org.edisoncor.gui.textField.TextField txtAdminClienteApellidos;
    private org.edisoncor.gui.textField.TextField txtAdminClienteCP;
    private org.edisoncor.gui.textField.TextField txtAdminClienteCiudad;
    private org.edisoncor.gui.textField.TextField txtAdminClienteCorreo;
    private org.edisoncor.gui.textField.TextField txtAdminClienteDireccion;
    private org.edisoncor.gui.textField.TextField txtAdminClienteDni;
    private org.edisoncor.gui.textField.TextField txtAdminClienteNombre;
    private org.edisoncor.gui.textField.TextField txtAdminClienteTelefono;
    private org.edisoncor.gui.textField.TextField txtAdminMaquiNombre;
    private org.edisoncor.gui.textField.TextField txtAdminTrabajApellid;
    private org.edisoncor.gui.textField.TextField txtAdminTrabajCP;
    private org.edisoncor.gui.passwordField.PasswordField txtAdminTrabajContrsña;
    private org.edisoncor.gui.textField.TextField txtAdminTrabajCorreo;
    private org.edisoncor.gui.textField.TextField txtAdminTrabajDireccion;
    private org.edisoncor.gui.textField.TextField txtAdminTrabajDni;
    private org.edisoncor.gui.textField.TextField txtAdminTrabajNombre;
    private org.edisoncor.gui.textField.TextField txtAdminTrabajTeled;
    private org.edisoncor.gui.passwordField.PasswordFieldRound txtLoguinContraseña;
    private org.edisoncor.gui.textField.TextFieldRound txtLoguinUsuario;
    private org.edisoncor.gui.textField.TextField txtMonitorApellidos;
    private org.edisoncor.gui.textField.TextField txtMonitorCorreo;
    private org.edisoncor.gui.textField.TextField txtMonitorDni;
    private org.edisoncor.gui.textField.TextField txtMonitorNombre;
    private org.edisoncor.gui.textField.TextField txtMonitorTelefono;
    private org.edisoncor.gui.textField.TextFieldRound txtnombre;
    private org.edisoncor.gui.textField.TextFieldRound txtnombre1;
    // End of variables declaration//GEN-END:variables

    


    
}

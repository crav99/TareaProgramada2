/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaAdministracionPaquetes;

import DataStructures.InterfazColas;
import DataStructures.PriorityQueue;
import DataStructures.implementHeap;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 */
public class SAP extends javax.swing.JFrame {
    
    int contador;
    int VPC;
    int VNPC;
    int VSC;
    int range;
    InterfazColas perecederoQueue;
    InterfazColas noPerecederoQueue;
    InterfazColas securityQueue;
    DefaultTableModel colasTableModel;
    DefaultTableModel perecederoTableModel;
    DefaultTableModel noPerecederoTableModel;
    DefaultTableModel securityTableModel;
    DefaultTableModel totalPerecederoTableModel;
    DefaultTableModel totalNoPerecederoTableModel;
    DefaultTableModel totalSecurityTableModel;
    DefaultListModel clientesListModel;
    Security securityThread;
    Cliente[] perecederoArray;
    Cliente[] noPerecederoArray;

    /**
     * Creates new form SAP
     */
    public SAP(String typePerecedero, String typeNoPerecedero, String typeSecurity, int range, int tamañoP, int tamañoNP, int tamañoS) {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.colasTableModel = (DefaultTableModel) this.tableColas.getModel();
        this.tableColas.setAutoCreateRowSorter(true);
        this.perecederoTableModel = (DefaultTableModel) this.perecederoTable.getModel();
        this.perecederoTable.setAutoCreateRowSorter(true);
        this.noPerecederoTableModel = (DefaultTableModel) this.noPerecederoTable.getModel();
        this.noPerecederoTable.setAutoCreateRowSorter(true);
        this.securityTableModel = (DefaultTableModel) this.securityTable.getModel();
        this.securityTable.setAutoCreateRowSorter(true);
        this.totalNoPerecederoTableModel = (DefaultTableModel) this.tableTotalNoPerecedero.getModel();
        this.tableTotalNoPerecedero.setAutoCreateRowSorter(true);
        this.totalPerecederoTableModel = (DefaultTableModel) this.tableTotalPerecedero.getModel();
        this.tableTotalPerecedero.setAutoCreateRowSorter(true);
        this.totalSecurityTableModel = (DefaultTableModel) this.tableTotalSecurity.getModel();
        this.tableTotalSecurity.setAutoCreateRowSorter(true);
        this.clientesListModel = new DefaultListModel();
        this.clienteList.setModel(this.clientesListModel);
        this.range = range;
        this.perecederoArray = new Cliente[tamañoP];
        this.noPerecederoArray = new Cliente[tamañoNP];
        set(typePerecedero, typeNoPerecedero, typeSecurity, tamañoP, tamañoNP, tamañoS);
        this.securityThread = new Security(this.securityQueue, this.securityTable, this.tableTotalSecurity, this.tiempoSeguridad, this.range);
        this.securityThread.start();
    }
    
    public void set(String typePerecedero, String typeNoPerecedero, String typeSecurity, int tamañoP, int tamañoNP, int tamañoS) {
        if(typePerecedero.equals("Heap")) {
            this.perecederoQueue = (InterfazColas) new implementHeap();
        }else {
            this.perecederoQueue = (InterfazColas) new PriorityQueue();
        }
        if(typeNoPerecedero.equals("Heap")) {
            this.noPerecederoQueue = (InterfazColas) new implementHeap();
        }else {
            this.noPerecederoQueue = (InterfazColas) new PriorityQueue();
        }
        if(typeSecurity.equals("Heap")) {
            this.securityQueue = (InterfazColas) new implementHeap();
        }else {
            this.securityQueue = (InterfazColas) new PriorityQueue();
        }
        int amount = 1;
        while(tamañoP >= amount) {
            Object[] rowTemp =  {"Ventanilla "+amount, "Libre"};
            this.perecederoTableModel.addRow(rowTemp);
            Object[] rowAdd = {rowTemp[0], 0};
            this.totalPerecederoTableModel.addRow(rowAdd);
            amount ++;
        }
        amount = 1;
        while(tamañoNP >= amount) {
            Object[] rowTemp = {"Ventanilla "+amount, "Libre"};
            this.noPerecederoTableModel.addRow(rowTemp);
            Object[] rowAdd = {rowTemp[0], 0};
            this.totalNoPerecederoTableModel.addRow(rowAdd);
            amount ++;
        }
        amount = 1;
        while(tamañoS >= amount) {
            Object[] rowTemp = {"Ventanilla "+amount, "Libre"};
            this.securityTableModel.addRow(rowTemp);
            Object[] rowAdd = {rowTemp[0], 0};
            this.totalSecurityTableModel.addRow(rowAdd);
            amount ++;
        }
        this.VPC = tamañoP;
        this.VNPC = tamañoNP;
        this.VSC = tamañoS;
        Object[] statusPer= {"Perecedero", typePerecedero, "0", "Vacio"};
        Object[] statusNoPer= {"No Perecedero", typeNoPerecedero, "0", "Vacio"};
        Object[] statusSec= {"Seguridad", typeSecurity, "0", "Vacio"};
        this.colasTableModel.addRow(statusPer);
        this.colasTableModel.addRow(statusNoPer);
        this.colasTableModel.addRow(statusSec);
    }
    
    public void addToTypeCount(String type) {
        switch(type) {
            case "P-D":
                String value = String.valueOf(Integer.valueOf(this.totalPerDiscapacidad.getText()) + 1);
                this.totalPerDiscapacidad.setText(value);
                break;
            case "P-M":
                String value0 = String.valueOf(Integer.valueOf(this.totalPerAdultoMayor.getText()) + 1);
                this.totalPerAdultoMayor.setText(value0);
                break;
            case "P-E":
                String value1 = String.valueOf(Integer.valueOf(this.totalPerEmbarazada.getText()) + 1);
                this.totalPerEmbarazada.setText(value1);
                break;
            case "P-R":
                String value2 = String.valueOf(Integer.valueOf(this.totalPerRegular.getText()) + 1);
                this.totalPerRegular.setText(value2);
                break;
            case "NP-D":
                String value3 = String.valueOf(Integer.valueOf(this.totalNoPerDiscapacidad.getText()) + 1);
                this.totalNoPerDiscapacidad.setText(value3);
                break;
            case "NP-M":
                String value4 = String.valueOf(Integer.valueOf(this.totalNoPerAdultoMayor.getText()) + 1);
                this.totalNoPerAdultoMayor.setText(value4);
                break;
            case "NP-E":
                String value5 = String.valueOf(Integer.valueOf(this.totalNoPerEmbarazada.getText()) + 1);
                this.totalNoPerEmbarazada.setText(value5);
                break;
            case "NP-R":
                String value6 = String.valueOf(Integer.valueOf(this.totalNoPerRegular.getText()) + 1);
                this.totalNoPerRegular.setText(value6);
                break;
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

        jLabel38 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        autoservicio = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nameEntry = new javax.swing.JTextField();
        emailEntry = new javax.swing.JTextField();
        packageSelector = new javax.swing.JComboBox<>();
        userSelector = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        phoneEntry = new javax.swing.JTextField();
        administracion = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableColas = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        clienteList = new javax.swing.JList<>();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        windowPerecedero = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        windowNoPerecedero = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        windowSeguridad = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tiempoPerecedero = new javax.swing.JLabel();
        tiempoNoPerecedero = new javax.swing.JLabel();
        tiempoSeguridad = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        totalPerecedero = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        totalNoPerecedero = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        totalPerDiscapacidad = new javax.swing.JLabel();
        totalNoPerDiscapacidad = new javax.swing.JLabel();
        totalPerAdultoMayor = new javax.swing.JLabel();
        totalNoPerAdultoMayor = new javax.swing.JLabel();
        totalPerEmbarazada = new javax.swing.JLabel();
        totalNoPerEmbarazada = new javax.swing.JLabel();
        totalPerRegular = new javax.swing.JLabel();
        totalNoPerRegular = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableTotalPerecedero = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableTotalNoPerecedero = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableTotalSecurity = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        perecederoTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        noPerecederoTable = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        securityTable = new javax.swing.JTable();

        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Total atendidos por Ventanilla");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        autoservicio.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Autoservicio");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Correo:");

        emailEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailEntryActionPerformed(evt);
            }
        });

        packageSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Paquete -", "Perecedero", "No Perecedero" }));

        userSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Usuario -", "Discapacidad", "Adulto Mayor", "Mujer Embarazada", "Regular" }));

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Telefono:");

        phoneEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneEntryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout autoservicioLayout = new javax.swing.GroupLayout(autoservicio);
        autoservicio.setLayout(autoservicioLayout);
        autoservicioLayout.setHorizontalGroup(
            autoservicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(autoservicioLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(autoservicioLayout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(autoservicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(autoservicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(autoservicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(autoservicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameEntry, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(emailEntry)
                    .addComponent(phoneEntry))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(autoservicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(packageSelector, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userSelector, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        autoservicioLayout.setVerticalGroup(
            autoservicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(autoservicioLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(autoservicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(autoservicioLayout.createSequentialGroup()
                        .addGroup(autoservicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nameEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(autoservicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(emailEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(packageSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(userSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(autoservicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        administracion.setBackground(new java.awt.Color(0, 0, 0));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Administracion");
        jLabel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Estado de Colas");

        tableColas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ventanillas", "Cola", "Cantidad", "Siguiente"
            }
        ));
        jScrollPane4.setViewportView(tableColas);

        jScrollPane5.setViewportView(clienteList);

        jButton4.setText("Ver Clientes");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Administracion de Ventanillas");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Perecedero");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("No Perecedero");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Seguridad");

        jButton5.setText("Modificar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Estadisticas");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tiempo Promedio de Espera");

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Perecedero");

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("No Perecedero");

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Seguridad");

        tiempoPerecedero.setBackground(new java.awt.Color(255, 255, 255));
        tiempoPerecedero.setText("0");
        tiempoPerecedero.setOpaque(true);

        tiempoNoPerecedero.setBackground(new java.awt.Color(255, 255, 255));
        tiempoNoPerecedero.setText("0");
        tiempoNoPerecedero.setOpaque(true);

        tiempoSeguridad.setBackground(new java.awt.Color(255, 255, 255));
        tiempoSeguridad.setText("0");
        tiempoSeguridad.setOpaque(true);

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Total de Paquetes Atendidos por Tipo");

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Perecedero");

        totalPerecedero.setBackground(new java.awt.Color(255, 255, 255));
        totalPerecedero.setText("0");
        totalPerecedero.setOpaque(true);

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("No Perecedero");

        totalNoPerecedero.setBackground(new java.awt.Color(255, 255, 255));
        totalNoPerecedero.setText("0");
        totalNoPerecedero.setOpaque(true);

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Total de Clientes Atendidos por Tipos");

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Discapacidad");

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Adulto Mayor");

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Embarazada");

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Regular");

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Perec.");

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("No Per.");

        totalPerDiscapacidad.setBackground(new java.awt.Color(255, 255, 255));
        totalPerDiscapacidad.setText("0");
        totalPerDiscapacidad.setOpaque(true);

        totalNoPerDiscapacidad.setBackground(new java.awt.Color(255, 255, 255));
        totalNoPerDiscapacidad.setText("0");
        totalNoPerDiscapacidad.setOpaque(true);

        totalPerAdultoMayor.setBackground(new java.awt.Color(255, 255, 255));
        totalPerAdultoMayor.setText("0");
        totalPerAdultoMayor.setOpaque(true);

        totalNoPerAdultoMayor.setBackground(new java.awt.Color(255, 255, 255));
        totalNoPerAdultoMayor.setText("0");
        totalNoPerAdultoMayor.setOpaque(true);

        totalPerEmbarazada.setBackground(new java.awt.Color(255, 255, 255));
        totalPerEmbarazada.setText("0");
        totalPerEmbarazada.setOpaque(true);

        totalNoPerEmbarazada.setBackground(new java.awt.Color(255, 255, 255));
        totalNoPerEmbarazada.setText("0");
        totalNoPerEmbarazada.setOpaque(true);

        totalPerRegular.setBackground(new java.awt.Color(255, 255, 255));
        totalPerRegular.setText("0");
        totalPerRegular.setOpaque(true);

        totalNoPerRegular.setBackground(new java.awt.Color(255, 255, 255));
        totalNoPerRegular.setText("0");
        totalNoPerRegular.setOpaque(true);

        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Total atendidos Entregas Perec.");

        tableTotalPerecedero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ventanilla", "Cliente"
            }
        ));
        jScrollPane6.setViewportView(tableTotalPerecedero);

        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Total atendidos Entregas No Perec.");

        tableTotalNoPerecedero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ventanilla", "Cliente"
            }
        ));
        jScrollPane7.setViewportView(tableTotalNoPerecedero);

        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Total atendidos Seguridad");

        tableTotalSecurity.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ventanilla", "Cliente"
            }
        ));
        jScrollPane8.setViewportView(tableTotalSecurity);

        javax.swing.GroupLayout administracionLayout = new javax.swing.GroupLayout(administracion);
        administracion.setLayout(administracionLayout);
        administracionLayout.setHorizontalGroup(
            administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(administracionLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(administracionLayout.createSequentialGroup()
                .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, administracionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(administracionLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(administracionLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                            .addComponent(jLabel9)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(administracionLayout.createSequentialGroup()
                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(administracionLayout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(jButton4))
                            .addGroup(administracionLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(administracionLayout.createSequentialGroup()
                                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(windowPerecedero, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(windowNoPerecedero))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(windowSeguridad)))))
                            .addGroup(administracionLayout.createSequentialGroup()
                                .addGap(196, 196, 196)
                                .addComponent(jButton5))
                            .addGroup(administracionLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, administracionLayout.createSequentialGroup()
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalPerRegular, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalNoPerRegular, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(administracionLayout.createSequentialGroup()
                                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel15)
                                                .addComponent(jLabel19)
                                                .addGroup(administracionLayout.createSequentialGroup()
                                                    .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(tiempoPerecedero, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tiempoNoPerecedero, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tiempoSeguridad, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(administracionLayout.createSequentialGroup()
                                                    .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(totalPerecedero, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(totalNoPerecedero, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(jLabel14)
                                                .addComponent(jLabel22)
                                                .addGroup(administracionLayout.createSequentialGroup()
                                                    .addComponent(jLabel24)
                                                    .addGap(18, 18, 18)
                                                    .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(administracionLayout.createSequentialGroup()
                                                            .addComponent(totalPerDiscapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(totalNoPerDiscapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(administracionLayout.createSequentialGroup()
                                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jLabel28)))))
                                            .addGroup(administracionLayout.createSequentialGroup()
                                                .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(administracionLayout.createSequentialGroup()
                                                        .addComponent(jLabel25)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(totalPerAdultoMayor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(totalNoPerAdultoMayor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(administracionLayout.createSequentialGroup()
                                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(totalPerEmbarazada, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(totalNoPerEmbarazada, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(28, 28, 28)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel37)
                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel39)
                                            .addComponent(jLabel40)
                                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        administracionLayout.setVerticalGroup(
            administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(administracionLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(windowPerecedero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(windowNoPerecedero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(windowSeguridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addGap(4, 4, 4)
                .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(administracionLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(tiempoPerecedero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(tiempoNoPerecedero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(tiempoSeguridad)))
                    .addGroup(administracionLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39)))
                .addGap(4, 4, 4)
                .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(administracionLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(totalPerecedero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(totalNoPerecedero))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(totalPerDiscapacidad)
                            .addComponent(totalNoPerDiscapacidad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(totalPerAdultoMayor)
                            .addComponent(totalNoPerAdultoMayor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(totalPerEmbarazada)
                            .addComponent(totalNoPerEmbarazada))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(administracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(totalPerRegular)
                            .addComponent(totalNoPerRegular)))
                    .addGroup(administracionLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Entregas");
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        perecederoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ventanilla", "Cliente"
            }
        ));
        perecederoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perecederoTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(perecederoTable);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Perecedero");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("No Perecedero");

        noPerecederoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ventanilla", "Cliente"
            }
        ));
        noPerecederoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noPerecederoTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(noPerecederoTable);

        jButton2.setText("Atender");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Atender");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(84, 84, 84))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(57, 57, 57)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(115, 115, 115))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Seguridad");
        jLabel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        securityTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ventanilla", "Cliente", "Tiempo"
            }
        ));
        jScrollPane3.setViewportView(securityTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 469, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(autoservicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(administracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(autoservicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(administracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailEntryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailEntryActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(this.nameEntry.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese su nombre", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(this.emailEntry.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese su correo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(this.phoneEntry.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese su numero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(this.userSelector.getSelectedIndex() == -1 || this.userSelector.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione el tipo de usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(this.packageSelector.getSelectedIndex() == -1 || this.packageSelector.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione el tipo de paquete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Integer.valueOf(this.phoneEntry.getText());
        }catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente su numero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String tiquete;
        String user = (String)this.userSelector.getSelectedItem();
        String type = (String)this.packageSelector.getSelectedItem();
        int serial;
        if(type.equals("Perecedero")) {
            tiquete = "P-";
            serial = 20;
            String value = String.valueOf(Integer.valueOf(this.totalPerecedero.getText()) + 1);
            this.totalPerecedero.setText(value);
        }else {
            tiquete = "NP-";
            serial = 10;
            String value = String.valueOf(Integer.valueOf(this.totalNoPerecedero.getText()) + 1);
            this.totalNoPerecedero.setText(value);
        }
        switch (user) {
            case "Discapacidad":
                tiquete += "D-";
                serial += 4;
                break;
            case "Adulto Mayor":
                tiquete += "M-";
                serial += 3;
                break;
            case "Mujer Embarazada":
                tiquete += "E-";
                serial += 2;
                break;
            case "Regular":
                tiquete += "R-";
                serial += 1;
                break;
        }
        tiquete += this.contador;
        if(this.contador < 99) {
            this.contador ++;
        }else {
            this.contador = 0;
        }
        addToTypeCount(tiquete.substring(0, tiquete.lastIndexOf("-")));
        String nombre = this.nameEntry.getText();
        String correo = this.emailEntry.getText();
        String telefono = this.phoneEntry.getText();
        Cliente newCliente = new Cliente(tiquete, nombre, correo, telefono, serial);
        if(type.equals("Perecedero")) {
            this.perecederoQueue.agregarPasajero(newCliente);
            this.colasTableModel.setValueAt(this.perecederoQueue.getSize(), 0, 2);
            this.colasTableModel.setValueAt(this.perecederoQueue.getFirstPasajero(), 0, 3);
        }else {
            this.noPerecederoQueue.agregarPasajero(newCliente);
            this.colasTableModel.setValueAt(this.noPerecederoQueue.getSize(), 1, 2);
            this.colasTableModel.setValueAt(this.noPerecederoQueue.getFirstPasajero(), 1, 3);
        }
        JOptionPane.showMessageDialog(null, "Tiquete: "+tiquete, "Info", JOptionPane.INFORMATION_MESSAGE);
        this.nameEntry.setText(null);
        this.emailEntry.setText(null);
        this.phoneEntry.setText(null);
        this.userSelector.setSelectedIndex(0);
        this.packageSelector.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void phoneEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneEntryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneEntryActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Integer type= this.tableColas.getSelectedRow();
        try {
            this.clientesListModel.clear();
        }catch(NullPointerException ex) {
            
        }
        switch (type) {
            case 0:
                {
                    Cliente[] clienteTemp= this.perecederoQueue.getClienteOrder();
            for (Cliente clienteTemp1 : clienteTemp) {
                this.clientesListModel.addElement(clienteTemp1.getTiquete());
            }
break;
                }
            case 1:
                {
                    Cliente[] clienteTemp = this.noPerecederoQueue.getClienteOrder();
            for (Cliente clienteTemp1 : clienteTemp) {
                this.clientesListModel.addElement(clienteTemp1.getTiquete());
            }
break;
                }
            case 2:
                {
                    Cliente[] clienteTemp = this.securityQueue.getClienteOrder();
            for (Cliente clienteTemp1 : clienteTemp) {
                this.clientesListModel.addElement(clienteTemp1.getTiquete());
            }
break;
                }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            if(!this.windowPerecedero.getText().equals("")) {
                int cantidad = Integer.valueOf(this.windowPerecedero.getText());
                if(cantidad > this.perecederoTable.getRowCount()) {
                    while(cantidad > this.perecederoTable.getRowCount()) {
                        this.VPC ++;
                        Object[] rowTemp = {"Ventanilla "+this.VPC, "Libre"};
                        this.perecederoTableModel.addRow(rowTemp);
                        Object[] rowAdd = {rowTemp[0], 0};
                        this.totalPerecederoTableModel.addRow(rowAdd);
                    }
                }else if(cantidad < this.perecederoTable.getRowCount()) {
                    while(cantidad < this.perecederoTable.getRowCount()) {
                        this.perecederoTableModel.removeRow(this.perecederoTable.getRowCount() - 1);
                        this.totalPerecederoTableModel.removeRow(this.tableTotalPerecedero.getRowCount() - 1);
                        this.VPC = cantidad;
                    }
                }
                this.windowPerecedero.setText("");
            }
            if(!this.windowNoPerecedero.getText().equals("")) {
                int cantidad = Integer.valueOf(this.windowNoPerecedero.getText());
                if(cantidad > this.noPerecederoTable.getRowCount()) {
                    while(cantidad >= this.noPerecederoTable.getRowCount()) {
                        this.VNPC ++;
                        Object[] rowTemp = {"Ventanilla "+this.VNPC, "Libre"};
                        this.noPerecederoTableModel.addRow(rowTemp);
                        Object[] rowAdd = {rowTemp[0], 0};
                        this.totalNoPerecederoTableModel.addRow(rowAdd);
                    }
                }else if(cantidad < this.noPerecederoTable.getRowCount()) {
                    while(cantidad < this.noPerecederoTable.getRowCount()) {
                        this.noPerecederoTableModel.removeRow(this.noPerecederoTable.getRowCount() - 1);
                        this.totalNoPerecederoTableModel.removeRow(this.tableTotalNoPerecedero.getRowCount() - 1);
                        this.VNPC = cantidad;
                    }
                }
                this.windowNoPerecedero.setText("");
            }
            if(!this.windowSeguridad.getText().equals("")) {
                int cantidad = Integer.valueOf(this.windowSeguridad.getText());
                if(cantidad > this.securityTable.getRowCount()) {
                    while(cantidad > this.securityTable.getRowCount()) {
                        this.VSC ++;
                        Object[] rowTemp = {"Ventanilla "+this.VSC, "Libre"};
                        this.securityTableModel.addRow(rowTemp);
                        Object[] rowAdd = {rowTemp[0], 0};
                        this.totalSecurityTableModel.addRow(rowAdd);
                    }
                }else if(cantidad < this.securityTable.getRowCount()) {
                    while(cantidad < this.securityTable.getRowCount()) {
                        this.securityTableModel.removeRow(this.securityTable.getRowCount() - 1);
                        this.totalSecurityTableModel.removeRow(this.tableTotalSecurity.getRowCount() - 1);
                        this.VSC = cantidad;
                    }
                }
                this.windowSeguridad.setText("");
            }
        }catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error al ingresar cantidad de ventanillas deseadas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(this.perecederoArray[this.perecederoTable.getSelectedRow()] != null) {
            this.securityQueue.agregarPasajero(this.perecederoArray[this.perecederoTable.getSelectedRow()]);
            this.securityThread.setColaSeguridad(this.securityQueue);
        }
        if(this.perecederoQueue.getSize() != 0) {
            Cliente next = this.perecederoQueue.getNextPasajero();
            this.perecederoTable.setValueAt(next.getTiquete(), this.perecederoTable.getSelectedRow(), 1);
            this.perecederoArray[this.perecederoTable.getSelectedRow()] = next;
        }else {
            this.perecederoArray[this.perecederoTable.getSelectedRow()] = null;
            this.perecederoTable.setValueAt("Libre", this.perecederoTable.getSelectedRow(), 1);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(this.noPerecederoArray[this.noPerecederoTable.getSelectedRow()] != null) {
            this.securityQueue.agregarPasajero(this.noPerecederoArray[this.noPerecederoTable.getSelectedRow()]);
            this.securityThread.setColaSeguridad(this.securityQueue);
        }
        if(this.noPerecederoQueue.getSize() != 0) {
            Cliente next = this.noPerecederoQueue.getNextPasajero();
            this.noPerecederoTable.setValueAt(next.getTiquete(), this.noPerecederoTable.getSelectedRow(), 1);
            this.noPerecederoArray[this.noPerecederoTable.getSelectedRow()] = next;
        }else {
            this.noPerecederoArray[this.noPerecederoTable.getSelectedRow()] = null;
            this.perecederoTable.setValueAt("Libre", this.noPerecederoTable.getSelectedRow(), 1);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void perecederoTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perecederoTableMouseClicked
        String selected= (String)this.perecederoTable.getValueAt(this.perecederoTable.getSelectedRow(), 1);
        if(selected.equals("Libre")) {
            this.jButton2.setText("Atender");
        }else {
            this.jButton2.setText("Atender y Liberar");
        }
    }//GEN-LAST:event_perecederoTableMouseClicked

    private void noPerecederoTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noPerecederoTableMouseClicked
        String selected= (String)this.noPerecederoTable.getValueAt(this.noPerecederoTable.getSelectedRow(), 1);
        if(selected.equals("Libre")) {
            this.jButton3.setText("Atender");
        }else {
            this.jButton3.setText("Atender y Liberar");
        }
    }//GEN-LAST:event_noPerecederoTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel administracion;
    private javax.swing.JPanel autoservicio;
    private javax.swing.JList<String> clienteList;
    private javax.swing.JTextField emailEntry;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField nameEntry;
    private javax.swing.JTable noPerecederoTable;
    private javax.swing.JComboBox<String> packageSelector;
    private javax.swing.JTable perecederoTable;
    private javax.swing.JTextField phoneEntry;
    private javax.swing.JTable securityTable;
    private javax.swing.JTable tableColas;
    private javax.swing.JTable tableTotalNoPerecedero;
    private javax.swing.JTable tableTotalPerecedero;
    private javax.swing.JTable tableTotalSecurity;
    private javax.swing.JLabel tiempoNoPerecedero;
    private javax.swing.JLabel tiempoPerecedero;
    private javax.swing.JLabel tiempoSeguridad;
    private javax.swing.JLabel totalNoPerAdultoMayor;
    private javax.swing.JLabel totalNoPerDiscapacidad;
    private javax.swing.JLabel totalNoPerEmbarazada;
    private javax.swing.JLabel totalNoPerRegular;
    private javax.swing.JLabel totalNoPerecedero;
    private javax.swing.JLabel totalPerAdultoMayor;
    private javax.swing.JLabel totalPerDiscapacidad;
    private javax.swing.JLabel totalPerEmbarazada;
    private javax.swing.JLabel totalPerRegular;
    private javax.swing.JLabel totalPerecedero;
    private javax.swing.JComboBox<String> userSelector;
    private javax.swing.JTextField windowNoPerecedero;
    private javax.swing.JTextField windowPerecedero;
    private javax.swing.JTextField windowSeguridad;
    // End of variables declaration//GEN-END:variables
}

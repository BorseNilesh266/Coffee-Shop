/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Renderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import org.omg.CORBA.portable.InputStream;

/**
 *
 * @author Husain Ali
 */
public class frmDashboard extends javax.swing.JFrame {

    /**
     * Creates new form frmDashboard
     */
    //  String[] qty={"1","2","3","5"};
    JComboBox q = new JComboBox();
    JCheckBox ck=new JCheckBox();
    JLabel tbimg = new JLabel("Image");
    DBConn c = new DBConn();

    public FileInputStream fin;
    static int i = 1;

    public String path = null;
    public byte[] userimage = null;

    
    
    
    
    
    
    
    Double totalAmount=0.0;
    Double cash=0.0;
    Double balance=0.0;
    Double bHeight=0.0;
    
    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<String> quantity = new ArrayList<>();
    ArrayList<String> itemPrice = new ArrayList<>();
    ArrayList<String> subtotal = new ArrayList<>();
    
    
    
    
    
    
    
    
    
    String maxBillID,totalBill;
    
    public frmDashboard() {
        initComponents();

        
         ImageIcon icon=new ImageIcon(getClass().getResource("/images/login.png"));
	    this.setIconImage(icon.getImage());
        
        btnPrint.setEnabled(false);
        
        
        
        
        pnl_Sale.hide();
        pnl_AddCoffee.hide();
        pnl_Report.hide();
        for (int i = 0; i <= 20; i++) {
            System.out.println("" + i);
            q.addItem(i);

        }

        tblCoffee.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(q));
        tblCoffee.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(ck));
        q.setSelectedIndex(0);
        myData();
        report();

       
        pnlPrint.hide();
        
     maxBillID();
    }
    
    public void maxBillID()
    {
        try {
            c.selectQuery("SELECT MAX(id) FROM `bill`");

            while (c.rs.next()) {
                
                int id = 0, a = 1;
                id = Integer.parseInt(c.rs.getString("MAX(id)").toString());
                id = id + a;
                maxBillID=""+id;
            }
    
            
        } catch (Exception ex) {
            Logger.getLogger(frmDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public void myData() {
        DefaultTableModel model = (DefaultTableModel) tblCoffee.getModel();

        JLabel imageLabel = new JLabel();

        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        try {
            c.selectQuery("SELECT * FROM `coffee`");

            Object[] fila = new Object[4];
            while (c.rs.next()) {
                // fila[0] = c.rs.getString("id");
                fila[1] = c.rs.getString("name");
                fila[3] = c.rs.getString("price");
                model.addRow(fila);
            }
            tblCoffee.setModel(model);

        } catch (Exception ex) {
            Logger.getLogger(frmDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void report() {
        DefaultTableModel model = (DefaultTableModel) tblReport.getModel();

        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        try {
            c.selectQuery("SELECT * FROM `bill`");

            while (c.rs.next()) {

                Object[] row = {c.rs.getString("id"), c.rs.getString("customer_name"), c.rs.getString("mobile"), c.rs.getString("bill")};

                model.addRow(row);
            }

        } catch (Exception ex) {
            Logger.getLogger(frmDashboard.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        pnl_Sale = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCoffee = new javax.swing.JTable();
        lblTotalBill = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_Sales_Calculate = new javax.swing.JButton();
        btn_Sales_Save = new javax.swing.JButton();
        txt_Sales_Cname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_Sales_CMobile = new javax.swing.JTextField();
        btnPrint = new javax.swing.JButton();
        pnl_AddCoffee = new javax.swing.JPanel();
        txt_Coffee_Id = new javax.swing.JTextField();
        lblPic = new javax.swing.JLabel();
        txt_Coffee_Price = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btn_Coffee_Delete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btn_Coffee_Save = new javax.swing.JButton();
        btn_Coffee_Search = new javax.swing.JButton();
        btn_Coffee_Update = new javax.swing.JButton();
        btn_Coffee_Browse = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_Coffee_Name = new javax.swing.JTextField();
        pnl_Report = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblReport = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pnl_Login3 = new javax.swing.JPanel();
        lbl_Login3 = new javax.swing.JLabel();
        pnl_Login = new javax.swing.JPanel();
        lbl_Login = new javax.swing.JLabel();
        pnl_Login1 = new javax.swing.JPanel();
        lbl_Login1 = new javax.swing.JLabel();
        pnl_Login2 = new javax.swing.JPanel();
        lbl_Login2 = new javax.swing.JLabel();
        pnl_Login4 = new javax.swing.JPanel();
        lbl_Login4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnlPrint = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        lblTotalBill1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtItem = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Coffee Shop");
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        pnl_Sale.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Sale.setLayout(null);

        tblCoffee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Boolean(false), "Coffee 1", null, "25"},
                {null, "Coffee 2", null, "30"},
                {null, "Coffee 3", null, "45"},
                {null, "Coffee 4", null, "50"}
            },
            new String [] {
                "", "Coffee", "Quantity", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCoffee.setRowHeight(30);
        tblCoffee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCoffeeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCoffee);
        if (tblCoffee.getColumnModel().getColumnCount() > 0) {
            tblCoffee.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblCoffee.getColumnModel().getColumn(0).setMaxWidth(20);
            tblCoffee.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox()));
        }

        pnl_Sale.add(jScrollPane1);
        jScrollPane1.setBounds(20, 140, 630, 270);

        lblTotalBill.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalBill.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTotalBill.setText("Total Bill");
        pnl_Sale.add(lblTotalBill);
        lblTotalBill.setBounds(570, 440, 80, 20);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Customer Name :-");
        pnl_Sale.add(jLabel3);
        jLabel3.setBounds(10, 20, 150, 40);

        btn_Sales_Calculate.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_Sales_Calculate.setText("Calculate");
        btn_Sales_Calculate.setOpaque(false);
        btn_Sales_Calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sales_CalculateActionPerformed(evt);
            }
        });
        pnl_Sale.add(btn_Sales_Calculate);
        btn_Sales_Calculate.setBounds(350, 430, 100, 40);

        btn_Sales_Save.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_Sales_Save.setText("Save");
        btn_Sales_Save.setOpaque(false);
        btn_Sales_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sales_SaveActionPerformed(evt);
            }
        });
        pnl_Sale.add(btn_Sales_Save);
        btn_Sales_Save.setBounds(70, 430, 100, 40);

        txt_Sales_Cname.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_Sales_Cname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Sales_Cname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Sales_CnameActionPerformed(evt);
            }
        });
        pnl_Sale.add(txt_Sales_Cname);
        txt_Sales_Cname.setBounds(170, 20, 220, 40);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Total Bill :-");
        pnl_Sale.add(jLabel4);
        jLabel4.setBounds(470, 440, 100, 20);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Mobile Number :-");
        pnl_Sale.add(jLabel8);
        jLabel8.setBounds(10, 80, 150, 40);

        txt_Sales_CMobile.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_Sales_CMobile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Sales_CMobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Sales_CMobileActionPerformed(evt);
            }
        });
        pnl_Sale.add(txt_Sales_CMobile);
        txt_Sales_CMobile.setBounds(170, 80, 220, 40);

        btnPrint.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        pnl_Sale.add(btnPrint);
        btnPrint.setBounds(220, 430, 80, 40);

        jPanel1.add(pnl_Sale);
        pnl_Sale.setBounds(180, 0, 680, 510);

        pnl_AddCoffee.setBackground(new java.awt.Color(255, 255, 255));
        pnl_AddCoffee.setLayout(null);

        txt_Coffee_Id.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_Coffee_Id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Coffee_Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Coffee_IdActionPerformed(evt);
            }
        });
        pnl_AddCoffee.add(txt_Coffee_Id);
        txt_Coffee_Id.setBounds(190, 130, 280, 40);

        lblPic.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblPic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnl_AddCoffee.add(lblPic);
        lblPic.setBounds(490, 130, 140, 160);

        txt_Coffee_Price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_Coffee_Price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Coffee_Price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Coffee_PriceActionPerformed(evt);
            }
        });
        pnl_AddCoffee.add(txt_Coffee_Price);
        txt_Coffee_Price.setBounds(190, 250, 280, 40);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Coffee Price");
        pnl_AddCoffee.add(jLabel5);
        jLabel5.setBounds(70, 250, 110, 40);

        btn_Coffee_Delete.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_Coffee_Delete.setText("Delete");
        btn_Coffee_Delete.setOpaque(false);
        btn_Coffee_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Coffee_DeleteActionPerformed(evt);
            }
        });
        pnl_AddCoffee.add(btn_Coffee_Delete);
        btn_Coffee_Delete.setBounds(480, 390, 100, 40);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Coffee ID");
        pnl_AddCoffee.add(jLabel6);
        jLabel6.setBounds(70, 130, 110, 40);

        btn_Coffee_Save.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_Coffee_Save.setText("Save");
        btn_Coffee_Save.setOpaque(false);
        btn_Coffee_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Coffee_SaveActionPerformed(evt);
            }
        });
        pnl_AddCoffee.add(btn_Coffee_Save);
        btn_Coffee_Save.setBounds(90, 390, 100, 40);

        btn_Coffee_Search.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_Coffee_Search.setText("Search");
        btn_Coffee_Search.setOpaque(false);
        btn_Coffee_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Coffee_SearchActionPerformed(evt);
            }
        });
        pnl_AddCoffee.add(btn_Coffee_Search);
        btn_Coffee_Search.setBounds(220, 390, 100, 40);

        btn_Coffee_Update.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_Coffee_Update.setText("Update");
        btn_Coffee_Update.setOpaque(false);
        btn_Coffee_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Coffee_UpdateActionPerformed(evt);
            }
        });
        pnl_AddCoffee.add(btn_Coffee_Update);
        btn_Coffee_Update.setBounds(350, 390, 100, 40);

        btn_Coffee_Browse.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_Coffee_Browse.setText("Browse");
        btn_Coffee_Browse.setOpaque(false);
        btn_Coffee_Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Coffee_BrowseActionPerformed(evt);
            }
        });
        pnl_AddCoffee.add(btn_Coffee_Browse);
        btn_Coffee_Browse.setBounds(510, 300, 100, 40);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Coffee Name");
        pnl_AddCoffee.add(jLabel7);
        jLabel7.setBounds(70, 190, 110, 40);

        txt_Coffee_Name.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_Coffee_Name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Coffee_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Coffee_NameActionPerformed(evt);
            }
        });
        pnl_AddCoffee.add(txt_Coffee_Name);
        txt_Coffee_Name.setBounds(190, 190, 280, 40);

        jPanel1.add(pnl_AddCoffee);
        pnl_AddCoffee.setBounds(180, 0, 680, 510);

        pnl_Report.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Report.setLayout(null);

        tblReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Mobile No", "Total Bill"
            }
        ));
        tblReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblReportMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblReport);
        if (tblReport.getColumnModel().getColumnCount() > 0) {
            tblReport.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblReport.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        pnl_Report.add(jScrollPane2);
        jScrollPane2.setBounds(80, 110, 520, 320);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Report");
        pnl_Report.add(jLabel11);
        jLabel11.setBounds(190, 40, 280, 60);

        jPanel1.add(pnl_Report);
        pnl_Report.setBounds(180, 0, 680, 510);

        jPanel2.setBackground(new java.awt.Color(153, 0, 0));
        jPanel2.setLayout(null);

        pnl_Login3.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Login3.setLayout(null);

        lbl_Login3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_Login3.setForeground(new java.awt.Color(51, 51, 51));
        lbl_Login3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Login3.setText("Logout");
        lbl_Login3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_Login3MouseClicked(evt);
            }
        });
        pnl_Login3.add(lbl_Login3);
        lbl_Login3.setBounds(0, 0, 120, 40);

        jPanel2.add(pnl_Login3);
        pnl_Login3.setBounds(30, 370, 120, 40);

        pnl_Login.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Login.setLayout(null);

        lbl_Login.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_Login.setForeground(new java.awt.Color(51, 51, 51));
        lbl_Login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Login.setText("Dashboard");
        lbl_Login.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lbl_LoginAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        lbl_Login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_LoginMouseClicked(evt);
            }
        });
        pnl_Login.add(lbl_Login);
        lbl_Login.setBounds(0, 0, 120, 40);

        jPanel2.add(pnl_Login);
        pnl_Login.setBounds(30, 130, 120, 40);

        pnl_Login1.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Login1.setLayout(null);

        lbl_Login1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_Login1.setForeground(new java.awt.Color(51, 51, 51));
        lbl_Login1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Login1.setText("Add Coffee");
        lbl_Login1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_Login1MouseClicked(evt);
            }
        });
        pnl_Login1.add(lbl_Login1);
        lbl_Login1.setBounds(0, 0, 120, 40);

        jPanel2.add(pnl_Login1);
        pnl_Login1.setBounds(30, 250, 120, 40);

        pnl_Login2.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Login2.setLayout(null);

        lbl_Login2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_Login2.setForeground(new java.awt.Color(51, 51, 51));
        lbl_Login2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Login2.setText("Sales");
        lbl_Login2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lbl_Login2AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        lbl_Login2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_Login2MouseClicked(evt);
            }
        });
        pnl_Login2.add(lbl_Login2);
        lbl_Login2.setBounds(0, 0, 120, 40);

        jPanel2.add(pnl_Login2);
        pnl_Login2.setBounds(30, 190, 120, 40);

        pnl_Login4.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Login4.setLayout(null);

        lbl_Login4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_Login4.setForeground(new java.awt.Color(51, 51, 51));
        lbl_Login4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Login4.setText("Report");
        lbl_Login4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_Login4MouseClicked(evt);
            }
        });
        pnl_Login4.add(lbl_Login4);
        lbl_Login4.setBounds(0, 0, 120, 40);

        jPanel2.add(pnl_Login4);
        pnl_Login4.setBounds(30, 310, 120, 40);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 180, 520);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loginback.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(180, 10, 690, 550);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 870, 510);

        pnlPrint.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrint.setLayout(null);

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlPrint.add(lblLogo);
        lblLogo.setBounds(10, 10, 80, 70);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Main Road Shahada");
        pnlPrint.add(jLabel2);
        jLabel2.setBounds(100, 40, 160, 20);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("My Coffee Shop");
        pnlPrint.add(jLabel9);
        jLabel9.setBounds(100, 10, 160, 20);

        jSeparator1.setBackground(new java.awt.Color(102, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(102, 0, 0));
        pnlPrint.add(jSeparator1);
        jSeparator1.setBounds(0, 90, 290, 10);

        jSeparator2.setBackground(new java.awt.Color(102, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(102, 0, 0));
        pnlPrint.add(jSeparator2);
        jSeparator2.setBounds(0, 70, 290, 10);

        jLabel10.setText("Item Name                                         Ret      Qty        Amount");
        pnlPrint.add(jLabel10);
        jLabel10.setBounds(0, 70, 290, 20);

        lblTotalBill1.setText("jLabel3");
        pnlPrint.add(lblTotalBill1);
        lblTotalBill1.setBounds(224, 280, 50, 14);

        txtItem.setColumns(20);
        jScrollPane3.setViewportView(txtItem);

        pnlPrint.add(jScrollPane3);
        jScrollPane3.setBounds(0, 96, 290, 160);

        getContentPane().add(pnlPrint);
        pnlPrint.setBounds(0, 0, 320, 300);

        setSize(new java.awt.Dimension(866, 539));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_LoginAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lbl_LoginAncestorAdded
        // TODO add your handling code here:


    }//GEN-LAST:event_lbl_LoginAncestorAdded

    private void lbl_Login2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lbl_Login2AncestorAdded
        // TODO add your handling code here:


    }//GEN-LAST:event_lbl_Login2AncestorAdded

    private void lbl_Login2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_Login2MouseClicked
        // TODO add your handling code here:

        pnl_Sale.show();
        pnl_AddCoffee.hide();
        pnl_Report.hide();
    }//GEN-LAST:event_lbl_Login2MouseClicked

    private void lbl_LoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_LoginMouseClicked
        // TODO add your handling code here:

        pnl_Sale.hide();

        pnl_AddCoffee.hide();
        pnl_Report.hide();
    }//GEN-LAST:event_lbl_LoginMouseClicked

    private void btn_Sales_CalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sales_CalculateActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) tblCoffee.getModel();
        int b = 0;
        //int i = tblCoffee.getSelectedRow();
        for (int i = 0; i < tblCoffee.getRowCount(); i++) {
            boolean checked = Boolean.valueOf(model.getValueAt(i, 0).toString());
            String price = model.getValueAt(i, 3).toString();
            String qty = model.getValueAt(i, 2).toString();
            int a = Integer.parseInt(price);
            int qt = Integer.parseInt(qty);
            if (checked) {

                b = b + a * qt;
                lblTotalBill.setText("" + b);
               totalBill=""+b;
            }
        }

        
        
         
        
        
        
     
        
      
        
        
        
        
    }//GEN-LAST:event_btn_Sales_CalculateActionPerformed

    private void txt_Coffee_IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Coffee_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Coffee_IdActionPerformed

    private void txt_Coffee_PriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Coffee_PriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Coffee_PriceActionPerformed

    private void btn_Coffee_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Coffee_DeleteActionPerformed
        // TODO add your handling code here:

        try {

            PreparedStatement ps = c.con.prepareStatement("DELETE FROM `coffee` WHERE `id`=?");
            ps.setString(1, txt_Coffee_Id.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Record Successfully Delete");
        } catch (Exception ex) {
            Logger.getLogger(frmDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_Coffee_DeleteActionPerformed

    private void btn_Coffee_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Coffee_SaveActionPerformed

        try {

            //c.nonQuery("INSERT INTO `coffee`(`id`, `name`, `price`, `images`) VALUES ('" + txt_Coffee_Id.getText() + "','" + txt_Coffee_Name.getText() + "','" + txt_Coffee_Price.getText() + "','" + fin + "')");
            PreparedStatement ps = c.con.prepareStatement("INSERT INTO `coffee`(`id`, `name`, `price`, `images`) VALUES (?,?,?,?)");
            ps.setString(1, txt_Coffee_Id.getText());
            ps.setString(2, txt_Coffee_Name.getText());
            ps.setString(3, txt_Coffee_Price.getText());
            ps.setBinaryStream(4, fin);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Record Successfully Added");
        } catch (Exception ex) {
            Logger.getLogger(frmDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_Coffee_SaveActionPerformed

    private void btn_Coffee_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Coffee_SearchActionPerformed
        try {
            // TODO add your handling code here:

            c.selectQuery("SELECT * FROM `coffee` where id='" + txt_Coffee_Id.getText() + "'");
            while (c.rs.next()) {

                txt_Coffee_Name.setText(c.rs.getString("name"));
                txt_Coffee_Price.setText(c.rs.getString("price"));

                byte[] getimage = c.rs.getBytes("images");
                ImageIcon putImage = new ImageIcon(getimage);
                Image im = putImage.getImage();
                Image myimage = im.getScaledInstance(lblPic.getWidth(), lblPic.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon i = new ImageIcon(myimage);
                lblPic.setIcon(i);
                System.out.println("" + i);
            }

        } catch (Exception ex) {
            Logger.getLogger(frmDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_Coffee_SearchActionPerformed

    private void btn_Coffee_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Coffee_UpdateActionPerformed
        // TODO add your handling code here:
        try {

            c.nonQuery("UPDATE coffee SET name = '" + txt_Coffee_Name.getText() + "',price = '" + txt_Coffee_Price.getText() + "' WHERE id = '" + txt_Coffee_Id.getText() + "'");

            /*  PreparedStatement ps = c.con.prepareStatement("UPDATE coffee SET name = ?,price = ? WHERE id = ?");
            ps.setString(1, txt_Coffee_Id.getText());
            ps.setString(2, txt_Coffee_Name.getText());
            ps.setString(3, txt_Coffee_Price.getText());
            // ps.setBinaryStream(4, fin);*/
            //  ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Successfully Update");
        } catch (Exception ex) {
            Logger.getLogger(frmDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_Coffee_UpdateActionPerformed

    private void btn_Coffee_BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Coffee_BrowseActionPerformed

        Image i = new ImageIcon(c.showD()).getImage().getScaledInstance(lblPic.getWidth(), lblPic.getHeight(), Image.SCALE_SMOOTH);

        lblPic.setIcon(new ImageIcon(i));
        try {
            fin = new FileInputStream(c.myfile.getAbsolutePath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("File Path in Coffee=" + c.myfile.getAbsolutePath());
    }//GEN-LAST:event_btn_Coffee_BrowseActionPerformed

    private void lbl_Login1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_Login1MouseClicked
        // TODO add your handling code here:

        pnl_Sale.hide();

        pnl_AddCoffee.show();
        pnl_Report.hide();
    }//GEN-LAST:event_lbl_Login1MouseClicked

    private void lbl_Login4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_Login4MouseClicked
        // TODO add your handling code here:

        pnl_Sale.hide();
        pnl_AddCoffee.hide();
        pnl_Report.show();
    }//GEN-LAST:event_lbl_Login4MouseClicked

    private void tblCoffeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCoffeeMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tblCoffeeMouseClicked

    private void txt_Coffee_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Coffee_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Coffee_NameActionPerformed

    private void btn_Sales_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sales_SaveActionPerformed
       
        
        if(txt_Sales_Cname.getText().isEmpty())
        {
        
          JOptionPane.showMessageDialog(null, "Enter The Customer Name");
        }
        else  if(txt_Sales_CMobile.getText().isEmpty())
        {
        
          JOptionPane.showMessageDialog(null, "Enter The Customer Mobile No");
        }
        else
        {
        try {
            c.nonQuery("INSERT INTO `bill`(`id`, `customer_name`, `mobile`, `bill`) VALUES ('" + maxBillID + "','" + txt_Sales_Cname.getText() + "','" + txt_Sales_CMobile.getText() + "','" + lblTotalBill.getText() + "')");
        } catch (Exception ex) {
            Logger.getLogger(frmDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < tblCoffee.getRowCount(); i++) {
            try {
                c.nonQuery("INSERT INTO `sales`(`id`, `customer_name`, `mobile_no`, `coffee_name`, `quantity`, `price`) VALUES ('" + maxBillID + "','" + txt_Sales_Cname.getText() + "','" + txt_Sales_CMobile.getText() + "','" + tblCoffee.getValueAt(i, 1).toString() + "','" + tblCoffee.getValueAt(i, 2).toString() + "','" + tblCoffee.getValueAt(i, 3).toString() + "')");
            } catch (Exception ex) {
                Logger.getLogger(frmDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          JOptionPane.showMessageDialog(null, "Bill Successfully Pay");
        
        try {
            c.selectQuery("SELECT * FROM `sales` WHERE mobile_no ='" + txt_Sales_CMobile.getText() + "'");
            while (c.rs.next()) {
                itemName.add(c.rs.getString("coffee_name"));
                quantity.add(c.rs.getString("quantity"));
                itemPrice.add(c.rs.getString("price"));
                Integer q = new Integer(c.rs.getString("quantity"));
                Integer pr = new Integer(c.rs.getString("price"));
                int st = q * pr;
                subtotal.add("" + st);
            }
        } catch (Exception ex) {
            Logger.getLogger(frmDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           btnPrint.setEnabled(true);
        }
    }//GEN-LAST:event_btn_Sales_SaveActionPerformed

    private void lbl_Login3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_Login3MouseClicked
        // TODO add your handling code here:
        
        new frm_Login().setVisible(true);
        JOptionPane.showMessageDialog(this,"Log out Sucessfully");
        this.dispose();
    }//GEN-LAST:event_lbl_Login3MouseClicked

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
      
        
         
         
         
         
        bHeight = Double.valueOf(itemName.size());
        //JOptionPane.showMessageDialog(rootPane, bHeight);
        
        PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
             pj.print();
          
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
        
        
    
        
    }//GEN-LAST:event_btnPrintActionPerformed

    private void tblReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReportMouseClicked
        // TODO add your handling code here:
        
       
    }//GEN-LAST:event_tblReportMouseClicked

    private void txt_Sales_CMobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Sales_CMobileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Sales_CMobileActionPerformed

    private void txt_Sales_CnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Sales_CnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Sales_CnameActionPerformed

   
    
    
    
    
    
     public PageFormat getPageFormat(PrinterJob pj)
 {
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double bodyHeight = bHeight;  
    double headerHeight = 5.0;                  
    double footerHeight = 5.0;        
    double width = cm_to_pp(8); 
    double height = cm_to_pp(headerHeight+bodyHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(0,10,width,height - cm_to_pp(1));  
            
    pf.setOrientation(PageFormat.PORTRAIT);  
    pf.setPaper(paper);    

    return pf;
}
 
 
  
    protected static double cm_to_pp(double cm)
    {            
	        return toPPI(cm * 0.393600787);            
    }
 
protected static double toPPI(double inch)
    {            
	        return inch * 72d;            
    }
 
public class BillPrintable implements Printable {
    
   
    
    
  public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
      
      int r= itemName.size();
      ImageIcon icon=new ImageIcon(getClass().getResource("/images/loginback.png")); 
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    
            double width = pageFormat.getImageableWidth();                               
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 



          //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
        
        try{
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
           // int headerRectHeighta=40;
            
                
            g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
            g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);y+=yShift+30;
            g2d.drawString("-------------------------------------",12,y);y+=yShift;
            g2d.drawString("        The Coffee Shop        ",12,y);y+=yShift;
            g2d.drawString("            Main Road          ",12,y);y+=yShift;
            g2d.drawString("    Near ICICI Bank, Shahada  ",12,y);y+=yShift;
        //    g2d.drawString("                               ",12,y);y+=yShift;
           // g2d.drawString("          +91-0000000000       ",12,y);y+=yShift;
            g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;
            g2d.drawString("Customer Name:- "+txt_Sales_Cname.getText()+"  ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;

            g2d.drawString(" Item Name                    Price   ",10,y);y+=yShift;
            g2d.drawString("--------------------------------------",10,y);y+=headerRectHeight;
     
            for(int s=0; s<r; s++)
            {
            
                         
            g2d.drawString(" "+itemName.get(s)+"                            ",10,y);y+=yShift;
            g2d.drawString("      "+quantity.get(s)+" * "+itemPrice.get(s),10,y); 
            g2d.drawString("   "+subtotal.get(s),160,y);y+=yShift;

            }
            
        
          
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Total amount:                 "+lblTotalBill.getText()+"   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
           
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("       THANK YOU COME AGAIN            ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            //g2d.drawString("         SOFTWARE BY:Nilesh           ",10,y);y+=yShift;
          //  g2d.drawString("   CONTACT: 9552115028                      ",10,y);y+=yShift;       
           

    }
    catch(Exception e){
    e.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
   }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(frmDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btn_Coffee_Browse;
    private javax.swing.JButton btn_Coffee_Delete;
    private javax.swing.JButton btn_Coffee_Save;
    private javax.swing.JButton btn_Coffee_Search;
    private javax.swing.JButton btn_Coffee_Update;
    private javax.swing.JButton btn_Sales_Calculate;
    private javax.swing.JButton btn_Sales_Save;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPic;
    private javax.swing.JLabel lblTotalBill;
    private javax.swing.JLabel lblTotalBill1;
    private javax.swing.JLabel lbl_Login;
    private javax.swing.JLabel lbl_Login1;
    private javax.swing.JLabel lbl_Login2;
    private javax.swing.JLabel lbl_Login3;
    private javax.swing.JLabel lbl_Login4;
    private javax.swing.JPanel pnlPrint;
    private javax.swing.JPanel pnl_AddCoffee;
    private javax.swing.JPanel pnl_Login;
    private javax.swing.JPanel pnl_Login1;
    private javax.swing.JPanel pnl_Login2;
    private javax.swing.JPanel pnl_Login3;
    private javax.swing.JPanel pnl_Login4;
    private javax.swing.JPanel pnl_Report;
    private javax.swing.JPanel pnl_Sale;
    private javax.swing.JTable tblCoffee;
    private javax.swing.JTable tblReport;
    private javax.swing.JTextArea txtItem;
    private javax.swing.JTextField txt_Coffee_Id;
    private javax.swing.JTextField txt_Coffee_Name;
    private javax.swing.JTextField txt_Coffee_Price;
    private javax.swing.JTextField txt_Sales_CMobile;
    private javax.swing.JTextField txt_Sales_Cname;
    // End of variables declaration//GEN-END:variables
}

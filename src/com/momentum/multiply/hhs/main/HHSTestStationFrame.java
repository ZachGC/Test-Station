/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.momentum.multiply.hhs.main;

import com.momentum.multiply.SQL.DBAccess;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author zachristophers
 */
public class HHSTestStationFrame extends javax.swing.JFrame {

    private ApplicationMainFrame amf;
    private String as400UN, as400PW, coreUN, corePW;
    private DBAccess as400 = null, core = null, test = null;

    public String getAs400UN() {
        return as400UN;
    }

    public void setAs400UN(String as400UN) {
        this.as400UN = as400UN;
    }

    public String getAs400PW() {
        return as400PW;
    }

    public void setAs400PW(String as400PW) {
        this.as400PW = as400PW;
    }

    public String getCoreUN() {
        return coreUN;
    }

    public void setCoreUN(String coreUN) {
        this.coreUN = coreUN;
    }

    public String getCorePW() {
        return corePW;
    }

    public void setCorePW(String corePW) {
        this.corePW = corePW;
    }

    public HHSTestStationFrame(ApplicationMainFrame amf) {
        initComponents();
        this.amf = amf;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                checkTxtFields();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                checkTxtFields();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                checkTxtFields();
            }
        };

        scroller = new javax.swing.JScrollPane();
        tblResults = new javax.swing.JTable();
        txtNumGreen = new javax.swing.JTextField();
        txtNumAmber = new javax.swing.JTextField();
        txtNumRed = new javax.swing.JTextField();
        lblGreen = new javax.swing.JLabel();
        lblAmber = new javax.swing.JLabel();
        lblRed = new javax.swing.JLabel();
        lblHeader = new javax.swing.JLabel();
        btnGenerate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("HHS Test Station");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 900, 600));
        setPreferredSize(new java.awt.Dimension(900, 650));
        setResizable(false);
        setSize(new java.awt.Dimension(900, 650));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                amf.setVisible(true);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HHSTestStationFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        tblResults.setVisible(false);
        getContentPane().add(tblResults, new AbsoluteConstraints(200, 300, 500, 200));

        scroller.setViewportView(tblResults);

        txtNumGreen.setText("");
        txtNumGreen.setEditable(true);
        txtNumGreen.getDocument().addDocumentListener(dl);
        getContentPane().add(txtNumGreen, new AbsoluteConstraints(225, 100, 75, 25));

        txtNumAmber.setText("");
        txtNumAmber.setEditable(true);
        txtNumAmber.getDocument().addDocumentListener(dl);
        getContentPane().add(txtNumAmber, new AbsoluteConstraints(450, 100, 75, 25));

        txtNumRed.setText("");
        txtNumRed.setEditable(true);
        txtNumRed.getDocument().addDocumentListener(dl);
        getContentPane().add(txtNumRed, new AbsoluteConstraints(675, 100, 75, 25));

        lblGreen.setText("Green Clients:");
        getContentPane().add(lblGreen, new AbsoluteConstraints(100, 100, 100, 25));

        lblAmber.setText("Amber Clients:");
        getContentPane().add(lblAmber, new AbsoluteConstraints(325, 100, 100, 25));

        lblRed.setText("Red Clients:");
        getContentPane().add(lblRed, new AbsoluteConstraints(550, 100, 100, 25));

        lblHeader.setText("Insert the number of:");
        getContentPane().add(lblHeader, new AbsoluteConstraints(350, 50, 200, 25));

        btnGenerate.setText("Generate");
        btnGenerate.setEnabled(false);
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });
        getContentPane().add(btnGenerate, new AbsoluteConstraints(700, 550, 100, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {
        int counter = 0;
        do {
            as400 = connect("AS400");
            counter++;
        } while (!as400.connectionSuccessful() && counter < 3);
        if (counter < 5) {
            counter = 0;
        }
        do {
            core = connect("CORE");
            counter++;
        } while (!core.connectionSuccessful() && counter < 3);
        if (counter < 5) {
            counter = 0;
        }
        do {
            test = connect("TEST");
            counter++;
        } while (!test.connectionSuccessful() && counter < 3);

        if (counter != 5) {
            generate();
        } else {
            System.out.println("Problem at connection terminals");
        }
    }

    private DBAccess connect(String db) {
        DBAccess temp = null;

        if (db == "CORE") {
            new LoginUI(this, "CORE").setVisible(true);
            temp = new DBAccess("com.ibm.db2.jcc.DB2Driver", "jdbc:db2://EDB2DEV3:60022/MMULTDCS", coreUN, corePW);
        } else if (db == "AS400") {
            new LoginUI(this, "AS400").setVisible(true);
            temp = new DBAccess("com.ibm.as400.access.AS400JDBCDriver", "jdbc:as400://isd.momentum.co.za:1433/lpprdlib", as400UN, as400UN);
        } else if (db == "TEST") {
            temp = new DBAccess("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://mmdkfvuhtdev01\\MSSQLSERVER:1433;databaseName=TEST_HHS_REPOSITORY", "sa", "Admin01+");
        }

        return temp;
    }

    private void generate(){
        try {
            txtNumAmber.setEditable(false);
            txtNumGreen.setEditable(false);
            txtNumRed.setEditable(false);
            File file = new File("lastidnumber.txt");
            if(file.createNewFile()){
                new PrintWriter(file).print("0");
            }
            Scanner line = new Scanner(file);

            int lastnum = line.nextInt();
            line.close();
            int greens = Integer.parseInt(txtNumGreen.getText());
            int ambers = Integer.parseInt(txtNumAmber.getText());
            int reds = Integer.parseInt(txtNumRed.getText());
            int total = greens + reds + ambers;

            String[] columns = {"CLIENT_NUMBER", "GENDER", "AGE", "HEIGHT", "WEIGHT", "WAIST", "CHOLESTOROL", "BLOOD_PRESSURE_SYSTOLIC", "BLOOD_PRESSURE_DIASTOLIC", "GLUCOSE", "SMOKER", "EXPECTED_OUTPUT"};
            String[][] data = new String[greens][12];
            List<String> usedClientNumbers = new ArrayList<String>();
            ResultSet rs = as400.executeQuery("SELECT DISTINCT CRPOLA, CRPOLN, CRCNBR, CRRTYP, CCIDNR, CCDTOB, CCSEXC, CCFNAM, CCSNAM, CONMLPCSTA \n"
                    + "FROM BBLIB.CMSROLEPF A                                                                 \n"
                    + "LEFT JOIN LPCPCONMLA D ON D.CONMLPNOAL = A.CRPOLA AND D.CONMLPNUMB = A.CRPOLN                        \n"
                    + "LEFT JOIN BBLIB.CMSCLNTPF B ON B.CCCNBR = A.CRCNBR\n"
                    + "WHERE crpola in ('MM','MB')\n"
                    + "AND CRRTYP IN ('POLHOLD','PARTNER')\n"
                    + "AND CONMLPCSTA = '10INFPPAY' -- Filter on active contracts\n"
                    + "AND CCIDNR IS NOT NULL\n"
                    + "AND CCIDNR > " + line + "\n"
                    + "ORDER BY CCFNAM\n"
                    + "LIMIT " + total);

            
            double[] bodyfat = new CalculateBodyFat(reds, greens, ambers,generateAges(rs, total)).calculate();
            
            test.insertInto("dbo.CLIENT_MEASUREMENTS", columns, data, greens);
        } catch (SQLException ex) {
            Logger.getLogger(HHSTestStationFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HHSTestStationFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HHSTestStationFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int[] generateAges(ResultSet rs, int size) {
        String[] array = new String[size];
        try {
            for (int i = 0; i < size; i++) {
                array[i] = "19" + rs.getString("CCIDNR").substring(0, 2) + "/" + rs.getString("CCIDNR").substring(2, 4) + "/" + rs.getString("CCIDNR").substring(4, 6);
                rs.next();
            }
        } catch (Exception e) {
            System.out.println("Yo. Exception.");
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        int[] arrays = new int[size];
        
        for (int i = 0; i < size; i++) {
            arrays[i] = getDiffYears(new java.util.Date(array[i]), new java.util.Date());
        }
        return arrays;
    }

    public int getDiffYears(java.util.Date first, java.util.Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)
                || (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    public Calendar getCalendar(java.util.Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

    private String[] generateGenders(ResultSet rs, int size) {

    }

    private void checkTxtFields() {
        String green = txtNumGreen.getText(), amber = txtNumAmber.getText(), red = txtNumRed.getText();
        if (green.equals("") || amber.equals("") || red.equals("")) {
            btnGenerate.setEnabled(false);
        } else {
            try {
                Integer.parseInt(green);
                Integer.parseInt(amber);
                Integer.parseInt(red);
                btnGenerate.setEnabled(true);
            } catch (NumberFormatException e) {
                btnGenerate.setEnabled(false);
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnGenerate;
    private javax.swing.JLabel lblGreen;
    private javax.swing.JLabel lblAmber;
    private javax.swing.JLabel lblRed;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JScrollPane scroller;
    private javax.swing.JTable tblResults;
    private javax.swing.JTextField txtNumGreen;
    private javax.swing.JTextField txtNumAmber;
    private javax.swing.JTextField txtNumRed;
    // End of variables declaration                   
}

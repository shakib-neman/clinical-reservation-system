/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author user
 */
public class LabMenu extends javax.swing.JFrame {

    /**
     * Creates new form LabMenu
     */
    File table, Folder;
    Scanner read;
    String book, current, st;
    Date crt;

    public LabMenu() {
        initComponents();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable2.setShowGrid(true);
        jScrollPane2.setVisible(false);
        jTable2.setVisible(false);
        jtfPhone.setVisible(false);
        jtfName.setVisible(false);
        Phone.setVisible(false);
        Name.setVisible(false);
        BookButton.setVisible(false);
        Hint.setVisible(false);
        showTime();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    void tableFill(String n) throws FileNotFoundException {
        table = new File("Labs//Lab" + n + ".dat");

        for (int i = 0; i < jTable2.getRowCount(); i++) {
            for (int j = 1; j < jTable2.getColumnCount(); j++) {
                if (jTable2.getColumnName(j).contains("Sat") || jTable2.getColumnName(j).contains("Sun")) {
                    jTable2.setValueAt("Closed", i, j);
                } else {
                    jTable2.setValueAt("", i, j);
                }
            }
        }
        if (table.exists()) {
            read = new Scanner(table);
            if (table.canRead() == true) {
                while (read.hasNext()) {
                    int row = read.nextInt();
                    int col = read.nextInt();
                    String rest = read.nextLine();
                    String dtfile = rest.substring(rest.length() - 4);
                    int sub;
                    int dt = Integer.parseInt(dtfile.substring(2, 4));
                    Month month1 = Month.of(Integer.parseInt(dtfile.substring(0, 2)));
                    Month month2 = Month.of(Integer.parseInt(current.substring(2)));
                    if (month1.compareTo(month2) >= 0) {
                        if (month1.compareTo(month2) > 0) {
                            int nmbday = month2.length(true);
                            sub = nmbday - Integer.parseInt(current.substring(0, 2)) + dt;
                        } else {
                            sub = dt - Integer.parseInt(current.substring(0, 2));
                        }
                        if (sub >= 0) {
                            jTable2.setValueAt("Occupied", row, sub + 1);
                        }
                    }
                }
            }
        }
    }

    void showTime() {

        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Date d = new Date();
                crt = d;
                SimpleDateFormat crt = new SimpleDateFormat("ddMM");
                current = crt.format(d);
                SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat da = new SimpleDateFormat("E");
                SimpleDateFormat t = new SimpleDateFormat("HH");
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
                date.setText(dt.format(d));
                time.setText(s.format(d));
                st = da.format(d);
                if (!da.format(d).equals("Sat") && !da.format(d).equals("Sun")) {
                    if (Integer.parseInt(t.format(d)) > 7 && Integer.parseInt(t.format(d)) < 24) {
                        int times = Integer.parseInt(t.format(d)) - 8;
                        if (times == 3) {
                            times--;
                        } else if (times >= 4) {
                            times = times - 2;
                        }
                        for (int i = 0; i <= times; i++) {
                            if (i >= 5) {
                                continue;
                            }
                            jTable2.setValueAt("Passed", i, 1);
                        }
                    }
                }
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable(){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component comp = super.prepareRenderer(renderer, row, col);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment( JLabel.CENTER );
                for(int j=0;j<8;j++)
                jTable2.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
                Object value = getModel().getValueAt(row, col);
                // if (getSelectedRow() == row) {
                    if(value==null){
                        comp.setForeground(Color.BLACK);
                        jTable2.setValueAt("", row, col);
                    }
                    else if (value.equals("Occupied")) {
                        comp.setForeground(Color.red);}
                    else if (value.equals("Passed")) {
                        comp.setBackground(Color.LIGHT_GRAY);
                    }else if(value.equals("Closed")){
                        comp.setBackground(Color.gray);
                    }else {
                        comp.setForeground(Color.BLACK);
                    }
                    //} else {
                    //   comp.setBackground(Color.white);

                    return comp;
                }
            }
            ;
            BackButton = new javax.swing.JButton();
            LoginButton = new javax.swing.JButton();
            BookButton = new javax.swing.JButton();
            jtfPhone = new javax.swing.JTextField();
            jtfName = new javax.swing.JTextField();
            Name = new javax.swing.JLabel();
            Phone = new javax.swing.JLabel();
            Hint = new javax.swing.JLabel();
            Menu = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setTitle("LABORATORY");
            setMaximumSize(new java.awt.Dimension(1000, 685));
            setMinimumSize(new java.awt.Dimension(1000, 685));
            setPreferredSize(new java.awt.Dimension(1000, 685));
            setResizable(false);
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    formWindowClosing(evt);
                }
            });
            getContentPane().setLayout(null);

            jLabel4.setBackground(new java.awt.Color(51, 153, 255));
            jLabel4.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(255, 255, 255));
            jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel4.setText(" COVID-19 RT-PCR");
            jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 2, true));
            jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabel4.setOpaque(true);
            jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel4MouseClicked(evt);
                }
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jLabel4MouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jLabel4MouseExited(evt);
                }
            });
            getContentPane().add(jLabel4);
            jLabel4.setBounds(40, 380, 910, 40);

            jLabel3.setBackground(new java.awt.Color(51, 153, 255));
            jLabel3.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel3.setText(" Semen Analysis");
            jLabel3.setToolTipText("");
            jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 2, true));
            jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabel3.setOpaque(true);
            jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel3MouseClicked(evt);
                }
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jLabel3MouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jLabel3MouseExited(evt);
                }
            });
            getContentPane().add(jLabel3);
            jLabel3.setBounds(40, 290, 910, 40);

            jLabel2.setBackground(new java.awt.Color(51, 153, 255));
            jLabel2.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(255, 255, 255));
            jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel2.setText(" Urinalysis / Stool Test");
            jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 2, true));
            jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabel2.setOpaque(true);
            jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel2MouseClicked(evt);
                }
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jLabel2MouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jLabel2MouseExited(evt);
                }
            });
            getContentPane().add(jLabel2);
            jLabel2.setBounds(40, 200, 910, 40);

            jLabel1.setBackground(new java.awt.Color(51, 153, 255));
            jLabel1.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel1.setText(" Blood Test");
            jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 2, true));
            jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabel1.setOpaque(true);
            jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel1MouseClicked(evt);
                }
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jLabel1MouseEntered(evt);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jLabel1MouseExited(evt);
                }
            });
            getContentPane().add(jLabel1);
            jLabel1.setBounds(40, 110, 910, 40);

            date.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            date.setForeground(new java.awt.Color(255, 255, 255));
            date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            getContentPane().add(date);
            date.setBounds(330, 10, 230, 40);

            time.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            time.setForeground(new java.awt.Color(255, 255, 255));
            time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            getContentPane().add(time);
            time.setBounds(480, 10, 230, 40);

            jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

            jTable2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTable2.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 20));
            Calendar c=Calendar.getInstance();
            Date d = new Date();
            c.setTime(d);
            ArrayList<String> cal=new ArrayList();
            SimpleDateFormat day= new SimpleDateFormat("E");
            SimpleDateFormat title =new SimpleDateFormat("E dd / MM");
            for(int i=0;i<7;i++){
                Date tab=c.getTime();
                if(i==0){
                    cal.add(title.format(tab));
                    c.add(Calendar.DATE, 1);}
                else{
                    c.add(Calendar.DATE, 1);
                    cal.add(title.format(tab));}}
            jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {"9:00 - 10:00", null, null, null, null, null},
                    {"10:00 - 11:00", null, null, null, null, null},
                    {"11:00 - 12:00", null, null, null, null, null},
                    {"14:00 - 15:00", null, null, null, null, null},
                    {"15:00 - 16:00", null, null, null, null, null}
                },
                new String [] {
                    "Time", cal.get(0), cal.get(1), cal.get(2), cal.get(3), cal.get(4), cal.get(5), cal.get(6)
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false,false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jTable2.setRowHeight(57);
            jTable2.setSelectionBackground(new java.awt.Color(255, 255, 255));
            jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    jTable2MousePressed(evt);
                }
            });
            jScrollPane2.setViewportView(jTable2);

            getContentPane().add(jScrollPane2);
            jScrollPane2.setBounds(10, 80, 980, 320);

            BackButton.setBackground(new java.awt.Color(0, 204, 204));
            BackButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            BackButton.setForeground(new java.awt.Color(0, 0, 204));
            BackButton.setText("BACK");
            BackButton.setOpaque(false);
            BackButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    BackButtonMouseClicked(evt);
                }
            });
            getContentPane().add(BackButton);
            BackButton.setBounds(10, 10, 120, 40);

            LoginButton.setBackground(new java.awt.Color(0, 204, 204));
            LoginButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            LoginButton.setForeground(new java.awt.Color(0, 0, 204));
            LoginButton.setText("LAB LOGIN");
            LoginButton.setOpaque(false);
            LoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    LoginButtonMouseClicked(evt);
                }
            });
            getContentPane().add(LoginButton);
            LoginButton.setBounds(820, 10, 170, 40);

            BookButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
            BookButton.setForeground(new java.awt.Color(0, 0, 255));
            BookButton.setText("BOOK");
            BookButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    BookButtonMouseClicked(evt);
                }
            });
            getContentPane().add(BookButton);
            BookButton.setBounds(750, 455, 180, 70);

            jtfPhone.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jtfPhone.setForeground(new java.awt.Color(0, 0, 204));
            jtfPhone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jtfPhone.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 2, true));
            jtfPhone.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    jtfPhoneKeyPressed(evt);
                }
            });
            getContentPane().add(jtfPhone);
            jtfPhone.setBounds(330, 490, 290, 40);

            jtfName.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jtfName.setForeground(new java.awt.Color(0, 0, 204));
            jtfName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jtfName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 2, true));
            getContentPane().add(jtfName);
            jtfName.setBounds(330, 430, 290, 40);

            Name.setBackground(new java.awt.Color(51, 153, 255));
            Name.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            Name.setForeground(new java.awt.Color(255, 255, 255));
            Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Name.setText("FULL NAME");
            Name.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 2, true));
            Name.setOpaque(true);
            getContentPane().add(Name);
            Name.setBounds(120, 430, 190, 40);

            Phone.setBackground(new java.awt.Color(51, 153, 255));
            Phone.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            Phone.setForeground(new java.awt.Color(255, 255, 255));
            Phone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Phone.setText("PHONE NUMBER");
            Phone.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 2, true));
            Phone.setOpaque(true);
            getContentPane().add(Phone);
            Phone.setBounds(120, 490, 190, 40);

            Hint.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            Hint.setForeground(new java.awt.Color(255, 255, 255));
            Hint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            getContentPane().add(Hint);
            Hint.setBounds(310, 550, 350, 30);

            Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/finalproject/Menu.jpg"))); // NOI18N
            getContentPane().add(Menu);
            Menu.setBounds(0, 0, 1000, 650);

            pack();
            setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void LoginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginButtonMouseClicked
        // TODO add your handling code here:
        Login s;
        try {
            s = new Login();
            s.setVisible(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LabMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_LoginButtonMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int YesNo = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Exit ?", "Abort", JOptionPane.YES_NO_OPTION);
        if (YesNo == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void BackButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackButtonMouseClicked
        // TODO add your handling code here:
        if (jTable2.isVisible() == true) {
            LabMenu s;
            s = new LabMenu();
            this.dispose();
            s.setVisible(true);

        } else {
            Choice s = new Choice();
            this.setVisible(false);
            s.setVisible(true);
        }
    }//GEN-LAST:event_BackButtonMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        try {
            tableFill("4");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConsultMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jScrollPane2.setVisible(true);
        jTable2.setVisible(true);
        jtfPhone.setVisible(true);
        jtfName.setVisible(true);
        Phone.setVisible(true);
        Name.setVisible(true);
        BookButton.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        // TODO add your handling code here:
        jLabel4.setSize(920, 60);
        jLabel4.setBackground(Color.cyan);
        jLabel4.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        // TODO add your handling code here:
        jLabel4.setSize(910, 40);
        jLabel4.setBackground(new Color(51, 153, 255));
        jLabel4.setForeground(Color.white);
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        try {
            tableFill("3");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConsultMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jScrollPane2.setVisible(true);
        jTable2.setVisible(true);
        jtfPhone.setVisible(true);
        jtfName.setVisible(true);
        Phone.setVisible(true);
        Name.setVisible(true);
        BookButton.setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        // TODO add your handling code here:
        jLabel3.setSize(920, 60);
        jLabel3.setBackground(Color.cyan);
        jLabel3.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        // TODO add your handling code here:
        jLabel3.setSize(910, 40);
        jLabel3.setBackground(new Color(51, 153, 255));
        jLabel3.setForeground(Color.white);
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        try {
            tableFill("2");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConsultMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jScrollPane2.setVisible(true);
        jTable2.setVisible(true);
        jtfPhone.setVisible(true);
        jtfName.setVisible(true);
        Phone.setVisible(true);
        Name.setVisible(true);
        BookButton.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
        jLabel2.setSize(920, 60);
        jLabel2.setBackground(Color.cyan);
        jLabel2.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // TODO add your handling code here:
        jLabel2.setSize(910, 40);
        jLabel2.setBackground(new Color(51, 153, 255));
        jLabel2.setForeground(Color.white);
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        try {
            tableFill("1");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LabMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jScrollPane2.setVisible(true);
        jTable2.setVisible(true);
        jtfPhone.setVisible(true);
        jtfName.setVisible(true);
        Phone.setVisible(true);
        Name.setVisible(true);
        BookButton.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
        jLabel1.setSize(920, 60);
        jLabel1.setBackground(Color.cyan);
        jLabel1.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        // TODO add your handling code here:
        jLabel1.setSize(910, 40);
        jLabel1.setBackground(new Color(51, 153, 255));
        jLabel1.setForeground(Color.white);
    }//GEN-LAST:event_jLabel1MouseExited

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        // TODO add your handling code here:
        int row = jTable2.getSelectedRow();
        int column = jTable2.getSelectedColumn();
        if (jTable2.getModel().getValueAt(row, column).equals("X")) {
            jTable2.setValueAt("", row, column);
        } else {
            Object o = jTable2.getModel().getValueAt(row, column);
            if (o.toString().equals("")) {
                for (int i = 0; i < jTable2.getRowCount(); i++) {
                    for (int j = 1; j < jTable2.getColumnCount(); j++) {
                        if (i == row && j == column) {
                            jTable2.setValueAt("X", row, column);
                        } else {
                            if (jTable2.getModel().getValueAt(i, j).equals("Occupied") || jTable2.getModel().getValueAt(i, j).equals("Closed")) {
                                continue;
                            }
                            jTable2.setValueAt("", i, j);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jTable2MousePressed

    private void BookButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookButtonMouseClicked
        // TODO add your handling code here:

        PrintWriter write;
        boolean selected = false;
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            for (int j = 1; j < jTable2.getColumnCount(); j++) {
                if (jTable2.getModel().getValueAt(i, j).equals("X")) {
                    selected = true;
                }
            }
        }
        if (selected == true) {
            try {
                String name = jtfName.getText();
                String phone = (jtfPhone.getText());
                if (name.equals("")) {
                    Hint.setText("*Enter Your Name");
                    Hint.setVisible(true);
                } else if (phone.length() < 8) {
                    Hint.setText("*Enter 8 Digits Only");
                    Hint.setVisible(true);
                } else {
                    Hint.setVisible(false);
                    String save = "";
                    if (table.exists()) {
                        read = new Scanner(table);
                        while (read.hasNextLine()) {
                            save += "\n" + read.nextLine();
                        }

                    }
                    int YesNo = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Book Your Apointment?", "Verification", JOptionPane.YES_NO_OPTION);
                    if (YesNo == 0) {
                        Folder = new File("Labs\\");
                        if (!Folder.exists()) {
                            Folder.mkdirs();
                        }
                        write = new PrintWriter(table);
                        book = jTable2.getColumnName(jTable2.getSelectedColumn()).substring(9, 11) + jTable2.getColumnName(jTable2.getSelectedColumn()).substring(4, 6);
                        write.append(Integer.toString(jTable2.getSelectedRow()) + " " + Integer.toString(jTable2.getSelectedColumn()) + " " + jtfName.getText() + " " + jtfPhone.getText() + " " + book + save);
                        write.close();
                        this.dispose();
                        LabMenu s;
                        s = new LabMenu();
                        this.dispose();
                        s.setVisible(true);
                    }
                }
            } catch (Exception ex) {
                Hint.setText("*Enter Your PHONE NUMBER");
                Hint.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select A Date For Appointment!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BookButtonMouseClicked

    private void jtfPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPhoneKeyPressed
        // TODO add your handling code here:
        String len = jtfPhone.getText();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' && len.length() < 8 || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getKeyCode() == KeyEvent.VK_DELETE) {
            jtfPhone.setEditable(true);
            Hint.setText("");
            Hint.setVisible(false);
        } else {
            jtfPhone.setEditable(false);
            Hint.setText("*Enter 8 Digits Only");
            Hint.setVisible(true);
        }
    }//GEN-LAST:event_jtfPhoneKeyPressed

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
            java.util.logging.Logger.getLogger(LabMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LabMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LabMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LabMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LabMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton BookButton;
    private javax.swing.JLabel Hint;
    private javax.swing.JButton LoginButton;
    private javax.swing.JLabel Menu;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel Phone;
    private javax.swing.JLabel date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jtfName;
    private javax.swing.JTextField jtfPhone;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}

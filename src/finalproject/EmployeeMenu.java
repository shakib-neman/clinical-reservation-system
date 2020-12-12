/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.awt.*;
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
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
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
public class EmployeeMenu extends javax.swing.JFrame {

    /**
     * Creates new form FinalProject1
     */
    File Doctors, Passwords;
    Scanner read, read1;
    String current, st;
    Date crt;
    int filenumb;
    PrintWriter write;

    public EmployeeMenu() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public EmployeeMenu(int i) throws FileNotFoundException {
        initComponents();
        filenumb = i - 1;
        jTable1.setShowGrid(true);
        jList1.setVisible(false);
        jInternalFrame2.setVisible(false);
        jInternalFrame1.setVisible(false);
        showTime();
        tableFill(i);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    void showTime() {

        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Date d = new Date();
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
                            jTable1.setValueAt("Passed", i, 1);
                        }
                    }
                }
            }
        }).start();
    }

    void tableFill(int n) throws FileNotFoundException {
        Date d = new Date();
        crt = d;
        SimpleDateFormat crnt = new SimpleDateFormat("ddMM");
        current = crnt.format(d);
        Doctors = new File("Polyclinic//Doctor" + Integer.toString(n) + ".dat");
        if (n > 4) {
            n = n - 4;
            Doctors = new File("Labs//Lab" + n + ".dat");
        }
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            for (int j = 1; j < jTable1.getColumnCount(); j++) {
                if (jTable1.getColumnName(j).contains("Sat") || jTable1.getColumnName(j).contains("Sun")) {
                    jTable1.setValueAt("Closed", i, j);
                } else {
                    jTable1.setValueAt("", i, j);
                }
            }
        }
        if (Doctors.exists()) {
            read = new Scanner(Doctors);
            if (Doctors.canRead() == true) {
                while (read.hasNext()) {
                    int row = read.nextInt();
                    int col = read.nextInt();
                    String rest = read.nextLine();
                    String dtfile = rest.substring(rest.length() - 4);

                    int index = rest.indexOf(dtfile);
                    int sub;
                    String info = rest.substring(1, index - 1);
                    int dt = Integer.parseInt(dtfile.substring(2, 4));
                    Month month2 = Month.of(Integer.parseInt(current.substring(2)));
                    Month month1 = Month.of(Integer.parseInt(dtfile.substring(0, 2)));
                    if (month1.compareTo(month2) >= 0) {
                        if (month1.compareTo(month2) > 0) {
                            int nmbday = month2.length(true);
                            sub = nmbday - Integer.parseInt(current.substring(0, 2)) + dt;
                        } else {
                            sub = dt - Integer.parseInt(current.substring(0, 2));
                        }
                        if (sub >= 0) {
                            jTable1.setValueAt(info, row, sub + 1);
                        }
                    }
                }
            }
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

        jInternalFrame2 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        jtfchange = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        History = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component comp = super.prepareRenderer(renderer, row, col);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment( JLabel.CENTER );
                for(int j=0;j<8;j++)
                jTable1.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
                Object value = getModel().getValueAt(row, col);
                // if (getSelectedRow() == row) {
                    if(value==null){
                        comp.setForeground(Color.BLACK);
                        jTable1.setValueAt("", row, col);
                    }
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
            time = new javax.swing.JLabel();
            date = new javax.swing.JLabel();
            jMenuBar1 = new javax.swing.JMenuBar();
            Edit = new javax.swing.JMenu();
            jMenuItem1 = new javax.swing.JMenuItem();

            setLocation(new java.awt.Point(0, 0));
            setMaximumSize(new java.awt.Dimension(985, 760));
            setMinimumSize(new java.awt.Dimension(985, 510));
            setPreferredSize(new java.awt.Dimension(985, 500));
            setResizable(false);
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    formWindowClosing(evt);
                }
            });
            getContentPane().setLayout(null);

            jInternalFrame2.setClosable(true);
            jInternalFrame2.setVisible(true);
            jInternalFrame2.getContentPane().setLayout(null);

            jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel1.setText("NEW PASSWORD");
            jInternalFrame2.getContentPane().add(jLabel1);
            jLabel1.setBounds(0, 0, 244, 34);

            jtfchange.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jtfchange.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jtfchange.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    jtfchangeKeyPressed(evt);
                }
            });
            jInternalFrame2.getContentPane().add(jtfchange);
            jtfchange.setBounds(12, 43, 220, 38);

            jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jButton1.setText("CHANGE");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
            jInternalFrame2.getContentPane().add(jButton1);
            jButton1.setBounds(72, 88, 100, 36);

            getContentPane().add(jInternalFrame2);
            jInternalFrame2.setBounds(370, 160, 260, 160);

            jInternalFrame1.setClosable(true);
            jInternalFrame1.setVisible(true);
            jInternalFrame1.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
                public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                }
                public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                }
                public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                    jInternalFrame1InternalFrameClosing(evt);
                }
                public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
                }
                public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
                }
                public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
                }
                public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                }
            });

            jList1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jScrollPane1.setViewportView(jList1);

            jInternalFrame1.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

            getContentPane().add(jInternalFrame1);
            jInternalFrame1.setBounds(0, 380, 980, 360);

            History.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            History.setText("HISTORY");
            History.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    HistoryMouseClicked(evt);
                }
            });
            getContentPane().add(History);
            History.setBounds(440, 390, 110, 41);

            jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

            jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jTable1.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 20));
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
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
            jTable1.setRowHeight(57);
            jTable1.setSelectionBackground(new java.awt.Color(255, 255, 255));
            jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    jTable1MousePressed(evt);
                }
            });
            jScrollPane2.setViewportView(jTable1);

            getContentPane().add(jScrollPane2);
            jScrollPane2.setBounds(0, 60, 980, 320);

            time.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            getContentPane().add(time);
            time.setBounds(480, 10, 230, 40);

            date.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            getContentPane().add(date);
            date.setBounds(330, 10, 230, 40);

            Edit.setText("Edit");
            Edit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

            jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jMenuItem1.setText("Change Password");
            jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem1ActionPerformed(evt);
                }
            });
            Edit.add(jMenuItem1);

            jMenuBar1.add(Edit);

            setJMenuBar(jMenuBar1);

            setSize(new java.awt.Dimension(985, 500));
            setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int YesNo = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Exit ?", "Abort", JOptionPane.YES_NO_OPTION);
        if (YesNo == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void HistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryMouseClicked
        try {
            // TODO add your handling code here:
            read = new Scanner(Doctors);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EmployeeMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<String> List = new ArrayList();
        String line;
        while (read.hasNext()) {
            line = read.nextLine();
            List.add(line);
        }
        List.sort(new Comparator<String>() {
            public int compare(String str1, String str2) {
                String substr1 = str1.substring(str1.length() - 4);
                String substr2 = str2.substring(str2.length() - 4);

                return Integer.valueOf(substr2).compareTo(Integer.valueOf(substr1));
            }
        });
        DefaultListModel dm = new DefaultListModel();
        jList1.setModel(dm);
        for (int i = 0; i < List.size(); i++) {
            dm.addElement(List.get(i).substring(List.get(i).length() - 4, List.get(i).length() - 2) + " / " + List.get(i).substring(List.get(i).length() - 2) + "       " + List.get(i).substring(4, List.get(i).length() - 4));
        }
        this.setSize(985, 800);
        this.setLocationRelativeTo(null);
        jInternalFrame1.setVisible(true);
        jList1.setVisible(true);


    }//GEN-LAST:event_HistoryMouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        int column = jTable1.getSelectedColumn();
        if (!jTable1.getModel().getValueAt(row, column).equals("") && !jTable1.getModel().getValueAt(row, column).equals("Passed") && !jTable1.getModel().getValueAt(row, column).equals("Closed")) {
            String number = jTable1.getModel().getValueAt(row, column).toString().substring(jTable1.getModel().getValueAt(row, column).toString().length() - 8);
            JOptionPane.showMessageDialog(null, "Name : " + jTable1.getModel().getValueAt(row, column).toString().substring(0, jTable1.getModel().getValueAt(row, column).toString().length() - number.length() - 1) + "\nNumber : " + number, "Patient Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jTable1MousePressed

    private void jInternalFrame1InternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_jInternalFrame1InternalFrameClosing
        // TODO add your handling code here:
        this.setSize(985, 500);
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_jInternalFrame1InternalFrameClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Passwords = new File("Passwords.dat");
        if (!jtfchange.getText().equals("")) {

            String old, name, copy = "";
            try {
                read1 = new Scanner(Passwords);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EmployeeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int x = 0; x < filenumb; x++) {
                read1.nextLine();
            }
            name = read1.next();
            old = read1.next();
            try {
                read1 = new Scanner(Passwords);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EmployeeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (read1.hasNextLine()) {
                copy = copy + read1.nextLine() + "\n";
            }

            copy = copy.replaceFirst(name + " " + old, name + " " + jtfchange.getText());
            copy = copy.substring(0, copy.length() - 1);
            try {
                write = new PrintWriter(Passwords);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EmployeeMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            write.print(copy);
            write.close();
            jInternalFrame2.dispose();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        jInternalFrame2.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jtfchangeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfchangeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
            jtfchange.setEditable(false);
        } else {
            jtfchange.setEditable(true);
        }
    }//GEN-LAST:event_jtfchangeKeyPressed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Edit;
    private javax.swing.JButton History;
    private javax.swing.JLabel date;
    private javax.swing.JButton jButton1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtfchange;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksiDB;
import logic.pinjaman;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nursy
 */
public class vPinjaman extends javax.swing.JFrame {
private DefaultTableModel tabmode;
private ResultSet RsPinjaman;
private String trans;
public String id, nama;
    /**
     * Creates new form vDivisi
     */
    public vPinjaman() {
        initComponents();
        auto();
        isiTrid();
        datatable();
    }
    
    public void itemTerpilih1(){
        vDataAnggota1 Pp = new vDataAnggota1();
        Pp.plg = this;
        txtidanggota.setText(id);
        txtnama.setText(nama);
}
    
    private void auto()
    {
       try {
            String sql="select * from dt_pinjaman order by idpinjaman desc";
            RsPinjaman = koneksiDB.getRS(sql); 
            if (RsPinjaman.next()) {
                String kode = RsPinjaman.getString("idpinjaman").substring(3);
                String AN = "" + (Integer.parseInt(kode) + 1);
                String Nol = "";

                if(AN.length()==1)
                {Nol = "000";}
                else if(AN.length()==2)
                {Nol = "00";}
                else if(AN.length()==3)
                {Nol = "0";}
                else if(AN.length()==4)
                {Nol = "";}

               txtid.setText("ID-" + Nol + AN);
            } else {
               txtid.setText("ID-0001");
            }

           }catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex);
           }
    }
    
    public void emptytext() {
        txttgl.setDate(null);
        txtidanggota.setText("");
        txtnama.setText("");
        txtjmlpinjaman.setText("");
        txtbln.setText("");
        txtbunga.setText("");
        txtjmlbunga.setText("");
        txttotalpinjaman.setText("");
        txtangsuran.setText("");
        }
    
    private void isiTrid() {
        delTrid(TPinjaman);
        DefaultTableModel defGrid = (DefaultTableModel) TPinjaman.getModel();
        RsPinjaman = koneksiDB.getRS("select * from dt_pinjaman");
        try {
            while (RsPinjaman.next()) {
                String[] data = {RsPinjaman.getString("idpinjaman"),RsPinjaman.getString("tgl"),
                                 RsPinjaman.getString("idanggota"),RsPinjaman.getString("nama"),
                                 RsPinjaman.getString("jmlpinjaman"),RsPinjaman.getString("lamapinjaman"),
                                 RsPinjaman.getString("bunga"),RsPinjaman.getString("totalpinjaman"),
                                 RsPinjaman.getString("angsuran")};
                defGrid.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(vPinjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void delTrid(JTable gg) {
        DefaultTableModel defGrid = (DefaultTableModel) gg.getModel();
        int brs = gg.getRowCount();
        for(int i=0;i<brs;i++) {
            defGrid.removeRow(0);
        }
    }
    
    protected void datatable(){
        Object[] Baris ={"ID. Pinjaman","Tanggal","ID. Anggota","Nama","Jumlah","Lama Pinjam","Bunga","Total Pinjam","Angsuran"}; 
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem=txtcari.getText();

            try {
                String sql = "SELECT * FROM dt_pinjaman where idpinjaman like '%"+cariitem+"%' order by idpinjaman asc"; 
                RsPinjaman = koneksiDB.getRS(sql);
                    
                    while (RsPinjaman.next()){
                        tabmode.addRow(new Object[]{
                        RsPinjaman.getString(1),
                        RsPinjaman.getString(2),
                        RsPinjaman.getString(3),
                        RsPinjaman.getString(4),
                        RsPinjaman.getString(5),
                        RsPinjaman.getString(6),
                        RsPinjaman.getString(7),
                        RsPinjaman.getString(8),
                        RsPinjaman.getString(9)
                        });
 
                        }
                        TPinjaman.setModel(tabmode);
                } catch (Exception e){
                        JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
                    }
    }
    
    void bunga(){
        int a1=Integer.parseInt(txtjmlpinjaman.getText());
        int b1=Integer.parseInt(txtbunga.getText());
        int total=a1*b1/100;
        txtjmlbunga.setText(Integer.toString(total));
    }
    
    void totalpinjaman(){
        int a2=Integer.parseInt(txtjmlpinjaman.getText());
        int b2=Integer.parseInt(txtjmlbunga.getText());
        int totala=a2+b2;
        txttotalpinjaman.setText(Integer.toString(totala));
    }
    
    void angsuran(){
        int a3=Integer.parseInt(txttotalpinjaman.getText());
        int b3=Integer.parseInt(txtbln.getText());
        int totalaa=a3/b3;
        txtangsuran.setText(Integer.toString(totalaa));
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TPinjaman = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txtidanggota = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        txtjmlpinjaman = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtbln = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txtjmlbunga = new javax.swing.JTextField();
        txtangsuran = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtbunga = new javax.swing.JTextField();
        txttotalpinjaman = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txttgl = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ACTION :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 30));

        jPanel5.setBackground(new java.awt.Color(0, 153, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 153)));

        jButton2.setBackground(new java.awt.Color(153, 255, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jButton2.setText("ADD");
        jButton2.setAlignmentY(0.0F);
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(153, 255, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        jButton3.setText("EDIT");
        jButton3.setAlignmentY(0.0F);
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(153, 255, 153));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del.png"))); // NOI18N
        jButton4.setText("DELETE");
        jButton4.setAlignmentY(0.0F);
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(153, 255, 153));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        jButton5.setText("CANCEL");
        jButton5.setAlignmentY(0.0F);
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(153, 255, 153));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        jButton7.setText("Cetak");
        jButton7.setAlignmentY(0.0F);
        jButton7.setBorder(null);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 250));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 120, 510));

        jPanel3.setBackground(new java.awt.Color(0, 153, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TRANSAKSI - PINJAMAN");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 80));

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel4.setForeground(new java.awt.Color(0, 153, 0));
        jPanel4.setToolTipText("");

        TPinjaman.setModel(new javax.swing.table.DefaultTableModel(
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
        TPinjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TPinjamanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TPinjaman);

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cari.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 940, 260));

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(txtidanggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 120, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel4.setText("bulan");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, 20));

        txtnama.setEditable(false);
        jPanel6.add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 150, -1));
        jPanel6.add(txtjmlpinjaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 150, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nama Anggota");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Jumlah Pinjaman");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Lama Pinjaman");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 20));
        jPanel6.add(txtbln, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 50, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("ID Anggota");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 20));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cari.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 30, 20));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 330, 160));

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtjmlbunga.setEditable(false);
        txtjmlbunga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjmlbungaActionPerformed(evt);
            }
        });
        jPanel7.add(txtjmlbunga, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 110, -1));

        txtangsuran.setEditable(false);
        jPanel7.add(txtangsuran, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 160, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("%");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 10, 20, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Total Pinjaman");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Jumlah Angsuran");
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 20));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Bunga");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 20));

        txtbunga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbungaKeyReleased(evt);
            }
        });
        jPanel7.add(txtbunga, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 30, -1));

        txttotalpinjaman.setEditable(false);
        jPanel7.add(txttotalpinjaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 160, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 530, 160));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1080, 10));

        jPanel9.setBackground(new java.awt.Color(204, 255, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("ID Pinjaman");
        jPanel9.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 20));

        txtid.setEditable(false);
        jPanel9.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 110, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Tanggal Pinjaman");
        jPanel9.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 10, 110, 20));
        jPanel9.add(txttgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 160, -1));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 940, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtjmlbungaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjmlbungaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjmlbungaActionPerformed

    private void txtbungaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbungaKeyReleased
        bunga();
        totalpinjaman();
        angsuran();
    }//GEN-LAST:event_txtbungaKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String tgl = dateFormat.format(txttgl.getDate());
        
        trans="tambah";
        String[] plg={txtid.getText(),tgl,txtidanggota.getText(),txtnama.getText(),txtjmlpinjaman.getText(),
                      txtbln.getText(),txtbunga.getText(),txttotalpinjaman.getText(),
                      txtangsuran.getText()};
     if (pinjaman.updatepinjaman(trans,plg)>0){
                javax.swing.JOptionPane.showMessageDialog(this, "Data Pinjaman Telah Tersimpan");
        emptytext();
        isiTrid();
        }
     else{javax.swing.JOptionPane.showMessageDialog(this,
                        "Terjadi kesalahan, Silahkan coba lagi", "Kesalahan",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
    
        }
     auto();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String tgl = dateFormat.format(txttgl.getDate());
        
        trans="ubah";
        String[] plg={txtid.getText(),tgl,txtidanggota.getText(),txtnama.getText(),txtjmlpinjaman.getText(),
                      txtbln.getText(),txtbunga.getText(),txttotalpinjaman.getText(),
                      txtangsuran.getText()};
     if (pinjaman.updatepinjaman(trans,plg)>0){
                javax.swing.JOptionPane.showMessageDialog(this, "Data Pinjaman Telah Diubah");
        emptytext();
        isiTrid();
        }
     else{javax.swing.JOptionPane.showMessageDialog(this,
                        "Terjadi kesalahan, Silahkan coba lagi", "Kesalahan",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
    
        }
     auto();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        trans = "hapus";
        String[] plg = {txtid.getText()};
        if(javax.swing.JOptionPane.showConfirmDialog(this,
            "Anda yakin akan menghapus data ini ?", "Hati-hati !",
            javax.swing.JOptionPane.YES_NO_OPTION)==0) {
        if(pinjaman.updatepinjaman(trans, plg)>0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Data Pinjaman Telah Terhapus");
            emptytext();
            isiTrid();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Terjadi kesalahan, Silahkan coba lagi", "Kesalahan",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
            auto();
      }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        emptytext();
        auto();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        
    }//GEN-LAST:event_txtcariActionPerformed

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            datatable();
            }
    }//GEN-LAST:event_txtcariKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        datatable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TPinjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TPinjamanMouseClicked
        try {
        DefaultTableModel model = (DefaultTableModel) TPinjaman.getModel();
        int baris= TPinjaman.getSelectedRow();
        Date date = new SimpleDateFormat("dd MMMM yyyy").parse((String)model.getValueAt(baris, 1));
        if (pinjaman.caripinjaman(TPinjaman.getValueAt
                    (TPinjaman.getSelectedRow(),0).toString())==true) {
            txtid.setText(pinjaman.getIdpinjaman());
            txttgl.setDate(date);
            txtidanggota.setText(pinjaman.getIdanggota());
            txtnama.setText(pinjaman.getNama());
            txtjmlpinjaman.setText(pinjaman.getJumlahpinjaman());
            txtbln.setText(pinjaman.getLamapinjam());
            txtbunga.setText(pinjaman.getBunga());
            txttotalpinjaman.setText(pinjaman.getTotalpinjaman());
            txtangsuran.setText(pinjaman.getAngsuran());
        }
    } catch (ParseException ex) {
        Logger.getLogger(vPinjaman.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_TPinjamanMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        vDataAnggota1 Pp = new vDataAnggota1();
        Pp.plg = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try{
            String Logo = new File("src\\images\\logo fix.png").getAbsolutePath();
            String NamaFile = "./src/report/TransaksiPinjaman.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kk_7utama","root","");
            HashMap param = new HashMap();
            //Mengambil parameter
            param.put("gambar", Logo);
            param.put("id",txtcari.getText());

            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
            JasperViewer.viewReport(JPrint,false);
        }catch(Exception ex){
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Data tidak dapat di cetak","cetak data",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(vPinjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vPinjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vPinjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vPinjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vPinjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TPinjaman;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtangsuran;
    private javax.swing.JTextField txtbln;
    private javax.swing.JTextField txtbunga;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtidanggota;
    private javax.swing.JTextField txtjmlbunga;
    private javax.swing.JTextField txtjmlpinjaman;
    private javax.swing.JTextField txtnama;
    private com.toedter.calendar.JDateChooser txttgl;
    private javax.swing.JTextField txttotalpinjaman;
    // End of variables declaration//GEN-END:variables
}

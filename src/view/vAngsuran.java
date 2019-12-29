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
import logic.angsuran;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nursy
 */
public class vAngsuran extends javax.swing.JFrame {
private DefaultTableModel tabmode;
private ResultSet RsTransaksi;
private String trans;
public String idpinjaman, tglpinjaman,totalpinjaman,lamapinjaman,idanggota,nama,angsuran1;
    /**
     * Creates new form vDivisi
     */
    public vAngsuran() {
        initComponents();
        auto();
        datatable();
        isiTrid();
    }
    
    void jmlangsuran(){
        int a2=Integer.parseInt(txtangsuranke.getText());
        int b2=Integer.parseInt(txtangsuran.getText());
        int total=a2*b2;
        txtjmlangsuran.setText(Integer.toString(total));
    }
    
    void totalangsuran(){
        int a3=Integer.parseInt(txttotalpinjaman.getText());
        int b3=Integer.parseInt(txtjmlangsuran.getText());
        int total=a3-b3;
        txttotal.setText(Integer.toString(total));
    }
    
    void sisaangsuran(){
        int a4=Integer.parseInt(txtlamapinjaman.getText());
        int b4=Integer.parseInt(txtangsuranke.getText());
        int total=a4-b4;
        txtsisa.setText(Integer.toString(total));
    }
    
    public void itemTerpilih(){
        vDataPinjaman Pp = new vDataPinjaman();
        Pp.plg = this;
        txtidpinjaman.setText(idpinjaman);
        txttglpinjaman.setText(tglpinjaman);
        txttotalpinjaman.setText(totalpinjaman);
        txtlamapinjaman.setText(lamapinjaman);
        txtidanggota.setText(idanggota);
        txtnama.setText(nama);
        txtangsuran.setText(angsuran1);
    }
    
    private void auto()
    {
       try {
            String sql="select * from dt_angsuran order by idangsuran desc";
            RsTransaksi = koneksiDB.getRS(sql); 
            if (RsTransaksi.next()) {
                String kode = RsTransaksi.getString("idangsuran").substring(3);
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

               txtidangsuran.setText("ID-" + Nol + AN);
            } else {
               txtidangsuran.setText("ID-0001");
            }

           }catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex);
           }
    }
    
    public void emptytext() {
        txttgl.setDate(null);
        txtidpinjaman.setText("");
        txttglpinjaman.setText("");
        txttotalpinjaman.setText("");
        txtlamapinjaman.setText("");
        txtidanggota.setText("");
        txtnama.setText("");
        txtangsuranke.setText("");
        txtangsuran.setText("");
        txtjmlangsuran.setText("");
        txttotal.setText("");
        txtsisa.setText("");
        }
    
    private void isiTrid() {
        delTrid(TAngsuran);
        DefaultTableModel defGrid = (DefaultTableModel) TAngsuran.getModel();
        RsTransaksi = koneksiDB.getRS("select * from dt_angsuran");
        try {
            while (RsTransaksi.next()) {
                String[] data = {RsTransaksi.getString("idangsuran"),RsTransaksi.getString("tgl"),
                                 RsTransaksi.getString("idpinjaman"),RsTransaksi.getString("totalpinjaman"),
                                 RsTransaksi.getString("lamapinjaman"),RsTransaksi.getString("idanggota"),RsTransaksi.getString("tglpinjaman"),
                                 RsTransaksi.getString("nama"),RsTransaksi.getString("angsuranke"),
                                 RsTransaksi.getString("angsuran"),RsTransaksi.getString("jmlangsuran"),
                                 RsTransaksi.getString("totalangsuran"),RsTransaksi.getString("sisaangsuran")};
                defGrid.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(vAnggota.class.getName()).log(Level.SEVERE, null, ex);
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
        Object[] Baris ={"ID. Angsuran","Tanggal","ID. Pinjaman","Tgl. Pinjam","Total Pinjaman","Lama Pinjaman","ID. Anggota","Nama","Angsuran Ke","Angsuran","Total Angsuran","Sisa Hutang","Sisa Angsuran"}; 
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem=txtcari.getText();

            try {
                String sql = "SELECT * FROM dt_angsuran where idangsuran like '%"+cariitem+"%' or nama like"
                          + "'%"+cariitem+"%' order by nama asc"; 
                RsTransaksi = koneksiDB.getRS(sql);
                    
                    while (RsTransaksi.next()){
                        tabmode.addRow(new Object[]{
                        RsTransaksi.getString(1),
                        RsTransaksi.getString(2),
                        RsTransaksi.getString(3),
                        RsTransaksi.getString(4),
                        RsTransaksi.getString(5),
                        RsTransaksi.getString(6),
                        RsTransaksi.getString(7),
                        RsTransaksi.getString(8),
                        RsTransaksi.getString(9),
                        RsTransaksi.getString(10),
                        RsTransaksi.getString(11),
                        RsTransaksi.getString(12),
                        RsTransaksi.getString(13)
                        });
 
                        }
                        TAngsuran.setModel(tabmode);
                } catch (Exception e){
                        JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtcari = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TAngsuran = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtidanggota = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttglpinjaman = new javax.swing.JTextField();
        txtlamapinjaman = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txttotalpinjaman = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtidpinjaman = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtsisa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtjmlangsuran = new javax.swing.JTextField();
        txtangsuranke = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtangsuran = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtidangsuran = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
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

        jButton8.setBackground(new java.awt.Color(153, 255, 153));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        jButton8.setText("Cetak");
        jButton8.setAlignmentY(0.0F);
        jButton8.setBorder(null);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 240));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 120, 570));

        jPanel3.setBackground(new java.awt.Color(0, 153, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TRANSAKSI - ANGSURAN");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 80));

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel4.setForeground(new java.awt.Color(0, 153, 0));
        jPanel4.setToolTipText("");

        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cari.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        TAngsuran.setAutoCreateRowSorter(true);
        TAngsuran.setModel(new javax.swing.table.DefaultTableModel(
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
        TAngsuran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TAngsuranMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TAngsuran);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 835, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 1010, 270));

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("ID Pinjaman");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 20));

        txtidanggota.setEditable(false);
        jPanel6.add(txtidanggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 150, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Nama Anggota");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 20));

        txttglpinjaman.setEditable(false);
        jPanel6.add(txttglpinjaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 150, -1));

        txtlamapinjaman.setEditable(false);
        jPanel6.add(txtlamapinjaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 40, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Tanggal Pinjaman");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Total Pinjaman");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Lama Pinjaman");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, 20));

        txttotalpinjaman.setEditable(false);
        jPanel6.add(txttotalpinjaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 150, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel14.setText("bulan");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, 20));
        jPanel6.add(txtidpinjaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 110, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("ID Anggota");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 20));

        txtnama.setEditable(false);
        jPanel6.add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 150, 20));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cari.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 30, 20));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 350, 220));

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Sisa Angsuran");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 20));

        txtsisa.setEditable(false);
        jPanel7.add(txtsisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 60, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Total Angsuran (dibayarkan)");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel11.setText("bulan");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, 20));

        txtjmlangsuran.setEditable(false);
        jPanel7.add(txtjmlangsuran, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 160, -1));

        txtangsuranke.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtangsurankeKeyReleased(evt);
            }
        });
        jPanel7.add(txtangsuranke, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 60, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Angsuran Ke-");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 20));

        txttotal.setEditable(false);
        jPanel7.add(txttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 160, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Sisa Hutang");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 20));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Angsuran");
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 20));

        txtangsuran.setEditable(false);
        jPanel7.add(txtangsuran, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 160, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 600, 220));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1160, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1160, 10));

        jPanel9.setBackground(new java.awt.Color(204, 255, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("ID Angsuran");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 20));
        jPanel9.add(txtidangsuran, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 110, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Tanggal Angsuran");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 110, 20));
        jPanel9.add(txttgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 160, -1));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 1010, 40));

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String tgl = dateFormat.format(txttgl.getDate());
        trans="tambah";
        String[] plg={txtidangsuran.getText(),tgl,txtidpinjaman.getText(),txttglpinjaman.getText(),txttotalpinjaman.getText(),txtlamapinjaman.getText(),txtidanggota.getText(),
                      txtnama.getText(),txtangsuranke.getText(),txtangsuran.getText(),txtjmlangsuran.getText(),
                      txttotal.getText(),txtsisa.getText()};
     if (angsuran.updatetransaksi(trans,plg)>0){
                javax.swing.JOptionPane.showMessageDialog(this, "Data Angsuran Telah Tersimpan");
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
        String[] plg={txtidangsuran.getText(),tgl,txtidpinjaman.getText(),txttglpinjaman.getText(),txttotalpinjaman.getText(),txtlamapinjaman.getText(),txtidanggota.getText(),
                      txtnama.getText(),txtangsuranke.getText(),txtangsuran.getText(),txtjmlangsuran.getText(),
                      txttotal.getText(),txtsisa.getText()};
     if (angsuran.updatetransaksi(trans,plg)>0){
                javax.swing.JOptionPane.showMessageDialog(this, "Data Angsuran Telah Diubah");
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
        String[] plg = {txtidangsuran.getText()};
        if(javax.swing.JOptionPane.showConfirmDialog(this,
            "Anda yakin akan menghapus data ini ?", "Hati-hati !",
            javax.swing.JOptionPane.YES_NO_OPTION)==0) {
        if(angsuran.updatetransaksi(trans, plg)>0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Data Angsuran Telah Terhapus");
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

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            datatable();
        }
    }//GEN-LAST:event_txtcariKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        datatable();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void TAngsuranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TAngsuranMouseClicked
        try {
            DefaultTableModel model = (DefaultTableModel) TAngsuran.getModel();
            int baris= TAngsuran.getSelectedRow();
            Date date = new SimpleDateFormat("dd MMMM yyyy").parse((String)model.getValueAt(baris, 1));
            if (angsuran.cariangsuran(TAngsuran.getValueAt
                (TAngsuran.getSelectedRow(),0).toString())==true) {
            txtidangsuran.setText(angsuran.getIdangsuran());
            txttgl.setDate(date);
            txtidpinjaman.setText(angsuran.getIdpinjaman());
            txttglpinjaman.setText(angsuran.getTglpinjaman());
            txttotalpinjaman.setText(angsuran.getTotalpinjaman());
            txtlamapinjaman.setText(angsuran.getLamapinjaman());
            txtidanggota.setText(angsuran.getIdanggota());
            txtnama.setText(angsuran.getNama());
            txtangsuranke.setText(angsuran.getAngsuranke());
            txtangsuran.setText(angsuran.getAngsuran());
            txtjmlangsuran.setText(angsuran.getJmlangsursn());
            txttotal.setText(angsuran.getTotalangsuran());
            txtsisa.setText(angsuran.getSisangasuran());
        }
        } catch (ParseException ex) {
            Logger.getLogger(vAngsuran.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TAngsuranMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        vDataPinjaman Pp = new vDataPinjaman();
        Pp.plg = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtangsurankeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtangsurankeKeyReleased
        jmlangsuran();
        totalangsuran();
        sisaangsuran();
    }//GEN-LAST:event_txtangsurankeKeyReleased

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try{
            String Logo = new File("src\\images\\logo fix.png").getAbsolutePath();
            String NamaFile = "./src/report/TransaksiAngsuran.jasper";
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
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(vAngsuran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vAngsuran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vAngsuran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vAngsuran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vAngsuran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TAngsuran;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtangsuran;
    private javax.swing.JTextField txtangsuranke;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtidanggota;
    private javax.swing.JTextField txtidangsuran;
    private javax.swing.JTextField txtidpinjaman;
    private javax.swing.JTextField txtjmlangsuran;
    private javax.swing.JTextField txtlamapinjaman;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtsisa;
    private com.toedter.calendar.JDateChooser txttgl;
    private javax.swing.JTextField txttglpinjaman;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttotalpinjaman;
    // End of variables declaration//GEN-END:variables
}

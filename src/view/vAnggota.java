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
import logic.anggota;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nursy
 */
public class vAnggota extends javax.swing.JFrame {
private ResultSet RsKoprasi;
private DefaultTableModel tabmode; 
private String trans;
    /**
     * Creates new form vDivisi
     */
    public vAnggota() {
        initComponents();
        Combobox();
        isiGrid();
        auto();
        datatable();
    }
    
    public void Combobox(){
        { //db.koneksiDatabase();
        try {
            String sql = "select * from  dt_divisi order by nama asc";
            //Statement st = db.getKoneksi().createStatement();
            RsKoprasi = koneksiDB.getRS(sql);
             
            while (RsKoprasi.next()) {                
                cbdivisi.addItem(RsKoprasi.getString("nama"));
            }
             
            RsKoprasi.last();
            int jumlahdata = RsKoprasi.getRow();
            RsKoprasi.first();
             
        } catch (SQLException e) {
        }
      }
    }
    
    private void auto()
    {
       try {
            String sql="select * from  dt_anggota order by id desc";
            RsKoprasi = koneksiDB.getRS(sql); 
            if (RsKoprasi.next()) {
                String kode = RsKoprasi.getString("id").substring(3);
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
        txtnama.setText("");
        txttempatlahir.setText("");
        txttgllahir.setDate(null);
        txtalamat.setText("");
        txtnotlp.setText("");
        txtsimpananpokok.setText("");
        cbdivisi.setSelectedItem("--Pilih--");
        txttglmasuk.setDate(null);
    }
    
    private void isiGrid() {
        delTrid(tblAnggota);
        DefaultTableModel defGrid = (DefaultTableModel) tblAnggota.getModel();
        RsKoprasi = koneksiDB.getRS("select * from dt_anggota");
        try {
            while (RsKoprasi.next()) {
                String[] data = {RsKoprasi.getString("id"),RsKoprasi.getString("nama"),
                                 RsKoprasi.getString("tempatlahir"),RsKoprasi.getString("tgllahir"),
                                 RsKoprasi.getString("alamat"),RsKoprasi.getString("notlp"),
                                 RsKoprasi.getString("divisi"),RsKoprasi.getString("simpananpokok"),
                                 RsKoprasi.getString("tglmasuk")};
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
        Object[] Baris ={"ID","Nama","Tempat Lahir","Tgl. Lahir","Alamat","No. Telepon","Divisi","Simpanan Pokok","Tgl. Masuk"}; 
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem=txtcari.getText();

            try {
                String sql = "SELECT * FROM dt_anggota where id like '%"+cariitem+"%' order by id asc"; 
                RsKoprasi = koneksiDB.getRS(sql);
                    
                    while (RsKoprasi.next()){
                        tabmode.addRow(new Object[]{
                        RsKoprasi.getString(1),
                        RsKoprasi.getString(2),
                        RsKoprasi.getString(3),
                        RsKoprasi.getString(4),
                        RsKoprasi.getString(5),
                        RsKoprasi.getString(6),
                        RsKoprasi.getString(7),
                        RsKoprasi.getString(8),
                        RsKoprasi.getString(9)
                        });
 
                        }
                        tblAnggota.setModel(tabmode);
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
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAnggota = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        bcari = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        txttgllahir = new com.toedter.calendar.JDateChooser();
        txttempatlahir = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtnotlp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtsimpananpokok = new javax.swing.JTextField();
        cbdivisi = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txttglmasuk = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();

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

        jButton6.setBackground(new java.awt.Color(153, 255, 153));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        jButton6.setText("Cetak");
        jButton6.setAlignmentY(0.0F);
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 240));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 120, 510));

        jPanel3.setBackground(new java.awt.Color(0, 153, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DATA MASTER - ANGGOTA");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 80));

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel4.setForeground(new java.awt.Color(0, 153, 0));
        jPanel4.setToolTipText("");

        tblAnggota.setModel(new javax.swing.table.DefaultTableModel(
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
        tblAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAnggotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAnggota);

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

        bcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cari.png"))); // NOI18N
        bcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcariActionPerformed(evt);
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
                        .addComponent(bcari, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 17, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtcari)
                    .addComponent(bcari, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 940, 230));

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("ID Anggota");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 20));

        txtid.setEditable(false);
        jPanel6.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 80, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Alamat");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 20));
        jPanel6.add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 150, -1));
        jPanel6.add(txttgllahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 150, -1));
        jPanel6.add(txttempatlahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 150, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nama");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Tempat Lahir");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 20));

        txtalamat.setColumns(20);
        txtalamat.setRows(5);
        jScrollPane2.setViewportView(txtalamat);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 170, 100));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Tanggal Lahir");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 20));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 330, 250));

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel7.add(txtnotlp, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 160, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Tanggal Masuk");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, 20));
        jPanel7.add(txtsimpananpokok, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 160, -1));

        cbdivisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--" }));
        jPanel7.add(cbdivisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 160, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("No. Telp");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 20));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Divisi");
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, 20));
        jPanel7.add(txttglmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 160, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Simpanan Pokok");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 20));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 530, 250));

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String combo = cbdivisi.getModel().getSelectedItem().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMM yyyy");
        String tgllahir = dateFormat.format(txttgllahir.getDate());
        String tglmasuk = dateFormat.format(txttglmasuk.getDate());
        trans="tambah";
        String[] plg={txtid.getText(),txtnama.getText(),txttempatlahir.getText(),tgllahir,txtalamat.getText(),
            txtnotlp.getText(),combo,txtsimpananpokok.getText(),tglmasuk};
     if (anggota.updateanggota(trans,plg)>0){
                javax.swing.JOptionPane.showMessageDialog(this, "Data Anggota Telah Tersimpan");
        emptytext();
        isiGrid();
        }
     else{javax.swing.JOptionPane.showMessageDialog(this,
                        "Terjadi kesalahan, Silahkan coba lagi", "Kesalahan",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
    
        }
        auto();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String combo = cbdivisi.getModel().getSelectedItem().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMM yyyy");
        String tgllahir = dateFormat.format(txttgllahir.getDate());
        String tglmasuk = dateFormat.format(txttglmasuk.getDate());
        trans="ubah";
        String[] plg={txtid.getText(),txtnama.getText(),txttempatlahir.getText(),tgllahir,txtalamat.getText(),
            txtnotlp.getText(),combo,txtsimpananpokok.getText(),tglmasuk};
     if (anggota.updateanggota(trans,plg)>0){
                javax.swing.JOptionPane.showMessageDialog(this, "Data Anggota Telah Diubah");
        emptytext();
        isiGrid();
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
        if(anggota.updateanggota(trans, plg)>0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Data Anggota Telah Terhapus");
            emptytext();
            isiGrid();
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

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        datatable();
    }//GEN-LAST:event_bcariActionPerformed

    private void tblAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAnggotaMouseClicked
        try {
        DefaultTableModel model = (DefaultTableModel) tblAnggota.getModel();
        int baris= tblAnggota.getSelectedRow();
        Date date = new SimpleDateFormat("dd MMMMM yyyy").parse((String)model.getValueAt(baris, 3));
        Date date1 = new SimpleDateFormat("dd MMMMM yyyy").parse((String)model.getValueAt(baris, 8));
        String s2 = tblAnggota.getValueAt(baris, 6).toString();
        if (anggota.carianggota(tblAnggota.getValueAt
            (tblAnggota.getSelectedRow(),0).toString())==true) {
            txtid.setText(anggota.getId());
            txtnama.setText(anggota.getNama());
            txttempatlahir.setText(anggota.getTempatlahir());
            txttgllahir.setDate(date);
            txtalamat.setText(anggota.getAlamat());
            txtnotlp.setText(anggota.getNotlp());
            cbdivisi.setSelectedItem(s2);
            txtsimpananpokok.setText(anggota.getSimpananpokok());
            txttglmasuk.setDate(date1);
        }
    } catch (ParseException ex) {
        Logger.getLogger(vAnggota.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_tblAnggotaMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try{
            String Logo = new File("src\\images\\logo fix.png").getAbsolutePath();
            String NamaFile = "./src/report/CetakKartu.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kk_7utama","root","");
            HashMap param = new HashMap();
            //Mengambil parameter
            param.put("gambar",Logo);
            param.put("tgl",txtcari.getText());

            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
            JasperViewer.viewReport(JPrint,false);
        }catch(Exception ex){
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Data tidak dapat di cetak","cetak data",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(vAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vAnggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bcari;
    private javax.swing.JComboBox<String> cbdivisi;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAnggota;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnotlp;
    private javax.swing.JTextField txtsimpananpokok;
    private javax.swing.JTextField txttempatlahir;
    private com.toedter.calendar.JDateChooser txttgllahir;
    private com.toedter.calendar.JDateChooser txttglmasuk;
    // End of variables declaration//GEN-END:variables
}

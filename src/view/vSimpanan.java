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
import javax.swing.table.TableModel;
import koneksi.koneksiDB;
import logic.simpanan;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nursy
 */
public class vSimpanan extends javax.swing.JFrame {
private DefaultTableModel tabmode;
private ResultSet RsSimpanan;
private String trans;
public String id, nama;
    /**
     * Creates new form vDivisi
     */
    public vSimpanan() {
        initComponents();
        auto();
        isiTrid();
        totalsaldo();
        datatable();
    }
    
    public void itemTerpilih(){
        vDataAnggota Pp = new vDataAnggota();
        Pp.plg = this;
        txtidanggota.setText(id);
        txtnama.setText(nama);
}
    
    private void auto()
    {
       try {
            String sql="select * from dt_simpanan order by idsimpanan desc";
            RsSimpanan = koneksiDB.getRS(sql); 
            if (RsSimpanan.next()) {
                String kode = RsSimpanan.getString("idsimpanan").substring(3);
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

               txtidsimpanan.setText("ID-" + Nol + AN);
            } else {
               txtidsimpanan.setText("ID-0001");
            }

           }catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex);
           }
    }
    
    public void emptytext() {
        txttgl.setDate(null);
        txtidanggota.setText("");
        txtnama.setText("");
        txtsukarela.setText("");
        txtsaldo.setText("0");
        
    }
    
    private void isiTrid() {
        delTrid(TSimpanan);
        DefaultTableModel defGrid = (DefaultTableModel) TSimpanan.getModel();
        RsSimpanan = koneksiDB.getRS("select * from dt_simpanan");
        try {
            while (RsSimpanan.next()) {
                String[] data = {RsSimpanan.getString("idsimpanan"),RsSimpanan.getString("tgl"),
                                 RsSimpanan.getString("idanggota"),RsSimpanan.getString("nama"),
                                 RsSimpanan.getString("simpananwajib"),RsSimpanan.getString("simpanansukarela")};
                defGrid.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(vSimpanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void delTrid(JTable gg) {
        DefaultTableModel defGrid = (DefaultTableModel) gg.getModel();
        int brs = gg.getRowCount();
        for(int i=0;i<brs;i++) {
            defGrid.removeRow(0);
        }
    }
    
    private void totalsaldo() {
        datatable();
        int a = TSimpanan.getRowCount();
        int saldo = 0;
        int setoran;
        int setoran1;
        TableModel tabelModel;
        tabelModel = TSimpanan.getModel();
        for (int i=0; i < a; i++){
        //jumlahBarang = Integer.parseInt(tabelModel.getValueAt(i, 0).toString());
        setoran = Integer.parseInt(tabelModel.getValueAt(i, 4).toString());
        setoran1 = Integer.parseInt(tabelModel.getValueAt(i, 5).toString());
        saldo = saldo + (setoran) + (setoran1);
        }
        txttotalsaldo.setText(String.valueOf(saldo));
    }
    
    private void hitungsaldo() {
        datatable();
        int a = TSimpanan.getRowCount();
        int saldo = 0;
        int setoran;
        int setoran1;
        TableModel tabelModel;
        tabelModel = TSimpanan.getModel();
        for (int i=0; i < a; i++){
        //jumlahBarang = Integer.parseInt(tabelModel.getValueAt(i, 0).toString());
        setoran = Integer.parseInt(tabelModel.getValueAt(i, 4).toString());
        setoran1 = Integer.parseInt(tabelModel.getValueAt(i, 5).toString());
        saldo = saldo + (setoran) + (setoran1);
        }
        txtsaldo.setText(String.valueOf(saldo));
    }
    
    protected void datatable(){
        Object[] Baris ={"ID. Simpanan","Tanggal","ID. Anggota","Nama","Simpanan Wajib","Simpanan Sukarela"}; 
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem=txtcari.getText();

            try {
                String sql = "SELECT * FROM dt_simpanan where idanggota like '%"+cariitem+"%' or idsimpanan like"
                                 + "'%"+cariitem+"%' order by idanggota asc";  
                RsSimpanan = koneksiDB.getRS(sql);
                    
                    while (RsSimpanan.next()){
                        tabmode.addRow(new Object[]{
                        RsSimpanan.getString(1),
                        RsSimpanan.getString(2),
                        RsSimpanan.getString(3),
                        RsSimpanan.getString(4),
                        RsSimpanan.getString(5),
                        RsSimpanan.getString(6)
                        });
 
                    }
                TSimpanan.setModel(tabmode);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        TSimpanan = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        bcari = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txtidanggota = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        txtwajib = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtsukarela = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txttotalsaldo = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtsaldo = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtidsimpanan = new javax.swing.JTextField();
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
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 260));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 120, 510));

        jPanel3.setBackground(new java.awt.Color(0, 153, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TRANSAKSI - SIMPANAN");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 80));

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel4.setForeground(new java.awt.Color(0, 153, 0));
        jPanel4.setToolTipText("");

        TSimpanan.setModel(new javax.swing.table.DefaultTableModel(
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
        TSimpanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TSimpananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TSimpanan);

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bcari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 780, 260));

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(txtidanggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 120, -1));

        txtnama.setEditable(false);
        jPanel6.add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 150, -1));

        txtwajib.setEditable(false);
        txtwajib.setText("50000");
        jPanel6.add(txtwajib, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 150, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nama Anggota");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Simpanan Wajib");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Simpanan Sukarela");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 20));
        jPanel6.add(txtsukarela, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 150, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cari.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 30, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("ID Anggota");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 20));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 330, 160));

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Total Saldo Simpanan");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 20));

        txttotalsaldo.setEditable(false);
        txttotalsaldo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txttotalsaldo.setForeground(new java.awt.Color(0, 153, 0));
        txttotalsaldo.setText("0");
        jPanel7.add(txttotalsaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 240, 40));

        jButton7.setBackground(new java.awt.Color(0, 153, 0));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hitung.png"))); // NOI18N
        jButton7.setText("Hitung Saldo");
        jButton7.setAlignmentY(0.0F);
        jButton7.setBorder(null);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 100, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Rp.");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, 30));

        txtsaldo.setEditable(false);
        txtsaldo.setText("0");
        jPanel7.add(txtsaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 120, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 430, 160));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 920, 10));

        jPanel9.setBackground(new java.awt.Color(204, 255, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("ID Simpanan");
        jPanel9.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 20));

        txtidsimpanan.setEditable(false);
        jPanel9.add(txtidsimpanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 110, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Tanggal Simpanan");
        jPanel9.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 110, 20));
        jPanel9.add(txttgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 160, -1));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 780, 40));

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMM yyyy");
        String tgl = dateFormat.format(txttgl.getDate());
        trans="tambah";
        String[] plg={txtidsimpanan.getText(),tgl,txtidanggota.getText(),txtnama.getText(),txtwajib.getText(),txtsukarela.getText()};
     if (simpanan.updatesimpanan(trans,plg)>0){
                javax.swing.JOptionPane.showMessageDialog(this, "Data Simpanan Telah Tersimpan");
        emptytext();
        isiTrid();
        }
     else{javax.swing.JOptionPane.showMessageDialog(this,
                        "Terjadi kesalahan, Silahkan coba lagi", "Kesalahan",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
    
        }
        auto();
        totalsaldo();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMM yyyy");
        String tgl = dateFormat.format(txttgl.getDate());
        trans="ubah";
        String[] plg={txtidsimpanan.getText(),tgl,txtidanggota.getText(),txtnama.getText(),txtwajib.getText(),txtsukarela.getText()};
     if (simpanan.updatesimpanan(trans,plg)>0){
                javax.swing.JOptionPane.showMessageDialog(this, "Data Simpanan Telah Diubah");
        emptytext();
        isiTrid();
        }
     else{javax.swing.JOptionPane.showMessageDialog(this,
                        "Terjadi kesalahan, Silahkan coba lagi", "Kesalahan",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
    
        }
        auto();
        totalsaldo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        trans = "hapus";
        String[] plg = {txtidsimpanan.getText()};
        if(javax.swing.JOptionPane.showConfirmDialog(this,
            "Anda yakin akan menghapus data ini ?", "Hati-hati !",
            javax.swing.JOptionPane.YES_NO_OPTION)==0) {
        if(simpanan.updatesimpanan(trans, plg)>0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Data Simpanan Telah Terhapus");
            emptytext();
            isiTrid();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Terjadi kesalahan, Silahkan coba lagi", "Kesalahan",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
            auto();
            totalsaldo();
      }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        emptytext();
        auto();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void TSimpananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TSimpananMouseClicked
        try {
        DefaultTableModel model = (DefaultTableModel) TSimpanan.getModel();
        int baris= TSimpanan.getSelectedRow();
        Date date = new SimpleDateFormat("dd MMMMM yyyy").parse((String)model.getValueAt(baris, 1));
        if (simpanan.carisimpanan(TSimpanan.getValueAt
            (TSimpanan.getSelectedRow(),0).toString())==true) {
            txtidsimpanan.setText(simpanan.getIdsimpanan());
            txttgl.setDate(date);
            txtidanggota.setText(simpanan.getIdanggota());
            txtnama.setText(simpanan.getNama());
            txtwajib.setText(simpanan.getSimpananwajib());
            txtsukarela.setText(simpanan.getSimpananpokok());
        }
    } catch (ParseException ex) {
        Logger.getLogger(vSimpanan.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_TSimpananMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        hitungsaldo();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        
    }//GEN-LAST:event_txtcariActionPerformed

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            hitungsaldo();
            datatable(); 
        }
    }//GEN-LAST:event_txtcariKeyPressed

    private void bcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcariActionPerformed
        datatable();
    }//GEN-LAST:event_bcariActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        vDataAnggota Pp = new vDataAnggota();
        Pp.plg = this;
        Pp.setVisible(true);
        Pp.setResizable(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try{
            String Logo = new File("src\\images\\logo fix.png").getAbsolutePath();
            String NamaFile = "./src/report/TransaksiSimpanan.jasper";
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
            java.util.logging.Logger.getLogger(vSimpanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vSimpanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vSimpanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vSimpanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new vSimpanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TSimpanan;
    private javax.swing.JButton bcari;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtidanggota;
    private javax.swing.JTextField txtidsimpanan;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtsaldo;
    private javax.swing.JTextField txtsukarela;
    private com.toedter.calendar.JDateChooser txttgl;
    private javax.swing.JTextField txttotalsaldo;
    private javax.swing.JTextField txtwajib;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.koneksiDB;

/**
 *
 * @author PC-USER
 */
public class pinjaman {

    /**
     * @return the idpinjaman
     */
    public static String getIdpinjaman() {
        return idpinjaman;
    }

    /**
     * @param aIdpinjaman the idpinjaman to set
     */
    public static void setIdpinjaman(String aIdpinjaman) {
        idpinjaman = aIdpinjaman;
    }

    /**
     * @return the tgl
     */
    public static String getTgl() {
        return tgl;
    }

    /**
     * @param aTgl the tgl to set
     */
    public static void setTgl(String aTgl) {
        tgl = aTgl;
    }

    /**
     * @return the idanggota
     */
    public static String getIdanggota() {
        return idanggota;
    }

    /**
     * @param aIdanggota the idanggota to set
     */
    public static void setIdanggota(String aIdanggota) {
        idanggota = aIdanggota;
    }

    /**
     * @return the nama
     */
    public static String getNama() {
        return nama;
    }

    /**
     * @param aNama the nama to set
     */
    public static void setNama(String aNama) {
        nama = aNama;
    }

    /**
     * @return the jumlahpinjaman
     */
    public static String getJumlahpinjaman() {
        return jumlahpinjaman;
    }

    /**
     * @param aJumlahpinjaman the jumlahpinjaman to set
     */
    public static void setJumlahpinjaman(String aJumlahpinjaman) {
        jumlahpinjaman = aJumlahpinjaman;
    }

    /**
     * @return the lamapinjam
     */
    public static String getLamapinjam() {
        return lamapinjam;
    }

    /**
     * @param aLamapinjam the lamapinjam to set
     */
    public static void setLamapinjam(String aLamapinjam) {
        lamapinjam = aLamapinjam;
    }

    /**
     * @return the bunga
     */
    public static String getBunga() {
        return bunga;
    }

    /**
     * @param aBunga the bunga to set
     */
    public static void setBunga(String aBunga) {
        bunga = aBunga;
    }

    /**
     * @return the totalpinjaman
     */
    public static String getTotalpinjaman() {
        return totalpinjaman;
    }

    /**
     * @param aTotalpinjaman the totalpinjaman to set
     */
    public static void setTotalpinjaman(String aTotalpinjaman) {
        totalpinjaman = aTotalpinjaman;
    }

    /**
     * @return the angsuran
     */
    public static String getAngsuran() {
        return angsuran;
    }

    /**
     * @param aAngsuran the angsuran to set
     */
    public static void setAngsuran(String aAngsuran) {
        angsuran = aAngsuran;
    }

    /**
     * @return the RsPinjaman
     */
    public static ResultSet getRsPinjaman() {
        return RsPinjaman;
    }

    /**
     * @param aRsPinjaman the RsPinjaman to set
     */
    public static void setRsPinjaman(ResultSet aRsPinjaman) {
        RsPinjaman = aRsPinjaman;
    }
    
    private static String idpinjaman;
    private static String tgl;
    private static String idanggota;
    private static String nama;
    private static String jumlahpinjaman;
    private static String lamapinjam;
    private static String bunga;
    private static String totalpinjaman;
    private static String angsuran;
    private static ResultSet RsPinjaman;
    
    public static boolean caripinjaman(String id) {
        setIdpinjaman(id);
        boolean b = false;
        String strSQL = "select * from dt_pinjaman where idpinjaman='" + getIdpinjaman()+"'";
         setRsPinjaman(koneksi.koneksiDB.getRS(strSQL));
        try {
            if (getRsPinjaman().next()) {
                b = true;
                setIdpinjaman(getRsPinjaman().getString("idpinjaman"));
                setTgl(getRsPinjaman().getString("tgl"));
                setIdanggota(getRsPinjaman().getString("idanggota"));
                setNama(getRsPinjaman().getString("nama"));
                setJumlahpinjaman(getRsPinjaman().getString("jmlpinjaman"));
                setLamapinjam(getRsPinjaman().getString("lamapinjaman"));
                setBunga(getRsPinjaman().getString("bunga"));
                setTotalpinjaman(getRsPinjaman().getString("totalpinjaman"));
                setAngsuran(getRsPinjaman().getString("angsuran"));
                }
        } catch (SQLException ex) {
            Logger.getLogger(pinjaman.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                getRsPinjaman().close();
            } catch (SQLException ex) {
                Logger.getLogger(pinjaman.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return b;
    }

    public static int updatepinjaman(String trans, String[] plg) {
        setIdpinjaman(plg[0]);
        int n=0;
        String strSQL = null;
        if(trans.equals("tambah")) {
            strSQL = "insert into dt_pinjaman (idpinjaman,tgl,idanggota,nama,jmlpinjaman,lamapinjaman,bunga,totalpinjaman,angsuran)"+
                    " values ('"+plg[0]+"','"+plg[1]+"','"+plg[2]+"','"+plg[3]+"','"+plg[4]+"','"+plg[5]+
                    "','"+plg[6]+"','"+plg[7]+"','"+plg[8]+"')";                    
        }else if(trans.equals("ubah")) {
            strSQL = "update dt_pinjaman set tgl='"+plg[1]+"', idanggota='"+plg[2]+ "',nama='"+plg[3]+ "',jmlpinjaman='"+plg[4]+"',lamapinjaman='"+plg[5]
                    +"',bunga='"+plg[6]+"', totalpinjaman='"+plg[7]+ "',angsuran='"+plg[8]
                    + "' where idpinjaman = '"+plg [0]+"'";
        } else {
            strSQL = "delete from dt_pinjaman where idpinjaman='"+plg[0]+"'";
        }
        n = koneksiDB.execSQL(strSQL);
        return n;
    }
}

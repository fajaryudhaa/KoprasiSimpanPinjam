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
public class angsuran {

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
     * @return the idangsuran
     */
    public static String getIdangsuran() {
        return idangsuran;
    }

    /**
     * @param aIdangsuran the idangsuran to set
     */
    public static void setIdangsuran(String aIdangsuran) {
        idangsuran = aIdangsuran;
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
     * @return the tglpinjaman
     */
    public static String getTglpinjaman() {
        return tglpinjaman;
    }

    /**
     * @param aTglpinjaman the tglpinjaman to set
     */
    public static void setTglpinjaman(String aTglpinjaman) {
        tglpinjaman = aTglpinjaman;
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
     * @return the lamapinjaman
     */
    public static String getLamapinjaman() {
        return lamapinjaman;
    }

    /**
     * @param aLamapinjaman the lamapinjaman to set
     */
    public static void setLamapinjaman(String aLamapinjaman) {
        lamapinjaman = aLamapinjaman;
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
     * @return the angsuranke
     */
    public static String getAngsuranke() {
        return angsuranke;
    }

    /**
     * @param aAngsuranke the angsuranke to set
     */
    public static void setAngsuranke(String aAngsuranke) {
        angsuranke = aAngsuranke;
    }

    /**
     * @return the jmlangsursn
     */
    public static String getJmlangsursn() {
        return jmlangsursn;
    }

    /**
     * @param aJmlangsursn the jmlangsursn to set
     */
    public static void setJmlangsursn(String aJmlangsursn) {
        jmlangsursn = aJmlangsursn;
    }

    /**
     * @return the totalangsuran
     */
    public static String getTotalangsuran() {
        return totalangsuran;
    }

    /**
     * @param aTotalangsuran the totalangsuran to set
     */
    public static void setTotalangsuran(String aTotalangsuran) {
        totalangsuran = aTotalangsuran;
    }

    /**
     * @return the sisangasuran
     */
    public static String getSisangasuran() {
        return sisangasuran;
    }

    /**
     * @param aSisangasuran the sisangasuran to set
     */
    public static void setSisangasuran(String aSisangasuran) {
        sisangasuran = aSisangasuran;
    }

    /**
     * @return the RsTransaksi
     */
    public static ResultSet getRsTransaksi() {
        return RsTransaksi;
    }

    /**
     * @param aRsTransaksi the RsTransaksi to set
     */
    public static void setRsTransaksi(ResultSet aRsTransaksi) {
        RsTransaksi = aRsTransaksi;
    }
    
    private static String idangsuran;
    private static String tgl;
    private static String idpinjaman;
    private static String tglpinjaman;
    private static String totalpinjaman;
    private static String lamapinjaman;
    private static String idanggota;
    private static String nama;
    private static String angsuranke;
    private static String angsuran;
    private static String jmlangsursn;
    private static String totalangsuran;
    private static String sisangasuran;
    private static ResultSet RsTransaksi;
    
    public static boolean cariangsuran(String id) {
        setIdangsuran(id);
        boolean b = false;
        String strSQL = "select * from dt_angsuran where idangsuran='" + getIdangsuran()+"'";
         setRsTransaksi(koneksi.koneksiDB.getRS(strSQL));
        try {
            if (getRsTransaksi().next()) {
                b = true;
                setIdangsuran(getRsTransaksi().getString("idangsuran"));
                setTgl(getRsTransaksi().getString("tgl"));
                setIdpinjaman(getRsTransaksi().getString("idpinjaman"));
                setTglpinjaman(getRsTransaksi().getString("tglpinjaman"));
                setTotalpinjaman(getRsTransaksi().getString("totalpinjaman"));
                setLamapinjaman(getRsTransaksi().getString("lamapinjaman"));
                setIdanggota(getRsTransaksi().getString("idanggota"));
                setNama(getRsTransaksi().getString("nama"));
                setAngsuranke(getRsTransaksi().getString("angsuranke"));
                setAngsuran(getRsTransaksi().getString("angsuran"));
                setJmlangsursn(getRsTransaksi().getString("jmlangsuran"));
                setTotalangsuran(getRsTransaksi().getString("totalangsuran"));
                setSisangasuran(getRsTransaksi().getString("sisaangsuran"));
                }
        } catch (SQLException ex) {
            Logger.getLogger(angsuran.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                getRsTransaksi().close();
            } catch (SQLException ex) {
                Logger.getLogger(angsuran.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return b;
    }

    public static int updatetransaksi(String trans, String[] plg) {
        setIdangsuran(plg[0]);
        int n=0;
        String strSQL = null;
        if(trans.equals("tambah")) {
            strSQL = "insert into dt_angsuran (idangsuran,tgl,idpinjaman,tglpinjaman,totalpinjaman,lamapinjaman,idanggota,nama,angsuranke,angsuran,jmlangsuran,totalangsuran,sisaangsuran)"+
                    " values ('"+plg[0]+"','"+plg[1]+"','"+plg[2]+"','"+plg[3]+"','"+plg[4]+"','"+plg[5]+
                    "','"+plg[6]+"','"+plg[7]+"','"+plg[8]+"','"+plg[9]+"','"+plg[10]+"','"+plg[11]+"','"+plg[12]+"')";                    
        }else if(trans.equals("ubah")) {
            strSQL = "update dt_angsuran set tgl='"+plg[1]+"', idpinjaman='"+plg[2]+ "',tglpinjaman='"+plg[3]+ "',totalpinjaman='"+plg[4]+"',lamapinjaman='"+plg[5]
                    +"',idanggota='"+plg[6]+"', nama='"+plg[7]+ "',angsuranke='"+plg[8]+ "',angsuran='"+plg[9]+ "',jmlangsuran='"+plg[10]+"',totalangsuran='"+plg[11]+"',sisaangsuran='"+plg[12]
                    + "' where idangsuran = '"+plg [0]+"'";
        } else {
            strSQL = "delete from dt_angsuran where idangsuran='"+plg[0]+"'";
        }
        n = koneksiDB.execSQL(strSQL);
        return n;
    }
}

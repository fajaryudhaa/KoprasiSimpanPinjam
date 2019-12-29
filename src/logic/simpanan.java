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
public class simpanan {

    /**
     * @return the idsimpanan
     */
    public static String getIdsimpanan() {
        return idsimpanan;
    }

    /**
     * @param aIdsimpanan the idsimpanan to set
     */
    public static void setIdsimpanan(String aIdsimpanan) {
        idsimpanan = aIdsimpanan;
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
     * @return the simpananwajib
     */
    public static String getSimpananwajib() {
        return simpananwajib;
    }

    /**
     * @param aSimpananwajib the simpananwajib to set
     */
    public static void setSimpananwajib(String aSimpananwajib) {
        simpananwajib = aSimpananwajib;
    }

    /**
     * @return the simpananpokok
     */
    public static String getSimpananpokok() {
        return simpananpokok;
    }

    /**
     * @param aSimpananpokok the simpananpokok to set
     */
    public static void setSimpananpokok(String aSimpananpokok) {
        simpananpokok = aSimpananpokok;
    }

    /**
     * @return the RsKoprasi
     */
    public static ResultSet getRsKoprasi() {
        return RsKoprasi;
    }

    /**
     * @param aRsKoprasi the RsKoprasi to set
     */
    public static void setRsKoprasi(ResultSet aRsKoprasi) {
        RsKoprasi = aRsKoprasi;
    }
    
    private static String idsimpanan;
    private static String tgl;
    private static String idanggota;
    private static String nama;
    private static String simpananwajib;
    private static String simpananpokok;
    private static ResultSet RsKoprasi;
    
    public static boolean carisimpanan(String idsimpanan) {
        setIdsimpanan(idsimpanan);
        boolean b = false;
        String strSQL = "select * from dt_simpanan where idsimpanan='" + getIdsimpanan()+"'";
         setRsKoprasi(koneksi.koneksiDB.getRS(strSQL));
        try {
            if (getRsKoprasi().next()) {
                b = true;
                setIdsimpanan(getRsKoprasi().getString("idsimpanan"));
                setTgl(getRsKoprasi().getString("tgl"));
                setIdanggota(getRsKoprasi().getString("idanggota"));
                setNama(getRsKoprasi().getString("nama"));
                setSimpananwajib(getRsKoprasi().getString("simpananwajib"));
                setSimpananpokok(getRsKoprasi().getString("simpanansukarela"));
                }
        } catch (SQLException ex) {
            Logger.getLogger(simpanan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                getRsKoprasi().close();
            } catch (SQLException ex) {
                Logger.getLogger(simpanan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return b;
    }

    public static int updatesimpanan(String trans, String[] plg) {
        setIdsimpanan(plg[0]);
        int n=0;
        String strSQL = null;
        if(trans.equals("tambah")) {
            strSQL = "insert into dt_simpanan (idsimpanan,tgl,idanggota,nama,simpananwajib,simpanansukarela)"+
                    " values ('"+plg[0]+"','"+plg[1]+"','"+plg[2]+"','"+plg[3]+"','"+plg[4]+"','"+plg[5]+"')";                    
        }else if(trans.equals("ubah")) {
            strSQL = "update dt_simpanan set tgl='"+plg[1]+"', idanggota='"+plg[2]+ "',nama='"+plg[3]+"',simpananwajib='"+plg[4]+"',simpanansukarela='"+plg[5]+"'"
                    + " where idsimpanan = '"+plg [0]+"'";
        } else {
            strSQL = "delete from dt_simpanan where idsimpanan='"+plg[0]+"'";
        }
        n = koneksiDB.execSQL(strSQL);
        return n;
    }
}

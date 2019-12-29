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
public class anggota {

    /**
     * @return the id
     */
    public static String getId() {
        return id;
    }

    /**
     * @param aId the id to set
     */
    public static void setId(String aId) {
        id = aId;
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
     * @return the tempatlahir
     */
    public static String getTempatlahir() {
        return tempatlahir;
    }

    /**
     * @param aTempatlahir the tempatlahir to set
     */
    public static void setTempatlahir(String aTempatlahir) {
        tempatlahir = aTempatlahir;
    }

    /**
     * @return the tgllahir
     */
    public static String getTgllahir() {
        return tgllahir;
    }

    /**
     * @param aTgllahir the tgllahir to set
     */
    public static void setTgllahir(String aTgllahir) {
        tgllahir = aTgllahir;
    }

    /**
     * @return the alamat
     */
    public static String getAlamat() {
        return alamat;
    }

    /**
     * @param aAlamat the alamat to set
     */
    public static void setAlamat(String aAlamat) {
        alamat = aAlamat;
    }

    /**
     * @return the notlp
     */
    public static String getNotlp() {
        return notlp;
    }

    /**
     * @param aNotlp the notlp to set
     */
    public static void setNotlp(String aNotlp) {
        notlp = aNotlp;
    }

    /**
     * @return the divisi
     */
    public static String getDivisi() {
        return divisi;
    }

    /**
     * @param aDivisi the divisi to set
     */
    public static void setDivisi(String aDivisi) {
        divisi = aDivisi;
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
     * @return the tglmasuk
     */
    public static String getTglmasuk() {
        return tglmasuk;
    }

    /**
     * @param aTglmasuk the tglmasuk to set
     */
    public static void setTglmasuk(String aTglmasuk) {
        tglmasuk = aTglmasuk;
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
    
    private static String id;
    private static String nama;
    private static String tempatlahir;
    private static String tgllahir;
    private static String alamat;
    private static String notlp;
    private static String divisi;
    private static String simpananpokok;
    private static String tglmasuk;
    private static ResultSet RsKoprasi;
    
    public static boolean carianggota(String id) {
        setId(id);
        boolean b = false;
        String strSQL = "select * from dt_anggota where id='" + getId()+"'";
         setRsKoprasi(koneksi.koneksiDB.getRS(strSQL));
        try {
            if (getRsKoprasi().next()) {
                b = true;
                setId(getRsKoprasi().getString("id"));
                setNama(getRsKoprasi().getString("nama"));
                setTempatlahir(getRsKoprasi().getString("tempatlahir"));
                setTgllahir(getRsKoprasi().getString("tgllahir"));
                setAlamat(getRsKoprasi().getString("alamat"));
                setNotlp(getRsKoprasi().getString("notlp"));
                setDivisi(getRsKoprasi().getString("divisi"));
                setSimpananpokok(getRsKoprasi().getString("simpananpokok"));
                setTglmasuk(getRsKoprasi().getString("tglmasuk"));       
            }
        } catch (SQLException ex) {
            Logger.getLogger(anggota.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                getRsKoprasi().close();
            } catch (SQLException ex) {
                Logger.getLogger(anggota.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return b;
    }

    public static int updateanggota(String trans, String[] plg) {
        setId (plg[0]);
        int n=0;
        String strSQL = null;
        if(trans.equals("tambah")) {
            strSQL = "insert into dt_anggota (id,nama,tempatlahir,tgllahir,alamat,notlp,divisi,simpananpokok,tglmasuk)"+
                    " values ('"+plg[0]+"','"+plg[1]+"','"+plg[2]+"','"+plg[3]+"','"
                    +plg[4]+"','"+plg[5]+"','"+plg[6]+"','"+plg[7]+"','"+plg[8]+"')";                    
        }else if(trans.equals("ubah")) {
            strSQL = "update dt_anggota set nama='"+plg[1]+"', tempatlahir='"+plg[2]+ "',tgllahir='"+plg[3]+
                    "',alamat='"+plg[4]+"',notlp='"+plg[5]+"', divisi='"+plg[6]+ "',simpananpokok='"+plg[7]+"',tglmasuk='"+plg[8]+ "'"
                    + "where id = '"+plg [0]+"'";
        } else {
            strSQL = "delete from dt_anggota where id='"+plg[0]+"'";
        }
        n = koneksiDB.execSQL(strSQL);
        return n;
    }
}

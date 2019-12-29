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
public class divisi {

    /**
     * @return the kode
     */
    public static String getKode() {
        return kode;
    }

    /**
     * @param aKode the kode to set
     */
    public static void setKode(String aKode) {
        kode = aKode;
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
    
    private static String kode;
    private static String nama;
    private static ResultSet RsKoprasi;
    
    public static boolean caridivisi(String kode) {
        setKode(kode);
        boolean b = false;
        String strSQL = "select * from dt_divisi where kode='" + getKode()+"'";
         setRsKoprasi(koneksi.koneksiDB.getRS(strSQL));
        try {
            if (getRsKoprasi().next()) {
                b = true;
                setKode(getRsKoprasi().getString("kode"));
                setNama(getRsKoprasi().getString("nama"));
                }
        } catch (SQLException ex) {
            Logger.getLogger(divisi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                getRsKoprasi().close();
            } catch (SQLException ex) {
                Logger.getLogger(divisi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return b;
    }

    public static int updatedivisi(String trans, String[] plg) {
        setKode(plg[0]);
        int n=0;
        String strSQL = null;
        if(trans.equals("tambah")) {
            strSQL = "insert into dt_divisi (kode,nama)"+
                    " values ('"+plg[0]+"','"+plg[1]+"')";                    
        }else if(trans.equals("ubah")) {
            strSQL = "update dt_divisi set nama='"+plg[1]+"' where kode = '"+plg [0]+"'";
        } else {
            strSQL = "delete from dt_divisi where kode='"+plg[0]+"'";
        }
        n = koneksiDB.execSQL(strSQL);
        return n;
    }
}

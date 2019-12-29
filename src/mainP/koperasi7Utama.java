package mainP;

import view.vLogin;

/**
 *
 * @author BIMBIMBUM
 */
public class koperasi7Utama {
    public static void main(String [] bamz){
        new vLogin().setVisible(true);
        koneksi.koneksiDB.koneksiMySQL("kk_7utama", "root", "");
    }
}
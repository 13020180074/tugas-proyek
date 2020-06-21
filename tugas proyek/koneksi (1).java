/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Yanti
 */
public class koneksi {
 
 static Connection Koneksi;
    public static Connection getConnection(){
        try{
            Koneksi = DriverManager.getConnection("jdbc:mysql://localhost/menghitung gaji","root","");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Koneksi Database Gagal");
        }
        return Koneksi;
    }   
}
   


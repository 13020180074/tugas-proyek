/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yanti
 */
import com.mysql.jdbc.Statement;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import koneksi.koneksi;
import javax.swing.table.DefaultTableModel;
public class form_Gaji extends javax.swing.JFrame {

    /**
     * Creates new  form_Gaji;
     */
   
        
        
      public final class Form_Gaji {
       private DefaultTableModel model;
       String nip, nama, golongan;
      int gapok, jamkerja, lembur; 
    }
    public void loadData() {
        txtnip.getText();
       txtnama.getText();
       cmbgol.getSelectedItem();
    }
    public void loadGaji( ) {
     golongan = " "+ cmbgol.getSelectedItem();
     switch(golongan){
             case "I":
                    gapok    = 500000;
             break;
             case "II":
                    gapok    = 300000;
             break;
             case "III":
                    gapok    = 250000;
             break;
             case "IV":
                    gapok   = 100000;
             break;
      }
      jamkerja = (int) (gapok * 0.1);
      lembur     = gapok + jamkerja;
      txtgaji.setText(""+gapok);
      txtjam.setText(""+jam kerja);
      txtlembur.setText(""+lembur);
}              

private void datatable(){
    DefaultTableModel  tbl = new DefaultTableModel();
        tbl.addColumn("nip");
        tbl.addColumn("Nama pegawai");
        tbl.addColumn("golongan");
        tbl.addColumn("gaji pokok");
        tbl.addColumn("jam kerja");
        tbl.addColumn("lembur");
       
        try{
            Statement statement = (Statement) koneksi.getConnection().createStatement();
            ResultSet res = statement.executeQuery("select * from tabel_mahasiswa");
            while(res.next())
            {
                tbl.addRow(new Object[]{
                    res.getString("nip"),
                    res.getString("Nama pegawai"),
                    res.getString("golongan"),
                    res.getString("gaji pokok"),
                    res.getString("jam kerja"),
                        res.getString("lembur")
                });
               
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Gagal");
        }
}
private void BkeluarActionPerformed(java.awt.event.ActionEvent evt) {                                        
        dispose();
 } 
private void btnSaveActionPerformed(java.awt.event.ActionEvent evt{
       saveData();
 }
public void saveData(){
      loadData();
      try{
           Statement stat = (Statement) Db_Connection.getKoneksi().createStatement();
           String sql        = "Insert into gaji (Nip, Nama, golongan, gapok, jam kerja, lembur)"
                                      + "values ('"+ nip +"','"+ nama +"','"+ golongan +"','"+ gapok +"',"
                                      + " '"+ jam kerja +"', '"+ lembur +"')";
          PreparedStatement p = (PreparedStatement) Db_Connection.getKoneksi().preparedStatement(sql);
          p.executeUpdate();
          getData();
       }catch (SQLException err){
          JOptionPane.showMessageDialog(null, err.getMessage());
       }
      datatable();
}

private void cmboxJabatanActionPerformed(java.awt.event.ActionEvent evt{
       loadGaji();
 }

    public void Reset( ){
    nip           = " ";
    nama       = " ";
    golongan    = " ";
    gapok     = 0;
    jam kerja = 0;
    lembur     = 0;
    txtnip.setText(nip);
    txtnama.setText(nama);
    txtgaji.setText(" ");
    txtjam.setText(" ");
    txtlembur.setText(" ");
} 
 } 
private void btnResetActionPerformed(java.awt.event.ActionEvent evt{
    Reset();
 } 
public void dataSelect(){
      int i = tblGaji.getSelectRow();
      if (i == -1){
          //tidak ada data terpilih
          return;
      }
      txtnip.setText(""+model.getValueAt(i, 0));
      txtnama.setText(""+model.getValueAt(i, 1));
      cmbgol.setSelectedItem(""+model.getValueAt(i, 2));
      txtgaji.setText(""+model.getValueAt(i, 3));
       txtjam.setText(""+model.getValueAt(i, 4));
      txtlembur.setText(""+model.getValueAt(i, 5)); 
}

public void updateData(){
       /* memanggil class loadData() untuk menentukan kondisi atau variable nip
           yang akan diubah, berdasarkan yang dipilih
       */
       loadData();
       try{
            Statement stat = (Statement) Db_Connection.getConection().createStatement();
            String sql        = "UPDATE gaji SET Nama       = '"+ nama +"',"
                                                              + "golongan     = '"+ golongan +"',"
                                                              + "Gapok      = '"+ gapok+"',"
                                                              + "jam kerja   = '"+jamkerja +"',"
                                                              + "lembur        = '"+ lembur +"',"
                                       + "WHERE Nip = '"+ nip +"' ";
       PreparedStatement p = (PreparedStatement) Db_Connection.getConection().preparedStatement(sql);
       p.executeUpdate();

        getData();
       Reset();
       JOptionPane.showMessageDialog(null, "Update berhasil....");
     catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage);
    
}
private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt){
        updateData( );
 }
public void deleteData(){
     loadData();
     //pesan konfirmasi untuk menghapus data 
     int pesan = JOptionPane.showConfirmDialog(null, "Anda yakin menghapus data"+ nip "+"?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION);
     //jika user mengklik tombol OK maka proses delete dilakukan
     if (pesan = JOptionPane.OK_OPTION){
         try{
               Statement stat = (Statement) Db_Connection.getConection().createStatement();
               String sql = "DELETE FROM gaji WHERE Nip = '"+ nip +"' ";
         PreparedStatement p = (PreparedStatement) Db_Connection.getConection().preparedStatement(sql);
         p.executeUpdate();
         getData();
         Reset();
         JOptionPane.showMessageDialog(null, "Delete berhasil");
         }catch(SQLException err){
                JOptionPane.showMessageDialog(null, err.getMessage);
         }
}
private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt){
      deleteData();
 }
private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt){
this.dispose();
 }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtgaji = new javax.swing.JTextField();
        txtjam = new javax.swing.JTextField();
        txtlembur = new javax.swing.JTextField();
        txtnip = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        bsave = new javax.swing.JButton();
        breset = new javax.swing.JButton();
        bupadte = new javax.swing.JButton();
        bdelete = new javax.swing.JButton();
        bkeluar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_pegawai = new javax.swing.JTable();
        cmbgol = new javax.swing.JComboBox<>();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Data Gaji Karyawan");

        jLabel2.setText("nip");

        jLabel3.setText("nama pegawai");

        jLabel4.setText("golongan");

        jLabel5.setText("gaji pokok");

        jLabel6.setText("jam kerja");

        jLabel7.setText("lembur");

        txtgaji.setText("300000");

        txtjam.setText("1");
        txtjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjamActionPerformed(evt);
            }
        });

        txtlembur.setText("2");

        txtnip.setText("64765");

        txtnama.setText("yndjf");

        bsave.setText("save");

        breset.setText("reset");

        bupadte.setText("update");

        bdelete.setText("delete");

        bkeluar.setText("keluar");

        tabel_pegawai.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabel_pegawai);

        cmbgol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "I", "II", "III", "IV" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnama, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtnip, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbgol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(296, 296, 296)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 179, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtgaji, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bsave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(breset)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bupadte))
                            .addComponent(txtjam, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtlembur, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bdelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bkeluar)
                        .addGap(32, 32, 32))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtnip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtgaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtlembur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel7)
                        .addComponent(cmbgol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bsave)
                    .addComponent(breset)
                    .addComponent(bupadte)
                    .addComponent(bdelete)
                    .addComponent(bkeluar))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjamActionPerformed
        
    }//GEN-LAST:event_txtjamActionPerformed
private void BnewActionPerformed(java.awt.event.ActionEvent evt) {                                     
        txtnip.setText("");
        txtnama.setText("");
        cmbgol.setSelectedItem("Teknik Informatika");
        txtgaji.setText("");
        txtjam.requestFocus();
        txtlembur.setText("");
    }                             
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
            java.util.logging.Logger.getLogger(form_Gaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_Gaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_Gaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_Gaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_Gaji().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bdelete;
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton breset;
    private javax.swing.JButton bsave;
    private javax.swing.JButton bupadte;
    private javax.swing.JComboBox<String> cmbgol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tabel_pegawai;
    private javax.swing.JTextField txtgaji;
    private javax.swing.JTextField txtjam;
    private javax.swing.JTextField txtlembur;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnip;
    // End of variables declaration//GEN-END:variables
}

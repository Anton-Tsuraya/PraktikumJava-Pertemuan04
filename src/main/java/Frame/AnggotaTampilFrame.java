
package Frame;

import db.Koneksi;
import model.Anggota;
import model.Petugas;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AnggotaTampilFrame extends javax.swing.JFrame {

    Anggota anggota;
    
    public AnggotaTampilFrame() {
        initComponents();
        setLocationRelativeTo(null);
        resetTable("");
    }
    
    public ArrayList<Anggota> getAnggotaList (String keyword) {
        ArrayList<Anggota> anggotaList = new ArrayList<Anggota>();
        Koneksi koneksi = new Koneksi();
        Connection connection = koneksi.getConnection();
        
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT anggota.*, petugas.* FROM anggota "
                    + "INNER JOIN petugas ON anggota.id_petugas = petugas.id ";
        String order = " ORDER BY anggota.id";
        if(!keyword.equals(""))
            query = query+ " WHERE anggota.id = ? OR nama_anggota like ?";
        
        query = query+order;
        try{
            ps = connection.prepareStatement(query);
            if(!keyword.equals("")){
                ps.setString(1,eCari.getText());
                ps.setString(2, "%"+eCari.getText()+"%");
            }
            rs = ps.executeQuery();
            while(rs.next()){
                anggota = new Anggota(
                        rs.getString("anggota.id"),
                        rs.getString("nama_anggota"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("tanggal_lahir"),
                        rs.getString("agama"),
                        rs.getInt("id_petugas"),
                        rs.getString("petugas.nama_petugas"),
                        rs.getBlob("foto_anggota"));
                anggotaList.add(anggota);
            }
        } catch(SQLException ex){
            System.err.println("ERROR getAnggotaList : "+ex);
            System.err.println(ex.getMessage());
        }
        return anggotaList;
    }
    
    public void selectAnggota(String keyword){
        ArrayList<Anggota> list;
        list = getAnggotaList(keyword);
        DefaultTableModel model = (DefaultTableModel)tAnggota.getModel();
        Object[] row =  new Object[8];
        
        for (int i=0; i<list.size(); i++){
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getNamaAnggota();
            row[2] = list.get(i).getJenisKelamin();
            row[3] = list.get(i).getTanggalLahir();
            row[4] = list.get(i).getAgama();
            row[5] = list.get(i).getPetugas().getId();
            row[6] = list.get(i).getPetugas().getNamaPetugas();
            row[7] = list.get(i).getFotoAnggota();
            
            model.addRow(row);
        }
    }
    
    public final void resetTable (String keyword){
        DefaultTableModel model = (DefaultTableModel)tAnggota.getModel();
        model.setRowCount(0);
        selectAnggota(keyword);
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        tAnggota = new javax.swing.JTable();
        eCari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        bCari = new javax.swing.JButton();
        bTambah = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
        bUbah = new javax.swing.JButton();
        bBatal = new javax.swing.JButton();
        bTutup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tAnggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nama Anggota", "Jenis Kelamin", "Tanggal Lahir", "Agama", "Id Petugas", "Petugas", "Foto Anggota"
            }
        ));
        scrollPane.setViewportView(tAnggota);
        if (tAnggota.getColumnModel().getColumnCount() > 0) {
            tAnggota.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        eCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eCariActionPerformed(evt);
            }
        });

        jLabel1.setText("Cari Petugas");

        bCari.setText("Cari");

        bTambah.setText("Tambah");
        bTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahActionPerformed(evt);
            }
        });

        bHapus.setText("Hapus");
        bHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusActionPerformed(evt);
            }
        });

        bUbah.setText("Ubah");
        bUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahActionPerformed(evt);
            }
        });

        bBatal.setText("Batal");
        bBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBatalActionPerformed(evt);
            }
        });

        bTutup.setText("Tutup");
        bTutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTutupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bTambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bHapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bUbah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bBatal))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eCari, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bCari, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bTutup, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCari)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTambah)
                    .addComponent(bHapus)
                    .addComponent(bUbah)
                    .addComponent(bBatal)
                    .addComponent(bTutup))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        // TODO add your handling code here:
        int i  = tAnggota.getSelectedRow();
        if(i>=0) {
            TableModel model  = tAnggota.getModel();
            anggota = new Anggota();
            anggota.setId(model.getValueAt(i, 0).toString());
            anggota.setNamaAnggota(model.getValueAt(i, 1).toString());
            anggota.setJenisKelamin(model.getValueAt(i, 2).toString());
            anggota.setTanggalLahir(model.getValueAt(i, 3).toString());
            anggota.setAgama(model.getValueAt(i, 4).toString());
            anggota.setPetugas(new Petugas
                    (Integer.parseInt(model.getValueAt(i, 5).toString()),
                    model.getValueAt(i,6).toString()));
            Blob blob = (Blob) model.getValueAt(i, 7);
            anggota.setFotoAnggota(blob);
            
            AnggotaTambahFrame anggotaTambahFrame = new AnggotaTambahFrame(anggota);
            anggotaTambahFrame.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin diubah");
        }
    }//GEN-LAST:event_bUbahActionPerformed

    private void bTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTutupActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_bTutupActionPerformed

    private void bBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatalActionPerformed
        // TODO add your handling code here:
        resetTable("");
    }//GEN-LAST:event_bBatalActionPerformed

    private void eCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eCariActionPerformed
        // TODO add your handling code here:
        resetTable(eCari.getText());
    }//GEN-LAST:event_eCariActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:
         int i = tAnggota.getSelectedRow();
        int pilihan = JOptionPane.showConfirmDialog(
                null,
                "Yakin mau hapus ?",
                "Konfirmasi hapus",
                JOptionPane.YES_NO_OPTION
        );
        if(pilihan==0){
            if(i>=0){
                try{
                    TableModel model = tAnggota.getModel();
                    Koneksi koneksi = new Koneksi();
                    Connection con = koneksi.getConnection();
                    String executeQuery = "delete from anggota where id =?";
                    PreparedStatement ps = con.prepareStatement(executeQuery);
                    ps.setString(1, model.getValueAt(i, 0).toString());
                    ps.executeUpdate();
                } catch (SQLException ex){
                    System.err.println(ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus");
            }
        }
        resetTable("");
    }//GEN-LAST:event_bHapusActionPerformed

    private void bTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahActionPerformed
        // TODO add your handling code here:
        AnggotaTambahFrame anggotaTambahFrame = new AnggotaTambahFrame();
        anggotaTambahFrame.setVisible(true);
    }//GEN-LAST:event_bTambahActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        resetTable("");
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(AnggotaTampilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnggotaTampilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnggotaTampilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnggotaTampilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AnggotaTampilFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBatal;
    private javax.swing.JButton bCari;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bTambah;
    private javax.swing.JButton bTutup;
    private javax.swing.JButton bUbah;
    private javax.swing.JTextField eCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tAnggota;
    // End of variables declaration//GEN-END:variables
}

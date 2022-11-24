/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package method;

import controller.barang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi.connection;
import view.tampilan;

/**
 *
 * @author astse
 */
public class methodbarang implements barang{

    @Override
    public void Simpan(tampilan barang) throws SQLException {
        try {
            Connection con = connection.getKoneksi();
            String sql = "INSERT INTO data_barang VALUES (?,?,?,?)";
            PreparedStatement prr = con.prepareStatement(sql);
            prr.setInt(1, Integer.parseInt(barang.txtId.getText()));
            prr.setString(2, barang.txtnamaBarang.getText());
            prr.setString(3, barang.txtjumlah.getText());
            prr.setString(4, barang.txttanggal.getText());
            prr.executeUpdate();
            prr.close();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal di simpan");
            System.err.println("Error "+ e);   
        } finally{
            Tampil(barang);
            Reset(barang);
            barang.setLebarKolom();
            AutoNumber(barang);
        }
    }

    @Override
    public void Ubah(tampilan barang) throws SQLException {
        try {
            Connection con = connection.getKoneksi();
            String sql = "UPDATE data_barang SET nama=?, jumlah=? , tanggal=? WHERE id=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            
            PreparedStatement prr = con.prepareStatement(sql);
            prr.setInt(4, Integer.parseInt(barang.txtId.getText()));
            prr.setString(1, barang.txtnamaBarang.getText());
            prr.setString(2, barang.txtjumlah.getText());
            prr.setString(3, barang.txttanggal.getText());
            prr.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            prepare.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal di ubah");
            System.err.println("Error "+ e); 
        } finally {
            Tampil(barang);
            Reset(barang);
            barang.setLebarKolom();
            AutoNumber(barang);
        }
    }

    @Override
    public void Hapus(tampilan barang) throws SQLException {
        String sql = "DELETE FROM data_barang WHERE id=?";
        String sql1 = "ALTER TABLE data_barang DROP id";
        String sql2 = "ALTER TABLE data_barang ADD id INT(100) NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST";
        try {
            Connection con = connection.getKoneksi();
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setInt(1, Integer.valueOf(barang.txtId.getText()));
            prepare.executeUpdate();
            con.createStatement().execute(sql1);
            con.createStatement().execute(sql2);
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            prepare.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal di hapus");
            System.err.println("Error "+ e); 
        } finally{
            Tampil(barang);
            barang.setLebarKolom();
            Reset(barang);
            AutoNumber(barang);
        }
    }

    @Override
    public void Tampil(tampilan barang) throws SQLException {
        barang.tblmodel.getDataVector().removeAllElements();
        barang.tblmodel.fireTableDataChanged();
        
        try {
            Connection con = connection.getKoneksi();
            Statement stt = con.createStatement();
            // Query menampilkan semua data pada tabel siswa
            // dengan urutan NIS dari kecil ke besar
            String sql = "SELECT * FROM data_barang ORDER BY id ASC";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                Object[] ob = new Object[8];
                ob[0] = res.getString(1);
                ob[1] = res.getString(2);
                ob[2] = res.getString(3);
                ob[3] = res.getString(4);
                barang.tblmodel.addRow(ob);
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void KlikTable(tampilan barang) throws SQLException {
        try {
            int pilih = barang.table.getSelectedRow();
            if (pilih == -1){
                return;
            }
            barang.txtId.setText(barang.tblmodel.getValueAt(pilih, 0).toString());
            barang.txtnamaBarang.setText(barang.tblmodel.getValueAt(pilih, 1).toString());
            barang.txtjumlah.setText(barang.tblmodel.getValueAt(pilih, 2).toString());
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void Reset(tampilan barang) throws SQLException {
        barang.txtnamaBarang.setText("");
        barang.txtjumlah.setText("");
    }
    

   public void AutoNumber(tampilan barang) throws SQLException {
        try {
            Connection con = connection.getKoneksi();
            Statement stt = con.createStatement();
            String sql = "SELECT MAX(id) FROM data_barang";
            ResultSet res = stt.executeQuery(sql);

            while (res.next()) {
                int a = res.getInt(1);
                barang.txtId.setText(Integer.toString(a + 1));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}

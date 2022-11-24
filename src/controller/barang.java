package controller;

import java.sql.SQLException;
import view.tampilan;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author astse
 */
public interface barang {
    public void Simpan(tampilan barang)throws SQLException;
    public void Ubah(tampilan barang)throws SQLException;
    public void Hapus(tampilan barang)throws SQLException;
    public void Tampil(tampilan barang)throws SQLException;
    public void KlikTable(tampilan barang)throws SQLException;
    public void Reset(tampilan barang)throws SQLException;
}

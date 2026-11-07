package kelas.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

public class FormController {

    @FXML
    private TextField namaField, hargaField;
    @FXML
    private ComboBox<String> kategoriBox;
    @FXML
    private Label pesanLabel;

    private Navigator navigator;
    private DataProduk data;

    @FXML
    private void initialize() {
        kategoriBox.getItems().addAll("Makanan", "Minuman");
        kategoriBox.setValue("Makanan");
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
    }

    @FXML
    private void simpan() {
        Produk produk;
        try {
            long harga = Long.parseLong(hargaField.getText().trim());
            produk = data.tambah(namaField.getText(), kategoriBox.getValue(), harga);
        } catch (NumberFormatException e) {
            // Harus ditangkap lebih dulu karena NumberFormatException
            // adalah turunan dari IllegalArgumentException.
            pesanLabel.setText("Harga harus berupa angka bulat tanpa titik/koma, contoh: 15000.");
            return;
        } catch (IllegalArgumentException e) {
            // Pesan berasal dari validasi di constructor Produk.
            pesanLabel.setText(e.getMessage());
            return;
        }
        try {
            navigator.bukaDetail(produk);
        } catch (IOException e) {
            data.hapus(produk);
            pesanLabel.setText("Halaman detail gagal dibuka; data dibatalkan. Periksa FXML.");
            e.printStackTrace();
        }
    }

    @FXML
    private void bukaDaftar() {
        try {
            navigator.bukaDaftar();
        } catch (IOException e) {
            pesanLabel.setText("Gagal membuka daftar.");
            e.printStackTrace();
        }
    }
}

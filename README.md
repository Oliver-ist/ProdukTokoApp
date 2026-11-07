# Pencatatan Produk Toko (JavaFX)

Aplikasi desktop sederhana untuk mencatat produk toko, dibuat dengan JavaFX dan Maven.
Proyek ini adalah hasil modifikasi (refactor) dari aplikasi praktikum **TiketAppAdvanced**
untuk Tugas Individu mata kuliah Pemrograman Lanjut.

**Nama:** (Oliver Nathanael)
---

## Fitur

1. **Form tambah produk**: input nama produk, kategori (Makanan/Minuman), dan harga.
2. **Tabel daftar produk**: menampilkan kode, nama, kategori, dan harga.
3. **Pencarian**: mencari produk berdasarkan nama lewat tombol **Cari** (tidak membedakan huruf besar/kecil).
4. **Lihat detail**: membuka halaman detail dari produk yang dipilih di tabel.
5. **Validasi input**: nama kosong dan harga tidak valid ditolak dengan pesan error.

Fitur tambahan dari praktikum yang dipertahankan: tombol **Reset** (menghapus kata pencarian) dan **Hapus** (menghapus produk terpilih).

## Teknologi

- Java 17
- JavaFX 21 (controls dan FXML)
- Maven (`javafx-maven-plugin`)
- Data disimpan di memori selama aplikasi berjalan (tanpa database)

## Struktur Proyek

```
ProdukTokoApp/
├── pom.xml
└── src/main/
    ├── java/kelas/
    │   ├── MainApp.java            # titik masuk aplikasi
    │   ├── Navigator.java          # perpindahan halaman, membawa DataProduk dan objek Produk
    │   ├── model/Produk.java       # model data + validasi
    │   ├── data/DataProduk.java    # penyimpanan produk (ObservableList) + pembuat kode
    │   └── controller/
    │       ├── FormController.java
    │       ├── DaftarController.java
    │       └── DetailController.java
    └── resources/kelas/
        ├── form.fxml
        ├── daftar.fxml
        ├── detail.fxml
        └── styles/app.css
```

## Cara Menjalankan

Prasyarat: JDK 17 atau lebih baru, Maven, dan koneksi internet pada run pertama (untuk mengunduh JavaFX).

```
mvn clean javafx:run
```

Di NetBeans: klik kanan proyek, pilih **Run Maven**, lalu **Goals...**, isi `clean javafx:run`.

## Penjelasan Kode Singkat

| Berkas | Peran |
|---|---|
| `Produk` | Atribut `private` kode, nama, kategori, harga, beserta constructor dan getter. Constructor melempar `IllegalArgumentException` jika nama kosong ("Nama produk tidak boleh kosong.") atau harga tidak lebih dari nol ("Harga harus lebih dari nol."). |
| `DataProduk` | Menyimpan `ObservableList<Produk>`. Method `tambah(nama, kategori, harga)` membuat kode berurutan `PRD001`, `PRD002`, dan seterusnya. |
| `Navigator` | Memuat FXML, mengirim `DataProduk` ke form dan daftar, serta mengirim objek `Produk` ke halaman detail lewat `bukaDetail(Produk)`. |
| `FormController` | Membaca input, memanggil `DataProduk.tambah`, menampilkan pesan error, lalu membuka halaman detail. |
| `DaftarController` | Mengisi tabel, menyaring data berdasarkan nama saat **Cari** ditekan, dan membuka detail dari baris terpilih. |
| `DetailController` | Menampilkan data dari objek `Produk` yang diterima (bukan teks tetap di kode). |

## Pengujian dan Screenshot

### 1. Form tambah produk
![Form tambah produk](docs/screenshots/01-form.png)

### 2. Tiga produk berhasil dimasukkan
Produk yang diuji:

| Kode | Nama | Kategori | Harga |
|---|---|---|---|
| PRD001 | Nasi Goreng | Makanan | 15000 |
| PRD002 | Es Teh Manis | Minuman | 5000 |
| PRD003 | Kopi Susu | Minuman | 18000 |

![Tabel berisi tiga produk](docs/screenshots/02-daftar-tiga-produk.png)

### 3. Pencarian berdasarkan nama
Pencarian kata `es` hanya menampilkan "Es Teh Manis".

![Hasil pencarian](docs/screenshots/03-pencarian.png)

### 4. Halaman detail produk terpilih
Baris "Es Teh Manis" dipilih lalu tombol **Lihat Detail** ditekan.

![Detail produk](docs/screenshots/04-detail.png)

### 5. Validasi: nama kosong ditolak
![Error nama kosong](docs/screenshots/05-error-nama-kosong.png)

### 6. Validasi: harga tidak valid ditolak
Harga `0`, `-100`, dan teks non-angka ditolak.

![Error harga tidak valid](docs/screenshots/06-error-harga.png)

## Hasil Pengujian

| Skenario | Hasil |
|---|---|
| Memasukkan 3 produk | Ketiganya muncul di tabel dengan kode PRD001 sampai PRD003 |
| Mencari `es` | Hanya Es Teh Manis yang tampil |
| Lihat Detail pada baris terpilih | Detail sesuai produk yang dipilih |
| Nama kosong | Ditolak: "Nama produk tidak boleh kosong." |
| Harga `0` atau negatif | Ditolak: "Harga harus lebih dari nol." |
| Harga bukan angka bulat | Ditolak: pesan harga harus berupa angka bulat |

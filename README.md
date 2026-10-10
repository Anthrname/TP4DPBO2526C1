# TP4 DPBO - PeopleData (Java Swing GUI)

Aplikasi CRUD data orang berbasis Java Swing (IntelliJ IDEA, Swing UI Designer).

## Desain & Fungsi Program

| Berkas | Fungsi |
|---|---|
| `PeopleData/Person.java` | Class model: `id`, `nama`, `tahunLahir`, `kategori`, `tinggi` beserta getter/setter |
| `PeopleData/PeopleMenu.java` | Logika GUI dan operasi CRUD |
| `PeopleData/PeopleMenu.form` | Tata letak form (Swing UI Designer) |

**Atribut:** ID, Nama, Tahun Lahir, Kategori (JComboBox), dan **Tinggi Badan (JSlider, 140-200 cm)** sebagai atribut baru (komponen non-JTextField).

**Komponen form:** `idField`, `namaField`, `tahunLahirField`, `kategoriComboBox`, `tinggiSlider`, `tinggiValueLabel`, `addUpdateButton`, `cancelButton`, `deleteButton`, `peopleTable`.

## Alur Program

1. Saat dijalankan, `populateList()` mengisi `listPerson` dengan data awal, lalu `setTable()` menampilkannya di tabel.
2. **Create:** isi form lalu tekan **Add**. Data divalidasi (field kosong dan tahun lahir harus angka), ditambahkan ke list, tabel diperbarui, form dibersihkan.
3. **Read:** semua data tampil di tabel. Klik satu baris untuk memuat datanya ke form. Tombol berubah menjadi **Update** dan tombol **Delete** muncul.
4. **Update:** ubah isi form lalu tekan **Update**. Data pada list diperbarui dan tabel dimuat ulang.
5. **Delete:** tekan **Delete**, dialog konfirmasi muncul. Pilih **Yes** untuk menghapus, **No** untuk membatalkan.
6. **Cancel:** mengosongkan form dan mengembalikan mode ke **Add**.

## Dokumentasi Menjalankan Program

Dokumentasi demonstrasi program:
- [Screen Recording 2026-10-11 012953.mp4](Dokumentasi/Screen%20Recording%202026-10-11%20012953.mp4)

## Cara Menjalankan

1. Buka folder project di IntelliJ IDEA (JDK 17 atau lebih baru disarankan).
2. Pastikan plugin **Swing UI Designer** aktif.
3. Run `PeopleMenu.java`.

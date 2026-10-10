public class Person {
    private String id, nama, kategori;
    private int tahunLahir, tinggi;

    public Person(String id, String nama, int tahunLahir, String kategori, int tinggi) {
        this.id = id;
        this.nama = nama;
        this.tahunLahir = tahunLahir;
        this.kategori = kategori;
        this.tinggi = tinggi;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public int getTahunLahir() { return tahunLahir; }
    public String getKategori() { return kategori; }
    public int getTinggi() { return tinggi; }

    public void setId(String id) { this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public void setTahunLahir(int tahunLahir) { this.tahunLahir = tahunLahir; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public void setTinggi(int tinggi) { this.tinggi = tinggi; }
}

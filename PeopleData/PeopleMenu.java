import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PeopleMenu extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel tinggiValueLabel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField tahunLahirField;
    private JComboBox<String> kategoriComboBox;
    private JSlider tinggiSlider;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JTable peopleTable;

    private ArrayList<Person> listPerson;
    private int selectedIndex = -1;

    public static void main(String[] args) {
        PeopleMenu menu = new PeopleMenu();
        menu.setTitle("Data Orang");
        menu.setSize(700, 600);
        menu.setLocationRelativeTo(null);
        menu.setContentPane(menu.mainPanel);
        menu.getContentPane().setBackground(Color.WHITE);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
    }

    public PeopleMenu() {
        listPerson = new ArrayList<>();
        populateList();
        peopleTable.setModel(setTable());

        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        String[] kategoriData = {"???", "Mahasiswa", "Dosen", "Staf", "Alumni"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        tinggiSlider.setMinimum(140);
        tinggiSlider.setMaximum(200);
        tinggiSlider.setValue(170);
        tinggiValueLabel.setText("170 cm");
        tinggiSlider.addChangeListener(e ->
                tinggiValueLabel.setText(tinggiSlider.getValue() + " cm"));

        deleteButton.setVisible(false);

        addUpdateButton.addActionListener(e -> {
            if (selectedIndex == -1) insertData();
            else updateData();
        });

        deleteButton.addActionListener(e -> {
            int pilihan = JOptionPane.showConfirmDialog(this,
                    "Yakin ingin menghapus data ini?", "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (pilihan == JOptionPane.YES_OPTION) deleteData();
        });

        cancelButton.addActionListener(e -> clearForm());

        peopleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = peopleTable.getSelectedRow();
                if (row < 0 || row >= listPerson.size()) return;
                selectedIndex = row;

                Person p = listPerson.get(selectedIndex);
                idField.setText(p.getId());
                namaField.setText(p.getNama());
                tahunLahirField.setText(String.valueOf(p.getTahunLahir()));
                kategoriComboBox.setSelectedItem(p.getKategori());
                tinggiSlider.setValue(p.getTinggi());

                addUpdateButton.setText("Update");
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        Object[] cols = {"No", "ID", "Nama", "Tahun Lahir", "Kategori", "Tinggi (cm)"};
        DefaultTableModel tmp = new DefaultTableModel(null, cols) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (int i = 0; i < listPerson.size(); i++) {
            Person p = listPerson.get(i);
            tmp.addRow(new Object[]{i + 1, p.getId(), p.getNama(),
                    p.getTahunLahir(), p.getKategori(), p.getTinggi()});
        }
        return tmp;
    }

    private boolean validasi(String id, String nama, String kategori) {
        if (id.isEmpty() || nama.isEmpty() || kategori.equals("???")) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void insertData() {
        try {
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            String kategori = kategoriComboBox.getSelectedItem().toString();
            if (!validasi(id, nama, kategori)) return;

            int tahun = Integer.parseInt(tahunLahirField.getText().trim());
            int tinggi = tinggiSlider.getValue();

            listPerson.add(new Person(id, nama, tahun, kategori, tinggi));
            peopleTable.setModel(setTable());
            clearForm();

            System.out.println("Insert berhasil");
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tahun lahir harus berupa angka!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateData() {
        try {
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            String kategori = kategoriComboBox.getSelectedItem().toString();
            if (!validasi(id, nama, kategori)) return;

            int tahun = Integer.parseInt(tahunLahirField.getText().trim());

            Person p = listPerson.get(selectedIndex);
            p.setId(id);
            p.setNama(nama);
            p.setTahunLahir(tahun);
            p.setKategori(kategori);
            p.setTinggi(tinggiSlider.getValue());

            peopleTable.setModel(setTable());
            clearForm();

            System.out.println("Update berhasil");
            JOptionPane.showMessageDialog(this, "Data berhasil diubah");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tahun lahir harus berupa angka!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteData() {
        listPerson.remove(selectedIndex);
        peopleTable.setModel(setTable());
        clearForm();

        System.out.println("Delete berhasil");
        JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
    }

    public void clearForm() {
        idField.setText("");
        namaField.setText("");
        tahunLahirField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        tinggiSlider.setValue(170);

        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        peopleTable.clearSelection();
        selectedIndex = -1;
    }

    private void populateList() {
        listPerson.add(new Person("P001", "Budi Santoso", 2003, "Mahasiswa", 172));
        listPerson.add(new Person("P002", "Siti Aminah", 2002, "Mahasiswa", 160));
        listPerson.add(new Person("P003", "Rian Pratama", 1985, "Dosen", 175));
        listPerson.add(new Person("P004", "Dewi Lestari", 1990, "Staf", 163));
        listPerson.add(new Person("P005", "Ahmad Fauzi", 1998, "Alumni", 170));
        listPerson.add(new Person("P006", "Nadia Putri", 1994, "Staf", 158));
    }
}

import javax.swing.*;
import java.util.Random;
import java.lang.NumberFormatException;


public class ProgramPenjual {
    public static void main(String[] args) {
        Random random = new Random();
        int captcha = random.nextInt(9000) + 1000; // Untuk 4 digit angkat random 

        // GUI Bagian Login
        while (true) {
            JPanel loginPanel = new JPanel();
            loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JTextField captchaField = new JTextField();

            loginPanel.add(new JLabel("Username:"));
            loginPanel.add(usernameField);
            loginPanel.add(new JLabel("Password:"));
            loginPanel.add(passwordField);
            loginPanel.add(new JLabel("Captcha: (" + captcha + ")"));
            loginPanel.add(captchaField);

            int result = JOptionPane.showConfirmDialog(null, loginPanel, "Login Admin", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String userCaptcha = captchaField.getText();

                if (username.equals("kwnhoon") && password.equals("160198") && userCaptcha.equals(String.valueOf(captcha))) {
                    JOptionPane.showMessageDialog(null, "Login berhasil!");
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Login gagal, silakan coba lagi!");
                    captcha = random.nextInt(9000) + 1000; // captcha akan di buat ulang, jika salah
                }
            } else {
                JOptionPane.showMessageDialog(null, "Login dibatalkan.");
                System.exit(0);
            }
        }

        // GUI Bagian penginputan barang pada struk
        while (true) {
            try {
                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        
                JTextField noFakturField = new JTextField();
                JTextField kodeBarangField = new JTextField();
                JTextField namaBarangField = new JTextField();
                JTextField hargaBarangField = new JTextField();
                JTextField jumlahBeliField = new JTextField();
                JTextField namaKasirField = new JTextField();
        
                inputPanel.add(new JLabel("No. Faktur:"));
                inputPanel.add(noFakturField);
                inputPanel.add(new JLabel("Kode Barang:"));
                inputPanel.add(kodeBarangField);
                inputPanel.add(new JLabel("Nama Barang:"));
                inputPanel.add(namaBarangField);
                inputPanel.add(new JLabel("Harga Barang:"));
                inputPanel.add(hargaBarangField);
                inputPanel.add(new JLabel("Jumlah Beli:"));
                inputPanel.add(jumlahBeliField);
                inputPanel.add(new JLabel("Nama Kasir:"));
                inputPanel.add(namaKasirField);
        
                int result = JOptionPane.showConfirmDialog(null, inputPanel, "Input Data Barang", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String noFaktur = noFakturField.getText();
                    String kodeBarang = kodeBarangField.getText();
                    String namaBarang = namaBarangField.getText();
        
                    // Validasi input harga barang
                    if (hargaBarangField.getText().isEmpty()) {
                        throw new NumberFormatException("Harga barang tidak boleh kosong!");
                    }
                    double hargaBarang = Double.parseDouble(hargaBarangField.getText());
        
                    // Validasi input jumlah beli
                    if (jumlahBeliField.getText().isEmpty()) {
                        throw new NumberFormatException("Jumlah beli tidak boleh kosong!");
                    }
                    int jumlahBeli = Integer.parseInt(jumlahBeliField.getText());
        
                    String namaKasir = namaKasirField.getText().trims();
        
                    // Membuat objek struk
                    Struk struk = new Struk(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli, namaKasir);
        
                    // Menampilkan data struk
                    struk.tampilkanData();
                    break; // Keluar dari loop jika input berhasil
                } else {
                    JOptionPane.showMessageDialog(null, "Input data dibatalkan.");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Masukkan angka yang valid untuk harga barang atau jumlah beli.\n" + e.getMessage());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: Input data tidak valid.");
            }
        }
    }
}

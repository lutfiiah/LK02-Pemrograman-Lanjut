import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Kendaraan {
    String platNomor;
    int kapasitas;
    String driver;
    List<String> penumpang;

    Kendaraan(String platNomor, int kapasitas) {
        this.platNomor = platNomor;
        this.kapasitas = kapasitas;
        this.driver = null;
        this.penumpang = new ArrayList<>();
    }

    void naik(String penumpangBaru) throws Exception {
        if (penumpang.size() >= kapasitas) {
            throw new Exception("Kapasitas penumpang terlampaui");
        }
        penumpang.add(penumpangBaru);
    }

    void naik() throws Exception {
        naik("Penumpang");
    }

    void naik(int jumlahPenumpang) throws Exception {
        if (penumpang.size() + jumlahPenumpang > kapasitas) {
            throw new Exception("Jumlah penumpang melebihi kapasitas");
        }
        for (int i = 1; i <= jumlahPenumpang; i++) {
            naik("Penumpang " + i);
        }
    }

    void turun(String penumpangTurun) throws Exception {
        if (!penumpang.contains(penumpangTurun)) {
            throw new Exception("Penumpang tidak ditemukan");
        }
        penumpang.remove(penumpangTurun);
    }

    void turun() throws Exception {
        if (penumpang.isEmpty()) {
            throw new Exception("Tidak ada penumpang dalam kendaraan");
        }
        penumpang.remove(penumpang.size() - 1);
    }

    void tambahDriver(String driverBaru) {
        this.driver = driverBaru;
    }

    int getJumlahPenumpang() {
        return penumpang.size();
    }

    String getDriver() {
        return driver != null ? driver : "Belum ada driver";
    }

    String getPlatNomor() {
        return platNomor;
    }
}

class Bus extends Kendaraan {
    int jumlahPintu;

    Bus(String platNomor, int kapasitas, int jumlahPintu) {
        super(platNomor, kapasitas);
        this.jumlahPintu = jumlahPintu;
    }

    void tambahKonduktor(String namaKonduktor) {
        System.out.println("Konduktor " + namaKonduktor + " ditambahkan ke dalam bus");
    }

    @Override
    void naik(String namaPenumpang) throws Exception {
        super.naik(namaPenumpang);
        System.out.println("Penumpang " + namaPenumpang + " naik ke dalam bus");
    }

    @Override
    void turun() throws Exception {
        if (penumpang.isEmpty()) {
            throw new Exception("Tidak ada penumpang dalam bus");
        }
        System.out.println("Penumpang " + penumpang.get(penumpang.size() - 1) + " turun dari bus");
        penumpang.remove(penumpang.size() - 1);
    }
}

class Truk extends Kendaraan {
    String muatanMaks;

    Truk(String platNomor, int kapasitas, String muatanMaks) {
        super(platNomor, kapasitas);
        this.muatanMaks = muatanMaks;
    }

    void tambahMuatan(String muatanBaru) {
        System.out.println("Muatan " + muatanBaru + " ditambahkan ke dalam truk");
    }

    @Override
    void naik(String muatanBaru) throws Exception {
        super.naik(muatanBaru);
        System.out.println("Muatan " + muatanBaru + " naik ke dalam truk");
    }

    @Override
    void turun() throws Exception {
        if (penumpang.isEmpty()) {
            throw new Exception("Tidak ada penumpang dalam truk");
        }
        System.out.println("Penumpang " + penumpang.get(penumpang.size() - 1) + " turun dari truk");
        penumpang.remove(penumpang.size() - 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kendaraan kendaraan = null;

        while (true) {
            System.out.println("Menu Aplikasi:");
            System.out.println("1. Buat Kendaraan");
            System.out.println("2. Naik Penumpang");
            System.out.println("3. Turun Penumpang");
            System.out.println("4. Tambah Driver");
            System.out.println("5. Lihat Jumlah Penumpang");
            System.out.println("6. Lihat Detail Kendaraan");
            System.out.println("7. Keluar");
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan plat nomor: ");
                    String platNomor = scanner.nextLine().replaceAll("\\s+", "");
                    System.out.print("Masukkan kapasitas kendaraan: ");
                    int kapasitas = scanner.nextInt();
                    System.out.print("Masukkan jenis kendaraan (Bus/Truk): ");
                    String jenisKendaraan = scanner.next();

                    if (jenisKendaraan.equalsIgnoreCase("Bus")) {
                        System.out.print("Masukkan jumlah pintu: ");
                        int jumlahPintu = scanner.nextInt();
                        kendaraan = new Bus(platNomor, kapasitas, jumlahPintu);
                    } else if (jenisKendaraan.equalsIgnoreCase("Truk")) {
                        System.out.print("Masukkan jenis muatan maksimal: ");
                        String muatanMaks = scanner.next();
                        kendaraan = new Truk(platNomor, kapasitas, muatanMaks);
                    } else {
                        System.out.println("Jenis kendaraan tidak valid");
                    }
                    break;
                case 2:
                    if (kendaraan == null) {
                        System.out.println("Belum ada kendaraan yang dibuat");
                        break;
                    }
                    System.out.print("Masukkan jumlah penumpang yang naik: ");
                    int jumlahPenumpangNaik = scanner.nextInt();
                    try {
                        kendaraan.naik(jumlahPenumpangNaik);
                        System.out.println(jumlahPenumpangNaik + " penumpang naik ke dalam kendaraan");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    if (kendaraan == null) {
                        System.out.println("Belum ada kendaraan yang dibuat");
                        break;
                    }
                    try {
                        kendaraan.turun();
                        System.out.println("Satu penumpang turun dari kendaraan");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    if (kendaraan == null) {
                        System.out.println("Belum ada kendaraan yang dibuat");
                        break;
                    }
                    System.out.print("Masukkan nama driver: ");
                    String namaDriver = scanner.nextLine();
                    kendaraan.tambahDriver(namaDriver);
                    System.out.println("Driver ditambahkan: " + namaDriver);
                    break;
                case 5:
                    int jumlahPenumpangSaatIni = kendaraan != null ? kendaraan.getJumlahPenumpang() : 0;
                    System.out.println("Jumlah penumpang saat ini: " + jumlahPenumpangSaatIni);
                    break;
                case 6:
                    if (kendaraan == null) {
                        System.out.println("Belum ada kendaraan yang dibuat");
                        break;
                    }
                    System.out.println("Detail Kendaraan:");
                    System.out.println("Plat Nomor: " + kendaraan.getPlatNomor());
                    System.out.println("Nama Driver: " + kendaraan.getDriver());
                    System.out.println("Jumlah Penumpang: " + kendaraan.getJumlahPenumpang());
                    if (kendaraan instanceof Bus) {
                        Bus bus = (Bus) kendaraan;
                        System.out.println("Jumlah Pintu: " + bus.jumlahPintu);
                    } else if (kendaraan instanceof Truk) {
                        Truk truk = (Truk) kendaraan;
                        System.out.println("Muatan Maks: " + truk.muatanMaks);
                    }
                    break;
                case 7:
                    System.out.println("Keluar dari aplikasi");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }
}

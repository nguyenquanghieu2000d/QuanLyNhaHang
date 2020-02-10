package sample;

public class NhanVien {
    private String MaNhanVien;
    private String Username;
    private String password;
    private String TenNhanVien;
    private String GioiTinh;
    private String DiaChi;
    private String DienThoai;
    private String NgaySinh;
    private String Tuoi;
    private String MaChucVu;

    public NhanVien(String maNhanVien, String username, String password, String tenNhanVien, String gioiTinh, String diaChi, String dienThoai, String ngaySinh, String tuoi, String maChucVu) {
        MaNhanVien = maNhanVien;
        Username = username;
        this.password = password;
        TenNhanVien = tenNhanVien;
        GioiTinh = gioiTinh;
        DiaChi = diaChi;
        DienThoai = dienThoai;
        NgaySinh = ngaySinh;
        Tuoi = tuoi;
        MaChucVu = maChucVu;
    }


    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return password;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public String getTuoi() {
        return Tuoi;
    }

    public String getMaChucVu() {
        return MaChucVu;
    }
}

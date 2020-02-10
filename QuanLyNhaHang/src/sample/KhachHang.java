package sample;

public class KhachHang {
    private String MaKhachHang;
    private String TenKhachHang;
    private String GioiTinh;
    private String DiaChi;
    private String SoDienThoai;
    private String NgaySinh;
    private String Tuoi;

    public KhachHang(String maKhachHang, String tenKhachHang, String gioiTinh, String diaChi, String soDienThoai, String ngaySinh, String tuoi) {
        MaKhachHang = maKhachHang;
        TenKhachHang = tenKhachHang;
        GioiTinh = gioiTinh;
        DiaChi = diaChi;
        SoDienThoai = soDienThoai;
        NgaySinh = ngaySinh;
        Tuoi = tuoi;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public String getTuoi() {
        return Tuoi;
    }

}

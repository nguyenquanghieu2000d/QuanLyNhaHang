package sample;

public class KhoNguyenLieu {
    private String MaNguyenLieu;
    private String TenNguyenLieu;
    private String SoLuong;
    private String DonViTinh;
    private String TinhTrang;

    public KhoNguyenLieu(String maNguyenLieu, String tenNguyenLieu, String soLuong, String donViTinh, String tinhTrang) {
        MaNguyenLieu = maNguyenLieu;
        TenNguyenLieu = tenNguyenLieu;
        SoLuong = soLuong;
        DonViTinh = donViTinh;
        TinhTrang = tinhTrang;
    }

    public String getMaNguyenLieu() {
        return MaNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return TenNguyenLieu;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }
}

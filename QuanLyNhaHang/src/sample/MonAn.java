package sample;

public class MonAn {
    private String MaMonAn;
    private String TenMonAn;
    private String DonViTinh;
    private String TinhTrang;
    private String DonGia;
    public MonAn(String maMonAn, String tenMonAn, String donViTinh, String tinhTrang,String donGia) {
        MaMonAn = maMonAn;
        TenMonAn = tenMonAn;
        DonViTinh = donViTinh;
        TinhTrang = tinhTrang;
        DonGia = donGia;
    }

    public String getMaMonAn() {
        return MaMonAn;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }


    public String getDonViTinh() {
        return DonViTinh;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public String getDonGia() {
        return DonGia;
    }
}

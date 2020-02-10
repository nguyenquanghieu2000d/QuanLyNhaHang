package temp;

public class MonAn {
    private String MaMonAn;
    private String TenMonAn;
    private String DonViTinh;
    private String TinhTrang;

    public MonAn(String maMonAn, String tenMonAn, String donViTinh, String tinhTrang) {
        MaMonAn = maMonAn;
        TenMonAn = tenMonAn;
        DonViTinh = donViTinh;
        TinhTrang = tinhTrang;
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


}

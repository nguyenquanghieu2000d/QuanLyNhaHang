package sample;

public class DanhSachNguyenLieuCuaMonAn extends KhoNguyenLieu {

    public DanhSachNguyenLieuCuaMonAn(String maNguyenLieu, String tenNguyenLieu, String soLuong, String donViTinh, String tinhTrang, String soLuongCan) {
        super(maNguyenLieu, tenNguyenLieu, soLuong, donViTinh, tinhTrang);
        SoLuongCan = soLuongCan;
    }


    private String SoLuongCan;
    public void setSoLuongCan(String soLuongCan) {
        SoLuongCan = soLuongCan;
    }

    public String getSoLuongCan() {
        return SoLuongCan;
    }

    public void Display(){
        System.out.printf("%s %s %s %s %s %s",getMaNguyenLieu(),getTenNguyenLieu(),getSoLuongCan(),getSoLuong(),getDonViTinh(),getTinhTrang());
    }
}

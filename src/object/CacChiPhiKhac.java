/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.Date;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class CacChiPhiKhac {

    private String loaiChiPhi;
    private Date ngay;
    private Integer tienTra;

    public CacChiPhiKhac() {
    }

    public CacChiPhiKhac(String loaiChiPhi, Date ngay, Integer tienTra) {
        this.loaiChiPhi = loaiChiPhi;
        this.ngay = ngay;
        this.tienTra = tienTra;
    }

    public String getLoaiChiPhi() {
        return loaiChiPhi;
    }

    public void setLoaiChiPhi(String loaiChiPhi) {
        this.loaiChiPhi = loaiChiPhi;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Integer getTienTra() {
        return tienTra;
    }

    public void setTienTra(Integer tienTra) {
        this.tienTra = tienTra;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class ImportFile {
    public int docDanhSachSachTuFileExcel(String path) {
        int ketqua = 0;
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            Workbook workBook = new XSSFWorkbook(fis);
            org.apache.poi.ss.usermodel.Sheet firtSheet = workBook.getSheetAt(0);
            Iterator<Row> iRow = firtSheet.iterator();
            JOptionPane.showMessageDialog(null, "Chuẩn bị thêm sách từ file. Nhấn OK và đợi trong giây lát");
            int dong = 0;
            while (iRow.hasNext()) {
                Row row = iRow.next();
                dong++;
                Iterator<Cell> iCell = row.iterator();
                String giaTriCaDong = "";
                while (iCell.hasNext()) {
                    Cell cell = iCell.next();
                    giaTriCaDong += cell.getStringCellValue() + ";";
                }
                if (!giaTriCaDong.equals("")) {
                    giaTriCaDong = giaTriCaDong.substring(0, giaTriCaDong.length() - 1);
                    giaTriCaDong = giaTriCaDong.trim();
                    String[] arr = giaTriCaDong.split(";");
                    if (arr.length == 5) {
//                        String maSach = taoSo.layMaSachTiepTheo();
//                        String tenSach = arr[0];
//                        String tacGia = arr[1];
//                        String nhaXuatBan = arr[2];
//                        String theLoai = arr[3];
//                        String tinhTrang = arr[4];
//                        if (bllSach.themSach(maSach, tenSach, tacGia, nhaXuatBan, theLoai, tinhTrang) > 0) {
//                            ketqua++;
//                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Sách ở dòng " + dong + " không được thêm do thiếu thông tin");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }
}

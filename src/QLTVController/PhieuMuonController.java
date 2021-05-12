/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLTVController;

import Connect.ConnectToSql;
import QLTVModel.PhieuMuonChiTiet;
import QLTVModel.PhieuMuonClass;
import QLTVModel.PhieuTraChiTiet;
import QLTVModel.SachClass;
import view.phieumuon.ThongTinPhieuThem;
import Util.LuuDuLieu;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author WIN1064
 */
public class PhieuMuonController {

    Connection conn;
    PreparedStatement pst;
    CallableStatement stm;
    SachController sc = new SachController();

    public PhieuMuonController() {
    }

    public ArrayList<PhieuMuonClass> getPM(String tuNgay, String denNgay, String maPhieu) {
        conn = ConnectToSql.connectDb();
        ArrayList<PhieuMuonClass> dsPM = new ArrayList<PhieuMuonClass>();
        String sql;
        if (tuNgay == null && denNgay == null && maPhieu == "") {
            sql = "select * from PhieuMuon where DaTra = 0";
        } else if (maPhieu == "") {
            sql = "select * from PhieuMuon where DaTra = 0 AND NgayMuon >= '" + tuNgay + "' AND NgayMuon <= '" + denNgay + "'";
        } else if (tuNgay == null && denNgay == null) {
            sql = "select * from PhieuMuon where DaTra = 0 AND MaPhieu like '%" + maPhieu + "%'";
        } else {
            sql = "select * from PhieuMuon where DaTra = 0 AND NgayMuon >= '" + tuNgay + "' AND NgayMuon <= '" + denNgay + "' AND MaPhieu like '%" + maPhieu + "%'";
        }
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PhieuMuonClass pm = new PhieuMuonClass(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getInt(4));
                dsPM.add(pm);
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "@");
        }
        return dsPM;
    }

    public ArrayList<PhieuMuonClass> getPhieuTra(String tuNgay, String denNgay, String maPhieu, String kieuLoc) {
        conn = ConnectToSql.connectDb();
        ArrayList<PhieuMuonClass> dsPM = new ArrayList<PhieuMuonClass>();
        String sql;

        if (tuNgay == null && denNgay == null && maPhieu == "") {
            sql = "select * from PhieuMuon where DaTra = 1";
        } else if (maPhieu == "") {
            if (kieuLoc == "Ngày mượn") {
                sql = "select * from PhieuMuon where DaTra = 1 AND NgayMuon >= '" + tuNgay + "' AND NgayMuon <= '" + denNgay + "'";
            } else {
                sql = "select * from PhieuMuon where DaTra = 1 AND NgayTra >= '" + tuNgay + "' AND NgayTra <= '" + denNgay + "'";
            }
        } else if (tuNgay == null && denNgay == null) {
            sql = "select * from PhieuMuon where DaTra = 1 AND MaPhieu like '%" + maPhieu + "%'";
        } else {
            if (kieuLoc == "Ngày mượn") {
                sql = "select * from PhieuMuon where DaTra = 1 AND NgayMuon >= '" + tuNgay + "' AND NgayMuon <= '" + denNgay + "' AND MaPhieu like '%" + maPhieu + "%'";
            } else {
                sql = "select * from PhieuMuon where DaTra = 1 AND NgayTra >= '" + tuNgay + "' AND NgayTra <= '" + denNgay + "' AND MaPhieu like '%" + maPhieu + "%'";
            }

        }

        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PhieuMuonClass pm = new PhieuMuonClass(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getInt(4));
                dsPM.add(pm);
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "@");
        }
        return dsPM;
    }

    public String getMaSinhVien(String maPhieu) {
        conn = ConnectToSql.connectDb();
        String maSV = "";
        String sql = "Select MaSinhVien from PhieuMuon where MaPhieu = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, maPhieu);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                maSV = rs.getString(1);
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "2");
        }
        return maSV;
    }

    public PhieuMuonChiTiet getMotPhieu(String maPhieu) {
        conn = ConnectToSql.connectDb();
        PhieuMuonChiTiet phieu = null;
        String sql = "SELECT PhieuMuon.MaPhieu,MaSach,TienCoc,NgayMuon,NgayTra,GhiChu FROM "
                + "CT_PhieuMuon inner join PhieuMuon on CT_PhieuMuon.MaPhieu = PhieuMuon.MaPhieu where PhieuMuon.MaPhieu = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, maPhieu);
            ResultSet rs = pst.executeQuery();
            ArrayList<SachClass> arr = new ArrayList<SachClass>();
            phieu = new PhieuMuonChiTiet();
            int tongTien = 0;
            while (rs.next()) {
                phieu.setMaPhieu(rs.getString(1));
                phieu.setMaSV(getMaSinhVien(maPhieu));
                arr.add(sc.timKiemSachMuon(rs.getString(2)));
                tongTien += Integer.parseInt(rs.getString(3));
                phieu.setNgayMuon(rs.getString(4));
                phieu.setNgayTra(rs.getString(5));
                phieu.setGhiChu(rs.getString(6));
                phieu.setNgayTra(rs.getString(5));
            }
            phieu.setArrSach(arr);
            phieu.setTienCoc(tongTien);
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "#");
        }
        return phieu;
    }


    public ArrayList<PhieuMuonChiTiet> getPMCT() {
        ArrayList<PhieuMuonChiTiet> dsPMCT = new ArrayList<PhieuMuonChiTiet>();
        ArrayList<PhieuMuonClass> dsPM = getPM(null, null, "");
        for (PhieuMuonClass p : dsPM) {
            PhieuMuonChiTiet pmct = getMotPhieu(p.getMaPhieu());
            dsPMCT.add(pmct);
        }
        return dsPMCT;
    }

    public ArrayList<PhieuMuonChiTiet> getPMCTDaTra() {
        ArrayList<PhieuMuonChiTiet> dsPMCT = new ArrayList<PhieuMuonChiTiet>();
        ArrayList<PhieuMuonClass> dsPM = getPhieuTra(null, null, "", "");
        for (PhieuMuonClass p : dsPM) {
            PhieuMuonChiTiet pmct = getMotPhieu(p.getMaPhieu());
            dsPMCT.add(pmct);
        }
        retur/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLTVController;

import Connect.ConnectToSql;
import QLTVModel.PhieuMuonChiTiet;
import QLTVModel.PhieuMuonClass;
import QLTVModel.PhieuTraChiTiet;
import QLTVModel.SachClass;
import view.phieumuon.ThongTinPhieuThem;
import Util.LuuDuLieu;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author WIN1064
 */
public class PhieuMuonController {

    Connection conn;
    PreparedStatement pst;
    CallableStatement stm;
    SachController sc = new SachController();

    public PhieuMuonController() {
    }

    public ArrayList<PhieuMuonClass> getPM(String tuNgay, String denNgay, String maPhieu) {
        conn = ConnectToSql.connectDb();
        ArrayList<PhieuMuonClass> dsPM = new ArrayList<PhieuMuonClass>();
        String sql;
        if (tuNgay == null && denNgay == null && maPhieu == "") {
            sql = "select * from PhieuMuon where DaTra = 0";
        } else if (maPhieu == "") {
            sql = "select * from PhieuMuon where DaTra = 0 AND NgayMuon >= '" + tuNgay + "' AND NgayMuon <= '" + denNgay + "'";
        } else if (tuNgay == null && denNgay == null) {
            sql = "select * from PhieuMuon where DaTra = 0 AND MaPhieu like '%" + maPhieu + "%'";
        } else {
            sql = "select * from PhieuMuon where DaTra = 0 AND NgayMuon >= '" + tuNgay + "' AND NgayMuon <= '" + denNgay + "' AND MaPhieu like '%" + maPhieu + "%'";
        }
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PhieuMuonClass pm = new PhieuMuonClass(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getInt(4));
                dsPM.add(pm);
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "@");
        }
        return dsPM;
    }

    public ArrayList<PhieuMuonClass> getPhieuTra(String tuNgay, String denNgay, String maPhieu, String kieuLoc) {
        conn = ConnectToSql.connectDb();
        ArrayList<PhieuMuonClass> dsPM = new ArrayList<PhieuMuonClass>();
        String sql;

        if (tuNgay == null && denNgay == null && maPhieu == "") {
            sql = "select * from PhieuMuon where DaTra = 1";
        } else if (maPhieu == "") {
            if (kieuLoc == "Ngày mượn") {
                sql = "select * from PhieuMuon where DaTra = 1 AND NgayMuon >= '" + tuNgay + "' AND NgayMuon <= '" + denNgay + "'";
            } else {
                sql = "select * from PhieuMuon where DaTra = 1 AND NgayTra >= '" + tuNgay + "' AND NgayTra <= '" + denNgay + "'";
            }
        } else if (tuNgay == null && denNgay == null) {
            sql = "select * from PhieuMuon where DaTra = 1 AND MaPhieu like '%" + maPhieu + "%'";
        } else {
            if (kieuLoc == "Ngày mượn") {
                sql = "select * from PhieuMuon where DaTra = 1 AND NgayMuon >= '" + tuNgay + "' AND NgayMuon <= '" + denNgay + "' AND MaPhieu like '%" + maPhieu + "%'";
            } else {
                sql = "select * from PhieuMuon where DaTra = 1 AND NgayTra >= '" + tuNgay + "' AND NgayTra <= '" + denNgay + "' AND MaPhieu like '%" + maPhieu + "%'";
            }

        }

        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PhieuMuonClass pm = new PhieuMuonClass(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getInt(4));
                dsPM.add(pm);
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "@");
        }
        return dsPM;
    }

    public String getMaSinhVien(String maPhieu) {
        conn = ConnectToSql.connectDb();
        String maSV = "";
        String sql = "Select MaSinhVien from PhieuMuon where MaPhieu = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, maPhieu);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                maSV = rs.getString(1);
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "2");
        }
        return maSV;
    }

    public PhieuMuonChiTiet getMotPhieu(String maPhieu) {
        conn = ConnectToSql.connectDb();
        PhieuMuonChiTiet phieu = null;
        String sql = "SELECT PhieuMuon.MaPhieu,MaSach,TienCoc,NgayMuon,NgayTra,GhiChu FROM "
                + "CT_PhieuMuon inner join PhieuMuon on CT_PhieuMuon.MaPhieu = PhieuMuon.MaPhieu where PhieuMuon.MaPhieu = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, maPhieu);
            ResultSet rs = pst.executeQuery();
            ArrayList<SachClass> arr = new ArrayList<SachClass>();
            phieu = new PhieuMuonChiTiet();
            int tongTien = 0;
            while (rs.next()) {
                phieu.setMaPhieu(rs.getString(1));
                phieu.setMaSV(getMaSinhVien(maPhieu));
                arr.add(sc.timKiemSachMuon(rs.getString(2)));
                tongTien += Integer.parseInt(rs.getString(3));
                phieu.setNgayMuon(rs.getString(4));
                phieu.setNgayTra(rs.getString(5));
                phieu.setGhiChu(rs.getString(6));
                phieu.setNgayTra(rs.getString(5));
            }
            phieu.setArrSach(arr);
            phieu.setTienCoc(tongTien);
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "#");
        }
        return phieu;
    }


    public ArrayList<PhieuMuonChiTiet> getPMCT() {
        ArrayList<PhieuMuonChiTiet> dsPMCT = new ArrayList<PhieuMuonChiTiet>();
        ArrayList<PhieuMuonClass> dsPM = getPM(null, null, "");
        for (PhieuMuonClass p : dsPM) {
            PhieuMuonChiTiet pmct = getMotPhieu(p.getMaPhieu());
            dsPMCT.add(pmct);
        }
        return dsPMCT;
    }

    public ArrayList<PhieuMuonChiTiet> getPMCTDaTra() {
        ArrayList<PhieuMuonChiTiet> dsPMCT = new ArrayList<PhieuMuonChiTiet>();
        ArrayList<PhieuMuonClass> dsPM = getPhieuTra(null, null, "", "");
        for (PhieuMuonClass p : dsPM) {
            PhieuMuonChiTiet pmct = getMotPhieu(p.getMaPhieu());
            dsPMCT.add(pmct);
        }
        return dsPMCT;
    }

    public void themPhieu(PhieuMuonClass pm, ArrayList<SachClass> arrSach) {
        conn = ConnectToSql.connectDb();
        try {
            stm = conn.prepareCall("{call InSertInPM(?,?,?,?,?)}");
            stm.setString("maSinhVien", pm.getMaSV());
            stm.setString("ngayMuon", pm.getNgayMuon());
            stm.setInt("daTra", pm.getDaTra());
            stm.setString("ghiChu", pm.getGhiChu());
            stm.registerOutParameter("maPhieu", Types.VARCHAR);
            stm.execute();
            String m_count = stm.getString(5);
//            LuuDuLieu.currentPhieuMuon.setMaPhieu(m_count);
//            int tongTienCoc = 0;
//            for (int i = 0; i < arrSach.size(); i++) {
//                tongTienCoc += (arrSach.get(i).getGia() * arrSach.get(i).getSoLuong());
//            }

            for (int i = 0; i < arrSach.size(); i++) {
                stm = conn.prepareCall("{call InsertCTPhieuMuon(?,?,?)}");
                stm.setString(1, m_count);
                stm.setString(2, arrSach.get(i).getMaSach());
                stm.setInt(3, arrSach.get(i).getGia());
                stm.execute();
                stm.close();
            }
            ConnectToSql.closeConnect();
            LuuDuLieu.currentPhieuMuonChiTiet = getMotPhieu(m_count);
//
            ThongTinPhieuThem ttpt = new ThongTinPhieuThem();
            ttpt.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "2");
            ex.printStackTrace();

        }
    }

    public void updateDaTra(String maPhieu) {
        conn = ConnectToSql.connectDb();
        String sql = "update PhieuMuon set DaTra = 1, NgayTra = ? where MaPhieu = ?";
        try {
            pst = conn.prepareStatement(sql);
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(now);
            pst.setString(1, date);
            pst.setString(2, maPhieu);
            pst.execute();
            pst.close();
            ConnectToSql.closeConnect();
            LuuDuLieu.dsPMCT = getPMCT();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public ArrayList<PhieuMuonChiTiet> locPhieu(String tuNgay, String denNgay, String keywords) {
        ArrayList<PhieuMuonChiTiet> dsPMCT = new ArrayList<PhieuMuonChiTiet>();
        ArrayList<PhieuMuonClass> dsPM = getPM(tuNgay, denNgay, keywords);
        for (PhieuMuonClass p : dsPM) {
            PhieuMuonChiTiet pmct = getMotPhieu(p.getMaPhieu());
            dsPMCT.add(pmct);
        }
        return dsPMCT;
    }

    public ArrayList<PhieuMuonChiTiet> locPhieuTra(String tuNgay, String denNgay, String maPhieu, String kieuLoc) {
        ArrayList<PhieuMuonChiTiet> dsPMCT = new ArrayList<PhieuMuonChiTiet>();
        ArrayList<PhieuMuonClass> dsPM = getPhieuTra(tuNgay, denNgay, maPhieu, kieuLoc);
        for (PhieuMuonClass p : dsPM) {
            PhieuMuonChiTiet pmct = getMotPhieu(p.getMaPhieu());
            dsPMCT.add(pmct);
        }
        return dsPMCT;
    }

    public ArrayList<PhieuMuonClass> getPMSV(String maSV) {
        conn = ConnectToSql.connectDb();
        ArrayList<PhieuMuonClass> dsPM = new ArrayList<PhieuMuonClass>();
        String sql = "Select * from PhieuMuon where MaSinhVien = ? AND DaTra = 0";
      
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, maSV);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PhieuMuonClass pm = new PhieuMuonClass(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getInt(4));
                dsPM.add(pm);
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "@");
        }
        return dsPM;
    }

    public int kiemTraSinhVien(ArrayList<SachClass> arrSach, String maSV) {
        ArrayList<PhieuMuonClass> dsPM = getPMSV(maSV);
        for (int i = 0; i < dsPM.size(); i++) {
            PhieuMuonChiTiet pmct = getMotPhieu(dsPM.get(i).getMaPhieu());
            for (int j = 0; j < arrSach.size(); j++) {
                if (pmct.getArrSach().contains(arrSach.get(j))) {
                    return 1;
                }
            }
        }
        return 0;
    }
}

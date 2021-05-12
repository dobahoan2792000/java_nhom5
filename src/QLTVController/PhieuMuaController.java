/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLTVController;

import Connect.ConnectToSql;
import QLTVModel.PhieuMuaChiTiet;
import QLTVModel.PhieuMuaClass;
import QLTVModel.SachClass;
import Util.LuuDuLieu;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Date;
import view.phieumua.ThongTinPhieuMua;
/**
 *
 * @author WIN1064
 */
public class PhieuMuaController {
    Connection conn;
    PreparedStatement pst;
    CallableStatement stm;
    SachController sc = new SachController();

    public PhieuMuaController() {
    }
    public ArrayList<PhieuMuaClass> getPM(String tuNgay, String denNgay, String maPhieu) {
        conn = ConnectToSql.connectDb();
        ArrayList<PhieuMuaClass> dsPM = new ArrayList<PhieuMuaClass>();
        String sql;
        if (tuNgay == null && denNgay == null && maPhieu == "") {
            sql = "select * from PhieuMua";
        } else if (maPhieu == "") {
            sql = "select * from PhieuMua where NgayDat >= '" + tuNgay + "' AND NgayDat <= '" + denNgay + "'";
        } else if (tuNgay == null && denNgay == null) {
            sql = "select * from PhieuMua where MaMua like '%" + maPhie/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLTVController;

import Connect.ConnectToSql;
import QLTVModel.PhieuMuaChiTiet;
import QLTVModel.PhieuMuaClass;
import QLTVModel.SachClass;
import Util.LuuDuLieu;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Date;
import view.phieumua.ThongTinPhieuMua;
/**
 *
 * @author WIN1064
 */
public class PhieuMuaController {
    Connection conn;
    PreparedStatement pst;
    CallableStatement stm;
    SachController sc = new SachController();

    public PhieuMuaController() {
    }
    public ArrayList<PhieuMuaClass> getPM(String tuNgay, String denNgay, String maPhieu) {
        conn = ConnectToSql.connectDb();
        ArrayList<PhieuMuaClass> dsPM = new ArrayList<PhieuMuaClass>();
        String sql;
        if (tuNgay == null && denNgay == null && maPhieu == "") {
            sql = "select * from PhieuMua";
        } else if (maPhieu == "") {
            sql = "select * from PhieuMua where NgayDat >= '" + tuNgay + "' AND NgayDat <= '" + denNgay + "'";
        } else if (tuNgay == null && denNgay == null) {
            sql = "select * from PhieuMua where MaMua like '%" + maPhieu + "%'";
        } else {
            sql = "select * from PhieuMua where NgayDat >= '" + tuNgay + "' AND NgayDat <= '" + denNgay + "' AND MaMua like '%" + maPhieu + "%'";
        }
        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PhieuMuaClass pm = new PhieuMuaClass(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getInt(3));
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
    public ArrayList<PhieuMuaChiTiet> getPMCT() {
        ArrayList<PhieuMuaChiTiet> dsPMCT = new ArrayList<PhieuMuaChiTiet>();
        ArrayList<PhieuMuaClass> dsPM = getPM(null, null, "");
        for (PhieuMuaClass p : dsPM) {
            PhieuMuaChiTiet pmct = getMotPhieu(p.getMaMua());
            dsPMCT.add(pmct);
        }
        return dsPMCT;
    }
    public int themPhieu(PhieuMuaClass pm, ArrayList<SachClass> arrSach) {
        conn = ConnectToSql.connectDb();
        int kq = 0;
        try {
            stm = conn.prepareCall("{call InsertInPhieuMua(?,?,?,?,?)}");
            stm.setString("maSinhVien", pm.getMaSV());
            stm.setString("ngayDat", pm.getNgayDat());
            stm.setInt("daTra", pm.getDaTra());
            stm.setString("ghiChu", pm.getGhiChu());
            stm.registerOutParameter("maMua", Types.VARCHAR);
            stm.execute();
            String m_count = stm.getString(5);
            for (int i = 0; i < arrSach.size(); i++) {
               
                stm = conn.prepareCall("{call InsertCTPhieuMua(?,?,?,?)}");
                stm.setString(1, m_count);
                stm.setString(2, arrSach.get(i).getMaSach());
                stm.setInt(3,arrSach.get(i).getSoLuongMua());
                stm.setInt(4, arrSach.get(i).getGia() * arrSach.get(i).getSoLuongMua());
                stm.execute();
                stm.close();
            }
            ConnectToSql.closeConnect();
            LuuDuLieu.currentPhieuMuaChiTiet = getMotPhieu(m_count);
            kq = 1;
            ThongTinPhieuMua ttpm = new ThongTinPhieuMua();
            ttpm.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "2");
            ex.printStackTrace();
        }
        return kq;
    }
    public String getMaSinhVien(String maMua) {
        conn = ConnectToSql.connectDb();
        String maSV = "";
        String sql = "Select MaSinhVien from PhieuMua where MaMua = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, maMua);
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

    public PhieuMuaChiTiet getMotPhieu(String maMua) {
        conn = ConnectToSql.connectDb();
        PhieuMuaChiTiet phieu = null;
        String sql = "SELECT PhieuMua.MaMua,MaSach,TienPhaiTra,SoLuong,NgayDat,GhiChu,DaTra FROM "
                + "CT_PhieuMua inner join PhieuMua on CT_PhieuMua.MaMua = PhieuMua.MaMua where PhieuMua.MaMua = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, maMua);
            ResultSet rs = pst.executeQuery();
            ArrayList<SachClass> arr = new ArrayList<SachClass>();
            phieu = new PhieuMuaChiTiet();
            int tongTien = 0;
            while (rs.next()) {
                phieu.setMaMua(rs.getString(1));
                phieu.setMaSV(getMaSinhVien(maMua));
                SachClass s = sc.timKiemSachMua(rs.getString(2));
                s.setSoLuongMua(Integer.parseInt(rs.getString(4)));
                arr.add(s);
                tongTien += Integer.parseInt(rs.getString(3));
                phieu.setNgayDat(rs.getString(5));
                phieu.setGhiChu(rs.getString(6));
                phieu.setDaTra(rs.getInt(7));
            }
            phieu.setArrSach(arr);
            phieu.setTienPhaiTra(tongTien);
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "#");
        }
        return phieu;
    }
    public void updateDaTra(String maMua) {
        conn = ConnectToSql.connectDb();
        String sql = "update PhieuMua set DaTra = 1 where MaMua = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, maMua);
            pst.execute();
            pst.close();
            ConnectToSql.closeConnect();
            LuuDuLieu.dsPhieuMuaCT = getPMCT();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }
    public ArrayList<PhieuMuaChiTiet> locPhieu(String tuNgay, String denNgay, String keywords) {
        ArrayList<PhieuMuaChiTiet> dsPMCT = new ArrayList<PhieuMuaChiTiet>();
        ArrayList<PhieuMuaClass> dsPM = getPM(tuNgay, denNgay, keywords);
        for (PhieuMuaClass p : dsPM) {
            PhieuMuaChiTiet pmct = getMotPhieu(p.getMaMua());
            dsPMCT.add(pmct);
        }
        return dsPMCT;
    }
}

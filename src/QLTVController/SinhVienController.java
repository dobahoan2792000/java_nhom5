/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLTVController;

import Connect.ConnectToSql;
import QLTVModel.SinhVienClass;
import view.sinhvien.ThemSinhVienThanhCong;
import Util.LuuDuLieu;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author WIN1064
 */
public class SinhVienController {

    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    CallableStatement stm;

    public SinhVienController() {

    }

    public ArrayList<SinhVienClass> getSV() {
        conn = ConnectToSql.connectDb();
        ArrayList<SinhVienClass> dsSV = new ArrayList<SinhVienClass>();
        String sql = "select * from SinhVien";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                SinhVienClass sv = new SinhVienClass(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                dsSV.add(sv);
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return dsSV;
    }

    public String getTenSV(String maSV) {
        conn = ConnectToSql.connectDb();
        String name = "";
        String sql = "select * from SinhVien where MaSinhVien ='" + maSV + "'";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                name = rs.getString(2);
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return name;
    }

    public int themSV(SinhVienClass sv) throws Exception {
        conn = ConnectToSql.connectDb();
        try {
            stm = conn.prepareCall("{call InsertSinhVien(?,?,?,?,?,?,?)}");
            stm.setString(1, sv.getTenSV());
            stm.setString(2, (String) sv.getNganhHoc());
            stm.setString(3, sv.getNgaySinh());
            stm.setString(4, sv.getGioiTinh());
            stm.setString(5, sv.getSDT());
            stm.setString(6, sv.getEmail());
            stm.registerOutParameter(7, Types.VARCHAR);
            stm.execute();
            String m_count = stm.getString(7);
            LuuDuLieu.currentSV.setMaSV(m_count);
            ThemSinhVienThanhCong xn = new ThemSinhVienThanhCong();
            xn.setVisible(true);
            stm.close();
            ConnectToSql.closeConnect();
            return 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return 0;
        }
    }

    public void suaSV(SinhVienClass s) {
        conn = ConnectToSql.connectDb();
        String sql = "{call UpdateSinhVien (?, ?, ?, ?, ?, ?, ?)}";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, s.getTenSV());
            pst.setString(2, s.getNganhHoc());
            pst.setString(3, s.getNgaySinh());
            pst.setString(4, s.getGioiTinh());
            pst.setString(5, s.getSDT());
            pst.setString(6, s.getEmail());
            pst.setString(7, s.getMaSV());
            pst.executeUpdate();
            conn.commit();
            pst.close();
            ConnectToSql.closeConnect();
            LuuDuLieu.dsSV = getSV();
            JOptionPane.showMessageDialog(null, "S???a th??nh c??ng");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void xoaSV(SinhVienClass s) {
        conn = ConnectToSql.connectDb();
        String sql = "DELETE SinhVien WHERE MaSinhVien = ?";
        try {
            pst = conn.prepareStatement(sql);

            pst.setString(1, s.getMaSV());
            pst.executeUpdate();
            conn.commit();
            pst.close();
            ConnectToSql.closeConnect();
            LuuDuLieu.dsSV = getSV();
            
            JOptionPane.showMessageDialog(null, "X??a th??nh c??ng");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public ArrayList<SinhVienClass> timKiem(String keywords) {
        conn = ConnectToSql.connectDb();
        ArrayList<SinhVienClass> dsSV = new ArrayList<SinhVienClass>();
        String sql = "select * from SinhVien where MaSinhVien like '%" + keywords + "%' OR TenSinhVien like N'%" + keywords + "%'";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                SinhVienClass s = new SinhVienClass(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                dsSV.add(s);
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return dsSV;
    }

}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLTVController;

import Connect.ConnectToSql;
import QLTVModel.SinhVienClass;
import view.sinhvien.ThemSinhVienThanhCong;
import Util.LuuDuLieu;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author WIN1064
 */
public class SinhVienController {

    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    CallableStatement stm;

    public SinhVienController() {

    }

    public ArrayList<SinhVienClass> getSV() {
        conn = ConnectToSql.connectDb();
        ArrayList<SinhVienClass> dsSV = new ArrayList<SinhVienClass>();
        String sql = "select * from SinhVien";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                SinhVienClass sv = new SinhVienClass(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                dsSV.add(sv);
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return dsSV;
    }

    public String getTenSV(String maSV) {
        conn = ConnectToSql.connectDb();
        String name = "";
        String sql = "select * from SinhVien where MaSinhVien ='" + maSV + "'";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                name = rs.getString(2);
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return name;
    }

    public int themSV(SinhVienClass sv) throws Exception {
        conn = ConnectToSql.connectDb();
        try {
            stm = conn.prepareCall("{call InsertSinhVien(?,?,?,?,?,?,?)}");
            stm.setString(1, sv.getTenSV());
            stm.setString(2, (String) sv.getNganhHoc());
            stm.setString(3, sv.getNgaySinh());
            stm.setString(4, sv.getGioiTinh());
            stm.setString(5, sv.getSDT());
            stm.setString(6, sv.getEmail());
            stm.registerOutParameter(7, Types.VARCHAR);
            stm.execute();
            String m_count = stm.getString(7);
            LuuDuLieu.currentSV.setMaSV(m_count);
            ThemSinhVienThanhCong xn = new ThemSinhVienThanhCong();
            xn.setVisible(true);
            stm.close();
            ConnectToSql.closeConnect();
            return 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return 0;
        }
    }

    public void suaSV(SinhVienClass s) {
        conn = ConnectToSql.connectDb();
        String sql = "{call UpdateSinhVien (?, ?, ?, ?, ?, ?, ?)}";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, s.getTenSV());
            pst.setString(2, s.getNganhHoc());
            pst.setString(3, s.getNgaySinh());
            pst.setString(4, s.getGioiTinh());
            pst.setString(5, s.getSDT());
            pst.setString(6, s.getEmail());
            pst.setString(7, s.getMaSV());
            pst.executeUpdate();
            conn.commit();
            pst.close();
            ConnectToSql.closeConnect();
            LuuDuLieu.dsSV = getSV();
            JOptionPane.showMessageDialog(null, "S???a th??nh c??ng");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public void xoaSV(SinhVienClass s) {
        conn = ConnectToSql.connectDb();
        String sql = "DELETE SinhVien WHERE MaSinhVien = ?";
        try {
            pst = conn.prepareStatement(sql);

            pst.setString(1, s.getMaSV());
            pst.executeUpdate();
            conn.commit();
            pst.close();
            ConnectToSql.closeConnect();
            LuuDuLieu.dsSV = getSV();
            
            JOptionPane.showMessageDialog(null, "X??a th??nh c??ng");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public ArrayList<SinhVienClass> timKiem(String keywords) {
        conn = ConnectToSql.connectDb();
        ArrayList<SinhVienClass> dsSV = new ArrayList<SinhVienClass>();
        String sql = "select * from SinhVien where MaSinhVien like '%" + keywords + "%' OR TenSinhVien like N'%" + keywords + "%'";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                SinhVienClass s = new SinhVienClass(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                dsSV.add(s);
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return dsSV;
    }

}

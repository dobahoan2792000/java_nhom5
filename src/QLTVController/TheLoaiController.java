/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLTVController;

import Connect.ConnectToSql;
import QLTVModel.TheLoaiClass;
import Util.LuuDuLieu;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Types;
/**
 *
 * @author WIN1064
 */
public class TheLoaiController {
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    CallableStatement stm;

    public TheLoaiController() {
    }
    public ArrayList<TheLoaiClass> getTL() {
        conn = ConnectToSql.connectDb();
        ArrayList<TheLoaiClass> dsTL = new ArrayList<TheLoaiClass>();
        String sql = "select * from TheLoai";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                TheLoaiClass tl;
                try {
                    tl = new TheLoaiClass(rs.getString(1), rs.getString(2));
                    dsTL.add(tl);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Thể loại lấy ra không đúng định dạng");
                }
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return dsTL;
    }
    public String getTenTL(String maTL) {
        conn = ConnectToSql.connectDb();
        String name = "";
        String sql = "select * from TheLoai where MaTheLoai ='" + maTL + "'";
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
     public void themTL(TheLoaiClass tl) throws Exception {
         conn = ConnectToSql.connectDb();
        try {
            stm = conn.prepareCall("{call InsertTheLoai(?,?)}");
            stm.setString(1, tl.getTenTheLoai());
            stm.registerOutParameter(2, Types.VARCHAR);
            stm.execute();
            String m_count = stm.getString(2);
            LuuDuLieu.currentTheLoai.setMaTheLoai(m_count);
            LuuDuLieu.dsTL.add(LuuDuLieu.currentTheLoai);
//            ThemSachThanhCong stv = new ThemSachThanhCong();
//            stv.setVisible(true);
            stm.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     public void suaTheLoai(TheLoaiClass tl) {
         conn = ConnectToSql.connectDb();
        String sql = "{call UpdateTheLoai (?, ?)}";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, tl.getTenTheLoai());
            pst.setString(2, tl.getMaTheLoai());
           
            pst.executeUpdate();
//            conn.commit();
            pst.close();
            ConnectToSql.closeConnect();
            LuuDuLieu.dsTL = getTL();
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     public void xoaTheLoai(TheLoaiClass tl) {
         conn = ConnectToSql.connectDb();
        String sql = "DELETE TheLoai WHERE MaTheLoai = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, tl.getMaTheLoai());
            pst.executeUpdate();
            pst.close();
            ConnectToSql.closeConnect();
            LuuDuLieu.dsTL = getTL();
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        } catch (SQLException ex) {
            if(ex.getMessage().contains("conflict"))
            {
                 JOptionPane.showMessageDialog(null, "Thể loại này đang gắn vào sách");
            }
            else
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     public ArrayList<TheLoaiClass> timKiem(String keywords){
         conn = ConnectToSql.connectDb();
         ArrayList<TheLoaiClass> dsTL = new ArrayList<TheLoaiClass>();
        String sql = "select * from TheLoai where MaTheLoai like '%" + keywords + "%' OR TenTheLoai like N'%" + keywords + "%'";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                TheLoaiClass tl;
                try {
                    tl = new TheLoaiClass(rs.getString(1), rs.getString(2));
                    dsTL.add(tl);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return dsTL;
    }
}
                                   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLTVController;

import Connect.ConnectToSql;
import QLTVModel.TheLoaiClass;
import Util.LuuDuLieu;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Types;
/**
 *
 * @author WIN1064
 */
public class TheLoaiController {
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    CallableStatement stm;

    public TheLoaiController() {
    }
    public ArrayList<TheLoaiClass> getTL() {
        conn = ConnectToSql.connectDb();
        ArrayList<TheLoaiClass> dsTL = new ArrayList<TheLoaiClass>();
        String sql = "select * from TheLoai";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                TheLoaiClass tl;
                try {
                    tl = new TheLoaiClass(rs.getString(1), rs.getString(2));
                    dsTL.add(tl);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Thể loại lấy ra không đúng định dạng");
                }
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return dsTL;
    }
    public String getTenTL(String maTL) {
        conn = ConnectToSql.connectDb();
        String name = "";
        String sql = "select * from TheLoai where MaTheLoai ='" + maTL + "'";
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
     public void themTL(TheLoaiClass tl) throws Exception {
         conn = ConnectToSql.connectDb();
        try {
            stm = conn.prepareCall("{call InsertTheLoai(?,?)}");
            stm.setString(1, tl.getTenTheLoai());
            stm.registerOutParameter(2, Types.VARCHAR);
            stm.execute();
            String m_count = stm.getString(2);
            LuuDuLieu.currentTheLoai.setMaTheLoai(m_count);
            LuuDuLieu.dsTL.add(LuuDuLieu.currentTheLoai);
//            ThemSachThanhCong stv = new ThemSachThanhCong();
//            stv.setVisible(true);
            stm.close();
            ConnectToSql.closeConnect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     public void suaTheLoai(TheLoaiClass tl) {
         conn = ConnectToSql.connectDb();
        String sql = "{call UpdateTheLoai (?, ?)}";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, tl.getTenTheLoai());
            pst.setString(2, tl.getMaTheLoai());
           
            pst.executeUpdate();
//            conn.commit();
            pst.close();
            ConnectToSql.closeConnect();
            LuuDuLieu.dsTL = getTL();
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     public void xoaTheLoai(TheLoaiClass tl) {
         conn = ConnectToSql.connectDb();
        String sql = "DELETE TheLoai WHERE MaTheLoai = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, tl.getMaTheLoai());
            pst.executeUpdate();
            pst.close();
            ConnectToSql.closeConnect();
            LuuDuLieu.dsTL = getTL();
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        } catch (SQLException ex) {
            if(ex.getMessage().contains("conflict"))
            {
                 JOptionPane.showMessageDialog(null, "Thể loại này đang gắn vào sách");
            }
            else
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     public ArrayList<TheLoaiClass> timKiem(String keywords){
         conn = ConnectToSql.connectDb();
         ArrayList<TheLoaiClass> dsTL = new ArrayList<TheLoaiClass>();
        String sql = "select * from TheLoai where MaTheLoai like '%" + keywords + "%' OR TenTheLoai like N'%" + keywords + "%'";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                TheLoaiClass tl;
                try {
                    tl = new TheLoaiClass(rs.getString(1), rs.getString(2));
                    dsTL.add(tl);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
            }
            pst.close();
            rs.close();
            ConnectToSql.closeConnect();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return dsTL;
    }
}

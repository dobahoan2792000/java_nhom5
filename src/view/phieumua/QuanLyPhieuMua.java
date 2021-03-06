/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.phieumua;

import QLTVController.PhieuMuaController;
import QLTVModel.PhieuMuaChiTiet;
import Util.CustomTablePhieuMua;
import view.phieumuon.*;
import Util.CustomTablePhieuMuon;
import Util.LuuDuLieu;
import Util.StyleHeaderTable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author WIN1064
 */
public class QuanLyPhieuMua extends javax.swing.JPanel {
    PhieuMuaController pmc = new PhieuMuaController();
    int selectedRow = -1;
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    String keywords = "";
    String tongPhieu,tongPhieuDT,tongPhieuCT;
    /**
     * Creates new form QuanLyPhieuMuon
     */
    public QuanLyPhieuMua() {
        initComponents();
        LuuDuLieu.dsPhieuMuaCT = pmc.getPMCT();
        loadTable();
        for(PhieuMuaChiTiet pm : LuuDuLieu.dsPhieuMuaCT)
        {
            System.out.println(pm.toString());
        }
        jDateChooserTu.setDateFormatString("dd-MM-yyyy");
        jDateChooserDen.setDateFormatString("dd-MM-yyyy");
        jTableMuaSach.getTableHeader().setDefaultRenderer(new StyleHeaderTable());
        CustomTablePhieuMuon ct = new CustomTablePhieuMuon();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        jTableMuaSach.getColumnModel().getColumn(2).setPreferredWidth(200);
        for (int i = 0; i < ct.getColumnCount(); i++) {
            jTableMuaSach.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        jTableMuaSach.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public void loadFilter(){
        jDateChooserDen.setDate(null);
        jDateChooserTu.setDate(null);
        jTextKeyWord.setText("");
        selectedRow = -1;
    }
    public void loadTable() {
        jTableMuaSach.setModel(new CustomTablePhieuMua());
        CustomTablePhieuMua ct = new CustomTablePhieuMua();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        jTableMuaSach.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (int i = 0; i < ct.getColumnCount(); i++) {
            jTableMuaSach.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        jTableMuaSach.getColumnModel().getColumn(2).setPreferredWidth(200);
        thongKe();
    }
    public void thongKe(){
        tongPhieu = Integer.toString(LuuDuLieu.dsPhieuMuaCT.size());
        tongPhieuDT = Integer.toString((int) LuuDuLieu.dsPhieuMuaCT.stream().filter(i -> i.getDaTra() == 1).count());
        tongPhieuCT = Integer.toString((int) LuuDuLieu.dsPhieuMuaCT.stream().filter(i -> i.getDaTra() == 0).count());
        jLabelTongPMCT.setText("T???ng phi???u ch??a tr??? : " + tongPhieuCT);
        jLabelTongPMDT.setText("T???ng phi???u ???? tr??? : " +tongPhieuDT);
        jLabelTongPhieu.setText("T???ng phi???u : " +tongPhieu);
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabelTongPMDT = new javax.swing.JLabel();
        jLabelTongPhieu = new javax.swing.JLabel();
        jLabelTongPMCT = new javax.swing.JLabel();
        jDateChooserDen = new com.toedter.calendar.JDateChooser();
        jButtonTaiLai = new javax.swing.JButton();
        jDateChooserTu = new com.toedter.calendar.JDateChooser();
        jTextKeyWord = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMuaSach = new javax.swing.JTable();
        jButtonLoc = new javax.swing.JButton();
        jButtonTimKiem = new javax.swing.JButton();
        jButtonXacNhan = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Th???ng k??", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        jLabelTongPMDT.setText("T???ng phi???u ???? tr??? :");

        jLabelTongPhieu.setText("T???ng phi???u :");

        jLabelTongPMCT.setText("T???ng phi???u ch??a tr??? :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelTongPMDT, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabelTongPMCT, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelTongPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(243, 243, 243))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTongPMDT)
                    .addComponent(jLabelTongPMCT))
                .addGap(18, 18, 18)
                .addComponent(jLabelTongPhieu)
                .addGap(19, 19, 19))
        );

        jButtonTaiLai.setBackground(new java.awt.Color(0, 204, 204));
        jButtonTaiLai.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTaiLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reset-20.png"))); // NOI18N
        jButtonTaiLai.setText("T???i l???i b???ng");
        jButtonTaiLai.setBorder(null);
        jButtonTaiLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTaiLaiActionPerformed(evt);
            }
        });

        jLabel1.setText("T??? ng??y");

        jLabel2.setText("?????n ng??y");

        jTableMuaSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableMuaSach.setFocusable(false);
        jTableMuaSach.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTableMuaSach.setRowHeight(25);
        jTableMuaSach.setSelectionBackground(new java.awt.Color(255, 0, 102));
        jTableMuaSach.setShowVerticalLines(false);
        jTableMuaSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMuaSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMuaSach);

        jButtonLoc.setBackground(new java.awt.Color(0, 204, 204));
        jButtonLoc.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/filter-20.png"))); // NOI18N
        jButtonLoc.setText("L???c");
        jButtonLoc.setBorder(null);
        jButtonLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLocActionPerformed(evt);
            }
        });

        jButtonTimKiem.setBackground(new java.awt.Color(0, 204, 204));
        jButtonTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        jButtonTimKiem.setText("T??m ki???m");
        jButtonTimKiem.setBorder(null);
        jButtonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimKiemActionPerformed(evt);
            }
        });

        jButtonXacNhan.setBackground(new java.awt.Color(0, 204, 204));
        jButtonXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        jButtonXacNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/return-user-book.png"))); // NOI18N
        jButtonXacNhan.setText("X??c nh???n ???? tr??? ti???n");
        jButtonXacNhan.setBorder(null);
        jButtonXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooserTu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(17, 17, 17)
                        .addComponent(jDateChooserDen, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextKeyWord, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonTaiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextKeyWord, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jDateChooserTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooserDen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonTaiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMuaSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMuaSachMouseClicked
        selectedRow = jTableMuaSach.getSelectedRow();
        LuuDuLieu.currentPhieuMuaChiTiet = LuuDuLieu.dsPhieuMuaCT.get(selectedRow);
    }//GEN-LAST:event_jTableMuaSachMouseClicked

    private void jButtonXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXacNhanActionPerformed
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Ch???n phi???u mu???n x??c nh???n");
        } else {
            pmc.updateDaTra(LuuDuLieu.currentPhieuMuaChiTiet.getMaMua());
            loadTable();
            selectedRow = -1;
        }
    }//GEN-LAST:event_jButtonXacNhanActionPerformed

    private void jButtonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimKiemActionPerformed
        keywords = jTextKeyWord.getText() + "";
        if (jDateChooserTu.getDate() != null && jDateChooserDen.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String tuNgay = sdf.format(jDateChooserTu.getDate());
            String denNgay = sdf.format(jDateChooserDen.getDate());
            LuuDuLieu.dsPhieuMuaCT = pmc.locPhieu(tuNgay, denNgay, keywords);
        } else {
            LuuDuLieu.dsPhieuMuaCT = pmc.locPhieu(null, null, keywords);
        }
        loadTable();
    }//GEN-LAST:event_jButtonTimKiemActionPerformed

    private void jButtonLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLocActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tuNgay = sdf.format(jDateChooserTu.getDate());
        String denNgay = sdf.format(jDateChooserDen.getDate());
        LuuDuLieu.dsPhieuMuaCT = pmc.locPhieu(tuNgay, denNgay, keywords);

        loadTable();
    }//GEN-LAST:event_jBu/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.phieumua;

import QLTVController.PhieuMuaController;
import QLTVModel.PhieuMuaChiTiet;
import Util.CustomTablePhieuMua;
import view.phieumuon.*;
import Util.CustomTablePhieuMuon;
import Util.LuuDuLieu;
import Util.StyleHeaderTable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author WIN1064
 */
public class QuanLyPhieuMua extends javax.swing.JPanel {
    PhieuMuaController pmc = new PhieuMuaController();
    int selectedRow = -1;
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    String keywords = "";
    String tongPhieu,tongPhieuDT,tongPhieuCT;
    /**
     * Creates new form QuanLyPhieuMuon
     */
    public QuanLyPhieuMua() {
        initComponents();
        LuuDuLieu.dsPhieuMuaCT = pmc.getPMCT();
        loadTable();
        for(PhieuMuaChiTiet pm : LuuDuLieu.dsPhieuMuaCT)
        {
            System.out.println(pm.toString());
        }
        jDateChooserTu.setDateFormatString("dd-MM-yyyy");
        jDateChooserDen.setDateFormatString("dd-MM-yyyy");
        jTableMuaSach.getTableHeader().setDefaultRenderer(new StyleHeaderTable());
        CustomTablePhieuMuon ct = new CustomTablePhieuMuon();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        jTableMuaSach.getColumnModel().getColumn(2).setPreferredWidth(200);
        for (int i = 0; i < ct.getColumnCount(); i++) {
            jTableMuaSach.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        jTableMuaSach.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public void loadFilter(){
        jDateChooserDen.setDate(null);
        jDateChooserTu.setDate(null);
        jTextKeyWord.setText("");
        selectedRow = -1;
    }
    public void loadTable() {
        jTableMuaSach.setModel(new CustomTablePhieuMua());
        CustomTablePhieuMua ct = new CustomTablePhieuMua();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        jTableMuaSach.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (int i = 0; i < ct.getColumnCount(); i++) {
            jTableMuaSach.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        jTableMuaSach.getColumnModel().getColumn(2).setPreferredWidth(200);
        thongKe();
    }
    public void thongKe(){
        tongPhieu = Integer.toString(LuuDuLieu.dsPhieuMuaCT.size());
        tongPhieuDT = Integer.toString((int) LuuDuLieu.dsPhieuMuaCT.stream().filter(i -> i.getDaTra() == 1).count());
        tongPhieuCT = Integer.toString((int) LuuDuLieu.dsPhieuMuaCT.stream().filter(i -> i.getDaTra() == 0).count());
        jLabelTongPMCT.setText("T???ng phi???u ch??a tr??? : " + tongPhieuCT);
        jLabelTongPMDT.setText("T???ng phi???u ???? tr??? : " +tongPhieuDT);
        jLabelTongPhieu.setText("T???ng phi???u : " +tongPhieu);
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabelTongPMDT = new javax.swing.JLabel();
        jLabelTongPhieu = new javax.swing.JLabel();
        jLabelTongPMCT = new javax.swing.JLabel();
        jDateChooserDen = new com.toedter.calendar.JDateChooser();
        jButtonTaiLai = new javax.swing.JButton();
        jDateChooserTu = new com.toedter.calendar.JDateChooser();
        jTextKeyWord = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMuaSach = new javax.swing.JTable();
        jButtonLoc = new javax.swing.JButton();
        jButtonTimKiem = new javax.swing.JButton();
        jButtonXacNhan = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Th???ng k??", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        jLabelTongPMDT.setText("T???ng phi???u ???? tr??? :");

        jLabelTongPhieu.setText("T???ng phi???u :");

        jLabelTongPMCT.setText("T???ng phi???u ch??a tr??? :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelTongPMDT, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabelTongPMCT, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelTongPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(243, 243, 243))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTongPMDT)
                    .addComponent(jLabelTongPMCT))
                .addGap(18, 18, 18)
                .addComponent(jLabelTongPhieu)
                .addGap(19, 19, 19))
        );

        jButtonTaiLai.setBackground(new java.awt.Color(0, 204, 204));
        jButtonTaiLai.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTaiLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reset-20.png"))); // NOI18N
        jButtonTaiLai.setText("T???i l???i b???ng");
        jButtonTaiLai.setBorder(null);
        jButtonTaiLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTaiLaiActionPerformed(evt);
            }
        });

        jLabel1.setText("T??? ng??y");

        jLabel2.setText("?????n ng??y");

        jTableMuaSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableMuaSach.setFocusable(false);
        jTableMuaSach.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTableMuaSach.setRowHeight(25);
        jTableMuaSach.setSelectionBackground(new java.awt.Color(255, 0, 102));
        jTableMuaSach.setShowVerticalLines(false);
        jTableMuaSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMuaSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMuaSach);

        jButtonLoc.setBackground(new java.awt.Color(0, 204, 204));
        jButtonLoc.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/filter-20.png"))); // NOI18N
        jButtonLoc.setText("L???c");
        jButtonLoc.setBorder(null);
        jButtonLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLocActionPerformed(evt);
            }
        });

        jButtonTimKiem.setBackground(new java.awt.Color(0, 204, 204));
        jButtonTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        jButtonTimKiem.setText("T??m ki???m");
        jButtonTimKiem.setBorder(null);
        jButtonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimKiemActionPerformed(evt);
            }
        });

        jButtonXacNhan.setBackground(new java.awt.Color(0, 204, 204));
        jButtonXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        jButtonXacNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/return-user-book.png"))); // NOI18N
        jButtonXacNhan.setText("X??c nh???n ???? tr??? ti???n");
        jButtonXacNhan.setBorder(null);
        jButtonXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooserTu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(17, 17, 17)
                        .addComponent(jDateChooserDen, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextKeyWord, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonTaiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextKeyWord, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jDateChooserTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooserDen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonTaiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMuaSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMuaSachMouseClicked
        selectedRow = jTableMuaSach.getSelectedRow();
        LuuDuLieu.currentPhieuMuaChiTiet = LuuDuLieu.dsPhieuMuaCT.get(selectedRow);
    }//GEN-LAST:event_jTableMuaSachMouseClicked

    private void jButtonXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXacNhanActionPerformed
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Ch???n phi???u mu???n x??c nh???n");
        } else {
            pmc.updateDaTra(LuuDuLieu.currentPhieuMuaChiTiet.getMaMua());
            loadTable();
            selectedRow = -1;
        }
    }//GEN-LAST:event_jButtonXacNhanActionPerformed

    private void jButtonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimKiemActionPerformed
        keywords = jTextKeyWord.getText() + "";
        if (jDateChooserTu.getDate() != null && jDateChooserDen.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String tuNgay = sdf.format(jDateChooserTu.getDate());
            String denNgay = sdf.format(jDateChooserDen.getDate());
            LuuDuLieu.dsPhieuMuaCT = pmc.locPhieu(tuNgay, denNgay, keywords);
        } else {
            LuuDuLieu.dsPhieuMuaCT = pmc.locPhieu(null, null, keywords);
        }
        loadTable();
    }//GEN-LAST:event_jButtonTimKiemActionPerformed

    private void jButtonLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLocActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tuNgay = sdf.format(jDateChooserTu.getDate());
        String denNgay = sdf.format(jDateChooserDen.getDate());
        LuuDuLieu.dsPhieuMuaCT = pmc.locPhieu(tuNgay, denNgay, keywords);

        loadTable();
    }//GEN-LAST:event_jButtonLocActionPerformed

    private void jButtonTaiLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaiLaiActionPerformed
        LuuDuLieu.dsPhieuMuaCT = pmc.getPMCT();
        loadTable();
        loadFilter();
    }//GEN-LAST:event_jButtonTaiLaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLoc;
    private javax.swing.JButton jButtonTaiLai;
    private javax.swing.JButton jButtonTimKiem;
    private javax.swing.JButton jButtonXacNhan;
    private com.toedter.calendar.JDateChooser jDateChooserDen;
    private com.toedter.calendar.JDateChooser jDateChooserTu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelTongPMCT;
    private javax.swing.JLabel jLabelTongPMDT;
    private javax.swing.JLabel jLabelTongPhieu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMuaSach;
    private javax.swing.JTextField jTextKeyWord;
    // End of variables declaration//GEN-END:variables
}

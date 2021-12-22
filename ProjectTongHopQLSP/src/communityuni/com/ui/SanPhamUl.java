package communityuni.com.ui;

import com.sun.org.apache.xpath.internal.operations.Mod;
import communityuni.com.io.JsonFileFactory;
import communityuni.com.model.SanPham;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.ObjectView;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SanPhamUl {
    private JPanel pnMain;
    private JTable tblSanPham;
    private JTextField txtMa;
    private JTextField txtTen;
    private JTextField txtGia;
    private JButton btnThem;
    private JButton btnTiep;
    private JButton btnXoa;
    private JButton btnThoat;
    private DefaultTableModel modelSanPham;

    private JMenuBar mnuBar;
    private JMenu mnuHeThong;
    private  JMenuItem mnuHethongLuuFile;
    private  JMenuItem mnuHeThongDocFile;
    private  JMenuItem mnuHeThongThoat;

    private List<SanPham> dsSp = new ArrayList<>();


    public JPanel getPnMain() {
        return pnMain;
    }

    private void createUIComponents() {
        modelSanPham = new DefaultTableModel();
        modelSanPham.addColumn("Mã Sản Phẩm");
        modelSanPham.addColumn("Tên Sản Phẩm ");
        modelSanPham.addColumn("Đơn giá");
        tblSanPham = new JTable(modelSanPham);

    }
    private void ganHinhChoButton() {
        btnThem.setIcon(new ImageIcon("Hinh/add.png"));
        btnTiep.setIcon(new ImageIcon("Hinh/clear.png"));
        btnXoa.setIcon(new ImageIcon("Hinh/delete.png"));
        btnThoat.setIcon(new ImageIcon("Hinh/exit.png"));
    }
    public  SanPhamUl() {
        ganHinhChoButton();

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xyLyThem();
            }
        });
        btnTiep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMa.setText("");
                txtTen.setText("");
                txtGia.setText("");
                txtMa.requestFocus();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xyLyXoa();
                
            }

            private void xyLyXoa() {
                int ret1 = JOptionPane.showConfirmDialog(
                        null
                        ,"Xóa Sản Phẩm ?"
                        ,"Hỏi Xóa"
                        , JOptionPane.YES_NO_OPTION);
                if (ret1 == JOptionPane.YES_NO_OPTION)
                if (tblSanPham.getSelectedRow() >= 0 ) {
                    dsSp.remove(tblSanPham.getSelectedRow());
                    hienthiDanhSachSanPham();
                }
            }
        });
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xyLyThoat();
            }

            private void xyLyThoat() {
               int ret = JOptionPane.showConfirmDialog(
                       null
                       ,"Thoát Phần Mềm"
                       ,"Hỏi Thoát"
                       , JOptionPane.YES_NO_OPTION);
               if (ret == JOptionPane.YES_NO_OPTION)
                   System.exit(0);

            }

        });
//    tblSanPham.addMouseListener(new MouseAdapter() { } );
//        tblSanPham.addComponentListener(new ComponentAdapter() {
//        });
//        tblSanPham.addComponentListener(new ComponentAdapter() {
//        });
        tblSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(tblSanPham.getSelectedRow() >=0 ) {
                    SanPham sp =dsSp.get(tblSanPham.getSelectedRow());
                    txtMa.setText(sp.getMa());
                    txtTen.setText(sp.getTen());
                    txtGia.setText(sp.getGia()+"");

                }

            }
        });
    }
    private void xyLyThem() {
        SanPham sp = new SanPham();
        sp.setMa(txtMa.getText());
        sp.setTen(txtTen.getText());
        sp.setGia(Double.parseDouble(txtGia.getText()));
        dsSp.add(sp);
        hienthiDanhSachSanPham();

    }
    private void hienthiDanhSachSanPham() {
        modelSanPham.setRowCount(0);
        for (SanPham sp : dsSp) {
            Vector<Object> vec = new Vector<>();
            vec.add(sp.getMa());
            vec.add(sp.getTen());
            vec.add(sp.getGia());
            modelSanPham.addRow(vec);

        }
    }

    public void createMenu(JFrame parent) {
        mnuBar = new JMenuBar();
        mnuHeThong = new JMenu("Hệ thống");
        mnuHeThong.setIcon(new ImageIcon("Hinh/system.png"));
        mnuBar.add(mnuHeThong);

        mnuHethongLuuFile = new JMenuItem("Lưu File");
        mnuHethongLuuFile.setIcon(new ImageIcon("Hinh/save.png"));
        mnuHeThong.add(mnuHethongLuuFile);

        mnuHeThong.addSeparator();

        mnuHeThongDocFile = new JMenuItem("Đọc File");
        mnuHeThongDocFile.setIcon(new ImageIcon("Hinh/open.png"));
        mnuHeThong.add(mnuHeThongDocFile);

        mnuHeThong.addSeparator();

        mnuHeThongThoat = new JMenuItem("Thoát File");
        mnuHeThongThoat.setIcon(new ImageIcon("Hinh/exit.png"));
        mnuHeThong.add(mnuHeThongThoat);

        parent.setJMenuBar(mnuBar);

        mnuHeThongThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnThoat.doClick();
            }
        });

//        mnuHeThongDocFile.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                xyLyDocFile();
//            }
//
//            private void xyLyDocFile() {
//                JFileChooser jfc = new JFileChooser();
//                if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
//                    String path = jfc.getSelectedFile().getAbsolutePath();
//                    JsonFileFactory jff = new JsonFileFactory();
//                    dsSp =jff.DocDuLieu(path);
//                    hienthiDanhSachSanPham();
//                }
//            });
        mnuHethongLuuFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xyLyLuuFile();

            }


            private void xyLyLuuFile() {
                JFileChooser jfc = new JFileChooser();
                if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    JsonFileFactory jff = new JsonFileFactory();
                    boolean kq = jff.LuuDuLieu(dsSp,path);
                    if (kq == true ) {
                        JOptionPane.showConfirmDialog(
                                null,"Lưu File thành công"
                        );
                    }
                    else {
                        JOptionPane.showConfirmDialog(
                                null,"Lwu File thất bại"
                        );
                    }
                }
            }
        });

    }

}

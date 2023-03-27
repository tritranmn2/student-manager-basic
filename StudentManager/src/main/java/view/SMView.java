package view;

import controller.SMController;
import controller.SortListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import model.ImageChooser;
import model.SMModel;
import model.Student;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author tritranmn2
 */



public class SMView extends JPanel {

    public SMModel model;
    public static DefaultTableModel modeltable;
    public static JTable Table;
    public static JPanel contentPane;
    public static JPanel jPMode, jPProfie;
    public static JButton btnAdd, btnDelete, btnExport, btnImport, btnSave, btnUpdate;
    public static ImageViewer imageViewer;
    public static ImageChooser imageChooser;
    public static JScrollPane jSPTable;
    public static JLabel lbAddr, lbId, lbImg, lbModeId, lbModePoint, lbName, lbNote, lbPoint;
    public static JComboBox<String> modeId;
    public static JComboBox<String> modePoint;
    public static JTextField txtAddr, txtId, txtName, txtNote, txtPoint;

    public SMView(LayoutManager layout) {
        super(layout);
        this.model = new SMModel();

    }

    public SMView() {
        this.model = new SMModel();

    }

    public static void changeFont(Component component, Font font) {
        component.setFont(font);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                changeFont(child, font);
            }
        }
    }

    public static void createAndShowUI() {
        JFrame frame = new JFrame("StudentManager(SM)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SMView view = new SMView();
        SMController action = new SMController(view);
        SortListener actionComboBox = new SortListener(view);
        contentPane = new SMView(new AbsoluteLayout());
//        
        jPMode = new JPanel(new AbsoluteLayout());
        jPMode.setBorder(BorderFactory.createTitledBorder(null, "Chế độ xem", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 0, 14)));

        lbModeId = new JLabel("Mã HS");
        modeId = new JComboBox<>();
        modeId.setModel(new DefaultComboBoxModel<>(new String[]{"Tăng dần", "Giảm dần"}));
        modeId.addActionListener(actionComboBox);

        lbModePoint = new JLabel("Điểm");
        modePoint = new JComboBox<>();
        modePoint.setModel(new DefaultComboBoxModel<>(new String[]{"Tăng dần", "Giảm dần"}));
        modePoint.addActionListener(actionComboBox);

        btnImport = new JButton("Import");
        btnImport.addActionListener(action);

        btnExport = new JButton("Export");
        btnExport.addActionListener(action);

        jPMode.add(lbModeId, new AbsoluteConstraints(70, 20, 60, 30));
        jPMode.add(modeId, new AbsoluteConstraints(140, 20, -1, 30));
        jPMode.add(lbModePoint, new AbsoluteConstraints(260, 20, 60, 30));
        jPMode.add(modePoint, new AbsoluteConstraints(320, 20, -1, 30));
        jPMode.add(btnImport, new AbsoluteConstraints(680, 20, -1, 30));
        jPMode.add(btnExport, new AbsoluteConstraints(790, 20, -1, 30));

        jSPTable = new JScrollPane();
        jSPTable.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, null));
        Table = new JTable();
        modeltable = new DefaultTableModel(new Object[][]{}, new String[]{
            "Mã HS", "Tên ", "Địa chỉ", "Điểm", "Hình ảnh", "Ghi chú"
        }){
            Class[] types = new Class[]{
                String.class, String.class, String.class, String.class, String.class, String.class
            };
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        Table.setModel(modeltable);

        jPProfie = new JPanel(new AbsoluteLayout());
        jSPTable.setViewportView(Table);
        jPProfie.setBorder(BorderFactory.createTitledBorder(null, "Thông tin chi tiết", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 0, 14)));

        lbId = new JLabel("Mã HS");
        txtId = new JTextField();

        lbName = new JLabel("Họ và tên");
        txtName = new JTextField();

        lbAddr = new JLabel("Địa chỉ");
        txtAddr = new JTextField();

        lbPoint = new JLabel("Điểm");
        txtPoint = new JTextField();

        lbNote = new JLabel("Ghi chú");
        txtNote = new JTextField();

        lbImg = new JLabel("Hình ảnh");
        imageChooser = new ImageChooser();
        imageViewer = new ImageViewer();
        imageViewer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        imageViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Image image = imageChooser.choose();
                    if (image != null) {
                        imageViewer.setImage(image);
                    }
                } catch (IOException ex) {
                    System.err.println("Cannot read image file: " + ex.getMessage());
                }
            }
        });
        
        btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(action);
        btnDelete = new JButton("Xoá");
        btnDelete.addActionListener(action);
        btnUpdate = new JButton("Sửa");
        btnUpdate.addActionListener(action);
        btnSave = new JButton("Lưu");
        btnSave.addActionListener(action);

        jPProfie.add(lbId, new AbsoluteConstraints(40, 40, 50, -1));
        jPProfie.add(txtId, new AbsoluteConstraints(110, 40, 130, -1));
        jPProfie.add(lbName, new AbsoluteConstraints(40, 80, 70, -1));
        jPProfie.add(txtName, new AbsoluteConstraints(110, 80, 130, -1));
        jPProfie.add(lbAddr, new AbsoluteConstraints(40, 120, 50, -1));
        jPProfie.add(txtAddr, new AbsoluteConstraints(110, 120, 130, -1));
        jPProfie.add(lbPoint, new AbsoluteConstraints(40, 160, 50, -1));
        jPProfie.add(txtPoint, new AbsoluteConstraints(110, 160, 130, -1));
        jPProfie.add(lbNote, new AbsoluteConstraints(40, 200, 50, -1));
        jPProfie.add(txtNote, new AbsoluteConstraints(110, 200, 130, -1));
        jPProfie.add(lbImg, new AbsoluteConstraints(480, 120, 70, -1));
        jPProfie.add(imageViewer, new AbsoluteConstraints(600, 30, 200, 200));
        jPProfie.add(btnAdd, new AbsoluteConstraints(250, 230, 70, -1));
        jPProfie.add(btnDelete, new AbsoluteConstraints(340, 230, 70, -1));
        jPProfie.add(btnUpdate, new AbsoluteConstraints(430, 230, 70, -1));
        jPProfie.add(btnSave, new AbsoluteConstraints(520, 230, 70, -1));

        contentPane.add(jSPTable, new AbsoluteConstraints(20, 80, 900, 200));
        contentPane.add(jPProfie, new AbsoluteConstraints(20, 285, 900, 270));
        contentPane.add(jPMode, new AbsoluteConstraints(20, 10, 900, 60));
        changeFont(contentPane, new Font("Segoe UI", 0, 14));

        frame.add(contentPane);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        createAndShowUI();
    }

    public void deleteForm() {
        txtId.setText("");
        txtName.setText("");
        txtAddr.setText("");
        txtPoint.setText("");
        
        txtNote.setText("");
    }

    public Student getStudentForm() {
        String id = txtId.getText();
        String name = txtName.getText();
        String img = imageChooser.imagePath;
//        String img ="";
//        System.out.println(img);
        String addr = txtAddr.getText();
        float point = 0;
        try {
            point = Float.parseFloat((String) txtPoint.getText());
        } catch (Exception e) {
            System.out.println("e");
            point = 0;
        }
        String note = txtNote.getText();
        return new Student(id, name, img, addr, point, note);
    }

    public void showStudentForm(Student st) {
        if (st == null) {
            return;
        }
        txtId.setText(st.getId());
        txtName.setText(st.getName());
        txtAddr.setText(st.getAddr());
        txtPoint.setText(Float.toString(st.getPoint()));
        txtNote.setText(st.getNote());
        try {
            Image image = ImageIO.read(new File(st.getImg()));
            if (image != null) {
                imageViewer.setImage(image);
            }
        } catch (IOException ex) {
            System.err.println("Cannot read image file: " + ex.getMessage());
        }
    }

    public void showStudentForm() {
        showStudentForm(getRowTableSelected());
    }

    public void addStudent(Student st) {
        this.model.insert(st);
//        this.model.addStudentToFile(st);
        this.model.saveFile();
        this.updateTable();

//        this.addRowTable(st);
    }

    public void deleteStudent(Student st) {
        this.model.delete(st);
        this.model.saveFile();
        this.updateTable();
    }

    public void updateStudent(Student st) {
        this.model.update(st);
        this.model.saveFile();
        this.updateTable();
        this.deleteForm();
    }

    public Student getRowTableSelected() {
        int index_row = Table.getSelectedRow();
        if (index_row != -1) {
            String id = (String) modeltable.getValueAt(index_row, 0);
            String name = (String) modeltable.getValueAt(index_row, 1);
            String addr = (String) modeltable.getValueAt(index_row, 2);
            Float point = (Float) modeltable.getValueAt(index_row, 3);
            String img = (String) modeltable.getValueAt(index_row, 4);
            String note = (String) modeltable.getValueAt(index_row, 5);
            return new Student(id, name, img, addr, point, note);
        }
        return null;
    }
    
    public void deleteRowTableSelected() {
       int index_row = Table.getSelectedRow();
       modeltable.removeRow(index_row);
    }
    
    public void setRowTable(int index_row, Student st) {
        modeltable.setValueAt(st.getId(), index_row, 0);
        modeltable.setValueAt(st.getName(), index_row, 1);
        modeltable.setValueAt(st.getAddr(), index_row, 2);
        modeltable.setValueAt(st.getPoint() , index_row, 3);
        modeltable.setValueAt(st.getImg(), index_row, 4);
        modeltable.setValueAt(st.getNote(), index_row, 5);
    }

    public void addRowTable(Student st) {
        modeltable.addRow(new Object[]{st.getId(), st.getName(), st.getAddr(), st.getPoint(), st.getImg(), st.getNote()});
    }
    
    public void updateRowTable(Student st) {
        int countRow = modeltable.getRowCount();
        for (int i = 0; i < countRow; i++) {
            System.out.println(st.getId().length());
            if (st.getId().equals((String) modeltable.getValueAt(i, 0))) {
                setRowTable(i, st);
                break;
            }
        }
    }
    
    public void updateTable() {
        modeltable.setRowCount(0); // clear all rows
        ArrayList<Student> students = this.model.getStudents();
        for (Student s : students) {
            modeltable.addRow(new Object[]{s.getId(), s.getName(),  s.getAddr(), s.getPoint(),s.getImg(), s.getNote()});
        }
    }


}



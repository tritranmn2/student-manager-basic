package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JOptionPane;
import model.Student;
import view.SMView;

/**
 *
 * @author tritranmn2
 */
public class SMController implements Action {
    public SMView view;

    public SMController(SMView view) {
        this.view = view;
    }
    
    @Override
    public Object getValue(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void putValue(String key, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setEnabled(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEnabled() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
//        JOptionPane.showMessageDialog(view, "Bạn vừa nhấn:"+cm);
        if(cm.equals("Thêm")){
            this.view.deleteForm();
        }else if(cm.equals("Xoá")){
            Student st = this.view.getRowTableSelected();
            this.view.deleteStudent(st);
        }else if(cm.equals("Sửa")){
            this.view.showStudentForm();
        }else if(cm.equals("Lưu")){
            Student st = this.view.getStudentForm();
            if(this.view.model.getSelect().equals("")||this.view.model.getSelect().equals("Thêm")){
                this.view.addStudent(st);
            }else if(this.view.model.getSelect().equals("Sửa")){
                this.view.updateStudent(st);
            }
          
        }else if(cm.equals("Import")){
            try {
                this.view.model.importFromTextFile();
            } catch (IOException ex) {
//                Logger.getLogger(SMController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(view, "Không tìm thấy file view.txt");
            }
            this.view.updateTable();
        }else if(cm.equals("Export")){
            JOptionPane.showMessageDialog(view, "Export ra file view.txt");
            try {
                this.view.model.exportToTextFile();
            } catch (IOException ex) {
//                Logger.getLogger(SMController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.view.model.setSelect(cm);
    }


    
}

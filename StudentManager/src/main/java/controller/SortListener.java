package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import view.SMView;

public class SortListener implements ActionListener {
    public SMView view;

    public SortListener(SMView view) {
        this.view = view;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
        String selectedSort = (String) comboBox.getSelectedItem();

        if (comboBox == SMView.modePoint) {
            // xử lý sự kiện cho JComboBox modePoint
            switch (selectedSort) {
                case "Tăng dần":
                    this.view.model.sortPointAsc();
//                    this.view.sortPointAsc();
                    break;
                case "Giảm dần":
                    this.view.model.sortPointDes();
//                    this.view.sortPointDes();
                    break;
            }
        } else if (comboBox == SMView.modeId) {
            // xử lý sự kiện cho JComboBox modeId
            switch (selectedSort) {
                case "Tăng dần":
                    this.view.model.sortIdAsc();
//                    this.view.sortIdAsc();
                    break;
                case "Giảm dần":
                    this.view.model.sortIdDes();
//                    this.view.sortIdDes();
                    break;
            }
        }
        this.view.updateTable();
    }
}

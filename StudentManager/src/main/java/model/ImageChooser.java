/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageChooser {
    private JFileChooser chooser;
    public String imagePath;
    public ImageChooser() {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        imagePath = "";
    }

    public Image choose() throws IOException {
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            imagePath = selectedFile.getPath();
            return ImageIO.read(selectedFile);
        }
        return null;
    }
}

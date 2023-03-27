/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tritranmn2
 */
public class SMModel {
    public ArrayList<Student> students;
    public String select;
    public SMModel() {
        this.students = new ArrayList<Student>();
        this.select = "";
    }

    public SMModel(ArrayList<Student> students) {
        this.students = students;
        this.select = "";
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "StudentManager{" + "students=" + students + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.students);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SMModel other = (SMModel) obj;
        return Objects.equals(this.students, other.students);
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getSelect() {
        return select;
    }
    
    public void insert(Student student){
        this.students.add(student);
    }
    
    public void delete(Student student){
        this.students.remove(student);
    }
    
    public void update(Student student){
        for (Student st : students) {
            if(st.getId().equals(student.getId())){
                st.copy(student);break;
            }
        }
    }

    public void sortIdAsc() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getId().compareTo(s2.getId());
            }
        });
    }
    
    public void sortIdDes() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s2.getId().compareTo(s1.getId()); // Thay đổi ở đây
            }
        });
    }
    
    public void sortPointAsc() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Float.compare(s1.getPoint(), s2.getPoint());
            }
        });
    }
    
        
    public void sortPointDes() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Float.compare(s2.getPoint(), s1.getPoint());
            }
        });

    }

    public void saveFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.csv"));
            for (Student student : this.students) {
                oos.writeObject(student);
            }
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void importStudentsFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.csv"));
            Student student;
            while (((student = (Student) ois.readObject()) != null) ) {
                students.add(student);
            }
            
            ois.close();
        } catch (Exception e) {
            System.out.println("khong tim thay file"); 
        }
    }
    
    public void importFromTextFile() throws IOException {
        String filePath="view.csv";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            System.out.println(fields[0]);
            String id = fields[0];
            String name = fields[1];
            String img = fields[2];
            String addr = fields[3];
            float point = Float.parseFloat(fields[4]);
            String note="" ;
            if(fields.length != 5){
                note = fields[5];
            }
            Student student = new Student(id, name, img, addr, point, note);
            students.add(student);
        }
        reader.close();
    }
    
    public void exportToTextFile() throws IOException {
        String filePath="view.csv";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Student student : students) {
            String line = student.getId() + "," + student.getName() + "," + student.getImg()
                    + "," + student.getAddr() + "," + student.getPoint() + "," + student.getNote();
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

    
}

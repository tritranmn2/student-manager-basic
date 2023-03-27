/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author tritranmn2
 */
public class Student implements Serializable{
    private String id;
    private String name;
    private String img;
    private String addr;
    private float point;
    private String note;

    public Student(String id, String name, String img, String addr, float point, String note) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.addr = addr;
        this.point = point;
        this.note = note;
    }

    public Student(String id, String name, float point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }
    
    public void copy(Student st) {
        this.id = st.id;
        this.name = st.name;
        if(st.img.length() != 0){
            this.img = st.img;
        }
        this.addr = st.addr;
        this.point = st.point;
        this.note = st.note;
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getAddr() {
        return addr;
    }

    public float getPoint() {
        return point;
    }

    public String getNote() {
        return note;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", img=" + img + ", addr=" + addr + ", point=" + point + ", note=" + note + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.img);
        hash = 29 * hash + Objects.hashCode(this.addr);
        hash = 29 * hash + Float.floatToIntBits(this.point);
        hash = 29 * hash + Objects.hashCode(this.note);
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
        final Student other = (Student) obj;
        if (Float.floatToIntBits(this.point) != Float.floatToIntBits(other.point)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.img, other.img)) {
            return false;
        }
        if (!Objects.equals(this.addr, other.addr)) {
            return false;
        }
        return Objects.equals(this.note, other.note);
    }
    
    
    
    
}

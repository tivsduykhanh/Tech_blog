package com.example.demo.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String hoten;

    @Column(nullable = false, length = 10)
    private String sdt;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", hoten='" + hoten + '\'' +
                ", sdt='" + sdt + '\'' +
                ", email='" + email + '\'' +
                ", created_at=" + created_at +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

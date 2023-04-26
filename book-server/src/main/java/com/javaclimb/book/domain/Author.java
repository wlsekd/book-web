package com.javaclimb.book.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 作者
 */
public class Author implements Serializable {
    private Integer id;
    private String name;
    private Byte sex;
    private String pic;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday_time;
    private String location;
    private String introduction;

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", pic='" + pic + '\'' +
                ", birthday_time=" + birthday_time +
                ", location='" + location + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getBirthday_time() {
        return birthday_time;
    }

    public void setBirthday_time(Date birthday_time) {
        this.birthday_time = birthday_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}

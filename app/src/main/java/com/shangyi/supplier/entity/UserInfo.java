package com.shangyi.supplier.entity;

import io.realm.RealmObject;

/**
 * description :
 * project name：MyTest
 * author : Vincent
 * creation time: 2016/12/3  12:27
 *
 * @version 1.0
 */

public class UserInfo extends RealmObject {
    private String name;
    private int age;
    //get set方法省略
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

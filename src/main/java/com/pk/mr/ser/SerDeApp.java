package com.pk.mr.ser;

import java.io.*;

/**
 * 序列化与反序列化基础
 */
public class SerDeApp {

    public static void main(String[] args) throws Exception {

//        Person person = new Person(1, "PK哥", 100.0, 31);
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ser/person.obj"));
//        out.writeObject(person);
//        out.close();
//
//        ObjectInputStream in = new ObjectInputStream(new FileInputStream("ser/person.obj"));
//        Person p = (Person) in.readObject();
//        System.out.println(p);


        Person person = new Person(1, "PK哥", 100.0, 31);
        DataOutputStream out = new DataOutputStream(new FileOutputStream("ser/person2.obj"));
        out.writeInt(person.getId());
        out.writeUTF(person.getName());
        out.close();

        // 这里一定要注意：上面以什么顺序写出去，这里就要以什么顺序读进来
        DataInputStream in = new DataInputStream(new FileInputStream("ser/person2.obj"));

        int id = in.readInt();
        String name = in.readUTF();
        System.out.println("id:"+id + ", name:" + name);

        double salary = in.readDouble();
        System.out.println(salary);
    }
}

class Person {
    private int id;
    private String name;
    private double salary;
    private int age;

    public Person(int id, String name, double salary, int age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

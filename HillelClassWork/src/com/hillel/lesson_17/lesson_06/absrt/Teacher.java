package com.hillel.lesson_17.lesson_06.absrt;

public class Teacher extends Human {
    private int salary;

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        super.toString();
        return "Teacher{" +
                "name='" + super.getName() + '\'' +
                ", age=" + super.getAge() +
                ", salary=" + salary +
                '}';
    }
}

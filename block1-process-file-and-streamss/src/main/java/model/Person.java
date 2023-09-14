package model;

public class Person {
    private String name;
    private String town;
    private int age;

    public Person(String name, String town, int age){
        this.name = name;
        this.town = town;
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getTown(){
        return this.town;
    }

    public void setTown(String town){
        this.town = town;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String toString() {
        String persona = "Nombre: " + name + ", ciudad: " + town + ", edad: ";
        if(age!=0){
            persona = persona + age;
        } else {
            persona = persona + "desconocida";
        }
        return persona;
    }




}
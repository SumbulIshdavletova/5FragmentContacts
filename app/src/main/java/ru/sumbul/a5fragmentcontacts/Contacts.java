package ru.sumbul.a5fragmentcontacts;

import java.util.Objects;

public final class Contacts {

    public int id;

    public String name;

    public int number;


    public Contacts(int id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override

    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Contacts Contacts = (Contacts) o;

        return id == Contacts.id &&

                Objects.equals(name, Contacts.name) &&

                number == Contacts.number;

    }


    @Override

    public int hashCode() {
        return Objects.hash(id, name, number);
    }


    @Override
    public String toString() {

        return "Contacts { id =" + id + "name =" + name + "number =" + number + "}";

    }

}
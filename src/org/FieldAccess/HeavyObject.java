package org.FieldAccess;

public class HeavyObject {
    private int id;        // Typically 4 bytes
    private String name;   // Reference to a string (address/8 bytes)

    // Constructors and methods
    public HeavyObject() {
        this.id = 0;
        this.name = "default";
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "HeavyObject{id=" + id + ", name='" + name + "'}";
    }
}


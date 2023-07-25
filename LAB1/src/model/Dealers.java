package model;

import controller.status;

public class Dealers {
    protected String id;
    protected String name;
    protected String address;
    protected String phone;
    protected status status;
    
    public Dealers(){
        
    }

    public Dealers(String id, String name, String address, String phone, status status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }


    public String toString(){
        return id + " " + name + " " + address + " " + phone + " " + status;
    }

 
    
    
}

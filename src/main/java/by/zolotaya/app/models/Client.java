package by.zolotaya.app.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Client {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    private String surname;

    @NotEmpty(message = "PassportId should not be empty")
    private String passportId;

    public Client() {
    }

    public Client(int id, String name, String surname, String passportId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.passportId = passportId;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
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
}

package sample;

import javafx.beans.property.SimpleStringProperty;

public class Users {

    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private int Userid;
    private Books rentedBook;

    public void setRentedBook(Books variable ) {
        this.rentedBook = variable;
    }
    public void setUsername (String username) {
        this.username.set(username);
    }
    public void setPassword (String password) {
        this.password.set(password);
    }

    public String getUsername() {
        return this.username.get();
    }

    public String getPassword() {
        return this.password.get();
    }

    public Books getRentedBook() {
        return rentedBook;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }
}

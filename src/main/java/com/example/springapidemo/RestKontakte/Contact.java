package com.example.springapidemo.RestKontakte;

public class Contact {
    private Long id;
    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private String zipcode;
    private String city;
    private String gender;

    public Contact(Long id, String firstName, String lastName, String mail, String phone, String zipcode, String city, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.zipcode = zipcode;
        this.city = city;
        this.gender = gender;
    }

    // Getter-Methoden für alle Felder
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getMail() { return mail; }
    public String getPhone() { return phone; }
    public String getZipcode() { return zipcode; }
    public String getCity() { return city; }
    public String getGender() { return gender; }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s', mail='%s', phone='%s', zipcode='%s', city='%s', gender='%s']",
                id, firstName, lastName, mail, phone, zipcode, city, gender);
    }

}
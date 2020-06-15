package com.example.phonecontacts;

public class phoneContact {

    String contactName;
    String phoneNumber;

    public phoneContact(String contactName, String phoneNumber) {
        this.setContactName(contactName);
        this.setPhoneNumber(phoneNumber);
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

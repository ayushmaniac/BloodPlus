package in.socialninja.bloodplus.modal;

import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * Created by Unique on 10-03-2018.
 */

public class SearchContactModal {

    String id;
    String name;
    String contact;
    String gender;
    String dob;
    String city;
    String bloodgroup;
    String lastdonatedate;
    String problem;
    String emailaddress;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public SearchContactModal(String id, String name, String city, String dob, String gender, String contact, String emailaddress, String problem, String bloodgroup, String lastdonatedate) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.emailaddress = emailaddress;
        this.dob = dob;
        this.contact = contact;
        this.bloodgroup = bloodgroup;
        this.problem = problem;
        this.lastdonatedate = lastdonatedate;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getLastdonatedate() {
        return lastdonatedate;
    }

    public void setLastdonatedate(String lastdonatedate) {
        this.lastdonatedate = lastdonatedate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
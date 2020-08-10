package in.socialninja.bloodplus.modal;

/**
 * Created by Unique on 06-03-2018.
 */

public class NavModalClass {

    String image;
    String name;
    String city;
    String mapaddress;
    String id;
    String contact;


    public NavModalClass(String image, String name, String city, String mapaddress, String h_id, String contact) {
        this.image = image;
        this.name = name;
        this.city = city;
        this.mapaddress = mapaddress;
        this.id = h_id;
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getMapaddress() {
        return mapaddress;
    }

    public void setMapaddress(String mapaddress) {
        this.mapaddress = mapaddress;
    }

    public void setId(String id) {
        this.name = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
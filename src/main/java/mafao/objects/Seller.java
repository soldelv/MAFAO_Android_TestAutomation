package mafao.objects;

public class Seller {
    private int id;
    private String name;
    private String display_name;
    private String street;
    private String city;
    private String country;

    public Seller() {
    }

    public Seller(int id, String name, String display_name,String street,String city, String country) {
        this.id = id;
        this.name = name;
        this.display_name = display_name;
        this.street = street;
        this.city = city;
        this.country = country;
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
    public String getDisplay_name() {
        return display_name;
    }
    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", country='" + country + '\'' +
                ", display_name=" + display_name +
                '}';
    }
}

package mafao.objects;

import static database.UserQuery.getSellerIDByProduct;

public class Product {

    private int id;
    private String display_name;
    private String description_sale;
    private String expiration_date;
    private boolean superBonus;
    private double list_price;
    private double oldPrice;
    private int categ_id;
    private boolean active;
    private String create_date;
    private int sale_delay;
    private boolean is_published;
    private String status;
    private int initial_quantity;

    public Product() {
    }

    public Product(int id, String display_name, String description_sale, String expiration_date, boolean superBonus, double list_price, double oldPrice,
                   int categ_id,boolean active,String create_date,int sale_delay,boolean is_published,
                   String status,int initial_quantity) {
        this.id = id;
        this.display_name = display_name;
        this.description_sale = description_sale;
        this.expiration_date = expiration_date;
        this.superBonus = superBonus;
        this.list_price = list_price;
        this.categ_id = categ_id;
        this.active = active;
        this.create_date = create_date;
        this.sale_delay = sale_delay;
        this.is_published = is_published;
        this.status = status;
        this.initial_quantity = initial_quantity;
        this.oldPrice = oldPrice;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getDescription_sale() {
        return description_sale;
    }

    public void setDescription_sale(String description_sale) {
        this.description_sale = description_sale;
    }
    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public boolean getSuperBonus() {
        return superBonus;
    }

    public void setSuperBonus(boolean superBonus) {
        this.superBonus = superBonus;
    }

    public double getList_price() {
        return list_price;
    }

    public void setList_price(double list_price) {
        this.list_price = list_price;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getSellerID() {
        return getSellerIDByProduct(this.id);
    }
    public int getCategoryID() {
        return categ_id;
    }
    public void setCategoryID(int categ_id) {
        this.categ_id = categ_id;
    }
    public boolean getIsActive() {
        return active;
    }
    public void setIsActive(boolean active) {
        this.active = active;
    }
    public boolean getIsPublished() {
        return is_published;
    }
    public void setIsPublished(boolean is_published) {
        this.is_published = is_published;
    }

    public int getInitialQuantity() {
        return initial_quantity;
    }
    public void setInitialQuantity(String initial_quantity) {
        if(initial_quantity!=null){
            this.initial_quantity = Integer.parseInt(initial_quantity);
        }
    }
    public int getSaleDelay() {
        return sale_delay;
    }
    public void setSaleDelay(String sale_delay) {
        if(sale_delay!=null){
            this.sale_delay = Integer.parseInt(sale_delay);
        }
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCreationDate() {
        return create_date;
    }
    public void setCreationDate(String create_date) {
        this.create_date = create_date;
    }

    public String getFormattedListPrice(){
        String productPrice = String.valueOf(getList_price());
        productPrice = productPrice.substring(0, productPrice.length() - 2);
        return productPrice;
    }

    public String getFormattedOldPrice(){
        String productOldPrice = String.valueOf(getOldPrice());
        productOldPrice = productOldPrice.substring(0, productOldPrice.length() - 2);
        return productOldPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", display_name='" + display_name + '\'' +
                ", description_sale='" + description_sale + '\'' +
                ", expiration_date='" + expiration_date + '\'' +
                ", superBonus='" + superBonus + '\'' +
                ", list_price=" + list_price +
                ", categ_id=" + categ_id +
                ", active=" + active +
                ", create_date=" + create_date +
                ", sale_delay=" + sale_delay +
                ", is_published=" + is_published +
                ", status=" + status +
                ", initial_quantity=" + initial_quantity +
                ", oldPrice=" + oldPrice +
                '}';
    }
}
package mafao.objects;

import static database.ProductQuery.getProductDetailById;

public class OrderLines {
    private int id;
    private int order_id;
    private Product product;
    private int quantity;
    private String total_price;
    private double price_subtotal;
    private double price_tax;
    private String create_date;
    private boolean is_delivery;
    private String marketplace_seller_id;
    private String marketplace_state;
    private String pickup_location;
    private String pickup_date;
    private String country;
    private String name;
    private String purchaseOrderId;
    private boolean isReceived;
    private double unit_price; //price_unit
    private String seller_display_name;
    private String status;

    public OrderLines() {
    }

    public OrderLines(int id,int order_id,Product product, int quantity,String total_price, String purchaseOrderId, boolean isReceived,
                      double unit_price, String seller_display_name, String status, String country,String pickup_date,
                      double price_subtotal,double price_tax,String create_date,boolean is_delivery,
                      String marketplace_seller_id,String marketplace_state,String pickup_location,String name) {
        this.id = id;
        this.order_id = order_id;
        this.product = product;
        this.quantity = quantity;
        this.total_price = total_price;
        this.purchaseOrderId = purchaseOrderId;
        this.isReceived = isReceived;
        this.unit_price = unit_price;
        this.seller_display_name = seller_display_name;
        this.status = status;
        this.price_subtotal = price_subtotal;
        this.price_tax = price_tax;
        this.create_date = create_date;
        this.is_delivery = is_delivery;
        this.marketplace_seller_id = marketplace_seller_id;
        this.marketplace_state = marketplace_state;
        this.pickup_location = pickup_location;
        this.name = name;
        this.country = country;
        this.pickup_date=pickup_date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrderId() {
        return order_id;
    }
    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setProductByID(String productId) {
        if(productId!=null){
            Product toTestProduct = getProductDetailById(Integer.parseInt(productId));
            if(toTestProduct!=null){
                this.product = toTestProduct;
            }
        }
    }
    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }
    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }
    public String getPriceTotal() {
        return total_price;
    }
    public void setPriceTotal(String price_total) {
        this.total_price = total_price;
    }
    public double getPriceSubtotal() {
        return price_subtotal;
    }
    public void setPriceSubtotal(double price_subtotal) {
        this.price_subtotal = price_subtotal;
    }
    public double getPriceUnit() {
        return unit_price;
    }
    public void setPriceUnit(double unit_price) {
        this.unit_price = unit_price;
    }
    public double getPriceTax() {
        return price_tax;
    }
    public void setPriceTax(double price_tax) {
        this.price_tax = price_tax;
    }
    public String getCreate_date() {
        return create_date;
    }
    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
    public boolean getIsDelivery() {
        return is_delivery;
    }
    public void setIsDelivery(boolean id) {
        this.is_delivery = is_delivery;
    }
    public String getMarketplace_seller_id() {
        return marketplace_seller_id;
    }
    public void setMarketplace_seller_id(String marketplace_seller_id) {
        this.marketplace_seller_id = marketplace_seller_id;
    }
    public String getMarketplaceState() {
        return marketplace_state;
    }
    public void setMarketplaceState(String marketplace_state) {
        this.marketplace_state = marketplace_state;
    }
    public String getPickupLocation() {
        return pickup_location;
    }
    public void setPickupLocation(String pickup_location) {
        this.pickup_location = pickup_location;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSeller_display_name() {
        return seller_display_name;
    }
    public void setSeller_display_name(String seller_display_name) {
        this.seller_display_name = seller_display_name;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderLines{" +
                "product=" + product +
                ", quantity='" + quantity + '\'' +
                ", total_price='" + total_price + '\'' +
                ", purchaseOrderId='" + purchaseOrderId + '\'' +
                ", isReceived='" + isReceived + '\'' +
                ", unit_price='" + unit_price + '\'' +
                ", seller_display_name='" + seller_display_name + '\'' +
                ", price_subtotal='" + price_subtotal + '\'' +
                ", price_tax='" + price_tax + '\'' +
                ", create_date='" + create_date + '\'' +
                ", is_delivery='" + is_delivery + '\'' +
                ", marketplace_seller_id='" + marketplace_seller_id + '\'' +
                ", marketplace_state='" + marketplace_state + '\'' +
                ", pickup_location='" + pickup_location + '\'' +
                ", name='" + name + '\'' +
                ", pickup_date='" + pickup_date + '\'' +
                ", country='" + country + '\'' +
                ", status=" + status +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }
}

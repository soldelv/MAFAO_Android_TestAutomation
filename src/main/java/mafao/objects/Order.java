package mafao.objects;

import java.util.List;

public class Order {
    private int orderId;
    private String uuid;
    private List<OrderLines> order_lines;;
    private double amount_total;
    private String paymentMethod;
    private double balanceBeforeOrder;
    private double bonusUsedAmount;
    private String paymentTransactionId;
    private String access_token;
    private String name;
    private String reference;
    private String state;
    private String date_order;
    private int user_id;

    public Order() {
    }

    public Order(int orderId, String uuid,List<OrderLines> order_lines, double amount, String paymentMethod, double balanceBeforeOrder,
                 double bonusUsedAmount, String paymentTransactionId,String access_token,String name,String reference,
                 String state,String date_order,int user_id) {
        this.orderId = orderId;
        this.uuid = uuid;
        this.order_lines = order_lines;
        this.amount_total = amount;
        this.paymentMethod = paymentMethod;
        this.balanceBeforeOrder = balanceBeforeOrder;
        this.bonusUsedAmount = bonusUsedAmount;
        this.paymentTransactionId = paymentTransactionId;

        this.access_token = access_token;
        this.name = name;
        this.reference = reference;
        this.state = state;
        this.date_order = date_order;
        this.user_id = user_id;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public List<OrderLines> getOrderLines() {
        return order_lines;
    }
    public void setOrderLines(List<OrderLines> order_lines) {
        this.order_lines = order_lines;
    }

    public double getAmount_total() {
        return amount_total;
    }
    public void setAmount_total(double amount_total) {
        this.amount_total = amount_total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate_order() {
        return date_order;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", uuid='" + uuid + '\'' +
                ", order_lines='" + order_lines + '\'' +
                ", amount='" + amount_total + '\'' +
                ", balanceBeforeOrder='" + balanceBeforeOrder + '\'' +
                ", bonusUsedAmount='" + bonusUsedAmount + '\'' +
                ", paymentTransactionId='" + paymentTransactionId + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", access_token='" + access_token + '\'' +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                ", state='" + state + '\'' +
                ", date_order='" + date_order + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}

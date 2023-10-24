package mafao.objects;

public class Alert {

    private String alertId;
    private String name;

    public Alert() {
    }

    public Alert(String alertId, String name) {
        this.alertId = alertId;
        this.name = name;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + alertId +
                ", display_name='" + name + '\'' +
                '}';
    }
}

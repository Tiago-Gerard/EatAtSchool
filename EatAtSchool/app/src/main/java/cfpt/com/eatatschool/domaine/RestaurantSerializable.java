package cfpt.com.eatatschool.domaine;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantSerializable {

    @SerializedName("idRestaurant")
    @Expose
    private String idRestaurant;
    @SerializedName("nomRestaurant")
    @Expose
    private String nomRestaurant;
    @SerializedName("latitudeRestaurant")
    @Expose
    private Double latitudeRestaurant;
    @SerializedName("longitudeRestaurant")
    @Expose
    private Double longitudeRestaurant;
    @SerializedName("siteWebRestaurant")
    @Expose
    private String siteWebRestaurant;
    @SerializedName("livraisonRestaurant")
    @Expose
    private String livraisonRestaurant;
    @SerializedName("conditionLivraisonRestaurant")
    @Expose
    private String conditionLivraisonRestaurant;

    public String getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(String idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getNomRestaurant() {
        return nomRestaurant;
    }

    public void setNomRestaurant(String nomRestaurant) {
        this.nomRestaurant = nomRestaurant;
    }

    public Double getLatitudeRestaurant() {
        return latitudeRestaurant;
    }

    public void setLatitudeRestaurant(Double latitudeRestaurant) {
        this.latitudeRestaurant = latitudeRestaurant;
    }

    public Double getLongitudeRestaurant() {
        return longitudeRestaurant;
    }

    public void setLongitudeRestaurant(Double longitudeRestaurant) {
        this.longitudeRestaurant = longitudeRestaurant;
    }

    public String getSiteWebRestaurant() {
        return siteWebRestaurant;
    }

    public void setSiteWebRestaurant(String siteWebRestaurant) {
        this.siteWebRestaurant = siteWebRestaurant;
    }

    public String getLivraisonRestaurant() {
        return livraisonRestaurant;
    }

    public void setLivraisonRestaurant(String livraisonRestaurant) {
        this.livraisonRestaurant = livraisonRestaurant;
    }

    public String getConditionLivraisonRestaurant() {
        return conditionLivraisonRestaurant;
    }

    public void setConditionLivraisonRestaurant(String conditionLivraisonRestaurant) {
        this.conditionLivraisonRestaurant = conditionLivraisonRestaurant;
    }

}
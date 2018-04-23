package cfpt.com.eatatschool.domaine;

/**
 * Created by ADMINELEVE on 15.03.2018.
 *
 * Classe de l'objet Restaurant
 */

public class Restaurant {
    // Variables de classe pour l'objet Restaurant
    private Integer id;
    private String nom;
    private Double longitude;
    private Double latitude;
    private String siteWeb;
    private Integer livraisonRestaurant;
    private String conditionLivraisonRestaurant;

    // Constructeur par défault
    public Restaurant()
    {
    }

    /**
     * Constructeur désigner
     * @param unId l'Id de l'objet Restaurant
     * @param unNom le nom de l'objet Restaurant
     * @param uneLongitude sa longitude de l'objet Restaurant
     * @param uneLatitude sa latitude de l'objet Restaurant
     * @param unSiteWeb l'url du site web de l'objet Restaurant
     * @param uneLivraisonRestaurant la valeur livraison de l'objet Restaurant 0 ou 1
     * @param uneConditionLivraisonRestaurant les condition pour lesquelles on peut étre livré de l'objet Restaurant
     */
    public Restaurant( Integer unId, String unNom, Double uneLongitude, Double uneLatitude, String unSiteWeb, Integer uneLivraisonRestaurant, String uneConditionLivraisonRestaurant)
    {
        SetId(unId);
        SetNom(unNom);
        SetLongitude(uneLongitude);
        SetLatitude(uneLatitude);
        SetSiteWeb(unSiteWeb);
        SetLivraisonRestaurant(uneLivraisonRestaurant);
        SetConditionLivraisonRestaurant(uneConditionLivraisonRestaurant);
    }

    /***
     * Getter Setter pour l'objet Restaurant
     */
    public void SetId(Integer aId){
        id = aId;
    }

    public Integer GetId(){
        return id;
    }

    public void SetNom(String aNom){
        nom = aNom;
    }

    public String GetNom(){
        return nom;
    }
    public void SetLongitude(Double aLongitude){
        longitude = aLongitude;
    }

    public Double GetLongitude(){
        return longitude;
    }

    public void SetLatitude(Double aLatitude){
        latitude = aLatitude;
    }

    public Double GetLatitude(){
        return latitude;
    }
    public void SetSiteWeb(String aSiteWeb){
        siteWeb = aSiteWeb;
    }

    public String GetSiteWeb(){
        return siteWeb;
    }

    public void SetLivraisonRestaurant(Integer aLivraisonRestaurant){
        livraisonRestaurant = aLivraisonRestaurant;
    }

    public Integer GetLivraisonRestaurant(){
        return livraisonRestaurant;
    }

    public void SetConditionLivraisonRestaurant(String aConditionLivraisonRestaurant){
        conditionLivraisonRestaurant = aConditionLivraisonRestaurant;
    }

    public String GetConditionLivraisonRestaurant(){
        return conditionLivraisonRestaurant;
    }

    /***
     * Override de la methode ToString pour la class Restaurant
     * @return toutes les propriétés de l'objet Restaurant
     */
    public String ToString(){
        return  id + ", " + nom + ", " + longitude + ", " + latitude + ", " + siteWeb + ", " + livraisonRestaurant + ", " + conditionLivraisonRestaurant;
    }

    /***
     * Override de la methode equals pour la class Restaurant
     * @param restaurant l'objet Restaurant à comparer
     * @return boolean true si les latitudes et les longitudes sont égales sinon false
     */
    public boolean equals(Restaurant restaurant)
    {
        if (longitude == restaurant.longitude && latitude == restaurant.latitude)
        {
            return true;
        }
        return false;
    }
}

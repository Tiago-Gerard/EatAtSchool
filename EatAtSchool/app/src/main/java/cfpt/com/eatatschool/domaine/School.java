package cfpt.com.eatatschool.domaine;

/**
 * Created by ADMINELEVE on 15.03.2018.
 *
 * Classe de l'objet School
 */
public class School {
    // Variables de classe pour l'objet School
    private Integer id;
    private String nom;
    private Double longitude;
    private Double latitude;

    // Constructeur par défault
    public School()
    {

    }

    /**
     * Constructeur désigner
     * @param unId l'Id de l'objet School
     * @param unNom le nom de l'objet School
     * @param uneLongitude sa longitude de l'objet School
     * @param uneLatitude sa latitude de l'objet School
     */
    public  School(Integer unId, String unNom, Double uneLongitude, Double uneLatitude)
    {
        SetId(unId);
        SetNom(unNom);
        SetLongitude(uneLongitude);
        SetLatitude(uneLatitude);
    }

    /***
     * Getter Setter pour l'objet School
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
    /***
     * Override de la methode ToString pour la class School
     * @return toutes les propriétés de l'objet School
     */
    public String ToString(){
        return  id + ", " + nom + ", " + longitude + ", " + latitude;
    }
    /***
     * Override de la methode equals pour la class School
     * @param school l'objet School à comparer
     * @return boolean true si les latitudes et les longitudes sont égales sinon false
     */
    public boolean equals(School school)
    {
        if (longitude == school.longitude && latitude == school.latitude)
        {
            return true;
        }
        return false;
    }


}

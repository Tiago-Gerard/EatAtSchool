package cfpt.com.eatatschool.domaine;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSerializable {

    @SerializedName("idUser")
    @Expose
    private String idUser;
    @SerializedName("emailUser")
    @Expose
    private String emailUser;
    @SerializedName("loginUser")
    @Expose
    private String loginUser;
    @SerializedName("passwordUser")
    @Expose
    private String passwordUser;
    @SerializedName("imageUser")
    @Expose
    private String imageUser;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

}

package bean;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String cin;
    private String sexe;
    private int signale;
    private float solde;
    private String tel;
    private String password;
    private String datenaissance;
    private String role;

    public float getSolde() {
        return solde;
    }
    public void setSolde(float solde) {
        this.solde = solde;
    }
    public int getSignale() {
        return signale;
    }
    public void setSignale(int signale) {
        this.signale = signale;
    }
    public String getDatenaissance() {
        return datenaissance;
    }
    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getCin() {
        return cin;
    }
    public void setCin(String cin) {
        this.cin = cin;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public User() {
        super();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public User(int id, String nom, String prenom, String email,String sexe, String tel, String password,String role) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe=sexe;
        this.tel=tel;
        this.password = password;
        this.role = role;

    }
    public User(String nom, String prenom, String email,String sexe,String tel,String password,String role) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe=sexe;
        this.tel=tel;
        this.password = password;
        this.role = role;

    }

}

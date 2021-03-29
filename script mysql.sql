

#------------------------------------------------------------
# Table: users
#------------------------------------------------------------

CREATE TABLE users(
        id     Int  Auto_increment  NOT NULL ,
        nom    Varchar (50) NOT NULL ,
        prenom Varchar (50) NOT NULL ,
        email  Varchar (100) NOT NULL ,
		cin    Varchar (100) NOT NULL ,
        sexe   Varchar(10) NOT NULL,
		role varchar(10) NOT NULL,
		signale int NOT NULL,
		datenaissance date NOT NULL,
		pdp  Varchar(1000) NOT NULL, #chemin de la photo de profil
		cinphoto  Varchar(1000) NOT NULL, #chemin de la photo de cin
	tel    Varchar (10) NOT NULL ,
        password Varchar (100) NOT NULL ,
        solde float NOT NULL , # a linscription il sera initialise par 0
	,CONSTRAINT User_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: offre
#------------------------------------------------------------

CREATE TABLE offre(
        id       Int  Auto_increment  NOT NULL ,
		id_conducteur        Int NOT NULL,
		vdepart  Varchar(1000) NOT NULL,
		adepart  Varchar(1000) NOT NULL,
		varrivee  Varchar(1000) NOT NULL,
		aarrivee  Varchar(1000) NOT NULL,
		datedepart DATETIME NOT NULL,
		typevoiture Varchar(1000) NOT NULL,
		nummatricule Varchar(1000) NOT NULL,
		numplace int NOT NULL,
		mode_paiement Varchar(1000) NOT NULL,
		prix float NOT NULL,
		taillevalise Varchar(1000) NOT NULL,
		sexe_passager Varchar(1000) NOT NULL, # les filles seules,les garcons seuls,tout le monde
	,CONSTRAINT Offre_PK PRIMARY KEY (id_offre)

	,CONSTRAINT Offre_User_FK FOREIGN KEY (id_conducteur) REFERENCES users(id)
)ENGINE=InnoDB;
#------------------------------------------------------------
# Table: passage 
#------------------------------------------------------------

CREATE TABLE passage(                          #definir les villes de passage
        id       Int  Auto_increment  NOT NULL ,
		numoffre int,
		vpassage  Varchar(1000) NOT NULL, 
	,CONSTRAINT passage_PK PRIMARY KEY (id)

)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: messagerie
#------------------------------------------------------------

CREATE TABLE messagerie(
		id Int  Auto_increment  NOT NULL ,
        id_destinataire       Int NOT NULL ,
        id_destinateur          Int NOT NULL ,
        message  Varchar(1000) NOT NULL,
		datemesg DATETIME NOT NULL,
		lu int NOT NULL,
	,CONSTRAINT messagerie_PK PRIMARY KEY (id)

	,CONSTRAINT messagerie_FK FOREIGN KEY (id_destinataire) REFERENCES users(id)
	,CONSTRAINT messagerie0_FK FOREIGN KEY (id_destinateur) REFERENCES users(id)
)ENGINE=InnoDB;



































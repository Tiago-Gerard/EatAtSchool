<?php
/*
	Projet:			Eat@School - Projet réalisé dans le cadre du module 306 - Réaliser un petit projet informatique
	
	Page: 			index.php
	Description: 	Retourne tous les avis enregistrés dans la base de données
	
	Auteur:			Florent Beney, 
					Clément Christensen, 
					Anthony Chevrolet, 
					Yannis Perrin, 
					Gael Mariot, 
					Aïssa Bovet, 
					Constantin Herrmann, 
					Jamal Albadri
*/

// Require du PDO
require "../pdo.php";

/*
	Classe pour modéliser un Avis 
	
	id: 			L'id de l'avis
	Commentaire: 	Le texte qui représente l'avis
	Note Qualité:	La note de la qualité du service et de la nourriture
	Note Rapidité:	La note de rapidité du service du Restaurant
	Id Restaurant:	L'id du restaurant concerné par l'avis
*/	
class Avis{
	public $id;
	public $commentaire;
	public $noteQualite;
	public $noteRapidite;
	public $idRestaurant;
	
}

// Retourne tous les avis en Json
function getAvis(){
    $db = getDB();
    $request = $db->prepare("SELECT * FROM `Avis`");
    $request->execute();
    $data = $request->fetchAll(PDO::FETCH_ASSOC);
	$array = array();
	foreach($data as $entry){
		$obj = new Avis();
		$obj->id = $entry['idAvis'];
		$obj->commentaire = $entry['commentaireAvis'];
		$obj->noteQualite =$entry['noteQualiteAvis'];
		$obj->noteRapidite =$entry['noteRapiditeAvis'];
		$obj->idRestaurant =$entry['idRestaurant'];
		$array[] = $obj;
	}
    return json_encode($array);
}


echo getAvis();

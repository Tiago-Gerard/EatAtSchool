<?php
/*
	Projet:			Eat@School - Projet réalisé dans le cadre du module 306 - Réaliser un petit projet informatique
	
	Page: 			index.php
	Description: 	Retourne toutes les écoles enregistrées dans la base de données
	
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
	Classe pour modéliser une école 
	
	id: 			L'id de l'école
	Nom: 			Nom de l'école
	Lon:			Longitude de l'école
	Lat:			Latitude de l'école
*/	
class Ecole{
	public $id;
	public $nom;
	public $lon;
	public $lat;
}

// Retourne toutes les écoles en Json
function getEcoles(){
    $db = getDB();
    $request = $db->prepare("SELECT * FROM `Ecole`");
    $request->execute();
    $data = $request->fetchAll(PDO::FETCH_ASSOC);
	$array = array();
	foreach($data as $entry){
		$obj = new Ecole();
		$obj->id = $entry['idEcole'];
		$obj->nom = $entry['nomEcole'];
		$obj->lon =$entry['longitudeEcole'];
		$obj->lat =$entry['latitudeEcole'];
		$array[] = $obj;
	}
    return json_encode($array);
}


echo getEcoles();

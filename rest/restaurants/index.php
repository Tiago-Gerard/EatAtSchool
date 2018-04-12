<?php
/*
	Projet:			Eat@School - Projet réalisé dans le cadre du module 306 - Réaliser un petit projet informatique
	
	Page: 			index.php
	Description: 	Retourne tous les restaurants enregistrés dans la base de données
	
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
	Classe pour modéliser un Restaurant 
	
	idRestaurant: 						L'id du restaurant
	Nom Restaurant:						Le nom du restaurant
	Latitude Restaurant:				La latitude du restaurant
	Longitude Restaurant:				La longitude du restaurant
	Site Web Restaurant:				L'adresse du site web du restaurant
	Livraison Restaurant:				Si le restaurant livre ou pas
	Condition Livraison Restaurant:		Les conditions de la livraison du restaurant
*/	
class Restaurant {
	public $idRestaurant;
	public $nomRestaurant;
	public	$latitudeRestaurant ;
	public	$longitudeRestaurant ;
	public	$siteWebRestaurant; 
	public	$livraisonRestaurant;
	public	$conditionLivraisonRestaurant;
}

// Retourne tous les restaurants en Json
function getRestaurants(){
    $db = getDB();
    $request = $db->prepare("SELECT * FROM `Restaurant`");
    $request->execute();
    $data = $request->fetchAll(PDO::FETCH_ASSOC);
	$array = array();
	foreach($data as $entry){
		$obj = new Restaurant();
		$obj->idRestaurant = $entry['idRestaurant'];
		$obj->nomRestaurant = $entry['nomRestaurant'];
		$obj->latitudeRestaurant =$entry['latitudeRestaurant'];
		$obj->longitudeRestaurant =$entry['longitudeRestaurant'];
		$obj->siteWebRestaurant =$entry['siteWebRestaurant'];
		$obj->livraisonRestaurant =$entry['livraisonRestaurant'];
		$obj->conditionLivraisonRestaurant =$entry['conditionLivraisonRestaurant'];
		$array[] = $obj;
	}
    return json_encode($array);
}

echo getRestaurants();

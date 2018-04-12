<?php
/*
	Projet:			Eat@School - Projet réalisé dans le cadre du module 306 - Réaliser un petit projet informatique
	
	Page: 			index.php
	Description: 	Retourne tous les menus enregistrés dans la base de données
	
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
	Classe pour modéliser un Menu 
	
	idMenu: 				L'id du Menu
	Nom Menu: 				Le nom du menu
	Prix Menu:				Le prix du menu
	Boisson Comprise Menu:	Indique si la boisson est comprise dans le menu
	Composition Menu:		La composition du menu
	Id Restaurant:			L'id du restaurant auquel appartient le menu
*/	
class Menu{
		public $idMenu;
		public $nomMenu;
		public $prixMenu;
		
		public $boissonCompriseMenu ;
		public $CompositionMenu ;
		public $idRestaurant ;
}

// Retourne les menus d'un restaurant en fonction de l'id du restaurant, en Json
function getMenusByIdRestaurant($id){
    $db = getDB();
    $request = $db->prepare("SELECT * FROM `Menu` WHERE idRestaurant = :id");
	$request->bindParam(':id',$id);
    $request->execute();
    $data = $request->fetchAll(PDO::FETCH_ASSOC);
	$array = array();
	foreach($data as $entry){
		$obj = new Menu();
		$obj->idMenu = $entry['idMenu'];
		$obj->nomMenu = $entry['nomMenu'];
		$obj->prixMenu =$entry['prixMenu'];
		$obj->boissonCompriseMenu =$entry['boissonCompriseMenu'];
		$obj->CompositionMenu =$entry['CompositionMenu'];
		$obj->idRestaurant =$entry['idRestaurant'];
		$array[] = $obj;
	}
    return json_encode($array);
}

// Retourne tous les menu en Json
function getMenus(){
    $db = getDB();
    $request = $db->prepare("SELECT * FROM `Menu`");
	$request->bindParam(':id',$id);
    $request->execute();
    $data = $request->fetchAll(PDO::FETCH_ASSOC);
	$array = array();
	foreach($data as $entry){
		$obj = new Menu();
		$obj->idMenu = $entry['idMenu'];
		$obj->nomMenu = $entry['nomMenu'];
		$obj->prixMenu =$entry['prixMenu'];
		$obj->boissonCompriseMenu =$entry['boissonCompriseMenu'];
		$obj->CompositionMenu =$entry['CompositionMenu'];
		$obj->idRestaurant =$entry['idRestaurant'];
		$array[] = $obj;
	}
    return json_encode($array);
}

$search = $_SERVER['QUERY_STRING'];
$method = $_SERVER['REQUEST_METHOD'];
$search = json_decode(strtolower($search));

if($search !="")
{
	echo getMenusByIdRestaurant($search);
}
else
{
	echo getMenus();
}		
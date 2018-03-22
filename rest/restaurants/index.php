<?php
require "../pdo.php";
class Restaurant {
	public $idRestaurant;
	public $nomRestaurant;
	public	$latitudeRestaurant ;
	public	$longitudeRestaurant ;
	public	$siteWebRestaurant; 
	public	$livraisonRestaurant;
	public	$conditionLivraisonRestaurant;
}
function getRestaurants(){
    $db = getDB();
    $request = $db->prepare("SELECT * FROM `Restaurant`");
    $request->execute();
    $data = $request->fetchAll(PDO::FETCH_ASSOC);
    return json_encode($data,JSON_FORCE_OBJECT);
}
function getRestaurantsObj(){
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

echo getRestaurantsObj();

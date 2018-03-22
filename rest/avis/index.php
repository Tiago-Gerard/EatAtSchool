<?php
require "../pdo.php";
class Avis{
	public $id;
	public $commentaire;
	public $noteQualite;
	public $noteRapidite;
	public $idRestaurant;
	
}
function getAvis(){
    $db = getDB();
    $request = $db->prepare("SELECT * FROM `Avis`");
    $request->execute();
    $data = $request->fetchAll(PDO::FETCH_ASSOC);
    return json_encode($data,JSON_FORCE_OBJECT);
}
function getAvisObj(){
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


echo getAvisObj();

<?php
require "../pdo.php";
class Ecole{
	public $id;
	public $nom;
	public $lon;
	public $lat;
}
function getEcoles(){
    $db = getDB();
    $request = $db->prepare("SELECT * FROM `Ecole`");
    $request->execute();
    $data = $request->fetchAll(PDO::FETCH_ASSOC);
    return json_encode($data,JSON_FORCE_OBJECT);
}
function getEcolesObj(){
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


echo getEcolesObj();

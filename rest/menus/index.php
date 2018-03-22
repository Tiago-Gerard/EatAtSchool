<?php
require "../pdo.php";
class Menu{
		public $idMenu;
		public $nomMenu;
		public $prixMenu;
		
		public $boissonCompriseMenu ;
		public $CompositionMenu ;
		public $idRestaurant ;
}

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
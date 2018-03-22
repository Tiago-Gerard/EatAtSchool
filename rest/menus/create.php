<?php
require "../pdo.php";

function insertMenu($nom,$prix,$boisson,$composition,$idRestaurant)
{
	$boisson = ($boisson?1:0);
	try{
		$db = getDB();
		$request = $db->prepare("INSERT INTO `Menu` (nomMenu,prixMenu,boissonCompriseMenu,CompositionMenu,idRestaurant) VALUES (:nom, :prix, :boisson,:compo,:restaurant)");
		$request->bindParam(':nom',$nom,PDO::PARAM_STR);
		$request->bindParam(':prix',$prix,PDO::PARAM_INT);
		$request->bindParam(':boisson',$boisson,PDO::PARAM_INT);
		$request->bindParam(':compo',$composition,PDO::PARAM_STR);
		$request->bindParam(':restaurant',$idRestaurant,PDO::PARAM_INT);
		return $request->execute();
		
	}	
	catch(Exception $e){
		return false;
	}
}

		$data = json_decode(file_get_contents("php://input"));
		if (insertMenu($data->name, $data->prix,$data->boisson,$data->composition,$data->idRestaurant)) {
			$dataReturn->message = "creation reussie";
			echo json_encode($dataReturn);
		} else {			
		    $dataReturn->message = "cpabien";
			echo json_encode($dataReturn);
		}
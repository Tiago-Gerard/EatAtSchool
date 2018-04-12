<?php
/*
	Projet:			Eat@School - Projet réalisé dans le cadre du module 306 - Réaliser un petit projet informatique
	
	Page: 			index.php
	Description: 	Vérifie si un restaurant donné accepte les livraisons
	
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

// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

// Retourne le champ 'livraisonRestaurant' qui permet de savoir si un restaurant livre, en Json
function getLivraison($id)
{
    $request = "SELECT `livraisonRestaurant` FROM `Restaurant` WHERE `idRestaurant` = :id";
    $connect = getDB();
    $dbQuery = $connect->prepare($request);
    $dbQuery->bindParam(':id',$id);
    $dbQuery->execute();
    $data = $dbQuery->fetch(PDO::FETCH_ASSOC);
    return json_encode($data,JSON_FORCE_OBJECT);
}

$data = json_decode(file_get_contents("php://input"));
echo getLivraison($data->id);

?>

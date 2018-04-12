<?php
/*
	Projet:			Eat@School - Projet réalisé dans le cadre du module 306 - Réaliser un petit projet informatique
	
	Page: 			index.php
	Description: 	Retourne le site d'un restaurant donné
	
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

// Retourne le site d'un restaurant en fonction de l'id du restaurant, en Json
function getSite($id)
{
    $request = "SELECT `siteWebRestaurant` FROM `Restaurant` WHERE `idRestaurant` = :id";
    $connect = getDB();
    $dbQuery = $connect->prepare($request);
    $dbQuery->bindParam(':id',$id);
    $dbQuery->execute();
    $data = $dbQuery->fetch(PDO::FETCH_ASSOC);
    return json_encode($data,JSON_FORCE_OBJECT);
}

$data = json_decode(file_get_contents("php://input"));
echo getSite($data->id);

?>

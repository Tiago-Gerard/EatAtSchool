<?php
/*
	Projet:			Eat@School - Projet réalisé dans le cadre du module 306 - Réaliser un petit projet informatique
	
	Page: 			create.php
	Description: 	Ajoute une école dans la base de données
	
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
	Fonction pour ajouter une école
	
	Nom: 			Nom de l'école
	Lon:			Longitude de l'école
	Lat:			Latitude de l'école
*/	
function createEcole($nom, $lon, $lat) {
    $flagOk = true;
    if(empty($nom) || empty($lon)||empty($lat)){
        $flagOk=false;
    }
    else{
        try {
            $request = "INSERT INTO Ecole (`nomEcole`,`longitudeEcole`,`latitudeEcole`) VALUES(:nom,:lon,:lat)";
            $connect = getDB();
            $dbQuery = $connect->prepare($request);
            $dbQuery->bindParam(':nom', $nom);
            $dbQuery->bindParam(':lon', $lon);
            $dbQuery->bindParam(':lat', $lat);
            $dbQuery->execute();
        } catch (Exception $e) {
            $flagOk = false;
        }
    }
    return $flagOk;
}

$data = json_decode(file_get_contents("php://input"));
if (createEcole($data->name, $data->lon, $data->lat)) {
    $dataReturn->message = "Ecole created sucessfuly";
    echo json_encode($dataReturn);
} else {
    $dataReturn->message = "Ecole couldn't be created";
    echo json_encode($dataReturn);
}
	

<?php

require "../pdo.php";

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
	

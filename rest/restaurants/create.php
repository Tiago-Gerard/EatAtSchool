<?php
require "../pdo.php";

// required headers
header("Access-Control-Allow-Methods: POST");

function createRestaurant($nom ,$lon,$lat,$site,$livraison,$condition)
{
  $flagOk = true;
  if (empty($nom) || empty($lon) || empty($lat) || empty($site) || empty($livraison) || empty($condition)) {
    return false;
  }
    try {
    $request = "INSERT INTO Restaurant VALUES(NULL,:nom,:lon,:lat,:site,:livraison,:condition)";
    $connect = getDB();
    $dbQuery = $connect->prepare($request);
    $dbQuery->bindParam(':nom',$nom);
    $dbQuery->bindParam(':lon',$lon);
    $dbQuery->bindParam(':lat',$lat);
    $dbQuery->bindParam(':site',$site);
    $dbQuery->bindParam(':livraison',$livraison);
    $dbQuery->bindParam(':condition',$condition);
    $dbQuery->execute();
  } catch (Exception $e) {
      //return false
      $flagOk = false;
  }
  return $flagOk;
}

$data = json_decode(file_get_contents("php://input"));

if (createRestaurant($data->nom, $data->lon, $data->lat, $data->site, $data->livraison, $data->condition)) {
    $dataReturn->message = "Restaurant created sucessfuly";
    echo json_encode($dataReturn);
} else {
    $dataReturn->message = "Restaurant couldn't be created";
    echo json_encode($dataReturn);
}

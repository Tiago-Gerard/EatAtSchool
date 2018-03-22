<?php
require "../pdo.php";

function createAvis($comm, $noteQualite, $noteRapidite, $idRestaurant){
    $request = "INSERT INTO Avis VALUES(NULL, :comm, :noteQualite, :noteRapidite, :idRestaurant)";
    $connect = getDB();
    $dbQuery = $connect->prepare($request);
    $dbQuery->bindParam(':comm', $comm);
    $dbQuery->bindParam(':noteQualite', $noteQualite);
    $dbQuery->bindParam(':noteRapidite', $noteRapidite);
    $dbQuery->bindParam(':idRestaurant', $idRestaurant);
    $dbQuery->execute();
}

//Changer $jsonReceveid par les valeurs reçues depuis l'appli
$jsonReceveid = '{"commentaire":"Cest un test","noteQualite":2.5,"noteRapidite":3,"idRestaurant":1}';
$json = json_decode($jsonReceveid, true);

//Changer les noms des cases du tableau si nécessaire
$comm = (isset($json['commentaire'])) ? filter_var($json['commentaire'], FILTER_SANITIZE_STRING) : "";
$noteQualite = (isset($json['noteQualite'])) ? filter_var($json['noteQualite'], FILTER_SANITIZE_NUMBER_FLOAT, FILTER_FLAG_ALLOW_FRACTION) : "";
$noteRapidite = (isset($json['noteRapidite'])) ? filter_var($json['noteRapidite'], FILTER_SANITIZE_NUMBER_FLOAT, FILTER_FLAG_ALLOW_FRACTION) : "";
$idRestaurant = (isset($json['idRestaurant'])) ? filter_var($json['idRestaurant'], FILTER_SANITIZE_NUMBER_INT) : "";

if($comm != null && $noteQualite != null && $noteRapidite != null && $idRestaurant != null){
    createAvis($comm, $noteQualite, $noteRapidite, $idRestaurant);
}

<?php
require "../pdo.php";

// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

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

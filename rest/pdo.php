﻿<?php
DEFINE('DBHOST',"localhost");
DEFINE('URL',"http://10.134.98.158/rest/");
DEFINE('DBUSER',"admin");
DEFINE('DBPASS',"eatatschool");
DEFINE('DBNAME', "EatAtSchool");
static $pdo = null;
header("Content-Type: application/json; charset=UTF-8");

function getDB(){
    if(is_null($pdo)){

    $dsn = 'mysql:dbname='.DBNAME.';host='.DBHOST;
    try{
    $pdo = new PDO($dsn,DBUSER,DBPASS);
    } catch (PDOException $e){
        echo 'Connexion �chou�e : '.$e->getMessage();
    }}
    return $pdo;
}
?>

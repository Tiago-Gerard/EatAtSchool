<?php
/*
	Projet:			Eat@School - Projet réalisé dans le cadre du module 306 - Réaliser un petit projet informatique
	
	Page: 			pdo.php
	Description: 	Contient les informations de connexion à la base de données
	
	Auteur:			Florent Beney, 
					Clément Christensen, 
					Anthony Chevrolet, 
					Yannis Perrin, 
					Gael Mariot, 
					Aïssa Bovet, 
					Constantin Herrmann, 
					Jamal Albadri
*/

// Constantes
DEFINE('DBHOST',"localhost");
DEFINE('URL',"http://10.134.98.158/rest/");
DEFINE('DBUSER',"admin");
DEFINE('DBPASS',"eatatschool");
DEFINE('DBNAME', "EatAtSchool");
static $pdo = null;
header("Content-Type: application/json; charset=UTF-8");

// Connexion à la base de données
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

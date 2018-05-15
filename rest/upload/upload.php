<?php
uploadMedia("img", array('.png', '.gif', '.jpg', '.jpeg'));
function uploadMedia($typeMedia, $ext, $lastIdInserted = NULL) {
    if (isset($_FILES['image'])) {
        $dossier = "images/";
        $extensions = $ext;
        $taille_maxi = 100000000;
        var_dump($_FILES);
        $fichier = basename($_FILES['image']['name']) . microtime();
        if (!isset($erreur)) { //S'il n'y a pas d'erreur, on upload
            var_dump($_FILES['image']['name']);
            //On formate le nom du fichier ici...
            $fichier = strtr($fichier, 'ÀÁÂÃÄÅÇÈÉÊËÌÍÎÏÒÓÔÕÖÙÚÛÜÝàáâãäåçèéêëìíîïðòóôõöùúûüýÿ', 'AAAAAACEEEEIIIIOOOOOUUUUYaaaaaaceeeeiiiioooooouuuuyy');
            $fichier = preg_replace('/([^.a-z0-9]+)/i', '-', $fichier);
            error_log(print_r($_FILES,true));
            if (move_uploaded_file($_FILES['image']['tmp_name'], $dossier.$fichier)) { //Si la fonction renvoie TRUE, c'est que ça a fonctionné...
                echo $fichier;
                json_encode("Réussi");
            } else { //Sinon (la fonction renvoie FALSE).
                json_encode('Echec de l\'upload !');
                header("Status: HTTP/1.0 500 Internal Error");
            }
        } else {
            header("Status: HTTP/1.0 501 Internal Error");
            echo $erreur;
        }
    }
}
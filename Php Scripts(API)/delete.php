<?php

    $connection = mysqli_connect("localhost","id12226132_shawon","12345678","id12226132_employee_management_system");
    
     $id = $_POST["id"];
     
     $sql = "DELETE FROM data WHERE id='$id'";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Deleted";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($connection);
     


?>
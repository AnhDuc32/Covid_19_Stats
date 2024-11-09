<?php
if(!empty($_POST['email']) && !empty($_POST['password'])){
    $email = $_POST['email'];
    $password = $_POST['password'];
    $result = array();
    $con = mysqli_connect("localhost", "root", "", " login_register");
    if ($con) {
        $sql = "select * from users where email = '".$email."'";
        $res = mysqli_query($con, $sql);
        if (mysqli_num_rows($res) != 0) {
            $row = mysqli_fetch_assoc($res);
            if ( $email == $row['email'] && password_verify($password, $row['password'])){
                try {
                    $apiKey = bin2hex(random_bytes(23));
                } catch (Exception $e) {
                    $apiKey = bin2hex(uniqid($email, true));
                }
                $sqlUpdate = "update users set apiKey = '" . $apiKey . "' where email = '" . $email . "'";
                if (mysqli_query($con, $sqlUpdate)) {
                    $result = array("status" => "success", "message" => "Login successful", "name" => $row['name'], "email" => $row['email'], "apiKey" => $apiKey);
                }else $result = array("status" => "failed", "message" => "Error updating API key: " . mysqli_error($con));
            }else $result = array("status" => "failed", "message" => "Incorrect password");
        }else $result = array("status" => "failed", "message" => "Email not found");
    }else $result = array("status" => "failed", "message" => "Database connection fail1ed");
}else $result = array("status" => "failed", "message" => "All fields are required");

echo json_encode($result, JSON_PRETTY_PRINT);
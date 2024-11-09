<?php
if (!empty($_POST['email']) && !empty($_POST['apiKey'])) {
    $email = $_POST['email'];
    $apiKey = $_POST['apiKey'];
    $result = array();
    $con = mysqli_connect("localhost", "root", "", " login_register");
    if ($con){
        $sql = "select * from users where email= '".$email."' and apiKey='".$apiKey."'";
        $res = mysqli_query($con, $sql);
        if (mysqli_num_rows($res) != 0) {
            $row = mysqli_fetch_assoc($res);
            $result =  array("status" => "success", "message" => "Data fetch successfully", "name" => $row["name"], "email" => $row["email"], "phone" => $row["phone"], "apiKey" => $row["apiKey"]);
        }else $result = array("status" => "failed", "message" => "Unauthorized access");
    }else $result = array("status" => "failed", "message" => "Database connection failed");
}else $result = array("status" => "failed", "message" => "All fields are required");

echo json_encode($result, JSON_PRETTY_PRINT);
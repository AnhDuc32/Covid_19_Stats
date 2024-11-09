<?php
if(!empty($_POST['name']) && !empty($_POST['email'] && !empty($_POST['password']) && !empty($_POST['phone']))) {
    $con = mysqli_connect("localhost", "root", "", " login_register");
    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = password_hash($_POST['password'], PASSWORD_DEFAULT);
    $phone = $_POST['phone'];
    if ($con) {
        $sql = "insert into users (name, email, password, phone) values ('".$name."', '".$email."', '".$password."', '".$phone."')";
        if(mysqli_query($con, $sql)) {
            echo "success";
        }else echo "Registration failed";

    } else echo "Database connection failed";
} else echo "All fields are required";
<?php
if (!empty($_POST['email']) && !empty($_POST['apiKey'])) {
    $email = $_POST['email'];
    $apiKey = $_POST['apiKey'];
    $con = mysqli_connect("localhost", "root", "", " login_register");

    if (!$con) {
        die("Database connection failed: " . mysqli_connect_error());
    }

    $sql = "SELECT * FROM users WHERE email = '$email' AND apiKey = '$apiKey'";
    $res = mysqli_query($con, $sql);

    if (mysqli_num_rows($res) != 0) {
        $row = mysqli_fetch_assoc($res);
        $sqlUpdate = "UPDATE users SET apiKey = '' WHERE email = '$email'";

        if (mysqli_query($con, $sqlUpdate)) {
            echo "success";
        } else {
            echo "Logout failed: " . mysqli_error($con);
        }
    } else {
        echo "Unauthorized access";
    }
} else {
    echo "All fields are required";
}
?>

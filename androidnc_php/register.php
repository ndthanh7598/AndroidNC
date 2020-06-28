<?php
	$con = mysqli_connect("localhost","ffcteam","nhansutpt","ffcteam");
	date_default_timezone_set('Asia/Ho_Chi_Minh');

	if ($_SERVER['REQUEST_METHOD'] === 'POST') {
		$email = $_POST['email'];
		$pass = $_POST['pass'];
		$name = $_POST['name'];

		$sql = "SELECT * FROM a_tbl_taikhoan WHERE sEmail LIKE '" .$email. "'";

		$query = mysqli_query($con, $sql);
		if ($query->num_rows === 0){
			$sql = "INSERT INTO a_tbl_users(sTenHienThi) VALUES 
					('".$name."')";

			mysqli_query($con, $sql);
			$id = mysqli_insert_id($con); 
			$sql = "INSERT INTO a_tbl_taikhoan(sEmail, sMatKhau, FK_iMaUser, sAvatar) VALUES 
					('".$email."','".$pass."','".$id."', 'http://ffcteam.com/androidnc/image/deep.jpg')";
			$query = mysqli_query($con, $sql);
			
			if (mysqli_affected_rows($con) != 0)
            {
                echo json_encode(["result" => "Thêm tài khoản thành công"]);
            }
            else{
            	echo json_encode(["result" => "Có lỗi xảy ra. Vui lòng thử lại!"]);
            }
		}
		else{
			echo json_encode(["result" => "Email đã tồn tại"]);
		}
		exit();
	}
?>
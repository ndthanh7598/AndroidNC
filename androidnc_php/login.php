<?php
	$con = mysqli_connect("localhost","ffcteam","nhansutpt","ffcteam");
	date_default_timezone_set('Asia/Ho_Chi_Minh');

	if ($_POST['email']){
		$email = $_POST['email'];
		$pass = $_POST['pass'];

		$sql = "SELECT * FROM a_tbl_taikhoan
				INNER JOIN a_tbl_users ON a_tbl_taikhoan.FK_iMaUser = a_tbl_users.PK_iMaUser
				WHERE sEmail LIKE '" .$email. "' AND sMatKhau = '". $pass ."'";

		$query = mysqli_query($con, $sql);
		while($row = $query->fetch_assoc())
		{
			$arr = $row;
			break;
		}
		if (!empty($arr)){
			echo json_encode($arr);
		}
		else{
			echo json_encode(["result" => "Không có dữ liệu"]);
		}
		exit();
	}

	if ($_POST['token']){
		$userId = $_POST['userId'];
		$token = $_POST['token'];

		$sql = "UPDATE a_tbl_users SET sRegToken = '$token' WHERE PK_iMaUser = $userId";
		$query = mysqli_query($con, $sql);
		
		if (mysqli_affected_rows($con) != 0)
        {
            echo json_encode(["result" => "Cập nhật token thành công"]);
        }
        else{
        	echo json_encode(["result" => "Có lỗi xảy ra. Vui lòng thử lại!"]);
        }
	}
?>
<?php
	date_default_timezone_set('Asia/Ho_Chi_Minh');

	function connect()
	{
		return mysqli_connect("localhost","ffcteam","nhansutpt","ffcteam");
	}

	function getListKhaoSat()
	{
		$user = $_POST['userId'];
		$con = connect();
		$result = [];

		$sql = "SELECT * FROM a_dm_khaosat";
		$query = mysqli_query($con, $sql);
		$dmks = [];
		while($row = $query->fetch_assoc())
		{
			$dmks[] = $row;
		}
		$result['dmks'] = $dmks;

		$sql = "SELECT * FROM a_tbl_user_khaosat WHERE PK_iMaUser = ".$user;
		$query = mysqli_query($con, $sql);
		$ksuser = [];
		while($row = $query->fetch_assoc())
		{
			$ksuser[] = $row;
		}
		$result['ksuser'] = $ksuser;

		echo json_encode($result);
		exit();
	}

	function saveKhaoSat()
	{
		$user = $_POST['userId'];
		$listks = json_decode($_POST['listKs']);
		$con = connect();

		$sql = "DELETE FROM a_tbl_user_khaosat WHERE PK_iMaUser = ".$user;
		$query = mysqli_query($con, $sql);

		$sql = "INSERT INTO a_tbl_user_khaosat(PK_iMaKS, PK_iMaUser) VALUES ";
		foreach ($listks as $k => $v) {
			$sql .= "(".$v.",".$user."),";
		}
		$sql = rtrim($sql, ",");
		$query = mysqli_query($con, $sql);

		$sql = "UPDATE a_tbl_users SET sKhaoSat = 'daks' WHERE PK_iMaUser = ".$user;
		$query = mysqli_query($con, $sql);
		
		// if (mysqli_affected_rows($con) != 0){
            echo json_encode(["result" => "Lưu thông tin khảo sát thành công"]);
        // }
        // else{
        // 	echo json_encode(["result" => "Có lỗi xảy ra. Vui lòng thử lại!"]);
        // }
        exit();
	}

	// function saveThemKhaoSat(){
	// 	$tieuchi = $_POST['tieuchi'];
	// 	$ghichu = $_POST['ghichu'];
	// 	$image = $_POST['image'];
	// 	$con = connect();

	// 	if (!empty($image)){
	// 		$imgName = time().'.jpg';
	// 		$path = 'image/'.$imgName;
	// 		$sLinkAnh = 'http://ffcteam.com/androidnc/'.$path;
	// 	}
	// 	else{
	// 		$sLinkAnh = '';
	// 	}

	// 	$sql = "INSERT INTO a_dm_khaosat(sTieuChi, sGhiChu, sAnh) VALUES ('$tieuchi', '$ghichu', '$sLinkAnh')";

	// 	$query = mysqli_query($con, $sql);

	// 	if (mysqli_affected_rows($con) != 0){
	// 		if (!empty($image)){
	// 			file_put_contents($path, base64_decode($image));
	// 		}
 //            echo json_encode(["result" => "Thêm khảo sát thành công"]);
 //        }
 //        else{
 //        	echo json_encode(["result" => "Thêm khảo sát không thành công!"]);
 //        }
 //        exit();
	// }

	if ($_SERVER['REQUEST_METHOD'] === 'POST') {
		$action = $_POST['action'];

		switch ($action) {
			case 'getListKhaoSat':
				getListKhaoSat();
				break;
			case 'saveKhaoSat':
				saveKhaoSat();
				break;
			// case 'saveThemKhaoSat':
			// 	saveThemKhaoSat();
			// 	break;
		}
	}
?>
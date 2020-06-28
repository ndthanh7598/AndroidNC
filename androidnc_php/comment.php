<?php
	date_default_timezone_set('Asia/Ho_Chi_Minh');

	function connect()
	{
		return mysqli_connect("localhost","ffcteam","nhansutpt","ffcteam");
	}

	function notificationComment($data, $user, $sLinkAnh)
	{
		error_reporting(-1);
	    ini_set('display_errors', 'On');
		require_once __DIR__ . '/firebase/firebase.php';
	    require_once __DIR__ . '/firebase/push.php';
        $firebase = new Firebase();
        $push = new Push();

        $payload = array();
        $payload['team'] = 'Group10';
        $payload['score'] = '10';

        $title = $user['sTenHienThi'];
        $tenmon = $data[0]['sTenMon'];
        $message = "Đã bình luận về món $tenmon";

        $push->setTitle($title);
        $push->setMessage($message);

        if (!empty($sLinkAnh)) {
            $push->setImage("$sLinkAnh");
        } else {
            $push->setImage('');
        }
        $push->setIsBackground(FALSE);
        $push->setPayload($payload);

        $json = '';
        $response = '';

        $regId = [];
        foreach ($data as $key => $value) {
        	if (!empty($value['sRegToken'])){
        		$regId[] = $value['sRegToken'];
        	}
        }

        $json = $push->getPush();
        foreach ($regId as $key => $value) {
        	$response = $firebase->send($value, $json);
        }
        return $response;
	}

	function saveComment()
	{
		$idFood = $_POST['idFood'];
		$maUser = $_POST['maUser'];
		$sNoiDung = $_POST['noidung'];
		$sThoiGian = $_POST['thoigian'];
		$image = $_POST['image'];

		if (!empty($image)){
			$imgName = time().'.jpg';
			$path = 'image/'.$imgName;
			$sLinkAnh = 'http://ffcteam.com/androidnc/'.$path;
		}
		else{
			$sLinkAnh = '';
		}

		$con = connect();
		$sql = "INSERT INTO `a_tbl_binhluan`(`sNoiDung`, `sThoiGian`, `sLinkAnh`, `FK_iMaUser`, `FK_iMaMon`) VALUES ('$sNoiDung', '$sThoiGian', '$sLinkAnh', $maUser, $idFood)";

		$query = mysqli_query($con, $sql);
		if (mysqli_affected_rows($con) != 0){
			if (!empty($image)){
				file_put_contents($path, base64_decode($image));
			}

			$sql = "SELECT * FROM a_tbl_binhluan
					INNER JOIN a_tbl_users ON a_tbl_users.PK_iMaUser = a_tbl_binhluan.FK_iMaUser
					INNER JOIN a_tbl_monan ON a_tbl_monan.PK_iMaMon = a_tbl_binhluan.FK_iMaMon
					WHERE FK_iMaMon = $idFood AND FK_iMaUser != $maUser";
			$query = mysqli_query($con, $sql);
			while($row = $query->fetch_assoc())
			{
				$dataInComment[] = $row;
			}

			$sql = "SELECT * FROM a_tbl_users WHERE PK_iMaUser = $maUser";
			$query = mysqli_query($con, $sql);
			while($row = $query->fetch_assoc())
			{
				$infoUserCmt = $row;
				break;
			}
			$res = notificationComment($dataInComment, $infoUserCmt, $sLinkAnh);

			echo json_encode(["result" => $sLinkAnh, $res]);
		}
		else{
			// echo json_encode(["result" => $sql]);
			echo json_encode(["result" => "Có lỗi xảy ra. Vui lòng thử lại!"]);
		}
		exit();
	}

	function getCommentByIdFood()
	{
		$id = $_POST['idFood'];
		$con = connect();
		$sql = "SELECT a_tbl_binhluan.*, a_tbl_users.sTenHienThi, a_tbl_taikhoan.sAvatar FROM a_tbl_binhluan
				INNER JOIN a_tbl_users ON a_tbl_users.PK_iMaUser = a_tbl_binhluan.FK_iMaUser
				INNER JOIN a_tbl_taikhoan ON a_tbl_users.PK_iMaUser = a_tbl_taikhoan.FK_iMaUser
				WHERE FK_iMaMon = ".$id." ORDER BY sThoiGian ASC";
		
		$query = mysqli_query($con, $sql);
		$arr = [];

		while($row = $query->fetch_assoc())
		{
			$arr[] = $row;
		}

		echo json_encode(["data" => $arr]);
		exit();
	}

	function rateFood()
	{
		$idFood = $_POST['idFood'];
		$rate = $_POST['rate'];
		$con = connect();
		$sql = "UPDATE a_tbl_monan SET fDanhGia = CASE
				WHEN iSoDanhGia = 0 THEN $rate 
				ELSE (fDanhGia * iSoDanhGia + $rate)/(iSoDanhGia + 1) END, iSoDanhGia = (iSoDanhGia + 1)
				WHERE PK_iMaMon = $idFood";
		
		$query = mysqli_query($con, $sql);

		if (mysqli_affected_rows($con) != 0){
			$sql = "SELECT fDanhGia FROM a_tbl_monan WHERE PK_iMaMon = $idFood";
			$query = mysqli_query($con, $sql);
			while($row = $query->fetch_assoc())
			{
				$arr = $row;
				break;
			}
			echo json_encode(["result" => $arr["fDanhGia"]]);
		}
		else{
			echo json_encode(["result" => "Có lỗi xảy ra. Vui lòng thử lại!"]);
		}

		exit();
	}

	if ($_SERVER['REQUEST_METHOD'] === 'POST') {
		$action = $_POST['action'];

		switch ($action) {
			case 'saveComment':
				saveComment();
				break;
			case 'getCommentByIdFood':
				getCommentByIdFood();
				break;
			case 'rateFood':
				rateFood();
				break;
		}
	}
?>
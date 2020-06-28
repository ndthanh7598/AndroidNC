<?php
	date_default_timezone_set('Asia/Ho_Chi_Minh');

	function connect()
	{
		return mysqli_connect("localhost","ffcteam","nhansutpt","ffcteam");
	}

	function getFoodHint()
	{
		$user = $_POST['userId'];
		$con = connect();
		$sql = "SELECT * FROM a_tbl_user_khaosat
				INNER JOIN a_dm_khaosat ON a_tbl_user_khaosat.PK_iMaKS = a_dm_khaosat.PK_iMaKS
				WHERE PK_iMaUser = ".$user;
		
		$query = mysqli_query($con, $sql);
		$khaosat = [];

		while($row = $query->fetch_assoc())
		{
			$khaosat[] = $row;
		}
		
		if (!empty($khaosat)){
			$sql = "SELECT * FROM a_tbl_monan WHERE ";

			$condition = "";
			foreach ($khaosat as $k => $v) {
				$sql .= $condition.$v['sGhiChu']." LIKE '%".$v['sTieuChi']."%'";
				$condition = " OR ";
			}

			$query = mysqli_query($con, $sql);
			$arrHint = [];
			while($row = $query->fetch_assoc())
			{
				$arrHint[] = $row;
			}

			$arrWhere = "";
			foreach ($arrHint as $k => $v) {
				$arrWhere .= $v['PK_iMaMon'].",";
			}

			$sql = "SELECT * FROM a_tbl_monan WHERE PK_iMaMon NOT IN (".rtrim($arrWhere, ",").")";

			$query = mysqli_query($con, $sql);
			$arrNoHint = [];
			while($row = $query->fetch_assoc())
			{
				$arrNoHint[] = $row;
			}

			$arr = array_merge($arrHint, $arrNoHint);
		}
		else{
			$sql = "SELECT * FROM a_tbl_monan";

			$query = mysqli_query($con, $sql);
			$arr = [];
			while($row = $query->fetch_assoc())
			{
				$arr[] = $row;
			}
		}
		
		echo json_encode(["data" => $arr]);
		exit();
	}

	function getFoodByIngredients()
	{
		$ingredient = $_POST['hintFood'];
		$con = connect();
		// $sql = "SELECT * FROM a_tbl_monan WHERE sCongThuc LIKE '%".$ingredient."%' OR sNguyenLieu LIKE '%".$ingredient."%'";
		$sql = "SELECT * FROM a_tbl_monan WHERE sNguyenLieu LIKE '%".$ingredient."%'";
		
		$query = mysqli_query($con, $sql);
		$arr = [];

		while($row = $query->fetch_assoc())
		{
			$arr[] = $row;
		}

		echo json_encode(["data" => $arr]);
		exit();
	}

	function getFoodById()
	{
		$id = $_POST['idFood'];
		$con = connect();
		$sql = "SELECT * FROM a_tbl_monan WHERE PK_iMaMon = ".$id;
		
		$query = mysqli_query($con, $sql);
		$arr = [];

		while($row = $query->fetch_assoc())
		{
			$arr = $row;
			break;
		}

		echo json_encode($arr);
		exit();
	}

	function getFoodInDay()
	{
		$ngay = $_POST['ngay'];
		$userId = $_POST['maUser'];
		$con = connect();

		$sqlGetUserInGroup = "SELECT PK_iMaUser FROM `a_tbl_users` WHERE `sNhom` LIKE (SELECT `sNhom` FROM a_tbl_users WHERE `PK_iMaUser` = 1)";

		$sql = "SELECT * FROM a_tbl_monan
				INNER JOIN a_tbl_thucdon_tuan ON a_tbl_monan.PK_iMaMon = a_tbl_thucdon_tuan.FK_iMaMon
				WHERE sNgayThucHien LIKE '$ngay' AND FK_iMaUser IN ($sqlGetUserInGroup)";

		$query = mysqli_query($con, $sql);
		$arr = [];
		while($row = $query->fetch_assoc())
		{
			$arr[] = $row;
		}

		$arrConvert['sang'] = [];
		$arrConvert['trua'] = [];
		$arrConvert['toi'] = [];
		foreach ($arr as $k => $v) {
			$arrConvert[$v['sBuoi']][] = $v;
		}

		echo json_encode($arrConvert);
		exit();
	}

	function notificationFood($trangthai, $userId, $foodId, $buoi, $ngay)
	{
		$con = connect();
		$sql = "SELECT * FROM a_tbl_users WHERE PK_iMaUser = $userId";
		$query = mysqli_query($con, $sql);
		while($row = $query->fetch_assoc())
		{
			$user = $row;
			break;
		}

		$sql = "SELECT * FROM a_tbl_monan WHERE PK_iMaMon = $foodId";
		$query = mysqli_query($con, $sql);
		while($row = $query->fetch_assoc())
		{
			$food = $row;
			break;
		}

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
        $tenmon = $food['sTenMon'];
        switch ($trangthai) {
        	case 'xoá':
        		$action = 'khỏi';
        		break;
        	default:
        		$action = 'vào';
        		break;
        }
        $message = "Đã $trangthai món $tenmon $action thực đơn buổi $buoi ngày $ngay";

        $push->setTitle($title);
        $push->setMessage($message);

        $push->setImage('');
        $push->setIsBackground(FALSE);
        $push->setPayload($payload);

        $json = '';
        $response = '';

        $sql = "SELECT * FROM a_tbl_users
        		WHERE sNhom IN (SELECT sNhom FROM a_tbl_users WHERE PK_iMaUser = $userId)";
		$query = mysqli_query($con, $sql);

		$listUserNhom = [];
		while($row = $query->fetch_assoc())
		{
			$listUserNhom[] = $row;
		}

        $regId = [];
        foreach ($listUserNhom as $key => $value) {
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

	function saveFoodInDay()
	{
		$buoi = $_POST['buoi'];
		$ngay = $_POST['ngay'];
		$foodId = $_POST['maMon'];
		$userId = $_POST['maUser'];
		$con = connect();

		$sql = "INSERT INTO `a_tbl_thucdon_tuan`(`sNgayThucHien`, `sBuoi`, `FK_iMaMon`, `FK_iMaUser`) VALUES ('$ngay', '$buoi', $foodId, $userId)";

		$query = mysqli_query($con, $sql);
		if (mysqli_affected_rows($con) != 0)
        {
            switch ($buoi) {
            	case 'trua':
            		$convertBuoi = 'trưa';
            		break;
            	case 'toi':
            		$convertBuoi = 'tối';
            		break;
            	default:
            		$convertBuoi = 'sáng';
            		break;
            }
            $res = notificationFood("thêm", $userId, $foodId, $convertBuoi, $ngay);
            echo json_encode(["result" => mysqli_insert_id($con), $res]);
        }
        else{
        	echo json_encode(["result" => "Có lỗi xảy ra. Vui lòng thử lại!"]);
        }
        exit();
	}

	function deleteFoodInDay()
	{
		$idTD = $_POST['idTD'];
		$con = connect();

		$sql = "SELECT * FROM a_tbl_thucdon_tuan WHERE PK_iMaTD = $idTD";
		$query = mysqli_query($con, $sql);
		while($row = $query->fetch_assoc())
		{
			$thucdon = $row;
			break;
		}

		$sql = "DELETE FROM a_tbl_thucdon_tuan WHERE PK_iMaTD = ".$idTD;
		$query = mysqli_query($con, $sql);
		if (mysqli_affected_rows($con) != 0)
        {
        	switch ($thucdon['sBuoi']) {
            	case 'trua':
            		$convertBuoi = 'trưa';
            		break;
            	case 'toi':
            		$convertBuoi = 'tối';
            		break;
            	default:
            		$convertBuoi = 'sáng';
            		break;
            }
            $res = notificationFood("xoá", $thucdon['FK_iMaUser'], $thucdon['FK_iMaMon'], $convertBuoi, $thucdon['sNgayThucHien']);
            echo json_encode(["result" => "Gỡ món khỏi thực đơn thành công"]);
        }
        else{
        	echo json_encode(["result" => "Có lỗi xảy ra. Vui lòng thử lại!"]);
        }
        exit();
	}

	if ($_SERVER['REQUEST_METHOD'] === 'POST') {
		$action = $_POST['action'];

		switch ($action) {
			case 'getFoodHint':
				getFoodHint();
				break;
			case 'getFoodByIngredients':
				getFoodByIngredients();
				break;
			case 'getFoodById':
				getFoodById();
				break;
			case 'getFoodInDay':
				getFoodInDay();
				break;
			case 'saveFoodInDay':
				saveFoodInDay();
				break;
			case 'deleteFoodInDay':
				deleteFoodInDay();
				break;
		}
	}
?>
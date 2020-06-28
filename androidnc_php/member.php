<?php
	date_default_timezone_set('Asia/Ho_Chi_Minh');

	function connect()
	{
		return mysqli_connect("localhost","ffcteam","nhansutpt","ffcteam");
	}

	function getSearchUsers()
	{
		$search = $_POST['search'];
		$user = $_POST['currentUser'];
		$con = connect();
		$sql = "SELECT * FROM a_tbl_users
				INNER JOIN a_tbl_taikhoan ON a_tbl_taikhoan.FK_iMaUser = a_tbl_users.PK_iMaUser
				WHERE PK_iMaUser != ".$user." AND (sNhom NOT IN (SELECT sNhom FROM a_tbl_users WHERE PK_iMaUser = ".$user.") OR sNhom = '')";
		if (!empty($search)){
			$sql .= " AND sTenHienThi LIKE '%".$search."%'";
		}
		$query = mysqli_query($con, $sql);
		$arr = [];

		while($row = $query->fetch_assoc())
		{
			$arr[] = $row;
		}
		echo json_encode(["data" => $arr]);
		exit();
	}

	function getGroup()
	{
		$nhom = $_POST['nhom'];
		$user = $_POST['currentUser'];
		$con = connect();
		if (!empty($nhom)){
			$sql = "SELECT * FROM a_tbl_users
					INNER JOIN a_tbl_taikhoan ON a_tbl_users.PK_iMaUser = a_tbl_taikhoan.FK_iMaUser
					WHERE sNhom LIKE '" .$nhom. "' AND PK_iMaUser != ".$user;

			$query = mysqli_query($con, $sql);
			$arr = [];
			while($row = $query->fetch_assoc())
			{
				$arr[] = $row;
			}
			if (!empty($arr)){
				echo json_encode(["data" => $arr]);
			}
		}
		exit();
	}

	function addGroup()
	{
		$userId = $_POST['currentId'];
		$addId = $_POST['addId'];
		$con = connect();
		// print_r('expression'); exit();

		$sql = "SELECT sNhom FROM a_tbl_users WHERE PK_iMaUser = ".$userId;
		$query = mysqli_query($con, $sql);

		while($row = $query->fetch_assoc())
		{
			$arr = $row;
			break;
		}

		if (!empty($arr['sNhom'])){
			$nhom = $arr['sNhom'];
		}
		else{
			$nhom = '(m.max_number + 1)';
		}

		$sql = "UPDATE a_tbl_users as u
				CROSS JOIN (SELECT MAX(sNhom) AS max_number FROM a_tbl_users) AS m
				SET sNhom=".$nhom." WHERE PK_iMaUser = ".$addId;

		if (empty($arr['sNhom'])){
			$sql .= " OR PK_iMaUser = ".$userId;
		}
		$query = mysqli_query($con, $sql);

		if (mysqli_affected_rows($con) != 0){
            echo json_encode(["result" => "Thêm thành viên thành công"]);
        }
        else{
        	echo json_encode(["result" => "Có lỗi xảy ra. Vui lòng thử lại!"]);
        }
        exit();
	}

	function deleteGroup()
	{
		$userId = $_POST['userId'];
		$con = connect();

		$sql = "UPDATE a_tbl_users SET sNhom = '' WHERE PK_iMaUser = ".$userId;

		$query = mysqli_query($con, $sql);

		if (mysqli_affected_rows($con) != 0){
            echo json_encode(["result" => "Xoá thành viên thành công"]);
        }
        else{
        	echo json_encode(["result" => "Có lỗi xảy ra. Vui lòng thử lại!"]);
        }
        exit();
	}

	function getMyInfo(){
		$userId = $_POST['_id'];
		$con = connect();

		$sql = "SELECT *
				FROM a_tbl_users 
				INNER JOIN a_tbl_taikhoan ON a_tbl_users.PK_iMaUser = a_tbl_taikhoan.FK_iMaUser
				WHERE PK_iMaUser = ".$userId;

		$query = mysqli_query($con, $sql);
		$arr = [];
		while($row = $query->fetch_assoc())
		{
			$arr = $row;
			break;
		}

		if (!empty($arr)){
			echo json_encode($arr);
		}

		exit();
	}

	function update_MyInfo(){
		$userId = $_POST['userId'];
		$hoten = $_POST['hoten'];
		$ngaysinh = $_POST['ngaysinh'];
		$gioitinh = $_POST['gioitinh'];
		$image = $_POST['image'];
		$con = connect();

		if (!empty($image)){
			$imgName = time().'.jpg';
			$path = 'image/'.$imgName;
			$sLinkAnh = 'http://ffcteam.com/androidnc/'.$path;
		}
		else{
			$sLinkAnh = '';
		}

		$sql = "UPDATE a_tbl_users
				SET sTenHienThi =  '$hoten', sGioiTinh = '$gioitinh', sNgaySinh = '$ngaysinh'
				WHERE  PK_iMaUser = ".$userId;
				
		$sql2 = "UPDATE a_tbl_taikhoan SET sAvatar='".$sLinkAnh."' WHERE FK_iMaUser = ".$userId;

		$query = mysqli_query($con, $sql);
		$query = mysqli_query($con, $sql2);

		if (mysqli_affected_rows($con) != 0){
			if (!empty($image)){
				file_put_contents($path, base64_decode($image));
			}
            echo json_encode(["result" => "Cập nhật thông tin thành công"]);
        }
        else{
        	echo json_encode(["result" => "Cập nhật thông tin không thành công!"]);
        }
        exit();
	}

	if ($_SERVER['REQUEST_METHOD'] === 'POST') {
		$action = $_POST['action'];

		switch ($action) {
			case 'getSearchUsers':
				getSearchUsers();
				break;
			case 'getGroup':
				getGroup();
				break;
			case 'addGroup':
				addGroup();
				break;
			case 'delGroup':
				deleteGroup();
				break;
			case 'getMyInfo':
				getMyInfo();
				break;
			case 'update_MyInfo':
				update_MyInfo();
				break;
		}
	}
?>
-- phpMyAdmin SQL Dump
-- version 4.0.10.20
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 06, 2019 at 11:55 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 5.6.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ffcteam`
--

-- --------------------------------------------------------

--
-- Table structure for table `a_dm_khaosat`
--

CREATE TABLE IF NOT EXISTS `a_dm_khaosat` (
  `PK_iMaKS` int(11) NOT NULL AUTO_INCREMENT,
  `sTieuChi` varchar(255) CHARACTER SET utf8 NOT NULL,
  `sGhiChu` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sAnh` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`PK_iMaKS`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `a_dm_khaosat`
--

INSERT INTO `a_dm_khaosat` (`PK_iMaKS`, `sTieuChi`, `sGhiChu`, `sAnh`) VALUES
(1, 'Thịt bò', 'sNguyenLieu', 'http://ffcteam.com/androidnc/image/thit_bo.jpg'),
(2, 'Thịt gà', 'sNguyenLieu', 'http://ffcteam.com/androidnc/image/thit_ga.jpg'),
(3, 'Rau', 'sNguyenLieu', 'http://ffcteam.com/androidnc/image/rau.jpg'),
(4, 'Nướng', 'sCongThuc', 'http://ffcteam.com/androidnc/image/nuong.jpg'),
(5, 'Luộc', 'sCongThuc', 'http://ffcteam.com/androidnc/image/luoc.jpg'),
(6, 'Việt', 'sXuatXu', 'http://ffcteam.com/androidnc/image/viet_nam.png'),
(7, 'Hàn', 'sXuatXu', 'http://ffcteam.com/androidnc/image/han_quoc.jpg'),
(8, 'Ý', 'sXuatXu', 'http://ffcteam.com/androidnc/image/italia.png');

-- --------------------------------------------------------

--
-- Table structure for table `a_tbl_binhluan`
--

CREATE TABLE IF NOT EXISTS `a_tbl_binhluan` (
  `PK_iMaBL` int(11) NOT NULL AUTO_INCREMENT,
  `sNoiDung` text NOT NULL,
  `sThoiGian` varchar(255) NOT NULL,
  `sLinkAnh` varchar(255) DEFAULT NULL,
  `FK_iMaUser` int(11) NOT NULL,
  `FK_iMaMon` int(11) NOT NULL,
  PRIMARY KEY (`PK_iMaBL`),
  KEY `FK_iMaUser` (`FK_iMaUser`),
  KEY `FK_iMaMon` (`FK_iMaMon`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=79 ;

--
-- Dumping data for table `a_tbl_binhluan`
--

INSERT INTO `a_tbl_binhluan` (`PK_iMaBL`, `sNoiDung`, `sThoiGian`, `sLinkAnh`, `FK_iMaUser`, `FK_iMaMon`) VALUES
(1, 'Món này ngon', '2019-07-09 10:21:21', 'http://ffcteam.com/androidnc/image/1562641254.jpg', 1, 2),
(4, 'Món này làm đơn giản mà ngon !', '2019-07-09 04:11:09', 'http://ffcteam.com/androidnc/image/1562662242.jpg', 7, 13),
(6, 'mình thích món này', '2019-07-10 04:37:02', '', 1, 14),
(7, 'test', '2019-07-10 04:37:32', '', 1, 14),
(8, 'nhưng ko thích ăn', '2019-07-10 04:43:05', '', 1, 13),
(9, 'testtt', '2019-07-10 04:43:23', '', 1, 13),
(10, 'món này cũng ko thích', '2019-07-10 04:44:35', '', 1, 11),
(11, 'ngon', '2019-07-10 04:44:45', '', 1, 11),
(12, 'hoho', '2019-07-10 04:45:23', 'http://ffcteam.com/androidnc/image/1562750692.jpg', 1, 11),
(13, 'không duyệt', '2019-07-10 04:48:57', 'http://ffcteam.com/androidnc/image/1562750905.jpg', 1, 11),
(14, 'mình cũng thích món này', '2019-07-11 04:26:05', '', 7, 14),
(15, 'good', '2019-07-12 01:23:54', '', 7, 13),
(44, 'like', '2019-07-12 05:10:47', '', 7, 2),
(45, 'great', '2019-07-12 05:26:54', '', 1, 13),
(46, 'abc', '2019-07-13 10:39:29', 'http://ffcteam.com/androidnc/image/1562987927.jpg', 3, 2),
(47, 'Không thích.', '2019-07-13 02:22:49', '', 3, 2),
(64, 'Mình rất thích món này.', '2019-07-14 04:19:37', '', 7, 17),
(65, 'Nhà mình đều thích món này', '2019-07-14 04:27:44', 'http://ffcteam.com/androidnc/image/1563095218.jpg', 7, 18),
(66, 'abc', '2019-07-14 04:29:53', '', 7, 18),
(67, 'def', '2019-07-14 04:32:11', '', 1, 18),
(68, 'test comment', '2019-07-14 04:33:25', '', 7, 18),
(69, 'qqw', '2019-07-14 04:33:28', '', 1, 18),
(70, 'oh yeah', '2019-07-14 05:11:56', '', 3, 2),
(71, 'ok', '2019-07-14 05:15:54', '', 3, 2),
(72, 'test notice', '2019-07-14 05:28:57', '', 7, 18),
(74, 'Món này mình cũng thích', '2019-07-15 07:08:30', '', 7, 12),
(75, 'ok', '2019-07-15 07:11:59', '', 1, 13),
(76, 'cũng đc', '2019-07-15 07:14:26', '', 7, 13),
(77, 'okla', '2019-07-15 07:57:25', '', 1, 13),
(78, 'test', '2019-07-15 08:38:42', 'http://ffcteam.com/androidnc/image/1563153474.jpg', 1, 12);

-- --------------------------------------------------------

--
-- Table structure for table `a_tbl_monan`
--

CREATE TABLE IF NOT EXISTS `a_tbl_monan` (
  `PK_iMaMon` int(11) NOT NULL AUTO_INCREMENT,
  `sTenMon` varchar(255) NOT NULL,
  `sGioiThieu` text NOT NULL,
  `sCongThuc` text NOT NULL,
  `sNguyenLieu` text NOT NULL,
  `sLinkAnh` varchar(255) DEFAULT NULL,
  `fDanhGia` float DEFAULT NULL,
  `iSoDanhGia` int(11) NOT NULL,
  `sXuatXu` varchar(255) NOT NULL,
  `sLoai` varchar(255) NOT NULL,
  `sCheDo` varchar(255) NOT NULL,
  PRIMARY KEY (`PK_iMaMon`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `a_tbl_monan`
--

INSERT INTO `a_tbl_monan` (`PK_iMaMon`, `sTenMon`, `sGioiThieu`, `sCongThuc`, `sNguyenLieu`, `sLinkAnh`, `fDanhGia`, `iSoDanhGia`, `sXuatXu`, `sLoai`, `sCheDo`) VALUES
(1, 'Thịt kho tàu', 'Thịt kho tàu là món ăn quen thuộc của người Việt Nam, đặc biệt là người miền Bắc. Cách nấu thịt kho tàu của người miền Bắc và miền Nam chung quy lại cũng giống nhau, tuy nhiên người miền Nam ăn ngọt và cay nhiều hơn. Chính vì thế tùy theo khẩu vị của từng người mà điều chỉnh gia vị sao cho hợp lý.', '- Sơ chế nguyên liệu:\nThịt mua về cạo sạch lông, rửa lại nhiều lần, sau đó ngâm trong chậu nước có pha muối loãng khoảng 5 phút, sau đó vớt ra rửa với nước lã, để ráo.\n\nHành tỏi khô bóc vỏ, băm nhỏ.\n\nHành lá rửa sạch, xắt khúc.\n\nDừa bổ ra lấy nước, phần cùi dừa có thể thái từng lát mỏng cho vào 1 cái bát\n\n- Ướp thịt\nThịt sau khi rửa sạch thái từng miếng vừa ăn, không nên thái quá bé khi kho sẽ bị nát. Cho thịt vào 1 tô to, ướp cùng với 1 chút hành tỏi khô băm, 1 thìa mì chính, 2 thìa nước mắm, 1/2 thìa đường, 1 thìa mì chính, 2 thìa dầu ăn, 1 thìa hạt tiêu. Ướp thịt ít nhất trong vòng 30 phút để thịt được ngấm gia vị.\n\n- Luộc trứng\nTrong lúc đợi thịt ngấm gia vị, cho trứng gà hoặc trứng cút vào nồi, đổ nước xâm xấp rồi đậy vung, để lửa vừa, luộc chín. Cho 1 chút muối hạt, 1 thìa dấm gạo vào nồi luộc trứng để trứng nhanh chín, không bị vỡ và dễ bóc vỏ.\n\nTrứng chín thì vớt ra, bóc vỏ rồi cho vào 1 cái bát để riêng.\n\nNhiều người chiên qua trứng sau khi luộc vì sợ trứng vỡ nhưng làm vậy sẽ khiến lớp vỏ ngoài cùng bị dai, nổ lỗ chỗ trông không đẹp mắt\n\n- Thắng nước hàng để kho thịt\n\nNhiều người vẫn dùng nước hàng mua sẵn ngoài cửa hàng để kho thịt, kho cá. Tuy nhiên tự tay thắng nước hàng sẽ giúp cho món ăn thơm và ngon hơn.\n\nCách thắng nước hàng cũng rất đơn giản và nhanh gọn, không hề mất thời gian. Cho 3 thìa đường nâu vào chảo, 4 thìa nước, dùng đũa quấy đều. Cho chảo đường lên bếp, để lửa vừa, nấu đến khi đường sôi và chuyển sang màu nâu cánh gián, nước sánh thì tắt bếp, đổ ra 1 cái bát.\n\n- Nấu thịt kho tàu\nBắc nồi lên bếp, cho 2 thìa dầu ăn vào, vặn lửa vừa đun nóng già, phi thơm số hành tỏi băm còn lại.\n\nĐể món ăn đậm vị hơn, ở bước phi thơm hành tỏi này, cho thêm 1/2 thìa nước mắm ngon vào.\n\nTrút thịt đã ướp vào nồi, cùi dừa (nếu thích) đảo đều tay đến khi thịt săn lại thì cho 2 thìa nước hàng đã làm ở bước 3 vào đảo cùng.\n\nTiếp đến, đổ nước dừa tươi vào sao cho lượng nước xâm xấp mặt thịt là được. Lúc này, vặn lửa to để nồi thịt sôi bùng lên, sau đó vặn lửa bé để thịt kho được chín mềm.\n\nCho ra đĩa, rưới thêm nước kho thịt nâu óng sóng sánh lên trên để thịt càng bóng và đẹp mắt. Trang trí thêm chút hành lá xắt nhỏ, 1 đôi cọng mùi xanh rì và quả ớt tươi đỏ để tăng thêm tính thẩm mỹ cho món ăn ngon này.', '5 lạng thịt lợn\n3 củ hành khô\n1 củ tỏi\n1 quả dừa tươi\n10 quả trứng cút hoặc 3-4 quả trứng gà\nHành lá\nĐường nâu\nNước hàng kho thịt\nMuối hạt\nNước mắm, mì chính, hạt tiêu, dầu ăn', 'http://ffcteam.com/androidnc/image/thitkhotau.jpg', 2.5, 2, 'Việt Nam', 'Kho', ''),
(2, 'Bánh tráng nướng', 'Món bánh tráng nướng là món ăn vặt trên đường phố có nguồn gốc xuất xứ từ Đà Lạt. Hình thức ban đầu của bánh tráng nướng rất sơ khai, chỉ là đơn giản nướng bánh với mỡ hành, ăn kèm thêm tương ớt. Theo thời gian, để đáp ứng nhu cầu ngày càng cao của thực khách, người bán đã có những sáng tạo để món bánh nướng trở nên phong phú và đa dạng với nhiều loại nhân hấp dẫn như: xúc xích, gà xé, chà bông hay phô mai…', '- Cách sơ chế nguyên liệu\nHành lá lặt sạch, rửa sạch và đem xắt nhỏ. Tỏi bóc vỏ, rửa sạch, đem đập dập và cũng xắt nhỏ.\n\nTép nhặt sạch bụi, cát và các tạp chất còn lẫn vào. Sau đó rửa sạch và để ráo cho khô.\n\nLấy một cái tô, đập trứng gà và cho 1 ít hành lá đã xắt nhỏ vào, đánh đều tay cho trứng tan hoàn toàn.\n\nXúc xích đem cắt lát mỏng vừa ăn.\n\n- Cách làm bánh tráng nướng\nBật bếp, để lửa vừa, chờ cho chảo nóng già thì cho 1 muỗng bơ vào, để cho bơ tan, tráng đều mặt chảo và cho 1 muỗng cafe tỏi băm vào, phi thơm.\n\nTiếp theo, cho 1 muỗng tép khô đã rửa sạch vào xào. Đảo đều tay cho đến khi tép chín mềm và chuyển sang màu đỏ cam. Trút hết phần tép vày vào chén, để riêng.\n\nCho một cái chảo khô khác lên bếp. Khi chảo đã nóng già và đều, cho bánh tráng vào để nướng. Khi bạn thấy bánh tráng khô lại, rìa bánh cong cong lên thì đổ phần trứng gà đã đánh tan vào, trải đều khắp mặt bánh. Chú ý, bạn nên đổ từ từ để không cho trứng tràn ra khỏi bánh.\n\nKhi mặt trứng đã se lại, bánh tráng giòn đều thì bạn tiếp tục cho chà bông, khô bò xé sợi, phô mai sợi và xúc xích đã xắt lát mỏng lên trên mặt bánh. Cho xốt Mayonnaise và tương ớt lên trên cùng. Sau đó gập đôi bánh lại và cho ra đĩa. Cứ làm như thế cho đến khi hết nguyên liệu là có thể tắt bếp.', 'Trứng gà: 2 trái\nTép khô: 4 muỗng\nBánh tráng: 4 cái\nXúc xích ăn liền: 4 cây\nChà bông heo: 4 muỗng\nKhô bò: 4 muỗng\nPhô mai sợi: 4 muỗng cafe\nBơ mặn: 4 muỗng\nTỏi: 1 củ\nHành lá: 3 nhánh\nXốt Mayonnaise và tương ớt', 'http://ffcteam.com/androidnc/image/banhtrangnuong.jpg', NULL, 0, 'Việt Nam', 'Nướng', ''),
(3, 'Pizza', 'Bánh Pizza chắc chắn đã quá quen thuộc với nhiều bạn. Hương vị bánh hấp dẫn, đa dạng để người ăn có thể chọn theo sở thích chính là điểm cộng cho Pizza. Nếu bạn thích ăn hải sản thì pizza hải sản với mực, tôm sẽ chinh phục bạn; còn nếu thích các loại thịt nguội thì bánh pizza thịt nguội dành riêng cho bạn; nếu bạn đang theo chế độ ăn chay thì cũng có bánh pizza chay đấy!', 'Bước 1: Cho men nở, nước ấm và đường vào chung với nhau. Để khoảng 10 phút để men có thể hoạt động, nó sẽ sủi bọt nhỏ lên.\r\n\r\nBước 2: Bột, muối trộn chung với nhau. Rót hỗn hợp men, dầu ăn vào âu bột, dùng thìa gỗ trộn bột lên cho đều. Đặt bột ra mặt phẳng sạch có rắc bột áo, nhào đều bằng tay tới khi bột mịn và không dính tay. Để bột vào âu sạch có quét dầu ăn, bọc kín bằng màng nilon, ủ tới khi bột nở gấp đôi ban đầu.\r\n\r\nBước 3: Ớt chuông, nấm và xúc xích hun khói thái hạt lựu. Cho chút xíu dầu ăn vào chảo, đổ phần nguyên liệu trên vào xào sơ trong vài phút. Bật lò nướng ở 240 độ C trong 10 phút trước khi nướng.\r\n\r\nBước 4: Lấy bột đã ủ ra, dùng cây cán bột mỏng khoảng 0,5 cm để làm đế bánh pizza. Có thể chia đôi bột để làm hai bánh nhỏ.\r\n\r\nBước 5: Dùng cọ hoặc thìa để phết sốt cà chua lên đế bánh. Tiếp đó, rải ½ phô mai Mozzarella lên mặt bánh. Thêm ớt chuông, nấm và xúc xích lên.\r\n\r\nBước 7: Rắc phần phô mai Mozzarella còn lại lên trên cùng, rồi đem nướng trong 10 phút. Bạn thấy phô mai tan chảy đều trên mặt bánh và viền bánh nâu vàng đẹp mắt là được. Lấy bánh ra, dùng dao cắt bánh thành từng miếng hình tam giác để thưởng thức, chấm với tương ớt, tương cà.', 'Phần vỏ\n- 100 gram bột mì\n- 50 ml nước ấm khoảng 40 độ C\n- 2 gram men nở khô\n- 2 gram muối, 2 gram đường, 3 gram dầu ăn\n\nPhần nhân\n- 1 quả ớt chuông (khoảng 50 gram)\n- 10 cây nấm nhỏ\n- 200 gram phô mai Mozzarella\n- 50 gram xúc xích hun khói\n- Sốt cà chua', 'http://ffcteam.com/androidnc/image/pizza.jpg', 4, 2, 'Ý', '', ''),
(11, 'Rau muống trộn tép sấy', 'Món rau trộn ngẩu hứng nhưng ăn rất ngon.', 'Bước 1: Rau muống rửa sạch vẩy ráo nước, mỡ heo cắt nhỏ rán gần vàng thì cho hành tím cắt mỏng vô rán tiếp cho hành vàng là được. Pha 2 muỗng canh nước mắm + 2 muỗng canh đường khuấy tan thêm chanh tùy ý và 1 muỗng canh tỏi ớt băm.\r\nBước 2: Cho rau muống vô thau đổ nước mắm vô trộn đều. Cho rau trộn ra dĩa đổ mở hành lên.', '300 g rau muống bào\r\n30 g tép sấy\r\n150 g mỡ heo\r\n2 muỗng canh nước mắm\r\n2 muỗng canh đường\r\nHành tím, tỏi, ớt, chanh', 'http://ffcteam.com/androidnc/image/raumuong.jpg', NULL, 0, 'Việt Nam', 'Xào', ''),
(12, 'Thịt bò xào rau muống', '', '- Bước 1:\r\nRau muống vặt bỏ lá. Thịt bò thái lát ướp với 1 thìa cafe muối, bột ngọt, đường trong 10 phút.\r\n- Bước 2: \r\nCho dầu ăn lên chảo, phi thơm tỏi, cho thịt bò vô đảo cho hơi tái, đổ ra đĩa.\r\n- Bước 3:\r\nCho rau muống vô xào, khi rau tái lại cho thịt bò vô, nêm nếm lại. Sắp bắc ra cho tỏi bằm vô đảo đều.', '3 lạng thịt bò\r\n1 bó rau muống\r\n2 củ tỏi\r\nĐường- muối- bột ngọt', 'http://ffcteam.com/androidnc/image/thitboxaorau.jpg', 3, 1, 'Việt Nam', '', ''),
(13, 'Đậu phụ rán tẩm hành', 'Đậu phụ rán tẩm hành là một món ăn vô cùng đơn giản, dễ làm từ khâu mua nguyên liệu đến cách chế biến. Hơn nữa món ăn này lại rất ngon miệng và đưa cơm nữa đấy. Thay vì đậu rán, đậu sốt cà chua các bạn hãy thử làm món này đổi món cho cả nhà xem sao, đảm bảo ai cũng phải thích cho mà xem.', '- Đậu phụ rửa qua nước cho sạch bụi, sau đó cắt miếng vừa ăn, đặt lên khăn thấm nước để khi rán đậu không bị bắn dầu.\n- Hành lá nhặt và rửa sạch, để ráo nước rồi cắt bỏ phần đầu trắng, thái nhỏ.\n- Cho nước mắm ra bát và cho hành lá thái nhỏ vào, khuấy đều. Nếu không thích ăn mặn quá, có thể pha nước mắm và nước lọc theo tỷ lệ 1:2. Ngoài ra cũng có thể pha nước mắm, đường, nước lọc theo tỷ lệ 1:1:2. Tùy khẩu vị gia đình để điều chỉnh cho phù hợp.\n- Cho dầu vào chảo, đun nóng già dầu thì cho đậu phụ vào rán vàng đều các mặt. Khi đậu đã vàng, gắp đậu ra và nhúng vào bát nước mắm đã chuẩn bị sẵn.\n- Lần lượt nhúng các miếng đậu đã chín vàng vào nước mắm, rồi cho ra đĩa để thưởng thức.', '- Đậu phụ khoảng 3 bìa lớn\r\n\r\n- Hành lá 4-5 nhánh\r\n\r\n- Dầu ăn\r\n\r\n- Nước mắm ngon\r\n\r\n- Đường', 'http://ffcteam.com/androidnc/image/dau-phu-tam-hanh.jpg', 3.2, 5, 'Việt Nam', 'Rán', ''),
(14, 'Chân Gà Nướng Sả', 'Chân Gà Nướng Sả là món ăn luôn được các chàng và các nàng ưa chuộng vì độ ngon và không gây ngấy, có thể ăn lai rai rất tuyệt. Tuy nhiên, vấn đề vệ sinh thực phẩm là vấn đề đáng lo ngại khi ăn món Chân Gà Nướng ở các hàng ăn bên ngoài. Barona chia sẻ với nàng cách làm Chân gà nướng sả cực đơn giản, ăn hoài không ngán nhé. Chỉ cần chân gà và gói Xốt Gia Vị Hoàn Chỉnh Thịt Nướng Sả Barona là nàng hoàn toàn có thể tự tin trổ tài thực hiện món ăn này rồi đấy, vừa siêu ngon lại vừa an tâm về vệ sinh an toàn thực phẩm.', '- Bước 1: Hấp chân gà với gừng, sả cắt khúc trong 10 phút, sau đó rửa lại với nước lạnh. \r\n- Bước 2: Sau đó, ướp chân gà với 1 gói Xốt Gia Vị Hoàn Chỉnh Thịt Nướng Sả Barona, tỏi, sả, ớt băm, 2 muỗng canh dầu ăn, ướp trong thời gian 120 phút. \r\n- Bước 3: Sau đó cho vào khay và nướng bằng bếp nướng trong 20 phút ở nhiệt độ 170 độ C. Sau khi nướng được 10 phút thì lấy khay nướng ra, dùng cọ quết đều xốt ướp vào chân gà, sau đó đặt vào bếp nướng nướng tiếp. Nếu không có bếp nướng có thể dùng nướng than để thay thế. Hoàn thành món chân gà nướng có thể ăn cùng với rau răm và chấm muối ớt chanh. ', '500g chân gà (khoảng 3 cặp)\r\n30g gừng , 40g sả, 10g tỏi\r\nXốt Gia Vị Hoàn Chỉnh Thịt Nướng Sả Barona 80g\r\nGừng cắt lát, 30g sả cắt khúc, 10g sả thái khoanh tròn mỏng \r\nChân gà rửa sạch ', 'http://ffcteam.com/androidnc/image/changa.jpg', NULL, 0, 'Việt Nam', '', ''),
(15, 'Vịt kho măng', 'Món ngon mỗi ngày đậm đà thấm vị vào những miếng thịt vịt ngọt ngon béo ngậy hòa cùng chút thanh đạm của măng tươi như thấm phần chất béo vào trong măng làm cho người dùng cảm giác thật thú vị khi thưởng thức.', 'Bước 1: Cho thịt vịt vào nồi cùng nước xâm xấp, vài lát gừng và 1 muỗng muối lên bếp nấu sôi cho đến khi nổi bọt thì vớt thịt vịt ra đĩa\r\nBước 2: Ướp vịt với 1 gói GVHC Cá/thịt kho 80g trong 20 phút.\r\nBước 3: Măng cho vào nồi luộc chín cho ra hết đắng.\r\nBước 4: Bắc chảo lên bếp cho 1 muỗng canh dầu ăn vào đun nóng sau đó cho ít tỏi băm vào phi thơm.\r\nBước 5: Cho thịt vịt vào xào săn vàng. Thêm 1 ít gừng, Cho nước vào sâm sấp mặt thịt nấu khoảng 10 phút.\r\nBước 6: Cho măng vào kho thêm 10 phút cho cạn nước thì tắt bếp. Trình bày và thưởng thức Múc thịt vịt kho măng tươi ra tô. Rắc lên ít hành hoa xé sợi. ', '- 500g thịt vịt.\r\n- 250 g măng tươi, củ gừng, ớt.\r\n- Gói GVHC Cá/thịt kho 80g\r\n- Thịt vịt mua về rửa sạch, chặt miếng vừa ăn.\r\n- Gừng xắt sợi hoặc lát\r\n- Măng cắt miếng vừa ăn', 'http://ffcteam.com/androidnc/image/vit-kho-mang.jpg', 4, 1, 'Việt Nam', '', ''),
(16, 'Canh rau ngót nấu mọc', 'Có lẽ các bạn thường quen thuộc với món canh rau ngót thịt băm hay canh rau ngót nấu sườn…Hôm nay, Barona sẽ hướng dẫn các bạn cách nấu canh rau ngót với mọc lạ miệng cho bữa cơm ngày cuối tuần thêm hấp dẫn nhé.', 'Bước 1: Đun một nồi nước sôi khoảng 400ml nêm nếm gia vị, khi nước sôi thả mọc vào.\r\nBước 2: Đợi khi mọc nổi lên các bạn đổ rau vào đun thêm 10 phút là hoàn thành xong cách nấu canh rau ngót với mọc. Múc ra bát và thưởng thức.', 'Rau ngót: 1 bó\r\nGiò sống: 50g\r\nTai nấm mèo\r\nGia vị: Muối, đường, hạt nêm, nước mắm, tiêu\r\nHành lá\r\nRau ngót tuốt lấy phần lá, bỏ những lá vàng úa. Sau đó vò và rửa sạch để giảm vị chát của rau. \r\n\r\n- Hành lá thái nhỏ.\r\n\r\n- Tai nấm mèo ngâm với nước ấm cho nở đều rồi rửa sạch và thái nhỏ.\r\n\r\n- Trộn giò sống với nấm mèo, hành tươi và cho thêm nước mắm, bột ngọt và chút hạt tiêu trộn đều.\r\n\r\n- Thoa một chút dầu ăn vào lòng bàn tay rồi nặn thành từng viên nhỏ vừa ăn, nặn cho tới khi hết phần giò sống.', 'http://ffcteam.com/androidnc/image/canh-rau-ngot.jpg', NULL, 0, 'Việt Nam', '', ''),
(17, 'Khô gà lá chanh', 'Món khô gà ăn vặt nức tiếng được lòng rất nhiều người có vị cay cay, đậm đậm thơm mùi lá chanh ăn một lần sẽ mê tít. Xem ngay cách làm khô gà xé cay đơn giản dưới đây, vừa ngon sạch, làm quà tặng cũng được luôn nhé.', 'Bước 1: Sơ chế gà\r\n- Sả bóc vỏ đập dập cắt khúc ngắn xé sợi, hành tây bóc vỏ già thái hình miếng cau, lá chanh thái làm 4 phần bằng nhau. \r\n- Ức gà xát muối, rửa sạch rồi cắt thành vuông khoảng 10cm cho vào nồi, cho một nửa phần sả đập dập, gừng vào luộc sôi nhỏ lửa trong 15 phút. \r\n- Gà luộc chín xong vớt ra để nguội, xé thành sợi vừa đều nhau. Không xé nhỏ, mỏng quá khi sấy hoặc rang khô sẽ dễ bị cháy hoặc giòn quá mất ngon.\r\nBước 2: Làm khô gà lá chanh\r\n- Cho 300ml nước lọc vào tô, cho muối, đường, nước mắm, bột nghệ, bột ớt, dầu điều vào rồi dùng đũa khuấy đến khi đường và muối tan hết thành hỗn hợp màu vàng đẹp mắt. Sau đó tưới đều hỗn hợp mới pha vào gà, dùng đũa đảo đều, để ngấm trong 30 phút. \r\n- (Pha hỗn hợp nước để ướp gà sẽ đều hơn so với trộn trực tiếp dễ bị miếng mặn miếng nhạt không đều nhau)\r\nBước 3: Sao khô gà bằng chảo: \r\n- Đun nóng chảo, cho gà, sả, hành tây vào đảo đều liên tục đến khi sợi gà khô lại. Quá trình đảo cho lửa nhỏ, không để lửa to gà sẽ bị cháy khô. \r\n- Khô gà xé sợi ăn thịt thơm mùi lá chanh, sả, vị cay, chua ngọt ăn rất ngon. Sấy khô gà xong bạn cho ra khay để nguội rồi đóng hộp kín bảo quản trong tủ lạnh để dùng dần nếu ăn không hết.\r\n- Ngoài cách sao khô bằng chảo, bạn có thể dùng lò vi sóng với tầm nhiệt từ 130 đến 140 độ trong 5 đến 7 phút rồi lấy ra dùng đũa đảo đều rồi lại cho vào sấy tầm nhiệt 150 độ trong 5 phút rồi lấy ra để nguội rồi thưởng thức.', '- Lườn gà 1,5kg\r\n\r\n- Sả, gừng, lá chanh, hành tây, tỏi\r\n\r\n- Gia vị: Muối, nước mắm ngon, đường, bột nghệ, bột ớt, dầu điều', 'http://ffcteam.com/androidnc/image/1561713776-816-thumbnail.jpg', NULL, 0, 'Việt Nam', 'Đồ khô', ''),
(18, 'Chân gà ngâm sả ướt chua ngọt', 'Thử ngay cách làm chân gà ngâm sả ớt với bí quyết hay đảm bảo ngon hơn hàng quán. Chân gà giòn mềm ăn vị chua ngọt, cay cay với mùi thơm từ chanh tỏi. Thật tiếc nếu bạn không thử làm qua một lần.', 'Bước 1: Làm sạch chân gà và nguyên liệu:\r\nChân gà rửa sạch, chặt làm đôi, xát muối rồi chần qua nước sôi một lần để khử hôi.\r\n\r\nQuất thái miếng mỏng, 1 quả thái làm 3, bỏ miếng đầu và cuối, bỏ hạt. Sả đập dập cắt khúc, lá chanh thái sợi, ớt băm nhỏ, gừng thái lát.\r\n\r\nTiếp đó cho vài lát sả, gừng vào lót dưới đáy nồi rồi cho chân gà vào, đổ nước ngập mặt rồi đun sôi vặn nhỏ lửa trong 3 phút để gà chín mềm. Vớt chân gà ra cho ngay vào thau nước đá lạnh, ngâm trong 20 phút sẽ làm chân gà trắng hơn, ăn giòn hơn. Ngâm xong vớt ra để ráo nước.\r\nBước 2: Làm nước ngâm chân gà sả ớt:\r\nBắc nồi lên bếp, cho 1,5 lít nước vào đun sôi sau cho 5 muỗng canh đường vào khuấy tan đến khi tan hết thì cho tiếp 5 muỗng canh giấm, 4 muỗng nước mắm, 2 thìa cà phê muối khuấy đều tất cả hỗn hợp rồi tắt bếp, để nguội hẳn. Nêm nếm gia vị sao cho vừa ăn.\r\nBước 3: Cách ngâm chân gà sả ớt đúng vị:\r\n- Hộp dùng để ngâm đảm bảo khô ráo, không dính nước lã. Xếp một lượt sả ở lớp đáy hộp, tiếp đến là quất, ớt, gừng rồi đến chân gà, lớp trên cùng sẽ là sả và quất. Cứ thế xếp lần lượt các lớp đến khi nào đầy hộp thì thôi. \r\n- Xếp xong, đổ hỗn hợp nước ngâm đã làm vào đảm bảo chân gà chìm hẳn trong nước, nếu nước không ngập chân gà dễ bị hỏng, nổi váng. Sau đó đậy nắp kín để vào tủ lạnh, ngày hôm sau là có thể mang ra ăn được rồi.', '- Chân gà công nghiệp: 25 chiếc\r\n\r\n- Sả, quất, ớt, lá chanh, gừng\r\n\r\n- Đường, giấm gạo, nước mắm nguyên chất, muối\r\n\r\n- Hộp (khay) đựng.', 'http://ffcteam.com/androidnc/image/changasauot.jpg', 2, 1, '', '', ''),
(19, 'Salad trộn', 'Nếu như bạn đã chán ngấy với các món ăn nhiều đạm, dầu mỡ và cần sự thanh lọc, “refresh” cho cơ thể thì các món salad thực sự là lựa chọn tuyệt vời. Rất nhiều cách làm salad tùy theo khẩu vị của bạn và người thân. Bạn có thể biến tấu theo nhiều cách khác nhau để cảm nhận sự đa dạng ẩm thực.', 'Bước 1: Sơ chế nguyên liệu\r\n- Xà lách:\r\nChọn những cây xanh non và khá lớn nhưng không già. Xà lách Đà Lạt được nhiều người chọn lựa.\r\nTiến hành cắt phần rễ, bỏ lá úa và rửa sạch nhiều lần nhưng không nên để dập nát. Bạn có thể ngâm với nước muối pha loãng 10-15 phút để khử khuẩn sau đó vớt ra để vắt ráo nước.\r\n- Dưa chuột:\r\nCắt đi 2 phần đầu một đoạn nhỏ, ngâm với muối để ra hết nhựa. Sau đó rửa lại với nước sạch rồi để ráo.\r\nCó thể gọt hoặc không tùy theo sở thích của bạn. Cắt thành những lát mỏng và nên có cả vỏ để khi trộn thì cà chua sẽ được giòn hơn.\r\n- Cà chua:\r\nRửa sạch với loại cà chua bi. Loại bình thường bạn có thể bổ 4 sau khi ráo nước.\r\n- Rau mùi:\r\nCắt rễ, nhặt lá úa rồi rửa sạch để ráo. Cắt thành khúc vừa ăn.\r\nBước 2: Làm nước sốt và trộn salad\r\n- 3 muỗng giấm, 2 muỗng đường hòa tan đường rồi tiếp tục cho thêm 1 muỗng nước lọc, cùng 1 muỗng dầu mè, một chút muối và một chút tiêu xay nhuyễn.\r\n- Khuấy đều cho các gia vị được tan đều. Nước sốt giấm trộn salad đã hoàn thành.\r\n- Cho rau xà lách, cà chua, dưa leo cùng rau mùi vào bát tô lớn. Đổ sốt vừa trộn vào rồi dùng đũa trộn đều các nguyên liệu với nhau, để khoảng 10 phút cho ngấm đều nước sốt.\r\nBước 3: Trang trí và thưởng thức\r\n- Xếp salad ra đĩa, cuối cùng là rưới sốt mayonnaise lên trên đĩa rau.\r\n- Trộn đều tất cả nguyên liệu rồi thưởng thức được ngay. Bạn sẽ cảm nhận được vị thanh mát của rau, thơm của giấm cùng vị béo của mayonnaise.', '- 4 cây rau xà lách\r\n\r\n- 2 quả dưa chuột\r\n\r\n- Cà chua\r\n\r\n- Dầu mè\r\n\r\n- Rau mùi\r\n\r\n- Đường\r\n\r\n- Muối\r\n\r\n- Giấm\r\n\r\n- Sốt mayonnaise', 'http://ffcteam.com/androidnc/image/salad.jpg', NULL, 0, '', '', ''),
(20, 'Chả lá lốt', 'Cách làm chả lá lốt đơn giản với nguyên liệu dễ kiếm, thực hiện trong thời gian ngắn là bạn đã có món ngon chiêu đãi cả gia đình. Chả vàng ruộm, thịt ăn mềm ngọt không bị khô là bí quyết thành công khi làm món này.', 'Bước 1: Xay nhỏ thịt\r\n- Thịt lợn rửa sạch, thái miếng rồi cho vào máy xay, xay nhỏ. Nếu có thời gian, bạn băm thịt sẽ ngon hơn xay\r\n- Lá lốt bỏ cuống, rửa sạch, để ráo nước và thấm khô để khi rán không bị bắn dầu. Lấy 3 lá lốt băm nhỏ để trộn cùng thịt cho thơm.\r\n- Hành khô bóc vỏ rồi rửa sạch băm nhỏ. Hành lá nhặt bỏ rễ và lá sâu rửa sạch sau đó thái nhỏ.\r\nBước 2: Ướp thịt xay với gia vị\r\n- Cho thịt băm, hành lá, hành khô, lá lốt vào bát tô rồi cho hạt tiêu, bột canh, chút mắm, chút đường, mình chính sau đó trộn đều.\r\n- Để thịt cuốn lá lốt ăn mềm không bị khô thì bạn trộn thêm chút dầu ăn vào để ướp khi đó thịt khi rán sẽ không bị mất nước ăn ngọt và mềm hơn.\r\nBước 3: Cách cuốn chả lá lốt đẹp\r\n- Đặt mặt trái lá lốt trên thớt, dùng thìa nhỏ múc nhân thịt cho lên lá trải đều rồi cuộn lại thành hình trụ tròn.\r\n- Cuộn xong dùng tăm xiên nhỏ xiên ngang để cố định chả lá lốt khi rán không bị bung.\r\nBước 4: Rán chả lá lốt đẹp\r\n- Cho dầu ăn vào chảo đun nóng rồi cho chả vào rán nhỏ lửa.\r\n- Lật đều 2 mặt chả lá lốt sẽ không bị cháy tới khi lá lốt chuyển sang màu vàng nâu là được.\r\nKhi chín gắp chả ra đĩa có giấy thấm dầu để miếng chả không bị ngấy, vậy là hoàn thành các bước hướng dẫn cách làm chả lá lốt.', '- Thịt ba chỉ: 300g\r\n\r\n- Lá lốt 1 mớ\r\n\r\n- Hành lá 3 nhánh\r\n\r\n- Bột nêm, hạt tiêu, muối, dầu ăn, mắm, đường', 'http://ffcteam.com/androidnc/image/chalanot.jpg', NULL, 0, '', '', ''),
(21, 'Gà hấp bia ngọt thanh', 'Gà xào sả ớt, gà luộc, gà nướng… đã rất quen thuộc đối với mâm cơm Việt. Nếu muốn sáng tạo một món ăn mới cho gia đình đừng bỏ qua món gà hấp bia. Gà hấp bia được biến tấu từ món gà hấp thông thường. Dưới đây là cách làm chi tiết gà hấp bia ngon.', 'Bước 1: Chọn gà:\r\n- Da gà có màu đều đẹp, không bị tím tái, không thâm đen, có nốt.\r\n\r\n- Mùi thơm, không có mùi hôi.\r\n\r\n- Không bị ra nước, lõm thịt, thịt trơn, biến dạng…\r\n\r\n- Nếu bạn chọn gà sống thì gà ngon thường có mào đỏ, khỏe mạnh, nhanh nhẹn.\r\n\r\n- Da gà đẹp đều, không có dấu hiệu bị thâm đen như đọng máu, trên da không có chấm đỏ như tiêm nước.\r\nBước 2: Làm sạch gà\r\n- Rửa gà cùng nước và gừng tươi để khử mùi hôi.\r\n- Bỏ hết phần nội tạng trong bụng gà.\r\nBước 3: Ướp gà:\r\n- Lá chanh rửa sạch, thái nhỏ nhồi vào bụng gà.\r\n\r\n- Sả vặt bỏ lá già, cắt gọn, đập giập rồi nhét vào bụng gà cho thơm.\r\n\r\n- Tỏi bóc vỏ, rửa sạch giã nhỏ rồi trộn với ớt tươi (tùy vào độ ăn cay của mỗi người).\r\n\r\n- Cho thêm vào một thìa nước mắm, một thìa hạt nêm, tiêu xay, muối trộn đều.\r\n\r\n- Sau đó, thoa đều hỗn hợp lên gà. Ướp chừng 30 phút.\r\nBước 4: Hấp gà:\r\n- Đổ 3 lon bia vào nồi hấp. Thêm sả đập dập để hấp gà có mùi thơm.\r\n\r\n- Cho gà vào trỏ, đặt vào nồi rồi đun cho đến khi thịt gà mềm, thơm. Dùng đũa chọc thấy không còn chảy ra nước đơn nữa là được.\r\n\r\n- Thời gian hấp gà thích hợp từ 40-60 phút. Để gà thịt gà ngọt hơn cứ mỗi 20 phút bạn nên lật gà một lần.\r\nBước 5: Làm sốt chấm gà:\r\n- Lấy một bát nước hấp gà, đun sôi. Tiếp tục thêm nửa lon bia vào nước hấp. Nấu nhỏ lửa đến khi nước luộc sệt đặc lại. Thêm nước mắm tùy theo khẩu vị.\r\nBước 6: Gà sau khi hấp để nguội, xé thịt, dưới nước sốt lên trên là có thể thưởng thức. Gà hấp bia có thể ăn kèm với hành tím, hành tươi, các loại rau sống.\r\nGà hấp bia đạt chuẩn thì da gà phải bóng vàng, thịt mềm, ngọt thanh. Cách gà hấp bia trên khá chi tiết và đơn giản. Chỉ cần cẩn thận canh đúng chuẩn thời gian hấp gà bạn đã có một món ăn ngon cho gia đình thưởng thức. Còn chờ gì mà bắt tay ngay vào bếp cùng cách làm gà hấp bia ngon trên.', '- Gà ta 1 con (1,5kg)\r\n\r\n- 3 lon bia\r\n\r\n- Lá chanh: 10 lá\r\n\r\n- Sả: 10 nhánh\r\n\r\n- Ớt, tỏi: 2 muỗng canh\r\n\r\n- Muối, hạt nêm, tiêu xay, nước mắm.', 'http://ffcteam.com/androidnc/image/ga.jpg', 3.5, 2, '', '', ''),
(22, 'Khoai tây chiên giòn', 'Trời se lạnh, chị em có thể làm món khoai tây chiên thơm ngon, đủ vị không cần chấm thêm gì bằng công thức dưới đây.', 'Bước 1: Sơ chế\r\n- Khoai tây gọt vỏ thái sợi không quá dày.\r\n\r\n- Ngâm khoai tây vào nước có vắt chanh từ 5-7 phút.\r\n\r\nLưu ý: Việc ngâm khoai với nước chanh pha loãng này giúp khoai không bị thâm xỉn.\r\n\r\n- Sau đó đổ ra rổ, xả qua nước lạnh.\r\nBước 2: Chần khoai\r\n- Nấu nước cùng với muối. Nước sôi cho khoai tây vào chần 3 phút là đổ ra rổ để ráo (bạn có thể dùng khăn hay giấy thấm khô).\r\nBước 3: Tẩm ướp\r\n- Cho khoai tây, muối ớt, bột chiên giòn cho hết vào bao nilon xóc đều.\r\nBước 4: Chiên khoai\r\n- Bắc chảo dầu lên bếp, chờ dầu nóng cho từng ít khoai vào chiên với lửa vừa.\r\n\r\n- Khi thấy khoai tây chiên vàng giòn thì vớt ra khay có lót giấy thấm dầu.\r\nCho khoai tây chiên cho ra đĩa. Bạn có thể ăn khoai tây chiên không hoặc ăn kèm tương cà thì tùy ý.\r\n\r\nKhoai tây chiên vàng ươm, giòn ngon, lại có chút cay cay của bột ớt vô cùng thú vị.', '- 2 củ khoai tây\r\n- 1/2 muỗng cà phê muối ớt\r\n- 1/2 muỗng cà phê muối\r\n- 70gr bột chiên giòn\r\n\r\n- 1/2 quả chanh to', 'http://ffcteam.com/androidnc/image/khoaitay.jpg', NULL, 0, '', '', ''),
(23, 'Cà chua nhồi cá thác lác hấp', 'Hãy đổi món cho bữa cơm gia đình bạn trong những ngày hè nóng nực này bằng món cà chua nhồi cá thác lác hấp thơm ngon nhé!', 'Bước 1: Sơ chế\r\nHành lá bỏ rễ, lá giập úa, rửa sạch, xắt nhỏ. Ớt bỏ cuống, rửa sạch, xắt nhỏ. Cho cá vào tô, sau đó cho hành lá và ớt đã xắt nhỏ ở trên, thêm một ít gia vị, tiêu xay trộn đều. Dùng thìa quết thật đều cho thịt cá được dai.\r\nCà chua chọn quả tròn, đẹp, cắt ngang khoảng 1/4 phía trên đầu. Sau đó khoét lấy ruột để riêng, phần lõi giữa bỏ đi. Có thể tỉa trang trí tuỳ thích. \r\nBước 2: Nhồi cá vào cà chua\r\nNhồi cá đã quết ở trên vào trong quả cà chua cho chặt. \r\nBước 3: Hấp cà chua nhồi cá\r\nSau đó cho cà chua vào xửng hấp chín hoặc dùng mang bao thực phẩm bao lại cho lò vi sóng chế độ hấp quay chín. \r\nBước 4: Làm nước sốt\r\nTrong lúc chờ cà chua chín chuẩn bị phần nước sốt bằng cách bắc chảo lên bếp, thêm 1 thìa dầu ăn. Khi dầu nóng cho ruột cà lấy ra ở trên vào chảo xào kỹ. Thêm chút đường, nước mắm nêm nếm vừa ăn là được. Chú ý do ruột cà chua có vị tương đối chua nên để vị của món này dịu, vừa ăn bạn nên cho khoảng 1/2 thìa đường nhé! ', '- 200g thịt cá thác lác\r\n\r\n- 5-6 trái cà chua\r\n\r\n- Hành lá, ớt, bột nêm, tiêu, dầu ăn, đường, nước mắm. ', 'http://ffcteam.com/androidnc/image/cachuaca.jpg', NULL, 0, '', '', ''),
(24, 'Tôm cuộn dừa chiên', '', 'Bước 1:\r\nTôm mua về các bạn đem rửa sạch với muối, ít rượu và gừng để khử mùi tanh của tôm. Sau đó, các bạn rửa lại với nước lần nữa cho sạch. Tiếp theo, các bạn bóc bỏ phần vỏ và đầu tôm, để lại phần đuôi tôm. Các bạn làm khô tôm bằng khăn giấy. Sau đó, các bạn dùng một đầu dao giữ tôm nằm thẳng ra.\r\nBước 2:\r\nTrong một chiếc bát lớn, các bạn trộn tôm với 2 thìa canh Sriracha và 1 thìa canh mật ong. Để khoảng 15 phút cho tôm ngấm gia vị.\r\nBước 3:\r\nTiếp theo, các bạn trộn mayonnaise với nước cốt chanh, rau mùi cùng 1 thìa Srircha. Thêm muối và hạt tiêu rồi đặt qua một bên. Đây sẽ là nước chấm sau khi chúng ta hoàn thành.\r\nBước 4:\r\nSau đó, các bạn nhúng hoàn toàn tôm trong bột dừa nạo nhỏ.\r\nBước 5:\r\nCác bạn cắt đôi tấm bánh tráng cuốn theo đường chéo. Đặt lên một mặt phẳng và mũi chính hướng về phía bên trái\r\nBước 6:\r\nCác bạn đặt tôm vào phần thân dưới của tấm bánh tráng. Đuôi tôm nằm ở phía ngoài rồi cuộn tròn tấm bánh quanh tôm, đến nửa chừng thì gấp điểm mũi chính vào (như hình) rồi sau đó cuộn cho đến hết.\r\nBước 7:\r\nTiếp theo, các bạn bắc 1 chảo dầu lên bếp, đun nóng dầu trong chảo ở nhiệt độ 300 độ C. Sau đó, dầu sôi thì các bạn thả tôm vào chiên đều các mặt đến khi vàng giòn. Bước này mất khoảng 2 phút.\r\nKhi chiên xong, tôm chín, các bạn đặt tôm lên rây cho loại bỏ bớt dầu.\r\nCuối cùng, các bạn trình vày tôm ra đĩa rồi thưởng thức cùng nước sốt ngay khi còn giòn nhé!', '- Tôm 24 con\r\n- Sốt chấm - 2 thìa canh + 1 thìa canh Sriracha\r\n- Mật ong - 1 thìa mật ong\r\n- Chanh - 1/2 quả\r\n- Rau mùi thái nhỏ - 1 thìa canh\r\n- Gia vị: muối, tiêu\r\n- Dừa nạo: 2/3 chén\r\n- Bánh tráng làm gỏi cuốn, cắt làm đôi theo đường chéo: 12 tấm\r\n- Trứng 1 quả\r\n- Dầu thực vật 2 chén', 'http://ffcteam.com/androidnc/image/tom-cuon-dua.jpg', 2.2, 5, '', '', ''),
(25, 'Bánh chuối nướng', 'Bánh chuối nướng – món ăn dinh dưỡng cho bữa sáng ngon miệng', 'Bước 1: Sơ chế nguyên liệu làm bánh mì chuối nướng.\r\n- Chuối chín các bạn bóc bỏ vỏ, thái thành những khoanh tròn rồi cho vào đĩa dặm thật nhuyễn. Nếu có máy xay sinh tố thì bạn có thể cho vào máy xay xay vừa nhuyễn tới.\r\n- Bột làm bánh mì các bạn lọc lại một lần cho bột thật mịn.\r\n\r\n- Tiếp theo, các bạn cho bơ vào lò vi sóng khoảng 30 giây cho bơ tan chảy hoàn toàn\r\n\r\n- Cuối cùng các bạn làm nóng sẵn lò nướng ở nhiệt độ là 340 độ F tương đương với 170 độ C\r\nBước 2: Các bước làm món bánh mì chuối nướng.\r\n- Trước tiên, các bạn đổ chuối đã nghiền nhuyễn vào một cái bát tô lớn rồi cho thêm bơ tan chảy vào\r\n- Tiếp theo, các bạn cho thêm một muỗng cà phê vani vào bát chuối rồi đến 1 muỗng cà phê bột nở và bột nhục đậu khấu, đường cát trắng và cuối cùng là bột làm bánh mì. Tuy nhiên, với phần bột làm bánh chúng ta sẽ chia làm hai lần để khi trộn với hỗn hợp chuối sẽ dễ dàng hơn và bột cũng không bị vón cục, hỗn hợp được nhuyễn, mịn hơn.\r\n- Các bạn trộn thật đều hỗn hợp bánh chuối cho thật mịn và trở nên đặc hơn.\r\n\r\n- Sau đó, các bạn phết một lớp mỏng bơ vào các khuôn nướng bánh để chống dính rồi múc hỗn hợp bánh vào khuôn với một lượng vừa đủ để khi chín bánh có khoảng trống để nở.\r\n- Khi đã chuẩn bị xong, các bạn cho khuôn bánh vào lò nướng đã được làm nóng sẵn và nướng trong 40-45 phút hoặc cho đến khi bánh chín, có bề ngoài màu vàng nâu.\r\n- Hết thời gian này, các bạn lấy khuôn bánh ra ngoài và kiểm tra xem bánh đã chín chưa, nếu như bánh còn chưa đủ chín hoặc bạn thích ăn bánh theo kiểu giòn hơn thì hãy đặt khuôn bánh vào lò nướng thêm 5 phút nữa là được.\r\n\r\n- Sau đó, các bạn xếp bánh lên một cái giá cho nguội trước khi ăn.\r\n\r\n- Cuối cùng, các bạn trình bày bánh ra đĩa và ăn kèm với bơ, mật ong hay mứt tùy theo khẩu vị của từng người.', '- Chuối chín - 2 - 3 quả\r\n- Bơ - 1 muỗng canh\r\n- Vani - 1 muỗng cà phê\r\n- Bột nở - 1 muỗng cà phê\r\n- Bột làm bánh mì - 1 chén\r\n- Đường cát trắng - 1/2 chén\r\n- Hạt nhục đậu khấu - 1 muỗng cà phê', 'http://ffcteam.com/androidnc/image/banh-mi-chuoi.jpg', NULL, 0, '', '', ''),
(26, 'Súp tôm và ngô non', 'Súp tôm và ngô non – các món súp kiểu âu', 'Bước 1: Ninh xương heo lấy nước dùng như đã hướng dẫn.\r\nBước 2: Rửa sạch tôm, hấp và đem lột vỏ hoàn toàn sau đó thái hạt lựu. Lưu ý, dùng lại nước luộc tôm để cho vào cùng với nước dùng tạo vị ngọt tự nhiên.\r\nBước 3: Ngâm nấm hương và cắt dọc làm bốn.\r\nBước 3: Phi hành và xào tôm cho thơm. Lần lượt cho tiếp nấm hương, ngô và đậu hà lan vào xào sơ qua.\r\nBước 4: Cho hỗn hợp đã xào vào trong nồi nước dùng. Khi các nguyên liệu vừa chín tới, bạn từ từ hòa lòng trắng trứng vào nồi súp để tạo thành sợi và hòa nước bột bắp để tạo độ sánh cho món súp.', '- 450gr tôm tươi\r\n- 3 cái xương vai heo\r\n- 2 trái ngô non (tách lấy hạt)\r\n- 1 hộp đậu Hà Lan (loại nhỏ)\r\n- 5 tai nấm hương (ngâm nấm hương và cắt dọc làm bốn)\r\n- 3 muỗng canh bột bắp\r\n- 2 trứng gà\r\n- Gia vị: tiêu, muối', 'http://ffcteam.com/androidnc/image/sup-khai-vi.jpg', NULL, 0, '', '', ''),
(27, 'Hải sản xào chua ngọt', 'Vị chua ngọt từ cà chua của nước xốt hòa cùng hải sản tươi ngon cho món ăn tuyệt vời không cưỡng lại được', 'Sơ chế:\r\nMực làm sạch, khía vẩy rồng, cắt miếng vừa ăn.\r\nTôm lột vỏ chừa đuôi lấy chỉ đen\r\nỚt chuông cắt miếng vuông,\r\nHành tây bổ múi cau,\r\nDứa cắt miếng\r\nCần tây bỏ lá, rửa sạch cắt khúc.\r\nCà rốt bào vỏ,rửa sạch cắt khoanh tròn\r\nCà chua cắt miếng vuông\r\nThực hiện:\r\n- Bước 1: Cho dầu vào chảo, xào thơm hành tỏi băm, cho mực và tôm vào xào hơi săn lại.\r\n\r\n- Bước 2: Cho 1gói Xốt gia vị hoàn chỉnh Barona - Hải sản xào chua ngọt đảo đều đến khi mực, tôm gần chín và thấm xốt. Thêm rau củ đảo đều rồi tắt bếp.\r\n\r\n- Bước 3: Cho hải sản xào chua ngọt ra đĩa. Dùng với cơm nóng.', '- 1 gói Xốt gia vị hoàn chỉnh Barona - Hải sản xào chua ngọt (80g)\r\n- 200g mực ống/mực lá\r\n- 200g tôm sú/tôm thẻ\r\n- ½ trái ớt chuông xanh\r\n- ½ trái ớt chuông đỏ\r\n- Cà chua, thơm,cần tây\r\n- 1 củ cà rốt nhỏ, 1 muỗng dầu ăn, ½ củ hành tây', 'http://ffcteam.com/androidnc/image/haisan.jpg', NULL, 0, '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `a_tbl_taikhoan`
--

CREATE TABLE IF NOT EXISTS `a_tbl_taikhoan` (
  `PK_iMaTaiKhoan` int(11) NOT NULL AUTO_INCREMENT,
  `sEmail` varchar(255) NOT NULL,
  `sMatKhau` varchar(255) NOT NULL,
  `FK_iMaUser` int(11) NOT NULL,
  `sAvatar` text NOT NULL,
  PRIMARY KEY (`PK_iMaTaiKhoan`),
  UNIQUE KEY `sEmail` (`sEmail`),
  KEY `FK_iMaUser` (`FK_iMaUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `a_tbl_taikhoan`
--

INSERT INTO `a_tbl_taikhoan` (`PK_iMaTaiKhoan`, `sEmail`, `sMatKhau`, `FK_iMaUser`, `sAvatar`) VALUES
(1, 'ndthanh.7598@gmail.com', '12', 1, 'http://ffcteam.com/androidnc/image/1563153529.jpg'),
(3, 'tuyendo', '1', 3, 'http://ffcteam.com/androidnc/image/deep.jpg'),
(7, 'dotuyen221098@gmail.com', '1', 7, 'http://ffcteam.com/androidnc/image/1564553731.jpg'),
(9, 'test@gmail.com', '1', 9, ''),
(10, 'hongdothi0509@gmail.com', '1', 10, 'http://ffcteam.com/androidnc/image/deep.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `a_tbl_thucdon_tuan`
--

CREATE TABLE IF NOT EXISTS `a_tbl_thucdon_tuan` (
  `PK_iMaTD` int(11) NOT NULL AUTO_INCREMENT,
  `sNgayThucHien` varchar(255) NOT NULL,
  `sBuoi` varchar(255) NOT NULL,
  `FK_iMaMon` int(11) NOT NULL,
  `FK_iMaUser` int(11) NOT NULL,
  PRIMARY KEY (`PK_iMaTD`),
  KEY `FK_iMaMon` (`FK_iMaMon`),
  KEY `FK_iMaUser` (`FK_iMaUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=37 ;

--
-- Dumping data for table `a_tbl_thucdon_tuan`
--

INSERT INTO `a_tbl_thucdon_tuan` (`PK_iMaTD`, `sNgayThucHien`, `sBuoi`, `FK_iMaMon`, `FK_iMaUser`) VALUES
(2, '08/07/2019', 'trua', 1, 1),
(3, '08/07/2019', 'toi', 13, 1),
(4, '08/07/2019', 'toi', 15, 1),
(5, '10/07/2019', 'toi', 2, 1),
(6, '10/07/2019', 'toi', 3, 1),
(11, '14/07/2019', 'sang', 14, 7),
(12, '10/07/2019', 'trua', 3, 1),
(14, '08/07/2019', 'sang', 23, 7),
(15, '13/07/2019', 'sang', 3, 1),
(16, '09/07/2019', 'sang', 1, 1),
(17, '10/07/2019', 'sang', 2, 1),
(18, '09/07/2019', 'trua', 15, 1),
(22, '10/07/2019', 'sang', 13, 1),
(26, '09/07/2019', 'trua', 27, 7),
(28, '15/07/2019', 'sang', 11, 1),
(29, '15/07/2019', 'trua', 12, 1),
(31, '15/07/2019', 'toi', 1, 1),
(32, '22/07/2019', 'sang', 1, 7),
(33, '22/07/2019', 'trua', 11, 7),
(34, '22/07/2019', 'toi', 12, 7),
(35, '31/07/2019', 'toi', 1, 7),
(36, '31/07/2019', 'sang', 11, 7);

-- --------------------------------------------------------

--
-- Table structure for table `a_tbl_users`
--

CREATE TABLE IF NOT EXISTS `a_tbl_users` (
  `PK_iMaUser` int(11) NOT NULL AUTO_INCREMENT,
  `sTenHienThi` varchar(255) NOT NULL,
  `sGioiTinh` varchar(3) NOT NULL,
  `sNgaySinh` varchar(255) NOT NULL,
  `sNhom` varchar(255) NOT NULL,
  `sKhaoSat` varchar(255) NOT NULL DEFAULT 'chuaks',
  `sRegToken` text NOT NULL,
  PRIMARY KEY (`PK_iMaUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `a_tbl_users`
--

INSERT INTO `a_tbl_users` (`PK_iMaUser`, `sTenHienThi`, `sGioiTinh`, `sNgaySinh`, `sNhom`, `sKhaoSat`, `sRegToken`) VALUES
(1, 'Nguyễn Duy Thành', 'Nam', '07/05/1998', '1', 'daks', 'e9-XQ3Po4Ns:APA91bEtllQclzXHB7laLwDaqEBtk3i4ujhwACFrrXa3Z9Ug9S1jl_SsqdQUjaQHboPwfSasRA562PC2CQHr6I1hc0GcsfsvNTUf0YB4eJ95LzR81uYJB5-5nvL69C_8nNL-flqsD0s1'),
(3, 'Nhung Tuyên Đỗ', 'Nữ', '', '2', 'daks', 'e9-XQ3Po4Ns:APA91bEtllQclzXHB7laLwDaqEBtk3i4ujhwACFrrXa3Z9Ug9S1jl_SsqdQUjaQHboPwfSasRA562PC2CQHr6I1hc0GcsfsvNTUf0YB4eJ95LzR81uYJB5-5nvL69C_8nNL-flqsD0s1'),
(7, 'Tuyên Đỗ', 'Nữ', '22/10/1998', '1', 'daks', 'dh_yx9Ticys:APA91bGRaA5VUKxFFpsSq5vc0rywOfrrS9Qp9V3-GGl1Jwrc6hmXqiSlw6T-Pdv7ploE7br0xAmz_oMsFpqhLGyvWWPlltacZQ5vUdkO3saJAkKuJ1W6rMdOO1I4mxeTwWaX1OxFQG4N'),
(9, 'Test App', '', '', '', 'chuaks', 'e9-XQ3Po4Ns:APA91bEtllQclzXHB7laLwDaqEBtk3i4ujhwACFrrXa3Z9Ug9S1jl_SsqdQUjaQHboPwfSasRA562PC2CQHr6I1hc0GcsfsvNTUf0YB4eJ95LzR81uYJB5-5nvL69C_8nNL-flqsD0s1'),
(10, 'Hồng Đỗ', '', '', '1', 'daks', 'efSUhVxuhBE:APA91bFbt_NU8hV_xmfjBrf1ha_suBu4OBTzTU7qCVAQUKFtD1UlefGMYRlrnW5ie1lZ3GaLI5TobXi101zoEZpCYwXI-DvBH7Iaibh6hk-PwZsekO73yIMH1ZoYLbSmpTWcr7YQyLSc');

-- --------------------------------------------------------

--
-- Table structure for table `a_tbl_user_khaosat`
--

CREATE TABLE IF NOT EXISTS `a_tbl_user_khaosat` (
  `PK_iMaKS` int(11) NOT NULL,
  `PK_iMaUser` int(11) NOT NULL,
  PRIMARY KEY (`PK_iMaKS`,`PK_iMaUser`),
  KEY `PK_iMaUser` (`PK_iMaUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `a_tbl_user_khaosat`
--

INSERT INTO `a_tbl_user_khaosat` (`PK_iMaKS`, `PK_iMaUser`) VALUES
(1, 1),
(1, 3),
(1, 7),
(1, 10),
(2, 3),
(2, 7),
(2, 10),
(3, 1),
(3, 7),
(4, 3),
(4, 10),
(5, 7),
(6, 10),
(7, 3),
(7, 7),
(7, 10);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `a_tbl_binhluan`
--
ALTER TABLE `a_tbl_binhluan`
  ADD CONSTRAINT `a_tbl_binhluan_ibfk_1` FOREIGN KEY (`FK_iMaUser`) REFERENCES `a_tbl_users` (`PK_iMaUser`) ON UPDATE CASCADE,
  ADD CONSTRAINT `a_tbl_binhluan_ibfk_2` FOREIGN KEY (`FK_iMaMon`) REFERENCES `a_tbl_monan` (`PK_iMaMon`) ON UPDATE CASCADE;

--
-- Constraints for table `a_tbl_taikhoan`
--
ALTER TABLE `a_tbl_taikhoan`
  ADD CONSTRAINT `fk_user_tk` FOREIGN KEY (`FK_iMaUser`) REFERENCES `a_tbl_users` (`PK_iMaUser`) ON UPDATE CASCADE;

--
-- Constraints for table `a_tbl_thucdon_tuan`
--
ALTER TABLE `a_tbl_thucdon_tuan`
  ADD CONSTRAINT `a_tbl_thucdon_tuan_ibfk_1` FOREIGN KEY (`FK_iMaMon`) REFERENCES `a_tbl_monan` (`PK_iMaMon`) ON UPDATE CASCADE,
  ADD CONSTRAINT `a_tbl_thucdon_tuan_ibfk_2` FOREIGN KEY (`FK_iMaUser`) REFERENCES `a_tbl_users` (`PK_iMaUser`) ON UPDATE CASCADE;

--
-- Constraints for table `a_tbl_user_khaosat`
--
ALTER TABLE `a_tbl_user_khaosat`
  ADD CONSTRAINT `a_tbl_user_khaosat_ibfk_1` FOREIGN KEY (`PK_iMaKS`) REFERENCES `a_dm_khaosat` (`PK_iMaKS`) ON UPDATE CASCADE,
  ADD CONSTRAINT `a_tbl_user_khaosat_ibfk_2` FOREIGN KEY (`PK_iMaUser`) REFERENCES `a_tbl_users` (`PK_iMaUser`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

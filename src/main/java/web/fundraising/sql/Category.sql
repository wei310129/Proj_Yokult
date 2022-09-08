-- 建立募資平台資料庫
-- 選擇專題資料庫
use `YOKULT`;

-- 提案類別
create table `Fund_CATEGORY`(
	`categoryID` varchar(2) not null primary key comment '分類編號',
	`categoryName` varchar(10) not null comment '分類名稱'
);

-- 存入資料
insert into `Fund_CATEGORY`(
	`categoryID`, 
    `categoryName`
) values
(1, '潔牙物品'),
(2, '牙齒矯治'),
(3, '公益活動'),
(4, '衛教出版')
-- 建立募資平台資料庫
-- 選擇專題資料庫
use `YOKULT`;

-- 建立方案資料
create table `Fund_PLAN`(
	`planID` integer auto_increment not null primary key comment '方案編號',
	`planName` varchar(50) not null comment '方案名稱',
	`planAmount` integer not null comment '方案金額',
	`planContent` varchar(300) not null comment '方案內容',
	`planPostNote` varchar(100) not null comment '寄送備註',
    `planPostDate` date comment '方案寄送時間',
	`planStartedDateTime` date not null comment '方案開始時間',
	`planEndedDateTime` date not null comment '方案結束時間',
    `planPicture` blob  comment '方案圖檔案',
	`proposalID` integer not null comment '提案編號',
    foreign key(`proposalID`) references `Fund_PROPOSAL`(`proposalID`)
);

-- 存入資料

insert into `Fund_PLAN`(
	`planName`,
	`planAmount`,
	`planContent`,
	`planPostNote`,
    `planPostDate`,
	`planStartedDateTime`,
	`planEndedDateTime`,
    `planPicture`,
	`proposalID`
) values

-- 1 偏鄉牙醫志工團｜為偏鄉學童巡迴牙齒健檢
('純贊助',200,'備註：僅代表團隊感謝您的贊助','無回饋',null,
'2022-06-19 00:00:00','2023-07-19 00:00:00','',1),
('感謝小卡',500,'備註：感謝小卡將由我們親自設計','只寄送台灣本島',
'2022-09-19 00:00:00','2022-06-19 00:00:00','2022-07-19 00:00:00','',1),
('大力贊助 + 紀念T恤',5000,'備註：紀念T恤為國寶設計師XXX設計','只寄送台灣本島',
'2028-03-25 00:00:00','2024-07-25 00:00:00','2027-12-25 00:00:00','',1),

-- 2 2023微笑曲線計畫｜為每個人打造獨一無二的微笑曲線
('瓷牙貼片',30000,'備註：瓷牙貼片多色可選','直接到診所',
'2023-09-19 00:00:00','2022-07-19 00:00:00','2023-07-19 00:00:00','',2),
('瓷牙貼片 + 齒列矯正',150000,'備註：瓷牙貼片多色可選，並使用AI+3D技術精準定位牙齒','直接到診所',
'2023-09-19 00:00:00','2025-07-19 00:00:00','2023-07-19 00:00:00','',2),

-- 3 環保牙刷計畫 | 刷牙也要用最天然的
('純贊助',200,'備註：僅代表團隊感謝您的贊助','無回饋',null,
'2026-08-01 00:00:00','2026-09-01 00:00:00','',3),
('馬細毛環保牙刷 x 4',1200,'備註：天然材質細微變色為正常現象','只寄送台灣本島',
'2026-08-01 00:00:00','2026-09-01 00:00:00','2026-12-01 00:00:00','',3),
('馬極纖細毛牙刷 x 2',3000,'備註：天然材質細微變色為正常現象','只寄送台灣本島',
'2026-08-01 00:00:00','2026-09-01 00:00:00','2026-12-01 00:00:00','',3)
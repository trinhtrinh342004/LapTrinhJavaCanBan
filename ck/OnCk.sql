BEGIN
   FOR t IN (SELECT table_name FROM user_tables) LOOP
      EXECUTE IMMEDIATE 'DROP TABLE ' || t.table_name || ' CASCADE CONSTRAINTS';
   END LOOP;
END;
/

ALTER SESSION SET CURRENT_SCHEMA = fly_the_end12A;

CREATE TABLE TOUR (
    MaTour NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    TenTour VARCHAR2(255),
    NgayKhoiHanh DATE,
    SoNgay NUMBER,
    SoDem NUMBER,
    Gia NUMBER
);

CREATE TABLE TINHTP (
    MaTTP NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    TenTTP VARCHAR2(255)
);

CREATE TABLE DIEMDL (
    MaDDL NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    TenDDL VARCHAR2(255),
    MaTTP NUMBER,
    DacTrung VARCHAR2(255),
    FOREIGN KEY (MaTTP) REFERENCES TINHTP(MaTTP)
);

CREATE TABLE CHITIET (
    MaTour NUMBER,
    MaDDL NUMBER,
    PRIMARY KEY (MaTour, MaDDL),
    FOREIGN KEY (MaTour) REFERENCES TOUR(MaTour),
    FOREIGN KEY (MaDDL) REFERENCES DIEMDL(MaDDL)
);


-- Thêm dữ liệu vào bảng TINHTP
INSERT INTO TINHTP (TenTTP) VALUES ('Hà Nội');
INSERT INTO TINHTP (TenTTP) VALUES ('Hồ Chí Minh');
INSERT INTO TINHTP (TenTTP) VALUES ('Đà Nẵng');

-- Thêm dữ liệu vào bảng DIEMDL
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Lăng Bác', 1, 'Nơi an nghỉ của Chủ tịch Hồ Chí Minh');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Nhà thờ Đức Bà', 2, 'Biểu tượng của thành phố Hồ Chí Minh');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Cầu Rồng', 3, 'Cầu rồng phun lửa và nước vào các ngày cuối tuần');

-- Thêm dữ liệu vào bảng TOUR
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Hà Nội', TO_DATE('2023-07-01', 'YYYY-MM-DD'), 3, 2, 3000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Hồ Chí Minh', TO_DATE('2023-08-01', 'YYYY-MM-DD'), 4, 3, 4000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Đà Nẵng', TO_DATE('2023-09-01', 'YYYY-MM-DD'), 5, 4, 5000000);

-- Thêm dữ liệu vào bảng CHITIET
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (1, 1);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (2, 2);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (3, 3);


-- Thêm dữ liệu vào bảng TOUR
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Hà Nội - Sài Gòn', TO_DATE('2023-07-15', 'YYYY-MM-DD'), 5, 4, 7000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Hà Nội - Đà Nẵng - Hội An', TO_DATE('2023-08-15', 'YYYY-MM-DD'), 6, 5, 8000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Sài Gòn - Phan Thiết - Nha Trang', TO_DATE('2023-09-15', 'YYYY-MM-DD'), 7, 6, 9000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Hà Nội - Đà Lạt - Nha Trang - Phan Thiết', TO_DATE('2023-10-01', 'YYYY-MM-DD'), 8, 7, 10000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Sài Gòn - Cần Thơ - Đà Nẵng - Huế', TO_DATE('2023-11-01', 'YYYY-MM-DD'), 9, 8, 11000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Hà Nội - Sapa - Hạ Long - Cát Bà', TO_DATE('2023-12-01', 'YYYY-MM-DD'), 10, 9, 12000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Đà Nẵng - Hội An - Huế - Quảng Bình', TO_DATE('2024-01-01', 'YYYY-MM-DD'), 11, 10, 13000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Sài Gòn - Đà Lạt - Nha Trang - Phan Thiết', TO_DATE('2024-02-01', 'YYYY-MM-DD'), 12, 11, 14000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Hà Nội - Hạ Long - Sapa - Mộc Châu', TO_DATE('2024-03-01', 'YYYY-MM-DD'), 13, 12, 15000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Đà Nẵng - Hội An - Huế - Quảng Nam', TO_DATE('2024-04-01', 'YYYY-MM-DD'), 14, 13, 16000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Sài Gòn - Đà Lạt - Nha Trang - Phan Rang', TO_DATE('2024-05-01', 'YYYY-MM-DD'), 15, 14, 17000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Hà Nội - Tam Đảo - Mai Châu - Sơn La', TO_DATE('2024-06-01', 'YYYY-MM-DD'), 16, 15, 18000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Đà Nẵng - Bà Nà - Hội An - Huế', TO_DATE('2024-07-01', 'YYYY-MM-DD'), 17, 16, 19000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Sài Gòn - Phan Thiết - Nha Trang - Quy Nhơn', TO_DATE('2024-08-01', 'YYYY-MM-DD'), 18, 17, 20000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Hà Nội - Hạ Long - Cát Bà - Sầm Sơn', TO_DATE('2024-09-01', 'YYYY-MM-DD'), 19, 18, 21000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Đà Lạt - Nha Trang - Phan Thiết - Mũi Né', TO_DATE('2024-10-01', 'YYYY-MM-DD'), 20, 19, 22000000);
INSERT INTO TOUR (TenTour, NgayKhoiHanh, SoNgay, SoDem, Gia) VALUES ('Tour Sài Gòn - Cần Thơ - Phú Quốc - Rạch Giá', TO_DATE('2024-11-01', 'YYYY-MM-DD'), 21, 20, 23000000);

-- Thêm dữ liệu vào bảng CHITIET cho các tour mới
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (11, 1);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (11, 2);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (11, 3);

INSERT INTO CHITIET (MaTour, MaDDL) VALUES (12, 2);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (12, 4);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (12, 5);

INSERT INTO CHITIET (MaTour, MaDDL) VALUES (13, 3);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (13, 4);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (13, 6);

INSERT INTO CHITIET (MaTour, MaDDL) VALUES (14, 1);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (14, 5);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (14, 7);

INSERT INTO CHITIET (MaTour, MaDDL) VALUES (15, 2);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (15, 6);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (15, 8);

INSERT INTO CHITIET (MaTour, MaDDL) VALUES (16, 3);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (16, 7);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (16, 9);

INSERT INTO CHITIET (MaTour, MaDDL) VALUES (17, 4);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (17, 8);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (17, 10);

INSERT INTO CHITIET (MaTour, MaDDL) VALUES (18, 5);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (18, 9);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (18, 11);

INSERT INTO CHITIET (MaTour, MaDDL) VALUES (19, 6);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (19, 10);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (19, 12);

INSERT INTO CHITIET (MaTour, MaDDL) VALUES (20, 7);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (20, 11);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (20, 13);

INSERT INTO CHITIET (MaTour, MaDDL) VALUES (21, 8);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (21, 12);
INSERT INTO CHITIET (MaTour, MaDDL) VALUES (21, 14);


INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Vịnh Hạ Long', 1, 'Di sản thiên nhiên thế giới');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Sapa', 1, 'Địa điểm du lịch nổi tiếng với cảnh sắc non nước hùng vĩ');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Mũi Né', 2, 'Thiên đường của gió và cát');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Vũng Tàu', 2, 'Thành phố biển nổi tiếng gần TP.HCM');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Đà Lạt', 2, 'Thành phố ngàn hoa và sương mù');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Biển Cửa Lò', 3, 'Điểm đến biển yên bình ở Nghệ An');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Phong Nha - Kẻ Bàng', 3, 'Di sản thiên nhiên thế giới với hang động kỳ vĩ');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Bà Nà Hills', 3, 'Thiên đường nghỉ dưỡng trên đỉnh núi');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Hội An', 2, 'Phố cổ Hội An với kiến trúc cổ kính');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Huế', 2, 'Di sản văn hóa thế giới của UNESCO');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Đà Nẵng', 3, 'Thành phố sẽ hội của biển và núi');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Côn Đảo', 3, 'Quần đảo hoang sơ với bãi biển đẹp nhất Việt Nam');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Nha Trang', 3, 'Thành phố biển với bãi biển đẹp nhất châu Á');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Quy Nhơn', 3, 'Thành phố biển yên bình của Bình Định');
INSERT INTO DIEMDL (TenDDL, MaTTP, DacTrung) VALUES ('Vịnh Xuân Đài', 3, 'Thiên đường nghỉ dưỡng của Thanh Hóa');
/*-- DROP ALL TABLE
BEGIN
   FOR t IN (SELECT table_name FROM user_tables) LOOP
      EXECUTE IMMEDIATE 'DROP TABLE ' || t.table_name || ' CASCADE CONSTRAINTS';
   END LOOP;
END;
*/

alter session set "_ORACLE_SCRIPT"=true;

CREATE USER PHONGKHAM IDENTIFIED BY password;
GRANT CONNECT, RESOURCE, DBA TO PHONGKHAM;

-- Sử dụng Schema
ALTER SESSION SET CURRENT_SCHEMA = PHONGKHAM;

CREATE TABLE BACSI (
    MABS VARCHAR2(10) PRIMARY KEY,
    TENBS VARCHAR2(50)
);

CREATE TABLE BENHNHAN (
    MABN VARCHAR2(10) PRIMARY KEY,
    TENBN VARCHAR2(50),
    NGSINH VARCHAR2(10),
    DCHI VARCHAR2(100),
    DTHOAI VARCHAR2(15),
    GIOITINH CHAR(1)
);

CREATE TABLE KHAMBENH (
    MAKB VARCHAR2(10) PRIMARY KEY,
    MABN VARCHAR2(10) REFERENCES BENHNHAN(MABN),
    MABS VARCHAR2(10) REFERENCES BACSI(MABS),
    NGAYKHAM VARCHAR2(10),
    YEUCAUKHAM VARCHAR2(200),
    KETLUAN VARCHAR2(200),
    THANHTOAN CHAR(1)
);

CREATE TABLE DICHVU (
    MADV VARCHAR2(10) PRIMARY KEY,
    TENDV VARCHAR2(50),
    DONGIA NUMBER(10, 2)
);

CREATE TABLE THUPHI (
    MAKB VARCHAR2(10),
    MADV VARCHAR2(10),
    SOLUONG NUMBER,
    THANHTIEN NUMBER(10, 2),
    PRIMARY KEY (MAKB, MADV),
    FOREIGN KEY (MAKB) REFERENCES KHAMBENH(MAKB),
    FOREIGN KEY (MADV) REFERENCES DICHVU(MADV)
);

SELECT DISTINCT TENBN FROM BENHNHAN WHERE MABN IN (SELECT MABN FROM KHAMBENH WHERE MABS = 'BS001' AND NGAYKHAM = TO_DATE('2024-06-01', 'YYYY-MM-DD'));

-- Thêm dữ liệu vào bảng BACSI
INSERT INTO BACSI (MABS, TENBS) VALUES ('BS001', 'Nguyen Van A');
INSERT INTO BACSI (MABS, TENBS) VALUES ('BS002', 'Le Thi B');
INSERT INTO BACSI (MABS, TENBS) VALUES ('BS003', 'Tran Van C');

-- Thêm dữ liệu vào bảng BENHNHAN
INSERT INTO BENHNHAN (MABN, TENBN, NGSINH, DCHI, DTHOAI, GIOITINH) VALUES ('BN001', 'Pham Van D', '01/01/1990', '123 Nguyen Trai', '0912345678', 'M');
INSERT INTO BENHNHAN (MABN, TENBN, NGSINH, DCHI, DTHOAI, GIOITINH) VALUES ('BN002', 'Tran Thi E', '15/05/1985', '456 Le Loi', '0987654321', 'F');
INSERT INTO BENHNHAN (MABN, TENBN, NGSINH, DCHI, DTHOAI, GIOITINH) VALUES ('BN003', 'Nguyen Van F', '20/10/2000', '789 Tran Hung Dao', '0901234567', 'M');

-- Thêm dữ liệu vào bảng KHAMBENH
INSERT INTO KHAMBENH (MAKB, MABN, MABS, NGAYKHAM, YEUCAUKHAM, KETLUAN, THANHTOAN) VALUES ('KB001', 'BN001', 'BS001', '1/6/2024', 'Kiem tra suc khoe tong quat', 'Khong co van de', 'N');
INSERT INTO KHAMBENH (MAKB, MABN, MABS, NGAYKHAM, YEUCAUKHAM, KETLUAN, THANHTOAN) VALUES ('KB002', 'BN002', 'BS002', '2/6/2024', 'Kham da lieu', 'Dieu tri viem da', 'N');
INSERT INTO KHAMBENH (MAKB, MABN, MABS, NGAYKHAM, YEUCAUKHAM, KETLUAN, THANHTOAN) VALUES ('KB003', 'BN003', 'BS003', '3/6/2024', 'Kham noi khoa', 'Dieu tri benh da day', 'N');

-- Thêm dữ liệu vào bảng DICHVU
INSERT INTO DICHVU (MADV, TENDV, DONGIA) VALUES ('DV001', 'Xet nghiem mau', 500000);
INSERT INTO DICHVU (MADV, TENDV, DONGIA) VALUES ('DV002', 'Chup X-Quang', 700000);
INSERT INTO DICHVU (MADV, TENDV, DONGIA) VALUES ('DV003', 'Sieu am', 800000);

-- Thêm dữ liệu vào bảng THUPHI
INSERT INTO THUPHI (MAKB, MADV, SOLUONG, THANHTIEN) VALUES ('KB001', 'DV001', 1, 500000);
INSERT INTO THUPHI (MAKB, MADV, SOLUONG, THANHTIEN) VALUES ('KB001', 'DV002', 1, 700000);
INSERT INTO THUPHI (MAKB, MADV, SOLUONG, THANHTIEN) VALUES ('KB002', 'DV002', 1, 700000);
INSERT INTO THUPHI (MAKB, MADV, SOLUONG, THANHTIEN) VALUES ('KB002', 'DV003', 2, 1600000);
INSERT INTO THUPHI (MAKB, MADV, SOLUONG, THANHTIEN) VALUES ('KB003', 'DV001', 2, 1000000);
INSERT INTO THUPHI (MAKB, MADV, SOLUONG, THANHTIEN) VALUES ('KB003', 'DV003', 1, 800000);

SELECT * FROM BACSI;
SELECT * FROM BENHNHAN;
SELECT * FROM KHAMBENH;
SELECT * FROM DICHVU;
SELECT * FROM THUPHI;
DELETE FROM KHAMBENH;
Create database QLCHDoAn_Java
go 
use QLCHDoAn_Java
go
create table NguoiDung(
	TaiKhoan varchar(20) primary key,
	MatKhau varchar(20),
	accRole varchar(20),
)

create table MonAn(
	MaMon varchar(20) primary key,
	TenMon nvarchar(20),
	DonGia int,
	Loai varchar(20),
)

create table KhachHang(
	MaKH varchar(20) primary key,
	TenKH nvarchar(50),
	SDT varchar(20)
)

Create table NhanVien(
	MaNV varchar(20) not null,
	TenNV nvarchar(50),
	GioiTinh varchar(20),
	QueQuan nvarchar(50),
	SDT varchar(20),
	ChucVu varchar(20),
	HSL float,
	primary key(MaNV),
)
--- 10/6

Create table HoaDon(
	MaHD varchar(20) primary key,
	MaNV varchar(20),
	MaKH varchar(20),
	NgayHD date,
	TongTien int,
	TrangThai varchar(20)
	foreign key (MaNV) references NhanVien(MaNV),
	foreign key (MaKH) references KhachHang(MaKH),
)
-- default HoaDon
Create default df_TrangThai_HoaDon
as 'No'
exec sp_bindefault df_TrangThai_HoaDon, 'HoaDon.TrangThai'

-- rule HoaDon
Create rule rule_TrangThai_HoaDon
as @TrangThai = 'Yes' or @TrangThai = 'No'

exec sp_bindrule rule_TrangThai_HoaDon, 'HoaDon.TrangThai'


Create table CTHoaDon(
	MaHD varchar(20) not null,
	MaMon varchar(20) not null,
	SoLuong int,
	primary key (MaHD, MaMon),
	foreign key (MaMon) references MonAn(MaMon),
	foreign key (MaHD) references HoaDon(MaHD),
)
-- rule CTHoaDon
Create rule rule_SoLuong_CTHoaDon
as @SoLuong >=1

exec sp_bindrule rule_SoLuong_CTHoaDon, 'CTHoaDon.SoLuong'

-- trigger insert, update, delete CTHOADON

Create Trigger TG_TongTien_HoaDon_insert on CTHoaDon
for insert, update
as
	Update HoaDon
	Set TongTien = (select sum(MonAn.DonGia*CTHoaDon.SoLuong)
					from CTHoaDon, MonAn, HoaDon
					where CTHoaDon.MaMon = MonAn.MaMon and HoaDon.MaHD = CTHoaDon.MaHD and HoaDon.MaHD = (select MaHD from inserted) 
					group by HoaDon.MaHD)
	Where HoaDon.MaHD = (select MaHD from inserted)

Create Trigger TG_TongTien_HoaDon_delete on CTHoaDon
for delete
as
	Update HoaDon
	Set TongTien = (select sum(MonAn.DonGia*CTHoaDon.SoLuong)
					from CTHoaDon, MonAn, HoaDon
					where CTHoaDon.MaMon = MonAn.MaMon and HoaDon.MaHD = CTHoaDon.MaHD and HoaDon.MaHD = (select MaHD from deleted) 
					group by HoaDon.MaHD)
	Where HoaDon.MaHD = (select MaHD from deleted)


drop trigger TG_TongTien_HoaDon_insert

-- 
-- Nguoi Dung
insert into NguoiDung values('admin', 'admin', 'admin')
insert into NguoiDung values('nv_01', '123456', 'nv')

select count(*) from nguoidung where taikhoan = 'admin' 
-- MonAn
insert into MonAn values ('MA01', N'Bánh Mì', 20000, 'DoAn'),
							('MA02', N'Cơm Trắng', 20000, 'DoAn'),
							('MA03', N'Thịt Hổ', 20000, 'DoAn'),
							('MA04', N'Xúc Xíc', 20000, 'DoAn'),
							('MA05', N'Coca Cola', 20000, 'ThucUong'),
							('MA06', N'Trà Sữa', 20000, 'ThucUong')

-- KhachHang
insert into KhachHang values ('KH01', 'Khach 01', 123456789),
							('KH02', 'Khach 02', 34522),
							('KH03', 'Khach 03', 7568657),
							('KH04', 'Khach 04', 1234),
							('KH05', 'Khach 05', 09867),
							('KH06', 'Khach 06', 8343345),
							('TMP', N'Khách Lẻ', null)
							
-- Nhan Vien 
insert into NhanVien values ('NV012', 'Nguyen Van A', 'Nam', 'HN', 123456, 'nv', 1.0),
							('NV02', 'Nguyen Van B', 'Nu',  'HN', 123, 'nv', 1.0),
							('NV99', 'ADMIN', 'Nam', 'HN', 123456, 'admin', 1.5)

insert into HoaDon(MaHD, MaNV, MaKH, NgayHD) values ('HD03', 'NV01', 'KH01', '2021-5-5')

--rule && defalut
-- acc role , chuc vu

create rule NguoiDung_role
as 
	@accRole = 'admin' or @accRole = 'nv'
create default df_NguoiDunng_role
as 'nv'

-- Mon An
create rule rule_LoaiMA
as 
	@Loai = 'ThucAn' or @Loai = 'DoUong'
create default df_LoaiMA
as 'ThucAn'

drop rule rule_LoaiMA
-- tk
exec sp_bindrule NguoiDung_role, 'NguoiDung.accRole'
exec sp_bindefault df_NguoiDunng_role, 'NguoiDung.accRole'

exec sp_unbindrule 'MonAn.Loai'
exec sp_unbindefault 'MonAn.Loai'
-- Mon An

exec sp_bindrule rule_LoaiMA, 'MonAn.Loai'
exec sp_bindefault df_LoaiMA, 'MonAn.Loai'



-- trigger 





--- query
select HoaDon.MaHD, Sum(CTHoaDon.SoLuong * MonAn.DonGia) from hoadon, CTHoaDon, MonAn where HoaDon.MaHD = CTHoaDon.MaHD and CTHoaDon.MaMon = MonAn.MaMon
group by HoaDon.MaHD


-- Test 
select CTHoaDon.MaMon, MonAn.TenMon,MonAn.DonGia, CTHoaDon.SoLuong, Sum(CTHoaDon.SoLuong*MonAn.DonGia) as ThanhTien
from CTHoaDon, MonAn
where CTHoaDon.MaMon = MonAn.MaMon and
		CTHoaDon.MaHD = 'HD01'
group by CTHoaDon.MaHD, CTHoaDon.MaMon, MonAn.TenMon, CTHoaDon.SoLuong,MonAn.DonGia

-- Tong Tien HD

select HoaDon.MaHD, sum(MonAn.DonGia*CTHoaDon.SoLuong)
from CTHoaDon, MonAn, HoaDon
where CTHoaDon.MaMon = MonAn.MaMon and HoaDon.MaHD = CTHoaDon.MaHD and HoaDon.MaHD = 'HD01'
group by HoaDon.MaHD

--
select TenNV, TenKH, NgayHD, TongTien, TrangThai
from HoaDon, NhanVien, KhachHang
where	HoaDon.MaKH = KhachHang.MaKH and NhanVien.MaNV = HoaDon.MaNV
		and HoaDon.MaHD = 'HD01'


-- mon an hoa don 
select MonAn.MaMon, MonAn.TenMon, MonAn.DonGia, MonAn.Loai , CTHoaDon.SoLuong, Sum(CTHoaDon.SoLuong*MonAn.DonGia) as 'Tong'
from HoaDon, CTHoaDon, MonAn
where HoaDon.MaHD = CTHoaDon.MaHD and MonAn.MaMon = CTHoaDon.MaMon and HoaDon.MaHD = 'HD01'
group by HoaDon.MaHD, MonAn.MaMon, MonAn.TenMon, MonAn.DonGia, CTHoaDon.SoLuong, MonAn.Loai




select * from HoaDon order by TrangThai


select HoaDon.MaHD , TenNV, TenKH, NgayHD, TongTien
from HoaDon, NhanVien, KhachHang 
where HoaDon.MaNV = NhanVien.MaNV and HoaDon.MaKH = KhachHang.MaKH and TrangThai = 'Yes'
	and NgayHD >= '01-02-2020' and NgayHD <= GETDATE() +2
order by TongTien DESC

select TOP 10 MonAn.MaMon, MonAn.TenMon, DonGia, SUM(SoLuong) as 'SoLuong', Sum(SoLuong * DonGia) as 'TongTien' 
from MonAn, CTHoaDon, HoaDon
where MonAn.MaMon = CTHoaDon.MaMon and HoaDon.MaHD = CTHoaDon.MaHD
and Hoadon.NgayHD >= CONVERT(date, '01-02-2020') and Hoadon.NgayHD <= GETDATE() +1
group by MonAn.MaMon, MonAn.TenMon, DonGia 
order by SoLuong DESC

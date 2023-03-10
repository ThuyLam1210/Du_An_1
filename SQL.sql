USE [master]
GO
/****** Object:  Database [QuanLy_TourDuLichGreenHouse]    Script Date: 12/21/2022 6:01:06 PM ******/
CREATE DATABASE [QuanLy_TourDuLichGreenHouse]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuanLy_TourDuLichGreenHouse', FILENAME = N'E:\New folder\DuAn1\QuanLy_TourDuLichGreenHouse.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuanLy_TourDuLichGreenHouse_log', FILENAME = N'E:\New folder\DuAn1\QuanLy_TourDuLichGreenHouse_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLy_TourDuLichGreenHouse].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET QUERY_STORE = OFF
GO
USE [QuanLy_TourDuLichGreenHouse]
GO
/****** Object:  Table [dbo].[ChiTietHopDong]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHopDong](
	[MaHDCT] [int] IDENTITY(1,1) NOT NULL,
	[MaHopDong] [varchar](20) NOT NULL,
	[TenKhachHang] [nvarchar](50) NOT NULL,
	[CMND_CCCD] [varchar](13) NULL,
	[SDT] [varchar](13) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHDCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[MaChucVu] [varchar](10) NOT NULL,
	[TenChucVu] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaChucVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DiemThamQuan]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DiemThamQuan](
	[MaDiaDiem] [varchar](10) NOT NULL,
	[TenDiaDiem] [nvarchar](50) NOT NULL,
	[DiaChi] [nvarchar](100) NOT NULL,
	[MoTa] [nvarchar](100) NOT NULL,
	[Tinh] [nvarchar](50) NULL,
	[HinhAnh] [nvarchar](500) NULL,
 CONSTRAINT [PK__DiemTham__F015962A5CB5B81E] PRIMARY KEY CLUSTERED 
(
	[MaDiaDiem] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HopDong]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HopDong](
	[MaHopDong] [varchar](20) NOT NULL,
	[MaVe] [varchar](20) NOT NULL,
	[NgayLapHopDong] [date] NOT NULL,
	[SoLuongKhach] [int] NOT NULL,
	[NoiDungHopDong] [nvarchar](200) NOT NULL,
 CONSTRAINT [PK__HopDong__36DD4342470D3EB2] PRIMARY KEY CLUSTERED 
(
	[MaHopDong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MaKhachHang] [varchar](10) NOT NULL,
	[TenKhachHang] [nvarchar](50) NOT NULL,
	[GioiTinh] [bit] NOT NULL,
	[CMND_CCCD] [varchar](13) NOT NULL,
	[SDT] [varchar](13) NOT NULL,
	[Email] [varchar](30) NOT NULL,
	[DiaChi] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachSan]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachSan](
	[MaKhachSan] [varchar](20) NOT NULL,
	[TenKhachSan] [nvarchar](50) NOT NULL,
	[Gia] [money] NOT NULL,
	[XepHang] [nvarchar](20) NULL,
	[SDT] [varchar](13) NOT NULL,
	[DiaChi] [nvarchar](100) NOT NULL,
	[Tinh] [nvarchar](50) NULL,
	[MoTa] [nvarchar](100) NULL,
 CONSTRAINT [PK__KhachSan__28215093CC57C9E8] PRIMARY KEY CLUSTERED 
(
	[MaKhachSan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LichTrinh]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LichTrinh](
	[MaLichTrinh] [varchar](10) NOT NULL,
	[NgayKhoiHanh] [date] NOT NULL,
	[NgayKetThuc] [date] NOT NULL,
	[NoiXuatPhat] [nvarchar](100) NOT NULL,
	[NoiDen] [nvarchar](100) NOT NULL,
	[TrangThai] [nvarchar](30) NOT NULL,
 CONSTRAINT [PK__LichTrin__32E7201DA4A88C49] PRIMARY KEY CLUSTERED 
(
	[MaLichTrinh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LichTrinhChiTiet]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LichTrinhChiTiet](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MaLichTrinh] [varchar](10) NOT NULL,
	[MaTour] [varchar](20) NOT NULL,
	[MocThoiGian] [datetime] NOT NULL,
	[NoiThamQuan] [nvarchar](100) NOT NULL,
	[GhiChu] [nvarchar](100) NULL,
 CONSTRAINT [PK_LichTrinhChiTiet_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiPhuongTien]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiPhuongTien](
	[MaLoaiPT] [varchar](10) NOT NULL,
	[TenLoaiPhuongTien] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLoaiPT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNhanVien] [varchar](10) NOT NULL,
	[MaChucVu] [varchar](10) NOT NULL,
	[TenNhanVien] [nvarchar](50) NOT NULL,
	[GioiTinh] [bit] NOT NULL,
	[TrangThai] [bit] NOT NULL,
	[CMND_CCCD] [varchar](13) NOT NULL,
	[SDT] [varchar](13) NOT NULL,
	[Email] [varchar](30) NOT NULL,
	[DiaChi] [nvarchar](100) NOT NULL,
	[HinhAnh] [nvarchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhuongTien]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhuongTien](
	[MaPhuongTien] [varchar](10) NOT NULL,
	[MaLoaiPhuongTien] [varchar](10) NOT NULL,
	[TenPhuongTien] [nvarchar](50) NOT NULL,
	[CuocPhi] [money] NOT NULL,
	[BienSo] [varchar](15) NOT NULL,
	[SoLuongChua] [int] NOT NULL,
	[GhiChu] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaPhuongTien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SetDiemThamQuan]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SetDiemThamQuan](
	[MaDiaDiem] [varchar](10) NOT NULL,
	[MaTour] [varchar](20) NOT NULL,
 CONSTRAINT [PK__SetDiemT__04F0C357EDCAAD48] PRIMARY KEY CLUSTERED 
(
	[MaDiaDiem] ASC,
	[MaTour] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SetKhachSan]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SetKhachSan](
	[MaKhachSan] [varchar](20) NOT NULL,
	[MaTour] [varchar](20) NOT NULL,
 CONSTRAINT [PK__SetKhach__DCC405EEDC566C47] PRIMARY KEY CLUSTERED 
(
	[MaKhachSan] ASC,
	[MaTour] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SetPhuongTien]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SetPhuongTien](
	[MaPhuongTien] [varchar](10) NOT NULL,
	[MaTour] [varchar](20) NOT NULL,
 CONSTRAINT [PK__SetPhuon__C1539DCDA11410F0] PRIMARY KEY CLUSTERED 
(
	[MaPhuongTien] ASC,
	[MaTour] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[TenDangNhap] [varchar](30) NOT NULL,
	[MatKhau] [varchar](20) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[QuyenTruyCap] [nvarchar](20) NULL,
	[MaNhanVien] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tour]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tour](
	[MaTour] [varchar](20) NOT NULL,
	[TenTour] [nvarchar](50) NOT NULL,
	[SoNgay] [int] NOT NULL,
	[SoDem] [int] NOT NULL,
	[GiaTour] [money] NOT NULL,
	[SoLuongKhach] [int] NOT NULL,
	[HinhAnh] [nvarchar](500) NULL,
 CONSTRAINT [PK__Tour__4E5557DE16AAF35E] PRIMARY KEY CLUSTERED 
(
	[MaTour] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ve]    Script Date: 12/21/2022 6:01:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ve](
	[MaVe] [varchar](20) NOT NULL,
	[MaTour] [varchar](20) NULL,
	[MaNhanVien] [varchar](10) NOT NULL,
	[MaKhachHang] [varchar](10) NOT NULL,
	[MaLichTrinh] [varchar](10) NOT NULL,
	[SoLuongVeNguoiLon] [int] NULL,
	[SoLuongVeTreEm] [int] NULL,
	[TongTien] [money] NOT NULL,
	[NgayBan] [date] NOT NULL,
 CONSTRAINT [PK__Ve__2725100F041682AC] PRIMARY KEY CLUSTERED 
(
	[MaVe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[ChiTietHopDong] ON 

INSERT [dbo].[ChiTietHopDong] ([MaHDCT], [MaHopDong], [TenKhachHang], [CMND_CCCD], [SDT]) VALUES (7, N'HD19122022560', N'Lâm Diễm Thúy', N'098192819212', N'0886077296')
INSERT [dbo].[ChiTietHopDong] ([MaHDCT], [MaHopDong], [TenKhachHang], [CMND_CCCD], [SDT]) VALUES (15, N'HD19122022309', N'Hồ Thị Vân Anh', N'098890991911', N'0886077296')
SET IDENTITY_INSERT [dbo].[ChiTietHopDong] OFF
GO
INSERT [dbo].[ChucVu] ([MaChucVu], [TenChucVu]) VALUES (N'CV01', N'Quản Lý')
INSERT [dbo].[ChucVu] ([MaChucVu], [TenChucVu]) VALUES (N'CV02', N'Nhân Viên Trực Quầy')
INSERT [dbo].[ChucVu] ([MaChucVu], [TenChucVu]) VALUES (N'CV03', N'Hướng Dẫn viên')
GO
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD012819', N'Hòn Tằm', N'trung tâm thành phố Nha Trang', N'', N'Tỉnh Khánh Hòa', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD01818', N'Rừng Quốc Gia U Minh Thượng', N'Huyện U Minh Thượng', N'', N'Tỉnh Kiên Giang', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD01827', N'Mũi Cà Mau', N'xóm Mũi, xã Đất Mũi, huyện Ngọc Hiển', N'Cực Nam Của Tổ quốc', N'Tỉnh Cà Mau', N'hinh1.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD018271', N'Rừng quốc gia U Minh Hạ', N'Huyện Ngọc Hiển', N'', N'Tỉnh Cà Mau', N'hinh1.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD01901', N'Hòn Đá Bạc', N'Huyện Hòn Đất', N'aaa', N'Tỉnh Kiên Giang', N'0510_hinh_nen_phong_canh16.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD019121', N'Hòn Vợ, Hòn Chồng', N'trung tâm TP Nha Trang', N'', N'Tỉnh Khánh Hòa', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD01918', N'Côn Đảo', N'nằm cách đất liền của Vũng Tàu khoảng 185km', N'', N'Tỉnh Bà Rịa Vũng Tàu', N'hinh1.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD0192', N'Tháp Bà Ponagar', N'đường 2/4 ven sông Cái, TP. Nha Trang', N'', N'Tỉnh Khánh Hòa', N'0510_hinh_nen_phong_canh17.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD01921', N'Đảo Phú Quốc', N'TP.Phú Quốc', N'ok', N'Tỉnh Kiên Giang', N'0510_hinh_nen_phong_canh4.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD01927', N'Hòn Đá Bạc', N'xã Khánh Bình Tây, huyện Trần Văn Thời', N'nơi lưu giữ những giá trị lịch sử của dân tộc và được xếp hạng là Di tích Lịch sử Quốc gia.', N'Tỉnh Cà Mau', N'hinh1.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD02112', N'Đảo Điệp Sơn', N'thuộc vịnh Vân Phong', N'một trong những quần đảo đẹp nhất ở Nha Trang mà bạn nhất định phải ghé đến.', N'Tỉnh Khánh Hòa', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD0233', N'Biển Long Hải', N' Huyện Long Điền tỉnh Bà Rịa Vũng Tàu', N'Bãi biển còn nhiều nét hoang sơ, sạch sẽ, bãi biển thoai thoải', N'Tỉnh Bà Rịa Vũng Tàu', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD0234', N' Đảo Titop', N' Vịnh Hạ Long, tỉnh Quảng Ninh', N'Phong cảnh thiên nhiên tươi đẹp', N'Tỉnh Quảng Ninh', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD0235', N'Cổng Trời SaPa', N'8RMX+Q5P, TT. Sa Pa, Sa Pa, Lào Cai', N'Top 10 địa điểm du lịch check – in “cực chất” ở Sapa', N'TP. Lào Cai', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD02817', N'Biển Hồ Tràm', N'Huyện Xuyên Mộc', N'', N'Tỉnh Bà Rịa Vũng Tàu', N'hinh1.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD02819', N'Bãi Tắm Ba Trái Đào', N'gần đảo Cát Bà', N'', N'Tỉnh Quảng Ninh', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD02938', N'Núi Bài Thơ', N'ở trung tâm Thành phố Hạ Long,', N'', N'Tỉnh Quảng Ninh', N'hinh1.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD03818', N'Bản Tả Phìn', N'thuộc huyện Sapa', N'', N'Tỉnh Lào Cai', N'hinh1.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD03819', N'Cổng trời Sapa', N'Thị xã Sapa', N'', N'Tỉnh Lào Cai', N'hinh1.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD03928', N'Fansipan Legend', N'Phía Tây Nam,Thị Trấn Sapa', N'', N'Tỉnh Lào Cai', N'hinh1.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD1001', N'Bến Ninh Kiều', N'Phường Tân An, Quận Ninh Kiều, TP.Cần Thơ', N'Đẹp xuất sắc', N'TP. Cần Thơ', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD1010', N'Vịnh Hạ Long', N'TP.Hạ Long, Tỉnh Quảng Ninh', N'Đẹp xuất sắc', N'TP. Quảng Ninh', N'0510_hinh_nen_phong_canh4.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD1099', N'Thung Lũng Tình Yêu Đà Lạt', N'Số 05- 07 Mai Anh Đào, phường 8, Thành phố Đà Lạt', N'Đẹp xuất sắc', N'TP. Đà Lạt', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD1111', N'Phố cỗ', N'Trung tâm thành phố Hà Nội', N'Đẹp xuất sắc', N'TP. Hà Nội', N'null')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD12001', N'Chùa một cột', N'Huyện Hai Bà Trưng', N'đẹp lắm Hà Nội ơi', N'TP. Hà Nội', N'0510_hinh_nen_phong_canh16.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD1211', N'Phố Cổ Hội An', N'Hạ lưu sông Thu Bồn, Tỉnh Quảng Nam', N'Đẹp xuất sắc', N'TP. Quảng Nam', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD1234', N'Cầu Mỹ Thuận', N'Quốc lộ 1A', N'cầu dây văng đầu tiên tại Việt Nam', N'TP.Vĩnh Long', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD1312', N'Dinh Độc Lập', N'135/Nam Kì Khởi Nghĩa, Bến Nghé, Quận 1, TP.HCM', N'Đẹp xuất sắc', N'TP. HCM', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD2141', N'Chợ nổi Trà Ôn', N'dòng sông Hậu', N' chợ nổi Trà Ôn bắt đầu nhộn nhịp từ tờ mờ sáng.', N'TP.Vĩnh Long', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD32002', N'Cầu Cần Thơ', N'Quận Cái Răng, Cần Thơ và thị xã Bình Minh, Vĩnh Long.', N'Cầu Cần Thơ nắm giữ kỉ lục là cây cầu có nhịp chính dài nhất Đông Nam Á.', N'TP. Cần Thơ', N'')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD32003', N'Làng du lịch Mỹ Khánh', N'Số 335, Lộ Vòng Cung, Mỹ Khánh, Phong Điền, Cần Thơ.', N'là một điểm du lịch sinh thái không chỉ nổi tiếng của Cần Thơ mà cả khu vực miền Tây Nam Bộ.', N'TP. Cần Thơ', N'')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD32004', N'Chợ nổi Cái Răng', N'Quận Cái Răng, Cần Thơ.', N'là khu chợ sầm uất, tiêu biểu nhất, đã được tạp chí du lịch Rough Guide của Anh Quốc.', N'TP. Cần Thơ', N'')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD32005', N'Thác Datanla', N'đèo Prenn, phường 3, tỉnh Lâm Đồng, thành phố Đà Lạt', N'nằm gọn trên con đèo Prenn, với độ cao 20m nhưng lại nằm ở thượng nguồn dòng chảy.', N'TP. Đà Lạt', N'')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD32006', N'Vườn Thú ZooDoo', N'xã Đa Nhim, huyện Lạc Dương, tỉnh Lâm Đồng, thành phố Đà Lạt', N'ZooDoo được bao quanh bởi rừng thông yên tĩnh và tươi mát', N'TP. Đà Lạt', N'')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD32007', N'Núi Langbiang', N'phường 8, tỉnh Lâm Đồng, thành phố Lâm Đồng', N' địa điểm mới khai trương, với phong cách khác hẳn với vẻ xưa cổ thường thấy tại Đà Lạt', N'TP. Đà Lạt', N'')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD32008', N'Bà Nà Hills', N'Thôn An Sơn, xã Hòa Ninh, huyện Hòa Vang, TP. Đà Nẵng.', N'mệnh danh là đường lên tiên cảnh – điểm đến thu hút nhất khi khách đến du lịch Đà Nẵng.', N'TP. Đà Nẵng', N'')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD32009', N'Công viên nước Mikazuki Đà Nẵng', N'Bãi biển Xuân Thiều, đường Nguyễn Tất Thành, Thành Phố Đà Nẵng.', N'hệ thống nước nóng quanh năm cho du khách thỏa sức vui chơi bất kể nắng mưa.', N'TP. Đà Nẵng', N'')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD32010', N'Hồ Gươm', N'Quận Hoàn Kiếm, Hà Nội.', N'trung tâm của thành phố Hà Nội, Hồ gươm Tháp rùa là biểu tượng của Hà Nội.', N'TP. Hà Nội', N'')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD32011', N'Cầu long biên', N'Quận Ba Đình, Hà Nội.', N' Từ lâu nó đã trở thành một địa điểm rất quen thuộc với người dân thủ đô.', N'TP. Hà Nội', N'')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD32012', N'Hồ Tây', N'Quận Tây Hồ, Hà Nội.', N'mệnh danh là địa điểm ăn ớt nhất của giới trẻ Hà Nội,', N'TP. Hà Nội', N'')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD3919', N'Bến Nhà Rồng', N'Đường Nguyễn Tất Thành, Phường 12, Quận 4,TP.HCM', N'Đẹp xuất sắc', N'TP. HCM', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD4521', N'Nhà cổ cai Cường', N' xã Bình Hòa Phước, Long Hồ, Vĩnh Long', N'không gian nhà xưa đặc trưng Nam Bộ', N'TP.Vĩnh Long', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD6181', N'Lạc Tiên Giới Đà Lạt', N'Số 1/3, đường Lâm Sinh, Phường 5 thành phố Đà Lạt', N'Đẹp xuất sắc', N'TP. Đà Lạt', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD654322', N'Công Viên Sông Hậu', N'Quận cái răng', N'đfsss', N'Tỉnh Cần Thơ', N'0510_hinh_nen_phong_canh1-2.jpg')
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD8011', N'Khu Du Lịch Đại Nam', N'Thủ Dầu Một', N'Rộng', N'TP.Bình Dương', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD8101', N'Hồ Dầu Tiếng', N'P.Thủ Dầu Một,', N'Thơ mộng', N'TP.Bình Dương', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD9181', N'Khu Du Lịch Thủy Châu', N'TX.Dĩ An', N'Quá Đẹp', N'TP.Bình Dương', NULL)
INSERT [dbo].[DiemThamQuan] ([MaDiaDiem], [TenDiaDiem], [DiaChi], [MoTa], [Tinh], [HinhAnh]) VALUES (N'DD9911', N'Bãi Biển Mỹ Khê', N'Phường Phước Mỹ, quận Sơn Trà, TP.Đà Nẵng', N'Đẹp xuất sắc', N'TP. Đà Nẵng', NULL)
GO
INSERT [dbo].[HopDong] ([MaHopDong], [MaVe], [NgayLapHopDong], [SoLuongKhach], [NoiDungHopDong]) VALUES (N'HD19122022174', N'VE191220225393', CAST(N'2022-12-19' AS Date), 1, N'Duyệt')
INSERT [dbo].[HopDong] ([MaHopDong], [MaVe], [NgayLapHopDong], [SoLuongKhach], [NoiDungHopDong]) VALUES (N'HD19122022309', N'VE191220224698', CAST(N'2022-12-19' AS Date), 2, N'Ok')
INSERT [dbo].[HopDong] ([MaHopDong], [MaVe], [NgayLapHopDong], [SoLuongKhach], [NoiDungHopDong]) VALUES (N'HD19122022560', N'VE191220227947', CAST(N'2022-12-19' AS Date), 2, N'OK')
INSERT [dbo].[HopDong] ([MaHopDong], [MaVe], [NgayLapHopDong], [SoLuongKhach], [NoiDungHopDong]) VALUES (N'HD19122022940', N'VE191220227687', CAST(N'2022-12-19' AS Date), 1, N'OK')
GO
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH00013', N'Lâm Diễm Thúy', 0, N'098092819201', N'0909090908', N'thutld12@gmail.com', N'Bạc Liêu')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH0102', N'Phạm Như Băng', 0, N'908990182901', N'0887988299', N'bangph12@gmail.com', N'Bạc Liêu')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH0120', N'Châu Hoàng Khải', 1, N'090109029092', N'0877391891', N'khaihc3@gmail.com', N'Bạc Liêu')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH015', N'dat', 1, N'799198917244', N'0887628994', N'anth189@gmail.com', N'Cà Mau')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH017', N'Lý Ngọc Ma', 0, N'386980109909', N'0763098192', N'ngocml22@gmail.com', N'Vĩnh Long')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH018', N'Thái Từ Khôn', 1, N'090190909102', N'0833081792', N'thaitt22@gmail.com', N'Sóc Trăng')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH0182', N'Trần Huyền Trân', 0, N'009909098921', N'0889799281', N'tranj23@gmail.com', N'Bạc Liêu')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH021', N'Trương Thúy Hằng', 0, N'309901901920', N'0880081091', N'thuyys19@gmail.com', N'Cà Mau')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH0819', N'Trần Kim Tuyền', 0, N'908172910920', N'0885266356', N'kimhn19@gmail.com', N'Kiên Giang')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH088', N'Phạm Xuân Vinh', 1, N'091091092019', N'0886077255', N'vinhpx23@gmail.com', N'Cần Thơ')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH10291', N'Võ Hoàng Gia Bảo', 1, N'385791100', N'0887281919', N'baovh18@gmail.com', N'Kiên Giang')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH123456', N'Trần Văn Thiện', 1, N'012332785411', N'0909090909', N'thien123@gmail.com', N'Cần Thơ')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH303001', N'Nguyễn Hùng Vĩ', 1, N'385901661234', N'0326360127', N'kietna44@fpt.edu.vn', N'Bạc Liêu')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH303002', N'Trần Trung Tính', 1, N'384902491234', N'0928768265', N'tuanmt2331@fpt.edu.vn', N'Cà Mau')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH303003', N'Lữ Huy Cường', 1, N'221443112828', N'0968095685', N'phongnh3@fpt.edu.vn', N'Bình Dương')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH303004', N'Nguyễn Hữu Trí', 1, N'385473222231', N'0927594737', N'ngoclt3@fpt.edu.vn', N'Cần Thơ')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH303005', N'Nguyễn Nam', 1, N'333812456211', N'0946984711', N'namn081@gmail.com', N'Sóc Trăng')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH303006', N'Thái Thị Tiền', 0, N'333812456896', N'0946984721', N'tienthai12@gmail.com', N'Sóc Trăng')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH303007', N'Nguyễn Huỳnh Như Ngọc', 0, N'331812456767', N'0946984982', N'nhungoc91@gmail.com', N'Tiền Giang')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH303008', N'Nguyễn Đoàn Quân', 1, N'333812428887', N'0946914211', N'duquan91@gmail.com', N'Kiên Giang')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH303009', N'Đoàn Tuyết Mai', 0, N'333812428767', N'0946914711', N'maidoan91@gmail.com', N'Tiền Giang')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH919', N'Trần Thảo Như', 0, N'998728198223', N'0887281982', N'nhugaa44@fpt.edu.vn', N'CẦN THƠ')
INSERT [dbo].[KhachHang] ([MaKhachHang], [TenKhachHang], [GioiTinh], [CMND_CCCD], [SDT], [Email], [DiaChi]) VALUES (N'KH990', N'Trần Ngọc', 1, N'902009190191', N'0886077889', N'tranhh2@gmail.com', N'Thanh Hóa')
GO
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS0001', N'Khách Sạn Sài Gòn Rạch Giá', 900000.0000, N'4 Sao', N'0888719182', N'An Hóa', N'Tỉnh Kiên Giang', N'View Đẹp ok')
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS010', N'Hotel Mường Thanh', 1100000.0000, N'4 sao', N'0982911444', N'Phường 5', N'Tỉnh Cà Mau', N'View Đẹp')
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS011', N'The Mira Hotel', 100.0000, N'5 sao', N'0982911814', N'Thủ Dầu Một', N'T.Bình Dương', N'null')
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS01929', N'Nipola Hotel', 2000000.0000, N'4 Sao', N'0888112121', N'Dương Đông', N'Tỉnh Phú QUốc', N'Nhiều Cảnh Đẹp')
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS02', N'Khách Sạn Lotte', 1600000.0000, N'3 sao', N'0982911818', N'Quận 1', N'HCM', N'1j')
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS029', N'LeMore Hotel Nha Trang', 1200000.0000, N'4 Sao', N'0886077296', N'Tọa lạc tại thành phố Nha Trang, cách Bãi biển Nha Trang 500 m', N'Tỉnh Khánh Hòa', N'')
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS03', N'Khách Sạn House', 1400000.0000, N'4 sao', N'0982911817', N'Quận Sơn Trà', N'Tp.Đà Nẵng', N'Ok lém')
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS04', N'Khách Sạn Đông Nam Á', 1540000.0000, N'4 sao', N'0982911816', N'Tỉnh Quảng Ninh', N'Tỉnh Quảng Ninh', N'Ok lém')
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS05', N'Khách Sạn Nhà Xanh', 1450000.0000, N'3 sao', N'0982911417', N'Tp.Đà Lạt', N'Tỉnh Lâm Đồng', N'Ok lém')
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS06', N'Khách Sạn Ánh Sao', 1900000.0000, N'3 sao', N'0982911845', N'Quận Tây Hồ', N'TP.Hà Nội', NULL)
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS07', N'Khách Sạn Sol Beach House', 2000000.0000, N'5 sao', N'0982911842', N'Khu 1, khu du lịch Đức Việt - khu liên hợp Bãi Trường, xã Dương Tơ, huyện Phú Quốc', N'Tỉnh Kiên Giang', NULL)
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS08', N'Melia Hồ Tràm Beach Resort', 1500000.0000, N'5 sao', N'0982911888', N'Hồ Tràm,huyện Xuyên Mộc', N'Tỉnh Vũng Tàu', N'Ok lém')
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS081', N'Danang Golden Bay', 5000000.0000, N'5 sao', N'0829198991', N'Lê Văn Duyệt, Sơn Trà', N'TP Đà Nẵng', N'View bể bơi đẹp')
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS09', N'KK Hotel Sapa', 1330000.0000, N'5 sao', N'09821442222', N'Thị Xã Sapa', N'Tỉnh Lào Cai', N'Ok lém')
INSERT [dbo].[KhachSan] ([MaKhachSan], [TenKhachSan], [Gia], [XepHang], [SDT], [DiaChi], [Tinh], [MoTa]) VALUES (N'KS65312', N'Vinpearl Hotel', 5000000.0000, N'5 sao', N'0394847364', N'Quận Ninh Kiều', N'TP. Cần Thơ', N'abc')
GO
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00001', CAST(N'2018-10-15' AS Date), CAST(N'2018-10-20' AS Date), N'Cần Thơ', N'Bình Dương', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00002', CAST(N'2018-11-01' AS Date), CAST(N'2018-11-07' AS Date), N'Cần Thơ', N'Cà Mau', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00003', CAST(N'2019-12-01' AS Date), CAST(N'2019-12-04' AS Date), N'Cần Thơ', N'Đà Lạt', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00004', CAST(N'2019-02-02' AS Date), CAST(N'2019-02-07' AS Date), N'Cần Thơ', N'Đà Nẵng', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00005', CAST(N'2019-03-04' AS Date), CAST(N'2019-03-09' AS Date), N'Cần Thơ', N'Vịnh Hạ Long', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00006', CAST(N'2019-04-03' AS Date), CAST(N'2019-04-10' AS Date), N'Cần Thơ', N'Hà Nội', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00007', CAST(N'2019-04-05' AS Date), CAST(N'2019-04-10' AS Date), N'Cần Thơ', N'Kiên Giang', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00008', CAST(N'2022-11-10' AS Date), CAST(N'2022-11-15' AS Date), N'Cần Thơ', N'Nha Trang', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00009', CAST(N'2020-03-10' AS Date), CAST(N'2020-03-14' AS Date), N'Cần Thơ', N'Phú Quốc', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00010', CAST(N'2020-10-12' AS Date), CAST(N'2020-10-16' AS Date), N'Cần Thơ', N'Quảng Nam', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00011', CAST(N'2021-06-17' AS Date), CAST(N'2021-06-21' AS Date), N'Cần Thơ', N'Hồ Chí Minh', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00012', CAST(N'2022-12-06' AS Date), CAST(N'2022-12-13' AS Date), N'Cần Thơ', N'SaPa', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00013', CAST(N'2022-08-10' AS Date), CAST(N'2022-08-14' AS Date), N'Cần Thơ', N'Vĩnh Long', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00014', CAST(N'2023-01-01' AS Date), CAST(N'2023-01-06' AS Date), N'Cần Thơ', N'Vũng Tàu', N'Chưa Khởi Hành')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00015', CAST(N'2017-06-10' AS Date), CAST(N'2017-06-17' AS Date), N'Cần Thơ', N'Sapa', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT00183', CAST(N'2022-12-21' AS Date), CAST(N'2022-12-26' AS Date), N'Cần Thơ', N'Đà Nẵng', N'Chưa Khởi Hành')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT01826', CAST(N'2022-12-21' AS Date), CAST(N'2022-12-26' AS Date), N'Cần Thơ', N'Bình Dương', N'Chưa Khởi Hành')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT0192', CAST(N'2022-12-21' AS Date), CAST(N'2022-12-26' AS Date), N'Cần Thơ', N'Bình Dương', N'Chưa Khởi Hành')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT122022', CAST(N'2022-12-20' AS Date), CAST(N'2022-12-22' AS Date), N'Cần Thơ', N'Vịnh Hạ Long', N'Chưa Khởi Hành')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT1234', CAST(N'2022-12-02' AS Date), CAST(N'2022-12-05' AS Date), N'Cần Thơ', N'Hà Nội', N'Kết thúc')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT918', CAST(N'2022-12-23' AS Date), CAST(N'2022-12-28' AS Date), N'Cần Thơ', N'Bình Dương', N'Chưa Khởi Hành')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT9182', CAST(N'2022-12-20' AS Date), CAST(N'2022-12-25' AS Date), N'Cần Thơ', N'Bình Dương', N'Chưa Khởi Hành')
INSERT [dbo].[LichTrinh] ([MaLichTrinh], [NgayKhoiHanh], [NgayKetThuc], [NoiXuatPhat], [NoiDen], [TrangThai]) VALUES (N'LT91822', CAST(N'2022-12-20' AS Date), CAST(N'2022-12-25' AS Date), N'Cần Thơ', N'Bình Dương', N'Chưa Khởi Hành')
GO
SET IDENTITY_INSERT [dbo].[LichTrinhChiTiet] ON 

INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (128, N'LT00001', N'CTBD5545', CAST(N'2018-10-16T08:00:00.000' AS DateTime), N'Khu Du Lịch Đại Nam', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (131, N'LT00001', N'CTBD5545', CAST(N'2018-10-17T07:00:00.000' AS DateTime), N'Hồ Dầu Tiếng', N'Có thể xuất phát trễ do thiếu người')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (132, N'LT00001', N'CTBD5545', CAST(N'2018-10-19T07:00:00.000' AS DateTime), N'Khu Du Lịch Thủy Châu', N'Có thể xuất phát sớm')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (133, N'LT00002', N'CTCM4070', CAST(N'2018-11-01T05:00:00.000' AS DateTime), N'Mũi Cà Mau', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (134, N'LT00002', N'CTCM4070', CAST(N'2018-11-03T07:00:00.000' AS DateTime), N'Rừng quốc gia U Minh Hạ', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (135, N'LT00002', N'CTCM4070', CAST(N'2018-11-05T07:00:00.000' AS DateTime), N'Hòn Đá Bạc', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (136, N'LT00003', N'CTDL3329', CAST(N'2019-12-01T05:00:00.000' AS DateTime), N'Thung Lũng Tình Yêu Đà Lạt', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (137, N'LT00003', N'CTDL3329', CAST(N'2019-12-01T12:00:00.000' AS DateTime), N'Thác Datanla', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (138, N'LT00003', N'CTDL3329', CAST(N'2019-12-02T07:00:00.000' AS DateTime), N'Vườn Thú ZooDoo', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (139, N'LT00003', N'CTDL3329', CAST(N'2019-12-02T11:00:00.000' AS DateTime), N'Núi Langbiang', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (140, N'LT00003', N'CTDL3329', CAST(N'2019-12-03T07:00:00.000' AS DateTime), N'Lạc Tiên Giới Đà Lạt', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (141, N'LT00004', N'CTDN5450', CAST(N'2019-02-02T06:00:00.000' AS DateTime), N'Bà Nà Hills', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (142, N'LT00004', N'CTDN5450', CAST(N'2019-02-04T06:00:00.000' AS DateTime), N'Công viên nước Mikazuki Đà Nẵng', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (143, N'LT00004', N'CTDN5450', CAST(N'2019-02-06T06:00:00.000' AS DateTime), N'Bãi Biển Mỹ Khê', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (144, N'LT00005', N'CTHL5530', CAST(N'2019-03-04T06:00:00.000' AS DateTime), N' Đảo Titop', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (145, N'LT00005', N'CTHL5530', CAST(N'2019-03-06T06:00:00.000' AS DateTime), N'Bãi Tắm Ba Trái Đào', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (146, N'LT00005', N'CTHL5530', CAST(N'2019-03-08T06:00:00.000' AS DateTime), N'Bãi Tắm Ba Trái Đào', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (147, N'LT00006', N'CTHN7650', CAST(N'2019-04-03T06:00:00.000' AS DateTime), N'Hồ Hoàng Kiếm', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (148, N'LT00006', N'CTHN7650', CAST(N'2019-04-05T06:00:00.000' AS DateTime), N'Hồ Gươm', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (149, N'LT00006', N'CTHN7650', CAST(N'2019-04-06T06:00:00.000' AS DateTime), N'Cầu long biên', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (150, N'LT00006', N'CTHN7650', CAST(N'2019-04-08T06:00:00.000' AS DateTime), N'Hồ Tây', N'Có thể xuất sớm hơn dự kiến')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (151, N'LT00007', N'CTKG040540', CAST(N'2019-04-05T06:00:00.000' AS DateTime), N'Rừng Quốc Gia U Minh Thượng', N'Có thể xuất phát trễ hơn dự kiếm')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (152, N'LT00007', N'CTKG040540', CAST(N'2019-04-07T06:00:00.000' AS DateTime), N'Hòn Đá Bạc', N'Có thể xuất phát sớm hơn dự kiếm')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (153, N'LT00007', N'CTKG040540', CAST(N'2019-04-09T06:00:00.000' AS DateTime), N'Đảo Phú Quốc', N'Có thể xuất phát sớm hơn dự kiếm')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (155, N'LT00008', N'CTNT5670', CAST(N'2019-11-10T06:00:00.000' AS DateTime), N'Hòn Tằm', N'Có thể xuất phát trễ ')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (158, N'LT00008', N'CTNT5670', CAST(N'2019-11-12T06:00:00.000' AS DateTime), N'Hòn Vợ, Hòn Chồng', N'Đúng giờ xuất phát')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (159, N'LT00008', N'CTNT5670', CAST(N'2019-11-13T07:00:00.000' AS DateTime), N'Tháp Bà Ponagar', N'Đúng giờ xuất phát')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (161, N'LT00008', N'CTNT5670', CAST(N'2019-11-14T08:00:00.000' AS DateTime), N'Đảo Điệp Sơn', N'Có thể xuất phát trễ')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (162, N'LT00009', N'CTPQ4340', CAST(N'2020-03-10T03:20:00.000' AS DateTime), N'Rừng Quốc Gia U Minh Thượng', N'Đúng giờ xuất phát')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (167, N'LT00009', N'CTPQ4340', CAST(N'2020-03-11T07:00:00.000' AS DateTime), N'Hòn Đá Bạc', N'Đúng giờ xuất phát')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (168, N'LT00009', N'CTPQ4340', CAST(N'2020-03-13T08:00:00.000' AS DateTime), N'Đảo Phú Quốc', N'Có thể xuất phát trễ')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (169, N'LT00010', N'CTQN4460', CAST(N'2020-10-12T07:00:00.000' AS DateTime), N'Phố Cổ Hội An', N'Đúng giờ xuất phát')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (170, N'LT00011', N'CTSG2250', CAST(N'2021-06-17T07:00:00.000' AS DateTime), N'Dinh Độc Lập', N'Đúng giờ xuất phát')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (171, N'LT00011', N'CTSG2250', CAST(N'2021-06-19T08:00:00.000' AS DateTime), N'Bến Nhà Rồng', N'Xuất phát trễ')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (172, N'LT00012', N'CTSP7790', CAST(N'2022-12-06T15:00:00.000' AS DateTime), N'Cổng Trời SaPa', N'Đi sớm')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (173, N'LT00013', N'CTVL030450', CAST(N'2022-10-08T09:30:00.000' AS DateTime), N'Cầu Mỹ Thuận', N'Đi sớm')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (176, N'LT00013', N'CTVL030450', CAST(N'2022-10-09T00:00:00.000' AS DateTime), N'Chợ nổi Trà Ôn', N'Đi trễ hơn 10 phút')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (177, N'LT00013', N'CTVL030450', CAST(N'2022-10-10T10:00:00.000' AS DateTime), N'Nhà cổ cai Cường', N'Đi sớm hơn 1 giờ')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (178, N'LT00014', N'CTVT4540', CAST(N'2023-01-01T06:00:00.000' AS DateTime), N'Côn Đảo', N'Đúng giờ xuất phát')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (179, N'LT00014', N'CTVT4540', CAST(N'2023-01-03T09:00:00.000' AS DateTime), N'Biển Long Hải', N'Đúng giờ xuất phát')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (180, N'LT00014', N'CTVT4540', CAST(N'2023-01-05T09:00:00.000' AS DateTime), N'Biển Hồ Tràm', N'Đúng giờ xuất phát')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (184, N'LT00183', N'CTDN5450', CAST(N'2022-12-22T11:36:38.000' AS DateTime), N'Công viên nước Mikazuki Đà Nẵng', N'')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (186, N'LT918', N'CTBD5545', CAST(N'2022-12-24T11:44:43.000' AS DateTime), N'Biển Long Hải', N'')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (187, N'LT9182', N'CTBD5545', CAST(N'2022-12-22T11:47:49.000' AS DateTime), N'Công Viên Sông Hậu', N'ok')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (188, N'LT0192', N'CTBD5545', CAST(N'2022-12-22T10:54:14.000' AS DateTime), N'Khu Du Lịch Đại Nam', N'')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (189, N'LT0192', N'CTBD5545', CAST(N'2022-12-23T08:54:14.000' AS DateTime), N'Khu Du Lịch Thủy Châu', N'')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (190, N'LT0192', N'CTBD5545', CAST(N'2022-12-24T08:54:14.000' AS DateTime), N'Hồ Dầu Tiếng', N'')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (191, N'LT122022', N'CTHL2120', CAST(N'2022-12-20T18:49:02.000' AS DateTime), N'Hồ Gươm', N'eee')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (192, N'LT122022', N'CTHL2120', CAST(N'2022-12-21T18:49:02.000' AS DateTime), N'Cầu long biên', N'eee')
INSERT [dbo].[LichTrinhChiTiet] ([ID], [MaLichTrinh], [MaTour], [MocThoiGian], [NoiThamQuan], [GhiChu]) VALUES (193, N'LT122022', N'CTHL2120', CAST(N'2022-12-21T00:00:00.000' AS DateTime), N'Hồ Gươm', N'eee')
SET IDENTITY_INSERT [dbo].[LichTrinhChiTiet] OFF
GO
INSERT [dbo].[LoaiPhuongTien] ([MaLoaiPT], [TenLoaiPhuongTien]) VALUES (N'PT54001', N'Xe Máy')
INSERT [dbo].[LoaiPhuongTien] ([MaLoaiPT], [TenLoaiPhuongTien]) VALUES (N'PT54002', N'Xe Ô Tô')
INSERT [dbo].[LoaiPhuongTien] ([MaLoaiPT], [TenLoaiPhuongTien]) VALUES (N'PT54003', N'Xe Du Lịch')
INSERT [dbo].[LoaiPhuongTien] ([MaLoaiPT], [TenLoaiPhuongTien]) VALUES (N'PT54004', N'Xe lửa')
INSERT [dbo].[LoaiPhuongTien] ([MaLoaiPT], [TenLoaiPhuongTien]) VALUES (N'PT54005', N'Máy Bay')
INSERT [dbo].[LoaiPhuongTien] ([MaLoaiPT], [TenLoaiPhuongTien]) VALUES (N'PT54006', N'Thuyền')
GO
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV021', N'CV03', N'Bùi Ngọc Hằng', 0, 1, N'092719281721', N'0887587171', N'hangnb18@gmail.com', N'Cần Thơ', NULL)
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV29001', N'CV01', N'Lâm Diễm Thúy', 0, 0, N'366328799234', N'0886081811', N'thuyldpc02874@fpt.edu.vn', N'Ninh Kiều, Cần Thơ', N'hinh2.png')
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV29003', N'CV02', N'Mai Văn Đạt', 1, 0, N'392810829194', N'0352057809', N'datmvpc02991@fpt.edu.vn', N'Ninh Kiều, Cần Thơ', N'0510_hinh_nen_phong_canh1-2.jpg')
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV29004', N'CV02', N'Thái Hoàng An', 1, 0, N'632928384232', N'0856081813', N'datmvpc@gmail.com', N'Ninh Kiều, Cần Thơ', N'0510_hinh_nen_phong_canh3.jpg')
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV29005', N'CV02', N'Nguyễn Minh Thức', 1, 1, N'732928385211', N'0886081823', N'thucmn44@fpt.edu.vn', N'Ninh Kiều, Cần Thơ', N'')
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV29006', N'CV02', N'Nữvbn', 0, 1, N'232928387234', N'0886081884', N'kiet2331@fpt.edu.vn', N'Ninh Kiều, Cần Thơ', N'Background.jpg')
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV29007', N'CV02', N'Nguyễn Anh Tuấn', 1, 0, N'232928381321', N'0886081546', N'tuanng@fpt.edu.vn', N'Ninh Kiều, Cần Thơ', N'')
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV29008', N'CV02', N'Nguyễn Lan Tường', 1, 1, N'233328386211', N'0386081811', N'tuonglan3@fpt.edu.vn', N'Ninh Kiều, Cần Thơ', N'')
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV29009', N'CV02', N'Lê Anh Ngọc', 0, 1, N'276328382231', N'0886081899', N'ngocle190@fpt.edu.vn', N'Ninh Kiều, Cần Thơ', N'')
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV29012', N'CV03', N'Nguyễn Văn Giàu', 1, 0, N'276320643331', N'0886081869', N'giaunv44@gmail.com', N'Ninh Kiều, Cần Thơ', N'')
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV29013', N'CV03', N'Lê Thành Quý', 1, 0, N'276303643121', N'0886081878', N'quyle11@fpt.edu.vn', N'Ninh Kiều, Cần Thơ', N'')
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV29014', N'CV03', N'Mai Văn Vệ', 1, 0, N'206323643123', N'0886081856', N'vemai11@fpt.edu.vn', N'Ninh Kiều, Cần Thơ', N'')
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV29015', N'CV03', N'Châu Thị Ánh', 0, 1, N'276023643452', N'0886081815', N'anhchau77@fpt.edu.vn', N'Ninh Kiều, Cần Thơ', N'')
INSERT [dbo].[NhanVien] ([MaNhanVien], [MaChucVu], [TenNhanVien], [GioiTinh], [TrangThai], [CMND_CCCD], [SDT], [Email], [DiaChi], [HinhAnh]) VALUES (N'NV919', N'CV01', N'bac', 1, 1, N'989291928199', N'0887088219', N'abciwz21@gmail.com', N'11', NULL)
GO
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT00001', N'PT54002', N'Xe Ô Tô', 200000.0000, N'95N1_00066', 4, N'hahah')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT234455', N'PT54003', N'Xe Ô Tô 4 chỗ', 1000000.0000, N'94A2-77665', 4, N'null')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55001', N'PT54003', N'Xe Du Lịch', 100000.0000, N'65-B1-4345232', 20, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55002', N'PT54004', N'Xe Máy', 100000.0000, N'65-B1-46533', 2, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55003', N'PT54001', N'Xe Máy', 100000.0000, N'65-B2-53401', 2, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55004', N'PT54002', N'Xe Ô Tô 4 chỗ', 1000000.0000, N'65A-43463', 4, N'Chúc q')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55005', N'PT54002', N'Xe Ô Tô 4 chỗ', 1000000.0000, N'65A-65343', 4, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55006', N'PT54002', N'Xe Ô Tô 4 chỗ', 1000000.0000, N'65B-10443', 4, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55007', N'PT54002', N'Xe Ô Tô 7 chỗ', 2000000.0000, N'65A-34234', 8, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55008', N'PT54001', N'Xe Ô Tô 7 chỗ', 2000000.0000, N'65A-89032', 8, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55009', N'PT54002', N'Xe Ô Tô 7 chỗ', 2000000.0000, N'65A-23094', 8, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55010', N'PT54003', N'Xe Du Lịch 16 chỗ', 3000000.0000, N'65A-55930', 16, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55011', N'PT54003', N'Xe Du Lịch 16 chỗ', 3000000.0000, N'65B-81653', 16, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55012', N'PT54003', N'Xe Du Lịch 16 chỗ', 3000000.0000, N'65C-53253', 16, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55013', N'PT54003', N'Xe Du Lịch 29 chỗ', 4000000.0000, N'65C-78643', 29, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55014', N'PT54003', N'Xe Du Lịch 29 chỗ', 4000000.0000, N'65D-23104', 29, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55015', N'PT54003', N'Xe Du Lịch 35 chỗ', 5000000.0000, N'65A-08964', 35, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55016', N'PT54003', N'Xe Du Lịch 35 chỗ', 5000000.0000, N'65A-54433', 35, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55017', N'PT54005', N'Vietnam Airlines ', 2000000.0000, N'VN1206', 50, N'Chuyến bay từ Cần thơ- Đà Nẵng')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55018', N'PT54005', N'Vietnam Airlines ', 2000000.0000, N'VN1200', 50, N'Chuyến bay từ Cần thơ- Đà Lạt')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55019', N'PT54005', N'Vietnam Airlines ', 2000000.0000, N'VN1202', 50, N'Chuyến bay từ Cần thơ- Hà Nội')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55020', N'PT54006', N'Nhà Hàng du thuyền Cần Thơ ', 500000.0000, N'CT34204', 100, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55021', N'PT54006', N'Du thuyền Ninh Kiều ', 600000.0000, N'CT23424', 100, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55022', N'PT54001', N'Xe Máy', 100000.0000, N'43-B1-23401', 2, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55023', N'PT54001', N'Xe Máy', 100000.0000, N'43-B1-46533', 2, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55024', N'PT54001', N'Xe Máy', 100000.0000, N'43-B2-53401', 2, N'Chúc quý khách chuyến đi vui vẻ')
INSERT [dbo].[PhuongTien] ([MaPhuongTien], [MaLoaiPhuongTien], [TenPhuongTien], [CuocPhi], [BienSo], [SoLuongChua], [GhiChu]) VALUES (N'PT55025', N'PT54002', N'Xe Ô Tô 4 chỗ', 1000000.0000, N'43A-43463', 4, N'Chúc quý khách chuyến đi vui vẻ')
GO
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD01818', N'CTKG040540')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD01827', N'CTCM4070')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD018271', N'CTCM4070')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD01901', N'CTKG040540')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD019121', N'CTNT5670')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD01918', N'CTVT4540')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD0192', N'CTNT5670')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD01921', N'CTKG040540')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD01921', N'CTPQ4340')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD01927', N'CTCM4070')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD02112', N'CTNT5670')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD0233', N'CTVT4540')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD0234', N'CTHL5530')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD02817', N'CTVT4540')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD02819', N'CTHL5530')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD02938', N'CTHL5530')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD03818', N'CTSP7790')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD03819', N'CTSP7790')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD03928', N'CTSP7790')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD1099', N'CTDL3329')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD1111', N'CTHN030230')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD1111', N'CTHN7650')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD1211', N'CTQN4460')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD1312', N'CTSG2250')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD32005', N'CTDL3329')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD32006', N'CTDL3329')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD32007', N'CTDL3329')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD32008', N'CTDN5450')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD32009', N'CTDN5450')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD32010', N'CTHN030230')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD32010', N'CTHN7650')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD32011', N'CTHN030230')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD32011', N'CTHN7650')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD32012', N'CTHN030230')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD32012', N'CTHN7650')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD3919', N'CTSG2250')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD6181', N'CTDL3329')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD8011', N'CTBD5545')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD8101', N'CTBD5545')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD9181', N'CTBD5545')
INSERT [dbo].[SetDiemThamQuan] ([MaDiaDiem], [MaTour]) VALUES (N'DD9911', N'CTDN5450')
GO
INSERT [dbo].[SetKhachSan] ([MaKhachSan], [MaTour]) VALUES (N'KS0001', N'CTKG040540')
INSERT [dbo].[SetKhachSan] ([MaKhachSan], [MaTour]) VALUES (N'KS010', N'CTCM4070')
INSERT [dbo].[SetKhachSan] ([MaKhachSan], [MaTour]) VALUES (N'KS011', N'CTBD5545')
INSERT [dbo].[SetKhachSan] ([MaKhachSan], [MaTour]) VALUES (N'KS02', N'CTSG2250')
INSERT [dbo].[SetKhachSan] ([MaKhachSan], [MaTour]) VALUES (N'KS029', N'CTNT5670')
INSERT [dbo].[SetKhachSan] ([MaKhachSan], [MaTour]) VALUES (N'KS03', N'CTDN5450')
INSERT [dbo].[SetKhachSan] ([MaKhachSan], [MaTour]) VALUES (N'KS04', N'CTHL5530')
INSERT [dbo].[SetKhachSan] ([MaKhachSan], [MaTour]) VALUES (N'KS06', N'CTHN030230')
INSERT [dbo].[SetKhachSan] ([MaKhachSan], [MaTour]) VALUES (N'KS06', N'CTHN7650')
INSERT [dbo].[SetKhachSan] ([MaKhachSan], [MaTour]) VALUES (N'KS07', N'CTPQ4340')
INSERT [dbo].[SetKhachSan] ([MaKhachSan], [MaTour]) VALUES (N'KS08', N'CTVT4540')
GO
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55010', N'CTSG2250')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55010', N'CTVL030450')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55011', N'CTHL5530')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55011', N'CTHN7650')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55011', N'CTKG040540')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55011', N'CTPQ4340')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55011', N'CTQN4460')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55013', N'CTDL3329')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55013', N'CTKG040540')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55013', N'CTNT5670')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55013', N'CTPQ4340')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55015', N'CTHN030230')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55016', N'CTBD5545')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55016', N'CTCM4070')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55016', N'CTSG2250')
INSERT [dbo].[SetPhuongTien] ([MaPhuongTien], [MaTour]) VALUES (N'PT55016', N'CTVT4540')
GO
SET IDENTITY_INSERT [dbo].[TaiKhoan] ON 

INSERT [dbo].[TaiKhoan] ([ID], [TenDangNhap], [MatKhau], [Email], [QuyenTruyCap], [MaNhanVien]) VALUES (2, N'Vananh', N'Anh198', N'thuyldpc02874@gmail.com', N'Nhân Viên Trực Quầy', N'NV29004')
INSERT [dbo].[TaiKhoan] ([ID], [TenDangNhap], [MatKhau], [Email], [QuyenTruyCap], [MaNhanVien]) VALUES (3, N'Datmv', N'Datmv12', N'mhldpc02874@gmail.com', N'Admin', N'NV29003')
INSERT [dbo].[TaiKhoan] ([ID], [TenDangNhap], [MatKhau], [Email], [QuyenTruyCap], [MaNhanVien]) VALUES (4, N'Thucmn', N'Thucm018', N'yytyldpc02874@gmail.com', N'Nhân Viên Hướng Dẫn', N'NV29005')
INSERT [dbo].[TaiKhoan] ([ID], [TenDangNhap], [MatKhau], [Email], [QuyenTruyCap], [MaNhanVien]) VALUES (5, N'Anan', N'Datmv12', N'duypc02874@gmail.com', N'Nhân Viên Hướng Dẫn', N'NV29004')
INSERT [dbo].[TaiKhoan] ([ID], [TenDangNhap], [MatKhau], [Email], [QuyenTruyCap], [MaNhanVien]) VALUES (7, N'Andh', N'Thiyy199', N'anhth21@gmail.com', N'Nhân Viên Hướng Dẫn', N'NV29006')
INSERT [dbo].[TaiKhoan] ([ID], [TenDangNhap], [MatKhau], [Email], [QuyenTruyCap], [MaNhanVien]) VALUES (8, N'Ngocanh', N'Ngoc910', N'ngoch91@gmail.com', N'Nhân Viên Trực Quầy', N'NV29009')
INSERT [dbo].[TaiKhoan] ([ID], [TenDangNhap], [MatKhau], [Email], [QuyenTruyCap], [MaNhanVien]) VALUES (13, N'Thuylam', N'Thuy1210', N'thuylam999999@gmail.com', N'Quản Lý', N'NV29001')
INSERT [dbo].[TaiKhoan] ([ID], [TenDangNhap], [MatKhau], [Email], [QuyenTruyCap], [MaNhanVien]) VALUES (14, N'Anyhh', N'An8001', N'anth910@gmail.com', N'Nhân Viên Trực Quầy', N'NV29004')
SET IDENTITY_INSERT [dbo].[TaiKhoan] OFF
GO
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTBD5545', N'Cần Thơ - Bình Dương - 5 Ngày 5 Đêm', 5, 5, 2038210.0000, 45, NULL)
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTCM4070', N'Cần Thơ - Cà Mau - 6 Ngày 6 Đêm', 6, 6, 4015471.0000, 70, NULL)
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTDL3329', N'Cần Thơ - Đà Lạt - 3 Ngày 3 Đêm', 3, 3, 3292243.0000, 39, N'fc1b98631b96ce5ac550ead556bb2e5d.png')
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTDN5450', N'Cần Thơ - Đà Nẵng - 5 Ngày 4 Đêm', 5, 4, 4022700.0000, 50, N'h2.jpg')
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTHL2120', N'Cần Thơ - Vịnh Hạ Long - 2 Ngày 1 Đêm', 2, 1, 0.0000, 20, NULL)
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTHL5530', N'Cần Thơ - Vịnh Hạ Long - 5 Ngày 5 Đêm ', 5, 5, 4296783.0000, 30, NULL)
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTHN030230', N'Cần Thơ - Hà Nội - 3 Ngày 2 Đêm', 3, 2, 4423283.0000, 30, NULL)
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTHN7650', N'Cần Thơ - Hà Nội - 7 Ngày 6 Đêm', 7, 6, 6643780.0000, 50, NULL)
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTKG040540', N'Cần Thơ - Kiên Giang - 4 ngày 5 đêm', 4, 5, 3399687.0000, 40, N'hinh2.png')
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTNT5670', N'Cần Thơ - Nha Trang - 5 Ngày 6 Đêm', 5, 6, 3997400.0000, 70, NULL)
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTPQ4340', N'Cần Thơ - Phú Quốc - 4 Ngày 3 Đêm', 4, 3, 3383875.0000, 40, NULL)
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTQN4460', N'Cần Thơ - Quảng Nam - 4 Ngày 4 Đêm', 4, 4, 695750.0000, 60, NULL)
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTSG2250', N'Cần Thơ - Hồ Chí Minh - 2 Ngày 3 Đêm', 2, 3, 2681800.0000, 50, N'h7.jpg')
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTSP7790', N'Cần Thơ - SaPa - 7 Ngày 7 Đêm', 7, 7, 1897500.0000, 90, NULL)
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTVL030450', N'Cần Thơ - Vĩnh Long - 3 Ngày 4 Đêm', 3, 4, 75900.0000, 50, NULL)
INSERT [dbo].[Tour] ([MaTour], [TenTour], [SoNgay], [SoDem], [GiaTour], [SoLuongKhach], [HinhAnh]) VALUES (N'CTVT4540', N'Cần Thơ - Vũng Tàu - 4 Ngày 5 Đêm', 4, 5, 4190312.0000, 40, NULL)
GO
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00001', N'CTBD5545', N'NV021', N'KH0102', N'LT00001', 10, 2, 90045000.0000, CAST(N'2018-10-13' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00002', N'CTBD5545', N'NV29001', N'KH0120', N'LT00001', 5, 1, 45022500.0000, CAST(N'2018-10-14' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00003', N'CTCM4070', N'NV29004', N'KH015', N'LT00002', 10, 10, 134750000.0000, CAST(N'2018-10-29' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00004', N'CTCM4070', N'NV29003', N'KH017', N'LT00002', 4, 2, 42350000.0000, CAST(N'2018-10-28' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00005', N'CTCM4070', N'NV29005', N'KH018', N'LT00002', 6, 1, 51975000.0000, CAST(N'2018-10-27' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00006', N'CTDL3329', N'NV29003', N'KH017', N'LT00003', 10, 10, 105000000.0000, CAST(N'2019-11-28' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00007', N'CTDL3329', N'NV29007', N'KH021', N'LT00003', 11, 0, 66000000.0000, CAST(N'2019-11-27' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00008', N'CTDN5450', N'NV29009', N'KH123456', N'LT00004', 5, 0, 15000000.0000, CAST(N'2019-02-01' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00009', N'CTDN5450', N'NV29012', N'KH088', N'LT00004', 10, 10, 52500000.0000, CAST(N'2019-01-30' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00010', N'CTDN5450', N'NV29014', N'KH0819', N'LT00004', 25, 0, 75000000.0000, CAST(N'2019-01-30' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00011', N'CTHL5530', N'NV29013', N'KH303001', N'LT00005', 15, 1, 96705000.0000, CAST(N'2019-03-03' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00012', N'CTHL5530', N'NV29001', N'KH303002', N'LT00005', 5, 1, 35305000.0000, CAST(N'2019-03-03' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00014', N'CTHN7650', N'NV29007', N'KH303004', N'LT00006', 10, 0, 67000000.0000, CAST(N'2019-04-01' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00015', N'CTHN7650', N'NV29008', N'KH303005', N'LT00006', 10, 0, 67000000.0000, CAST(N'2019-04-02' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00016', N'CTHN7650', N'NV29008', N'KH303006', N'LT00006', 20, 0, 134000000.0000, CAST(N'2019-04-02' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00017', N'CTHN7650', N'NV29013', N'KH303007', N'LT00006', 10, 0, 67000000.0000, CAST(N'2019-03-30' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00018', N'CTKG040540', N'NV29004', N'KH303008', N'LT00007', 20, 0, 212000000.0000, CAST(N'2019-04-04' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00019', N'CTKG040540', N'NV29004 ', N'KH303009', N'LT00007', 10, 1, 113950000.0000, CAST(N'2019-04-01' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00020', N'CTKG040540', N'NV29005', N'KH919', N'LT00007', 30, 10, 397500000.0000, CAST(N'2019-04-02' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00021', N'CTNT5670', N'NV29006', N'KH990', N'LT00008', 10, 10, 119000000.0000, CAST(N'2022-11-09' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00022', N'CTNT5670', N'NV29003', N'KH017', N'LT00008', 10, 5, 93500000.0000, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00023', N'CTPQ4340', N'NV29001', N'KH0102', N'LT00009', 10, 10, 178500000.0000, CAST(N'2020-03-08' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00025', N'CTQN4460', N'NV29004', N'KH123456', N'LT00010', 20, 5, 99750000.0000, CAST(N'2020-10-11' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00026', N'CTSG2250', N'NV29012', N'KH303002', N'LT00011', 10, 0, 110000000.0000, CAST(N'2021-06-10' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00027', N'CTSG2250', N'NV29007', N'KH088', N'LT00011', 5, 0, 55000000.0000, CAST(N'2021-06-06' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00028', N'CTSP7790', N'NV29004', N'KH021', N'LT00012', 30, 10, 60000000.0000, CAST(N'2022-12-06' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00029', N'CTSP7790', N'NV29007', N'KH0819', N'LT00012', 10, 0, 16000000.0000, CAST(N'2022-12-05' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00030', N'CTVL030450', N'NV29013', N'KH017', N'LT00013', 10, 10, 70000000.0000, CAST(N'2022-08-09' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00031', N'CTVL030450', N'NV29013', N'KH021', N'LT00013', 10, 0, 40000000.0000, CAST(N'2022-08-08' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00032', N'CTVT4540', N'NV29001', N'KH015', N'LT00014', 20, 0, 162000000.0000, CAST(N'2022-12-10' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'MV00033', N'CTVT4540', N'NV29014', N'KH303002', N'LT00014', 10, 0, 81000000.0000, CAST(N'2022-12-09' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'NV00024', N'CTPQ4340', N'NV29013', N'KH021', N'LT00009', 12, 0, 122400000.0000, CAST(N'2020-03-03' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE001', N'CTBD5545', N'NV29004', N'KH021', N'LT00001', 3, 1, 14774145.0000, CAST(N'2018-06-01' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE0010', N'CTBD5545', N'NV29001', N'KH303002', N'LT918', 5, 1, 22653689.0000, CAST(N'2022-03-01' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE0011', N'CTBD5545', N'NV29013', N'KH303005', N'LT918', 2, 1, 10834373.0000, CAST(N'2022-05-20' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE002', N'CTSP7790', N'NV29001', N'KH021', N'LT00015', 3, 1, 7115625.0000, CAST(N'2017-03-12' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE003', N'CTSP7790', N'NV29001', N'KH088', N'LT00015', 5, 2, 12333750.0000, CAST(N'2017-01-30' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE004', N'CTSP7790', N'NV29006', N'KH919', N'LT00015', 4, 2, 10436250.0000, CAST(N'2017-05-29' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE005', N'CTBD5545', N'NV29001', N'KH990', N'LT918', 5, 1, 22653689.0000, CAST(N'2022-10-01' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE006', N'CTBD5545', N'NV29001', N'KH021', N'LT918', 6, 1, 26593461.0000, CAST(N'2022-06-20' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE007', N'CTBD5545', N'NV29001', N'KH017', N'LT918', 7, 0, 27578404.0000, CAST(N'2022-02-10' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE009', N'CTVL030450', N'NV29004', N'KH303001', N'LT00013', 3, 0, 531300.0000, CAST(N'2022-09-10' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE181220220451', N'CTHN030230', N'NV29004', N'KH015', N'LT2023', 2, 0, 8846566.0000, CAST(N'2022-12-18' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE191220222588', N'CTBD5545', N'NV29004', N'KH123456', N'LT918', 2, 0, 7879544.0000, CAST(N'2022-12-19' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE191220224698', N'CTBD5545', N'NV29004', N'KH00013', N'LT918', 2, 0, 7879544.0000, CAST(N'2022-12-19' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE191220225393', N'CTBD5545', N'NV29004', N'KH0819', N'LT9182', 1, 0, 3939772.0000, CAST(N'2022-12-19' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE191220227687', N'CTVT4540', N'NV29004', N'KH0182', N'LT00014', 1, 0, 4190312.0000, CAST(N'2022-12-19' AS Date))
INSERT [dbo].[Ve] ([MaVe], [MaTour], [MaNhanVien], [MaKhachHang], [MaLichTrinh], [SoLuongVeNguoiLon], [SoLuongVeTreEm], [TongTien], [NgayBan]) VALUES (N'VE191220227947', N'CTVT4540', N'NV29004', N'KH088', N'LT00014', 2, 0, 8380624.0000, CAST(N'2022-12-19' AS Date))
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KhachHan__A9D10534107D6744]    Script Date: 12/21/2022 6:01:07 PM ******/
ALTER TABLE [dbo].[KhachHang] ADD UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KhachHan__B9137313A084F249]    Script Date: 12/21/2022 6:01:07 PM ******/
ALTER TABLE [dbo].[KhachHang] ADD UNIQUE NONCLUSTERED 
(
	[CMND_CCCD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KhachHan__CA1930A5AAC56672]    Script Date: 12/21/2022 6:01:07 PM ******/
ALTER TABLE [dbo].[KhachHang] ADD UNIQUE NONCLUSTERED 
(
	[SDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KhachSan__CA1930A5C6F51DDF]    Script Date: 12/21/2022 6:01:07 PM ******/
ALTER TABLE [dbo].[KhachSan] ADD  CONSTRAINT [UQ__KhachSan__CA1930A5C6F51DDF] UNIQUE NONCLUSTERED 
(
	[SDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanVien__A9D1053479ED8823]    Script Date: 12/21/2022 6:01:07 PM ******/
ALTER TABLE [dbo].[NhanVien] ADD UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanVien__B9137313955B2D25]    Script Date: 12/21/2022 6:01:07 PM ******/
ALTER TABLE [dbo].[NhanVien] ADD UNIQUE NONCLUSTERED 
(
	[CMND_CCCD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NhanVien__CA1930A579A66215]    Script Date: 12/21/2022 6:01:07 PM ******/
ALTER TABLE [dbo].[NhanVien] ADD UNIQUE NONCLUSTERED 
(
	[SDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__PhuongTi__F7052EB6F3DEA56E]    Script Date: 12/21/2022 6:01:07 PM ******/
ALTER TABLE [dbo].[PhuongTien] ADD UNIQUE NONCLUSTERED 
(
	[BienSo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__TaiKhoan__55F68FC09142E8D7]    Script Date: 12/21/2022 6:01:07 PM ******/
ALTER TABLE [dbo].[TaiKhoan] ADD UNIQUE NONCLUSTERED 
(
	[TenDangNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__TaiKhoan__A9D10534EB798ED0]    Script Date: 12/21/2022 6:01:07 PM ******/
ALTER TABLE [dbo].[TaiKhoan] ADD UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[HopDong] ADD  CONSTRAINT [DF__HopDong__NgayLap__1D7B6025]  DEFAULT (getdate()) FOR [NgayLapHopDong]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((0)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[TaiKhoan] ADD  DEFAULT (N'Nhân Viên Hướng Dẫn') FOR [QuyenTruyCap]
GO
ALTER TABLE [dbo].[Tour] ADD  CONSTRAINT [DF__Tour__GiaTour__5CD6CB2B]  DEFAULT ((0)) FOR [GiaTour]
GO
ALTER TABLE [dbo].[Ve] ADD  CONSTRAINT [DF__Ve__SoLuongVeNgu__4E53A1AA]  DEFAULT ((0)) FOR [SoLuongVeNguoiLon]
GO
ALTER TABLE [dbo].[Ve] ADD  CONSTRAINT [DF__Ve__SoLuongVeTre__4F47C5E3]  DEFAULT ((0)) FOR [SoLuongVeTreEm]
GO
ALTER TABLE [dbo].[Ve] ADD  CONSTRAINT [DF__Ve__NgayBan__503BEA1C]  DEFAULT (getdate()) FOR [NgayBan]
GO
ALTER TABLE [dbo].[ChiTietHopDong]  WITH CHECK ADD  CONSTRAINT [FK_HopDong_HopDongChiTiet] FOREIGN KEY([MaHopDong])
REFERENCES [dbo].[HopDong] ([MaHopDong])
GO
ALTER TABLE [dbo].[ChiTietHopDong] CHECK CONSTRAINT [FK_HopDong_HopDongChiTiet]
GO
ALTER TABLE [dbo].[HopDong]  WITH CHECK ADD  CONSTRAINT [FK_HopDong_Ve] FOREIGN KEY([MaVe])
REFERENCES [dbo].[Ve] ([MaVe])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[HopDong] CHECK CONSTRAINT [FK_HopDong_Ve]
GO
ALTER TABLE [dbo].[LichTrinhChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_LichTrinhChiTiet_LichTrinh] FOREIGN KEY([MaLichTrinh])
REFERENCES [dbo].[LichTrinh] ([MaLichTrinh])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[LichTrinhChiTiet] CHECK CONSTRAINT [FK_LichTrinhChiTiet_LichTrinh]
GO
ALTER TABLE [dbo].[LichTrinhChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_LichTrinhChiTiet_Tour] FOREIGN KEY([MaTour])
REFERENCES [dbo].[Tour] ([MaTour])
GO
ALTER TABLE [dbo].[LichTrinhChiTiet] CHECK CONSTRAINT [FK_LichTrinhChiTiet_Tour]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_ChucVu] FOREIGN KEY([MaChucVu])
REFERENCES [dbo].[ChucVu] ([MaChucVu])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_ChucVu]
GO
ALTER TABLE [dbo].[PhuongTien]  WITH CHECK ADD  CONSTRAINT [FK_LoaiPT_PhuongTien] FOREIGN KEY([MaLoaiPhuongTien])
REFERENCES [dbo].[LoaiPhuongTien] ([MaLoaiPT])
GO
ALTER TABLE [dbo].[PhuongTien] CHECK CONSTRAINT [FK_LoaiPT_PhuongTien]
GO
ALTER TABLE [dbo].[SetDiemThamQuan]  WITH CHECK ADD  CONSTRAINT [FK_DiemTQ_SetDiemTQ] FOREIGN KEY([MaDiaDiem])
REFERENCES [dbo].[DiemThamQuan] ([MaDiaDiem])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[SetDiemThamQuan] CHECK CONSTRAINT [FK_DiemTQ_SetDiemTQ]
GO
ALTER TABLE [dbo].[SetDiemThamQuan]  WITH CHECK ADD  CONSTRAINT [FK_DiemTQ_Tour] FOREIGN KEY([MaTour])
REFERENCES [dbo].[Tour] ([MaTour])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[SetDiemThamQuan] CHECK CONSTRAINT [FK_DiemTQ_Tour]
GO
ALTER TABLE [dbo].[SetKhachSan]  WITH CHECK ADD  CONSTRAINT [FK_KhachSan_SetKhachSan] FOREIGN KEY([MaKhachSan])
REFERENCES [dbo].[KhachSan] ([MaKhachSan])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[SetKhachSan] CHECK CONSTRAINT [FK_KhachSan_SetKhachSan]
GO
ALTER TABLE [dbo].[SetKhachSan]  WITH CHECK ADD  CONSTRAINT [FK_KhachSan_Tour] FOREIGN KEY([MaTour])
REFERENCES [dbo].[Tour] ([MaTour])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[SetKhachSan] CHECK CONSTRAINT [FK_KhachSan_Tour]
GO
ALTER TABLE [dbo].[SetPhuongTien]  WITH CHECK ADD  CONSTRAINT [FK_PhuongTien_SetPhuongTien] FOREIGN KEY([MaPhuongTien])
REFERENCES [dbo].[PhuongTien] ([MaPhuongTien])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[SetPhuongTien] CHECK CONSTRAINT [FK_PhuongTien_SetPhuongTien]
GO
ALTER TABLE [dbo].[SetPhuongTien]  WITH CHECK ADD  CONSTRAINT [FK_Tour_PhuongTien] FOREIGN KEY([MaTour])
REFERENCES [dbo].[Tour] ([MaTour])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[SetPhuongTien] CHECK CONSTRAINT [FK_Tour_PhuongTien]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_NhanVien] FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[NhanVien] ([MaNhanVien])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_NhanVien]
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD  CONSTRAINT [FK_Ve_KhachHang] FOREIGN KEY([MaKhachHang])
REFERENCES [dbo].[KhachHang] ([MaKhachHang])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Ve] CHECK CONSTRAINT [FK_Ve_KhachHang]
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD  CONSTRAINT [FK_Ve_NhanVien] FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[NhanVien] ([MaNhanVien])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Ve] CHECK CONSTRAINT [FK_Ve_NhanVien]
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD  CONSTRAINT [FK_Ve_Tour] FOREIGN KEY([MaTour])
REFERENCES [dbo].[Tour] ([MaTour])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Ve] CHECK CONSTRAINT [FK_Ve_Tour]
GO
ALTER TABLE [dbo].[HopDong]  WITH CHECK ADD  CONSTRAINT [CK__HopDong__SoLuong__1E6F845E] CHECK  (([SoLuongKhach]>(0)))
GO
ALTER TABLE [dbo].[HopDong] CHECK CONSTRAINT [CK__HopDong__SoLuong__1E6F845E]
GO
ALTER TABLE [dbo].[LichTrinh]  WITH CHECK ADD  CONSTRAINT [CK__LichTrinh__76969D2E] CHECK  (([NgayKhoiHanh]<[NgayKetThuc]))
GO
ALTER TABLE [dbo].[LichTrinh] CHECK CONSTRAINT [CK__LichTrinh__76969D2E]
GO
ALTER TABLE [dbo].[PhuongTien]  WITH CHECK ADD CHECK  (([CuocPhi]>=(0) AND [SoLuongChua]>(0)))
GO
ALTER TABLE [dbo].[Tour]  WITH CHECK ADD  CONSTRAINT [CK__Tour__5DCAEF64] CHECK  (([SoNgay]>(0) AND [SoDem]>(0) AND [SoLuongKhach]>(0) AND [GiaTour]>=(0)))
GO
ALTER TABLE [dbo].[Tour] CHECK CONSTRAINT [CK__Tour__5DCAEF64]
GO
ALTER TABLE [dbo].[Tour]  WITH CHECK ADD  CONSTRAINT [CK_SoNgay_SoDem] CHECK  ((([SoNgay]-[SoDem])<=(1) OR ([SoDem]-[SoNgay])<=(1)))
GO
ALTER TABLE [dbo].[Tour] CHECK CONSTRAINT [CK_SoNgay_SoDem]
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD  CONSTRAINT [CK__Ve__51300E55] CHECK  (([SoLuongVeNguoiLon]>=(0) AND [SoLuongVeTreEm]>=(0)))
GO
ALTER TABLE [dbo].[Ve] CHECK CONSTRAINT [CK__Ve__51300E55]
GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeDoanhThu]    Script Date: 12/21/2022 6:01:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_ThongKeDoanhThu]
		(@Nam int)
		as
BEGIN 
	SELECT TenTour AS N'Tên Tour',
	COUNT(LTCT.MaLichTrinh) AS N'Tổng Số Lộ Trình',
	SUM(SoLuongVeNguoiLon) AS N'Tổng Số Người Lớn',
	SUM(SoLuongVeTreEm) AS N'Tổng Số Trẻ Em',
	(FORMAT(SUM(TongTien),'N','vi-VN') + ' VNĐ') AS 'Doanh Thu',
	--SUM(TongTien) AS 'Doanh Thu',
	CONVERT(varchar,NgayBan,105) as N'Ngày Bán'
FROM Tour,dbo.LichTrinh,dbo.LichTrinhChiTiet LTCT,dbo.Ve
where LTCT.MaTour = Tour.MaTour 
and Ve.MaLichTrinh = LichTrinh.MaLichTrinh and YEAR(NgayBan) = @Nam
GROUP BY TenTour,NgayBan,TongTien
	ORDER BY [Doanh Thu] ASC
END
GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeTour]    Script Date: 12/21/2022 6:01:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_ThongKeTour](@Nam INT)
	AS 
	BEGIN
	    SELECT 
		TenTour AS N'Tên Tour',
	--	CONVERT(varchar,SoNgay,105) + N' Ngày ' + CONVERT(VARCHAR,SoDem,105)+N' Đêm' AS N'Thời Gian Đi',
	--	FORMAT(SoNgay,'N','vi-VN')+N' Ngày',
	--	(SoNgay+N' Ngày'+ SoDem +N' Đêm') AS N'Thời Gian Đi',
		(FORMAT(GiaTour,'N','vi-VN') + ' VNĐ') AS N'Giá Tour',
		(SoLuongVeNguoiLon + SoLuongVeTreEm) AS N'Tổng Số Du Khách'	,CONVERT(varchar,NgayBan,105) AS N'Thời Gian Đi'	
		FROM Tour,dbo.Ve
	WHERE Tour.MaTour = Ve.MaTour and
	 YEAR(NgayBan) = @Nam
	ORDER BY(SoLuongVeNguoiLon + SoLuongVeTreEm) DESC
	END 
GO
USE [master]
GO
ALTER DATABASE [QuanLy_TourDuLichGreenHouse] SET  READ_WRITE 
GO

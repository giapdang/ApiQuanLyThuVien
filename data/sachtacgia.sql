INSERT INTO sach_tac_gia (sach_tac_gia_id, sach_id, tac_gia_id) VALUES

-- ==================== VĂN HỌC ====================
-- Nguyễn Nhật Ánh (tac_gia_id = 1)
(1, 1, 1),    -- Mắt Biếc
(2, 2, 1),    -- Tôi Thấy Hoa Vàng Trên Cỏ Xanh
(3, 5, 1),    -- Cô Gái Đến Từ Hôm Qua
(4, 14, 1),   -- Cho Tôi Xin Một Vé Đi Tuổi Thơ
(5, 15, 1),   -- Kính Vạn Hoa

-- Nam Cao (tac_gia_id = 2)
(6, 7, 2),    -- Chí Phèo
(7, 8, 2),    -- Lão Hạc
(8, 9, 2),    -- Đời Thừa

-- Tô Hoài (tac_gia_id = 3)
(9, 13, 3),   -- Dế Mèn Phiêu Lưu Ký
(10, 16, 3),  -- O Chuột
(11, 17, 3),  -- Đất Rừng Phương Nam

-- Ngô Tất Tố (tac_gia_id = 4)
(12, 3, 4),   -- Tắt Đèn

-- Vũ Trọng Phụng (tac_gia_id = 5)
(13, 4, 5),   -- Số Đỏ
(14, 6, 5),   -- Giông Tố

-- Thạch Lam (tac_gia_id = 7)
(15, 10, 7),  -- Gió Đầu Mùa
(16, 11, 7),  -- Hai Đứa Trẻ

-- Nguyễn Tuân (tac_gia_id = 8)
(17, 12, 8),  -- Vang Bóng Một Thời

-- ==================== LỊCH SỬ ====================
-- TS. Lê Thị Mai (tac_gia_id = 11)
(18, 18, 11), -- Giáo trình Tiến trình Lịch sử Việt Nam
(19, 19, 11), -- Giáo trình Lịch sử Văn minh Thế giới
(20, 20, 11), -- Đại Việt Sử Ký Toàn Thư
(21, 21, 11), -- Việt Nam Sử Lược

-- ==================== KINH TẾ ====================
-- TS.  Nguyễn Thị Hồng (tac_gia_id = 14)
(22, 22, 14), -- Giáo trình Kinh tế Vi mô 1
(23, 23, 14), -- Giáo trình Kinh tế Vĩ mô 2
(24, 24, 14), -- Giáo trình Nguyên lý Kế toán
(25, 25, 14), -- Giáo trình Marketing Căn bản
(26, 26, 14), -- Bài tập Kinh tế Vi mô có Lời giải
(27, 27, 14), -- Phân tích Báo cáo Tài chính

-- ==================== CÔNG NGHỆ THÔNG TIN ====================
-- PGS. TS. Trần Văn Thành (tac_gia_id = 10)
(28, 28, 10), -- Giáo trình Nhập môn Lập trình
(29, 31, 10), -- Giáo trình Mạng Máy tính
(30, 32, 10), -- Giáo trình Hệ điều hành
(31, 39, 10), -- An toàn Dữ liệu

-- PGS.TS. Hoàng Minh Tuấn (tac_gia_id = 13)
(32, 29, 13), -- Giáo trình Cấu trúc Dữ liệu và Giải thuật
(33, 30, 13), -- Giáo trình Cơ sở Dữ liệu
(34, 33, 13), -- Giáo trình Công nghệ Phần mềm
(35, 36, 13), -- 100 Bài tập Lập trình Python

-- TS. Bùi Quang Huy (tac_gia_id = 17)
(36, 34, 17), -- Bài tập Lập trình Java cơ bản có Lời giải
(37, 35, 17), -- 100 Bài tập Lập trình C++ có Lời giải
(38, 37, 17), -- Kỷ nguyên Trí tuệ Nhân tạo
(39, 38, 17), -- Lập trình Web với ASP.NET

-- Đồng tác giả
(40, 37, 13), -- Kỷ nguyên Trí tuệ Nhân tạo - Hoàng Minh Tuấn (đồng tác giả)

-- ==================== TOÁN HỌC ====================
-- GS.  TSKH. Đặng Văn Sơn (tac_gia_id = 15)
(41, 40, 15), -- Giáo trình Giải tích 1
(42, 41, 15), -- Giáo trình Giải tích 2
(43, 42, 15), -- Giáo trình Đại số Tuyến tính
(44, 43, 15), -- Giáo trình Xác suất Thống kê
(45, 44, 15), -- Giáo trình Toán Rời rạc

-- ==================== KHOA HỌC ====================
-- GS. TS. Nguyễn Văn Hiệu (tac_gia_id = 9) - Vật lý
(46, 45, 9),  -- Giáo trình Vật lý Đại cương Tập 1
(47, 46, 9),  -- Giáo trình Cơ học Lượng tử

-- TS. Phạm Minh Châu (tac_gia_id = 20) - Hóa học, Sinh học
(48, 47, 20), -- Giáo trình Hóa học Đại cương
(49, 48, 20), -- Giáo trình Hóa Hữu cơ
(50, 49, 20), -- Giáo trình Hóa Vô cơ
(51, 50, 20), -- Giáo trình Sinh học Đại cương
(52, 51, 20), -- Giáo trình Di truyền học
(53, 52, 20), -- Giáo trình Sinh học Phân tử

-- GS. TS. Phạm Văn Đồng (tac_gia_id = 12) - Y học
(54, 53, 12), -- Giáo trình Giải phẫu học
(55, 54, 12), -- Giáo trình Sinh lý học Y khoa

-- ==================== NGOẠI NGỮ ====================
-- ThS. Trần Thị Lan Anh (tac_gia_id = 18)
(56, 55, 18), -- English Vocabulary in Use
(57, 56, 18), -- Cambridge English Skills: Real Listening & Speaking

-- ==================== LUẬT ====================
-- PGS.TS. Vũ Đức Nghĩa (tac_gia_id = 16)
(58, 57, 16), -- Giáo trình Luật Dân sự Việt Nam
(59, 58, 16), -- Giáo trình Luật Hình sự Việt Nam
(60, 59, 16), -- Giáo trình Luật Kinh tế
(61, 60, 16), -- Giáo trình Luật Hiến pháp

-- ==================== KỸ THUẬT ====================
-- PGS.TS. Lê Văn Hùng (tac_gia_id = 19)
(62, 61, 19), -- Giáo trình Cơ học Kỹ thuật
(63, 62, 19), -- Giáo trình Sức bền Vật liệu
(64, 63, 19), -- Giáo trình Điện tử Cơ bản

-- ==================== TRIẾT HỌC ====================
-- TS. Lê Thị Mai (tac_gia_id = 11)
(65, 64, 11), -- Giáo trình Triết học Mác-Lênin
(66, 65, 11), -- Giáo trình Lịch sử Triết học
(67, 66, 11); -- Tư tưởng Hồ Chí Minh
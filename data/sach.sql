INSERT INTO sach (sach_id, ten_sach, so_trang, kho_sach, anh_bia, gia_tien, nam_xuat_ban, nha_xuat_ban_id, linh_vuc_id, the_loai_id) VALUES

-- ==================== VĂN HỌC ====================
-- Tiểu thuyết (the_loai_id = 3)
(1, 'Mắt Biếc', 256, 'A1', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-mat-biec.jpg', 85000.00, '2019-01-15', 1, 1, 3),
(2, 'Tôi Thấy Hoa Vàng Trên Cỏ Xanh', 378, 'A1', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-toi-thay-hoa-vang-tren-co-xanh.jpg', 95000.00, '2018-03-20', 1, 1, 3),
(3, 'Tắt Đèn', 180, 'A2', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-tat-den.jpg', 50000.00, '2019-11-12', 3, 1, 3),
(4, 'Số Đỏ', 280, 'A2', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-so-do.jpg', 75000.00, '2020-02-28', 3, 1, 3),
(5, 'Cô Gái Đến Từ Hôm Qua', 280, 'A1', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-co-gai-den-tu-hom-qua.jpg', 82000.00, '2020-09-18', 1, 1, 3),
(6, 'Giông Tố', 320, 'A2', 'https://isach.info/images/story/cover/giong_to__vu_trong_phung.jpg', 88000.00, '2018-10-05', 3, 1, 3),

-- Truyện ngắn (the_loai_id = 4)
(7, 'Chí Phèo', 120, 'A3', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-chi-pheo.jpg', 45000.00, '2020-05-10', 3, 1, 4),
(8, 'Lão Hạc', 80, 'A3', 'https://m.media-amazon.com/images/S/compressed.photo.goodreads.com/books/1631870884i/43320300.jpg', 35000.00, '2021-03-14', 3, 1, 4),
(9, 'Đời Thừa', 95, 'A3', 'https://m.media-amazon.com/images/S/compressed.photo.goodreads.com/books/1631691889i/31340073.jpg', 38000.00, '2020-04-08', 3, 1, 4),
(10, 'Gió Đầu Mùa', 140, 'A4', 'https://product.hstatic.net/200000979221/product/gio-dau-mua_87d09a2b1a864e35918f810f640e9947.jpg', 48000.00, '2020-07-22', 3, 1, 4),
(11, 'Hai Đứa Trẻ', 85, 'A4', 'https://m.media-amazon.com/images/S/compressed.photo.goodreads.com/books/1605960703i/55974838.jpg', 32000.00, '2021-06-25', 3, 1, 4),
(12, 'Vang Bóng Một Thời', 220, 'A4', 'https://online.anyflip.com/mhnd/qpld/files/mobile/1.jpg?1672678754', 65000.00, '2021-01-08', 3, 1, 4),

-- Truyện thiếu nhi (the_loai_id = 5)
(13, 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-de-men-phieu-luu-ky.jpg', 200, 'B1', 'de_men. jpg', 55000.00, '2017-08-25', 2, 1, 5),
(14, 'Cho Tôi Xin Một Vé Đi Tuổi Thơ', 216, 'B1', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-cho-toi-xin-mot-ve-di-tuoi-tho.jpg', 78000.00, '2019-05-30', 1, 1, 5),
(15, 'Kính Vạn Hoa', 190, 'B1', 'https://upload.wikimedia.org/wikipedia/vi/6/65/B%C3%ACa_truy%E1%BB%87n_K%C3%ADnh_v%E1%BA%A1n_hoa_2012.jpg', 72000.00, '2022-02-14', 1, 1, 5),
(16, 'O Chuột', 150, 'B2', 'https://phatphapungdung.com/sach-noi/wp-content/uploads/2019/10/o-chuot.jpg', 42000.00, '2019-12-01', 2, 1, 5),
(17, 'Đất Rừng Phương Nam', 350, 'B2', 'https://nhasachmienphi.com/images/thumbnail/nhasachmienphi-dat-rung-phuong-nam.jpg', 89000.00, '2020-08-15', 2, 1, 5),

-- ==================== LỊCH SỬ ====================
-- Giáo trình (the_loai_id = 1)
(18, 'Giáo trình Tiến trình Lịch sử Việt Nam', 480, 'C1', 'https://images.vnuhcmpress.edu.vn/Picture/2024/3/15/image-20240315135055024.jpg', 145000.00, '2022-01-10', 4, 2, 1),
(19, 'Giáo trình Lịch sử Văn minh Thế giới', 520, 'C1', 'https://k3bxzjut8xobj.vcdn.cloud/Book/thumb-2409e890-2741-4c54-958e-df0e25dfd273.jpg', 155000.00, '2021-08-20', 4, 2, 1),

-- Tài liệu tham khảo (the_loai_id = 2)
(20, 'Đại Việt Sử Ký Toàn Thư', 650, 'C2', 'https://quangduc.com/images/file/rRAEINTd1AgBAeQP/dai-viet-su-ky-toan-thu.gif', 220000.00, '2019-05-12', 8, 2, 2),
(21, 'Việt Nam Sử Lược', 380, 'C2', 'https://m.media-amazon.com/images/S/compressed.photo.goodreads.com/books/1632034796i/11632236.jpg', 125000.00, '2020-03-18', 3, 2, 2),

-- ==================== KINH TẾ ====================
-- Giáo trình (the_loai_id = 1)
(22, 'Giáo trình Kinh tế Vi mô 1', 380, 'D1', 'https://images.nxbbachkhoa.vn/Picture/2023/7/13/image-20230713180349574.jpg', 138000.00, '2022-02-28', 5, 3, 1),
(23, 'Giáo trình Kinh tế Vĩ mô 2', 400, 'D1', 'https://images.nxbbachkhoa.vn/Picture/2023/7/13/image-20230713180424816.jpg', 142000.00, '2022-08-14', 5, 3, 1),
(24, 'Giáo trình Nguyên lý Kế toán', 350, 'D1', 'https://hvtc.edu.vn/Portals/4/gtnlkt_1.jpg', 125000.00, '2023-05-22', 5, 3, 1),
(25, 'Giáo trình Marketing Căn bản', 320, 'D2', 'https://photo2.tinhte.vn/data/attachment-files/2024/08/8428462_sach-marketing-can-ban-1702523982705275083973.jpg', 118000.00, '2023-01-15', 5, 3, 1),

-- Tài liệu tham khảo (the_loai_id = 2)
(26, 'Bài tập Kinh tế Vi mô có Lời giải', 250, 'D2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZ_FKn5dFjysyii-1yNQdC2MLR_bqc7Z1J4g&s', 85000.00, '2023-04-10', 5, 3, 2),
(27, 'Phân tích Báo cáo Tài chính', 280, 'D2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRC5WFgV7IJUqPLQSHMrCfaYUxoGA3NUpYTOA&s', 95000.00, '2022-11-08', 5, 3, 2),

-- ==================== CÔNG NGHỆ THÔNG TIN ====================
-- Giáo trình (the_loai_id = 1)
(28, 'Giáo trình Nhập môn Lập trình', 320, 'E1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBfvDdAeGJOFqDui0JECzuZK9mQJ_xF-OObQ&s', 125000.00, '2023-01-10', 6, 4, 1),
(29, 'Giáo trình Cấu trúc Dữ liệu và Giải thuật', 450, 'E1', 'https://images.vnuhcmpress.edu.vn/Picture/2024/7/1/image-20240701150234854.jpg', 155000.00, '2022-08-15', 6, 4, 1),
(30, 'Giáo trình Cơ sở Dữ liệu', 380, 'E1', 'https://images.vnuhcmpress.edu.vn/Picture/2023/GT-CSDL.jpg', 140000.00, '2023-03-20', 5, 4, 1),
(31, 'Giáo trình Mạng Máy tính', 420, 'E2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTre1itUth2ixXAgvFysiZZf8iKoldvmSiRlg&s', 148000.00, '2022-05-12', 6, 4, 1),
(32, 'Giáo trình Hệ điều hành', 400, 'E2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_1Mch6Wwg8QhEJbBUMkppuOphtbZgPaukIA&s', 142000.00, '2023-07-08', 6, 4, 1),
(33, 'Giáo trình Công nghệ Phần mềm', 380, 'E2', 'https://images.nxbdsh.vn/Picture/2023/3/31/image-20230331100509876.png', 138000.00, '2022-12-15', 5, 4, 1),

-- Tài liệu tham khảo (the_loai_id = 2)
(34, 'Bài tập Lập trình Java cơ bản có Lời giải', 550, 'E3', 'https://m.media-amazon.com/images/S/compressed.photo.goodreads.com/books/1629202926i/43230197.jpg', 185000.00, '2024-02-28', 5, 4, 2),
(35, '100 Bài tập Lập trình C++ có Lời giải', 320, 'E3', 'https://zendvn.com/images/sachLapTrinh/UYEfVBswSa.png', 95000.00, '2024-01-15', 6, 4, 2),
(36, '100 Bài tập Lập trình python', 250, 'E3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsTziTmpzTp8PMxJgms_S5btECAS_Bg0pU7A&s', 88000.00, '2023-11-20', 5, 4, 2),
(37, 'Kỷ nguyên trí tuệ nhân tạo', 450, 'E4', 'https://lib.caothang.edu.vn/book_images/26591.jpg', 195000.00, '2024-01-28', 6, 4, 2),
(38, 'Lập trình web với ASP.NET', 500, 'E4', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR43s0armD07AXVair8Pmk7-FoIPS34CSRSXQ&s', 210000.00, '2024-02-20', 5, 4, 2),
(39, 'An toàn dữ liệu', 420, 'E4', 'https://namsaigon.edu.vn/thuvien/wp-content/uploads/2020/11/scan0030.jpg', 185000.00, '2023-06-08', 6, 4, 2),


-- ==================== TOÁN HỌC ====================
-- Giáo trình (the_loai_id = 1)
(40, 'Giáo trình Giải tích 1', 400, 'F1', 'https://images.nxbbachkhoa.vn/Picture/2022/9/20/image-20220920111556313.jpg', 135000.00, '2021-09-05', 4, 5, 1),
(41, 'Giáo trình Giải tích 2', 380, 'F1', 'https://images.nxbbachkhoa.vn/Picture/2022/9/20/image-20220920111757796.jpg', 130000.00, '2022-02-18', 4, 5, 1),
(42, 'Giáo trình Đại số Tuyến tính', 350, 'F1', 'https://images.vnuhcmpress.edu.vn/Picture/2023/dai-so-tuyen-tinh.jpg', 120000.00, '2022-01-18', 4, 5, 1),
(43, 'Giáo trình Xác suất Thống kê', 380, 'F2', 'https://images.vnuhcmpress.edu.vn/Picture/2024/6/19/image-20240619110648888.jpg', 128000.00, '2023-06-22', 5, 5, 1),
(44, 'Giáo trình Toán Rời rạc', 300, 'F2', 'https://images.vnuhcmpress.edu.vn/Picture/2024/8/22/image-20240822143027869.jpg', 115000.00, '2022-11-08', 6, 5, 1),


-- ==================== KHOA HỌC ====================
-- Giáo trình (the_loai_id = 1)
(45, 'Giáo trình Vật lý Đại cương Tập 1', 420, 'F4', 'https://images.vnuhcmpress.edu.vn/Picture/2023/vat-ly-dai-cuong.jpg', 145000.00, '2021-07-14', 4, 6, 1),
(46, 'Giáo trình Cơ học Lượng tử', 450, 'F4', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQOpjUeGfNqYhaSVVUv7I9LYBwP9OwSVM4Qcw&s', 165000.00, '2022-04-08', 7, 6, 1),
(47, 'Giáo trình Hóa học Đại cương', 360, 'G1', 'https://images.nxbxaydung.com.vn/Picture/2021/12/9/image-2021120911041172.jpg', 130000.00, '2022-04-25', 7, 6, 1),
(48, 'Giáo trình Hóa Hữu cơ', 480, 'G1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS5V3RjCdXDM_xgIhwtObRm4ccAlyusxuyrSw&s', 165000.00, '2023-02-14', 7, 6, 1),
(49, 'Giáo trình Hóa Vô cơ', 420, 'G1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQJP9cB9PKYycp9uFB_vkQmxGJAHFY_HbcYdw&s', 155000.00, '2022-09-18', 7, 6, 1),
(50, 'Giáo trình Sinh học Đại cương', 400, 'G3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRD0CpGM0xDhsol1GP48WMCvfRzPJBGMa2w2w&s', 138000.00, '2022-06-15', 4,6, 1),
(51, 'Giáo trình Di truyền học', 350, 'G3', 'https://images.nxbbachkhoa.vn/Picture/2024/11/13/image-20241113154323947.jpg', 125000.00, '2023-01-22', 7, 6, 1),
(52, 'Giáo trình Sinh học Phân tử', 380, 'G3', 'https://images.vnuhcmpress.edu.vn/Picture/2023/sinh-hoc-phan-tu.jpg', 142000.00, '2023-04-10', 7, 6, 1),

-- ==================== NGOẠI NGỮ ====================
-- Giáo trình (the_loai_id = 1)
(53, 'English Vocabulary in use', 280, 'H1', 'https://gln.edu.vn/wp-content/uploads/2020/10/4-cuon-giao-trinh-hoc-tieng-anh-giao-tiep-khong-the-thieu-02.png', 98000.00, '2023-08-10', 4, 7, 1),
(54, 'Cambridge English Skills: Real Listening & Speaking', 300, 'H1', 'https://gln.edu.vn/wp-content/uploads/2020/10/4-cuon-giao-trinh-hoc-tieng-anh-giao-tiep-khong-the-thieu-03.png', 105000.00, '2023-10-15', 4, 7, 1),


-- ==================== LUẬT ====================
-- Giáo trình (the_loai_id = 1)
(55, 'Giáo trình Luật Dân sự Việt Nam', 550, 'I1', 'https://images.vnuhcmpress.edu.vn/Picture/2023/luat-dan-su-tap-1.jpg', 175000.00, '2023-07-08', 10, 8, 1),
(56, 'Giáo trình Luật Hình sự Việt Nam', 480, 'I1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQrjY1F_S8a5m6THVG6SNY3g8uatnwrZGWyQ&s', 168000.00, '2022-12-15', 10, 8, 1),
(57, 'Giáo trình Luật Kinh tế', 420, 'I1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ46pZisgt6yPE9koV2lze3XYApLdKt7E4_gA&s', 155000.00, '2024-03-20', 10, 8, 1),
(58, 'Giáo trình Luật Hiến pháp', 380, 'I2', 'https://nxbtuphap.moj.gov.vn/noidung/tintuc/PublishingImages/luat%205_2019052337.jpg', 145000.00, '2023-02-28', 10, 8, 1),


-- ==================== KHOA HỌC ====================
-- Giáo trình (the_loai_id = 1)
(59, 'Giáo trình Giải phẫu học', 650, 'J1', 'https://images.vnuhcmpress.edu.vn/Picture/2023/7/26/image-20230726090551402.jpg', 280000.00, '2021-03-15', 9, 6, 1),
(60, 'Giáo trình Sinh lý học Y khoa', 520, 'J1', 'https://images.vnuhcmpress.edu.vn/Picture/2024/10/28/image-20241028143618997.jpg', 245000.00, '2022-06-20', 9, 6, 1),


-- ==================== KỸ THUẬT ====================
-- Giáo trình (the_loai_id = 1)
(61, 'Giáo trình Cơ học Kỹ thuật', 420, 'K1', 'https://images.nxbbachkhoa.vn/Picture/2024/4/10/image-20240410191545208.jpg', 148000.00, '2022-03-20', 6, 9, 1),
(62, 'Giáo trình Sức bền Vật liệu', 380, 'K1', 'https://images.vnuhcmpress.edu.vn/Picture/2023/2013-09-09-12-44-05_SUC-BEN-VAT-LIEU-large.jpgg', 138000.00, '2022-08-14', 6, 9, 1),
(63, 'Giáo trình Điện tử Cơ bản', 350, 'K1', 'https://images.nxbxaydung.com.vn/Picture/2025/3/27/image-20250327112607445.png', 125000.00, '2023-01-10', 6, 9, 1),

-- ==================== TRIẾT HỌC ====================
-- Giáo trình (the_loai_id = 1)
(64, 'Giáo trình Triết học Mác-Lênin', 400, 'L1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxYpnmpo8xSTig0h_ejSkVPdwvE6SxbqORDg&s', 125000.00, '2022-01-15', 8, 10, 1),
(65, 'Giáo trình Lịch sử Triết học', 450, 'L1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSE7GvxjMh8k4Pm7q66C6ZgY1IWtwP6jO_x7g&s', 138000.00, '2021-09-20', 8, 10, 1),

-- Tài liệu tham khảo (the_loai_id = 2)
(66, 'Tư tưởng Hồ Chí Minh', 380, 'L2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJ1zhAVwCl8_8KWB6U9qDRgBxy8gO_JNPbjg&s', 95000.00, '2020-05-19', 8, 10, 2);
create table account
(
    account_id bigint auto_increment
        primary key,
    password   varchar(255) not null,
    role       varchar(255) not null,
    username   varchar(255) not null,
    constraint UKgex1lmaqpg0ir5g1f5eftyaa1
        unique (username)
);

create table doc_gia
(
    tien_ky_quy        decimal(38, 2) not null,
    account_id         bigint         not null,
    doc_gia_id         bigint auto_increment
        primary key,
    ngay_sinh          datetime(6)    not null,
    dia_chi            varchar(255)   not null,
    email              varchar(255)   not null,
    so_dien_thoai      varchar(255)   not null,
    ten_doc_gia        varchar(255)   not null,
    trang_thai_doc_gia varchar(255)   not null,
    constraint UK181xkx72sq1gva6osiaxoovdr
        unique (account_id),
    constraint UK2pq6tc4ufuxpyoe65hcwr1i72
        unique (email),
    constraint UKlp8f5bb77jjxo2fj5htv00th0
        unique (so_dien_thoai),
    constraint FK8sso4ijnbotkqlh6e0gr4bqqg
        foreign key (account_id) references account (account_id)
);

create table linh_vuc
(
    linh_vuc_id  bigint auto_increment
        primary key,
    ten_linh_vuc varchar(255) not null
);

create table nha_xuat_ban
(
    nha_xuat_ban_id  bigint auto_increment
        primary key,
    dia_chi          varchar(255) not null,
    ten_nha_xuat_ban varchar(255) not null
);

create table tac_gia
(
    ngay_thang_nam_sinh datetime(6)  null,
    tac_gia_id          bigint auto_increment
        primary key,
    dia_chi             varchar(255) null,
    noi_lam_viec        varchar(255) null,
    ten_tac_gia         varchar(255) not null
);

create table the_loai
(
    the_loai_id  bigint auto_increment
        primary key,
    ten_the_loai varchar(255) not null
);

create table sach
(
    gia_tien        decimal(15, 2) not null,
    so_trang        int            not null,
    linh_vuc_id     bigint         not null,
    nam_xuat_ban    datetime(6)    not null,
    nha_xuat_ban_id bigint         not null,
    sach_id         bigint auto_increment
        primary key,
    the_loai_id     bigint         not null,
    anh_bia         varchar(255)   not null,
    kho_sach        varchar(255)   not null,
    ten_sach        varchar(255)   not null,
    constraint FK5p8sdg0mvyxuwduai1n8fmbgd
        foreign key (nha_xuat_ban_id) references nha_xuat_ban (nha_xuat_ban_id),
    constraint FKfbd36vlmaloqjnaasyr4jp438
        foreign key (the_loai_id) references the_loai (the_loai_id),
    constraint FKqhuee84699q2kqtsr52kli04g
        foreign key (linh_vuc_id) references linh_vuc (linh_vuc_id)
);

create table ban_sao_sach
(
    ban_sao_sach_id         bigint auto_increment
        primary key,
    sach_id                 bigint       not null,
    tinh_trang_ban_sao_sach varchar(255) not null,
    trang_thai_ban_sao_sach varchar(255) not null,
    constraint FKjg86cmgxgu16h277cbfj9501t
        foreign key (sach_id) references sach (sach_id)
);

create table sach_tac_gia
(
    sach_id         bigint not null,
    sach_tac_gia_id bigint auto_increment
        primary key,
    tac_gia_id      bigint not null,
    constraint FK92k87r7rjd4r4a0690cefuk94
        foreign key (tac_gia_id) references tac_gia (tac_gia_id),
    constraint FKfl0rp68p6n5stmodpl0w7mwwd
        foreign key (sach_id) references sach (sach_id)
);

create table the_thu_vien
(
    so_luong_sach_duoc_muon int          not null,
    doc_gia_id              bigint       not null,
    ngay_cap                datetime(6)  not null,
    ngay_het_han            datetime(6)  not null,
    the_thu_vien_id         bigint auto_increment
        primary key,
    trang_thai              varchar(255) not null,
    constraint UKr43e50j0m6fpehflsivhkgt2r
        unique (doc_gia_id),
    constraint FK8c84flb18flid0j5nr4s5vhjx
        foreign key (doc_gia_id) references doc_gia (doc_gia_id)
);

create table phieu_muon
(
    ngay_muon             datetime(6)  not null,
    phieu_muon_id         bigint auto_increment
        primary key,
    the_thu_vien_id       bigint       not null,
    trang_thai_phieu_muon varchar(255) not null,
    constraint FK2fo0r1wi4jkup5vrxb8jac2f8
        foreign key (the_thu_vien_id) references the_thu_vien (the_thu_vien_id)
);

create table chi_tiet_muon_tra
(
    tien_phat            decimal(38, 2) not null,
    ban_sao_sach_id      bigint         not null,
    chi_tiet_muon_tra_id bigint auto_increment
        primary key,
    han_tra              datetime(6)    not null,
    ngay_tra             datetime(6)    not null,
    phieu_muon_id        bigint         not null,
    tinh_trang_khi_tra   varchar(255)   not null,
    constraint FK7pywgat8p7h4gwwtrbgs9307m
        foreign key (phieu_muon_id) references phieu_muon (phieu_muon_id),
    constraint FKge8twsaskg3hnce97rvje554b
        foreign key (ban_sao_sach_id) references ban_sao_sach (ban_sao_sach_id)
);


package org.example.apiquanlythuvien.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.entity.ChiTietMuonTra;
import org.example.apiquanlythuvien.data.entity.PhieuMuon;
import org.example.apiquanlythuvien.defaults.Const;
import org.example.apiquanlythuvien.responsitory.ChiTietMuonTraRepository;
import org.example.apiquanlythuvien.responsitory.PhieuMuonRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OverdueScheduler {

    private final ChiTietMuonTraRepository chiTietMuonTraRepository;
    private final PhieuMuonRepository phieuMuonRepository;

    /**
     * Runs daily at midnight to check for overdue loans.
     */
    @Scheduled(cron = "0 * * ? * *")
    @Transactional
    public void checkOverdueLoans() {
        log.info("Running overdue check scheduler...");

        Date today = new Date();
        List<String> statuses = List.of(Const.PHIEUMUON_CT_BORROWED, Const.PHIEUMUON_CT_OVERDUE);

        // Find all ChiTietMuonTra where hanTra < today AND status is DANG_MUON or
        // QUA_HAN
        List<ChiTietMuonTra> overdueDetails = chiTietMuonTraRepository.findOverdue(statuses, today);

        for (ChiTietMuonTra chiTiet : overdueDetails) {
            // Update status to QUA_HAN if it was DANG_MUON
            if (Const.PHIEUMUON_CT_BORROWED.equals(chiTiet.getTinhTrangKhiTra())) {
                chiTiet.setTinhTrangKhiTra(Const.PHIEUMUON_CT_OVERDUE);
            }

            // Calculate objective overdue fine: 10% of book price * total overdue days
            BigDecimal giaTien = chiTiet.getBanSaoSach().getSach().getGiaTien();
            long diffMillis = today.getTime() - chiTiet.getHanTra().getTime();
            long overdueDays = diffMillis / (1000 * 60 * 60 * 24);

            if (overdueDays > 0) {
                BigDecimal dailyFine = giaTien.multiply(new BigDecimal("0.10"));
                BigDecimal totalFine = dailyFine.multiply(BigDecimal.valueOf(overdueDays));

                if (chiTiet.getTienPhat() == null || chiTiet.getTienPhat().compareTo(totalFine) != 0) {
                    chiTiet.setTienPhat(totalFine);
                    chiTietMuonTraRepository.save(chiTiet);
                }
            } else if (Const.PHIEUMUON_CT_BORROWED.equals(chiTiet.getTinhTrangKhiTra())) {
                chiTietMuonTraRepository.save(chiTiet);
            }

            PhieuMuon phieuMuon = chiTiet.getPhieuMuon();
            if (!Const.PHIEUMUON_OVERDUE.equals(phieuMuon.getTrangThaiPhieuMuon())) {
                phieuMuon.setTrangThaiPhieuMuon(Const.PHIEUMUON_OVERDUE);
                phieuMuonRepository.save(phieuMuon);
            }
        }

        log.info("Overdue check completed. Processed {} overdue items.", overdueDetails.size());
    }
}

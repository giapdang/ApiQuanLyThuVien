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

        // Find all ChiTietMuonTra where hanTra < today AND status is DANG_MUON
        List<ChiTietMuonTra> overdueDetails = chiTietMuonTraRepository
                .findByTinhTrangKhiTraAndHanTraBefore(Const.PHIEUMUON_CT_BORROWED, today);

        for (ChiTietMuonTra chiTiet : overdueDetails) {
            // Update status to QUA_HAN
            chiTiet.setTinhTrangKhiTra(Const.PHIEUMUON_CT_OVERDUE);

            // Calculate overdue fine: 10% of book price * overdue days
            BigDecimal giaTien = chiTiet.getBanSaoSach().getSach().getGiaTien();
            long diffMillis = today.getTime() - chiTiet.getHanTra().getTime();
            long overdueDays = diffMillis / (1000 * 60 * 60 * 24);

            if (overdueDays > 0) {
                BigDecimal dailyFine = giaTien.multiply(new BigDecimal("0.10"));
                chiTiet.setTienPhat(dailyFine.multiply(BigDecimal.valueOf(overdueDays)));
            }

            chiTietMuonTraRepository.save(chiTiet);

            // Update parent PhieuMuon to QUA_HAN if not already
            PhieuMuon phieuMuon = chiTiet.getPhieuMuon();
            if (!Const.PHIEUMUON_OVERDUE.equals(phieuMuon.getTrangThaiPhieuMuon())) {
                phieuMuon.setTrangThaiPhieuMuon(Const.PHIEUMUON_OVERDUE);
                phieuMuonRepository.save(phieuMuon);
            }

            log.info("Marked ChiTietMuonTra ID {} as overdue with fine {}",
                    chiTiet.getChiTietMuonTraId(), chiTiet.getTienPhat());
        }

        log.info("Overdue check completed. Processed {} overdue items.", overdueDetails.size());
    }
}

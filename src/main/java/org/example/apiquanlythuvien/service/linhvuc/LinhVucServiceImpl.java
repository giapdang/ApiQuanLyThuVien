package org.example.apiquanlythuvien.service.linhvuc;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.entity.LinhVuc;
import org.example.apiquanlythuvien.data.request.CreateLinhVucAdminRequest;
import org.example.apiquanlythuvien.data.request.UpdateLinhVucAdminRequest;
import org.example.apiquanlythuvien.data.response.LinhVucAdminResponse;
import org.example.apiquanlythuvien.mapper.LinhVucMapper;
import org.example.apiquanlythuvien.responsitory.LinhVucRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LinhVucServiceImpl implements LinhVucService {

    private final LinhVucRepository linhVucRepository;
    private final LinhVucMapper linhVucMapper;

    @Override
    public Page<LinhVucAdminResponse> getAllLinhVuc(Pageable pageable) {
        return linhVucRepository.findAll(pageable)
                .map(linhVucMapper::toLinhVucAdminResponseMapper);
    }

    @Override
    public void createLinhVuc(CreateLinhVucAdminRequest request) {
        LinhVuc linhVuc = linhVucMapper.toLinhVucMapper(request);
        linhVucRepository.save(linhVuc);
    }

    @Override
    public void updateLinhVuc(UpdateLinhVucAdminRequest request) {
        LinhVuc linhVuc = linhVucRepository.findById(request.getLinhVucId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lĩnh vực với ID: " + request.getLinhVucId()));
        linhVucMapper.updateLinhVucFromRequest(request, linhVuc);
        linhVucRepository.save(linhVuc);
    }

    @Override
    public void deleteLinhVuc(Long id) {
        LinhVuc linhVuc = linhVucRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lĩnh vực với ID: " + id));
        linhVucRepository.delete(linhVuc);
    }
}

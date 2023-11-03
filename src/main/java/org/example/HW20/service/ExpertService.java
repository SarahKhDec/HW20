package org.example.HW20.service;

import org.example.HW20.dto.expert.*;
import org.example.HW20.entity.Expert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface ExpertService {

    Expert create(Expert expert, MultipartFile file) throws IOException;
    Expert findById(Long id);
    GetModifiedExpertTimeDto changePassword(Expert expert);
    Expert findByEmail(String email);
    List<Expert> findAllNewExperts();
    GetModifiedExpertTimeDto confirmExpert(Long id);
    Expert addExpertToSubService(Expert expert);
    Expert removeExpertFromSubService(Expert expert);
    void saveExpertImage(Expert expert) throws IOException;
    Expert findAcceptedExpertByEmail(String email);
    void calculateExpertScore(Expert expert, LocalDateTime localDateTime);
    void updateExpertCredit(Long money, Expert expert);
    void updateExpertScore(Integer score, Expert expert);
    List<Expert> search(String request);
}

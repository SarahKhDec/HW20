package org.example.HW20.service;

import org.example.HW20.dto.expert.*;
import org.example.HW20.entity.Expert;
import org.example.HW20.entity.SubService;
import org.example.HW20.entity.enumeration.ExpertStatus;
import org.example.HW20.exceptions.subservice.SubServiceNotFoundException;
import org.example.HW20.exceptions.user.PasswordNotMatchException;
import org.example.HW20.exceptions.user.UserExistException;
import org.example.HW20.exceptions.user.UserInActiveException;
import org.example.HW20.exceptions.user.UserNotFoundException;
import org.example.HW20.repository.ExpertRepository;
import org.example.HW20.repository.ExpertSearch;
import org.example.HW20.utils.ConvertUrlToByteArray;
import org.example.HW20.utils.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;
    private final ConvertUrlToByteArray convertUrlToByteArray;
    private final SubServiceServiceImpl subServiceService;
    private final ExpertSearch expertSearch;

    @Override
    public Expert create(Expert expert, MultipartFile file) throws IOException {
        try {
            byte[] image = convertUrlToByteArray.converter(file);
            Expert newExpert = expertRepository.save(expert);
            newExpert.setImageUrl(image);
            return expertRepository.save(newExpert);
        } catch (DataIntegrityViolationException e) {
            throw new UserExistException("expert with given email :-- " + expert.getEmail() + " -- has already registered.");
        }
    }

    @Override
    public Expert findById(Long id) {
        return expertRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("no expert found with this ID.")
        );
    }

    @Override
    public Expert findByEmail(String email) {
        return expertRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("no expert found with this email.")
        );
    }

    @Override
    public GetModifiedExpertTimeDto changePassword(Expert expert) {
        Expert newExpert = findByEmail(expert.getEmail());
        if (newExpert.getStatus() == ExpertStatus.INACTIVE) {
            throw new UserInActiveException("your account has been deactivated.");
        }
        if (!expert.getPassword().equals(expert.getRepeatPassword())) {
            throw new PasswordNotMatchException("the entered password does not match with its repetition.");
        }
        newExpert.setPassword(expert.getPassword());
        expertRepository.save(newExpert);
        return new GetModifiedExpertTimeDto(LocalDateTime.now(), "the password has been successfully updated.");
    }

    @Override
    public List<Expert> findAllNewExperts() {
        return expertRepository.findAllByStatus();
    }

    @Override
    public GetModifiedExpertTimeDto confirmExpert(Long id) {
        Expert expert = expertRepository.findExpertById(id).orElseThrow(
                () -> new UserNotFoundException("no new expert found with this ID.")
        );
        expert.setStatus(ExpertStatus.ACCEPTED);
        expertRepository.save(expert);
        return new GetModifiedExpertTimeDto(LocalDateTime.now(), "the expert has been successfully verified.");
    }

    @Override
    public Expert addExpertToSubService(Expert expert) {
        Expert newExpert = findById(expert.getId());
        SubService subService = subServiceService.findById(expert.getSubService().getId());
        newExpert.addSubService(subService);
        return expertRepository.save(newExpert);
    }

    @Override
    public Expert removeExpertFromSubService(Expert expert) {
        Expert newExpert = findById(expert.getId());
        SubService subService = newExpert.getSubServiceSet().stream()
                .filter(t -> Objects.equals(t.getId(), expert.getSubService().getId()))
                .findFirst().orElseThrow(
                        () -> new SubServiceNotFoundException("no sub services with this ID were found for this expert")
                );
        newExpert.removeSubService(subService);
        return expertRepository.save(newExpert);
    }

    @Override
    public void saveExpertImage(Expert expert) throws IOException {
        Expert newExpert = findByEmail(expert.getEmail());
        byte[] data = newExpert.getImageUrl();
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage image = ImageIO.read(bis);
        ImageIO.write(image, "jpg", new File("C:\\Users\\Sarai\\Documents\\HW19\\src\\main\\java\\org\\example\\HW19\\images\\" + newExpert.getLastname() + ".jpg"));
    }

    @Override
    public Expert findAcceptedExpertByEmail(String email) {
        return expertRepository.findByEmailAndStatus(email).orElseThrow(
                () -> new UserNotFoundException("no verified experts were found with this email")
        );
    }

    @Override
    public void calculateExpertScore(Expert expert, LocalDateTime localDateTime) {
        int result = localDateTime.getHour() - LocalDateTime.now().getHour();
        Integer expertScore = expert.getScore();
        int newScore = 0;
        if (result < 0) {
            for (int i=0; i < Math.abs(result); i++) {
                newScore = expertScore - 1;
            }
        } else {
            newScore = expertScore;
        }
        if (newScore < 0) {
            expert.setStatus(ExpertStatus.INACTIVE);
        }
        expert.setScore(newScore);
        expertRepository.save(expert);
    }

    @Override
    public void updateExpertCredit(Long money, Expert expert) {
        Long credit = expert.getCredit();
        credit += money;
        expert.setCredit(credit);
        expertRepository.save(expert);
    }

    @Override
    public void updateExpertScore(Integer score, Expert expert) {
        Integer point = expert.getScore();
        point += score;
        expert.setScore(point);
        expertRepository.save(expert);
    }

    @Override
    public List<Expert> search(String request) {
        List<SearchCriteria> params = new ArrayList<>();
        if (request != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|!|>=|<=)(\\w+?),");
            Matcher matcher = pattern.matcher(request + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }
        return expertSearch.searchUser(params);
    }
}

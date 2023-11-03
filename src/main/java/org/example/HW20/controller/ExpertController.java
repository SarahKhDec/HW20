package org.example.HW20.controller;

import org.example.HW20.dto.expert.*;
import org.example.HW20.entity.Expert;
import org.example.HW20.mappers.ExpertMapperImpl;
import org.example.HW20.service.ExpertServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/expert")
public class ExpertController {

    private final ExpertServiceImpl expertService;
    private final ExpertMapperImpl expertMapper;

    @PostMapping("/create")
    public GetExpertDto create(@Valid @ModelAttribute CreateExpertDto expertDto, BindingResult result,
                               @RequestParam("image") MultipartFile file) throws IOException, NoSuchMethodException, MethodArgumentNotValidException {
        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(new MethodParameter(getClass().getDeclaredMethod("create", CreateExpertDto.class, BindingResult.class, MultipartFile.class), 0), result);
        }
        Expert expert = expertMapper.expertDtoToExpert(expertDto);
        return expertMapper.expertToDto(expertService.create(expert, file));
    }

    @GetMapping("/findById/{id}")
    public GetExpertDto findById(@PathVariable Long id) {
        return expertMapper.expertToDto(expertService.findById(id));
    }

    @PutMapping("/changePassword")
    public GetModifiedExpertTimeDto changePassword(@Valid @RequestBody ChangeExpertPasswordDto changeExpertPasswordDto) {
        Expert expert = expertMapper.changePasswordDtoToExpert(changeExpertPasswordDto);
        return expertService.changePassword(expert);
    }

    @GetMapping("/findAllNewExpert")
    public List<GetExpertDto> findAllNewExpert() {
        return expertMapper.expertListToDtoList(expertService.findAllNewExperts());
    }

    @PutMapping("/confirmExpert/{id}")
    public GetModifiedExpertTimeDto confirmExpert(@PathVariable Long id) {
        return expertService.confirmExpert(id);
    }

    @PutMapping("/addExpertToSubService")
    public GetExpertSubServiceDto addExpertToSubService(@Valid @RequestBody ExpertToSubServiceDto expertToSubServiceDto) {
        Expert expert = expertMapper.expertSubServiceToExpert(expertToSubServiceDto);
        return expertMapper.expertToGetSubServiceDto(expertService.addExpertToSubService(expert));
    }

    @PutMapping("/removeExpertFromSubService")
    public GetExpertSubServiceDto removeExpertFromSubService(@Valid @RequestBody ExpertToSubServiceDto expertToSubServiceDto) {
        Expert expert = expertMapper.expertSubServiceToExpert(expertToSubServiceDto);
        return expertMapper.expertToGetSubServiceDto(expertService.removeExpertFromSubService(expert));
    }

    @PostMapping("/saveExpertImage")
    public ResponseEntity<Object> saveExpertImage(@Valid @RequestBody SaveImageDto saveImageDto) throws IOException {
        Expert expert = expertMapper.saveImageDtoToExpert(saveImageDto);
        expertService.saveExpertImage(expert);
        return new ResponseEntity<>("expert photo saved successfully", HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<GetExpertDto> findAll(@RequestParam(value = "search", required = false) String search) {
        return expertMapper.expertListToDtoList(expertService.search(search));
    }
}

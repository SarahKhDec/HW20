package org.example.HW20.controller;

import org.example.HW20.dto.captcha.CaptchaDto;
import org.example.HW20.dto.captcha.CaptchaResponseDto;
import org.example.HW20.dto.order.GetOrderWithOfferDto;
import org.example.HW20.dto.payment.CreatePaymentDto;
import org.example.HW20.dto.payment.ValidPaymentDto;
import org.example.HW20.entity.Orders;
import org.example.HW20.exceptions.captcha.ForbiddenException;
import org.example.HW20.mappers.OrderMapperImpl;
import org.example.HW20.service.OrderServiceImpl;
import org.example.HW20.service.ValidateCaptcha;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payment")
public class PaymentController {

    private final OrderServiceImpl orderService;
    private final ValidateCaptcha service;
    private final OrderMapperImpl orderMapper;
    CreatePaymentDto createPayment;

    @PostMapping("/captcha")
    @ResponseStatus(code = HttpStatus.OK)
    public CaptchaResponseDto validateCaptcha(@RequestBody CaptchaDto dto) throws ForbiddenException {
        final boolean isValidCaptcha = service.validateCaptcha(dto.getCaptchaResponse());
        if (!isValidCaptcha) {
            throw new ForbiddenException("INVALID_CAPTCHA");
        }
        return new CaptchaResponseDto("OK");
    }

    @PostMapping("/pay/1")
    public GetOrderWithOfferDto pay(@Valid @RequestBody CreatePaymentDto createPaymentDto) {
        Orders orders = orderMapper.paymentDtoToOrder(createPaymentDto);
        return orderMapper.orderToOfferDto(orderService.paymentWithOneMethod(orders));
    }

    @GetMapping("/redirect")
    public RedirectView redirect() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:63342/final_project_phase3/static/index.html?_ijt=91dnf238ruslbdq19tnelobr66&_ij_reload=RELOAD_ON_SAVE");
        return redirectView;
    }

    @PostMapping("/pay/2")
    public String onlinePayment(@Valid @RequestBody CreatePaymentDto createPaymentDto) {
        Orders orders = orderMapper.paymentDtoToOrder(createPaymentDto);
        orderService.checkDoneOrder(orders);
        createPayment = createPaymentDto;
        return redirect().getUrl();
    }

    @PostMapping("/finalPayment")
    @CrossOrigin(origins = "http://localhost:63342")
    public ResponseEntity<String> finalPayment(@Valid @RequestBody ValidPaymentDto validPaymentDto, BindingResult result) {
        Orders orders = orderMapper.paymentDtoToOrder(createPayment);
        if(!result.hasErrors()) {
            if (orderService.paymentWithTwoMethod(orders)) {
                System.out.println(validPaymentDto);
                System.out.println("OK");
                String responseData = "Success!";
                return ResponseEntity.ok(responseData);
            }
        } else {
            result.getAllErrors().forEach(
                    i -> System.out.println(i.getDefaultMessage())
            );
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

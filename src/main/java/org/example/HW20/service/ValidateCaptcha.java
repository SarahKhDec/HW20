package org.example.HW20.service;

import org.example.HW20.utils.CaptchaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ValidateCaptcha {

    private final RestTemplate template;
    @Value("${google.recaptcha.verification.endpoint}")
    String recaptchaEndpoint;
    @Value("${google.recaptcha.secret}")
    String recaptchaSecret;

    public ValidateCaptcha(final RestTemplateBuilder templateBuilder) {
        this.template = templateBuilder.build();
    }

    public boolean validateCaptcha(final String captchaResponse) {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("secret", recaptchaSecret);
        params.add("response", captchaResponse);

        CaptchaResponse apiResponse = null;
        try {
            apiResponse = template.postForObject(recaptchaEndpoint, params, CaptchaResponse.class);
        } catch (final RestClientException e) {
            System.out.println(e.getMessage());
        }

        return Objects.nonNull(apiResponse) && apiResponse.isSuccess();
    }
}

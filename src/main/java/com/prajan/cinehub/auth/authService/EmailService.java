package com.prajan.cinehub.auth.authService;


import com.resend.Resend;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class EmailService {

//    private final JavaMailSender mailSender;

    private final Resend resend;

    public EmailService(
            @Value("${resend.api-key}") String apiKey
    ) {
        this.resend = new Resend(apiKey);
    }
    public void sendOtp(String to, String otp) {

//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setTo(to);
//        message.setSubject("CineHub Email Verification");
//
//        message.setText("""
//                Welcome to CineHub!
//
//                Your OTP is:
//
//                %s
//
//                This OTP is valid for 5 minutes.
//
//                If you didn't request this verification, please ignore this email.
//
//                Regards,
//                CineHub Team
//                """.formatted(otp));
        SendEmailRequest request = SendEmailRequest.builder()
                .from("onboarding@resend.dev")
                .to(to)
                .subject("Your CineHub OTP")
                .html("""
                        <h2>CineHub Email Verification</h2>
                        <p>Your OTP is:</p>
                        <h1>%s</h1>
                        <p>This OTP is valid for 5 minutes.</p>
                        """.formatted(otp))
                .build();

        SendEmailResponse response = resend.emails().send(request);

        System.out.println("Email sent: " + response.getId());


    }
}

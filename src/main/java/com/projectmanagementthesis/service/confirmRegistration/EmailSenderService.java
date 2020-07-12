package com.projectmanagementthesis.service.confirmRegistration;

import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailSenderService {

	public static void send(String userMail, String token) throws IOException {

		Email from = new Email("beatricebaldassarre86@gmail.com");
		Email to = new Email(userMail);

		String subject = "Confirm your account";
		Content content = new Content("text/plain", "Thank you for registering. Please click on the below link to activate your account.\n" 
										+ "http://localhost:3000/confirmAccount/"
										+ token);

		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid("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");
		Request request = new Request();

		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());

		Response response = sg.api(request);

		System.out.println(response.getStatusCode());
		System.out.println(response.getHeaders());
		System.out.println(response.getBody());
	}
	
}

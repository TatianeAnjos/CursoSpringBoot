package com.tatianeanjos.services;

import org.springframework.mail.SimpleMailMessage;

import com.tatianeanjos.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}

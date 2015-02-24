package list6_1.user.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author wglee21g@gmail.com
 */
public class MockMailSender implements MailSender {
	@Override
	public void send(SimpleMailMessage simpleMessage) throws MailException {

	}

	@Override
	public void send(SimpleMailMessage... simpleMessages) throws MailException {

	}
}

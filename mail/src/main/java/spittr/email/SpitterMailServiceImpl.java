package spittr.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import spittr.domain.Spittle;

@Component
public class SpitterMailServiceImpl implements SpitterMailService {

	private JavaMailSender mailSender;

	@Autowired
	public SpitterMailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * spittr.email.SpitterMailService#sendSimpleSpittleEmail(java.lang.String,
	 * spittr.domain.Spittle)
	 */
	@Override
	public void sendSimpleSpittleEmail(String to, Spittle spittle) {
		SimpleMailMessage message = new SimpleMailMessage();
		String spitterName = spittle.getSpitter().getFullName();
		message.setFrom("linkaicheng2048@163.com");
		message.setTo(to);
		message.setSubject("主题");
		message.setText("你好！请于明天下午16:00 准时到B01会议室召开技术讨论会。");
		mailSender.send(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * spittr.email.SpitterMailService#sendSpittleEmailWithAttachment(java.lang.
	 * String, spittr.domain.Spittle)
	 */
	@Override
	public void sendSpittleEmailWithAttachment(String to, Spittle spittle) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		String spitterName = spittle.getSpitter().getFullName();
		helper.setFrom("linkaicheng2048@163.com");
		helper.setTo(to);
		helper.setSubject("New spittle from " + spitterName);
		helper.setText("<html><body><a href='https://www.baidu.com'>aaaaa</a></body></html>", true);
		// ClassPathResource couponImage = new
		// ClassPathResource("/collateral/coupon.jpg");
		// helper.addAttachment("Coupon.jpg", couponImage);
		mailSender.send(message);
	}

}

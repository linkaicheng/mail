package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan("spittr")
@PropertySource("classpath:mail.properties")
public class MailConfig {

	// @Bean
	// public GreenMailBean greenMail() {
	// GreenMailBean greenMailBean = new GreenMailBean();
	// greenMailBean.setUsers(Arrays.asList("linkaicheng2048:linkaicheng2017@qq.com"));
	// return greenMailBean;
	// }

	@Bean
	public JavaMailSenderImpl mailSender(Environment env) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(env.getProperty("mailserver.host"));
		mailSender.setPort(Integer.parseInt(env.getProperty("mailserver.port")));
		mailSender.setUsername(env.getProperty("mailserver.username"));
		mailSender.setPassword(env.getProperty("mailserver.password"));
		return mailSender;
	}

}

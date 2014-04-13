package org.hycoder.shorturl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * to be changed to JUnit
 * */
public class URLTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		URLDAO urlDAO = (URLDAO) context.getBean("urlDAO");
		URLMap urlMap = new URLMap(0, "a", "http://www.qq.com");
		int id = urlDAO.insert(urlMap);
		if (id > 0) {
			URLMap url = urlDAO.findById(id);
			System.out.println("urlmap: " + url);
		}
	}

}

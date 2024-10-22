package util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextUtil {
	private static ApplicationContext context;

	// Static block to initialize the context when the class is loaded
	static {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	// Method to retrieve beans by type
	public static <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}

	// Method to retrieve beans by name
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}
}

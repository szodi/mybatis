package mytest;

import java.util.Set;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{
	public static void main(String[] args)
	{
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ctx.register(AppConfig.class);
		MyDAO dao = ctx.getBean(MyDAO.class);
		Set<String> duplicatedFiles = dao.getChecksums();
		duplicatedFiles.forEach(System.out::println);
		ctx.close();
	}
}

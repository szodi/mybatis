package mytest;

import java.util.Set;

import org.apache.ibatis.session.SqlSession;

public class Main
{
	public static void main(String[] args)
	{
		SqlSession session = ConnectionFactory.getSession().openSession();
		MyDAO dao = session.getMapper(MyDAO.class);
		Set<String> duplicatedFiles = dao.getChecksums();
		duplicatedFiles.forEach(System.out::println);
		session.close();
	}
}

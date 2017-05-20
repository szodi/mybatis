package mytest;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class ConnectionFactory
{
	private static SqlSessionFactory sqlMapper;

	static
	{
		try
		{
			DataSource dataSource = getDataSource();
			TransactionFactory trxFactory = new JdbcTransactionFactory();
			Environment env = new Environment("dev", trxFactory, dataSource);
			Configuration config = new Configuration(env);
			config.addMapper(MyDAO.class);
			sqlMapper = new SqlSessionFactoryBuilder().build(config);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession()
	{
		return sqlMapper;
	}

	public static DataSource getDataSource()
	{
		Properties props = new Properties();
		try
		{
			props.load(ConnectionFactory.class.getResourceAsStream("database.properties"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		PooledDataSource dataSource = new PooledDataSource();
		dataSource.setDriver(props.getProperty("database.driver"));
		dataSource.setUrl(props.getProperty("database.url"));
		dataSource.setUsername(props.getProperty("database.username"));
		dataSource.setPassword(props.getProperty("database.password"));
		return dataSource;
	}
}
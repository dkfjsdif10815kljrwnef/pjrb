package pjrb.cms.errorlog.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

import egovframework.com.cmm.service.EgovProperties;

/**
 * 에러로그 관리
 * 프로젝트레인보우 개발팀 권대성
 * 2021.02.18
 * version 1.0
 *  
 *   수정일            수정자          수정내용
 *  ---------------------------------------------
 *  2021.02.18  권대성          최초 생성 
 *  
 */

public class ConnLog4j2 {

	private static interface Singleton {
        final ConnLog4j2 INSTANCE = new ConnLog4j2();
    }

    private final DataSource dataSource;

    private ConnLog4j2() {
        Properties properties = new Properties();
        properties.setProperty("user", EgovProperties.getProperty("Globals.UserName"));
        properties.setProperty("password", EgovProperties.getProperty("Globals.Password")); // or get properties from some configuration file
//        properties.setProperty("driverClassName", "com.ibm.db2.jcc.DB2Driver");

        try {
   loadDriver(EgovProperties.getProperty("Globals.DriverClassName"));
  } catch (SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
        
        GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<PoolableConnection>();
        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
        		EgovProperties.getProperty("Globals.Url"), properties
        );
        new PoolableConnectionFactory(
                connectionFactory, pool, null, "SELECT 1", 3, false, false, Connection.TRANSACTION_READ_COMMITTED
        );

        this.dataSource = new PoolingDataSource(pool);
    }

    public static Connection getDatabaseConnection() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }

    private static void loadDriver(String driver)
            throws SQLException
        {
            try
            {
                Class.forName(driver).newInstance();
            }
            catch(Exception e)
            {
                throw new SQLException("Unable to load driver: " + driver);
            }
        }
}

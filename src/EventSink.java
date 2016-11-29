import org.apache.log4j.Logger;

import com.thinkive.base.jdbc.connection.Configure;

public class EventSink
{
	
	private static Logger logger = Logger.getLogger(EventSink.class);
	
	/**
	 * 在服务器程序启动时调用，用于初始化服务器
	 */
	public static void onStart()
	{
		
		logger.info("系统开始启动[Java]");
		// 读入数据库配置文件
		//Configure.getInstance();
		
	}
	
	/**
	 * 在服务器停止时调用，用于释放服务器资源
	 */
	public static void onStop()
	{
		logger.info("系统开始停止[Java]");
		// 关闭数据源的连接
		Configure.getInstance().destroyDataSource();
	}
}

package biz.iws.interceptor.log;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Out info log
 *
 * @author Tomohiro Akinaga
 */
@Component
public class MethodInOutLogInterceptor implements MethodInterceptor {

	/** Logger container */
	private Map<String, Logger> loggers = new HashMap<>();

	/*
	 * (非 Javadoc)
	 *
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		Class<?> c = invocation.getMethod().getDeclaringClass();

		if (c.getAnnotation(CancelIOLog.class) == null
				&& invocation.getMethod().getAnnotation(CancelIOLog.class) == null) {

			String fqcn = c.getName();
			Logger logger = getLogger(fqcn);

			logger.info("process - {}", invocation.getMethod().getName());
			logger.info("args:");
			for (Object argument : invocation.getArguments()) {
				if (argument instanceof String) {
					logger.info(argument.toString());
				} else {
					logger.info(ReflectionToStringBuilder.toString(argument));
				}
			}

			long start = Calendar.getInstance().getTimeInMillis();

			Object obj = invocation.proceed();

			logger.info("return:");
			logger.info(ReflectionToStringBuilder.toString(obj));
			logger.info("execution time. : [{}ms]", Calendar.getInstance().getTimeInMillis() - start);

			return obj;
		} else {

			return invocation.proceed();
		}

	}

	/**
	 * @param fqcn fully qualified class name
	 * @return {@link Logger}
	 */
	private Logger getLogger(String fqcn) {
		Logger logger = null;
		if (loggers.containsKey(fqcn)) {
			logger = loggers.get(fqcn);
		} else {
			logger = LoggerFactory.getLogger(fqcn);
			loggers.put(fqcn, logger);
		}
		return logger;
	}

}

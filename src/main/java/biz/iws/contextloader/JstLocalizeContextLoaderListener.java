package biz.iws.contextloader;

import java.util.TimeZone;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

public class JstLocalizeContextLoaderListener extends ContextLoaderListener {

	private static final String TIME_ZONE = "Asia/Tokyo";

	public JstLocalizeContextLoaderListener() {
		super();
		TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));
	}

	public JstLocalizeContextLoaderListener(WebApplicationContext context) {
		super(context);
		TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));
	}

}

package biz.iws.contextloader;

import java.util.TimeZone;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

public class GmtLocalizeContextLoaderListener extends ContextLoaderListener {

	private static final String TIME_ZONE = "GMT";

	public GmtLocalizeContextLoaderListener() {
		super();
		TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));
	}

	public GmtLocalizeContextLoaderListener(WebApplicationContext context) {
		super(context);
		TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));
	}

}

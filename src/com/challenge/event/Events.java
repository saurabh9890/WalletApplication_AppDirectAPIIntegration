package com.challenge.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.challenge.utils.EventXML;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public class Events {
	public final static String SUBSCRIPTION_ORDER = "SUBSCRIPTION_ORDER";
	public final static String SUBSCRIPTION_CHANGE = "SUBSCRIPTION_CHANGE";
    public final static String SUBSCRIPTION_CANCEL = "SUBSCRIPTION_CANCEL";
    public final static String USER_ASSIGNMENT = "USER_ASSIGNMENT";
	public final static String USER_UNASSIGNMENT = "USER_UNASSIGNMENT";
	
	private final static String CUNSUMER_KEY = "appdirectintchallenge-6262";
	private final static String CUNSUMER_SECRET = "BJCPUbflMwiDQBsp";
	
	private String baseURL = "https://www.appdirect.com/api/integration/v1/events/%s";
	
	public String FectchEvent(String token) {
		String eventURL = String.format(baseURL, token);
		OAuthConsumer consumer = new DefaultOAuthConsumer(CUNSUMER_KEY, CUNSUMER_SECRET);
		URL url = null;
		BufferedReader reader = null;
		StringBuilder buff = new StringBuilder();

		try {
			url = new URL(eventURL);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			consumer.sign(request);
			request.connect();

			reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line = "";
			while((line = reader.readLine()) != null) {
				buff.append(line);
			}
		} catch (MalformedURLException e) {
			return e.toString();
		} catch (IOException e) {
			return e.toString();
		} catch (OAuthMessageSignerException e) {
			return e.toString();
		} catch (OAuthExpectationFailedException e) {
			return e.toString();
		} catch (OAuthCommunicationException e) {
			return e.toString();
		} finally {
			try {
				if (reader != null) reader.close();
			} catch(IOException ignore) {
			}
		}

		return buff.toString();
	}
	
	public String handleEvent(EventXML eventXML) {
		String eventType = eventXML.getEventType();
		if (eventType.equals(Events.SUBSCRIPTION_ORDER)) {
			return createOrder(eventXML);
		} else if (eventType.equals(Events.SUBSCRIPTION_CANCEL)) {
			return cancelOrder(eventXML);
		} else if (eventType.equals(Events.USER_ASSIGNMENT)) {
			return assignUser(eventXML);
		} else if (eventType.equals(Events.USER_UNASSIGNMENT)) {
			return unassignUser(eventXML);
		}
		return "Err unknown event type: "+ eventType;
	}

	private String createOrder(EventXML eventXML) {
		boolean success = true;
		String id = eventXML.getEmail();
		return String.format("<result><success>%b</success><accountIdentifier>%s</accountIdentifier></result>",
					success, id);
	}
	
	private String cancelOrder(EventXML eventXML) {
		boolean success = true;
		String id = eventXML.getEmail();
		return String.format("<result><success>%b</success><accountIdentifier>%s</accountIdentifier></result>", 
					success, id);
	}
	
	private String assignUser(EventXML eventXML) {
		boolean success = true;
		String id = eventXML.getEmail();
		String sub = eventXML.getSubscription();
		return "<result><success>true</success></result>";
	}
	
	private String unassignUser(EventXML eventXML) {
		String id = eventXML.getEmail();
		String sub = eventXML.getSubscription();
		return "<result><success>true</success></result>";
	}
}
package com.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.challenge.event.Events;
import com.challenge.utils.EventXML;

@Controller
@RequestMapping("/subscribe")
public class SubscriptionController {
	//http://appdirectintegration.cloudbees.com/Appdirect_challenge/subscribe/create?token={token}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public @ResponseBody String subscripe(@RequestParam(value="token", defaultValue="") String token) {

		String xml = "Err";
		Events e = new Events();
		xml = e.FectchEvent(token);
		xml = e.handleEvent(new EventXML(xml));

		if (xml.startsWith("Err")) xml = "<Err>Please log in AppDirect and subscribe this App.</Err>";
		return xml;
	}

 //http://appdirectintegration.cloudbees.com/Appdirect_challenge/subscribe/cancel?token={token}
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public @ResponseBody String unsubscripe(@RequestParam(value="token", defaultValue="") String token) {
		String xml = "Err";

		Events e = new Events();
		xml = e.FectchEvent(token);
		xml = e.handleEvent(new EventXML(xml));

		if (xml.startsWith("Err")) xml = "<Err>Please log in AppDirect and unsubscribe this App.</Err>";
		return xml;
	} 

}

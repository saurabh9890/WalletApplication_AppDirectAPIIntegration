package com.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.challenge.event.Events;
import com.challenge.utils.EventXML;

@Controller
public class AssignmentController {
	String test ="";
	//http://appdirectintegration.cloudbees.com/Appdirect_challenge/assign?token={token}
	@RequestMapping(value="/assign", method=RequestMethod.GET) 
	public @ResponseBody String assign(@RequestParam(value="token", defaultValue="") String token){
		String xml = "<Invalid></Invalid>";
		Events e = new Events();
		xml = e.FectchEvent(token);
		xml = e.handleEvent(new EventXML(xml));

		return xml;
	}
	
	//http://appdirectintegration.cloudbees.com/Appdirect_challenge/unassign?token={token}
	@RequestMapping(value="/unassign", method=RequestMethod.GET) 
	public @ResponseBody String unassign(@RequestParam(value="token", defaultValue="") String token){
		String xml = "<Invalid></Invalid>";
		Events e = new Events();
		xml = e.FectchEvent(token);
		xml = e.handleEvent(new EventXML(xml));

		return xml;
	}
}

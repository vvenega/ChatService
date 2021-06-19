package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class ChatServiceController {
	
	//private static final String VALID_CUSTOMER = "http://192.168.1.66:4200";
	private static final String VALID_CUSTOMER = "*";

	@Autowired
	private  SimpMessagingTemplate template;
	
	@Autowired
	ChatServiceDaoProxy proxy;

	
   /* public ChatServiceController(SimpMessagingTemplate template){
        this.template = template;
    }*/

	@CrossOrigin(origins = VALID_CUSTOMER)
    @MessageMapping("/send/message")
    public void onReceivedMesage(MessageBean message){
		System.err.println("On ReceivedMessage:Mensaje:"+message.getMessage());
		System.err.println("On ReceivedMessage:Receiver:"+message.getReceiver());
		System.err.println("On ReceivedMessage:Sender:"+message.getSender());
		System.err.println("On ReceivedMessage:channel:"+message.getChannel());
		System.err.println("On id:"+message.getId());
        //this.template.convertAndSend("/mychannel_"+message.getReceiver(),  new SimpleDateFormat("HH:mm:ss").format(new Date())+"- "+message.getMessage());
		
		try {
		ObjectMapper mapper = new ObjectMapper();
		String strMessage = mapper.writeValueAsString(message);
		this.template.convertAndSend("/mychannel_"+message.getReceiver(), strMessage);
		}catch(Exception e) {
			e.printStackTrace();
		}
        
        String name = message.getNamesender();
        
        if(name!=null) {
		name = name.replace("/", "-");
		name = name.replace("\\", "-");
		message.setNamesender(name);
        }
        
        name = message.getNamereceiver();
        if(name!=null) {
    		name = name.replace("/", "-");
    		name = name.replace("\\", "-");
    		message.setNamereceiver(name);
            }
		
		String msg = message.getMessage();
		msg = msg.replace("/", "-");
		msg = msg.replace("\\", "-");
		message.setMessage(msg);
		
		
        try {

        proxy.broadcastMessage(message.getMessage(),message.getSender(),
        		message.getReceiver(),message.getChannel(),message.getId(),
        		message.getObjectid(),message.getNamesender(),message.getNamereceiver());
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
	@CrossOrigin(origins = VALID_CUSTOMER)
    @MessageMapping("/broadcast/{message}")
    public void broadcastMessage(@PathVariable String message){
    	System.err.println("Send Message:Mensaje:"+message);
        this.template.convertAndSend("/chat",  new SimpleDateFormat("HH:mm:ss").format(new Date())+"- "+message);
    }
	
	@CrossOrigin(origins = VALID_CUSTOMER)
	@GetMapping("/GetConversation/{idconversation}")
	public List<MessageBean> getConversation(@PathVariable String idconversation){
		return proxy.getConversation(idconversation);
	}
	
	@CrossOrigin(origins = VALID_CUSTOMER)
	@GetMapping("/GetConversations/{username}")
	public List getConversations(@PathVariable String username){
		List lstReturn= proxy.getConversations(username);
		System.err.println("lstReturn:"+lstReturn);
		
		
		
		/*System.err.println(((UserConversationBean)lstReturn.get(0)).getId());
		System.err.println(((UserConversationBean)lstReturn.get(0)).getIdConversation());
		System.err.println(((UserConversationBean)lstReturn.get(0)).getName());
		System.err.println(((UserConversationBean)lstReturn.get(0)).getUsername());*/
		return lstReturn;
	}
	
	
	
}


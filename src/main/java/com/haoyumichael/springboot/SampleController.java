package main.java.com.haoyumichael.springboot;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import main.java.com.haoyumichael.springboot.entity.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
 
@RestController
@RequestMapping("/sample")
public class SampleController {
	
	@Autowired
	AccountRepository ar;
 
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @RequestMapping("/")
    @ResponseBody
    public String index() throws JsonParseException, JsonMappingException, IOException {
    	log.info("Working Directory = " +
                 System.getProperty("user.dir"));
    	ObjectMapper mapper = new ObjectMapper();
//    	Account account = mapper.readValue(new File("account.json"), Account.class);
    	String userData = "{\"username\": \"haou yu\",\"user_password\": \"AaPassword1\", \"business\": \"true\"}";
    	Map<String, Account> acctMap = mapper.readValue("{\"user1\":" + userData + "}", new TypeReference<Map<String, Account>>() {});
//    	System.out.println(account.getUsername());
//    	System.out.println(account.getPassword());
//    	System.out.println(account.isBusiness());
    	
    	Account at = mapper.readValue(new File("account.json"), Account.class);
    	
    	log.info("output as json....");
    	System.out.println(mapper.writeValueAsString(at));

//    	Map<String, String> map = mapper.readValue(userData, Map.class);
    	for(String key : acctMap.keySet()) {
        	System.out.println(key);
        	Account account = acctMap.get(key);
        	System.out.println(account.getUsername());
        	System.out.println(account.getPassword());
        	System.out.println(account.isBusiness());

    	}
    	
    	ObjectMapper mapper2 = new ObjectMapper();  
        try {  
            ObjectNode node = mapper.createObjectNode();  
            node.put("status", "status");  
            node.set("value", mapper.readTree("{\"foo\":\"bar\"}"));
      
            System.out.println(mapper.writeValueAsString(node));  
        } catch (JsonProcessingException e) {  
            throw new RuntimeException(e);  
        } catch (IOException e) {  
            throw new RuntimeException(e);  
        }  

    	System.out.println(ar);
    	ar.save(at);
    	ar.save(mapper.readValue(userData, Account.class));
    	for(Account a : ar.findAll()) {
    		System.out.println(a.getUsername());
    		System.out.println(a.getPassword());
    	}
//    	map.put("amnt", "386.33");
//    	mapper.writeValue(new File("acct-modified.json"), map);
    	
        return "hello";
    }
    
    @RequestMapping("/t")
    public String indexTemplate(Model model) {
        model.addAttribute("message", "HELLO!");
        return "hello";
    }
 
}
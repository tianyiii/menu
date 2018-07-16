package com.abc.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.menu.storage.Merchant;
import com.abc.menu.storage.MerchantMongoRepository;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MerchantController.class);
	
	@Autowired
	private MerchantMongoRepository merchantMongoRepository;

    @GetMapping
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @GetMapping(path="/test")
    public ResponseEntity<?> test() {
    	return new ResponseEntity<String>("12345", HttpStatus.OK);
    }
    
    @PostMapping(path="/{merchantId}/upload", consumes="application/json")
    public ResponseEntity<?> uploadMenu(@PathVariable String merchantId, @RequestBody Merchant merchant) {
    	// TODO: Remove
    	LOGGER.info(merchantMongoRepository.findMerhantByName(merchant.getName()).toString());
    	return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    @PostMapping(path="/register", consumes="application/json")
    public ResponseEntity<?> register(@RequestBody Merchant merchant) {
    	Merchant registerd = merchantMongoRepository.save(merchant);
    	if (registerd != null) {
    		LOGGER.info(String.format("Registered merchant: %s", registerd.toString()));
        	return new ResponseEntity<String>(HttpStatus.OK);
    	}
    	return new ResponseEntity<String>("Failed to register.", HttpStatus.BAD_REQUEST);
    }

}

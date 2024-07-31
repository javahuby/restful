package com.te.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.guest.GService;
import com.test.guest.GVo;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/Restful")
public class RestfulController {
	
	@Autowired
	private GService service;
	
	RestfulController() {
		System.out.println("===> RestfulController 확인");
	}
	
	@GetMapping(value="/list", produces="application/json; charset=UTF-8")	
	private String list(Model  model, GVo vo) {
		System.out.println("===>list 확인!");
		
        /*
         *  start , ch1, ch2, pageSize
         *  값은 Client Controller 쪽에서 넘겨준것을 받아서 사용한다.
         */
    			
		int totalCount = service.totalCount(vo);
			
		model.addAttribute("li",service.list(vo));
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("totalCount", totalCount);
		resultMap.put("li", service.list(vo));
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			return  mapper.writeValueAsString(resultMap);
		}catch( Exception e ){
			e.printStackTrace();
			return null;    
		}					
    }
}

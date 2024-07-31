package com.tes2.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ReController {
	
	ReController() {
		System.out.println("===> ReController 확인");
	}
	
	@GetMapping("/list")
	String Test2( Model  model , HttpServletRequest request, GVo vo) throws Exception { 
		
		String URL = "http://127.0.0.1:8777/Restful/list";
        String RESULTTYPE ="json" ;
      System.out.println("==> 확인: " +  vo.getCh1() + ":" + vo.getCh2());
        
        String ch1 ="";
        String ch2 ="";
        String start = "1";
        int pageSize = 10;
        if (vo.getCh1() != null) {
            ch1 = request.getParameter("ch1") ;
            ch2 = request.getParameter("ch2") ;
            start = request.getParameter("start") ;
        }else {
            ch1 = "" ;
            ch2 = "" ;
            start = "1" ;
        }
        System.out.println("==> 확인: " +  vo.getCh1() + ":" + vo.getCh2() + ":" + start);
		StringBuilder urlBuilder = new StringBuilder(URL); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode(RESULTTYPE, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("ch1","UTF-8") + "=" + URLEncoder.encode(ch1, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("ch2","UTF-8") + "=" + URLEncoder.encode(ch2, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("start","UTF-8") + "=" + URLEncoder.encode(start, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("pageSize","UTF-8") + "=" + pageSize); 
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
       
        System.out.println(sb);

    

        try {
	       	 
            JSONParser jsonParser = new JSONParser();
            
            // JSON데이터를 넣어 JSON Object 로 만들어 준다.
             JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
                          
             String totalCountStr = String.valueOf(jsonObject.get("totalCount")) ; 
                                         
             
            // items의 배열을 추출 
            JSONArray InfoArray = (JSONArray) jsonObject.get("li");

            System.out.println("* items *");

            List<GVo> li = new ArrayList<GVo>(); 
            
            for(int i=0; i< InfoArray.size(); i++){
            	GVo m = new GVo();
         	   
                System.out.println("=items_"+i+" ===========================================");
                 
                //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                JSONObject object = (JSONObject) InfoArray.get(i);
     
                m.setIdx( String.valueOf(object.get("idx")));
                m.setName( String.valueOf(object.get("name")));
                m.setMemo(String.valueOf(object.get("memo")));
                m.setAge(Integer.parseInt(String.valueOf(object.get("age"))));
                m.setRegdate(String.valueOf(object.get("regdate")));                
                li.add(m);      
                System.out.println("성공 ==>"+ i + ":" + m );
            } 
                		
    		int pageListSize = 10;
    		
    		int totalCount = Integer.parseInt(String.valueOf(totalCountStr));
      
    		int  totalPage =(int) (Math.ceil((double) totalCount / pageSize));  // 전체페이지 수 
    		int  currentPage = (Integer.parseInt(start) / pageSize) + 1;  // 현재페이지 
    		
    		int  lastPage = (totalPage - 1) * pageSize + 1;  // 마지막 페이지
    		
    	    int  listStartPage = (currentPage - 1) / pageListSize * pageListSize + 1;   // 하단 번호 시작
    	    int  listEndPage = listStartPage + pageListSize - 1;   // 하단 번호 끝
    		
          
    	    model.addAttribute("pageSize", pageSize);
    	    model.addAttribute("pageListSize", pageListSize);
    	    model.addAttribute("totalCount", totalCount);
    	    model.addAttribute("totalPage", totalPage);
    	    model.addAttribute("start", Integer.parseInt(start));
    	    model.addAttribute("currentPage", currentPage);
    	    model.addAttribute("listStartPage", listStartPage);
    	    model.addAttribute("listEndPage", listEndPage);
    	    model.addAttribute("lastPage", lastPage);
    	    model.addAttribute("ch1", ch1);
    	    model.addAttribute("ch2", ch2);
    	       	       		 
            model.addAttribute("totalCount", totalCount);
            model.addAttribute("li", li);
   
       } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
		return "list";   	
	}

}

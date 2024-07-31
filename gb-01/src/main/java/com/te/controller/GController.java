package com.te.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.guest.GService;
import com.test.guest.GVo;

@RequestMapping("/guest")
@Controller
public class GController {

	@Autowired
	private GService service;
	
	GController() {
		System.out.println("===> GController 확인");
	}
	
	@GetMapping("/Gform")
	private String Gform(GVo vo) {
		System.out.println("==>Gform 확인!");
		
		String [] firstName = {"김", "나", "박", "이", "오", "수", "남궁", "채", "유", "성", "송"};
		String [] LastName = {"하니", "수리", "소기", "수기", "서기", "수현", "권", "십자", "구리", "군자"};
		int [] age = {11, 12, 13, 14, 15, 16, 17, 18};
		String [] memo = {"page test1", "page test2", "page test3", "page test4", "page test5", 
				"page test6", "page test7", "page test8", "page test9", "page test10" };
		
		Random random = new Random();
		
		for (int i=1; i <= 125; i ++ ) {
		
		int firstNameArr = random.nextInt(11);
		int LastNameArr = random.nextInt(10);
		int ageArr = random.nextInt(8);
		int memoArr = random.nextInt(10);
		
		vo.setName(firstName[firstNameArr] + LastName[LastNameArr]);
		vo.setAge(age[ageArr]);
		vo.setMemo(memo[memoArr]);
		
		service.insert(vo);
		}
		
		return "redirect:/guest/Glist";
	}
	
	@GetMapping("/Glist")	
	private String Glist(Model  model, GVo vo) {
		System.out.println("===>Glist 확인!");
		
		int start = 0;
		int pageSize = 10;
		int pageListSize = 10;
		
		int totalCount = service.totalCount(vo);
		
		if (vo.getStart() == 0) {
			start = 1;
		} else {
			start = vo.getStart();
		}
		
		int  end = start + pageSize - 1 ;
		int  totalPage =(int) (Math.ceil((double) totalCount / pageSize));  // 전체페이지 수 
		int  currentPage = (start / pageSize) + 1;  // 현재페이지 
		int  lastPage = (totalPage - 1) * pageSize + 1;  // 마지막 페이지
	    int  listStartPage = (currentPage - 1) / pageListSize * pageListSize + 1;   // 하단 번호 시작
	    int  listEndPage = listStartPage + pageListSize - 1;   // 하단 번호 끝
		
		// 1. 페이지 사이즈
		model.addAttribute("pageSize", pageSize);
		// 2. 페이지 list 사이즈
		model.addAttribute("pageListSize", pageListSize);
		// 3. 전체 레코드 수
		model.addAttribute("totalCount", totalCount);
		// 4. 총페이지수
		model.addAttribute("totalPage", totalPage);
		// 5. 현재레코드
		model.addAttribute("start", start);
		// 6. 현재 페이지
		model.addAttribute("currentPage", currentPage);
		// 7. 하단 가로 시작
		model.addAttribute("listStartPage", listStartPage);
		// 8. 하단 가로 끝
		model.addAttribute("listEndPage", listEndPage);
		// 9. 마지막 페이지
		model.addAttribute("lastPage", lastPage);
		
		vo.setStart(start);
		vo.setEnd(end);
		
		model.addAttribute("ch1", vo.getCh1());
		model.addAttribute("ch2", vo.getCh2());
		model.addAttribute("li", service.list(vo));

		return "guest/Glist.html";		
	}	
	
	@GetMapping("/Gedit")
	private String Gedit(Model model, GVo vo) {
		System.out.println("==>Gedit 확인!");
		GVo m = new GVo();
		model.addAttribute("m", service.edit(vo));
		System.out.println("상세보기:" + vo);
		return "guest/Gedit";
	}
	
	@GetMapping("/update")
	private String update(GVo vo, Model model) {
		System.out.println("==>update 확인!");
		service.update(vo);
		System.out.println("수정:" + vo);
		return "redirect:/guest/Glist?idx=" + vo.getIdx();
	}
	
	@GetMapping("/delete")
	private String delete(GVo vo) {
		System.out.println("==>delete 확인!");
		service.delete(vo);
		System.out.println("삭제 :" + vo);
		return "redirect:/guest/Glist";
	}
}

package com.rar.khbook.servicecenter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rar.khbook.serviceboard.model.vo.EventBoard;
import com.rar.khbook.serviceboard.model.vo.NoticeBoard;
import com.rar.khbook.servicecenter.model.service.ServiceCenterService;
import com.rar.khbook.servicecenter.model.vo.Faq;

@Controller
public class ServiceCenterController {

	@Autowired
	private ServiceCenterService service;
	
	@RequestMapping("/service/serviceMain.do")
	public ModelAndView servicePage(ModelAndView mv) {
		System.out.println("보드리스트 가져오는 컨트롤러 실행");
		List<NoticeBoard> nb= service.searchNoticeBoardList();
		System.out.println("보드리스트 받아온 결과물 : "+nb);
		if(nb != null) {
			System.out.println("보드리스트가져오기 성공!");
		}else {
			System.out.println("보드리스트가져오기 실패패패패!");
		}
		mv.addObject("notice",nb);
		mv.setViewName("servicecenter/serviceMain");
		return mv;
	}

	@RequestMapping("/service/eventBoard.do")
	public ModelAndView eventPage(ModelAndView mv) {
			System.out.println("이벤트리스트 가져오는 컨트롤러 실행");
			List<EventBoard> ev= service.searchEventBoardList();
			System.out.println("이벤트리스트 받아온 결과물 : "+ev);
			if(ev != null) {
				System.out.println("이벤트리스트가져오기 성공!");
			}else {
				System.out.println("이벤트리스트가져오기 실패패패패!");
			}
			mv.addObject("event",ev);
			mv.setViewName("/servicecenter/eventBoard");
		return mv;
	}
	
	//faq 시작
	@RequestMapping("/service/faqPage.do")
	public ModelAndView faqPage(ModelAndView mv) {
		List<Faq> list=service.selectFaqList();
		mv.addObject("list", list);
		System.out.println(list);
		mv.setViewName("servicecenter/faqPage");
		return mv;
	}
	@RequestMapping("/service/inputAskFaq.do")
	public ModelAndView inputAskFaq(ModelAndView mv,@RequestParam Map param) {
		
		String type31=(String)param.get("type31");
		if(type31.equals("주문결제")) {
			type31="1";
		}else if(type31.equals("배송수령안내")) {
			type31="2";
		}else if(type31.equals("반품교환환불")) {
			type31="3";
		}else if(type31.equals("중고장터")) {
			type31="4";
		}else if(type31.equals("ebook")) {
			type31="5";
		}else if(type31.equals("기타")) {
			type31="6";
		}
		
		
		param.put("type31", type31);
		String askFaq=(String)param.get("askFaq");
		param.put("askFaq", askFaq);
		
		
		int result =service.inputAskFaq(param);
		
		String msg="";
		String loc="";
		if(result>0) {
			msg="FAQ 질문이 등록되었습니다.";
		}else {
			msg="FAQ 질문 등록이 실패되었습니다.";
		}
		
		loc="/service/faqPage.do";
		
		mv.addObject("msg", msg);
		mv.addObject("loc",loc);
		mv.setViewName("common/msg");
		return mv;
		
	}
	@RequestMapping("/service/searchFaq.do")
	@ResponseBody
	public List<Faq> searchFaq(@RequestParam Map param) {
		
		String type30=(String)param.get("type30");
		if(type30.equals("주문결제")) {
			type30="1";
		}else if(type30.equals("배송수령안내")) {
			type30="2";
		}else if(type30.equals("반품교환환불")) {
			type30="3";
		}else if(type30.equals("중고장터")) {
			type30="4";
		}else if(type30.equals("ebook")) {
			type30="5";
		}else if(type30.equals("기타")) {
			type30="6";
		}
		

		param.put("type30", type30);
		String searchFaq=(String)param.get("searchFaq");
		param.put("searchFaq", searchFaq);
		
		System.out.println(type30);
		System.out.println(searchFaq);
		
		List<Faq> list=service.searchFaq(param);
		
		System.out.println(list);
		
		return list;
	}


}

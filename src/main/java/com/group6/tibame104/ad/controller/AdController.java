package com.group6.tibame104.ad.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group6.tibame104.ad.model.AdService;
import com.group6.tibame104.ad.model.AdVO;
import com.group6.tibame104.announcement.model.AnnouncementVO;

@Controller
@RequestMapping("/back-end/ad")
@MultipartConfig(fileSizeThreshold = 100 * 1024 * 1024, maxFileSize = 1000 * 1024 * 1024, maxRequestSize = 100 * 100
* 1024 * 1024)
public class AdController {
	@Autowired
	private AdService adSvc;
	
	@PostMapping("/findByPrimaryKey")
	public String findByPrimaryKey(
			Model model, 
			@RequestParam("adSerialID") Integer adSerialID
		) {
		
		List<String> errorMsgs = new LinkedList<String>();
		model.addAttribute("errorMsgs", errorMsgs);
		
		AdVO adVO = adSvc.getOneAd(adSerialID);
		model.addAttribute("adVO",adVO);
		return "/back-end/ad/listOneAd2";
	}
	
	@PostMapping("/getOneForUpdate")
	public String getOneForUpdate(
			Model model,
			@RequestParam("adSerialID") Integer adSerialID
			) {
		
		AdVO adVO = adSvc.getOneAd(adSerialID);
		model.addAttribute("adVO",adVO);
		
		return  "/back-end/ad/update_ad_input2.jsp";
	}
	
	
			
	@PostMapping("/update")
	public String update(
			HttpSession session,
			Model model, 
			@RequestParam("adSerialID") Integer adSerialID,
			@RequestParam("adTitle") String adTitle,
			@RequestParam("adType") String adType,
			@RequestParam("adDescribe") String adDescribe,
			@RequestParam("adPhoto") Part adPhoto,
			@RequestParam("adStartDate") Date adStartDate,
			@RequestParam("adEndDate") Date adEndDate
			) throws IOException {
		
		// 圖片相關
				byte[] adPhoto1 = null;
				if (adPhoto.getSize() == 0) {
					adPhoto = null;
				} else {
					InputStream in = adPhoto.getInputStream();
					BufferedInputStream bis = new BufferedInputStream(in);
					adPhoto1 = new byte[bis.available()];
					bis.read(adPhoto1);
					bis.close();
					in.close();
				}
				
				List<String> errorMsgs = new LinkedList<String>();
				session.setAttribute("errorMsgs", errorMsgs);
				
				adSvc.updateAd(adSerialID, adTitle, adType, adDescribe, adPhoto1, adStartDate, adEndDate);
				return "/back-end/ad/listAllAd2";
	}
	
	@PostMapping("/insert")
	public String insert(
			HttpSession session,
			Model model,
			@RequestParam("administratorID") String administratorID,
			@RequestParam("groupBuyID") String groupBuyID,
			@RequestParam("adTitle") String adTitle,
			@RequestParam("adType") String adType,
			@RequestParam("adDescribe") String adDescribe,
			@RequestParam("adStartDate") Date adStartDate,
			@RequestParam("adEndDate") Date adEndDate,
			@RequestParam("adPhoto") Part adPhoto
			) throws IOException {
		
		// 圖片相關
		byte[] adPhoto1 = null;
		if (adPhoto.getSize() == 0) {
			adPhoto = null;
		} else {
			InputStream in = adPhoto.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(in);
			adPhoto1 = new byte[bis.available()];
			bis.read(adPhoto1);
			bis.close();
			in.close();
		}
		
		List<String> errorMsgs = new LinkedList<String>();
		session.setAttribute("errorMsgs", errorMsgs);
		
		adSvc.addAd(null, null, adTitle, adType, adDescribe, adStartDate, adEndDate, adPhoto1);
		return "/back-end/ad/listAllAd2.jsp";
	}
	
	@PostMapping("/delete")
	public String delete(
			Model model,
			@RequestParam("adSerialID") Integer adSerialID
		) {
		adSvc.deleteAd(adSerialID);
		return "/back-end/ad/listAllAd2.jsp";
	}


	//查詢全部
		@GetMapping("/getAll")
		public String getAll(HttpSession session, Model model) {
			List<AdVO> list = adSvc.getAll();
//			session.setAttribute("list", list);
			model.addAttribute("list", list);
			return "/back-end/ad/select_page2";
		}



}
	
	
	
	
	
	
	
	
	
	

	

		


		

			
			
			
			
			
			
			

	


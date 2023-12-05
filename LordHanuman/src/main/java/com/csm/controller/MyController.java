package com.csm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csm.model.Block;
import com.csm.model.Citizen;
import com.csm.model.Panchayat;
import com.csm.model.Village;
import com.csm.repository.BlockRepo;
import com.csm.repository.CitizenRepo;
import com.csm.repository.PanchayatRepo;
import com.csm.repository.VillageRepo;
import com.csm.service.CitizenService;
import com.csm.service.ExcelExportDynamic;

@Controller

public class MyController {

	@Autowired
	private BlockRepo blockRepo;
	
	@Autowired
	private PanchayatRepo panchayatRepo;
	
	@Autowired
	private VillageRepo villageRepo;
	
	@Autowired
	private CitizenRepo citizenRepo;
	
	@Autowired
	private CitizenService citizenService;
	
	@Autowired
	private ExcelExportDynamic excelDynamic;
	
	@GetMapping("/getmap")
	public String get(Model model) {
		model.addAttribute("blockList", blockRepo.findAll());
		model.addAttribute("citizenList", citizenRepo.findAll());
		return "Krishna";
	}
	
	@RequestMapping("/getPanchayatByBlock")
	public void getPanchayatByBlock(@RequestParam("blockId") Integer blockId, HttpServletResponse resp)
			throws IOException {
		PrintWriter pw = resp.getWriter();
		List<Panchayat> list = panchayatRepo.getPanchayatByBlock(blockId);
		String b = "<option value='" + 0 + "'>" + "--Select--" + "</option>";
		for (Panchayat p : list) {
			b += "<option value='" + p.getPanchayatId() + "'>" + p.getPanchayatName() + "</option>";
		}
		pw.print(b);
	}
	
	@RequestMapping("/getVillageById")
	public void getVillageById(@RequestParam("blockId") Integer blockId,
			@RequestParam("panchayatId") Integer panchayatId, HttpServletResponse resp) throws IOException {
		System.out.println(blockId + "::" + panchayatId);
		PrintWriter pw = resp.getWriter();
		List<Village> distList = villageRepo.getVillageById(blockId,panchayatId);
		System.out.println(distList);
		String b = "<option value='" + 0 + "'>" + "--Select--" + "</option>";
		for (Village dist : distList) {
			b += "<option value='" + dist.getVillageId() + "'>" + dist.getVillageName() + "</option>";
		}
		pw.print(b);

	}
	
	@PostMapping("/saveCitizen")
	public String saveCitizen (@RequestParam ("blockName") Integer blockId,
			@RequestParam ("panchayatName") Integer panchayatId,
			@RequestParam("villageName") Integer villageId,
			@RequestParam("citizenName") String citizenId,
			@RequestParam("citizenAge") Integer citizenAgeId,
			@RequestParam("citizenAnnualIncome") Integer citizenAnnualIncomeId){
		
		Citizen c = new Citizen();
		Block b = blockRepo.findById(blockId).get();
		c.setBlock(b);
		Panchayat p = panchayatRepo.findById(panchayatId).get();
		c.setPanchayat(p);
		Village v = villageRepo.findById(villageId).get();
		c.setVillage(v);
		c.setCitizenName(citizenId);
		c.setCitizenAge(citizenAgeId);
		c.setCitizenAnnualIncome(citizenAnnualIncomeId);
		
		Citizen c1 = citizenService.saveCitizen(c);
		System.out.println(c1 +"{}{}{}");
		return "redirect:/getmap";
	}
	
	@GetMapping("download/excel")
	 public void exportToExcel(HttpServletResponse res) throws IOException, ClassNotFoundException {
		 DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
		 res.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=Country_info"+currentDateTime+".xlsx";
			res.setHeader(headerKey, headerValue);			
			Object printHeader[] = {false,"PrintHeader", "Country Info"};
			
			String headers[] = {"Name", "BlockName", "PanchayatName","Village Name" ,"Age", "Annual Income" , "Eligibility" ,"Loan Amount" ,"Monthly Installment"};
			int columnWidth[] = {2, 2, 3, 3 ,4 ,3 ,2 , 3}; // 1 == 4 chars
			List<Object[]> data = citizenRepo.findAll().stream().map(m-> new Object[] {m.getBlock().getBlockName(),m.getPanchayat().getPanchayatName(),
					m.getVillage().getVillageName() , m.getCitizenAge(),m.getCitizenAnnualIncome()
					}).collect(Collectors.toList()); 
			 
			
		 excelDynamic.exportDynamic(res, printHeader, headers, columnWidth, data);
	 }
	
	
	
}

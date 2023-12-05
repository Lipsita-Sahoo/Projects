package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.model.AssesmentMark;
import com.example.demo.model.BatchAllocate;
import com.example.demo.model.EmployeeMaster;
import com.example.demo.repository.BatchRepository;
import com.example.demo.service.AssesmentMarkService;
import com.example.demo.service.BatchAllocateService;
import com.example.demo.service.EmployeeService;

@Controller
public class MyController {

	@Autowired
	private BatchRepository batchRepo;

	@Autowired
	private BatchAllocateService batchAllocateService;
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private AssesmentMarkService markService;
	
	@Autowired
	private EntityManager em;

	@GetMapping("/getForm")
	public String getForm(Model model) {
		model.addAttribute("batchList", batchRepo.findAll());
		return "MarkEntryForm";
	}

	@GetMapping("/getTechByBatchId")
	public void getTechnologyByBatchId(@RequestParam("batchId") Integer batchId, HttpServletResponse resp)
			throws IOException {
		PrintWriter pw = resp.getWriter();
		List<BatchAllocate> list = batchAllocateService.getTechByBatch(batchId);
		String b = "<option value='" + 0 + "'>" + "--Select--" + "</option>";
		for (BatchAllocate batchAllocate : list) {
			b += "<option value='" + batchAllocate.getTechnologyId().getTechnologyId() + "'>"
					+ batchAllocate.getTechnologyId().getTechnologyName() + "</option>";
		}
		pw.print(b);

	}

	@GetMapping("/getEmployeeById")
	public void getEmployeeById(@RequestParam("batchId") Integer batchId,
			@RequestParam("technologyId") Integer technologyId, HttpServletResponse resp) throws IOException {
		PrintWriter pw = resp.getWriter();

		List<BatchAllocate> empList = batchAllocateService.getEmpById(batchId, technologyId);
		String b = "<option value='" + 0 + "'>" + "--Select--" + "</option>";
		for (BatchAllocate batchAllocate : empList) {
			b += "<option value='" + batchAllocate.getEmployeeId().getEmployeeId() + "'>"
					+ batchAllocate.getEmployeeId().getEmployeeName() + "</option>";
		}
		pw.print(b);
	}

	@PostMapping("/saveMark")
	public String saveMark(@RequestParam(name = "batchName") Integer batchId,
			@RequestParam(name = "technologyName") Integer technologyId,
			@RequestParam(name = "employeeName") Integer employeeId, @RequestParam(name = "mark") Integer markId,
			RedirectAttributes rd) {

		BatchAllocate batch = new BatchAllocate();
		AssesmentMark mark = new AssesmentMark();
		mark.setEmpId(employeeId);
		mark.setMark(markId);
		AssesmentMark asmark = markService.saveMark(mark);
		System.out.println(asmark + "savemarks");
		rd.addFlashAttribute("SaveMark", "Data Saved Successfully");
		return "redirect:/getForm";
	}
	
	@PostMapping("/getList")
	public String getAllList(Model model) {
		model.addAttribute("batchList", batchRepo.findAll());
		List<Object[]>  resultList =  em.createStoredProcedureQuery("get_list").getResultList();
		model.addAttribute("RESULT", resultList);
		return "MarkEntryForm";
	}
}

package org.zerock.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.MemberVO;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;
import org.zerock.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	@Autowired
	private BoardService service;

	@Autowired
	private MemberService mservice;
	

	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void insert() {
		log.info("register GET");
	}



	@GetMapping("/list")
	public void list(Criteria cri, Model model) {

		log.info("list: " + cri);
		//model.addAttribute를 이용해 불러온 db를 list에 담아 view로 보내줍니다.
		model.addAttribute("list", service.getList(cri));
		int total = service.getTotal(cri);
		log.info("total: " + total);
		model.addAttribute("pageMaker", new PageDTO(cri, total));

	}



	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("==========================");
		log.info("registerPOST: " + board);
		if (board.getAttachList() != null) {
			//board.getAttachList().forEach(attach -> log.info(attach));
		}

		log.info("==========================");
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
	}


	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {

		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
	}


	@PreAuthorize("principal.username == #board.writer")
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		log.info("modify:" + board);

		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/board/list" + cri.getListLink();
	}

	

	@PreAuthorize("principal.username == #writer")
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr, String writer) {

		log.info("remove..." + bno);

		List<BoardAttachVO> attachList = service.getAttachList(bno);

		if (service.remove(bno)) {

			// delete Attach Files
			deleteFiles(attachList);

			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list" + cri.getListLink();
	}

	private void deleteFiles(List<BoardAttachVO> attachList) {

		if (attachList == null || attachList.size() == 0) {
			return;
		}

		log.info("delete attach files...................");
		//log.info(attachList);

		attachList.forEach(attach -> {
			try {
				Path file = Paths.get(
						"D:\\upload\\" + attach.getUploadPath() + "\\" + attach.getUuid() + "_" + attach.getFileName());

				Files.deleteIfExists(file);

				if (Files.probeContentType(file).startsWith("image")) {

					Path thumbNail = Paths.get("D:\\upload\\" + attach.getUploadPath() + "\\s_" + attach.getUuid() + "_"
							+ attach.getFileName());

					Files.delete(thumbNail);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} // end catch
		});// end foreachd
	}

	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno) {

		log.info("getAttachList " + bno);

		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);

	}

	// 회원가입 폼 처리
		@GetMapping("/signup")
		public void signup() {
			log.info("signup get");
		}



		@PostMapping("/signupPost")
		public String signup(MemberVO member) {
			log.info("==========================");
			log.info("signupPost: " + member);
			log.info("==========================");
			
			mservice.create(member);
			return "redirect:/board/list";
		}

		
		@ResponseBody
		@RequestMapping(value = "/idCheck",method = RequestMethod.POST)
		public int postIdCheck(HttpServletRequest req)throws Exception{
			log.info("post idCheck///////////////////////////////////////");
			
			String userid = req.getParameter("userid");
			
			MemberVO idCheck = mservice.idCheck(userid);
			log.info("//////////////////////userid: " + userid);
			log.info("//////////////////////idCheck: " + idCheck);
			int result = 0;

			if(idCheck != null) {
				result =1;
			}
			//log.info(result);
			return result;
		}
	 

}

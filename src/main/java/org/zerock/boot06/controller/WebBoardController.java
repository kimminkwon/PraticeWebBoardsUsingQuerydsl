package org.zerock.boot06.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.boot06.dto.WebBoardDto;
import org.zerock.boot06.dto.WebBoardModifyDto;
import org.zerock.boot06.dto.WebBoardSaveDto;
import org.zerock.boot06.service.WebBoardService;
import org.zerock.boot06.vo.PageMaker;
import org.zerock.boot06.vo.PageVO;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {

    private final WebBoardService service;
    /*
    // 컨트롤러에서의 Paging 처리
    // 전달되는 Data 1) 페이지: 페이지 번호, 페이지당 사이즈  2) 검색 Type, 검색 Keyword
    // @PageableDefault 어노테이션을 사용하면 someurl?page=2&size=30 등으로 접근 가능
    @GetMapping("/list")
    public void list( @PageableDefault(
            direction = Sort.Direction.DESC,
            sort = "bno",
            size = 10,
            page = 0) Pageable pageable ) {


        log.info("list() called..." + pageable);
    }
    */

    // @PageableDefault와 같은 방식으로 접근해도 알아서 PageVO로 처리해서 보내주게 됨
    @GetMapping("/list")
    public void list(@ModelAttribute("pageVO") PageVO vo, Model model) {
        Pageable pageable = vo.makePageable(0, "bno");
        Page<WebBoardDto> listOfDto = service.list(vo);

        log.info("IN CONTROLLER: list() called...");
        log.info("" + pageable);
        log.info("" + listOfDto);
        log.info("TOTAL PAGE NUMBER: " + listOfDto.getTotalPages());

        model.addAttribute("listOfDto", new PageMaker(listOfDto));
    }

    // 새로운 데이터를 저장하는 화면으로 이동
    @GetMapping("/register")
    public void registerGET(@ModelAttribute("dto")WebBoardSaveDto dto) {
        log.info("IN CONTROLLER: registerGET() called...");
    }

    // 데이터를 저장하는 메서드
    @PostMapping("/register")
    public String registerPOST(@ModelAttribute("dto")WebBoardSaveDto dto, RedirectAttributes rttr) {
        log.info("IN CONTROLLER: registerPOST() called...");
        log.info("" + dto);;

        service.save(dto);
        rttr.addFlashAttribute("msg", "success");

        // 리다이렉트를 하지않으면 사용자가 게시물을 여러번 등록 가능!
        return "redirect:/boards/list";
    }

    // 상세 내용 보는 페이지로 연결
    @GetMapping("/view")
    public void view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
        log.info("IN CONTROLLER: view() called...");
        log.info("BNO: " + bno);
        WebBoardDto dto = service.findById(bno);
        log.info("DTO: " + dto);
        model.addAttribute("dto", dto);
    }

    // 상세 내용 보기 + 수정 혹은 삭제로 이동 가능 페이지로 연결
    @GetMapping("/modify")
    public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
        log.info("IN CONTROLLER: modify() called...");
        log.info("BNO: " + bno);
        WebBoardDto dto = service.findById(bno);
        log.info("DTO: " + dto);
        model.addAttribute("dto", dto);
    }

    // modify 페이지에서 수정을 눌렀을 때
    @PostMapping("/modify")
    public String modifyPost(WebBoardModifyDto dto, PageVO vo, RedirectAttributes rttr) {
        log.info("IN CONTROLLER: modifyPost() called...");
        log.info("DTO: " + dto);

        service.modify(dto);
        rttr.addFlashAttribute("msg", "success");
        rttr.addAttribute("bno", dto.getBno());

        // paging과 검색했던 결과를 유지하기 위해
        rttr.addAttribute("page", vo.getPage());
        rttr.addAttribute("size", vo.getSize());
        rttr.addAttribute("type", vo.getType());
        rttr.addAttribute("keyword", vo.getKeyword());

        return "redirect:/boards/view";
    }

    // modify 페이지에서 삭제를 눌렀을 때
    @PostMapping("/delete")
    public String delete(Long bno, PageVO vo, RedirectAttributes rttr) {
        log.info("IN CONTROLLER: delete() called...");
        log.info("BNO: " + bno);
        service.deleteById(bno);

        rttr.addFlashAttribute("msg", "success");

        // paging과 검색했던 결과를 유지하기 위해
        rttr.addAttribute("page", vo.getPage());
        rttr.addAttribute("size", vo.getSize());
        rttr.addAttribute("type", vo.getType());
        rttr.addAttribute("keyword", vo.getKeyword());

        return "redirect:/boards/list";
    }
}

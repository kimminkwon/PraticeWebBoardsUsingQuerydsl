package org.zerock.boot06.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.boot06.dto.WebBoardDto;
import org.zerock.boot06.service.WebBoardService;
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
    public void list(PageVO vo, Model model) {
        Pageable pageable = vo.makePageable(0, "bno");
        log.info("IN CONTROLLER: list() called..." + pageable);
        Page<WebBoardDto> listOfDto = service.list(vo);
        model.addAttribute("listOfDto", listOfDto);
    }

}

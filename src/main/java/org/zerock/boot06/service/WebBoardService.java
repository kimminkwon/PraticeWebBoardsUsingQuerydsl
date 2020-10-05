package org.zerock.boot06.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.boot06.domain.WebBoard;
import org.zerock.boot06.dto.WebBoardDto;
import org.zerock.boot06.dto.WebBoardSaveDto;
import org.zerock.boot06.persistence.WebBoardRepository;
import org.zerock.boot06.vo.PageVO;

import java.util.function.Function;

@RequiredArgsConstructor
@Service
@Log
public class WebBoardService {
    private final WebBoardRepository repository;

    @Transactional
    public Page<WebBoardDto> list(PageVO vo) {
        Pageable pageable = vo.makePageable(0, "bno");
        Page<WebBoard> result = repository.findAll(repository.makePrdicate(vo.getType(), vo.getKeyword()), pageable);
        Page<WebBoardDto> resultOfDto = result.map(new Function<WebBoard, WebBoardDto>() {
            @Override
            public WebBoardDto apply(WebBoard entity) {
                WebBoardDto dto = new WebBoardDto(entity);
                // Conversion logic

                return dto;
            }
        });

        log.info("IN CONTROLLER: list() called...");
        log.info("" + pageable);
        log.info("" + resultOfDto);

        return resultOfDto;
    }

    @Transactional
    public void save(WebBoardSaveDto dto) {
        repository.save(dto.toEntity());
    }

    public WebBoardDto findById(Long bno) {
        WebBoard entity = repository.findById(bno)
                .orElseThrow(() -> new IllegalArgumentException("id가 존재하지 않습니다 id: " + bno));
        return new WebBoardDto(entity);
    }

    @Transactional
    public void deleteById(Long bno) {
        repository.deleteById(bno);
    }
}

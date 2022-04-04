package com.ssafy.boonmoja.api.controller.contents;

import com.ssafy.boonmoja.api.entity.contents.Contents;
import com.ssafy.boonmoja.api.service.ContentsService;
import com.ssafy.boonmoja.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ssafy.boonmoja.utils.ApiUtils.ApiResult;
import static com.ssafy.boonmoja.utils.ApiUtils.success;

@Tag(name = "Contents", description = "컨텐츠 관리 API")
@RestController
@RequestMapping("/api/contents")
@RequiredArgsConstructor
@Slf4j
public class ContentsController {
    
    private final UserService userService;
    private final ContentsService contentsService;
    
    
    /**
     * @param select ["지역", "상호"]
     * @param label  ["관광지", "쇼핑", "숙박", "음식", ... ]
     * @param query  검색어
     */
    @Operation(summary = "컨텐츠 검색", description = "URL에 담긴 변수와 일치하는 항목을 검색합니다.")
    @Parameters({
            @Parameter(name = "select", description = "지역 or 상호"),
            @Parameter(name = "label", description = "[\"관광지\", \"쇼핑\", \"숙박\", \"음식\", ... ]"),
            @Parameter(name = "query", description = "검색할 단어")
    })
    @GetMapping("/search/{select}/{label}/{query}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ApiResult<List<Contents>> getContentsList( @PathVariable String select,
                                                      @PathVariable String label,
                                                      @PathVariable String query) {
        List<Contents> contentsList = null;
        if (select.equals("지역")) {
            contentsList = contentsService.getContentsEqualLabelAndAddress(label, query);
        } else if (select.equals("상호")) {
            contentsList = contentsService.getContentsEqualLabelAndTitle(label, query);
        }
        log.trace("Get {} list contents label {} include {} : {}", select, label, query, contentsList);
        log.info("{}", query);
        return success(contentsList);
    }
    
    
}

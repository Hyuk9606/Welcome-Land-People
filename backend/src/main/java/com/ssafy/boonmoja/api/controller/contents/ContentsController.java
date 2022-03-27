package com.ssafy.boonmoja.api.controller.contents;

import com.ssafy.boonmoja.api.entity.contents.Contents;
import com.ssafy.boonmoja.api.service.ContentsService;
import com.ssafy.boonmoja.api.service.UserService;
import com.ssafy.boonmoja.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contents")
@RequiredArgsConstructor
@Slf4j
public class ContentsController {
    
    private final UserService userService;
    private final ContentsService contentsService;
    
    
    /**
     * @param select  ["지역", "상호"]
     * @param label   ["관광지", "쇼핑", "숙박", "음식", ... ]
     * @param query  검색어
     */
    @GetMapping("/search/{select}/{label}/{query}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ApiResponse getContentsList(@PathVariable String select,
                                       @PathVariable String label,
                                       @PathVariable String query) {
        List<Contents> contentsList = null;
        if (select.equals("지역")) {
            contentsList = contentsService.getContentsEqualLabelAndAddress(label, query);
        } else if (select.equals("상호")) {
            contentsList = contentsService.getContentsEqualLabelAndTitle(label, query);
        }
        log.info("Get {} list contents label {} include {} : {}", select, label, query, contentsList);
        
        return ApiResponse.success("data", contentsList);
    }
    
    
}

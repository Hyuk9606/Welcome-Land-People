package com.ssafy.boonmoja.api.controller.rank;

import com.ssafy.boonmoja.api.dto.RankDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static com.ssafy.boonmoja.utils.ApiUtils.ApiResult;
import static com.ssafy.boonmoja.utils.ApiUtils.success;

@Slf4j  // 로깅
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rank")
public class RankingController {
    
    @Operation(summary = "검색어 랭크", description = "검색어 랭킹 가져오기")
    @GetMapping
    public ApiResult<List<RankDto>> rank() {
        List<RankDto> list = new ArrayList<RankDto>();
        try{
            File file = new File("/application_log/result.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line ="";
            while((line = br.readLine())!=null){
                StringTokenizer st = new StringTokenizer(line);
                RankDto dto = new RankDto();
                dto.setRank_num(st.nextToken());
                dto.setRank_voca(st.nextToken());
                list.add(dto);
            }
        }
        catch(FileNotFoundException e){

        }
        catch(IOException e){

        }
        return  success(list);
    }

}

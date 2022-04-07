package com.ssafy.boonmoja.api.controller.rank;

import com.ssafy.boonmoja.api.dto.RankDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@RestController
public class RankingController {
    @GetMapping("/rank")
    public ResponseEntity<List> rank() {
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
        return  ResponseEntity.ok(list);
    }

}

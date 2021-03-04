package com.sondeos.jamrunner.Controller;

import com.sondeos.jamrunner.Dto.InfoRepo;
import com.sondeos.jamrunner.Dto.InfoResult;
import com.sondeos.jamrunner.Runner.JsRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Api {
    public static Logger logger = LoggerFactory.getLogger(Api.class);

    @PostMapping("/api/js")
    public ResponseEntity<InfoResult> jsProjectRunner(@RequestBody InfoRepo infoRepo) {
        try{
            logger.info("Request -> " + infoRepo);
            String response = new JsRunner(infoRepo.getRepo(), infoRepo.getName()).run();
            InfoResult result = new InfoResult(response);
            logger.info("Response -> " + result.toString());
            return ResponseEntity.ok(result);
        } catch (Exception e){
            logger.warn(e.getMessage() + " StackTrace: " + e.getStackTrace());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private String getTestInfo(String[] array) {
        int lengh = array.length;

        if (lengh > 0){
            StringBuffer response = new StringBuffer();
            for (String line : array)  {
                if(line.contains("pass") || line.contains("fail")){
                    response.append(line);
                    response.append(" ");
                }
            }
            return response.toString();
        }
        else
            return "";
    }
}

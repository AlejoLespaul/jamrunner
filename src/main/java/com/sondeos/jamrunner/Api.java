package com.sondeos.jamrunner;

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
    public ResponseEntity jsProjectRunner(@RequestBody InfoRepo infoRepo) {
        try{
            logger.info("Request -> " + infoRepo);
            String response = new JsRunner(infoRepo.getRepo(), infoRepo.getName()).run();
            logger.info("Response -> " + response);
            String[] array = response.trim().split("  ");
            return ResponseEntity.ok(getTestInfo(array));
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

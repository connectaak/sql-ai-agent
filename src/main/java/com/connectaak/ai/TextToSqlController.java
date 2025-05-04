package com.connectaak.ai;



import com.connectaak.ai.TextQueryRequest;
import com.connectaak.ai.TextToSqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/query")
@Slf4j
public class TextToSqlController {

    @Autowired
    private Environment env;

    @Autowired
    private TextToSqlService service;


    @GetMapping("/data")
    public List<Map<String, Object>> processQuery() {

        return service.processText("show all employees who joined after 2020");
    }

    @GetMapping("/data/ai")
    public List<Map<String, Object>> processQueryViaOpenAI() {

        return service.processTextViaOpenAI("List all employee names who joined before 2020");
    }

    @PostMapping
    public List<Map<String, Object>> processQuery(@RequestBody TextQueryRequest request) {
        return service.processText(request.getText());
    }
}
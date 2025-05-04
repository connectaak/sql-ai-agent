package com.connectaak.ai;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TextToSqlService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> processText(String text) {
        // Simple rules — in real app, use NLP or GPT model
        String sql;
        if (text.toLowerCase().contains("employees") && text.contains("2020")) {
            sql = "SELECT * FROM employees WHERE join_year > 2020";
        } else {
            sql = "SELECT * FROM employees";
        }

        return jdbcTemplate.queryForList(sql);
    }

    @Autowired
    private OpenAIClient openAIClient;

    public List<Map<String, Object>> processTextViaOpenAI(String text) {
        String sql = openAIClient.convertTextToSQL(text);
        return jdbcTemplate.queryForList(sql);
    }
}
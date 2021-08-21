package com.icia.wapoo.service;

import com.icia.wapoo.dao.AkinatorDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AkinatorService {
    @Autowired
    private final AkinatorDao akinatorDao;

    public List<Map<String, Object>> getAkinatorList() {
        List<Map<String, Object>> questionList = akinatorDao.selectAllQuestions();
        List akinatorList = new ArrayList();
        for(Map<String, Object> eachQuestion : questionList) {
            long questionId = (long) eachQuestion.get("questionId");
            List<Map<String, Object>> answerList = akinatorDao.selectAllAnswers(questionId);
            eachQuestion.put("answers", answerList);
            akinatorList.add(eachQuestion);
        }
        return akinatorList;
    }
}

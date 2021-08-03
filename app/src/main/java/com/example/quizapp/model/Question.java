package com.example.quizapp.model;
 /*
   model cho ngân hàng câu hỏi
   với constructor và Getter + Setter
 */
public class Question {
    private int answerResId;
    private Boolean answerTrue;

    public int getAnswerResId() {
        return answerResId;
    }

    public void setAnswerResId(int answerResId) {
        this.answerResId = answerResId;
    }

    public Boolean getAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(Boolean answerTrue) {
        this.answerTrue = answerTrue;
    }

    public  Question(){}

    public Question(int answerResId, Boolean answerTrue) {
        this.answerResId = answerResId;
        this.answerTrue = answerTrue;
    }
}

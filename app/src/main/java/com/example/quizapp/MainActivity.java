package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.quizapp.databinding.ActivityMainBinding;
import com.example.quizapp.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;

    private Question[] question = new Question[]{
            //ngân hàng câu hỏi với đáp án sd Array
            //create or instantiate objects
            new Question(R.string.question_amendments,false),//correct 27
            new Question(R.string.question_constitution,true),
            new Question(R.string.question_declaration,true),
            new Question(R.string.question_independence_rights,true),
            new Question(R.string.question_religion,true),
            new Question(R.string.question_government,false),
            new Question(R.string.question_government_feds,false),
            new Question(R.string.question_government_senators,true)
            //and add more
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // s/d DataBinding
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //thay đổi câu hỏi
        binding.questionTextView.setText(question[currentQuestionIndex]
                .getAnswerResId());

        //true button
        binding.trueButton.setOnClickListener(v -> { checkResult(true); });

        //false button
        binding.falseButton.setOnClickListener(v -> { checkResult(false); });

        //going forward button
        binding.nextButton.setOnClickListener(v -> {

            //check with log
            //Log.d("Main", "onCreate: "+ question[currentQuestionIndex++].getAnswerResId());

            /*
            khi currentQuestionIndex + 1 bằng với question.length
            Sẽ chia ra 0  Rồi sẽ quay lại index 0
            */

            currentQuestionIndex = (currentQuestionIndex + 1) % question.length; //++
            updateQuestion();

        });

        //going backward button
        binding.previousButton.setOnClickListener(v -> {
            if(currentQuestionIndex > 0) {
                currentQuestionIndex = (currentQuestionIndex - 1) % question.length; //--
                updateQuestion();
            }
        });


    }
    //thay đổi câu hỏi
    private void updateQuestion() {
        binding.questionTextView.setText(question[currentQuestionIndex]
                .getAnswerResId());
    }

    //Kiểm tra kết quả
    private void checkResult(boolean answer){

        //so sánh kết quả với ngân hàng câu hỏi
        boolean correctAnswer = question[currentQuestionIndex].getAnswerTrue();

        int messageId;

        if(correctAnswer == answer){
            messageId = R.string.correct_answer;

            //nếu dúng sẽ hiển thị câu hỏi kế tiếp
            currentQuestionIndex = (currentQuestionIndex +1) % question.length;//++
            updateQuestion();

        }else {
            messageId = R.string.wrong_answer;
        }

        //hiển thị chọn đúng hay không với messageId
        Snackbar.make(binding.imageView,messageId,Snackbar.LENGTH_SHORT).show();
    }
}
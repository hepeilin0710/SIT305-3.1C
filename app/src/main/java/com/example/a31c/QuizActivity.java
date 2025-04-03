package com.example.a31c;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    TextView questionText, userName;
    Button[] optionButtons = new Button[4];
    ProgressBar progressBar;
    Button submitButton;

    int currentQuestion = 0;
    int score = 0;
    String selectedAnswer = "";
    List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        userName = findViewById(R.id.userNameText);
        progressBar = findViewById(R.id.progressBar);
        submitButton = findViewById(R.id.submitButton);

        optionButtons[0] = findViewById(R.id.option1);
        optionButtons[1] = findViewById(R.id.option2);
        optionButtons[2] = findViewById(R.id.option3);
        optionButtons[3] = findViewById(R.id.option4);

        String name = getIntent().getStringExtra("userName");
        userName.setText("Hello，" + name);

        questions = Question.getSampleQuestions();
        showQuestion();

        for (Button btn : optionButtons) {
            btn.setOnClickListener(v -> {
                selectedAnswer = btn.getText().toString();
                resetButtonColors();
                btn.setBackgroundColor(Color.YELLOW);
                Toast.makeText(this, "You chose：" + selectedAnswer, Toast.LENGTH_SHORT).show();
            });
        }

        submitButton.setOnClickListener(v -> {
            if (selectedAnswer.isEmpty()) {
                Toast.makeText(this, "Please select an answer！", Toast.LENGTH_SHORT).show();
                return;
            }

            String correctAnswer = questions.get(currentQuestion).getCorrectAnswer();
            for (Button btn : optionButtons) {
                String text = btn.getText().toString();
                if (text.equals(correctAnswer)) {
                    btn.setBackgroundColor(Color.GREEN);
                } else if (text.equals(selectedAnswer)) {
                    btn.setBackgroundColor(Color.RED);
                }
            }

            if (selectedAnswer.equals(correctAnswer)) {
                Toast.makeText(this, "Congratulations, that's right！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wrong answer. The correct answer is：" + correctAnswer, Toast.LENGTH_SHORT).show();
            }

            new Handler().postDelayed(() -> {
                currentQuestion++;
                if (currentQuestion < questions.size()) {
                    selectedAnswer = "";
                    showQuestion();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("userName", name);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        });
    }

    void resetButtonColors() {
        for (Button btn : optionButtons) {
            btn.setBackgroundColor(Color.LTGRAY);
        }
    }

    void showQuestion() {
        Question q = questions.get(currentQuestion);
        questionText.setText(q.getQuestion());

        String[] opts = q.getOptions();
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText(opts[i]);
        }

        int progress = (int) (((currentQuestion + 1) / (float) questions.size()) * 100);
        progressBar.setProgress(progress);
    }
}

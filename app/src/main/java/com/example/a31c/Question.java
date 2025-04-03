package com.example.a31c;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private String[] options;
    private String correctAnswer;

    public Question(String question, String[] options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public static List<Question> getSampleQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question("2 + 2 = ?", new String[]{"3", "4", "5", "6"}, "4"));
        list.add(new Question("What is the Earth's satellite?", new String[]{"Mars", "moon", "venus", "Sun"}, "moon"));
        list.add(new Question("Which one is the programming language？", new String[]{"HTML", "CSS", "Java", "Photoshop"}, "Java"));
        list.add(new Question("The capital of China is？", new String[]{"shanghai", "beijing", "guangzhou", "chengdu"}, "beijing"));
        return list;
    }
}


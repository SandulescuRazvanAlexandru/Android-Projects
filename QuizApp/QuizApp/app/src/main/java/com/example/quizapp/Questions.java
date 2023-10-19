package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Questions extends AppCompatActivity implements View.OnClickListener {
    private TextView tvQuestion;
    private TextView tvCurrentQuestionNumber;
    private TextView tvCountdown;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;

    private List<Quest> questionList;

    private int currentQuestionNumber;

    private CountDownTimer cdt;

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        //initializam variabilele
        init();

        //am scris sus implements View.OnClickListener pentru a face o functie generala, sa nu scriu de fiecare data
        //incerc sa lucrez cat mai modularizat posibil
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        //setam intrebarile
        getQuestionList();
    }

    private void init() {
        tvQuestion = findViewById(R.id.sandulescu_razvan_alexandru_tvQuestion);
        tvCurrentQuestionNumber = findViewById(R.id.sandulescu_razvan_alexandru_tvCurrentQuestionNumber);
        tvCountdown = findViewById(R.id.sandulescu_razvan_alexandru_tvCountdown);
        option1 = findViewById(R.id.sandulescu_razvan_alexandru_btnOption1);
        option2 = findViewById(R.id.sandulescu_razvan_alexandru_btnOption2);
        option3 = findViewById(R.id.sandulescu_razvan_alexandru_btnOption3);
        option4 = findViewById(R.id.sandulescu_razvan_alexandru_btnOption4);
        score = 0;
    }

    private void getQuestionList() {
        questionList = new ArrayList<>();

        //populam lista de intrebari
        questionList.add(new Quest("An IP address is a ………. Number.", "32-bit", "16-bit", "8-bit", "64-bit", 1));
        questionList.add(new Quest("A pictorial representation of a program or the algorithm is known as …", "Diagram", "Flowchart", "Data flow", "Data representation", 2));
        questionList.add(new Quest("USB is a …… storage device.", "Primary", "Secondary", "Auxiliary", "Additional", 2));
        questionList.add(new Quest("Who among the following has designed the PHP programing language?", "Rasmus Lerdorf", "Guido van Rossum", "Brendan Eich", "James Gosling", 1));
        questionList.add(new Quest("If you need to cut the contents of MS Word, which command will you give?", "CTRL + X", "CTRL + C", "CTRL + V", "CTRL + Z", 1));
        questionList.add(new Quest("The malicious software program, which is detrimental to other computer programs is known as …", "Virus", "Worms", "Trojans", "Spyware", 1));
        questionList.add(new Quest("Part of computer, which performs subtraction, addition, multiplication, division, and comparison is known as …", "RAM", "ROM", "ALU", "Processor", 3));
        questionList.add(new Quest("Who among the following had first invented the first electronic digital computer?", "Greg Chesson", "John Vincent Atanasoff", "Charles Babbage", "Jack Dorsey", 2));

        //apelam aceasta functie care va porni cronometrul si va afisa pe ecran intrebarea cu raspunsurile aferente si va afisa si a cata intrebare este
        setQuestion();
    }

    private void setQuestion() {
        //da, initial cronometrul are o valoare fixa, o sa vedeti imediat de ce in functia startTimer();
        tvCountdown.setText(String.valueOf(10));

        tvQuestion.setText(questionList.get(0).getQ());
        option1.setText(questionList.get(0).getA());
        option2.setText(questionList.get(0).getB());
        option3.setText(questionList.get(0).getC());
        option4.setText(questionList.get(0).getD());

        tvCurrentQuestionNumber.setText(String.valueOf(1) + "/" + String.valueOf(questionList.size()));

        startTimer();

        currentQuestionNumber = 0;
    }

    private void startTimer() {
        //a se vedea si functia pe care o folosesc ca sa adaug o intarziere de 2 secunde, timp in care se randeaza animatia pentru intrebare + raspunsuri
        //care este in functis checkIfRightAnswer
        cdt = new CountDownTimer(12000, 1000) { //10 secunde, si le actualizam la 1 secunda
            //adica la fiecare 1000 de milisecunde, intra in functie si decrementeaza din cele 10.000 de milisecunde
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 10000) {
                    tvCountdown.setText(String.valueOf(millisUntilFinished / 1000)); //cate secunde mai sunt pana se termina countdown-ul
                    //care initial era 10.000
                    //dar, la fiecare 1000 de milisecunde, se decrementeaza
                    //iar noua valoare, gen 9.000, 8.000 etc se memoreaza in millisUntilFinished
                    //si eu impart millisUntilFinished la 1000 pentru a afisa secundele ramase
                }
            }
            //daca nu a fost selectat niciun raspuns, se va trece la intrebarea urmatoare
            @Override
            public void onFinish() {
                nextQuestion();
            }
        };

        //dam startul
        cdt.start();
    }

    @Override
    public void onClick(View v) { //v = this

        int selectedAnswer = 0; //aici stocam nr raspunsului selectat de utilizator

        //verificam ce raspuns a fost apasat
        if (v.getId() == R.id.sandulescu_razvan_alexandru_btnOption1)
            selectedAnswer = 1;
        else if (v.getId() == R.id.sandulescu_razvan_alexandru_btnOption2)
            selectedAnswer = 2;
        else if (v.getId() == R.id.sandulescu_razvan_alexandru_btnOption3)
            selectedAnswer = 3;
        else if (v.getId() == R.id.sandulescu_razvan_alexandru_btnOption4)
            selectedAnswer = 4;

        //oprim cronometrul
        cdt.cancel();

        //verificam daca raspunsul este corect
        checkIfRightAnswer(v, selectedAnswer);
    }

    private void checkIfRightAnswer(View v, int selectedAnswer) {
        if (selectedAnswer == questionList.get(currentQuestionNumber).getRightAnswer()) { //daca raspunsul este corect
            ((Button)v).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN)); //nu background, pentru ca background este round_corner; pe noi ne intereseaza doar culoarea
            score++;
        } else { //cazul cand raspunsul este gresit
            ((Button)v).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            //pe cel corect trebuie sa il facem verde
            if (questionList.get(currentQuestionNumber).getRightAnswer() == 1) {
                option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            } else if (questionList.get(currentQuestionNumber).getRightAnswer() == 2) {
                option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            } else if (questionList.get(currentQuestionNumber).getRightAnswer() == 3) {
                option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            } else if (questionList.get(currentQuestionNumber).getRightAnswer() == 4) {
                option4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            }
        }

        //punem o mica intarziere pe pornirea cronometrului, astfel inca sa nu se piarda nicio secunda cu animatia si schimbarea efectiva a intrebarii si a raspunsurilor
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextQuestion();
            }
        }, 2000); //intarziere de 2 secunde; dupa 2 secunde, va executa ce se afla in functia "run"
    }

    private void nextQuestion() {
        //verificam daca este ultima intrebare
        if (currentQuestionNumber < questionList.size() - 1) {
            currentQuestionNumber++;
            //ca sa fim extra, nu schimbam intrebarile pur si simplu, ci cu animatie
            //animatia va fi in felul urmatorul
            //intrebarea + raspunsurile se vor micsora de tot, pana la 0, apoi se vor extinde la loc cu noile raspunsuri si noua intrebare din lista
            nextAnimation(tvQuestion, 0, 0); //0 - inseamna ca va merge pana cand size = 0 (ma refer la value, al doilea camp)
            nextAnimation(option1, 0, 1);
            nextAnimation(option2, 0, 2);
            nextAnimation(option3, 0, 3);
            nextAnimation(option4, 0, 4);

            //incrementam si numarul intrebarii
            tvCurrentQuestionNumber.setText(String.valueOf(currentQuestionNumber + 1) + "/" + String.valueOf(questionList.size()));

            //resetam cronometrul (pentru ca incepe urmatoarea intrebare)
            tvCountdown.setText(String.valueOf(10));
            startTimer();
        }
        else { //daca este ultima intrebare afisam scorul
            Intent intent = new Intent(Questions.this, ScoreActivity.class);
            intent.putExtra("SCORE", String.valueOf(score) + "/" + String.valueOf(questionList.size()));
            startActivity(intent);
            Questions.this.finish(); //NU VREM CA UTILIZATORUL SA SE POATA INTOARCE, DE ASTA TREBUIE SA TERMIN DEFINITV ACEASTA ACTIVITATE !!!!!!!!!!!
        }
    }

    private void nextAnimation(final View v, final int value, final int n) {
            v.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    //cand se va termina animatia, vreau sa refac marimea initiala
                    //acum este 0
                    //si fix cand este 0, SCHIMB TEXTUL !!!!!!!!
                    if (value == 0) { //doar cand totul este micsorat
                        if (n == 0) { //intrebare
                            ((TextView)v).setText(questionList.get(currentQuestionNumber).getQ()); //variabila v poate sa fie orice, gen buton, textview etc si de asta trebuie sa ii facem cast
                        } else if (n == 1) { //raspunsuri
                            ((Button)v).setText(questionList.get(currentQuestionNumber).getA());
                        } if (n == 2) {
                            ((Button)v).setText(questionList.get(currentQuestionNumber).getB());
                        } if (n == 3) {
                            ((Button)v).setText(questionList.get(currentQuestionNumber).getC());
                        } if (n == 4) {
                            ((Button)v).setText(questionList.get(currentQuestionNumber).getD());
                        }
                        if (n != 0) { //restabilim si culorile, sa nu ramana verde/rosu
                            ((Button)v).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#004866")));
                        }
                        //deci, in acest moment totul este micsorat la size = 0
                        //practic, pe ecran nu este nimic
                        //acum reapelam functia pentru a mari la size-ul initial, DAR cu intrebarea + raspunsurile deja modificate
                        nextAnimation(v, 1, n); //value = 1 asta inseamna, la marimea initiala
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
    }
}


















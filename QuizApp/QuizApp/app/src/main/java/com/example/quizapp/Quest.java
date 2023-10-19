package com.example.quizapp;


//am numit Quest pentru ca deja activitatea este numita Questions
//si imi e super frica sa fac cu refractor sa schimb :)))
//nu vreau sa strict ceva, asa ca las asa acum, pe viitor o sa am mai mare grija la denumiri
public class Quest {

    String q; //q - de la question
    String a; //a - de la prima optiune
    String b, c, d;
    int rightAnswer; //numarul raspunsului corect

    public Quest(String q, String a, String b, String c, String d, int rightAnswer) {
        this.q = q;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.rightAnswer = rightAnswer;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}

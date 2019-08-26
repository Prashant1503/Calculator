package com.example.pintu.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends AppCompatActivity {

    private TextView calcScreen;
    private Button n0, n1, n2, n3, n4, n5, n6, n7, n8, n9,
            div, add, sub, mul, dot, equal;

    private Button del, clear;

    private boolean isOpPressed =false;
    private int secondNumberIndex =0;
    private double firstNumber=0;
    private char currentOp;
    private boolean isDot =false;

    private String screenContent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylayout);

        calcScreen = findViewById(R.id.calculator_screen);
        n0 = findViewById(R.id.btnZero);
        n1 = findViewById(R.id.btnOne);
        n2 = findViewById(R.id.btnTwo);
        n3 = findViewById(R.id.btnThree);
        n4 = findViewById(R.id.btnFour);
        n5 = findViewById(R.id.btnFive);
        n6 = findViewById(R.id.btnSix);
        n7 = findViewById(R.id.btnSeven);
        n8 = findViewById(R.id.btnEight);
        n9 = findViewById(R.id.btnNine);

        div = findViewById(R.id.btnDivide);
        add = findViewById(R.id.btnAddition);
        sub = findViewById(R.id.btnSubstraction);
        mul = findViewById(R.id.btnMultiplication);
        dot = findViewById(R.id.btnDot);
        equal = findViewById(R.id.btnEquals);
        del = findViewById(R.id.btnDelete);
        clear = findViewById(R.id.btnClear);


        final View.OnClickListener calculatorListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int id = v.getId();

                switch (id) {

//                    Numbers..
                    case R.id.btnZero:

                        calcScreen.append("0");

                        break;

                    case R.id.btnOne:
                        calcScreen.append("1");

                        break;

                    case R.id.btnTwo:
                        calcScreen.append("2");

                        break;

                    case R.id.btnThree:
                        calcScreen.append("3");

                        break;

                    case R.id.btnFour:
                        calcScreen.append("4");

                        break;

                    case R.id.btnFive:
                        calcScreen.append("5");

                        break;

                    case R.id.btnSix:
                        calcScreen.append("6");

                        break;

                    case R.id.btnSeven:
                        calcScreen.append("7");

                        break;

                    case R.id.btnEight:
                        calcScreen.append("8");

                        break;

                    case R.id.btnNine:
                        calcScreen.append("9");

                        break;

//                        Operations buttons...

                    case R.id.btnAddition:

                        opPressed('+');

                        break;

                    case R.id.btnSubstraction:

                        opPressed('-');

                        break;

                    case R.id.btnDivide:
                       opPressed('/');
                        break;


                    case R.id.btnMultiplication:
                         opPressed('*');

                        break;


                    case R.id.btnDot:

                        if(!isDot){
                            screenContent = calcScreen.getText().toString();
                            final int screenContentLength = screenContent.length();

                            if (screenContentLength <1){
                                return;
                            }
                            char lastchar = screenContent.charAt(screenContent.length()-1);

                            if (lastchar == '+' || lastchar == '-' || lastchar == '*' || lastchar == '/') {

                                return;
                            }

                            calcScreen.append(".");
                            isDot=true;

                        }

                        break;

                    case R.id.btnEquals:
                        if (isOpPressed){

                            screenContent = calcScreen.getText().toString();
                            char lastchar = screenContent.charAt(screenContent.length()-1);

                            if (lastchar == '+' || lastchar == '-' || lastchar == '*' || lastchar == '/') {

                                return;
                            }
                            String secondNumberString = screenContent.substring(secondNumberIndex,screenContent.length());
                            double secondNumber = Double.parseDouble(secondNumberString);

                            if (currentOp == '+'){
                                secondNumber+=firstNumber;
                            }
                            else if (currentOp == '-'){

                                secondNumber = firstNumber-secondNumber;
                            }
                            else if (currentOp == '*'){
                                secondNumber*=firstNumber;
                            }
                            else if (currentOp == '/'){
                                if (secondNumber ==0){
                                    return;
                                }
                                secondNumber = firstNumber/secondNumber;

                            }
                            String result = String.valueOf(secondNumber);
                            if (result.endsWith(".0")){
                                result = result.substring(0,result.length()-2);

                            }
                            calcScreen.setText(result);
                            isOpPressed=false;
                        }



                        break;
                }

            }
        };
        n0.setOnClickListener(calculatorListner);
        n1.setOnClickListener(calculatorListner);
        n2.setOnClickListener(calculatorListner);
        n3.setOnClickListener(calculatorListner);
        n4.setOnClickListener(calculatorListner);
        n5.setOnClickListener(calculatorListner);
        n6.setOnClickListener(calculatorListner);
        n7.setOnClickListener(calculatorListner);
        n8.setOnClickListener(calculatorListner);
        n9.setOnClickListener(calculatorListner);

        add.setOnClickListener(calculatorListner);
        sub.setOnClickListener(calculatorListner);
        mul.setOnClickListener(calculatorListner);
        div.setOnClickListener(calculatorListner);
        dot.setOnClickListener(calculatorListner);
        equal.setOnClickListener(calculatorListner);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String displayedElements = calcScreen.getText().toString();
                int length = displayedElements.length();

                if (length >0){
                    displayedElements= displayedElements.substring(0,length-1);
                    calcScreen.setText(displayedElements);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calcScreen.setText("");
            }
        });



    }
    private void opPressed(char operation){
        if (isOpPressed){
            return;
        }

        screenContent = calcScreen.getText().toString();
        final int screenContentLength = screenContent.length();

        if (screenContentLength <1){
            return;
        }
        secondNumberIndex = screenContent.length()+1;
        firstNumber = Double.parseDouble(screenContent);

        calcScreen.append(String.valueOf(operation));
        isOpPressed =true;
        isDot=false;
        currentOp = operation;



    }

}
package com.my.sumit.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{

    Button button;
    Button bplus,bminus,
           bequals, bclear;


    TextView calculator_result;

    boolean isOperatorPressed = false;

    Button lastOperator = null;

    Integer first_value = 0;
    Integer second_value = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting all the operator and extra buttons here.
        bplus       = (Button) findViewById(R.id.button_plus);
        bminus      = (Button) findViewById(R.id.button_minus);
        bequals     = (Button) findViewById(R.id.button_equals);
        bclear      = (Button) findViewById(R.id.button_clear);

        calculator_result = (TextView) findViewById(R.id.calculator_result);

        //Setting 0 in the beginning
        calculator_result.setText("0");

    }

    //This function works when any number is pressed.
    public void buttonPressed(View pressed_button){
        button = (Button)findViewById(pressed_button.getId());
        String result = calculator_result.getText().toString(); //Gets the button's text which is a number
        if(isOperatorPressed){
            calculator_result.setText("");
            isOperatorPressed = false;
        }
        if(result.equals("0")){ setOnZero(button); }
        else { appendToResult(button); }

    }

    //This function works when any operator or extra button is pressed.
    public void operatorPressed(View pressed_button){
        switch (pressed_button.getId())
        {   //For addition
            case R.id.button_plus:{


                if(!isOperatorPressed){
                    second_value = Integer.parseInt(calculator_result.getText().toString());
                    calculate();
                }

                lastOperator = bplus;
                isOperatorPressed = true;

                break;
            }
            //For subtraction
            case R.id.button_minus:{

                if(!isOperatorPressed){
                    second_value = Integer.parseInt(calculator_result.getText().toString());
                    calculate();
                }
                lastOperator = bminus;
                isOperatorPressed = true;

                break;
            }
            //For equal button
            case R.id.button_equals:{
                if(isOperatorPressed){
                    return;
                }
                second_value = Integer.parseInt(calculator_result.getText().toString());

                calculate();

                calculator_result.setText(first_value.toString());
                //first_value = 0;
                second_value = 0;
                isOperatorPressed = true;
                break;
            }
            //For clearing the values
            case R.id.button_clear:{
                calculator_result.setText("0");
                first_value = 0;
                second_value = 0;
                lastOperator = null;
                break;
            }

        }
    }


    //This method is called when an actual calculation occurs
    public void calculate(){
        if(lastOperator == bplus || lastOperator == null){
            first_value += second_value;
        }
        else{
            first_value -= second_value;
        }

        calculator_result.setText(first_value.toString());
        //calculate();

    }

    public void setOnZero(Button appendValue){
        calculator_result.setText(appendValue.getText().toString());
    }

    public void appendToResult(Button appendValue){
        calculator_result.append(appendValue.getText().toString());
    }
    /*
    public void calculate(){
        try {
            String oneValue = "";
            String result = calculator_result.getText().toString();
            for (int i = 0; i < result.length(); i++) {
                char currentChar = result.charAt(i);
                if (Character.isDigit(currentChar) || currentChar == '.') {
                    oneValue += currentChar;
                } else {
                    operandStack.push(Integer.parseInt(oneValue));
                    if (operatorStack.isEmpty() || currentChar == 'x' || currentChar == '/') {
                        operatorStack.push(currentChar);
                    } else {
                        partCalculate();
                        operatorStack.push(currentChar);
                    }

                    oneValue = "";
                }
            }
            if (!oneValue.equals("")) {
                operandStack.push(Integer.parseInt(oneValue));
                oneValue = "";
            }
            while (!operatorStack.isEmpty()) {
                partCalculate();
            }


            calculator_result.append("\n" + operandStack.peek().toString());
        }
        catch(Exception e){
            System.out.println("E=" + e);
            calculator_result.setText("Error!");
            error = true;
        }
    }

    public void partCalculate(){
        Integer result = 0;
        try {
            second_value = operandStack.pop();
            first_value = operandStack.pop();
            switch (operatorStack.pop()) {
                case '+': {
                    result = first_value + second_value;
                    break;
                }

                case '-': {
                    result = first_value - second_value;
                    break;
                }

                case 'x': {
                    result = first_value * second_value;
                    break;
                }

                case '/': {

                    result = first_value / second_value;
                    break;
                }
            }
            operandStack.push(result);
        }
        catch (ArithmeticException ae){
            System.out.println("AE = " + ae);
            calculator_result.setText("Error!");
            error = true;
        }

    }

    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

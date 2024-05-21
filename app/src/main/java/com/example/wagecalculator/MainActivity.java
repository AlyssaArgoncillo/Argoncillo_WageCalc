package com.example.wagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

        EditText noOfHours,hourlyPay;
        TextView payment, overtime, taxPaym, totalPaym;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            //Inputs
            noOfHours = findViewById(R.id.input_1);
            hourlyPay = findViewById(R.id.input_2);


            // Outputs
            payment = findViewById(R.id.txt_payment);
            overtime = findViewById(R.id.txt_overtimePayment);
            taxPaym = findViewById(R.id.txt_tax);
            totalPaym = findViewById(R.id.txt_totalPayment);
        }


        public void calculation(View view) {
            try {
                double workedHours= Double.parseDouble(noOfHours.getText().toString());
                double hourlyRate = Double.parseDouble(hourlyPay.getText().toString());


                double paymentP = 0;
                double overtimeP = 0;
                double totalP = 0;
                double tax = 0;


                if (workedHours <= 40) {
                    paymentP = workedHours * hourlyRate;
                    overtimeP = 0;
                    totalP = paymentP;
                } else {
                    paymentP = 40 * hourlyRate;
                    overtimeP = (workedHours - 40) * hourlyRate;
                    totalP = paymentP + overtimeP;
                }


                tax = totalP * 0.18;


                payment.setText(String.format("%.2f", paymentP));
                overtime.setText(String.format("%.2f", overtimeP));
                totalPaym.setText(String.format("%.2f", totalP));
                taxPaym.setText(String.format("%.2f", tax));


            } catch (Exception e) {


                TextView err =  findViewById(R.id.txt_error);
                err.setText("Opps! Enter numbers to calculate!");
                payment.setText("");
                overtime.setText("");
                totalPaym.setText("");
                taxPaym.setText("");


            }finally {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        TextView err =  findViewById(R.id.txt_error);
                        err.setText("");
                    }
                }, 2000);
            }
    }
}
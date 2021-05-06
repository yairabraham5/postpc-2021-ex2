package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }
    TextView calcOutput = findViewById(R.id.textViewCalculatorOutput);

    calcOutput.setText(calculator.output());
    /*
    TODO:
    - find all views
    - initial update main text-view based on calculator's output
    - set click listeners on all buttons to operate on the calculator and refresh main text-view
     */
    TextView buttonZero = findViewById(R.id.button0);
    TextView buttonOne = findViewById(R.id.button1);
    TextView buttonTwo = findViewById(R.id.button2);
    TextView buttonThree = findViewById(R.id.button3);
    TextView buttonFour = findViewById(R.id.button4);
    TextView buttonFive = findViewById(R.id.button5);
    TextView buttonSix = findViewById(R.id.button6);
    TextView buttonSeven = findViewById(R.id.button7);
    TextView buttonEight = findViewById(R.id.button8);
    TextView buttonNine = findViewById(R.id.button9);
    View backSpace = findViewById(R.id.buttonBackSpace);
    View clear = findViewById(R.id.buttonClear);
    View equals = findViewById(R.id.buttonEquals);
    View minus = findViewById(R.id.buttonMinus);
    View plus = findViewById(R.id.buttonPlus);
    buttonZero.setOnClickListener(v -> {

      calculator.insertDigit(0);
      calcOutput.setText(calculator.output());

    });

    buttonOne.setOnClickListener(v -> {
      calculator.insertDigit(1);
      calcOutput.setText(calculator.output());

    });
    buttonTwo.setOnClickListener(v -> {
      calculator.insertDigit(2);
      calcOutput.setText(calculator.output());

    });

    buttonThree.setOnClickListener(v -> {
      calculator.insertDigit(3);
      calcOutput.setText(calculator.output());

    });

    buttonFour.setOnClickListener(v -> {
      calculator.insertDigit(4);
      calcOutput.setText(calculator.output());

    });

    buttonFive.setOnClickListener(v -> {
      calculator.insertDigit(5);
      calcOutput.setText(calculator.output());

    });

    buttonSix.setOnClickListener(v -> {
      calculator.insertDigit(6);
      calcOutput.setText(calculator.output());

    });

    buttonSeven.setOnClickListener(v -> {
      calculator.insertDigit(7);
      calcOutput.setText(calculator.output());

    });

    buttonEight.setOnClickListener(v -> {
      calculator.insertDigit(8);
      calcOutput.setText(calculator.output());

    });

    buttonNine.setOnClickListener(v -> {
      calculator.insertDigit(9);
      calcOutput.setText(calculator.output());

    });

    plus.setOnClickListener(v -> {

      calculator.insertPlus();
      calcOutput.setText(calculator.output());
    });

    minus.setOnClickListener(v -> {

      calculator.insertMinus();
      calcOutput.setText(calculator.output());
    });

    equals.setOnClickListener(v -> {

      calculator.insertEquals();
      calcOutput.setText(calculator.output());
    });

    clear.setOnClickListener(v -> {

      calculator.clear();
      calcOutput.setText(calculator.output());
    });

    backSpace.setOnClickListener(v -> {

      calculator.deleteLast();
      calcOutput.setText(calculator.output());
    });
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("calculator", calculator.saveState());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    calculator.loadState(savedInstanceState.getSerializable("calculator"));
    TextView calcOutput = findViewById(R.id.textViewCalculatorOutput);
    calcOutput.setText(calculator.output());
  }
}
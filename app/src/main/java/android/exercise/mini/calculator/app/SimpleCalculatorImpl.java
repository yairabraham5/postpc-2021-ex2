package android.exercise.mini.calculator.app;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;

public class SimpleCalculatorImpl implements SimpleCalculator {


  private  ArrayList<String> allNumbers = new ArrayList<>();


  @Override
  public String output() {
    if(allNumbers.size() == 0){
      allNumbers.add("0");
    }
    StringBuilder result = new StringBuilder();
    for(String str: allNumbers){
      result.append(str);
    }
    return result.toString();
  }

  @Override
  public void insertDigit(int digit) {

    if (digit > 9 || digit < 0){
      throw new RuntimeException();
    }
    if(allNumbers.size() == 0){
      allNumbers.add(String.valueOf(digit));
      return;
    }
    if (allNumbers.size() > 0 && allNumbers.get(0).equals("0")){
      allNumbers.set(0, String.valueOf(digit));
      return;
    }

    int lastIndex = allNumbers.size() - 1;
    String currentValue = allNumbers.get(lastIndex);
    if (allNumbers.get(lastIndex).equals("+") || allNumbers.get(lastIndex).equals("-")){
      allNumbers.add(String.valueOf(digit));

    }
    else{
      currentValue += String.valueOf(digit);
      allNumbers.set(lastIndex, currentValue);
    }
  }

  @Override
  public void insertPlus() {
    if (allNumbers.size() == 0){
        allNumbers.add("0");
    }
    allNumbers.add("+");
  }

  @Override
  public void insertMinus() {
    if (allNumbers.size() == 0){
        allNumbers.add("0");
    }
    allNumbers.add("-");
  }

  @Override
  public void insertEquals() {

    int number;
    boolean isPlus = false;
    if (allNumbers.size() == 0){
      return;
    }
    int result = Integer.parseInt(allNumbers.get(0));
    for(int i = 1; i <allNumbers.size(); i++){
      if(i % 2 == 1){
        if(allNumbers.get(i).equals("+")) {
          isPlus = true;
        }
      }
      else {
        number = Integer.parseInt(allNumbers.get(i));
        if (isPlus) {
          result += number;
        } else {
          result -= number;
        }
        isPlus = false;
      }
    }
    allNumbers.clear();
    allNumbers.add(String.valueOf(result));
  }

  @Override
  public void deleteLast() {
    if(allNumbers.size() > 0 && !allNumbers.get(0).equals("0")){
      int lastIndex = allNumbers.size() - 1;
      String currentValue = allNumbers.get(lastIndex);
      if(currentValue.equals("+") || currentValue.equals("-")){
        allNumbers.remove(lastIndex);
      }
      else {
        String newNum = currentValue.substring(0, currentValue.length()- 1);
        if (newNum.equals("")){
          allNumbers.remove(lastIndex);
        }
        else{
          allNumbers.set(lastIndex, newNum);
        }
      }
    }
  }

  @Override
  public void clear() {
    allNumbers.clear();

  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();

    state.allParts.addAll(allNumbers);
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    allNumbers.clear();
    allNumbers.addAll(casted.allParts);

  }

  private static class CalculatorState implements Serializable {
    /*

    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
    private ArrayList<String> allParts = new ArrayList<>();

  }
}

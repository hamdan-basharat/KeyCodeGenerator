package keycodegeneratorhamdan;

import java.util.Scanner;
public class KeyCodeGeneratorHamdan {

 //-----------Object Declaration-----------------
 private static Scanner in = new Scanner(System.in);
 private static Scanner key = new Scanner(System.in);
 public static void main(String[] args) {
  //-------Variable Declaration for Menu---------  
  int menuChoice = 9;
  String validateKeyCode="";
  KeyCodeGenerator keyGenerator = new KeyCodeGenerator();
   //----------------Start of Menu---------------
  while (menuChoice != 0) {
   displayMainMenu();
   menuChoice = getMenuChoice();
   //------Start of Switch/Case statements-------
   switch (menuChoice) {
   case 1://If 1 is entered generateKeyCode() is run
   {
    //Displays the generated code
    System.out.println("KEYCODE GENERATED: ");
    keyGenerator.generateKeyCode();
    keyGenerator.setKeyCode("");
   }
   break;
    
   case 2://If 2 is entered validateKeyCode() is run
   {
    System.out.println("Please enter a KeyCode to be test:");
    //Displays the entered keycode
    keyGenerator.setKeyCode(key.nextLine());
    System.out.println(keyGenerator.getKeyCode());
    //If validateKeyCode is true (valid keycode) it goes to 'if' and if ont it goes to 'else'
    if (keyGenerator.validateKeyCode())
     System.out.println("The KeyCode entered is VALID");
    else
     System.out.println("The KeyCode entered is INVALID");
   }
   break;
    
   case 0://If 0 is entered, program exits
    System.out.println("Goodbye");
   break;
   }
  }
 }
 private static void displayMainMenu() {
  //Displays these statements before the Switch/Case
  System.out.println("MENU-----Select One ------");
  System.out.println("1. Generate a Key Code:");
  System.out.println("2. Verify a Key Code:");
  System.out.println("0. Exit Program");
  System.out.println("--------------------------");
 }
 private static int getMenuChoice() {
  //Recieves the value entered for the menu and returns it as 'menuChoice'
  while (!in.hasNextInt()) {
   in.nextLine();
   displayMainMenu();
  }
  int menuChoice = in.nextInt();
  return menuChoice;
 }//---------------end of main-------------------
}//-----end of KeyCodeGeneratorHamdanclass--------

class KeyCodeGenerator {
 private String keyCode = "";
 public String getKeyCode() {
 /* *******************************************
  * Returns the generated keycode to the menu
  * under the name 'keyCode'
  * **************************************** */
  return keyCode;
}//------------end of getKeyCode()--------------
 public void setKeyCode(String keyCode) {
 /* *******************************************
  * Assigns the value of the parameter keyCode 
  * to the field of the same name
  * **************************************** */
  this.keyCode = keyCode;
}//------------end of setKeyCode()--------------
 public void generateKeyCode() {
 /* *******************************************
  * Generates a random keycode
  * **************************************** */
  int firstNumber;
  int secondNumber;
  String generated = "";
  int numberOfPairs = 0;
  while (numberOfPairs < 9) {
   //Keeps setting firstNumber and secondNumber to random integers <10 until they add up to 9
   firstNumber = (int) (Math.random() * 10);
   secondNumber = (int) (Math.random() * 10);
   if ((firstNumber + secondNumber) == 9) {
    numberOfPairs++;
    if (numberOfPairs % 3 == 0) {
     if (numberOfPairs == 9)
      generated += Integer.toString(firstNumber)
        + Integer.toString(secondNumber);
     else
      generated += Integer.toString(firstNumber)
        + Integer.toString(secondNumber) + "-";
    } else
     generated += Integer.toString(firstNumber)
       + Integer.toString(secondNumber);
   }
  }
  System.out.print(generated +"\n");
 }//---------end of generateKeyCode()------------
 public boolean validateKeyCode() {
 /* *******************************************
  * Checks if entered keycode meets the rules 
  * to be considered valid
  * **************************************** */
  String[] keyPieces = keyCode.split("-");
  if (keyPieces.length != 3) //Checks if there are 3 segments to the keycode
   return false;
  for (String s : keyPieces)
   if (s.length() != 6)//Checks if the segments are 6 characters long
    return false;

  try {
   int keyPiece1 = Integer.parseInt(keyPieces[0]); //turns segment 1 into integers
   int keyPiece2 = Integer.parseInt(keyPieces[1]); //turns segment 2 into integers
   int keyPiece3 = Integer.parseInt(keyPieces[2]); //turns segment 3 into integers

   int[] piece1 = { keyPiece1 / 10000, (keyPiece1 / 100) % 100,
     keyPiece1 % 100 };
   int[] piece2 = { keyPiece2 / 10000, (keyPiece2 / 100) % 100,
     keyPiece2 % 100 };
   int[] piece3 = { keyPiece3 / 10000, (keyPiece3 / 100) % 100,
     keyPiece3 % 100 };
   
   int[] piece1parts = { piece1[0] / 10, piece1[0] % 10,
     piece1[1] / 10, piece1[1] % 10, piece1[2] / 10,
     piece1[2] % 10 };
   int[] piece2parts = { piece2[0] / 10, piece2[0] % 10,
     piece2[1] / 10, piece2[1] % 10, piece2[2] / 10,
     piece2[2] % 10 };
   int[] piece3parts = { piece3[0] / 10, piece3[0] % 10,
     piece3[1] / 10, piece3[1] % 10, piece3[2] / 10,
     piece3[2] % 10 };
   
   int i = 0;

   for (; i < 6;) {
    if ((piece1parts[i] + piece1parts[i + 1]) != 9)
     return false;
    i += 2;
   }
   i = 0;
   for (; i < 6;) {
    if ((piece2parts[i] + piece2parts[i + 1]) != 9)
     return false;
    i += 2;
   }
   i = 0;
   for (; i < 6;) {
    if ((piece3parts[i] + piece3parts[i + 1]) != 9)
     return false;
    i += 2;
   }

   return true;

  } catch (NumberFormatException e) {
   return false;
  }
 }//---------end of validateKeyCode()------------
}//----------end of KeyCodeGenerator-------------

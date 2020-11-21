package mainInterface;

import additionInterface.WindowManager;
import java.util.regex.Pattern;

public class Employee {

  /**
   * Object of FX Stage Manager to call Error and info Stage's.
   */
  final WindowManager WM = new WindowManager();

  /**
   * Name Of Employee
   */
  final StringBuilder name = new StringBuilder();

  /**
   * Username of Employee
   */
  String username;

  /**
   * Password of employee
   */
  final String password;

  /**
   * Email of employee
   */
  String email;

  Employee(String nameFull, String pass){
    if(checkName(nameFull)){
      setUsername(nameFull);
      setEmail(nameFull);
    }
    else{
      username = "default";
      email = "user@oracleacademy.Test";
      this.name.append("default");
      try{
        WM.displayOperation("Error ", 4, ": Invalid Name Try again\nMust have a Space!");
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
    if(isValidPassword(pass))
      password = pass;
    else{
      //Will Only Display if they Change the password in the file and Run the Employee Script
      //And Restart the program So this would mainly be for Employee Database Table if it includes
      //Passwords
      password = "pw";
      try{WM.displayOperation("Error ", 3, ": Invalid Password, Restart the Program");}
      catch(Exception e) {
        e.printStackTrace();
      }
    }
  }

  private boolean checkName(String name){
    boolean validName;
    try{
      String prior = name.substring(name.indexOf(" ")-1, name.indexOf(" ")); //Checks for double Space
      String after = name.substring(name.indexOf(" ")+1, name.indexOf(" ")+2); //check for double space
      if((!prior.contentEquals(" ")) && (!after.contentEquals(" "))){
        prior = name.substring(0,1).toUpperCase();
        after = after.toUpperCase();
        String Name = prior + name.substring(1,name.indexOf(" ")) + " " + after +
            name.substring(name.indexOf(" ")+2);
        validName = true;
        this.name.append(Name);
      }
      else
        validName = false;
    }
    catch(Exception e) {
      validName = false;
    }
    return validName;
  }

  private void setUsername(String fullName){
    username = fullName.substring(0,1).toLowerCase() +
        fullName.substring(fullName.indexOf(" ")+1).toLowerCase();
  }

  private void setEmail(String fullName){
    email = fullName.substring(0,fullName.indexOf(" ")).toLowerCase() + "." +
        fullName.substring(fullName.indexOf(" ")+1).toLowerCase() +
        "@oracleacademy.Test";
  }

  private boolean isValidPassword(String pass){
    boolean valid = true;
    Pattern[] regexes = new Pattern[3];
    regexes[0] = Pattern.compile(".*[A-Z].*");
    regexes[1] = Pattern.compile(".*[a-z].*");
    regexes[2] = Pattern.compile(".*[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*");

    for (Pattern input : regexes) {
      if (!input.matcher(pass).matches()) {
        valid = false;
      }
    }
    return valid;
  }

  @Override
  public String toString() {
    return "\nEmployee Details:" +
        "\nName : " + name +
        "\nUsername : " + username +
        "\nEmail : " + email +
        "\nInitial Password : " + password ;
  }
}

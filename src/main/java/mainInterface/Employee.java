package mainInterface;

import additionInterface.WindowManager;
import java.util.regex.Pattern;

public class Employee {

  WindowManager WM = new WindowManager();
  StringBuilder name = new StringBuilder();
  String username;
  String password;
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
        WM.displayError("Invalid Name Try again");
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
    if(isValidPassword(pass)) {
      password = pass;
    }
    else{
      password = "pw";
      try{WM.displayError("Invalid Password");}
      catch(Exception e){}
    }
  }

  private boolean checkName(String name){
    boolean validName = false;
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

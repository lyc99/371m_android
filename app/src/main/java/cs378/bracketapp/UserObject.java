package cs378.bracketapp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Development on 5/1/15.
 */
public class UserObject {
    @JsonProperty("email")
    private String email;
    @JsonProperty("brackets")
    private List<BracketObject> brackets;

    public UserObject(String e)
    {
        email = e;
        brackets = new ArrayList<BracketObject>();
    }

    public void addNewBracket(BracketObject b)
    {
        brackets.add(b);
    }

    public List<BracketObject> getBrackets() {
        return brackets;
    }

    public void setBrackets(List<BracketObject> brackets) {
        this.brackets = brackets;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}

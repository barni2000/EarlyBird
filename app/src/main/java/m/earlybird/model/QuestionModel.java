package m.earlybird.model;

import com.orm.SugarRecord;

public class QuestionModel extends SugarRecord{
    String question;
    String answer;

    public QuestionModel(){}
    public QuestionModel(String question, String answer){
        this.answer = answer;
        this.question = question;
    }

    public String getQuestion(){
        return question;
    }
    public String getAnswer(){
        return answer;
    }
}

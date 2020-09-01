package day6;


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SqlParse {

    public String parse(String description){
        description=description.toLowerCase();
        Stack<Character> symbol = new Stack<Character>();
        Stack<String> element = new Stack<String>();

        List<String> res = new LinkedList<String>();

        String subExpression = "";
        for (int i = 0;i<description.length();i++) {
            if(description.charAt(i) == '(') {
                symbol.push(description.charAt(i));
                if (!subExpression.equals("")) {
                    element.push(subExpression);
                    subExpression = "";
                }
            } else if(description.charAt(i) == ')') {
                if (!subExpression.equals("")) {
                    element.push(subExpression);
                    subExpression = "";
                }
                symbol.pop();
                String popValue = element.pop();
                check(popValue, res);
            } else {
                subExpression = subExpression + description.charAt(i);
            }
        }

        while (!element.isEmpty()) {
            check(element.pop(), res);
        }
        if (res.size() == 0 && !subExpression.equals(""))
            return generateSql(subExpression);

        if (!symbol.isEmpty())
            throw new IllegalArgumentException();

        return generateSql(res.get(0));

    }

    private void check(String popValue, List<String> res){
        if (popValue.replace(" ","").equals("and")){
            Condition expLeft = new ContextCondition(res.get(res.size()-2));
            Condition expRight = new ContextCondition(res.get(res.size()-1));
            Condition and = new AndCondition(expLeft,expRight);
            res.remove(res.size()-2);
            res.remove(res.size()-1);
            res.add(and.getDescription());
        } else if (popValue.replace(" ","").equals("or")){
            Condition expLeft = new ContextCondition(res.get(res.size()-2));
            Condition expRight = new ContextCondition(res.get(res.size()-1));
            Condition or = new OrCondition(expLeft,expRight);
            res.remove(res.size()-2);
            res.remove(res.size()-1);
            res.add(or.getDescription());
        }

        else if (popValue.replace(" ","").equals("!")){
            Condition condition = new ContextCondition(res.get(res.size()-1));
            Condition not = new NotCondition(condition);
            res.remove(res.size()-1);
            res.add(not.getDescription());
        }
        else {
            res.add(popValue);
        }
    }

    private String generateSql(String res) {
        res=res.replaceAll("==","=");
        res=res.replaceAll("!=","<>");
        return String.format("select * from table where %s",res);
    }
}

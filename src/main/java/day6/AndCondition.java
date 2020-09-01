package day6;


public class AndCondition implements Condition {

    private Condition leftCondition;
    private Condition rightCondition;

    public AndCondition(Condition leftCondition, Condition rightCondition) {
        this.leftCondition = leftCondition;
        this.rightCondition = rightCondition;
    }

    @Override
    public String getDescription() {
        return "("+leftCondition.getDescription()+") and ("+rightCondition.getDescription()+")";
    }
}

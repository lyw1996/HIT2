package day6;


public class OrCondition implements Condition {
    private Condition leftCondition;
    private Condition rightCondition;

    public OrCondition(Condition leftCondition, Condition rightCondition) {
        this.leftCondition = leftCondition;
        this.rightCondition = rightCondition;
    }

    @Override
    public String getDescription() {
        return "("+leftCondition.getDescription()+") or ("+rightCondition.getDescription()+")";
    }
}

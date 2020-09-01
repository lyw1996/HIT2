package day6;


public class NotCondition implements Condition {

    private Condition condition;

    public NotCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String getDescription() {
        return "not "+ condition.getDescription();
    }
}

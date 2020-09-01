package day6;


public class ContextCondition implements Condition{
    private String context;

    public ContextCondition(String context) {
        this.context = context;
    }

    @Override
    public String getDescription() {
        return context;
    }
}

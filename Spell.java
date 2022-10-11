public class Spell {

    private String action;
    private int potency;

    public Spell(String action, int potency) {
        this.action = action;
        this.potency = potency;
    }

    public String getAction() {
        return action;
    }

    public int getPotency() {
        return potency;
    }

}
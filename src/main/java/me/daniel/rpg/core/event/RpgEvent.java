package me.daniel.rpg.core.event;

public class RpgEvent {
    private String target;
    private RpgEventType type;
    private Object value;
    private int importance;

    public RpgEvent(String target, RpgEventType type, Object value, int importance) {
        this.target = target;
        this.type = type;
        this.value = value;
        this.importance = importance;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public RpgEventType getType() {
        return type;
    }

    public void setType(RpgEventType type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}

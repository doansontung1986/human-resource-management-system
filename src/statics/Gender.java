package statics;

public enum Gender {
    MALE("Nam"),
    FEMALE("Nữ"),
    OTHER("Khác");

    public String type;

    Gender(String type) {
        this.type = type;
    }
}

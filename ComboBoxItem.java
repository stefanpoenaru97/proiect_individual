package proiect_pi;

public class ComboBoxItem {

	private String code;
    private String name;

    public ComboBoxItem(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}

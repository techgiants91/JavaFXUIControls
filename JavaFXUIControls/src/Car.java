


import java.util.HashSet;

public class Car {
    // text field
    private String make;
    
    // text field
    private String model;

    // slider
    private Integer year;
    
    // radio buttons
    private String transmission;

    // combo box
    private String bodyType;

    // list view
    private HashSet<String> insurance = new HashSet();
    
    // check boxes
    private HashSet<String> extraOptions = new HashSet();

    public Car() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String transmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public HashSet<String> getInsurance() {
        return insurance;
    }

    public void setInsurance(HashSet<String> insurance) {
        this.insurance = insurance;
    }

    public HashSet<String> getExtraOptions() {
        return extraOptions;
    }

    public void setExtraOptions(HashSet<String> extraOptions) {
        this.extraOptions = extraOptions;
    }
}

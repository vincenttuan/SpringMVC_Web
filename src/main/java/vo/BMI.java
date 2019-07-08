package vo;

public class BMI {
    private double height;
    private double weight;
    private double value;
    
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BMI{" + "height=" + height + ", weight=" + weight + ", value=" + value + '}';
    }

    
    
    
}

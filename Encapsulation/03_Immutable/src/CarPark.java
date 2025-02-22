public class CarPark {

    private String machineName;   // название машины.
    private String machineBodyType;  // тип кузова.
    private int carMileageh;      // пробег машины.
    private String machineColor;  // цвет машины.

    public CarPark(String machineName, String machineBodyType) {
        this.machineName = machineName;
        this.machineBodyType = machineBodyType;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
    public String getMachineName() {
        return machineName;
    }

    public void setMachineBodyType(String machineBodyType) {
        this.machineBodyType = machineBodyType;
    }
    public String getMachineBodyType() {
        return machineBodyType;
    }

    public void setCarMileageh(int carMileageh) {
        this.carMileageh = carMileageh;
    }
    public int getCarMileageh() {
        return carMileageh;
    }

    public void setMachineColor(String machineColor) {
        this.machineColor = machineColor;
    }
    public String getMachineColor() {
        return machineColor;
    }

    public void print(String tittle) {
        System.out.println(tittle);
        System.out.println(machineName + "\n" + "Тип кузова: " + machineBodyType );
    }
}



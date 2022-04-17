public class Computer {
    private final String vendor; // производитель
    private final String name;   // название
    private int totalWeight = 0; // общая масса компьютера
    private Processor processor; // переменная названа именем класса, чтобы создать сеттер и геттер
    private RAM ram;
    private InformationAccumulator informationAccumulator;
    private Screen screen;
    private KeyBoard keyBoard;

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
        this.processor = processor;
        this.ram = ram;
        this.informationAccumulator = informationAccumulator;
        this.screen = screen;
        this.keyBoard = keyBoard;
    }
    public void add(int weightProcessor, int weightRA, int weightInformationAccumulator,
                    int diagonalScreen, int weightKeyBoard) { //метод расчёта общей массы компьютера, возвращающий
        // суммарный вес всех его комплектующих.
        totalWeight = weightProcessor + weightRA + weightInformationAccumulator + diagonalScreen +
                weightKeyBoard;
    }

    public void setProcessor(Processor processor) {this.processor = processor;
    }
    public Processor getProcessor() {return processor;
    }
    public void setRAM(RAM RAM) {this.ram = ram;
    }
    public RAM getRam() {return ram;
    }
    public void setInformationAccumulator(InformationAccumulator InformationAccumulator) {
        this.informationAccumulator = informationAccumulator;
    }
    public InformationAccumulator getInformationAccumulator() {return informationAccumulator;
    }
    public void setScreen(Screen Screen) {this.screen = screen;
    }
    public Screen getScreen() {return screen;
    }
    public void setKeyBoard(KeyBoard KeyBoard) {this.keyBoard = keyBoard;
    }

    public KeyBoard getKeyBoard() {return keyBoard;
    }

    public String toString() { //возвращает содержимое этого класса.
        return "Производитель:" + vendor + "\n" + "Название:" + name + "\n" + "Processor:" + processor +
                "\n" + "RAM: " + ram + "\n" + "InformationAccumulator: " + informationAccumulator + "\n" +
                "Screen: " + screen + "\n" + "KeyBoard: " + keyBoard + "\n" +
                "Cуммарный вес всех комплектующих:" + " " + totalWeight;
    }
}



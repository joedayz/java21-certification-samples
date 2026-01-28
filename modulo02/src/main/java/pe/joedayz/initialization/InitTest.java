package pe.joedayz.initialization;

public class InitTest {

    private static int count;

    private String name;

    static {
        count = 0;
        System.out.println("1) Count = "+ count);
    }

    {
        name = "Default";
        System.out.println("2) Name = "+ name);
    }

    public InitTest() {
        System.out.println("3) Constructor InitTest");
        name = "Default in constructor";
    }

    public static void main(String[] args) {
            InitTest initTest = new InitTest();
    }
}

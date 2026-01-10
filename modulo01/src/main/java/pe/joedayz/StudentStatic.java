package pe.joedayz;

public class StudentStatic {

    static String name;

    static{
        name = "Jose";
    }

    public StudentStatic() {
        name = "Fred";  // no tiene efecto, porque no tengo una instancia
    }

    public static void main(String[] args) {
        System.out.println(StudentStatic.name);
    }
}

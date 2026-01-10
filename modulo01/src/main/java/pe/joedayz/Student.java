package pe.joedayz;

public class Student {

    String name = "Hugo";


    { //Bloque de inicializacion de instancia
        name = "Jose";
    }

    public Student(){
        this.name = "Fred";
    }

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.name);
    }

}

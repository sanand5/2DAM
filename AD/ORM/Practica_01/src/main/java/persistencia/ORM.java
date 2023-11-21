package persistencia;

import org.hibernate.Session;

import centro.Persona;
// import centro.Matriculas;
// import centro.Modulos;
// import utilidades.App;
// import utilidades.Lector;


public class ORM 
{
    // private static Alumnos alumnos;
    // private static Modulos modulos;
    // private static Matriculas matriculas;
    private static Persona persona;
    private static final Conexion conexion=new Conexion();
    
    private int menu()
    {
        int option=-1;
        // Lector in = new Lector(System.in);
        while ((option<0))
        {
            System.out.println("(0)- Salir");
            System.out.println("(1)- Mantenimiento de Alumnos");            
            System.out.println("(2)- Mantenimiento de Modulos");
            System.out.println("(3)- Mantenimiento de Matriculas");
            // option=in.leerEntero(0,5);
        }
        return option;        
    }
    private static int menuAlumnos()
    {
        int retorno=0;
        // alumnos=new Alumnos();
        // alumnos.Gestion();
        return retorno;
    }            
    private static int menuModulos()
    {
        int retorno=0;
        // modulos=new Modulos();
        // modulos.Gestion();
        return retorno;
    }
    private static int menuMatriculas()
    {
        int retorno=0;
        // matriculas=new Matriculas();
        // matriculas.Gestion();
        return retorno;
    }
    private void haz(int choice)
    {
        switch(choice)
        {
            case 0:break;
            case 1:menuAlumnos();break;            
            case 2:menuModulos();break;
            case 3:menuMatriculas();break;            
            // default: System.out.println(App.ANSI_CYAN+"Hay que elegit una de las opciones (numero entre parentesis)"+App.ANSI_WHITE);
        }
    }
    public void haz()
    {
        int opcion;
        Session sesion = conexion.getSessionFactory().openSession();
        String resultado = (String) sesion.createNativeQuery("SELECT VERSION()").getSingleResult();
        System.out.println("La versión que estás usando es: " +resultado);
        do
        {
            opcion=menu();
            haz(opcion);
        }
        while (opcion!=0);
    }
    // public static Alumnos alumnos()
    // {
    //     return alumnos;
    // }
    // public static Modulos modulos()
    // {
    //     return modulos;
    // }
    // public static Matriculas matriculas()
    // {
    //     return matriculas;
    // }
    // public Conexion conexion()
    // {
    //     return conexion;
    // }
}
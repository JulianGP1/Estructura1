import com.google.gson.Gson;
import java.util.Scanner;

    public void main(String[] args) {
        Gson gson = new Gson();
        Lista l = new Lista();
        Scanner sc = new Scanner(System.in);
        boolean r = true;
        boolean listaCreada = false;

        while (r) {
            System.out.println("\nMenú:");
            System.out.println("0. Crear Lista de pacientes");
            System.out.println("1. Insertar Paciente");
            System.out.println("2. Desplegar lista");
            System.out.println("3. Ordenar lista con Bubble Sort");
            System.out.println("4. Buscar y modificar un valor");
            System.out.println("5. Salir");

            System.out.print("Seleccione una opción: ");

            String opcion = sc.next();

            switch (opcion) {
                case "0":
                    System.out.println("Creando agenda (lista de pacientes).. ");
                    new CreateLista(l);
                    listaCreada = true;

                    break;

                case "1":
                    if (!listaCreada) {
                        System.out.println("Primero debe crear la lista de pacientes.");
                        r=false;
                        break;
                    }

                    System.out.println("Ingresa nombre del paciente:");
                    sc.nextLine();
                    String nombre = sc.nextLine();
                    System.out.println("Ingresa numero telefonico del paciente:");
                    int numeroTel = sc.nextInt();
                    System.out.println("Ingresa numero de cedula del paciente:");
                    int cedula = sc.nextInt();
                    System.out.println("Ingresa la enfermedad del paciente:");
                    l.ingresarDato(nombre, numeroTel, cedula);
                    break;

                case "2":
                    l.desplegar();
                    break;

                case "3":
                    l.ordenarLista();
                    System.out.println("Lista ordenada con Bubble Sort.");
                    break;

                case "4":
                    System.out.print("Ingrese el número a buscar: ");
                    int buscar = sc.nextInt();
                    System.out.print("Ingrese el nuevo valor: ");
                    int nuevoValor = sc.nextInt();
                    boolean modificado = l.buscarYModificar(buscar, nuevoValor);
                    if (modificado) {
                        System.out.println("El número fue modificado correctamente.");
                    } else {
                        System.out.println("El número no se encontró en la lista.");
                    }
                    break;

                case "5":
                    r = false;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        sc.close();
    }


class CreateLista {

    // Constructor que toma una lista y agrega nodos
    public CreateLista(Lista lista) {
        // Crear algunos pacientes de ejemplo y asignarlos a la lista
        Nodo paciente1 = new Nodo("Juan Pérez", 123456789, 1010);
        lista.ingresarDato(paciente1);

        Nodo paciente2 = new Nodo("Ana Gómez", 987654321, 2020);
        lista.ingresarDato(paciente2);

        Nodo paciente3 = new Nodo("Carlos Rodríguez", 456789123, 3030);
        lista.ingresarDato(paciente3);

        System.out.println("Lista de pacientes creada con éxito.");
    }
}

class Nodo {
    String nombre;
    int numeroTel;
    int cedula;
    Nodo sig; // Apunta al siguiente nodo

    // Constructor para Nodo
    public Nodo(String nombre, int numeroTel, int cedula) {
        this.nombre = nombre;
        this.numeroTel = numeroTel;
        this.cedula = cedula;
    }
}

class Lista {
    Nodo primero;
    Nodo ultimo;

    // Método para insertar un nodo con los datos de un paciente
    public void ingresarDato(Nodo nuevo) {
        nuevo.sig = null;

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.sig = nuevo;
            ultimo = nuevo;
        }
    }

    // Método para insertar un paciente con datos (nombre, teléfono, cédula)
    public void ingresarDato(String nombre, int numeroTel, int cedula) {
        Nodo nuevo = new Nodo(nombre, numeroTel, cedula);
        nuevo.sig = null;

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.sig = nuevo;
            ultimo = nuevo;
        }
    }

    // Desplegar la lista de pacientes
    public void desplegar() {
        if (primero == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo actual = primero;
        while (actual != null) {
            System.out.println("Nombre: " + actual.nombre + ", Teléfono: " + actual.numeroTel + ", Cédula: " + actual.cedula);
            actual = actual.sig;
        }
    }

    // Ordenar la lista usando Bubble Sort
    public void ordenarLista() {
        if (primero == null || primero.sig == null) return;

        boolean huboIntercambio;
        do {
            huboIntercambio = false;
            Nodo actual = primero;

            while (actual.sig != null) {
                if (actual.cedula > actual.sig.cedula) {
                    // Intercambiar los datos de los nodos
                    String tempNombre = actual.nombre;
                    int tempNumeroTel = actual.numeroTel;
                    int tempCedula = actual.cedula;

                    actual.nombre = actual.sig.nombre;
                    actual.numeroTel = actual.sig.numeroTel;
                    actual.cedula = actual.sig.cedula;

                    actual.sig.nombre = tempNombre;
                    actual.sig.numeroTel = tempNumeroTel;
                    actual.sig.cedula = tempCedula;

                    huboIntercambio = true;
                }
                actual = actual.sig;
            }
        } while (huboIntercambio);
    }

    // Buscar y modificar un valor en la lista
    public boolean buscarYModificar(int buscar, int nuevoValor) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.cedula == buscar) {
                actual.cedula = nuevoValor;
                return true;
            }
            actual = actual.sig;
        }
        return false;
    }
}

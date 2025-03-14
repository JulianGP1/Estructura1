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
                case "6": int cont =0;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        sc.close();
    }


class CreateLista {

    public CreateLista(Lista lista) {
        // Crear algunos pacientes de ejemplo y asignarlos a la lista
        Nodo paciente1 = new Nodo("", 0, 0);
        lista.ingresarDato(paciente1);


        System.out.println("Lista de pacientes creada con éxito.");
    }
}

class Nodo {
    String nombre;
    int numeroTel;
    int cedula;
    Nodo sig; // Apunta al siguiente nodo


    public Nodo(String nombre, int numeroTel, int cedula) {
        this.nombre = nombre;
        this.numeroTel = numeroTel;
        this.cedula = cedula;
    }
}

class Lista {
    Nodo primero;
    Nodo ultimo;


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

    // Ordeno  la lista usando un  Bubble Sort
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

//funcion para buscar y modificar un dato ,* el buscar sirve para cualquir otra funcion*
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


    public void contar(int buscar) {
        int cont = 0;
        Nodo actual = primero;
        while (actual != null) {
            if (actual.cedula == buscar) {
                cont++;
            }
            System.out.println("hay " + cont + " nodos en la lista.");

        }
    }
}


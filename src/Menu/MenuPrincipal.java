package Menu;

import javax.swing.JOptionPane;
import Archivos.Animal;
import Archivos.ManejadorArchivo;

public class MenuPrincipal extends Animal {
    private static final long serialVersionUID = 1L;
    private ManejadorArchivo manejadorArchivo;

    public MenuPrincipal() {
        manejadorArchivo = new ManejadorArchivo();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            String opcionStr = JOptionPane.showInputDialog(null,
                    "Aplicación Veterinaria\n" + "Menú Principal\n" + "1. Ingresar\n" + "2. Modificar\n" + "0. Salir\n"
                            + "Seleccione una opción:",
                    "Menú Principal", JOptionPane.PLAIN_MESSAGE);

            opcion = Integer.parseInt(opcionStr);

            switch (opcion) {
                case 1:
                    ingresarAnimal();
                    mostrarInformacionAnimal();
                    break;
                case 2:
                    modificarAnimal();
                    mostrarInformacionAnimal();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "¡Adiós!", "Salir", JOptionPane.PLAIN_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.", "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        } while (opcion != 0);
    }

    private void ingresarAnimal() {
        String codigoStr = JOptionPane.showInputDialog(null, "Ingrese el código del animal:", "Ingresar Animal",
                JOptionPane.PLAIN_MESSAGE);
        int codigo = Integer.parseInt(codigoStr);

        String nombreEspecie = JOptionPane.showInputDialog(null, "Ingrese el nombre de la especie:",
                "Ingresar Animal", JOptionPane.PLAIN_MESSAGE);

        Animal animal = new Animal(codigo, nombreEspecie);
        if (manejadorArchivo.Insertar(animal)) {
            JOptionPane.showMessageDialog(null, "Animal ingresado correctamente.", "Éxito",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al ingresar el animal.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarAnimal() {
        String codigoStr = JOptionPane.showInputDialog(null, "Ingrese el código del animal a modificar:",
                "Modificar Animal", JOptionPane.PLAIN_MESSAGE);
        int codigo = Integer.parseInt(codigoStr);

        String nuevoNombreEspecie = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de la especie:",
                "Modificar Animal", JOptionPane.PLAIN_MESSAGE);

        if (manejadorArchivo.Modificar(codigo, nuevoNombreEspecie)) {
            JOptionPane.showMessageDialog(null, "Animal modificado correctamente.", "Éxito",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al modificar el animal o código no encontrado.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarInformacionAnimal() {
        JOptionPane.showMessageDialog(null, "Último animal ingresado o modificado:\n" + manejadorArchivo.obtenerUltimoAnimal(),
                "Información Animal", JOptionPane.PLAIN_MESSAGE);
    }
}

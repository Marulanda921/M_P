package Archivos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

public class ManejadorArchivo {
	public static final int TAMANO_REGISTRO = 8;
	private static final String archivot = "Datos//datosanimales.txt";

	public boolean Insertar(Animal datos) {
		boolean wmensaje = false;
		try {
			File file = new File(archivot);
			File directory = file.getParentFile();
			if (!directory.exists()) {
				directory.mkdirs();
			}

			RandomAccessFile archivo = new RandomAccessFile(file, "rw");
			archivo.seek(archivo.length());

			archivo.writeInt(datos.getCodigo());
			archivo.writeUTF(datos.getNombreEspecie());

			archivo.close();
			wmensaje = true;

		} catch (IOException e) {
			System.out.println("Error al insertar el animal: " + e.getMessage());
		}
		return wmensaje;
	}

	public boolean InsertarSerializado(Animal[] datosAnimal) {
		boolean wmensaje = false;
		try {
			File file = new File("Datos//datosanimalSerializado.txt");
			File directory = file.getParentFile();
			if (!directory.exists()) {
				directory.mkdirs();
			}

			FileOutputStream ruta_salida = new FileOutputStream(file);
			ObjectOutputStream archivo_salida = new ObjectOutputStream(ruta_salida);
			archivo_salida.writeObject(datosAnimal);
			archivo_salida.close();
			wmensaje = true;
		} catch (Exception e) {
			System.out.println("Error al insertar animales serializados: " + e.getMessage());
		}
		return wmensaje;
	}

	public boolean Modificar(int codigo, String nuevoNombreEspecie) {
		boolean modificado = false;
		try {
			File archivo = new File(archivot);
			File archivoTemporal = new File("Datos//datosanimales_temp.txt");
			File directory = archivo.getParentFile();
			if (!directory.exists()) {
				directory.mkdirs();
			}

			try (RandomAccessFile lector = new RandomAccessFile(archivo, "r");
					RandomAccessFile escritor = new RandomAccessFile(archivoTemporal, "rw")) {

				while (lector.getFilePointer() < lector.length()) {
					int codigoActual = lector.readInt();
					String nombreEspecieActual = lector.readUTF();

					if (codigoActual == codigo) {
						escritor.writeInt(codigo);
						escritor.writeUTF(nuevoNombreEspecie);
						modificado = true;
					} else {
						escritor.writeInt(codigoActual);
						escritor.writeUTF(nombreEspecieActual);
					}
				}
			}

			if (modificado) {
				archivo.delete();
				archivoTemporal.renameTo(archivo);
			} else {
				archivoTemporal.delete();
			}
		} catch (IOException e) {
			System.out.println("Error al modificar el animal en el archivo: " + e.getMessage());
		}
		return modificado;
	}

	public Animal obtenerUltimoAnimal() {
		Animal ultimoAnimal = null;
		try {
			File archivo = new File(archivot);
			if (archivo.exists()) {
				try (RandomAccessFile lector = new RandomAccessFile(archivo, "r")) {
					long posicionUltimoAnimal = archivo.length() - TAMANO_REGISTRO;
					if (posicionUltimoAnimal >= 0) {
						lector.seek(0);
						while (lector.getFilePointer() < posicionUltimoAnimal) {
							int codigo = lector.readInt();
							String nombreEspecie = lector.readUTF();
							ultimoAnimal = new Animal(codigo, nombreEspecie);
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error al obtener el último animal del archivo: " + e.getMessage());
		}
		return ultimoAnimal;
	}

}

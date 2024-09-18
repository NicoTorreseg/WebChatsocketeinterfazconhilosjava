package TP7.progredes.et37;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Binary_file {
	FileOutputStream fos;
	ObjectOutputStream oos;
	FileInputStream fis;
	ObjectInputStream ois;
	String Nombre_archivo;
	boolean append;
	public Binary_file(String Archivo, boolean append){
		this.Nombre_archivo=Archivo;
		this.append=append;
				
			
		
	}
	public void Escribir(Object objeto) throws FileNotFoundException, IOException{
		fos = new FileOutputStream(Nombre_archivo, append);
		oos = new ObjectOutputStream(fos);
		oos.flush();
		//AGARRA EL DIRECTORIO DEL PROYECTO
		oos.writeObject(objeto);
		oos.close();
		fos.close();
	
	}
	
	public Object Leer() throws FileNotFoundException,ClassNotFoundException,IOException{
		fis=null;
		ois=null;
		fis = new FileInputStream(Nombre_archivo);
		ois = new ObjectInputStream(fis);
		Object ArrayListObjeto= ois.readObject();;
		return ArrayListObjeto;
	}
	
	
	
}

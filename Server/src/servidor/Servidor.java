package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {	
	
	public static void main(String[] args) {
		
		ServerSocket servidor = null;
		
		try {
			
			System.out.println("Iniciando o servidor...");
			servidor = new ServerSocket(9999);
			System.out.println("Servidor Iniciado!");
			
			while(true){
				
				Socket cliente = servidor.accept();
				new GerenciadorClientes(cliente);
				
			}
			
		} catch(IOException e) {
			
			System.err.println("A porta do seridor est� ocupada");
			e.printStackTrace();
			
		}
	}
}

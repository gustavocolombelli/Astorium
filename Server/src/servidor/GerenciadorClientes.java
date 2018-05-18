package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GerenciadorClientes extends Thread{
	
		private Socket cliente;
		private String nomeCliente;
		
		public GerenciadorClientes(Socket cliente){
			this.cliente = cliente;
			start();
		}
		
		public void run() {
			try {
				BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
				escritor.println("Login: ");
				
				String msg = leitor.readLine();
				this.nomeCliente = msg;
				escritor.println("olá " + this.nomeCliente);
				
				while(true) {
					msg = leitor.readLine();
					escritor.println("Seu login é: " + msg);
				}
				
			} catch (IOException e) {
				System.err.println("O cliente desconectou-se");
				e.printStackTrace();
			}
		}
}

package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) {
		
		try {
			
			final Socket cliente = new Socket("127.0.0.1", 9999);
			
			//lendo mensagens do servidor
			 new Thread() {
				 @Override
				 public void run() {
					 try {
						BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
						PrintWriter escritor = new PrintWriter(cliente.getOutputStream());
						while(true) {
							String mensagem = leitor.readLine();
							System.out.println("O servidor disse: " + mensagem);
						}
						
					} catch (IOException e) {
						System.out.println("Impossivel ler mensagem do servidor");
						e.printStackTrace();
					}
				 }
			 }.start();
			 
			 //escrevendo para o servidor
			 PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
			 BufferedReader leitorTerminal = new BufferedReader(new InputStreamReader(System.in));
			 while(true) {
				 String mensagemTerminal = leitorTerminal.readLine();
				 escritor.println(mensagemTerminal);
			 }
			
		} catch (UnknownHostException e) {
			System.out.println("O endereço informado é inválido");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Conexão recusada");
			e.printStackTrace();
		}
	
	}

}

package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private ServerSocket serverSocket;
	
	private void criaServerSocket(int porta) throws IOException 
	{
		
		serverSocket = new ServerSocket(porta);
		
	}
	
	private Socket esperaConexao() throws IOException 
	{
		Socket socket = serverSocket.accept();		
		return socket;
	}
	
	private void fechaSocket(Socket socket) throws IOException
	{
		socket.close();
	}
	
	
	private void trataConexao(Socket socket) throws IOException
	{
		//protocolo da aplicação
		try {
			
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

			System.out.println("Tratando...");
			
			String login = input.readUTF();
			String senha = input.readUTF();
			System.out.println("Credenciais recebidas" + login + senha);
			
			output.writeUTF("Checando login" + login);
			output.flush();
			output.writeUTF("Checando senha" + senha);
			output.flush();
			
			output.close();
			input.close();
			
		}catch(IOException e) {
			System.out.println("Problema no tratamento de conexão com o cliente: " + socket.getInetAddress());
			System.out.println("Erro: " + e.getMessage());
		}finally {
			fechaSocket(socket);
		}
	}
	
	public static void main(String[] args) {
		try {
			
			Servidor servidor = new Servidor();
			System.out.println("Aguardando conexão de cliente...");
			servidor.criaServerSocket(5545);
			
			while(true) {		
				
				Socket cliente = servidor.esperaConexao();				
				System.out.println("Cliente conectado");
				servidor.trataConexao(cliente);
				System.out.print("Cliente finalizado");
				
			}
		}catch(IOException e) {
			System.out.println("Não foi possível inicar o servidor. Erro: " + e.getMessage());
		}
	}
}

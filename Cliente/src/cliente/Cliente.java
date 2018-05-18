package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) {
		
		try {
			
			System.out.println("Estabelecendo conexão...");
			Socket socket = new Socket("127.0.0.1", 5545);
			System.out.println("Conexão estabelecida.");
		
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			
			
			Scanner entrada = new Scanner(System.in);
			String login, senha;
			
			System.out.print("Login: ");
			login = entrada.next();
			System.out.print("Senha: ");
			senha = entrada.next();
			
			System.out.println("Enviando credenciais");
			
			output.writeUTF(login);
			output.writeUTF(senha);
			
			output.flush();
			
			String mensagemAutenticacaoLogin = input.readUTF();
			String mensagemAutenticacaoSenha = input.readUTF();
			System.out.println(mensagemAutenticacaoLogin);
			System.out.println(mensagemAutenticacaoSenha);
			
			input.close();
			output.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

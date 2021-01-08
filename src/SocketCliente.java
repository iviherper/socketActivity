import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketCliente {

	public static void main(String[] args) {
		final int PUERTO = 2021;
		final String ip= "localhost";
		
		System.out.println("------------Aplicacion Cliente------------");
		System.out.println("------------------------------------------");

		Socket socketCliente = null;
		InputStreamReader entrada = null;
		PrintStream salida = null;
		
		InetSocketAddress direccion = new InetSocketAddress(ip,PUERTO);
		Scanner sc = new Scanner(System.in);

		try {
			socketCliente = new Socket();
			socketCliente.connect(direccion);
			
			entrada = new InputStreamReader(socketCliente.getInputStream());
			salida = new PrintStream(socketCliente.getOutputStream());
			
			System.out.println("Introduce la operacion a realizar, sumar, restar, multiplicar o dividir");
			String operacion = sc.nextLine();
			
			System.out.println("Introduce el numero 1");
			operacion += "-"+sc.nextLine();
			System.out.println("Introduce el numero 2");
			operacion += "-"+sc.nextLine();
			
			salida.println(operacion);
			
			BufferedReader bf = new BufferedReader(entrada);
			String resultado = bf.readLine();
			System.out.println(resultado);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			salida.close();
			sc.close();
			try {
				entrada.close();
				socketCliente.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

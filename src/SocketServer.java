import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static void main(String[] args) {
		final String IP_SERVER = "localhost";
		final int PORT = 2021;

		System.out.println("------------Aplicacion Servidor------------");
		System.out.println("-------------------------------------------");

		Socket socketConexion = null;
		ServerSocket servidorSocket = null;
		PrintStream salida = null;
		InputStreamReader entrada = null;

		try {
			servidorSocket = new ServerSocket();
			InetSocketAddress direccion = new InetSocketAddress(PORT);
			servidorSocket.bind(direccion);

			while (true) {
				System.out.println("Esperando peticion...");
				socketConexion = servidorSocket.accept();

				entrada = new InputStreamReader(socketConexion.getInputStream());
				BufferedReader bf = new BufferedReader(entrada);
				String numeros[] = bf.readLine().toLowerCase().split("-");
				int num1 = Integer.parseInt(numeros[1]);
				int num2 = Integer.parseInt(numeros[2]);
				salida = new PrintStream(socketConexion.getOutputStream());
				int resultado=0;
				switch (numeros[0]) {
				case "restar":
					resultado = num1-num2;
					break;
				case "sumar":
					resultado=num1+num2;
					break;
				case "multiplicar":
					resultado=num1*num2;
					break;
				case "dividir":
					resultado=num1/num2;
					break;

				}
				salida.println(resultado);
			}

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}finally {
			salida.close();
			try {
				entrada.close();
				socketConexion.close();
				servidorSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
}

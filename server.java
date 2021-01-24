import java.net.*;
import java.io.* ;
import java.util.*;

public class server {

	public static void main(String[] args) throws IOException {
		
		ServerSocket ss = new ServerSocket(4999);			// server socket/node created
		Socket s = ss.accept();								// socket accepts request from client socket to server socket
		
		System.out.println("Client connected.");			// connection established.
		Scanner sc = new Scanner(System.in);				// scanner object created for input from keyboard
		String smsg, cmsg;
		
		InputStreamReader in = new InputStreamReader(s.getInputStream());
				// InputStreamReader converts byte streams to character streams
		BufferedReader bf = new BufferedReader(in);
				// BufferedReader uses buffering that reduces no. of read from disk/STDIN by copying chunks to main memory.

		// OR we could just write: BufferedReader bf = new BufferedReader( new InputStreamReader(s.getInputStream()) );

		PrintWriter pr = new PrintWriter(s.getOutputStream());
				// Prints formatted representations of objects to a text-output stream.

		while (true) {

			cmsg = bf.readLine();							// incoming message is stored into a string variable
			System.out.println("C: " + cmsg);				// incoming message is printed

			// End the session if input = "bye" or "exit"
			if ( cmsg.equalsIgnoreCase("bye") || cmsg.equalsIgnoreCase("exit")) {
				System.out.println("Connection terminated");
				break;
			}

			System.out.println("Enter a message.");
			System.out.print("S: ");
			smsg = sc.nextLine();							// input from keyboard
			pr.println(smsg);								// sends input to server
			pr.flush();										// forces any buffered output bytes in output stream to be written

			// End the session if input = "bye" or "exit"
			if ( smsg.equalsIgnoreCase("bye") || smsg.equalsIgnoreCase("exit")) {
				System.out.println("Connection terminated");
				break;
			}

		}
	}
}
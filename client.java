import java.net.* ;
import java.io.*  ;
import java.util.*;


public class client {

	public static void main(String[] args) throws IOException {
		
		Socket s = new Socket("LocalHost", 4999);			// client socket/node created

		Scanner sc = new Scanner(System.in);				// scanner object created for input from keyboard
		String msg, str;

		InputStreamReader in = new InputStreamReader(s.getInputStream());
				// InputStreamReader converts byte streams to character streams
		BufferedReader bf = new BufferedReader(in);
				// BufferedReader uses buffering that reduces no. of read from disk/STDIN by copying chunks to main memory.
		
		// OR we could just write: BufferedReader bf = new BufferedReader( new InputStreamReader(s.getInputStream()) );
		
		PrintWriter pr = new PrintWriter(s.getOutputStream());
				// Prints formatted representations of objects to a text-output stream.

		System.out.println("Server connected.");			// connection established.

		while (true) {

			System.out.println("Enter a message.");
			System.out.print("C: ");
			msg = sc.nextLine();							// input from keyboard
			pr.println(msg);								// sends input to server
			pr.flush();										// forces any buffered output bytes in output stream to be written
			
				// End the session if input = "bye" or "exit"
			if ( msg.equalsIgnoreCase("bye") || msg.equalsIgnoreCase("exit")) {
				System.out.println("Connection terminated");
				break;
			}

			str = bf.readLine();							// incoming message is stored into a string variable 
			System.out.println("S: "+str);					// incoming message is printed
				
			// End the session if input = "bye" or "exit"
			if ( str.equalsIgnoreCase("bye") || str.equalsIgnoreCase("exit")) {
				System.out.println("Connection terminated");
				break;
			}
			
		}
	}
}
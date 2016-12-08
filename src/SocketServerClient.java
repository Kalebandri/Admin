import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class SocketServerClient {

    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("ADMIN");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);

    
    public SocketServerClient() {

        
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

        
        textField.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
    }

   
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
            frame,
            "Masukkan IP Client:",
            "Welcome",
            JOptionPane.QUESTION_MESSAGE);
    }

    
    private String getName() {
        return JOptionPane.showInputDialog(
            frame,
            "Masukkan nama anda:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
    }

    
    private void run() throws IOException {

        
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9090);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                messageArea.append(line.substring(8) + "\n");
            }
        }
    }
 
    public static void main(String[] args) throws Exception {
    	SocketServerClient client = new SocketServerClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}
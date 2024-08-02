package selenium.selenium.Connections;

import org.apache.commons.net.PrintCommandListener; import org.apache.commons.net.ftp.FTP; import org.apache.commons.net.ftp.FTPClient; import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

public class FTPConnection {

 /*   private static String USER = "username";
    private static String PASS = "password ";
    private static int PORT = 55;
    private static String HOST = "somehost.test.com"; */

    FTPClient ftpClient = new FTPClient();

    public FTPConnection(String host, int port, String username, String password) throws Exception {

        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftpClient.connect(host, port);
        System.out.println("FTP URL is:" + ftpClient.getDefaultPort());
        reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)){
            ftpClient.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }

        ftpClient.login(username, password);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();

    }

	public void FTPUpload(String localFile, String fileName, String hostDir)throws Exception {
	    try {
	        InputStream input = new FileInputStream(new File(localFile));
	        this.ftpClient.storeFile(hostDir + fileName, input);
	        System.out.println("File uploaded successfully");
	    }   catch (Exception e) {    }
	
	}

    public void disconnect(){
        if (this.ftpClient.isConnected()) {
            try {
                this.ftpClient.logout();
                this.ftpClient.disconnect();
            } catch (IOException f) {  }
         }   
        }
    
    
    /*
     
     File file1 = new File("D:\TEST.csv"); 
     FTPConnect ftpboj = new FTPConnect("somehost.test.com", 55, "username", "password"); 
     ftpboj.FTPUpload(file1.getPath(), file1.getName(), ""); 
     ftpboj.disconnect();
      */
    
}
package snake.gui.login;

//  imports
import snake.gui.components.PassField;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Secure {

    //  fields
    private String pwd;
    private String us;
    private String encryptedPwd;
    private File file;

    //  constructor
    public Secure(String us, String pwd) {
        this.us = us;
        this.pwd = pwd;
    }

    public boolean start() {
        this.encryptedPwd = encrypt(this.pwd);
        boolean success = makeFile(this.us);
        return success;
    }

    //  encrypt
    public String encrypt(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] pwdBytes = pwd.getBytes();
            md.reset();
            byte[] digested = md.digest(pwdBytes);
            return toHex(digested);
        } catch (NoSuchAlgorithmException e) {
            //  do nothing
        }
        return null;
    }

    public String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }

    //  make file
    private boolean makeFile(String name) {
        try {
            this.file = new File("data/users/" + name + ".txt");
            if (file.createNewFile()) {
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            //  do nothing
        }
        return false;
    }

    public int writeToFile() {
        try {
            File file = this.file;
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter writer = new PrintWriter(bw);
            writer.println(this.encryptedPwd);
            writer.println("0");
            bw.close();
        } catch (IOException e) {
            //  do nothing
        }
        return 1;
    }

    public boolean verify() {
        try {
            File file = this.file;
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String expectedPwd = reader.readLine();
            if (this.encryptedPwd.equals(expectedPwd)) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            //  do nothing
        }
        return false;
    }

    //  get user
    public String getUser() {
        return this.us;
    }



}

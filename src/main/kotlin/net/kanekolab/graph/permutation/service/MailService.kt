package net.kanekolab.graph.permutation.service

/**
 * Created by bhags on 2017/01/30.
 */
import java.util.*
import javax.mail.*
import javax.mail.internet.*
import javax.activation.*
import net.kanekolab.graph.permutation.core.Config
class MailService {



    fun sendMail(from:String, to:String, subject:String, content:String){
        val props = Properties()
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        val session = Session.getDefaultInstance(props,
            object:javax.mail.Authenticator(){
                override fun getPasswordAuthentication(): PasswordAuthentication?{
                    return PasswordAuthentication(Config.gmailUserName,Config.gmailUserPassword)
                }
            }
        );

        try {
            val message =  MimeMessage(session);
            message.setFrom(InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
        } catch (e:MessagingException ) {
            throw  RuntimeException(e);
        }
    }

}


/**
 * public class msgsendsample{
static String msgText = "This is a message body.¥nHere's the second line.";

public static void main(String[] args){
if (args.length != 4){
usage();
System.exit(1);
}

System.out.println();

String to = args[0];
String from = args[1];
String host = args[2];
boolean debug = Boolean.valueOf(args[3]).booleanValue();

Properties props = new Properties();
props.put("mail.smtp.host", host);
if (debug){
props.put("mail.debug", args[3]);
}

Session session = Session.getInstance(props, null);
session.setDebug(debug);

try{
Message msg = new MimeMessage(session);
msg.setFrom(new InternetAddress(from));
InternetAddress[] address = {new InternetAddress(args[0])};
msg.setRecipients(Message.RecipientType.TO, address);
msg.setSubject("JavaMail APIs Test");
msg.setSentDate(new Date());
msg.setText(msgText);

Transport.send(msg);
}catch(MessagingException mex){
System.out.println("¥n--Exception handling in msgsendsample.java");
mex.printStackTrace();
}
}

private static void usage(){
System.out.println("usage: java msgsendsample <to> <from> <smtp> true|false");
}
}
 */

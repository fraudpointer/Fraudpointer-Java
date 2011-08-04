package com.fraudpointer;

import com.fraudpointer.api.ClientException;
import com.fraudpointer.api.ClientFactory;
import com.fraudpointer.api.IClient;

/**
 * This is a console application that can be used to encrypt a credit card hash the way Fraudpointer service requires you to do. <br/>
 * <br/>
 * We emphasize here that when you want to send the credit card to Fraudpointer Server, you <b>should not send it in plain clear format</b>.
 * You <b>should use the {@link com.fraudpointer.api.IClient#creditCardHash(String) creditCardHash} method</b>, like this sample application does
 * below.<br/>
 * <br/>
 * Note that you can use <code>java -cp fraudpointer_client.jar com.fraudpointer.EncryptCreditCard &lt;credit_card_to_encrypt&gt;</code> if you want to
 * immediately run this utility.
 */
public class EncryptCreditCard {
    public static void main (String[] args)
    {
        if (args.length != 1) {
            wrongSyntax();
            return;
        }

        String creditCard = args[0];

        IClient fraudpointerClient = ClientFactory.construct("", "");
        String encryptedCreditCard = "";
        try {
            encryptedCreditCard = fraudpointerClient.creditCardHash(creditCard);
        }
        catch (ClientException ex) {
            ex.printStackTrace();
            return;
        }

        System.out.println("Encrypted credit card: " + encryptedCreditCard);


    } // main ()
    //----------

    protected static void wrongSyntax ()
    {
        System.err.println("Correct syntax is: com.fraudpointer.EncryptCreditCard <credit_card_number>");
    } // wrongSyntax()
    //-----------------

} // class EncryptCreditCard
//---------------------------


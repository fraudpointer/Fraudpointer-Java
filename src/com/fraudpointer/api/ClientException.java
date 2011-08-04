package com.fraudpointer.api;

/**
 * Thrown by all IClient methods in case an error takes place and method cannot return its results normally. <br/>
 * <br/>
 * You should always put your IClient methods into <code>try/catch</code> blocks and make sure you catch this exception.
 * If you catch this exception, the message borne will be adequate to explain to you what has gone wrong.<br/>
 * <br/>
 * Here is how you can wrap your code (example of a Console Application) around <code>try/catch</code> block
 * and on error get information about what has gone wrong:<br/>
 *
 * <pre>
 * try {
 *     ... your Fraudpointer code goes here ....
 * }
 * catch (Exception ex)
 * {
 *   System.err.println(ex.getMessage());
 *   ex.printStackTrace();
 * }
 * </pre>
 */
public class ClientException extends Exception {
    public ClientException (String message, Exception innerException)
    {
        super(message, innerException);
    }
} // class ClientException
//-------------------------


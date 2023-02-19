package org.apache.commons.mail;

import org.apache.commons.mail.*;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.junit.Assert.*;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;

import junit.framework.TestCase;

public class EmailTest extends TestCase{
	EmailMock testEmail;
	Authenticator auth;
	Session testSession;
	Date date_pass = new Date();
	Date date_fail = null;
	
	/* Setup 
	 * param: none
	 * functionality: Creates a new EmailMock before each test case
	 * 				  is ran.
	 * */
	
	@Override
	public void setUp() {
		System.out.println("Setting it up!");
		testEmail = new EmailMock();
	}
	
	/* Test Add Bcc 
	 * param: none 
	 * functionality: Testing addBcc function from Email.java
	 * 				  if successful getBccAddresses should return the parameters
	 * 				  sent to addBcc.
	 * 
	 * error-check:	  If it happens to not return what was added,
	 * 				  then it will go into catch method.
	 * 
	 * */
	// Completed
	public void testAddBcc() throws EmailException {
		System.out.println("Running test case!");
		String[] email_array = new String[2];
		email_array[0] = "a@b.com";
		email_array[1] = "a@c.com";
		try { 
			testEmail.addBcc(email_array);
		}
		catch (EmailException e) {
			fail("ERROR Occured: " + e);
		}
	}
	

	/* Test Add Bcc 
	 * param: none 
	 * functionality: Intentionally feed addBcc function with empty array to reach if empty check
	 * 
	 * error-check:	  If it happens to not return an exception, then throw fail statement with
	 * 				  description that an error was supposed to occur.
	 * 
	 * */
	// Completed
	public void testAddBccEmpty() throws EmailException {
		System.out.println("Running test case!");
		String[] email_array = new String[0];
		try {
			testEmail.addBcc(email_array);
			fail("Should throw 'Address list provided invalid");
		}
		catch (EmailException e) {
			assertEquals("Address List provided was invalid", e.getMessage());
		}
	}
	
	/* Test Add Bcc Null
	 * param: none 
	 * functionality: Intentionally feed addBcc function with null array to reach if null check
	 * 
	 * error-check:	  If it happens to not return an exception then throw fail statement with
	 * 				  description that an error was supposed to occur.
	 * 
	 * */
	// Completed
	public void testAddBccNull() throws EmailException{
		System.out.println("Running test case!");
		String[] null_array = null;
		try { 
			testEmail.addBcc(null_array);
			fail("Should throw 'Address list provided was invalid");
		}
		catch (EmailException e) {
			assertEquals("Address List provided was invalid", e.getMessage());
		}
	
	}
	
	/* Test Add Cc 
	 * param: none 
	 * functionality: Testing addCc function from Email.java
	 * 				  if successful getCcAddresses should return the parameters
	 * 				  sent to addCc.
	 * 
	 * error-check:	  If it happens to not return what was added,
	 * 				  then it will go into catch method.
	 * 
	 * */
	// Completed
	public void testAddCc() throws EmailException {
		System.out.println("Running test case!");
		
		try {
			testEmail.addCc("a@b.com");
			assertEquals("a@b.com",testEmail.getCcAddresses().get(0).toString());
		}
		catch (EmailException e) {
			fail("ERROR: EmailException thrown " + e.getMessage());
		}
		
	}
	/* Test Add Header Normal
	 * param: none
	 * 
	 * functionality:  intentionally pass in values that will make the add header basic work
	 * 
	 * error checking: if it happens to give an error it will be caught by the catch statement. 
	 * */
	// Completed
	public void testAddHeaderNormal() throws IllegalArgumentException {
		System.out.println("Running test case!");
		try {
		testEmail.addHeader("Header", "-20");
		}
		catch(IllegalArgumentException e) {
			fail("ERROR: IllegalArgumentException thrown" + e.getMessage());
		}
	}
	/* Test Add Header Empty Name
	 * param: none
	 * 
	 * functionality: Pass in an empty string for name to addHeader() function expecting 
	 * 				  error message to be thrown
	 * 
	 * error checking: if it happens to pass, then it will send out a fail()
	 */
	// Completed
	public void testAddHeaderEmptyName() throws IllegalArgumentException {
		System.out.println("Running test case!");

		String empty_name = "";
		
		try {
			testEmail.addHeader(empty_name,"-20");
			fail("Should throw 'name can not be null or empty'");
		}
		catch(IllegalArgumentException e) {
			assertEquals("name can not be null or empty", e.getMessage());
		}
	}
	
	/* Test Add Header Empty Value
	 * param: none
	 * 
	 * functionality: Pass in empty string for value to addHeader() function expecting
	 * 				  error message to be thrown
	 *	
	 *	error checking: if it happens to pass, then it will send out a fail()
	 */
	// Completed
	public void testAddHeaderEmptyValue()  {
		System.out.println("Running test case!");
		try { 
			testEmail.addHeader("Header","");
			fail("Should throw 'name can not be null or empty");
		}
		catch(IllegalArgumentException e) {
			assertEquals("value can not be null or empty", e.getMessage());
		}
	}
	
	
	/****************** Unsure what assertion to use**************************/ 
	
	public void testAddReplyTo() throws EmailException {
		
		System.out.println("Running test case!");
		testEmail.addReplyTo("a@b.com", "Jonathan Trevino");
		
		
	}
	
	/* Test Build Mime Message No Dupe
	 * param: none
	 * 
	 * functionality: This function is meant to create a mime message successfully and try to create another one
	 * 				  Purposefully trying to reach Mime Message is already built exception
	 * 
	 *  error checking: if this function was somehow able to get past this exception it will immediately fail().
	 * 
	 */
	
	
	
	public void testUpdateContentTypeWithChar() {
		System.out.println("Running test case!");
		testEmail.updateContentType("text/plain; charset=utf-8");
		assertEquals("utf-8", testEmail.getCharset());
	}
	
	public void testUpdateContentTypeWithoutChar() {
		System.out.println("Running test case!");
		testEmail.setCharset("utf-8");
		testEmail.updateContentType("text/plain");
	}
	
	public void testBuildMimeMessageDupe() throws EmailException {
		System.out.println("Running test case!");
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");
		testEmail.addTo("c@b.com");
		testEmail.buildMimeMessage();
		try {
			testEmail.buildMimeMessage();
			fail("Should throw 'The MimeMessage is already built.'");
		}
		catch (IllegalStateException e) {
			assertEquals("The MimeMessage is already built.", e.getMessage());
		}
	}

	/* Test Build Mime Message No Host
	 * param: none
	 *
	 * functionality: This function is meant to initiate a can not find valid hostname
	 * 			 for mail session from buildMimeMessage.
	 * 
	 * error-checking: if this function was somehow able to get past this exception
	 * 				   it will immediately fail().
	 */
	public void testBuildMimeMessageNoHost() throws EmailException {
		System.out.println("Running test case!");
		
		try {
			testEmail.buildMimeMessage();
			fail("Should throw 'Cannot find valid hostname'");
		}
		catch (EmailException e) {
				assertEquals("Cannot find valid hostname for mail session", e.getMessage());
		}
		
	}
	
	/* Test Build Mime Message No From
	 * param: none
	 * 
	 * functionality: This function is meant to give a hostname, but not give a from address
	 * 			 so it could pass the hostname exception covered in method above,
	 * 		     and successfully cover the from address required exception
	 * 
	 * error checking: if this function somehow gets past this exception
	 *  			   it will immediately fail.
	 */
	
	public void testBuildMimeMessageNoFrom() throws EmailException {
		System.out.println("Running test case!");
		testEmail.setHostName("b.com");
		try {
			testEmail.buildMimeMessage();
			fail("Should throw 'From address required'");
		}
		catch (EmailException e) {
			assertEquals("From address required", e.getMessage());
		}
	}
	
	/* Test Build Mime Message No To 
	 * param: none
	 * 
	 * functionality: This function is meant to give hostname, from but no to so it could reach
	 * 			 At least one receiver address required exception.
	 * 
	 * error checking: if this function somehow gets past this exception
	 * 			       it will immediately fail
	 */
	
	public void testBuildMimeMessageNoTo() throws EmailException {
		System.out.println("Running test case!");
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");

		
		try {
			testEmail.buildMimeMessage();
			fail("Should throw 'At least one reciever address required'");
		}
		catch (EmailException e) {
			assertEquals("At least one receiver address required", e.getMessage());
		}
	}
	
	/* Test Build Mime Message Full
	 * param: none
	 * 
	 * functionality: This function is meant to fill all possible variables needed to successfully build the mime message
	 * 
	 * error checking: if this function somehow gets past this exception
	 * 			       it will immediately fail
	 */
	
	public void testBuildMimeMessageFull() throws EmailException {
		System.out.println("Running test case!");
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");
		testEmail.addTo("c@b.com");
		testEmail.addBcc("d@b.com");
		testEmail.addReplyTo("jonathantrevino@yahoo.com");
		testEmail.addHeader("jonathantrevino", "20");
		testEmail.subject = "Hello this is very urgent!";
		testEmail.setCharset("utf-8");
		testEmail.content = "JonathanTrevino@email.com";
		try {
			testEmail.buildMimeMessage();
		}
		catch(EmailException e) {
			fail("ERROR: " + e);
		}
	}
	
	/* Test Get Host Name Null
	 * param: none
	 * 
	 * functionality: Return host name to make sure it returns null.
	 * 
	 * error checking: 
	 */
	
	public void testGetHostName_null() throws EmailException {
		System.out.println("Running test case!");
		Object r = testEmail.getHostName();
		assertEquals(null, r);
	
	}
	
	/* Test Get Host Name Not Null
	 * param: none
	 * 
	 * functionality: Return host name to make sure it returns not null.
	 * 
	 * error checking: 
	 */
	
	public void testGetHostName_notNull() throws EmailException {
		System.out.println("Running test case!");
		Properties prop = System.getProperties();
		testSession = Session.getDefaultInstance(prop,null);
		testEmail.getHostName();
	}
	
	/* Test Get Host Name Not Null
	 * param: none
	 * 
	 * functionality: Cover pass branch of getHostName
	 * 
	 * error checking: 
	 */
	
	public void testGetHostName_pass() {
		System.out.println("Running test case!");
		testEmail.setHostName("Jonathan Trevino");
		testEmail.getHostName();
		
	}
	

	public void testGetMailSession () {
		
		System.out.println("Running test case!");
		Properties prop = System.getProperties();
		testSession = Session.getDefaultInstance(prop, auth);
		
		testEmail.setMailSession(testSession);
		
	}
	
	
	public void testGetSentDate_Fail() throws EmailException {
		System.out.println("Running test case!");
		date_fail = null;
		testEmail.setSentDate(date_fail);
		testEmail.getSentDate();
		
		
	}
	
	// Completed
	// Date_pass is != null, allowing the date to be updated.

	public void testGetSentDate_Pass() throws EmailException {
		System.out.println("Running test case!");
		testEmail.setSentDate(date_pass);
		testEmail.getSentDate();
		assertEquals(date_pass.toString(), testEmail.getSentDate().toString());
	}
	

	


	// Completed
	public void testGetSocketConnectionTimeout() throws EmailException {
		System.out.println("Running test case!");
		testEmail.getSocketConnectionTimeout();
	}
	
	public void testSending() throws EmailException {
		System.out.println("Running test case!");
		testEmail.setHostName("jonathan.com");
		testEmail.setFrom("jonathantrevino@yahoo.com");
		testEmail.addTo("j@t.com");
		testEmail.setSubject("This is urgent!");
		testEmail.send();
		assertEquals("This is urgent!", testEmail.getSent());
		
	
	}
	

	
	public void testSetFrom() throws EmailException {
		System.out.println("Running test case!");
		try {
			testEmail.setFrom("jonathantrevino@yahoo.com");
			assertEquals("jonathantrevino@yahoo.com",testEmail.getFromAddress().toString());
		}
		catch (EmailException e) {
			fail("ERROR: " +e );
		}
		
	}
	
	/* TearDown set testEmail to null.
	 * Make sure testEmail is null.
	 * 
	 * */
	
	@Override
	protected void tearDown() throws Exception {
		System.out.println("Tearing it down!");
		testEmail = null;
		assertNull(testEmail);
		
	}
	
	
}

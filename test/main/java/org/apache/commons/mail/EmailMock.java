package org.apache.commons.mail;

public class EmailMock extends SimpleEmail{
	private String sent;
	public String getSent() {
		return sent;
		
	}
	
	public String sendMimeMessage() throws EmailException {
		EmailUtils.notNull(this.message, "Mime Message has not been created yet");
		try {
			this.sent = this.message.getSubject();
			return this.message.getMessageID();
		}
		catch (Throwable t) {
			String error = "Sending the email to the following server failed: " + this.getHostName()
            + ":"
            + this.getSmtpPort();
			throw new EmailException(error,t);
		}
	}
		public String getCharset() {
			return charset;
		}

}

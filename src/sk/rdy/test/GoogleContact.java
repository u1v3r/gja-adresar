package sk.rdy.test;

import java.util.List;

import com.google.gdata.data.contacts.Birthday;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.Im;
import com.google.gdata.data.extensions.PhoneNumber;

public class GoogleContact {
	
	public String namePrefix;
	public String firstName;
	public String lastName;	
	public String nameSuffix;
	public List<PhoneNumber> phoneNumbers;
	public List<Email> emailAddresses;
	public List<Im> ims;
	public Birthday birthDay;
	public String photoHref;
	
}

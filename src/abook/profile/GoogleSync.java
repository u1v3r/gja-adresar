package abook.profile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;

import com.google.gdata.client.Query;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.ContactGroupEntry;
import com.google.gdata.data.contacts.ContactGroupFeed;
import com.google.gdata.data.contacts.GroupMembershipInfo;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.Im;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gdata.data.extensions.PostalAddress;
import com.google.gdata.data.extensions.StructuredPostalAddress;
import com.google.gdata.util.ServiceException;
import com.jtattoo.plaf.BaseTabbedPaneUI.MyTabComponentListener;

/**
 * Import contacts from google account
 * 
 * @author Radovan Dvorsky
 *
 */
public class GoogleSync {
	
	private static final String GOOGLE_SUFFIX = "@gmail.com";
	private static final String APP_NAME = "gja-adresar";
	private static final String REL_HOME_VALUE = "home";
	private static final String REL_WORK_VALUE = "work";
	private static final String REL_MOBILE = "mobile";
	private static final String REL_GOOGLE_TALK = "GOOGLE_TALK";
	private static final String REL_SKYPE = "SKYPE";
	private static final String REL_ICQ = "ICQ";
	private static final String ANDROID_FAVORITE="Starred in Android";

	private String login;
	private String pwd;
	private URL feedUrl;
	private AbProfile profile;
	private URL groupFeedUrl;
	
	public GoogleSync(String login, String pwd){
		
		this.login = login;
		this.pwd = pwd;
		
		this.profile = InitProfile.getProfile();		
					
		try {
			
			if(!GenericValidator.isEmail(login)){
				this.login = this.login + GOOGLE_SUFFIX;
			}
						
			this.feedUrl = new URL(
					"https://www.google.com/m8/feeds/contacts/" + this.login + "/full");
			this.groupFeedUrl = new URL(
					"https://www.google.com/m8/feeds/groups/" + this.login + "/full");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * Fetch all contacts from google account
	 * 
	 * @return List<AbPerson>
	 */
	public List<AbPerson> fetchContacts(){		
		
		List<AbPerson> persons = new ArrayList<AbPerson>();
		
		try {
			
			ContactsService myService = new ContactsService(APP_NAME);
			myService.setUserCredentials(this.login, this.pwd);
			Query myQuery = new Query(feedUrl);
			myQuery.setMaxResults(999);
			
			// skupiny
			ContactGroupFeed groupFeed = myService.getFeed(groupFeedUrl, ContactGroupFeed.class);					
			
			profile.addGroup(ANDROID_FAVORITE);
			
			for (ContactGroupEntry groupEntry : groupFeed.getEntries()) {
				if(groupEntry.hasSystemGroup()){
					String groupName;
					try {
						groupName = parseGroupName(groupEntry.getTitle().getPlainText());
						profile.addGroup(groupName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
			}			
			
			// kontakty
			ContactFeed contactFeed = myService.getFeed(myQuery , ContactFeed.class);
			
			for(ContactEntry entry : contactFeed.getEntries()){
				
				// kontakty s prazdnym menom nepridavaj
				if(!entry.hasName()) continue;
				
				AbPerson person = new AbPerson();
				
				// meno
				if(entry.hasName()){
					Name name = entry.getName();
										
					if(name.hasNamePrefix()){
						person.setNamePrefix(name.getNamePrefix().getValue());
					}
					if(name.hasNameSuffix()){
						person.setNameSuffix(name.getNameSuffix().getValue());
					}
					if(name.hasGivenName()){
						person.setFirstName(name.getGivenName().getValue());
					}
					if(name.hasFamilyName()){
						person.setLastName(name.getFamilyName().getValue());
					}					
				}
				
				//email
				if(entry.hasEmailAddresses()){
					for (Email email : entry.getEmailAddresses()) {
						if(parseRel(email.getRel()).equals(REL_HOME_VALUE)){
							person.setEmailHome(email.getAddress());
						}
						
						if(parseRel(email.getRel()).equals(REL_WORK_VALUE)){
							person.setEmailWork(email.getAddress());
						}						
					}
				}
				
				//phone
				if(entry.hasPhoneNumbers()){
					for(PhoneNumber number : entry.getPhoneNumbers()){
						
						if(parseRel(number.getRel()).equals(REL_HOME_VALUE)){
							person.setPhoneHome(number.getPhoneNumber());
						}else if(parseRel(number.getRel()).equals(REL_WORK_VALUE)){
							person.setPhoneWork(number.getPhoneNumber());
						}else if(parseRel(number.getRel()).equals(REL_MOBILE)){
							person.setCellPhone(number.getPhoneNumber());
						}
					}
				}
				
				//im
				if(entry.hasImAddresses()){
					for (Im im : entry.getImAddresses()) {
						if(parseRel(im.getRel()).equals(REL_GOOGLE_TALK)){
							person.setGtalk(im.getAddress());
						}else if(parseRel(im.getRel()).equals(REL_SKYPE)){
							person.setSkype(im.getAddress());
						}else if(parseRel(im.getRel()).equals(REL_ICQ)){
							person.setIcq(im.getAddress());
						}else {
							person.setJabber(im.getAddress());
						}
					}					
				}
				
				//narodeniny
				if(entry.hasBirthday()){
					
					try {						
						SimpleDateFormat format = new SimpleDateFormat(AbPerson.DATE_FORMAT);					
						person.setBirthday(format.parse(entry.getBirthday().getWhen()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//adresa
				if(entry.hasStructuredPostalAddresses()){
					for (StructuredPostalAddress address : entry.getStructuredPostalAddresses()) {
						if(address.hasCity()){
							person.setCity(address.getCity().getValue());
						}
						
						if(address.hasCountry()){
							person.setCountry(address.getCountry().getValue());
						}
						
						if(address.hasStreet()){
							person.setStreet(address.getStreet().getValue());
						}
						
						if(address.hasPostcode()){
							person.setPsc(address.getPostcode().getValue());
						}
					}
				}
								
				//priradenie do skupin
				if(entry.hasGroupMembershipInfos()){
					for (GroupMembershipInfo group : entry.getGroupMembershipInfos()) {
						try {
							/*
							 * TODO treba vymysliet nieco rychlejsie
							 */
							ContactGroupEntry groupEntry = myService.getEntry(new URL(group.getHref()), ContactGroupEntry.class);
							person.addGroup(parseGroupName(groupEntry.getTitle().getPlainText()));
						} catch (Exception e) {
							e.printStackTrace();
						}
												
					}
				}			
				persons.add(person);
			}
						
		
			InitListenerCore.getListenerCore().fireListeners(
					new AbEvent(this), AbListener.GROUPS_CHANGED);
			InitListenerCore.getListenerCore().fireListeners(new AbEvent(this), AbListener.PROFILE_CHANGED);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return persons;		
	}

	/**
	 * Parse google string and return group name
	 * 
	 * @param plainText
	 * @return String group name
	 * @throws Exception 
	 */
	private String parseGroupName(String plainText) throws Exception {		
		
		if(plainText == null) throw new Exception("NULL plainText");
				
		if(plainText.contains(":")){
			return (String) plainText.subSequence(plainText.indexOf(":") + 2, plainText.length());
		}
		else{
			return ANDROID_FAVORITE;
		}		
	}
	
	/**
	 * Parse google rel and return value of rel
	 * 
	 * @param rel
	 * @return String value
	 */
	private String parseRel(String rel){
		
		if(rel == null) return "";
		
		if(rel.contains("#")){
			return (String) rel.subSequence(rel.indexOf("#") + 1, rel.length());
		}
		
		return "";
	}

}

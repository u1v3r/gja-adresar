package abook.profile;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Radovan Dvorsky
 *
 */
public class AbPerson {	

	protected int id;
	protected String namePrefix;
	protected String firstName;
	protected String lastName;
	protected String nameSuffix;
	protected String street;
	protected String city;
	protected String psc;
	protected String country;
	protected String phoneWork;
	protected String phoneHome;
	protected String cellPhone;
	protected String emailWork;
	protected String emailHome;
	protected String skype;
	protected String icq;
	protected String jabber;
	protected String gtalk;	
	protected Image userImage;
	protected String note;
	protected Date birthday;
	protected List<String> listOfGroups;
	
	private static int counter = 1;
	
	
	public AbPerson(String firstName, String lastName) {
		this.id = counter;
		this.firstName = firstName;
		this.lastName = lastName;
		this.listOfGroups = new ArrayList<String>();		
		counter++;
	}
	
	public AbPerson(String namePrefix, String firstName, String lastName){
		this(firstName,lastName);
		this.namePrefix = namePrefix;		
	}
	
	public AbPerson(String namePrefix, String firstName, String lastName, String nameSuffix){
		this(namePrefix,firstName,lastName);
		this.nameSuffix = nameSuffix;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNameSuffix() {
		return nameSuffix;
	}

	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPsc() {
		return psc;
	}

	public void setPsc(String psc) {
		this.psc = psc;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneWork() {
		return phoneWork;
	}

	public void setPhoneWork(String phoneWork) {
		this.phoneWork = phoneWork;
	}

	public String getPhoneHome() {
		return phoneHome;
	}

	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmailWork() {
		return emailWork;
	}

	public void setEmailWork(String emailWork) {
		this.emailWork = emailWork;
	}

	public String getEmailHome() {
		return emailHome;
	}

	public void setEmailHome(String emailHome) {
		this.emailHome = emailHome;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getIcq() {
		return icq;
	}

	public void setIcq(String icq) {
		this.icq = icq;
	}

	public String getJabber() {
		return jabber;
	}

	public void setJabber(String jabber) {
		this.jabber = jabber;
	}

	public String getGtalk() {
		return gtalk;
	}

	public void setGtalk(String gtalk) {
		this.gtalk = gtalk;
	}

	public Image getUserImage() {
		return userImage;
	}

	public void setUserImage(Image userImage) {
		this.userImage = userImage;
	}
	
	public String getNote(){
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;		
	}
	
	public Date getBirthday(){
		return this.birthday;
	}

	public void setBirthday(Date date) {
		this.birthday = date;
	}
	
	public void addGroup(String group) {
		if(!InitProfile.getProfile().containsGroup(group)) {
			InitProfile.getProfile().addGroup(group);
		}
		this.listOfGroups.add(group);
	}
	
	public void removeGroup(String group) {
		if(listOfGroups.contains(group)) {
			this.listOfGroups.remove(group);
		}
	}
	
	public List<String> getListOfGroups() {
		return this.listOfGroups;
	}

	@Override
	public String toString() {
		return "AbPerson [id=" + id + ", namePrefix=" + namePrefix
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", nameSuffix=" + nameSuffix + ", street=" + street
				+ ", city=" + city + ", psc=" + psc + ", country=" + country
				+ ", phoneWork=" + phoneWork + ", phoneHome=" + phoneHome
				+ ", cellPhone=" + cellPhone + ", emailWork=" + emailWork
				+ ", emailHome=" + emailHome + ", skype=" + skype + ", icq="
				+ icq + ", jabber=" + jabber + ", gtalk=" + gtalk
				+ ", userImage=" + userImage + ", note=" + note + ", birthday="
				+ birthday + ", listOfGroups=" + listOfGroups + "]";
	}

	public String getFullname() {
		return this.firstName + " " 
				+ this.lastName;
	}	
}
package abook.profile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Contact attributes.
 * 
 * @author Radovan Dvorsky
 *
 */
public class AbPerson implements Comparable<AbPerson>{	

	public static final String DATE_FORMAT = "d.M.yyyy";

	private static final String DEFAULT_ICON = "/icons/default_user_icon.jpg";
				
	protected int id;
	protected String namePrefix = "";
	protected String firstName = "";
	protected String lastName = "";
	protected String nameSuffix = "";
	protected String street = "";
	protected String city = "";
	protected String psc = "";
	protected String country = "";
	protected String phoneWork = "";
	protected String phoneHome = "";
	protected String cellPhone = "";
	protected String emailWork = "";
	protected String emailHome = "";
	protected String skype = "";
	protected String icq = "";
	protected String jabber = "";
	protected String gtalk = "";	
	protected String note = "";
	protected Date birthday;
	protected List<String> listOfGroups;	
	protected Boolean photo = false;
	
	private static int counter = 1;
	
	/**
	 * Creates new contact.
	 */
	public AbPerson(){
		this.id = counter;
		counter++;
		this.listOfGroups = new ArrayList<String>();
		this.addGroup("all");
	}
	
	/**
	 * Creates new contact
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public AbPerson(String firstName, String lastName) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * Creates new contact.
	 * 
	 * @param namePrefix
	 * @param firstName
	 * @param lastName
	 */
	public AbPerson(String namePrefix, String firstName, String lastName){
		this(firstName,lastName);
		this.namePrefix = namePrefix;		
	}
	
	/**
	 * Creates new contact.
	 * 
	 * @param namePrefix
	 * @param firstName
	 * @param lastName
	 * @param nameSuffix
	 */
	public AbPerson(String namePrefix, String firstName, String lastName, String nameSuffix){
		this(namePrefix,firstName,lastName);
		this.nameSuffix = nameSuffix;
	}
	
	/**
	 * Returns id of contact.
	 * 
	 * @return id
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * Returns name prefix.
	 * 
	 * @return namePrefix
	 */
	public String getNamePrefix() {
		return namePrefix;
	}

	/**
	 * Sets name prefix.
	 * 
	 * @param namePrefix
	 */
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	/**
	 * Returns first name.
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets first name.
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns last name.
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets last name.
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns name suffix.
	 * 
	 * @return nameSuffix
	 */
	public String getNameSuffix() {
		return nameSuffix;
	}

	/**
	 * Sets name suffix.
	 * 
	 * @param nameSuffix
	 */
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	/**
	 * Returns street.
	 * 
	 * @return street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets street.
	 * 
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Returns city.
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets city.
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Returns zip code.
	 * 
	 * @return zip code
	 */
	public String getZipCode() {
		return psc;
	}

	/**
	 * Sets PSC.
	 * 
	 * @param psc
	 */
	public void setPsc(String psc) {
		this.psc = psc;
	}

	/**
	 * Returns country.
	 * 
	 * @return country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets country.
	 * 
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Returns phone number to work.
	 * 
	 * @return phoneWork
	 */
	public String getPhoneWork() {
		return phoneWork;
	}

	/**
	 * Sets phone number to work.
	 * 
	 * @param phoneWork
	 */
	public void setPhoneWork(String phoneWork) {
		this.phoneWork = phoneWork;
	}

	/**
	 * Returns phone number to home.
	 * 
	 * @return phoneHome
	 */
	public String getPhoneHome() {
		return phoneHome;
	}

	/**
	 * Sets phone number to home.
	 * 
	 * @param phoneHome
	 */
	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}

	/**
	 * Returns cell phone number.
	 * 
	 * @return cellPhone
	 */
	public String getCellPhone() {
		return cellPhone;
	}

	/**
	 * Sets cell phone number.
	 * 
	 * @param cellPhone
	 */
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	/**
	 * Returns email to work.
	 * 
	 * @return emailWork
	 */
	public String getEmailWork() {
		return emailWork;
	}

	/**
	 * Sets email to work.
	 * 
	 * @param emailWork
	 */
	public void setEmailWork(String emailWork) {
		this.emailWork = emailWork;
	}

	/**
	 * Returns email to home.
	 * 
	 * @return emailHome
	 */
	public String getEmailHome() {
		return emailHome;
	}

	/**
	 * Sets email to home.
	 * 
	 * @param emailHome
	 */
	public void setEmailHome(String emailHome) {
		this.emailHome = emailHome;
	}

	/**
	 * Returns skype ID.
	 * 
	 * @return skype
	 */
	public String getSkype() {
		return skype;
	}

	/**
	 * Sets skype ID.
	 * 
	 * @param skype
	 */
	public void setSkype(String skype) {
		this.skype = skype;
	}

	/**
	 * Returns ICQ number.
	 * 
	 * @return icq
	 */
	public String getIcq() {
		return icq;
	}

	/**
	 * Sets ICQ number.
	 * 
	 * @param icq
	 */
	public void setIcq(String icq) {
		this.icq = icq;
	}

	/**
	 * Returns jabber ID.
	 * 
	 * @return jabber
	 */
	public String getJabber() {
		return jabber;
	}

	/**
	 * Sets jabber ID.
	 * 
	 * @param jabber
	 */
	public void setJabber(String jabber) {
		this.jabber = jabber;
	}

	/**
	 * Returns GTalkt ID.
	 * 
	 * @return gtalk
	 */
	public String getGtalk() {
		return gtalk;
	}

	/**
	 * Sets GTalk ID.
	 * 
	 * @param gtalk
	 */
	public void setGtalk(String gtalk) {
		this.gtalk = gtalk;
	}

	/**
	 * Returns user image.
	 * 
	 * @return userImage
	 */
	public Image getUserImage() {
		
		File img = new File(InitProfile.getUserFileDir().getPath() 
				+ File.separator + this.id);		
		Image image = null;
		if(hasPhoto()){
			try {
				image =  ImageIO.read(img);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{			
			image = (new ImageIcon(this.getClass().getResource(DEFAULT_ICON))).getImage();			
		}
		
		return image;
	}
	
	/**
	 * Saves user image
	 * 
	 * @param image
	 */
	public void setUserImage(Image image){
		try {
			
			BufferedImage bi = new BufferedImage(
					image.getWidth(null), 
					image.getHeight(null), 
					BufferedImage.TYPE_INT_RGB);
			
			 Graphics2D g2 = bi.createGraphics();
		        g2.drawImage(image, 0, 0, null);
		        g2.dispose();
			
			File saveDir = new File(InitProfile.getUserFileDir().getPath());
			
			if(!saveDir.exists()){
				if(saveDir.mkdir()){
					System.out.println("created directory: " + saveDir.getPath());
				}else{
					System.err.println("failed to create directory: " + saveDir.getPath());
				}
			}
			
			File saveFile = new File(InitProfile.getUserFileDir().getPath() + File.separator + this.id);		
			ImageIO.write(bi, "PNG", saveFile);
			
			this.photo = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns note.
	 * 
	 * @return note
	 */
	public String getNote(){
		return this.note;
	}

	/**
	 * Sets note.
	 * 
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;		
	}
	
	/**
	 * Returns birthday.
	 * 
	 * @return birthday
	 */
	public Date getBirthday(){
		return this.birthday;
	}

	/**
	 * Sets birthday.
	 * 
	 * @param date
	 */
	public void setBirthday(Date date) {
		this.birthday = date;
	}
	
	/**
	 * Joins to group.
	 * 
	 * @param group
	 */
	public void addGroup(String group) {
		if(!InitProfile.getProfile().containsGroup(group)) {
			InitProfile.getProfile().addGroup(group);
		}
		this.listOfGroups.add(group);
	}
	
	/**
	 * Removes membership of group.
	 * 
	 * @param group
	 */
	public void removeGroup(String group) {
		
		int i = 0;
		for(String groupName : listOfGroups) {
			if(group.equals(groupName)) {
				break;
			}
			i++;
		}
		
		if(i < listOfGroups.size()) {
			listOfGroups.remove(i);
		}
	}
	
	/**
	 * Returns list of groups.
	 * 
	 * @return listOfGroups
	 */
	public List<String> getListOfGroups() {
		return this.listOfGroups;
	}
	
	/**
	 * Returns full name with space separator.
	 * 
	 * @return fullName
	 */
	public String getFullname() {
		return this.firstName + " " 
				+ this.lastName;
	}
	
	/**
	 * Returns true if user has photo.
	 * 
	 * @return boolean
	 */
	public boolean hasPhoto(){		
		return this.photo;		
	}
	
	/**
	 * Sets boolean value for photo.
	 * 
	 * @param photo boolean
	 */
	public void setHasPhoto(boolean photo){
		this.photo = photo;
	}

	@Override
	public int compareTo(AbPerson o) {
		return this.lastName.compareTo(o.lastName);
	}	
}
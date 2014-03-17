/*
     * [Michael Wolfe]
     * [mjw323@drexel.edu]
     * CS338:GUI, Final Project
     */


	 //Data Structure which holds network device information throughout all of the Main GUI functionality
	 //Includes getters and setters for all strings involved in a Network Object.
public class Device  {

	private String Type;
	private String OS;
	private String Name;
	private String IP;
	private String Network;
	private Device ConnectedTo;
	private Device ConnectedFrom;
	private String DevicePicture;
	private String DeviceIcon;
	
	public Device(){
		setType("N/A");
		setName("N/A");
		setIP("N/A");
		setNetwork("N/A");
		setConnectedTo(null);
		setConnectedFrom(null);
		setPicture("N/A");
		setIcon("N/A");
		
	}
	
	public Device(String deviceOS, String deviceType, String deviceName, String deviceIP, String deviceNetwork, String devicePicture){
		setType(deviceType);
		setName(deviceName);
		setIP(deviceIP);
		setNetwork(deviceNetwork);
		setPicture(devicePicture);
		setOS(deviceOS);
		
		if (deviceOS == "Computer"){
			setIcon("icons/Computer.png");
		}
		else if (deviceOS == "Server"){
			setIcon("icons/Server.png");
		}
		else if (deviceOS == "Router"){
			setIcon("icons/Router.png");
		}
		else if (deviceOS == "Switch"){
			setIcon("icons/Switch.png");
		}
		else{
			setIcon("N/A");
		}
		
	}
	
	public Device(String deviceOS, String deviceType, String deviceName, String deviceIP, String deviceNetwork, Device deviceConnectedTo, Device deviceConnectedFrom, String devicePicture){
		
		setType(deviceType);
		setName(deviceName);
		setIP(deviceIP);
		setNetwork(deviceNetwork);
		setConnectedTo(deviceConnectedTo);
		setConnectedFrom(deviceConnectedFrom);
		setPicture(devicePicture);
		setOS(deviceOS);
		
		if (OS == "Computer"){
			setIcon("icons/Computer.png");
		}
		else if (OS == "Server"){
			setIcon("icons/Server.png");
		}
		else if (OS == "Router"){
			setIcon("icons/Router.png");
		}
		else if (OS == "Bridge"){
			setIcon("icons/Switch.png");
		}
		else{
			setIcon("N/A");
		}
	}

	private void setType(String string) {
		this.Type = string;
	}
	
	private void setOS(String string) {
		this.OS = string;
	}
	
	private void setName(String string) {
		this.Name = string;
	}
	
	private void setIP(String string) {
		this.IP = string;
	}
	
	private void setNetwork(String string) {
		this.Network = string;
	}
	
	private void setConnectedTo(Device device ) {
		this.ConnectedTo = device;
	}
	
	private void setConnectedFrom(Device device ) {
		this.ConnectedFrom = device;
	}
	
	private void setPicture(String string) {
		this.DevicePicture = string;
	}
	
	private void setIcon(String string) {
		this.DeviceIcon = string;
	}
	
	public String getType(){
		return Type;
	}
	
	public String getOS(){
		return OS;
	}
	
	public String getIP(){
		return IP;
	}
	
	public String getName(){
		return Name;
	}
	
	public String getNetwork(){
		return Network;
	}
	
	public Device getDeviceConnectedTo(){
		return ConnectedTo;
	}
	
	public Device getDeviceConnectedFrom(){
		return ConnectedFrom;
	}
	
	public String getPicture(){
		return DevicePicture;
	}
	
	public String getIcon(){
		return DeviceIcon;
	}
}

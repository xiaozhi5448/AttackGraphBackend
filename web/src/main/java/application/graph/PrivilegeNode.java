package application.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@AllArgsConstructor
public class PrivilegeNode {
    Integer id;
    String deviceName;
    ArrayList<String> hostNames;
    String serviceName;
    String port;
    String privilege;

    public PrivilegeNode(){
        this.hostNames = new ArrayList<>();
    }
    public int generateId(){
        this.id = this.toString().hashCode();
        return this.id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer privilegeId) {
        this.id = privilegeId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public ArrayList<String> getHostNames() {
        return hostNames;
    }


    public void addHostName(String name){
        this.hostNames.add(name);
    }


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "PrivilegeNode{" +
                "deviceName='" + deviceName + '\'' +
                ", hostName='" + hostNames.toString() + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", port='" + port + '\'' +
                ", privilege='" + privilege + '\'' +
                '}';
    }
}

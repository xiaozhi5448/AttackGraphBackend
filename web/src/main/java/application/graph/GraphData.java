package application.graph;

import com.loop0day.security.db.bean.*;
import com.loop0day.security.db.dao.*;
import com.loop0day.security.tool.MapTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class GraphData {
    @Autowired
    MapTool mapTool;

    Graph attackGraph;

    public GraphData() {
        this.attackGraph = new Graph();
    }

    AttackPathMapper attackPathMapper = mapTool.session.getMapper(AttackPathMapper.class);
    AttackPathExample attackPathExample = new AttackPathExample();

    AttackModelMapper attackModelMapper = mapTool.session.getMapper(AttackModelMapper.class);
    AttackModelExample attackModelExample = new AttackModelExample();

    DeviceMapper deviceMapper = mapTool.session.getMapper(DeviceMapper.class);
    DeviceExample deviceExample = new DeviceExample();

    DeviceConnectMapper deviceConnectMapper = mapTool.session.getMapper(DeviceConnectMapper.class);
    DeviceConnectExample deviceConnectExample = new DeviceConnectExample();

    DeviceDataflowMapper deviceDataflowMapper = mapTool.session.getMapper(DeviceDataflowMapper.class);
    DeviceDataflowExample deviceDataflowExample = new DeviceDataflowExample();

    DeviceRouteMapper deviceRouteMapper = mapTool.session.getMapper(DeviceRouteMapper.class);
    DeviceRouteExample deviceRouteExample = new DeviceRouteExample();

    DeviceSecurityMapper deviceSecurityMapper = mapTool.session.getMapper(DeviceSecurityMapper.class);
    DeviceSecurityExample deviceSecurityExample = new DeviceSecurityExample();

    DeviceThreatMapper deviceThreatMapper = mapTool.session.getMapper(DeviceThreatMapper.class);
    DeviceThreatExample deviceThreatExample = new DeviceThreatExample();

    SecurityFunctionMapper securityFunctionMapper = mapTool.session.getMapper(SecurityFunctionMapper.class);
    SecurityFunctionExample securityFunctionExample = new SecurityFunctionExample();

    SecurityThreatMapper securityThreatMapper = mapTool.session.getMapper(SecurityThreatMapper.class);
    SecurityThreatExample securityThreatExample = new SecurityThreatExample();

    SecurityVariantMapper securityVariantMapper = mapTool.session.getMapper(SecurityVariantMapper.class);
    SecurityVariantExample securityVariantExample = new SecurityVariantExample();

    ServiceTestcaseMapper serviceTestcaseMapper = mapTool.session.getMapper(ServiceTestcaseMapper.class);
    ServiceTestcaseExample serviceTestcaseExample = new ServiceTestcaseExample();

    SingleTestMapper singleTestMapper = mapTool.session.getMapper(SingleTestMapper.class);
    SingleTestExample singleTestExample = new SingleTestExample();

    UserPrivilegeMapper userPrivilegeMapper = mapTool.session.getMapper(UserPrivilegeMapper.class);
    UserPrivilegeExample userPrivilegeExample = new UserPrivilegeExample();

    DeviceServiceMapper deviceServiceMapper = mapTool.session.getMapper(DeviceServiceMapper.class);
    DeviceServiceExample deviceServiceExample = new DeviceServiceExample();

    DevicePrivilegeMapper devicePrivilegeMapper = mapTool.session.getMapper(DevicePrivilegeMapper.class);
    DevicePrivilegeExample devicePrivilegeExample = new DevicePrivilegeExample();



    DeviceNetworkMapper deviceNetworkMapper = mapTool.session.getMapper(DeviceNetworkMapper.class);
    DeviceNetworkExample deviceNetworkExample = new DeviceNetworkExample();

    Logger logger = LoggerFactory.getLogger(GraphData.class);


    public void calculateTopologyData(String startDeviceName) {
        this.attackGraph.clear();

        if (startDeviceName == null) {
            return;
        } else {
            LinkedList<String> metaNodes = new LinkedList<>();
            metaNodes.addLast(startDeviceName);
        }
    }

    public void calculateGraphData(String startDeviceName) {
        this.attackGraph.clear();
        deviceExample.or().andDeviceNameEqualTo(startDeviceName);
        List<Device> startDevices = deviceMapper.selectByExample(deviceExample);
        deviceExample.clear();
        if (startDevices.size() < 1) {
            logger.error("cannot find a device with name " + startDeviceName);
            return;
        }
        Device startDevice = startDevices.get(0);
        LinkedList<PrivilegeNode> metaNodes = new LinkedList<>();
        PrivilegeNode privilegeNode = new PrivilegeNode();
        privilegeNode.setDeviceName(startDevice.getDeviceName());

        deviceNetworkExample.or().andDeviceIdEqualTo(startDevice.getDeviceId())
                .andAddressNotLike("-");
        List<DeviceNetwork> deviceAddresses = deviceNetworkMapper.selectByExample(deviceNetworkExample);
        deviceNetworkExample.clear();
        for (DeviceNetwork address : deviceAddresses) {
            privilegeNode.addHostName(address.getAddress());
        }
        privilegeNode.setServiceName("");
        privilegeNode.setPrivilege("root");
        privilegeNode.setServiceName("");
        privilegeNode.setPort("");



        privilegeNode.generateId();
        metaNodes.addFirst(privilegeNode);
        this.attackGraph.getNodes().add(privilegeNode);

        while (!metaNodes.isEmpty()) {
            PrivilegeNode srcNode = metaNodes.pollLast();
            if (srcNode != null) {
                for (String srcAddr : srcNode.getHostNames()) {
                    attackPathExample.or().andSrcEqualTo(srcAddr);
                    List<AttackPath> attackPaths = attackPathMapper.selectByExample(attackPathExample);
                    attackPathExample.clear();
                    for (AttackPath loopPath : attackPaths) {
                        PrivilegeNode newNode = new PrivilegeNode();
                        Edge edge = new Edge();
                        deviceNetworkExample.or().andAddressEqualTo(loopPath.getDst());
                        DeviceNetwork dstDeviceNet = deviceNetworkMapper.selectByExample(deviceNetworkExample).get(0);
                        deviceNetworkExample.clear();

                        deviceExample.or().andDeviceIdEqualTo(dstDeviceNet.getDeviceId());
                        Device dstDevice = deviceMapper.selectByExample(deviceExample).get(0);
                        deviceExample.clear();

                        newNode.setDeviceName(dstDevice.getDeviceName());

                        deviceNetworkExample.or().andDeviceIdEqualTo(dstDevice.getDeviceId())
                                .andAddressNotLike("-");
                        List<DeviceNetwork> addresses = deviceNetworkMapper.selectByExample(deviceNetworkExample);
                        deviceNetworkExample.clear();
                        for (DeviceNetwork address : addresses) {
                            newNode.addHostName(address.getAddress());
                        }

                        List<Integer> privilege_ids = new ArrayList<>();
                        String[] splits = loopPath.getPrivilege().split(",");
                        for(String id: splits){
                            privilege_ids.add(Integer.parseInt(id));
                        }
//                        devicePrivilegeExample.or()
//                                .andPrivilegeIdEqualTo(Integer.parseInt(loopPath.getPrivilege()));
                        devicePrivilegeExample.or().andPrivilegeIdIn(privilege_ids);
                        List<DevicePrivilege> newPrivileges = devicePrivilegeMapper.selectByExample(devicePrivilegeExample);
                        devicePrivilegeExample.clear();
                        ArrayList<String> privileges = new ArrayList<>();
                        for (DevicePrivilege privilege : newPrivileges) {
                            privileges.add(privilege.getComment());
                        }
                        newNode.setPrivilege(String.join(" ", privileges));

                        deviceServiceExample.or().andDeviceIdEqualTo(dstDevice.getDeviceId())
                                .andPortEqualTo(loopPath.getDport());
                        DeviceService deviceService = deviceServiceMapper.selectByExample(deviceServiceExample).get(0);
                        deviceServiceExample.clear();
                        newNode.setServiceName(deviceService.getService());
                        newNode.setPort(loopPath.getDport());
                        newNode.generateId();
                        edge.setSrc(srcNode.getId());
                        edge.setDst(newNode.getId());

                        attackModelExample.or().andVulnIdEqualTo(loopPath.getVulnId());
                        AttackModel attackModel = attackModelMapper.selectByExample(attackModelExample).get(0);
                        attackModelExample.clear();
                        edge.setThreatType(attackModel.getVulnName());
                        this.attackGraph.getEdges().add(edge);
//                        this.attackGraph.getNodes().add(newNode);
//                        metaNodes.addFirst(newNode);
                        if (this.attackGraph.findNodeById(newNode.getId()) == null) {
                            this.attackGraph.getNodes().add(newNode);
                            metaNodes.addFirst(newNode);
                        }
                    }

                }
            }

        }
    }

    public Graph getAttackGraph() {
        return attackGraph;
    }
    public void clearAll(){
        attackModelExample.or().andVulnIdIsNotNull();
        attackModelMapper.deleteByExample(attackModelExample);
        attackModelExample.clear();

        attackPathExample.or().andIdIsNotNull();
        attackPathMapper.deleteByExample(attackPathExample);
        attackPathExample.clear();

        deviceExample.or().andDeviceIdIsNotNull();
        deviceMapper.deleteByExample(deviceExample);
        deviceExample.clear();

        deviceConnectExample.or().andDeviceIdIsNotNull();
        deviceConnectMapper.deleteByExample(deviceConnectExample);
        deviceConnectExample.clear();

        deviceDataflowExample.or().andDeviceIdIsNotNull();
        deviceDataflowMapper.deleteByExample(deviceDataflowExample);
        deviceDataflowExample.clear();

        deviceNetworkExample.or().andDeviceIdIsNotNull();
        deviceNetworkMapper.deleteByExample(deviceNetworkExample);
        deviceNetworkExample.clear();

        devicePrivilegeExample.or().andPrivilegeIdIsNotNull();
        devicePrivilegeMapper.deleteByExample(devicePrivilegeExample);
        devicePrivilegeExample.clear();

        deviceRouteExample.or().andDeviceIdIsNotNull();
        deviceRouteMapper.deleteByExample(deviceRouteExample);
        deviceRouteExample.clear();

        deviceSecurityExample.or().andDeviceIdIsNotNull();
        deviceSecurityMapper.deleteByExample(deviceSecurityExample);
        deviceSecurityExample.clear();

        deviceServiceExample.or().andDeviceIdIsNotNull();
        deviceServiceMapper.deleteByExample(deviceServiceExample);
        deviceServiceExample.clear();

        deviceThreatExample.or().andDeviceIdIsNotNull();
        deviceThreatMapper.deleteByExample(deviceThreatExample);
        deviceThreatExample.clear();

        securityFunctionExample.or().andFunctionIdIsNotNull();
        securityFunctionMapper.deleteByExample(securityFunctionExample);
        securityFunctionExample.clear();

        securityThreatExample.or().andFunctionIdIsNotNull();
        securityThreatMapper.deleteByExample(securityThreatExample);
        securityThreatExample.clear();

        securityVariantExample.or().andVariantIdIsNotNull();
        securityVariantMapper.deleteByExample(securityVariantExample);
        securityVariantExample.clear();

        serviceTestcaseExample.or().andIdIsNotNull();
        serviceTestcaseMapper.deleteByExample(serviceTestcaseExample);
        serviceTestcaseExample.clear();

        singleTestExample.or().andDeviceIdIsNotNull();
        singleTestMapper.deleteByExample(singleTestExample);
        singleTestExample.clear();

        userPrivilegeExample.or().andDeviceIdIsNotNull();
        userPrivilegeMapper.deleteByExample(userPrivilegeExample);
        userPrivilegeExample.clear();
        mapTool.commit();
    }
}

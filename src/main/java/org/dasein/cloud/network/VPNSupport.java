/**
 * Copyright (C) 2009-2015 Dell, Inc.
 * See annotations for authorship information
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.dasein.cloud.network;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.Requirement;
import org.dasein.cloud.ResourceStatus;
import org.dasein.cloud.identity.ServiceAction;
import org.dasein.cloud.network.VPNConnection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("UnusedDeclaration")
interface VPNSupport extends AccessControlledService {
    static final ServiceAction ANY                = new ServiceAction("VPN:ANY");

    static final ServiceAction ATTACH             = new ServiceAction("VPN:ATTACH");
    static final ServiceAction CONNECT_GATEWAY    = new ServiceAction("VPN:CONNECT_GATEWAY");
    static final ServiceAction CREATE_GATEWAY     = new ServiceAction("VPN:CREATE_GATEWAY");
    static final ServiceAction CREATE_VPN         = new ServiceAction("VPN:CREATE_VPN");
    static final ServiceAction DETACH             = new ServiceAction("VPN:DETACH");
    static final ServiceAction DISCONNECT_GATEWAY = new ServiceAction("VPN:DISCONNECT_GATEWAY");
    static final ServiceAction GET_GATEWAY        = new ServiceAction("VPN:GET_GATEWAY");
    static final ServiceAction GET_VPN            = new ServiceAction("VPN:GET_VPN");
    static final ServiceAction LIST_GATEWAY       = new ServiceAction("VPN:LIST_GATEWAY");
    static final ServiceAction LIST_VPN           = new ServiceAction("VPN:LIST_VPN");
    static final ServiceAction REMOVE_GATEWAY     = new ServiceAction("VPN:REMOVE_GATEWAY");
    static final ServiceAction REMOVE_VPN         = new ServiceAction("VPN:REMOVE_VPN");

    void attachToVLAN(@Nonnull String providerVpnId, @Nonnull String providerVlanId) throws CloudException, InternalException;

    void connectToGateway(@Nonnull String providerVpnId, @Nonnull String toGatewayId) throws CloudException, InternalException;

    /* connectToVPNGateway
     * @param vpnName the name of the vpn to create gateway off of
     * @param endpoint is the ip address of the peer vpn
     * @param name the name of the gateway being created
     * @param description optional description 
     * @param protocol the protocol of the vpn connection
     * @param sharedSecret of the vpn connection
     * @param cidrs one or more IP addresses in CIDR notation
     * @return the created VPN gateway
     */
    VPNGateway connectToVPNGateway(String vpnName, String endpoint, String name, String description, VPNProtocol protocol, String sharedSecret, String cidr) throws CloudException, InternalException;

    @Deprecated
    @Nonnull VPN createVPN(@Nullable String inProviderDataCenterId, @Nonnull String name, @Nonnull String description, @Nonnull VPNProtocol protocol) throws CloudException, InternalException;

    @Nonnull VPN createVPN(@Nonnull VpnCreateOptions vpnLaunchOptions) throws CloudException, InternalException;

    @Nonnull VPNGateway createVPNGateway(@Nonnull String endpoint, @Nonnull String name, @Nonnull String description, @Nonnull VPNProtocol protocol, @Nonnull String bgpAsn) throws CloudException, InternalException;

    void deleteVPN(@Nonnull String providerVpnId) throws CloudException, InternalException;

    void deleteVPNGateway(@Nonnull String providerVPNGatewayId) throws CloudException, InternalException;

    void detachFromVLAN(@Nonnull String providerVpnId, @Nonnull String providerVlanId) throws CloudException, InternalException;

    void disconnectFromGateway(@Nonnull String providerVpnId, @Nonnull String fromGatewayId) throws CloudException, InternalException;

    @Nonnull VPNCapabilities getCapabilities()throws CloudException, InternalException;

    @Nullable VPNGateway getGateway(@Nonnull String gatewayId) throws CloudException, InternalException;

    @Nullable VPN getVPN(@Nonnull String providerVpnId) throws CloudException, InternalException;

    @Deprecated
    Requirement getVPNDataCenterConstraint() throws CloudException, InternalException;

    @Nonnull Iterable<VPNConnection> listGatewayConnections(@Nonnull String toGatewayId) throws CloudException, InternalException;

    @Nonnull Iterable<ResourceStatus> listGatewayStatus() throws CloudException, InternalException;

    @Nonnull Iterable<VPNGateway> listGateways() throws CloudException, InternalException;

    @Nonnull Iterable<VPNGateway> listGatewaysWithBgpAsn(@Nonnull String bgpAsn) throws CloudException, InternalException;

    @Nonnull Iterable<VPNConnection> listVPNConnections(@Nonnull String toVpnId) throws CloudException, InternalException;

    @Nonnull Iterable<ResourceStatus> listVPNStatus() throws CloudException, InternalException;

    @Nonnull Iterable<VPN> listVPNs() throws CloudException, InternalException;

    @Deprecated
    @Nonnull Iterable<VPNProtocol> listSupportedVPNProtocols() throws CloudException, InternalException;

    boolean isSubscribed() throws CloudException, InternalException;
}

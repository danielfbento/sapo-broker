//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.06.02 at 05:38:19 PM WEST 
//


package pt.com.gcs.conf.global;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pt.com.gcs.conf.global package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pt.com.gcs.conf.global
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Policies.Policy }
     * 
     */
    public Policies.Policy createPoliciesPolicy() {
        return new Policies.Policy();
    }

    /**
     * Create an instance of {@link Agents.Agent }
     * 
     */
    public Agents.Agent createAgentsAgent() {
        return new Agents.Agent();
    }

    /**
     * Create an instance of {@link Policies }
     * 
     */
    public Policies createPolicies() {
        return new Policies();
    }

    /**
     * Create an instance of {@link Agents }
     * 
     */
    public Agents createAgents() {
        return new Agents();
    }

    /**
     * Create an instance of {@link Agents.Agent.AgentPolicy }
     * 
     */
    public Agents.Agent.AgentPolicy createAgentsAgentAgentPolicy() {
        return new Agents.Agent.AgentPolicy();
    }

    /**
     * Create an instance of {@link GlobalConfig.CredentialValidators }
     * 
     */
    public GlobalConfig.CredentialValidators createGlobalConfigCredentialValidators() {
        return new GlobalConfig.CredentialValidators();
    }

    /**
     * Create an instance of {@link GlobalConfig.Messages.Topic }
     * 
     */
    public GlobalConfig.Messages.Topic createGlobalConfigMessagesTopic() {
        return new GlobalConfig.Messages.Topic();
    }

    /**
     * Create an instance of {@link GlobalConfig.CredentialValidators.CredentialValidator.ProviderParams }
     * 
     */
    public GlobalConfig.CredentialValidators.CredentialValidator.ProviderParams createGlobalConfigCredentialValidatorsCredentialValidatorProviderParams() {
        return new GlobalConfig.CredentialValidators.CredentialValidator.ProviderParams();
    }

    /**
     * Create an instance of {@link GlobalConfig }
     * 
     */
    public GlobalConfig createGlobalConfig() {
        return new GlobalConfig();
    }

    /**
     * Create an instance of {@link Condition.Address }
     * 
     */
    public Condition.Address createConditionAddress() {
        return new Condition.Address();
    }

    /**
     * Create an instance of {@link GlobalConfig.Domain }
     * 
     */
    public GlobalConfig.Domain createGlobalConfigDomain() {
        return new GlobalConfig.Domain();
    }

    /**
     * Create an instance of {@link GlobalConfig.Messages.Queues }
     * 
     */
    public GlobalConfig.Messages.Queues createGlobalConfigMessagesQueues() {
        return new GlobalConfig.Messages.Queues();
    }

    /**
     * Create an instance of {@link GlobalConfig.AuthorizationProviders }
     * 
     */
    public GlobalConfig.AuthorizationProviders createGlobalConfigAuthorizationProviders() {
        return new GlobalConfig.AuthorizationProviders();
    }

    /**
     * Create an instance of {@link Policies.Policy.Acl.Entry }
     * 
     */
    public Policies.Policy.Acl.Entry createPoliciesPolicyAclEntry() {
        return new Policies.Policy.Acl.Entry();
    }

    /**
     * Create an instance of {@link GlobalConfig.Domain.Peer.Transport }
     * 
     */
    public GlobalConfig.Domain.Peer.Transport createGlobalConfigDomainPeerTransport() {
        return new GlobalConfig.Domain.Peer.Transport();
    }

    /**
     * Create an instance of {@link GlobalConfig.Domain.Peer }
     * 
     */
    public GlobalConfig.Domain.Peer createGlobalConfigDomainPeer() {
        return new GlobalConfig.Domain.Peer();
    }

    /**
     * Create an instance of {@link GlobalConfig.AuthorizationProviders.AuthorizationProvider.ProviderParams }
     * 
     */
    public GlobalConfig.AuthorizationProviders.AuthorizationProvider.ProviderParams createGlobalConfigAuthorizationProvidersAuthorizationProviderProviderParams() {
        return new GlobalConfig.AuthorizationProviders.AuthorizationProvider.ProviderParams();
    }

    /**
     * Create an instance of {@link Policies.Policy.Acl }
     * 
     */
    public Policies.Policy.Acl createPoliciesPolicyAcl() {
        return new Policies.Policy.Acl();
    }

    /**
     * Create an instance of {@link GlobalConfig.Messages }
     * 
     */
    public GlobalConfig.Messages createGlobalConfigMessages() {
        return new GlobalConfig.Messages();
    }

    /**
     * Create an instance of {@link GlobalConfig.CredentialValidators.CredentialValidator }
     * 
     */
    public GlobalConfig.CredentialValidators.CredentialValidator createGlobalConfigCredentialValidatorsCredentialValidator() {
        return new GlobalConfig.CredentialValidators.CredentialValidator();
    }

    /**
     * Create an instance of {@link GlobalConfig.AuthorizationProviders.AuthorizationProvider }
     * 
     */
    public GlobalConfig.AuthorizationProviders.AuthorizationProvider createGlobalConfigAuthorizationProvidersAuthorizationProvider() {
        return new GlobalConfig.AuthorizationProviders.AuthorizationProvider();
    }

    /**
     * Create an instance of {@link BrokerSecurityPolicy }
     * 
     */
    public BrokerSecurityPolicy createBrokerSecurityPolicy() {
        return new BrokerSecurityPolicy();
    }

    /**
     * Create an instance of {@link Condition }
     * 
     */
    public Condition createCondition() {
        return new Condition();
    }

}

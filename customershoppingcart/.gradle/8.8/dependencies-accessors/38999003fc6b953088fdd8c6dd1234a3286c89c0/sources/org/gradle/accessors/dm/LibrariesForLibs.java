package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final ComLibraryAccessors laccForComLibraryAccessors = new ComLibraryAccessors(owner);
    private final JakartaLibraryAccessors laccForJakartaLibraryAccessors = new JakartaLibraryAccessors(owner);
    private final JunitLibraryAccessors laccForJunitLibraryAccessors = new JunitLibraryAccessors(owner);
    private final OrgLibraryAccessors laccForOrgLibraryAccessors = new OrgLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Group of libraries at <b>com</b>
     */
    public ComLibraryAccessors getCom() {
        return laccForComLibraryAccessors;
    }

    /**
     * Group of libraries at <b>jakarta</b>
     */
    public JakartaLibraryAccessors getJakarta() {
        return laccForJakartaLibraryAccessors;
    }

    /**
     * Group of libraries at <b>junit</b>
     */
    public JunitLibraryAccessors getJunit() {
        return laccForJunitLibraryAccessors;
    }

    /**
     * Group of libraries at <b>org</b>
     */
    public OrgLibraryAccessors getOrg() {
        return laccForOrgLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class ComLibraryAccessors extends SubDependencyFactory {
        private final ComFasterxmlLibraryAccessors laccForComFasterxmlLibraryAccessors = new ComFasterxmlLibraryAccessors(owner);
        private final ComGoogleLibraryAccessors laccForComGoogleLibraryAccessors = new ComGoogleLibraryAccessors(owner);

        public ComLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.fasterxml</b>
         */
        public ComFasterxmlLibraryAccessors getFasterxml() {
            return laccForComFasterxmlLibraryAccessors;
        }

        /**
         * Group of libraries at <b>com.google</b>
         */
        public ComGoogleLibraryAccessors getGoogle() {
            return laccForComGoogleLibraryAccessors;
        }

    }

    public static class ComFasterxmlLibraryAccessors extends SubDependencyFactory {
        private final ComFasterxmlJacksonLibraryAccessors laccForComFasterxmlJacksonLibraryAccessors = new ComFasterxmlJacksonLibraryAccessors(owner);

        public ComFasterxmlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.fasterxml.jackson</b>
         */
        public ComFasterxmlJacksonLibraryAccessors getJackson() {
            return laccForComFasterxmlJacksonLibraryAccessors;
        }

    }

    public static class ComFasterxmlJacksonLibraryAccessors extends SubDependencyFactory {
        private final ComFasterxmlJacksonCoreLibraryAccessors laccForComFasterxmlJacksonCoreLibraryAccessors = new ComFasterxmlJacksonCoreLibraryAccessors(owner);

        public ComFasterxmlJacksonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.fasterxml.jackson.core</b>
         */
        public ComFasterxmlJacksonCoreLibraryAccessors getCore() {
            return laccForComFasterxmlJacksonCoreLibraryAccessors;
        }

    }

    public static class ComFasterxmlJacksonCoreLibraryAccessors extends SubDependencyFactory {
        private final ComFasterxmlJacksonCoreJacksonLibraryAccessors laccForComFasterxmlJacksonCoreJacksonLibraryAccessors = new ComFasterxmlJacksonCoreJacksonLibraryAccessors(owner);

        public ComFasterxmlJacksonCoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.fasterxml.jackson.core.jackson</b>
         */
        public ComFasterxmlJacksonCoreJacksonLibraryAccessors getJackson() {
            return laccForComFasterxmlJacksonCoreJacksonLibraryAccessors;
        }

    }

    public static class ComFasterxmlJacksonCoreJacksonLibraryAccessors extends SubDependencyFactory {

        public ComFasterxmlJacksonCoreJacksonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>databind</b> with <b>com.fasterxml.jackson.core:jackson-databind</b> coordinates and
         * with version reference <b>com.fasterxml.jackson.core.jackson.databind</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getDatabind() {
            return create("com.fasterxml.jackson.core.jackson.databind");
        }

    }

    public static class ComGoogleLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleCodeLibraryAccessors laccForComGoogleCodeLibraryAccessors = new ComGoogleCodeLibraryAccessors(owner);

        public ComGoogleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.google.code</b>
         */
        public ComGoogleCodeLibraryAccessors getCode() {
            return laccForComGoogleCodeLibraryAccessors;
        }

    }

    public static class ComGoogleCodeLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleCodeGsonLibraryAccessors laccForComGoogleCodeGsonLibraryAccessors = new ComGoogleCodeGsonLibraryAccessors(owner);

        public ComGoogleCodeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.google.code.gson</b>
         */
        public ComGoogleCodeGsonLibraryAccessors getGson() {
            return laccForComGoogleCodeGsonLibraryAccessors;
        }

    }

    public static class ComGoogleCodeGsonLibraryAccessors extends SubDependencyFactory {

        public ComGoogleCodeGsonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>gson</b> with <b>com.google.code.gson:gson</b> coordinates and
         * with version reference <b>com.google.code.gson.gson</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGson() {
            return create("com.google.code.gson.gson");
        }

    }

    public static class JakartaLibraryAccessors extends SubDependencyFactory {
        private final JakartaEjbLibraryAccessors laccForJakartaEjbLibraryAccessors = new JakartaEjbLibraryAccessors(owner);
        private final JakartaWsLibraryAccessors laccForJakartaWsLibraryAccessors = new JakartaWsLibraryAccessors(owner);
        private final JakartaXmlLibraryAccessors laccForJakartaXmlLibraryAccessors = new JakartaXmlLibraryAccessors(owner);

        public JakartaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.ejb</b>
         */
        public JakartaEjbLibraryAccessors getEjb() {
            return laccForJakartaEjbLibraryAccessors;
        }

        /**
         * Group of libraries at <b>jakarta.ws</b>
         */
        public JakartaWsLibraryAccessors getWs() {
            return laccForJakartaWsLibraryAccessors;
        }

        /**
         * Group of libraries at <b>jakarta.xml</b>
         */
        public JakartaXmlLibraryAccessors getXml() {
            return laccForJakartaXmlLibraryAccessors;
        }

    }

    public static class JakartaEjbLibraryAccessors extends SubDependencyFactory {
        private final JakartaEjbJakartaLibraryAccessors laccForJakartaEjbJakartaLibraryAccessors = new JakartaEjbJakartaLibraryAccessors(owner);

        public JakartaEjbLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.ejb.jakarta</b>
         */
        public JakartaEjbJakartaLibraryAccessors getJakarta() {
            return laccForJakartaEjbJakartaLibraryAccessors;
        }

    }

    public static class JakartaEjbJakartaLibraryAccessors extends SubDependencyFactory {
        private final JakartaEjbJakartaEjbLibraryAccessors laccForJakartaEjbJakartaEjbLibraryAccessors = new JakartaEjbJakartaEjbLibraryAccessors(owner);

        public JakartaEjbJakartaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.ejb.jakarta.ejb</b>
         */
        public JakartaEjbJakartaEjbLibraryAccessors getEjb() {
            return laccForJakartaEjbJakartaEjbLibraryAccessors;
        }

    }

    public static class JakartaEjbJakartaEjbLibraryAccessors extends SubDependencyFactory {

        public JakartaEjbJakartaEjbLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>jakarta.ejb:jakarta.ejb-api</b> coordinates and
         * with version reference <b>jakarta.ejb.jakarta.ejb.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("jakarta.ejb.jakarta.ejb.api");
        }

    }

    public static class JakartaWsLibraryAccessors extends SubDependencyFactory {
        private final JakartaWsRsLibraryAccessors laccForJakartaWsRsLibraryAccessors = new JakartaWsRsLibraryAccessors(owner);

        public JakartaWsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.ws.rs</b>
         */
        public JakartaWsRsLibraryAccessors getRs() {
            return laccForJakartaWsRsLibraryAccessors;
        }

    }

    public static class JakartaWsRsLibraryAccessors extends SubDependencyFactory {
        private final JakartaWsRsJakartaLibraryAccessors laccForJakartaWsRsJakartaLibraryAccessors = new JakartaWsRsJakartaLibraryAccessors(owner);

        public JakartaWsRsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.ws.rs.jakarta</b>
         */
        public JakartaWsRsJakartaLibraryAccessors getJakarta() {
            return laccForJakartaWsRsJakartaLibraryAccessors;
        }

    }

    public static class JakartaWsRsJakartaLibraryAccessors extends SubDependencyFactory {
        private final JakartaWsRsJakartaWsLibraryAccessors laccForJakartaWsRsJakartaWsLibraryAccessors = new JakartaWsRsJakartaWsLibraryAccessors(owner);

        public JakartaWsRsJakartaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.ws.rs.jakarta.ws</b>
         */
        public JakartaWsRsJakartaWsLibraryAccessors getWs() {
            return laccForJakartaWsRsJakartaWsLibraryAccessors;
        }

    }

    public static class JakartaWsRsJakartaWsLibraryAccessors extends SubDependencyFactory {
        private final JakartaWsRsJakartaWsRsLibraryAccessors laccForJakartaWsRsJakartaWsRsLibraryAccessors = new JakartaWsRsJakartaWsRsLibraryAccessors(owner);

        public JakartaWsRsJakartaWsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.ws.rs.jakarta.ws.rs</b>
         */
        public JakartaWsRsJakartaWsRsLibraryAccessors getRs() {
            return laccForJakartaWsRsJakartaWsRsLibraryAccessors;
        }

    }

    public static class JakartaWsRsJakartaWsRsLibraryAccessors extends SubDependencyFactory {

        public JakartaWsRsJakartaWsRsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>jakarta.ws.rs:jakarta.ws.rs-api</b> coordinates and
         * with version reference <b>jakarta.ws.rs.jakarta.ws.rs.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("jakarta.ws.rs.jakarta.ws.rs.api");
        }

    }

    public static class JakartaXmlLibraryAccessors extends SubDependencyFactory {
        private final JakartaXmlBindLibraryAccessors laccForJakartaXmlBindLibraryAccessors = new JakartaXmlBindLibraryAccessors(owner);

        public JakartaXmlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.xml.bind</b>
         */
        public JakartaXmlBindLibraryAccessors getBind() {
            return laccForJakartaXmlBindLibraryAccessors;
        }

    }

    public static class JakartaXmlBindLibraryAccessors extends SubDependencyFactory {
        private final JakartaXmlBindJakartaLibraryAccessors laccForJakartaXmlBindJakartaLibraryAccessors = new JakartaXmlBindJakartaLibraryAccessors(owner);

        public JakartaXmlBindLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.xml.bind.jakarta</b>
         */
        public JakartaXmlBindJakartaLibraryAccessors getJakarta() {
            return laccForJakartaXmlBindJakartaLibraryAccessors;
        }

    }

    public static class JakartaXmlBindJakartaLibraryAccessors extends SubDependencyFactory {
        private final JakartaXmlBindJakartaXmlLibraryAccessors laccForJakartaXmlBindJakartaXmlLibraryAccessors = new JakartaXmlBindJakartaXmlLibraryAccessors(owner);

        public JakartaXmlBindJakartaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.xml.bind.jakarta.xml</b>
         */
        public JakartaXmlBindJakartaXmlLibraryAccessors getXml() {
            return laccForJakartaXmlBindJakartaXmlLibraryAccessors;
        }

    }

    public static class JakartaXmlBindJakartaXmlLibraryAccessors extends SubDependencyFactory {
        private final JakartaXmlBindJakartaXmlBindLibraryAccessors laccForJakartaXmlBindJakartaXmlBindLibraryAccessors = new JakartaXmlBindJakartaXmlBindLibraryAccessors(owner);

        public JakartaXmlBindJakartaXmlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>jakarta.xml.bind.jakarta.xml.bind</b>
         */
        public JakartaXmlBindJakartaXmlBindLibraryAccessors getBind() {
            return laccForJakartaXmlBindJakartaXmlBindLibraryAccessors;
        }

    }

    public static class JakartaXmlBindJakartaXmlBindLibraryAccessors extends SubDependencyFactory {

        public JakartaXmlBindJakartaXmlBindLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>jakarta.xml.bind:jakarta.xml.bind-api</b> coordinates and
         * with version reference <b>jakarta.xml.bind.jakarta.xml.bind.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("jakarta.xml.bind.jakarta.xml.bind.api");
        }

    }

    public static class JunitLibraryAccessors extends SubDependencyFactory {

        public JunitLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>junit</b> with <b>junit:junit</b> coordinates and
         * with version reference <b>junit.junit</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit() {
            return create("junit.junit");
        }

    }

    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgJsonLibraryAccessors laccForOrgJsonLibraryAccessors = new OrgJsonLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.json</b>
         */
        public OrgJsonLibraryAccessors getJson() {
            return laccForOrgJsonLibraryAccessors;
        }

    }

    public static class OrgJsonLibraryAccessors extends SubDependencyFactory {

        public OrgJsonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>json</b> with <b>org.json:json</b> coordinates and
         * with version reference <b>org.json.json</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJson() {
            return create("org.json.json");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final ComVersionAccessors vaccForComVersionAccessors = new ComVersionAccessors(providers, config);
        private final JakartaVersionAccessors vaccForJakartaVersionAccessors = new JakartaVersionAccessors(providers, config);
        private final JunitVersionAccessors vaccForJunitVersionAccessors = new JunitVersionAccessors(providers, config);
        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com</b>
         */
        public ComVersionAccessors getCom() {
            return vaccForComVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.jakarta</b>
         */
        public JakartaVersionAccessors getJakarta() {
            return vaccForJakartaVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.junit</b>
         */
        public JunitVersionAccessors getJunit() {
            return vaccForJunitVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org</b>
         */
        public OrgVersionAccessors getOrg() {
            return vaccForOrgVersionAccessors;
        }

    }

    public static class ComVersionAccessors extends VersionFactory  {

        private final ComFasterxmlVersionAccessors vaccForComFasterxmlVersionAccessors = new ComFasterxmlVersionAccessors(providers, config);
        private final ComGoogleVersionAccessors vaccForComGoogleVersionAccessors = new ComGoogleVersionAccessors(providers, config);
        public ComVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.fasterxml</b>
         */
        public ComFasterxmlVersionAccessors getFasterxml() {
            return vaccForComFasterxmlVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com.google</b>
         */
        public ComGoogleVersionAccessors getGoogle() {
            return vaccForComGoogleVersionAccessors;
        }

    }

    public static class ComFasterxmlVersionAccessors extends VersionFactory  {

        private final ComFasterxmlJacksonVersionAccessors vaccForComFasterxmlJacksonVersionAccessors = new ComFasterxmlJacksonVersionAccessors(providers, config);
        public ComFasterxmlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.fasterxml.jackson</b>
         */
        public ComFasterxmlJacksonVersionAccessors getJackson() {
            return vaccForComFasterxmlJacksonVersionAccessors;
        }

    }

    public static class ComFasterxmlJacksonVersionAccessors extends VersionFactory  {

        private final ComFasterxmlJacksonCoreVersionAccessors vaccForComFasterxmlJacksonCoreVersionAccessors = new ComFasterxmlJacksonCoreVersionAccessors(providers, config);
        public ComFasterxmlJacksonVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.fasterxml.jackson.core</b>
         */
        public ComFasterxmlJacksonCoreVersionAccessors getCore() {
            return vaccForComFasterxmlJacksonCoreVersionAccessors;
        }

    }

    public static class ComFasterxmlJacksonCoreVersionAccessors extends VersionFactory  {

        private final ComFasterxmlJacksonCoreJacksonVersionAccessors vaccForComFasterxmlJacksonCoreJacksonVersionAccessors = new ComFasterxmlJacksonCoreJacksonVersionAccessors(providers, config);
        public ComFasterxmlJacksonCoreVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.fasterxml.jackson.core.jackson</b>
         */
        public ComFasterxmlJacksonCoreJacksonVersionAccessors getJackson() {
            return vaccForComFasterxmlJacksonCoreJacksonVersionAccessors;
        }

    }

    public static class ComFasterxmlJacksonCoreJacksonVersionAccessors extends VersionFactory  {

        public ComFasterxmlJacksonCoreJacksonVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.fasterxml.jackson.core.jackson.databind</b> with value <b>2.16.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getDatabind() { return getVersion("com.fasterxml.jackson.core.jackson.databind"); }

    }

    public static class ComGoogleVersionAccessors extends VersionFactory  {

        private final ComGoogleCodeVersionAccessors vaccForComGoogleCodeVersionAccessors = new ComGoogleCodeVersionAccessors(providers, config);
        public ComGoogleVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.google.code</b>
         */
        public ComGoogleCodeVersionAccessors getCode() {
            return vaccForComGoogleCodeVersionAccessors;
        }

    }

    public static class ComGoogleCodeVersionAccessors extends VersionFactory  {

        private final ComGoogleCodeGsonVersionAccessors vaccForComGoogleCodeGsonVersionAccessors = new ComGoogleCodeGsonVersionAccessors(providers, config);
        public ComGoogleCodeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.google.code.gson</b>
         */
        public ComGoogleCodeGsonVersionAccessors getGson() {
            return vaccForComGoogleCodeGsonVersionAccessors;
        }

    }

    public static class ComGoogleCodeGsonVersionAccessors extends VersionFactory  {

        public ComGoogleCodeGsonVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.google.code.gson.gson</b> with value <b>2.10.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getGson() { return getVersion("com.google.code.gson.gson"); }

    }

    public static class JakartaVersionAccessors extends VersionFactory  {

        private final JakartaEjbVersionAccessors vaccForJakartaEjbVersionAccessors = new JakartaEjbVersionAccessors(providers, config);
        private final JakartaWsVersionAccessors vaccForJakartaWsVersionAccessors = new JakartaWsVersionAccessors(providers, config);
        private final JakartaXmlVersionAccessors vaccForJakartaXmlVersionAccessors = new JakartaXmlVersionAccessors(providers, config);
        public JakartaVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.ejb</b>
         */
        public JakartaEjbVersionAccessors getEjb() {
            return vaccForJakartaEjbVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.jakarta.ws</b>
         */
        public JakartaWsVersionAccessors getWs() {
            return vaccForJakartaWsVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.jakarta.xml</b>
         */
        public JakartaXmlVersionAccessors getXml() {
            return vaccForJakartaXmlVersionAccessors;
        }

    }

    public static class JakartaEjbVersionAccessors extends VersionFactory  {

        private final JakartaEjbJakartaVersionAccessors vaccForJakartaEjbJakartaVersionAccessors = new JakartaEjbJakartaVersionAccessors(providers, config);
        public JakartaEjbVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.ejb.jakarta</b>
         */
        public JakartaEjbJakartaVersionAccessors getJakarta() {
            return vaccForJakartaEjbJakartaVersionAccessors;
        }

    }

    public static class JakartaEjbJakartaVersionAccessors extends VersionFactory  {

        private final JakartaEjbJakartaEjbVersionAccessors vaccForJakartaEjbJakartaEjbVersionAccessors = new JakartaEjbJakartaEjbVersionAccessors(providers, config);
        public JakartaEjbJakartaVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.ejb.jakarta.ejb</b>
         */
        public JakartaEjbJakartaEjbVersionAccessors getEjb() {
            return vaccForJakartaEjbJakartaEjbVersionAccessors;
        }

    }

    public static class JakartaEjbJakartaEjbVersionAccessors extends VersionFactory  {

        public JakartaEjbJakartaEjbVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>jakarta.ejb.jakarta.ejb.api</b> with value <b>4.0.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("jakarta.ejb.jakarta.ejb.api"); }

    }

    public static class JakartaWsVersionAccessors extends VersionFactory  {

        private final JakartaWsRsVersionAccessors vaccForJakartaWsRsVersionAccessors = new JakartaWsRsVersionAccessors(providers, config);
        public JakartaWsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.ws.rs</b>
         */
        public JakartaWsRsVersionAccessors getRs() {
            return vaccForJakartaWsRsVersionAccessors;
        }

    }

    public static class JakartaWsRsVersionAccessors extends VersionFactory  {

        private final JakartaWsRsJakartaVersionAccessors vaccForJakartaWsRsJakartaVersionAccessors = new JakartaWsRsJakartaVersionAccessors(providers, config);
        public JakartaWsRsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.ws.rs.jakarta</b>
         */
        public JakartaWsRsJakartaVersionAccessors getJakarta() {
            return vaccForJakartaWsRsJakartaVersionAccessors;
        }

    }

    public static class JakartaWsRsJakartaVersionAccessors extends VersionFactory  {

        private final JakartaWsRsJakartaWsVersionAccessors vaccForJakartaWsRsJakartaWsVersionAccessors = new JakartaWsRsJakartaWsVersionAccessors(providers, config);
        public JakartaWsRsJakartaVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.ws.rs.jakarta.ws</b>
         */
        public JakartaWsRsJakartaWsVersionAccessors getWs() {
            return vaccForJakartaWsRsJakartaWsVersionAccessors;
        }

    }

    public static class JakartaWsRsJakartaWsVersionAccessors extends VersionFactory  {

        private final JakartaWsRsJakartaWsRsVersionAccessors vaccForJakartaWsRsJakartaWsRsVersionAccessors = new JakartaWsRsJakartaWsRsVersionAccessors(providers, config);
        public JakartaWsRsJakartaWsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.ws.rs.jakarta.ws.rs</b>
         */
        public JakartaWsRsJakartaWsRsVersionAccessors getRs() {
            return vaccForJakartaWsRsJakartaWsRsVersionAccessors;
        }

    }

    public static class JakartaWsRsJakartaWsRsVersionAccessors extends VersionFactory  {

        public JakartaWsRsJakartaWsRsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>jakarta.ws.rs.jakarta.ws.rs.api</b> with value <b>3.1.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("jakarta.ws.rs.jakarta.ws.rs.api"); }

    }

    public static class JakartaXmlVersionAccessors extends VersionFactory  {

        private final JakartaXmlBindVersionAccessors vaccForJakartaXmlBindVersionAccessors = new JakartaXmlBindVersionAccessors(providers, config);
        public JakartaXmlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.xml.bind</b>
         */
        public JakartaXmlBindVersionAccessors getBind() {
            return vaccForJakartaXmlBindVersionAccessors;
        }

    }

    public static class JakartaXmlBindVersionAccessors extends VersionFactory  {

        private final JakartaXmlBindJakartaVersionAccessors vaccForJakartaXmlBindJakartaVersionAccessors = new JakartaXmlBindJakartaVersionAccessors(providers, config);
        public JakartaXmlBindVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.xml.bind.jakarta</b>
         */
        public JakartaXmlBindJakartaVersionAccessors getJakarta() {
            return vaccForJakartaXmlBindJakartaVersionAccessors;
        }

    }

    public static class JakartaXmlBindJakartaVersionAccessors extends VersionFactory  {

        private final JakartaXmlBindJakartaXmlVersionAccessors vaccForJakartaXmlBindJakartaXmlVersionAccessors = new JakartaXmlBindJakartaXmlVersionAccessors(providers, config);
        public JakartaXmlBindJakartaVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.xml.bind.jakarta.xml</b>
         */
        public JakartaXmlBindJakartaXmlVersionAccessors getXml() {
            return vaccForJakartaXmlBindJakartaXmlVersionAccessors;
        }

    }

    public static class JakartaXmlBindJakartaXmlVersionAccessors extends VersionFactory  {

        private final JakartaXmlBindJakartaXmlBindVersionAccessors vaccForJakartaXmlBindJakartaXmlBindVersionAccessors = new JakartaXmlBindJakartaXmlBindVersionAccessors(providers, config);
        public JakartaXmlBindJakartaXmlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.jakarta.xml.bind.jakarta.xml.bind</b>
         */
        public JakartaXmlBindJakartaXmlBindVersionAccessors getBind() {
            return vaccForJakartaXmlBindJakartaXmlBindVersionAccessors;
        }

    }

    public static class JakartaXmlBindJakartaXmlBindVersionAccessors extends VersionFactory  {

        public JakartaXmlBindJakartaXmlBindVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>jakarta.xml.bind.jakarta.xml.bind.api</b> with value <b>4.0.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("jakarta.xml.bind.jakarta.xml.bind.api"); }

    }

    public static class JunitVersionAccessors extends VersionFactory  {

        public JunitVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>junit.junit</b> with value <b>4.8.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getJunit() { return getVersion("junit.junit"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgJsonVersionAccessors vaccForOrgJsonVersionAccessors = new OrgJsonVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.json</b>
         */
        public OrgJsonVersionAccessors getJson() {
            return vaccForOrgJsonVersionAccessors;
        }

    }

    public static class OrgJsonVersionAccessors extends VersionFactory  {

        public OrgJsonVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.json.json</b> with value <b>20240303</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getJson() { return getVersion("org.json.json"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}

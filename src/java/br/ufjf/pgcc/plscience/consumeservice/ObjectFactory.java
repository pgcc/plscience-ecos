
package br.ufjf.pgcc.plscience.consumeservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client package. 
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

    private final static QName _KelvinToFahreinheithResponse_QNAME = new QName("http://webservice.plscience.pgcc.ufjf.br/", "kelvinToFahreinheithResponse");
    private final static QName _CelsiusToKelvinResponse_QNAME = new QName("http://webservice.plscience.pgcc.ufjf.br/", "CelsiusToKelvinResponse");
    private final static QName _CelsiusToKelvin_QNAME = new QName("http://webservice.plscience.pgcc.ufjf.br/", "CelsiusToKelvin");
    private final static QName _KelvinToFahreinheith_QNAME = new QName("http://webservice.plscience.pgcc.ufjf.br/", "kelvinToFahreinheith");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CelsiusToKelvin }
     * 
     */
    public CelsiusToKelvin createCelsiusToKelvin() {
        return new CelsiusToKelvin();
    }

    /**
     * Create an instance of {@link CelsiusToKelvinResponse }
     * 
     */
    public CelsiusToKelvinResponse createCelsiusToKelvinResponse() {
        return new CelsiusToKelvinResponse();
    }

    /**
     * Create an instance of {@link KelvinToFahreinheithResponse }
     * 
     */
    public KelvinToFahreinheithResponse createKelvinToFahreinheithResponse() {
        return new KelvinToFahreinheithResponse();
    }

    /**
     * Create an instance of {@link KelvinToFahreinheith }
     * 
     */
    public KelvinToFahreinheith createKelvinToFahreinheith() {
        return new KelvinToFahreinheith();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KelvinToFahreinheithResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.plscience.pgcc.ufjf.br/", name = "kelvinToFahreinheithResponse")
    public JAXBElement<KelvinToFahreinheithResponse> createKelvinToFahreinheithResponse(KelvinToFahreinheithResponse value) {
        return new JAXBElement<KelvinToFahreinheithResponse>(_KelvinToFahreinheithResponse_QNAME, KelvinToFahreinheithResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CelsiusToKelvinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.plscience.pgcc.ufjf.br/", name = "CelsiusToKelvinResponse")
    public JAXBElement<CelsiusToKelvinResponse> createCelsiusToKelvinResponse(CelsiusToKelvinResponse value) {
        return new JAXBElement<CelsiusToKelvinResponse>(_CelsiusToKelvinResponse_QNAME, CelsiusToKelvinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CelsiusToKelvin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.plscience.pgcc.ufjf.br/", name = "CelsiusToKelvin")
    public JAXBElement<CelsiusToKelvin> createCelsiusToKelvin(CelsiusToKelvin value) {
        return new JAXBElement<CelsiusToKelvin>(_CelsiusToKelvin_QNAME, CelsiusToKelvin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KelvinToFahreinheith }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.plscience.pgcc.ufjf.br/", name = "kelvinToFahreinheith")
    public JAXBElement<KelvinToFahreinheith> createKelvinToFahreinheith(KelvinToFahreinheith value) {
        return new JAXBElement<KelvinToFahreinheith>(_KelvinToFahreinheith_QNAME, KelvinToFahreinheith.class, null, value);
    }

}

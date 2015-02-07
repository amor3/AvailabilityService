
package service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service package. 
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

    private final static QName _CheckPrice_QNAME = new QName("http://service/", "checkPrice");
    private final static QName _CheckPriceResponse_QNAME = new QName("http://service/", "checkPriceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckPrice }
     * 
     */
    public CheckPrice createCheckPrice() {
        return new CheckPrice();
    }

    /**
     * Create an instance of {@link CheckPriceResponse }
     * 
     */
    public CheckPriceResponse createCheckPriceResponse() {
        return new CheckPriceResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckPrice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "checkPrice")
    public JAXBElement<CheckPrice> createCheckPrice(CheckPrice value) {
        return new JAXBElement<CheckPrice>(_CheckPrice_QNAME, CheckPrice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckPriceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "checkPriceResponse")
    public JAXBElement<CheckPriceResponse> createCheckPriceResponse(CheckPriceResponse value) {
        return new JAXBElement<CheckPriceResponse>(_CheckPriceResponse_QNAME, CheckPriceResponse.class, null, value);
    }

}

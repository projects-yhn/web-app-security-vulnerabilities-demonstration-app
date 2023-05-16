package com.yhn.webappcyberattacksdemoapp.xxe;

import com.yhn.webappcyberattacksdemoapp.xxe.model.Product;
import com.yhn.webappcyberattacksdemoapp.xxe.model.XXEProductRequest;
import com.yhn.webappcyberattacksdemoapp.xxe.model.XXEProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class XXEService {

    private final ProductRepository productRepository;

    public XXEProductResponse updateXXEVulnerableProductWithXML(Long productId, String updateData) {
        Product product = productRepository.findById(productId).get();

        StringReader stringReader = new StringReader(updateData);
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(XXEProductRequest.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        XMLInputFactory inputFactory = XMLInputFactory.newFactory();
        inputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, true);
        XMLStreamReader streamReader = null;
        try {
            streamReader = inputFactory.createXMLStreamReader(stringReader);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        XXEProductRequest xxeProductRequest = null;
        try {
            xxeProductRequest = (XXEProductRequest) unmarshaller.unmarshal(streamReader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        if (xxeProductRequest.getCategory() != null) {
            product.setCategory(xxeProductRequest.getCategory());
        }
        if (xxeProductRequest.getName() != null) {
            product.setName(xxeProductRequest.getName());
        }
        if (xxeProductRequest.getDescription() != null) {
            product.setDescription(xxeProductRequest.getDescription());
        }
        if (xxeProductRequest.getPrice() != null) {
            product.setPrice(BigDecimal.valueOf(xxeProductRequest.getPrice()));
        }
        Product savedProduct  = productRepository.save(product);

        return new XXEProductResponse(savedProduct.getId(), savedProduct.getName(), savedProduct.getCategory(),
                savedProduct.getImageUrl(), savedProduct.getPrice(), savedProduct.getDescription());

    }

    public List<XXEProductResponse> getAllXXEVulnerableProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new XXEProductResponse(product.getId(), product.getName(),
                        product.getCategory(), product.getImageUrl(), product.getPrice(), product.getDescription()))
                .collect(Collectors.toList());
    }

    public XXEProductResponse getXXEVulnerableProduct(Long productId) {
        Product product = productRepository.findById(productId).get();
        return new XXEProductResponse(product.getId(), product.getName(),
                product.getCategory(), product.getImageUrl(), product.getPrice(), product.getDescription());
    }

}

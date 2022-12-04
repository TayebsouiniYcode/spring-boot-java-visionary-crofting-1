package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.classes.Message;
import com.youcode.visionarycrofting.entity.Product;
import com.youcode.visionarycrofting.entity.Stock;
import com.youcode.visionarycrofting.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List< Product> getProducts ( ) {
        return productRepository.findAll ();
    }

    public Product addProduct ( Product product ) {
        Message message = new Message (  );
        Optional<Product> productOptional = productRepository.findProductByProductReference ( product.getProductReference () );
        if (productOptional.isPresent ()){
            message.setState ( "Info" );
            message.setMessage ( "Product is aready with this reference, please change a reference or update this product" );
            product.setMessage ( message );
            return product;
        } else {
            message.setState ( "Success" );
            message.setMessage ( "Product has ben created" );
            product.setMessage ( message );
            productRepository.save ( product );
            return product;
        }

    }

    public Product updateProduct ( Product product ) {
        Optional<Product> productOptional = productRepository.findById ( product.getId () );

        if (productOptional.isPresent () ){
            if (!product.getName ().isEmpty ()
                    || product.getName () != null)
                productOptional.get ().setName ( product.getName ());

            if (!product.getDescription ().isEmpty ()
                    || product.getDescription () != null)
                productOptional.get ().setDescription ( product.getDescription ());

            if (!product.getCategory ().isEmpty ()
                    || product.getCategory () != null)
                productOptional.get ().setCategory ( product.getCategory ());

            if (product.getQuantity () != null
                    && productOptional.get ().getQuantity () != null
                    && product.getQuantity () >= 0 )
                productOptional.get ().setQuantity ( productOptional.get().getQuantity () + product.getQuantity ());

            if (productOptional.get().getQuantity () == null
                    && product.getQuantity () != null
                    && product.getQuantity () >= 0)
                productOptional.get ().setQuantity ( product.getQuantity ());

            if (productOptional.get ().getQuantity () >= 0
                    && product.getQuantity () > 0 )
                productOptional.get ().setQuantity ( productOptional.get().getQuantity () + product.getQuantity ());

            if (product.getMinQuantity () >= 0
                    || product.getName () != null)
                productOptional.get ().setMinQuantity ( product.getMinQuantity ());

            productRepository.save ( productOptional.get() );

            Message message = new Message ();
            message.setState ( "Success" );
            message.setMessage ( "Product up to date" );
            productOptional.get ().setMessage ( message );

            return productOptional.get ();
        } else {
            Message message = new Message ();
            message.setMessage ( "Product is not exists" );
            message.setState ( "Error" );
            product.setMessage ( message );

            return product;
        }

    }

    public Message deleteProduct ( Long id ) {
        Message message = new Message (  );
        Boolean exists = productRepository.existsById(id);
        if(!exists)
        {
            message.setState ( "Info" );
            message.setMessage ( "Product is not exists in the stock" );

            return message;
        } else {
            try {
                productRepository.deleteById(id);
                message.setState ( "Success" );
                message.setMessage ( "Prodact has ben deleted" );

                return message;
            } catch (Exception e){
                message.setState ( "Error" );
                message.setMessage ( "Exception : " + e.toString () );

                return message;
            }
        }
    }

    public Product getProductByReference(String reference){
        Message message = new Message ();
        Optional<Product> productOptional =  productRepository.findProductByProductReference (reference);

        if (productOptional.isPresent ()){
            message.setState ( "Success" );
            message.setMessage ( "Product details is present" );
            productOptional.get ().setMessage ( message );
            return productOptional.get ();
        } else {
            message.setState ( "Error" );
            message.setMessage ( "Product reference is not exists" );
            Product product = new Product (  );
            product.setMessage ( message );
            return product;
        }
    }

    public Product addProductInStock(Product product, Stock stock) {
        product.setStockList ( stock );
        System.out.println ( product.toString () );
        return addProduct ( product );
    }

}

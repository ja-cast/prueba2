package com.lifebank.component.parser;

import com.lifebank.entity.PrdXPty;
import com.lifebank.pojo.response.products.IProductsResponse;
import com.lifebank.pojo.response.products.ProductDetail;
import com.lifebank.pojo.response.products.ProductResponseNode;
import com.lifebank.pojo.response.products.ProductsResponse;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class ProductsParser implements IProductsParser {
    private Integer existsPos;
    private Iterator<PrdXPty> itr;
    private ProductsResponse response;

    public ProductsParser() {
        this.existsPos = -1;
    }

    public IProductsResponse parse(Iterable<PrdXPty> records){
        itr = records.iterator();
        this.response = new ProductsResponse();

        while(itr.hasNext()){
            //Obtengo la siguiente fila de la BDD
            PrdXPty dbProduct = itr.next();

            //Inicializo el producto a agregar
            ProductDetail prc = new ProductDetail();
            prc.setId(dbProduct.getProductId());
            prc.setName(dbProduct.getProductName());

            //Valido si el tipo de producto ya existe, si no existe lo agrego
            existsPos = existsProductType(response.getProductNodes(),dbProduct.getProductTypeName());
            if(existsPos == -1) {
                //Inicializo el nodo del siguiente tipo de producto
                ProductResponseNode prcNd = new ProductResponseNode();
                prcNd.setProductName(dbProduct.getProductTypeName());
                prcNd.getProducts().add(prc);

                //Agregando nuevo tipo de producto
                response.getProductNodes().add(prcNd);
                //Si ya existe el tipo de producto, solo agrego el producto
            }else{
                //Agregando producto
                response.getProductNodes().get(existsPos).getProducts().add(prc);
            }
        }

        return response;
    }

    private Integer existsProductType(List<ProductResponseNode> list, String productType){
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getProductName().equals(productType)){
                return i;
            }
        }
        return -1;
    }
}
